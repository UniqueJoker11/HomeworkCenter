package colin.web.homework.controller;

import colin.web.homework.common.ChatCommonTools;
import colin.web.homework.common.HomeworkConstants;

import colin.web.homework.core.vo.HomeworkMenuVo;
import colin.web.homework.core.vo.HomeworkUserInfo;
import colin.web.homework.service.MenuService;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.roster.RosterEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by ASUS on 2015/7/26.
 */
@Controller
@RequestMapping(value = HomeworkConstants.CONTROLLER_MANAGER)
public class DashboardController extends BaseController {

    @Autowired
    private MenuService menuService;//加载菜单服务
    @Autowired
    private ChatCommonTools chatCommonTools;

    @RequestMapping(value = HomeworkConstants.CONTROLLER_DASHBOARD, method = RequestMethod.GET)
    public String showDashboardPage(HttpServletRequest request) throws XMPPException, IOException, SmackException {
        //加载左侧导航菜单内容
        List<HomeworkMenuVo> menuList = menuService.getMenuInfoService();
        request.setAttribute("menuList", menuList);
        //加载用户的聊天好友
        HomeworkUserInfo homeworkUserInfo=super.fetchUserInfo();
        chatCommonTools.listenUserMsg(chatCommonTools.initXMPPConnection(homeworkUserInfo.getUser_name(), homeworkUserInfo.getUser_password()));
        /*request.setAttribute("userEntries",chatConfig.getAllUserList(homeworkUserInfo.getUser_name(), homeworkUserInfo.getUser_password()));*/
        Set<RosterEntry> rosterEntries=chatCommonTools.getAllUserList();
        System.out.println("用户的列表是");
        for(RosterEntry rosterEntry:rosterEntries){
            System.out.println("用户名是"+rosterEntry.getName()+"---状态是"+rosterEntry.getStatus());
        }
        request.setAttribute("userEntries",rosterEntries);
        return HomeworkConstants.PAGE_DASHBOARD;
    }
}
