package colin.homework.test.tools;

import colin.web.homework.tools.DateToolsUtils;
import org.junit.Test;

import java.util.Random;

/**
 * Created by ASUS on 2015/7/11.
 */
public class BaseOperationTest {
    @Test
    public void testStringOutput(){
        String output="select * from nam where a=a and b=fe and ";
        System.out.println(output.substring(0,output.lastIndexOf(" and ")));
    }
    @Test
    public void showSystemInfo(){
        System.out.println(this.getClass().getClassLoader().getResource(".").getPath());
        System.out.println(Thread.currentThread().getContextClassLoader().getResource(".").getPath());
        System.out.println(System.getProperty("user.dir"));

    }
    @Test
    public void testMathVal(){
        String pattern="YYYYMMddhhmmss";
        String dateResult= DateToolsUtils.getSpecificDate(pattern);
        Random random=new Random();
        System.out.println((dateResult+Math.floor(random.nextDouble()*10000)).split(".")[0]);
    }
}
