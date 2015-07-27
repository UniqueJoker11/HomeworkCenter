package colin.web.homework.tools;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

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

    /**
     * 根据命令获取当前工程或系统的信息
     * @param commond
     * @return
     */
    public static String fetchSystemInfo(String commond){
        return System.getProperty(commond);
    }

    /**
     * 返回一个随机的图片名称
     * @return
     */
    public static String fetchImageFileName(){
        String pattern="YYYYMMddhhmmss";
        String dateResult=DateToolsUtils.getSpecificDate(pattern);
        Random random=new Random();
        return (dateResult+Math.floor(random.nextDouble()*10000)).split(".")[0];
    }

}
