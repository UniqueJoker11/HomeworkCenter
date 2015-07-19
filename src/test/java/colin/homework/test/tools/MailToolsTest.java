package colin.homework.test.tools;

import colin.web.homework.tools.FileTools;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.*;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by ASUS on 2015/7/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MailToolsTest {
    @Autowired
    private FileTools fileTools;

    @Test
    public void testMail() throws IOException, MessagingException {
        String host = fileTools.fetchMailConfigVal("mail_server_host");

        String username = fileTools.fetchMailConfigVal("mail_server_username");

        String password = fileTools.fetchMailConfigVal("mail_server_password");

// Create empty properties

        Properties props = new Properties();

// Get session

        Session session = Session.getDefaultInstance(props, null);

// Get the store

        Store store = session.getStore("pop3");

        store.connect(host, username, password);

// Get folder

        Folder folder = store.getFolder("INBOX");

        folder.open(Folder.READ_ONLY);

// Get directory

        Message message[] = folder.getMessages();

        for (int i = 0, n = message.length; i < n; i++) {

            System.out.println(i + ": " + message[i].getFrom()[0]

                    + "/t" + message[i].getSubject());

        }

// Close connection

        folder.close(false);

        store.close();
    }
}
