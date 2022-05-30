package order_861385.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.org.apache.xpath.internal.operations.Or;
import order_861385.entity.Order;
import order_861385.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author LN
 * @since 2021-12-11
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping("/admin/getAll")
    public String getAll(Model model){
        List<Order> list = orderService.list(new QueryWrapper<Order>().orderByDesc("time"));
        model.addAttribute("orders",list);
        return "admin/order/getAll";
    }

    @PostMapping("/insert")
    @ResponseBody
    public Map<Object,Object> insert(Order order){
        HashMap<Object, Object> map = new HashMap<>();
        boolean save = orderService.save(order);
        if (save) {
            map.put("msg", "订单已生成");
            map.put("code",1);
        }else{
            map.put("msg","订单生成失败");
            map.put("code",0);
        }
        return map;
    }
    @GetMapping("/delete/{id}")
    @ResponseBody
    public Map<Object,Object> delete(@PathVariable String id){
        boolean b = orderService.removeById(id);
        HashMap<Object, Object> map = new HashMap<>();
        if (b){
            map.put("code",1);
        }else
            map.put("code",0);
        return map;
    }

}

