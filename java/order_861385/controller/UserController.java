package order_861385.controller;


import order_861385.entity.User;
import order_861385.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author LN
 * @since 2021-12-11
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/admin/getAll")
    public String getAll(Model model){
        List<User> list = userService.list();
        model.addAttribute("users",list);
        return "admin/user/getAll";
    }

    @GetMapping("/zhuce")
    public String zhuce(){
        return "client/zhuce";
    }
    @PostMapping("/zhuce")
    public String zhuce_p(User user){
        boolean save = userService.save(user);
        if (save)
            return "redirect:/index";
        else
            return "redirect:/zhuce";
    }

}

