package colin.homework.test.tools;

import org.junit.Test;

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
}
