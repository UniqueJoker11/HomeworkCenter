package colin.web.homework.controller;

import colin.web.homework.common.ChatCommonTools;
import colin.web.homework.common.HomeworkConstants;
import colin.web.homework.core.vo.HomeworkUserInfo;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

/**
 * Created by ASUS on 2015/8/2.
 */
@Controller
@RequestMapping(HomeworkConstants.CONTROLLER_MANAGER)
public class UserChatController extends BaseController{
    @Autowired
    private ChatCommonTools chatConfig;
    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_USER_CHAT_REGISTER,method = RequestMethod.POST)
    public Map<String,Object> registerUserToOpenfire() throws XMPPException, IOException, SmackException {
        HomeworkUserInfo userInfo=super.fetchUserInfo();
        AbstractXMPPConnection connection=chatConfig.initXMPPConnection(userInfo.getUser_callname(),userInfo.getUser_password());
        //connection.registerIQRequestHandler()
        return null;
    }
}
