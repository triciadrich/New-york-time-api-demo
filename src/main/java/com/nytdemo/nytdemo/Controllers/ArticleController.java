package com.nytdemo.nytdemo.Controllers;

import com.nytdemo.nytdemo.Models.Thumbnail;
import com.nytdemo.nytdemo.Services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArticleController {

    @Autowired
    ArticleService articleService;
    Thumbnail thumbnail;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("articleList", articleService.getMostPopular());

        return "index";
    }
}
