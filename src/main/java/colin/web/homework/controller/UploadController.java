package colin.web.homework.controller;

import colin.web.homework.common.HomeworkConstants;
import colin.web.homework.tools.Uploader;
import com.fasterxml.jackson.databind.deser.Deserializers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DELL on 2015/8/24.
 */
@Controller
@RequestMapping(HomeworkConstants.CONTROLLER_MANAGER_PREFIX)
public class UploadController extends BaseController{

    @ResponseBody
    @RequestMapping(HomeworkConstants.CONTROLLER_COMMON_UPLOAD_IMAGE)
    public Object uploadImg(MultipartHttpServletRequest request){
        Uploader uploader=new Uploader(request);
        ServletContextResource resource= new ServletContextResource(super.getServletContext(),HomeworkConstants.IMAGE_STORE_DIR);
        uploader.setSavePath(resource.getPath());
        Map<String,Object> resultMap=new HashMap<String,Object>();
        String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
        uploader.setAllowFiles(fileType);
        uploader.setMaxSize(10000); //单位KB
        try {
            uploader.upload();
            resultMap.put("isSuccess", true);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("isSuccess",false);
        }
        return resultMap;
    }
}
