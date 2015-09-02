package colin.web.homework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by DELL on 2015/9/2.
 */
@Controller
@RequestMapping("blog")
public class BlogIndexController {
    /**
     * 显示博客首页
     * @return
     */
    @RequestMapping(value = "blog_index.html")
    public String showBlogIndex(){
        return "blog_index";
    }
}
