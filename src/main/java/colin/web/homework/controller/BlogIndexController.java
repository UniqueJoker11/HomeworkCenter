package colin.web.homework.controller;

import colin.web.homework.service.AticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by DELL on 2015/9/2.
 */
@Controller
@RequestMapping("blog")
public class BlogIndexController {
    private final static int initIndex = 0;
    private final static int pageSize = 6;

    @Autowired
    private AticleService aticleService;

    /**
     * 显示博客首页
     *
     * @return
     */
    @RequestMapping(value = "blog_index.html", method = RequestMethod.GET)
    public String showBlogIndex() {
        return "blog_index";
    }

    /**
     * 分页查询博客内容
     *
     * @param pageIndex
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "find_blog_info_list.action", method = RequestMethod.POST)
    public Object findAllBlogInfoListByPage(@RequestParam int pageIndex) {
        if (pageIndex < 0) {
            pageIndex = initIndex;
        }
        return aticleService.findAllAticleInfoByPage(pageIndex, pageSize);
    }

    /**
     * 显示博文的具体页面
     * @param aticleId
     * @return
     */
    @RequestMapping(value = "findBlogDetail.html",method = RequestMethod.GET)
    public String findBlogInfoDetail(@RequestParam String aticleId){
        //加载博文详情，然后自增博文阅读次数，加载文章评论
        return "blog_detail";
    }
}
