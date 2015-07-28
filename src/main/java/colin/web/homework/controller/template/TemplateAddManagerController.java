package colin.web.homework.controller.template;

import colin.web.homework.common.HomeworkConstants;
import colin.web.homework.controller.BaseController;
import colin.web.homework.service.TemplateService;
import colin.web.homework.tools.FileTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 模板添加管理Controller
 * Created by DELL on 2015/7/27.
 */
@Controller
@RequestMapping(value = HomeworkConstants.CONTROLLER_MANAGER)
public class TemplateAddManagerController extends BaseController {

    @Autowired
    private TemplateService templateService;

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
            snapshotUrl.append(snapshotCopyFile.getPath()).append(",");
            snapshotFile.transferTo(snapshotCopyFile);
        }
        //处理上传的压缩包
        String resourcesUrl = "";
        String resourceOrignalName = templateResource.getOriginalFilename();
        File resourcesCopyFile = getUploadResourceFile(resourceOrignalName.substring(resourceOrignalName.lastIndexOf("."), resourceOrignalName.length()));
        resourcesUrl = resourcesCopyFile.getPath();
        templateResource.transferTo(resourcesCopyFile);
        //解压缩文件
        String accessUrl = "";
        if (templateResource.getOriginalFilename().endsWith(".rar")) {
            FileTools.unRarFile(resourcesUrl, HomeworkConstants.RESOURCES_COMPRESS_DIR + File.separator + resourcesCopyFile.getName().substring(0, resourcesCopyFile.getName().lastIndexOf(".")));
        } else {
            FileTools.unZipFiles(resourcesCopyFile, HomeworkConstants.RESOURCES_COMPRESS_DIR + File.separator + resourcesCopyFile.getName().substring(0, resourcesCopyFile.getName().lastIndexOf(".")));
        }
        accessUrl = HomeworkConstants.RESOURCES_COMPRESS_DIR + File.separator + resourcesCopyFile.getName() + "index.html";

        boolean result = templateService.addTemplateService(snapshotUrl.toString(), resourcesUrl, tamplateName, tamplateTips, tamplateDescribe, accessUrl, this.fetchUserInfo().getUser_name());
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
        File storeDir = new File(storeRoute.toString());
        if (!storeDir.exists()) {
            storeDir.mkdirs();
        }
        storeRoute.append(File.separator).append(FileTools.fetchImageFileName()).append(suffix);
        FileSystemResource imageFileResource = new FileSystemResource(storeRoute.toString());
        if (!imageFileResource.exists()) {
            imageFileResource.getFile().createNewFile();
        }
        return imageFileResource.getFile();
    }

    private File getUploadResourceFile(String suffix) throws IOException {
        StringBuilder storeRoute = new StringBuilder(HomeworkConstants.RESOURCES_STORE_DIR);
        File storeDir = new File(storeRoute.toString());
        if (!storeDir.exists()) {
            storeDir.mkdirs();
        }
        storeRoute.append(File.separator).append(FileTools.fetchResourceFileName()).append(suffix);
        FileSystemResource imageFileResource = new FileSystemResource(storeRoute.toString());
        if (!imageFileResource.exists()) {
            imageFileResource.getFile().createNewFile();
        }
        return imageFileResource.getFile();
    }


}
