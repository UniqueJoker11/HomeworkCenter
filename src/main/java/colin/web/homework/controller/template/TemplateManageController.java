package colin.web.homework.controller.template;

import colin.web.homework.common.HomeworkConstants;
import colin.web.homework.controller.BaseController;
import colin.web.homework.core.vo.HomeworkTemplateVo;
import colin.web.homework.service.TemplateService;
import colin.web.homework.tools.FileToolsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模板管理一览
 * Created by DELL on 2015/7/27.
 */
@Controller
@RequestMapping(value = HomeworkConstants.CONTROLLER_MANAGER_PREFIX)
public class TemplateManageController extends BaseController {

    @Autowired
    private TemplateService templateService;

    /**
     * 显示模板管理一览页面
     *
     * @return
     */
    @RequestMapping(value = HomeworkConstants.CONTROLLER_TEMPLATE_MANAGE, method = RequestMethod.GET)
    public String showTemplateManagePage(HttpServletRequest request) {
        List<HomeworkTemplateVo> menuList = this.templateService.initTemplateInfoService();
        request.setAttribute("templateList", menuList);
        return HomeworkConstants.PAGE_TEMPLATE_MANAGE;
    }

    /**
     * ajax获取模板分页信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_TEMPLATE_PAGE_LIST, method = RequestMethod.GET)
    public Object showTemplateInfoByPage(HttpServletRequest request) {
        //初始化模板列表内容
        int currentPage = 0;
        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.valueOf(request.getParameter("currentPage").toString());
        }
        int pageSize = 10;
        if (request.getParameter("pageSize") != null) {
            pageSize = Integer.valueOf(request.getParameter("pageSize").toString());
        }
        Map<String, Object> templateMap = templateService.fetchTemplateWithPage(null, currentPage, pageSize);
        return templateMap;
    }

    /**
     * 根据id来搜索模板对象
     *
     * @param template_id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_TEMPLATE_SEARCH, method = RequestMethod.POST)
    public Object searchTemplateObj(@RequestParam(value = "template_id") String template_id) {
        HomeworkTemplateVo templateVo = this.templateService.searchTemplateService(template_id);
        return templateVo;
    }

    /**
     * 根据id更新模板对象
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_TEMPLATE_UPDATE, method = RequestMethod.POST)
    public Object editTemplateObj(@RequestParam(value = "template_id") String template_id) {
        Map<String, Object> params = new HashMap<>();
        params.put("template_id", template_id);
        HttpServletRequest request = super.getRequestObj();
        if (request.getParameter("template_tip") != null) {
            params.put("template_tip", request.getParameter("template_tip").toString());
        }
        if (request.getParameter("template_name") != null) {
            params.put("template_name", request.getParameter("template_name").toString());
        }
        if (request.getParameter("template_describe") != null) {
            params.put("template_describe", request.getParameter("template_describe").toString());
        }
        boolean result = this.templateService.updateTemplateService(params);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("isSuccess", result);
        if (!result) {
            resultMap.put("msg", "模板信息更新失败！模板信息不正确");
        }
        return resultMap;
    }

    /**
     * 根据id来删除模板
     *
     * @param template_id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_TEMPLATE_DELETE, method = RequestMethod.POST)
    public Object deleteTemplateObj(@RequestParam(value = "template_id") String template_id) {
        boolean result = this.templateService.deleteTemplateService(template_id);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("isSuccess", result);
        if (!result) {
            resultMap.put("msg", "删除模板失败，模板信息有误");
        }
        return resultMap;
    }

    /**
     * 显示模板添加页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = HomeworkConstants.CONTROLLER_TEMPLATE_ADD, method = RequestMethod.GET)
    public String showTemplateAddPage(HttpServletRequest request) {
        return HomeworkConstants.PAGE_TEMPLATE_ADD_MANAGE;
    }

    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_TEMPLATE_ADD_FORM, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object uploadTemplateSnapshot(@RequestParam(value = "templateSnapshot", required = true) MultipartFile[] templateSnapshot, @RequestParam(value = "tamplateName", required = true) String tamplateName, @RequestParam(value = "tamplateTips", required = true) String tamplateTips, @RequestParam(value = "tamplateDescribe", required = true) String tamplateDescribe, @RequestParam(value = "templateResource", required = true) MultipartFile templateResource) throws IOException {
        //处理上传图片的类
        StringBuilder snapshotUrl = new StringBuilder("");
        for (MultipartFile snapshotFile : templateSnapshot) {
            String orginalFilename = snapshotFile.getOriginalFilename();
            File snapshotCopyFile = getUploadSnapshotFile(orginalFilename.substring(orginalFilename.lastIndexOf("."), orginalFilename.length()));
            snapshotUrl.append(HomeworkConstants.IMAGE_STORE_DIR + File.separator + snapshotCopyFile.getName()).append(",");
            snapshotFile.transferTo(snapshotCopyFile);
        }
        //处理上传的压缩包
        String resourcesUrl = "";
        String resourceOrignalName = templateResource.getOriginalFilename();
        File resourcesCopyFile = getUploadResourceFile(resourceOrignalName.substring(resourceOrignalName.lastIndexOf("."), resourceOrignalName.length()));
        resourcesUrl = HomeworkConstants.RESOURCES_STORE_DIR + File.separator + resourcesCopyFile.getName();
        templateResource.transferTo(resourcesCopyFile);
        //解压缩文件
        String accessUrl = "";
        if (templateResource.getOriginalFilename().endsWith(".rar")) {
            FileToolsUtils.unRarFile(super.getRequestObj(), resourcesUrl, HomeworkConstants.RESOURCES_COMPRESS_DIR + File.separator + resourcesCopyFile.getName().substring(0, resourcesCopyFile.getName().lastIndexOf(".")));
        } else {
            FileToolsUtils.unZipFiles(super.getRequestObj(), resourcesCopyFile, HomeworkConstants.RESOURCES_COMPRESS_DIR + File.separator + resourcesCopyFile.getName().substring(0, resourcesCopyFile.getName().lastIndexOf(".")));
        }
        accessUrl = HomeworkConstants.RESOURCES_COMPRESS_DIR + "/" + resourcesCopyFile.getName().substring(0, resourcesCopyFile.getName().lastIndexOf(".")) + "/index.html";
        //存储模板实体对象
        boolean result = templateService.addTemplateService(snapshotUrl.toString(), resourcesUrl, tamplateName, tamplateTips, tamplateDescribe, accessUrl.replaceAll(File.separator, "/"), this.fetchUserInfo().getUser_name());
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (result) {
            resultMap.put("isSuccess", true);
        } else {
            resultMap.put("isSuccess", false);
        }
        return resultMap;
    }

    /**
     * 返回上传图片的空文件
     *
     * @return
     * @throws IOException
     */
    private File getUploadSnapshotFile(String suffix) throws IOException {
        StringBuilder storeRoute = new StringBuilder(HomeworkConstants.IMAGE_STORE_DIR);
        ServletContextResource storeDir = new ServletContextResource(super.getServletContext(), storeRoute.toString());
        if (!storeDir.exists()) {
            storeDir.getFile().mkdirs();
        }
        storeRoute.append(File.separator).append(FileToolsUtils.fetchImageFileName()).append(suffix);
        ServletContextResource imageFileResource = new ServletContextResource(super.getServletContext(), storeRoute.toString());
        if (!imageFileResource.exists()) {
            imageFileResource.getFile().createNewFile();
        }
        return imageFileResource.getFile();
    }

    private File getUploadResourceFile(String suffix) throws IOException {
        StringBuilder storeRoute = new StringBuilder(HomeworkConstants.RESOURCES_STORE_DIR);
        ServletContextResource storeDir = new ServletContextResource(super.getServletContext(), storeRoute.toString());
        if (!storeDir.exists()) {
            storeDir.getFile().mkdirs();
        }
        storeRoute.append(File.separator).append(FileToolsUtils.fetchResourceFileName()).append(suffix);
        ServletContextResource imageFileResource = new ServletContextResource(super.getServletContext(), storeRoute.toString());
        if (!imageFileResource.exists()) {
            imageFileResource.getFile().createNewFile();
        }
        return imageFileResource.getFile();
    }


}
