package colin.web.homework.tools;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by ASUS on 2015/7/19.
 * 读取配置文件的值
 */
@Component
public class FileTools {
    /**
     * 记载邮箱的配置文件
     * @param propertyVal
     * @return
     * @throws IOException
     */
    public String fetchMailConfigVal(String propertyVal) throws IOException {
        //加载属性文件
        ClassPathResource mailConfig=new ClassPathResource("mail.properties");
        Properties properties=new Properties();
        properties.load(mailConfig.getInputStream());
        return properties.get(propertyVal).toString();
    }

}
