package colin.web.homework.controller.blog;

import colin.web.homework.common.CommonReturnResult;
import colin.web.homework.controller.BaseController;
import colin.web.homework.core.pojo.Homework_Aticle_Entity;
import colin.web.homework.core.vo.HomeworkAticleVo;
import colin.web.homework.service.AticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by DELL on 2016/1/8.
 */
@Controller
@Scope("request")
@RequestMapping("blog")
public class BlogAticleController extends BaseController {
    private final static int initIndex = 1;
    private final static int pageSize = 6;
    @Autowired
    private AticleService aticleService;

    @RequestMapping("blog_aticle_index.html")
    public String showBlogAtcileIndexPage(HttpServletRequest request, @RequestParam(value = "navId") String navId) {
        request.setAttribute("index", 2);
        request.setAttribute("navId", navId);
        return "blog_aticle";
    }

    @ResponseBody
    @RequestMapping("fetch_blog_aticle_list.action")
    public Object fetchBlogAticleInfo(@RequestParam(value = "navId") String navId, @RequestParam(value = "startIndex") int startIndex, @RequestParam(value = "pageSize") int pageSize) {
        Map<String,Object> resultMap=aticleService.findAticlesByNavId(navId, startIndex, pageSize);
        CommonReturnResult returnResult = null;
        if (resultMap == null || resultMap.isEmpty()) {
            returnResult=new CommonReturnResult(false);
        } else {
           returnResult = new CommonReturnResult(true,resultMap);
        }
        return returnResult;
    }

    /**
     * 显示博文详情页面
     *
     * @return
     */
    @RequestMapping(value = "blog_detail.html", method = RequestMethod.GET)
    public String showBlogDetail(HttpServletRequest request, @RequestParam String aticleId) {
        request.setAttribute("index", 2);
        //根据id加载文章详情
        Homework_Aticle_Entity aticleEntity = aticleService.findAticleDetailInfo(aticleId);
        super.getRequestObj().setAttribute("aticle", aticleEntity);
        //获取相似文章推荐列表，根绝标签进行搜索,一次性推荐最多6篇文章
        if (aticleEntity != null) {
            List<HomeworkAticleVo> recommondAticleList = this.aticleService.findRecommondAticleInfo(aticleEntity.getKey_words(), aticleEntity.getAticle_id());
            super.getRequestObj().setAttribute("recommondAticles", recommondAticleList);
        }
        //获取该篇文章的上一篇和下一篇
        HomeworkAticleVo prevAticle = this.aticleService.findUponAticlesInfo(aticleEntity.getAticle_createtime(), 0);
        HomeworkAticleVo nextAticle = this.aticleService.findUponAticlesInfo(aticleEntity.getAticle_createtime(), 1);
        super.getRequestObj().setAttribute("prevAticle", prevAticle);
        super.getRequestObj().setAttribute("nextAticle", nextAticle);
        return "blog_detail";
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
