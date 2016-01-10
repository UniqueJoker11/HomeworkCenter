package colin.web.homework.controller.blog;

import colin.web.homework.controller.BaseController;
import colin.web.homework.core.pojo.Homework_Aticle_Entity;
import colin.web.homework.core.vo.HomeworkAticleVo;
import colin.web.homework.service.AticleService;
import colin.web.homework.service.NavManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by DELL on 2015/9/2.
 */
@Controller
@RequestMapping("blog")
public class BlogIndexController extends BaseController {
    private final static int initIndex = 1;
    private final static int pageSize = 6;

    @Autowired
    private AticleService aticleService;
    @Autowired
    private NavManageService navManageService;

    /**
     * 显示博客首页
     * 首页的显示规则，按照导航分类，然后每一个分类中显示5条内容，点击查看更多跳转至相应的页面
     *
     * @return
     */
    @RequestMapping(value = "blog_index.html", method = RequestMethod.GET)
    public String showBlogIndex(HttpServletRequest request) {
        request.setAttribute("index",1);
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
        if (pageIndex <= 0) {
            pageIndex = initIndex;
        }
        return aticleService.findAllAticleInfoByPage(pageIndex, pageSize);
    }

}
