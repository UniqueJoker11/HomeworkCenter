package colin.web.homework.service;

import colin.web.homework.core.vo.ChatUserInfo;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.offline.OfflineMessageHeader;
import org.jivesoftware.smackx.offline.OfflineMessageManager;
import org.jivesoftware.smackx.vcardtemp.VCardManager;
import org.jivesoftware.smackx.vcardtemp.packet.VCard;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by ASUS on 2015/8/2.
 */
@Service
public class UserChatService {

    private AbstractXMPPConnection abstractXMPPConnection = null;

    /**
     * 初始化config配置
     *
     * @return
     */
    public XMPPTCPConnectionConfiguration initXMPPTCPConfigConfig() {
        XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                .setServiceName("colin").setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
                .setHost("127.0.0.1").setDebuggerEnabled(true)
                .setSendPresence(true)
                .setPort(5222)
                .build();
        return config;
    }

    /**
     * 初始化用户的连接
     *
     * @param username
     * @param password
     * @return
     * @throws java.io.IOException
     * @throws org.jivesoftware.smack.XMPPException
     * @throws org.jivesoftware.smack.SmackException
     */
    public AbstractXMPPConnection initXMPPConnection(String username, String password) throws IOException, XMPPException, SmackException {
        abstractXMPPConnection = new XMPPTCPConnection(this.initXMPPTCPConfigConfig());
        abstractXMPPConnection.connect();
        abstractXMPPConnection.login(username, password);
        return abstractXMPPConnection;
    }

    public void listenUserMsg(final AbstractXMPPConnection connection) throws XMPPException, IOException, SmackException {
        ChatManager chatManager = ChatManager.getInstanceFor(connection);
        chatManager.addChatListener(new ChatManagerListener() {
            @Override
            public void chatCreated(Chat chat, boolean b) {
                chat.addMessageListener(new ChatMessageListener() {
                    @Override
                    public void processMessage(Chat chat, Message message) {
                        if (message.getBody() != null) {
                            System.out.println("我收到的消息是" + message.getBody());
                            System.out.println("thread Id is " + chat.getThreadID());
                            System.out.println("type is " + message.getType().toString());

                            System.out.println(chat.getParticipant().split("/")[0]);
                            Roster roster = Roster.getInstanceFor(connection);
                            roster.getEntry("chat.getParticipant().split(\"/\")[0]").getStatus().name();
                            Message newMsg = new Message();
                            newMsg.setBody("谢谢你哦，我已经收到你的消息了");
                            try {
                                chat.sendMessage(newMsg);
                            } catch (SmackException.NotConnectedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

            }
        });
        while (true) ;
    }

    /**
     * 换取当前用户的Roster对象
     *
     * @param username
     * @param password
     * @return
     * @throws XMPPException
     * @throws IOException
     * @throws SmackException
     */
    public Roster initUserRoster(String username, String password) throws XMPPException, IOException, SmackException {
        Roster roster = Roster.getInstanceFor(this.initXMPPConnection(username, password));
        return roster;
    }

    /**
     * 获取用户的好友列表
     *
     * @return
     * @throws XMPPException
     * @throws IOException
     * @throws SmackException
     */
    public Set<RosterEntry> getAllUserList() throws XMPPException, IOException, SmackException {
       Roster roster= Roster.getInstanceFor(abstractXMPPConnection);
        Set<RosterEntry> rosterEntries =roster.getEntries();
        VCardManager vCardManager = VCardManager.getInstanceFor(abstractXMPPConnection);

        List<ChatUserInfo> userInfoList = new ArrayList<ChatUserInfo>();
        //获取离线对象
        OfflineMessageManager offlineMessageManager= new OfflineMessageManager(abstractXMPPConnection);
        List<Message> offlineMsg=offlineMessageManager.getMessages();
        for (RosterEntry entry : rosterEntries) {
            //获取用户的card
            VCard card= vCardManager.loadVCard(entry.getUser());
            //获取当前用户的登录状态
            Presence presence=roster.getPresence(entry.getUser());

            ChatUserInfo chatUserInfo = new ChatUserInfo();
            chatUserInfo.setName(entry.getName());
            chatUserInfo.setLoginState(presence.getStatus());
            //chatUserInfo.setUnreadMsgNum();
        }

        /*return this.initUserRoster(username, password).getEntries();*/
        return null;
    }

    /**
     * 获取用户的vcard信息
     *
     * @param connection
     * @param user
     * @return
     * @throws XMPPException
     */
    public static VCard getUserVCard(XMPPTCPConnection connection, String user) throws XMPPException {
        VCard vcard = new VCard();
        try {
            vcard.load(connection, user);
        } catch (SmackException.NoResponseException e) {
            e.printStackTrace();
        } catch (SmackException.NotConnectedException e) {
            e.printStackTrace();
        }

        return vcard;
    }

    /**
     * 获取用户头像信息
     */
    public static ImageIcon getUserImage(XMPPTCPConnection connection, String user) {
        ImageIcon ic = null;
        try {
            System.out.println("获取用户头像信息: " + user);
            VCard vcard = new VCard();
            vcard.load(connection, user);

            if (vcard == null || vcard.getAvatar() == null) {
                return null;
            }
            ByteArrayInputStream bais = new ByteArrayInputStream(
                    vcard.getAvatar());
            Image image = ImageIO.read(bais);


            ic = new ImageIcon(image);
            System.out.println("图片大小:" + ic.getIconHeight() + " " + ic.getIconWidth());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ic;
    }

    /**
     * 添加一个组
     */
    public static boolean addGroup(Roster roster, String groupName) {
        try {
            roster.createGroup(groupName);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除一个组
     */
    public static boolean removeGroup(Roster roster, String groupName) {
        return false;
    }

    /**
     * 添加一个好友  无分组
     */
    public static boolean addUser(Roster roster, String userName, String name) {
        try {
            roster.createEntry(userName, name, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 添加一个好友到分组
     *
     * @param roster
     * @param userName
     * @param name
     * @return
     */
    public static boolean addUser(Roster roster, String userName, String name, String groupName) {
        try {
            roster.createEntry(userName, name, new String[]{groupName});
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除一个好友
     *
     * @param roster
     * @param userName
     * @return
     */
    public static boolean removeUser(Roster roster, String userName) {
        try {

            if (userName.contains("@")) {
                userName = userName.split("@")[0];
            }
            RosterEntry entry = roster.getEntry(userName);
            System.out.println("删除好友:" + userName);
            System.out.println("User: " + (roster.getEntry(userName) == null));
            roster.removeEntry(entry);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
