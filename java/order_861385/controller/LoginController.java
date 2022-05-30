package order_861385.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import order_861385.entity.User;
import order_861385.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录 注销 注册
 *
 * @ClassName: LoginController
 * @Author: liunian
 * @Date: 2021/12/12 13:33
 */
@Controller
//@RequestMapping("")
public class LoginController {
    @Autowired
    private IUserService userService;
    @Autowired
    private HttpSession session;

    @GetMapping("/admin/login")
    public String login_get(Model model){
        model.addAttribute("msg",null);
        return "admin/login";
    }
    @PostMapping("/admin/login")
    public String login(String username, String password, Model model){
//        User one = userService.getOne(new QueryWrapper<User>().eq("username", username).eq("password", password));

        if ("admin".equals(username)&&"admin".equals(password)){
            session.setAttribute("admin","admin");
            return "redirect:/index";
        }else{
            model.addAttribute("msg","账号或密码不正确");
            return "/admin/login";
        }
    }
    @GetMapping("/admin/logOut")
    public String logout(){
        session.removeAttribute("admin");
        return "redirect:/admin/login";
    }

    @PostMapping("/client/login")
    @ResponseBody
    public Map<String,Object> c_login(User user){
        User one = userService.getOne(new QueryWrapper<User>().eq("username", user.getUsername()).eq("password", user.getPassword()));
        Map<String,Object> map=new HashMap<>();
        if (null==one){
            map.put("code",0);
            map.put("msg","用户名或密码错误");
        }else{
            map.put("code",1);
            session.setAttribute("user",one);
            map.put("msg","登录成功");
        }
        return map;
    }

    @GetMapping("/client/logout")
    public String c_logout(){
        session.removeAttribute("user");
        return "redirect:/index";
    }
}
