package order_861385.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import order_861385.entity.Log;
import order_861385.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 浏览日志 前端控制器
 * </p>
 *
 * @author LN
 * @since 2021-12-11
 */
@Controller
@RequestMapping("/log")
public class LogController {

    @Autowired
    private ILogService logService;

    @GetMapping("/admin/getAll")
    public String getAll(Model model){
        List<Log> list = logService.list(new QueryWrapper<Log>().orderByDesc("id"));
        model.addAttribute("logs",list);
        return "admin/log/getAll";
    }

    @PostMapping("/insert")
    public void insert(Log log){
        logService.save(log);
    }

}

