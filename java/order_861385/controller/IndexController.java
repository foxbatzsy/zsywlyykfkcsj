package order_861385.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import order_861385.entity.Catalogue;
import order_861385.entity.Commodity;
import order_861385.entity.Shopping;
import order_861385.entity.User;
import order_861385.service.*;
import order_861385.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 首页
 *
 * @ClassName: IndexController
 * @Author: liunian
 * @Date: 2021/12/12 13:56
 */
@Controller
public class IndexController {

    @Autowired
    private ICatalogueService catalogueService;
    @Autowired
    private ICommodityService commodityService;
    @Autowired
    private IShoppingService shoppingService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private ILogService logService;
    @Autowired
    private IUserService userService;
    @Autowired
    private HttpSession session;

    @GetMapping("/admin/index")
    public String adminIndex(Model model) {
        return "admin/index";
    }

    @GetMapping("/index")
    public String index(Model model, String sea) {
        List<Catalogue> list = catalogueService.list();
        Map<String, Object> map = new HashMap<>();
        if (null != sea && !"".equals(sea)) {
            List<Commodity> c_id = commodityService.list(new QueryWrapper<Commodity>().like("name", sea));
            map.put("ca_1",c_id);
        } else {
            list.forEach(catalogue -> {
                QueryWrapper<Commodity> query = new QueryWrapper<>();
                List<Commodity> c_id = commodityService.list(query.eq("c_id", catalogue.getId()));
                map.put("ca_" + catalogue.getId(), c_id);
            });
        }
        User user = (User) session.getAttribute("user");
        List<Shopping> shlist = new ArrayList<>();
        if (user != null) {
            QueryWrapper<Shopping> query = new QueryWrapper<>();
            query.eq("u_id", user.getId());
            shlist = shoppingService.list(query);
        }

        model.addAttribute("sh", shlist);
        model.addAttribute("user", user);
        model.addAttribute("ca", list);
        model.addAttribute("coms", map);
        return "client/index";
    }

    @GetMapping("/all")
    @ResponseBody
    public Map<String, Object> all() {
        List<DateUtil> num = userService.num();
        List<DateUtil> num1 = orderService.num();
        List<DateUtil> num2 = logService.num();
        HashMap<String, Object> map = new HashMap<>();
        map.put("order", num1);
        map.put("user", num);
        map.put("log", num2);
        return map;
    }
}
