package order_861385.controller;


import order_861385.entity.Catalogue;
import order_861385.service.ICatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 目录表 前端控制器
 * </p>
 *
 * @author LN
 * @since 2021-12-11
 */
@Controller
@RequestMapping("/admin/catalogue")
public class CatalogueController {

    @Autowired
    private ICatalogueService catalogueService;

    @GetMapping("/getAll")
    public String getAll(Model model){
        List<Catalogue> list = catalogueService.list();
        return "admin/catalogue/index";
    }

    @GetMapping("/save")
    public String save_g(){
        return "admin/catalogue/save";
    }
    @PostMapping("/save")
    public String save(Catalogue catalogue,Model model){
        boolean save = catalogueService.save(catalogue);
        if (save){
            return "redirect:/admin/catalogue/getAll";
        }else{
            model.addAttribute("msg","添加目录失败");
          return "redirect:/admin/catalogue/save";
        }
    }

    @GetMapping("/update")
    public String update_g(){
        return "admin/catalogue/update";
    }
    @PostMapping("/update")
    public String update(Catalogue catalogue,Model model){
        boolean save = catalogueService.updateById(catalogue);
        if (save){
            return "redirect:/admin/catalogue/getAll";
        }else{
            model.addAttribute("msg","修改目录失败");
            return "redirect:/admin/catalogue/update";
        }
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public Map<Object,Object> delete(@PathVariable String id){
        Map<Object, Object> map = new HashMap<>();
        boolean b = catalogueService.removeById(id);
        if (b){
            map.put("msg","删除成功");
        }else{
            map.put("msg","删除失败");
        }
        return map;
    }

}

