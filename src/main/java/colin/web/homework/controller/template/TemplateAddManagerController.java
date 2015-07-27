package colin.web.homework.controller.template;

import colin.web.homework.common.HomeworkConstants;
import colin.web.homework.tools.FileTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * 模板添加管理Controller
 * Created by DELL on 2015/7/27.
 */
@Controller
@RequestMapping(value = HomeworkConstants.CONTROLLER_MANAGER)
public class TemplateAddManagerController {
    @Resource(name = "taskExecutor")
    private ThreadPoolTaskExecutor taskExecutor;

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
    @RequestMapping(value = HomeworkConstants.CONTROLLER_TEMPLATE_ADD_FORM, method = RequestMethod.POST)
    public String uploadTemplateSnapshot(@RequestParam(value = "templateSnapshot", required = true) MultipartFile[] templateSnapshot, @RequestParam(value = "tamplateName", required = true) String tamplateName, @RequestParam(value = "tamplateTips", required = true) String tamplateTips, @RequestParam(value = "tamplateDescribe", required = true) String tamplateDescribe, @RequestParam(value = "templateResource", required = true) MultipartFile templateResource) throws IOException {
        //处理上传图片的类
        for (MultipartFile snapshotFile : templateSnapshot) {
            handleUplaodSnapshot(snapshotFile.getInputStream(), snapshotFile.getOriginalFilename());
        }
        return null;
    }

    /**
     * 处理上传图片
     *
     * @param snapshotInputStream
     * @param fileName
     * @return
     */
    private boolean handleUplaodSnapshot(InputStream snapshotInputStream, String fileName) {
        // taskExecutor.submit();
        return true;

    }

    public class UploadResource implements Runnable {

        private InputStream inputStream;
        private String fileName;
        private String storeDir;

        UploadResource(InputStream inputStream, String fileName, String storeDir) {
            this.fileName = fileName;
            this.inputStream = inputStream;
            this.storeDir = storeDir;
        }

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p/>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            //读取缓存资源
            BufferedInputStream snapshotUploadStream = new BufferedInputStream(inputStream);
            //构建资源的存储路径
            StringBuilder storeRoute=new StringBuilder(FileTools.fetchSystemInfo("user.dir"));
            // FileOutputStream resourceFile=new FileOutputStream();
        }
    }
}
