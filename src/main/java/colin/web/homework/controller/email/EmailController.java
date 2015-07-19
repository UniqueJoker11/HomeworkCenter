package colin.web.homework.controller.email;

import colin.web.homework.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by ASUS on 2015/7/19.
 */
@Controller
@RequestMapping(value="homework")
public class EmailController extends BaseController {

    /**
     * 显示邮件主页面
     * @return
     */
    @RequestMapping(value="email.html",method= RequestMethod.GET)
    public String showEmailPage(){
        return "email";
    }

    /**
     * 初始化邮件列表
     * @return
     */
    public List<String> initMailList(){

        return null;
    }

}
