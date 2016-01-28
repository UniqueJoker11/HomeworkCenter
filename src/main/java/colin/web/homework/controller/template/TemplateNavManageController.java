package colin.web.homework.controller.template;

import colin.web.homework.common.CommonReturnResult;
import colin.web.homework.common.HomeworkConstants;
import colin.web.homework.controller.BaseController;
import colin.web.homework.service.TemplateNavService;
import colin.web.homework.tools.FileToolsUtils;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.multipart.MultipartFile;
import sun.net.www.http.HttpClient;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by DELL on 2016/1/26.
 */
@Controller
@Scope("request")
@RequestMapping(HomeworkConstants.CONTROLLER_MANAGER_PREFIX)
public class TemplateNavManageController extends BaseController {

    @Autowired
    private TemplateNavService templateNavService;
    /**
     * 显示导航管理页面
     *
     * @return
     */
    @RequestMapping(HomeworkConstants.CONTROLLER_TEMPLATE_NAV_MANAGE)
    public String showTemplateNavPage() {
        return HomeworkConstants.PAGE_TEMPLATE_NAV_MANAGE;
    }

    /**
     * 增加导航轮播图
     *
     * @param navPic
     * @param navDigest
     * @param navOrder
     * @return
     */
    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_TEMPLATE_NAV_ADD_ACTION, method = RequestMethod.POST)
    public Object addTemplateNav(@RequestParam MultipartFile navPic, @RequestParam String navDigest, @RequestParam String navOrder, HttpServletRequest request) {
        if (navPic.isEmpty() && !navPic.getOriginalFilename().endsWith(".jpeg") && !navPic.getOriginalFilename().endsWith(".jpg") && !navPic.getOriginalFilename().endsWith(".png")) {
            return new CommonReturnResult(false, "图片文件为空或传输不支持的图片格式");
        } else {
            //存储图片
            try {
                String navSrc=storePic(request.getServletContext(),navPic);
                templateNavService.addTemplateNav(navSrc,navDigest,navOrder,super.fetchUserInfo().getUser_callname());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new CommonReturnResult(true);
        }

    }

    public Object updateTemplateNav(@RequestParam String navId, @RequestParam String navDigest, @RequestParam String navOrder, HttpServletRequest request) {
        return null;
    }

    public String storePic(ServletContext servletContext, MultipartFile picFile) throws IOException {
        StringBuilder storeRoute = new StringBuilder(HomeworkConstants.IMAGE_NAV_STORE_DIR);
        String picOrgName=picFile.getOriginalFilename();
        String fullFileName = storeRoute.append(File.separator).append(FileToolsUtils.fetchImageFileName()).append(picOrgName.substring(picOrgName.lastIndexOf("."),picOrgName.length())).toString();
        ServletContextResource servletContextResource = new ServletContextResource(servletContext, fullFileName);
        if (!servletContextResource.exists()) {
            servletContextResource.getFile().createNewFile();
        }
        picFile.transferTo(servletContextResource.getFile());
        return fullFileName;
    }


}
