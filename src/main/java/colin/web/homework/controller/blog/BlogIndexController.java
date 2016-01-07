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
     *
     * @return
     */
    @RequestMapping(value = "blog_index.html", method = RequestMethod.GET)
    public String showBlogIndex(HttpServletRequest request) {
        request.setAttribute("index",1);
        return "blog_index";
    }

    /**
     * 显示博文详情页面
     *
     * @return
     */
    @RequestMapping(value = "blog_detail.html", method = RequestMethod.GET)
    public String showBlogDetail(HttpServletRequest request,@RequestParam String aticleId) {
        request.setAttribute("index",2);
        //根据id加载文章详情
        Homework_Aticle_Entity aticleEntity = aticleService.findAticleDetailInfo(aticleId);
        super.getRequestObj().setAttribute("aticle", aticleEntity);
        //获取相似文章推荐列表，根绝标签进行搜索,一次性推荐最多6篇文章
        if (aticleEntity != null) {
            List<HomeworkAticleVo> recommondAticleList = this.aticleService.findRecommondAticleInfo(aticleEntity.getKey_words(), aticleEntity.getAticle_id());
            super.getRequestObj().setAttribute("recommondAticles",recommondAticleList);
        }
        //获取该篇文章的上一篇和下一篇
        HomeworkAticleVo prevAticle=this.aticleService.findUponAticlesInfo(aticleEntity.getAticle_createtime(),0);
        HomeworkAticleVo nextAticle=this.aticleService.findUponAticlesInfo(aticleEntity.getAticle_createtime(),1);
        super.getRequestObj().setAttribute("prevAticle",prevAticle);
        super.getRequestObj().setAttribute("nextAticle",nextAticle);
        return "blog_detail";
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

    /**
     * 显示博文的具体页面
     *
     * @param aticleId
     * @return
     */
    @RequestMapping(value = "findBlogDetail.html", method = RequestMethod.GET)
    public String findBlogInfoDetail(@RequestParam String aticleId) {
        //加载博文详情，然后自增博文阅读次数，加载文章评论
        return "blog_detail";
    }
}
