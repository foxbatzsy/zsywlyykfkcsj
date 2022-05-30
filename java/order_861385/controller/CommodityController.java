package order_861385.controller;


import order_861385.entity.Catalogue;
import order_861385.entity.Commodity;
import order_861385.service.ICatalogueService;
import order_861385.service.ICommodityService;
import order_861385.utils.ImgUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author LN
 * @since 2021-12-11
 */
@Controller
@RequestMapping("/commodity")
public class CommodityController {

    @Autowired
    private ICommodityService commodityService;
    @Autowired
    private ICatalogueService catalogueService;
    @Value("${ImagePath}")
    public String imagePath;

    @GetMapping("/admin/getAll")
    public String getAll(Model model){
        List<Commodity> list = commodityService.list();
        list.forEach(commodity -> {
            commodity.setCaName(catalogueService.getById(commodity.getcId()).getName());
        });
        model.addAttribute("Commoditys",list);
        return "admin/commodity/getAll";
    }
    @GetMapping("/admin/insert")
    public String insert(Model model){
        List<Catalogue> list = catalogueService.list();
        model.addAttribute("calist",list);
        return "admin/commodity/insert";
    }
    @PostMapping("/admin/insert")
    public String insert_p(Commodity commodity, @RequestParam("url") List<MultipartFile> url, Model model){
        if (null==url)
            return "admin/commodity/insert";
        for (int i=0;i<url.size();i++){
            if (null==url.get(i).getOriginalFilename()||"".equals(url.get(i).getOriginalFilename()))
                break;
            String img = ImgUpload.saveImg(url.get(i),imagePath);
            if (null==img)
                break;
            switch (i){
                case 0:commodity.setUrl1(img);break;
                case 1:commodity.setUrl2(img);break;
                case 2:commodity.setUrl3(img);break;
                case 3:commodity.setUrl4(img);break;
                case 4:commodity.setUrl5(img);break;
                default:
                    break;
            }
        }
        boolean save = commodityService.save(commodity);
        if (save)
            return "redirect:/commodity/admin/getAll";
        else
            model.addAttribute("msg","添加失败");
        return "admin/commodity/insert";
    }
    @GetMapping("/admin/update/{id}")
    public String update(@PathVariable String id, Model model){
        List<Catalogue> list = catalogueService.list();
        Commodity byId = commodityService.getById(id);
        model.addAttribute("com",byId);
        model.addAttribute("calist",list);
        return "admin/commodity/update";
    }

    @PostMapping("/admin/update")
    public String update_p(Commodity commodity, @RequestParam("url") List<MultipartFile> url,Model model){
        if (null==url)
            return "admin/commodity/insert";
        for (int i=0;i<url.size();i++){
            if (null==url.get(i).getOriginalFilename()||"".equals(url.get(i).getOriginalFilename()))
                break;
            String img = ImgUpload.saveImg(url.get(i),imagePath);
            if (null==img)
                break;
            switch (i){
                case 0:commodity.setUrl1(img);break;
                case 1:commodity.setUrl2(img);break;
                case 2:commodity.setUrl3(img);break;
                case 3:commodity.setUrl4(img);break;
                case 4:commodity.setUrl5(img);break;
                default:
                    break;
            }
        }
        boolean b = commodityService.updateById(commodity);
        if (b)
            return "redirect:/commodity/admin/getAll";
        else
            model.addAttribute("msg","修改失败");
        return "admin/commodity/update";
    }

    @GetMapping("/admin/delete/{id}")
    public String delete(@PathVariable String id,Model model){
        Commodity byId = commodityService.getById(id);
        byId.setDelFlag(1);
        boolean b = commodityService.updateById(byId);
        return "redirect:/commodity/admin/getAll";
    }

}

