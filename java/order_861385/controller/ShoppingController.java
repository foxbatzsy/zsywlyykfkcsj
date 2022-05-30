package order_861385.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import order_861385.entity.*;
import order_861385.service.ICommodityService;
import order_861385.service.ILogService;
import order_861385.service.IOrderService;
import order_861385.service.IShoppingService;
import order_861385.utils.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 购物车表 前端控制器
 * </p>
 *
 * @author LN
 * @since 2021-12-11
 */
@Controller
@RequestMapping("/shopping")
public class ShoppingController {

    @Autowired
    private IShoppingService shoppingService;
    @Autowired
    private ICommodityService commodityService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private ILogService logService;
    @Autowired
    private Email email;
    @Autowired
    private HttpSession session;

    @GetMapping("/getShopping/{id}")
    @ResponseBody
    public Map<String,Object> shopping(@PathVariable String id){
        QueryWrapper<Shopping> query = new QueryWrapper<>();
        query.eq("u_id",id);
        List<Shopping> list = shoppingService.list(query);
        HashMap<String, Object> map = new HashMap<>();
        map.put("data",list);
        return map;
    }

    @GetMapping("/insert/{id}")
    @ResponseBody
    public Map<String, Object> insert(@PathVariable Integer id) {
        User user = (User)session.getAttribute("user");

        Map<String,Object> map=new HashMap<>();
        if (null == user) {
            map.put("code", 0);
            map.put("msg","未登录");
            return map;
        }
        Commodity byId = commodityService.getById(id);
        Shopping c_id = shoppingService.getOne(new QueryWrapper<Shopping>().eq("c_id", id));
        boolean flag=false;
        if (c_id==null) {
            c_id = new Shopping();
            c_id.setcId(id);
            c_id.setcName(byId.getName());
            c_id.setNumber(1);
            c_id.setSpend(byId.getPrice());
            c_id.setuId(user.getId());
            flag = shoppingService.save(c_id);
        }else{
            c_id.setNumber(c_id.getNumber()+1);
            c_id.setSpend(c_id.getSpend().add(byId.getPrice()));
            flag=shoppingService.updateById(c_id);
        }

        Log log=new Log();
        log.setcId(c_id.getcId());
        log.setcName(c_id.getcName());
        log.setuId(user.getId());
        log.setuName(user.getName());
        logService.save(log);

        if (flag){
            map.put("code",1);
            map.put("msg","已添加");
        }else{
            map.put("code",0);
            map.put("msg","添加失败");
        }
        return map;
    }

    @GetMapping("/clear/{id}")
    public String clear(@PathVariable Integer id){
        boolean u_id = shoppingService.remove(new QueryWrapper<Shopping>().eq("u_id", id));
        return "redirect:/index";
    }
    @GetMapping("/confirm/{id}")
    public String confirm(@PathVariable String id){
        Order byId = orderService.getById(id);
        byId.setIsConf(1);
        orderService.updateById(byId);
        return "/client/confirm";
    }
    @PostMapping("/jiesuan/{id}")
    @ResponseBody
    public Map<String,Object> jesuan(@PathVariable String id,@RequestParam("address") String address){
        HashMap<String, Object> map = new HashMap<>();

        QueryWrapper<Shopping> query = new QueryWrapper<>();
        query.eq("u_id",id);
        Shopping one = shoppingService.getOne(query);

        if (one==null){
            map.put("msg","请先加入购物车");
            return map;
        }
        User user = (User) session.getAttribute("user");
        if (user==null){
            map.put("msg","请登录");
            return map;
        }


        Order order = new Order();
        order.setcId(one.getcId());
        order.setcName(one.getcName());
        order.setNumber(one.getNumber());
        order.setTime(LocalDateTime.now());
        order.setuName(user.getUsername());
        order.setuId(one.getId());
        order.setAddress(address);
        order.setIsConf(2);
        order.setIsPay(1);
        order.setSpend(one.getSpend().multiply(BigDecimal.valueOf(one.getNumber())));


        try {
            orderService.save(order);
            shoppingService.removeById(one.getId());
            email.sendSimpleMail(user.getEmil(),order.getId());
            map.put("msg","请在邮箱确认");
            map.put("code",1);
        }catch (Exception e){
            map.put("msg","错误");
            map.put("code",0);
        }
        return map;
    }

}

