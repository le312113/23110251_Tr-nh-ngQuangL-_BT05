package com.demo.Controller;


import com.demo.Entity.Category;
import com.demo.Entity.Video;
import com.demo.Service.CategoryServiceImplement;
import com.demo.Service.VideoServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VideoController {
    @Autowired
    private VideoServiceImplement videoService;
    @Autowired
    private CategoryServiceImplement categoryService;

    @GetMapping("/admin/video/add")
    public String addPage(Model model){
        Video v = new Video();
        v.setCategory(new Category());
        v.getCategory().setCate_id(-1);
        model.addAttribute("video", v);
        model.addAttribute("categories", categoryService.getAllCategory());
        return "admin/video/add_video";
    }
    @GetMapping("/admin/video/edit")
    public String editPage(@RequestParam("id") int id, Model model){
        model.addAttribute("video",videoService.findById(id));
        model.addAttribute("categories", categoryService.getAllCategory());
        return "admin/video/edit_video";
    }
    @PostMapping("/admin/video/add")
    public String addVideo(@ModelAttribute("video") Video video){
        int cateId = video.getCategory().getCate_id();
        if (cateId == -1) { // 👈 chưa chọn danh mục
            return "redirect:/admin/video/add";
        }
        Category cat = categoryService.findById(cateId);
        video.setCategory(cat);
        videoService.insertVideo(video);
        return "redirect:/admin/video";
    }
    @PostMapping("/admin/video/edit")
    public String editVideo(@ModelAttribute("video") Video video){
        int cateId = video.getCategory().getCate_id();
        Category category = categoryService.findById(cateId);
        video.setCategory(category);
        videoService.editVideo(video);
        return "redirect:/admin/video";
    }
    @PostMapping("/admin/video/delete")
    public String deleteVideo(@RequestParam("id") int id){
        videoService.deleteVideo(id);
        System.out.println("đã xóa"+id);
        return "redirect:/admin/video";
    }
    @GetMapping("/admin/video/search")
    public String searchVideos(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("videos", videoService.searchByTitle(keyword));
        return "admin/video/list_video";
    }
}
