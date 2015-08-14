package colin.web.homework.controller;

import colin.web.homework.tools.ChatCommonTools;
import colin.web.homework.common.HomeworkConstants;
import colin.web.homework.core.vo.HomeworkUserInfo;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

/**
 * Created by ASUS on 2015/8/2.
 */
@Controller
@RequestMapping(value = HomeworkConstants.CONTROLLER_MANAGER)
public class UserChatController extends BaseController{
    @Autowired
    private ChatCommonTools chatCommonTools;

    /**
     *
     * @return
     * @throws XMPPException
     * @throws IOException
     * @throws SmackException
     */
    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_USER_CHAT_REGISTER,method = RequestMethod.POST)
    public Map<String,Object> registerUserToOpenfire() throws XMPPException, IOException, SmackException {
        HomeworkUserInfo userInfo=super.fetchUserInfo();
        AbstractXMPPConnection connection=chatCommonTools.initXMPPConnection(userInfo.getUser_callname(),userInfo.getUser_password());
        //connection.registerIQRequestHandler()
        return null;
    }
    @MessageMapping(value = HomeworkConstants.CONTROLLER_USER_CHAT_SEND_MESSAGE)
    public void sendMsgToFriend(@RequestParam(value = "username",required = true)String username,@RequestParam(value = "message",required = true)String message){
        try {
            chatCommonTools.sendUserMessage(username,message);
        } catch (XMPPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SmackException e) {
            e.printStackTrace();
        }
    }
}
