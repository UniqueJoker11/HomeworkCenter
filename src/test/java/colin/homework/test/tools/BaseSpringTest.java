package colin.homework.test.tools;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.support.ServletContextResource;

import java.io.File;

/**
 * Created by DELL on 2015/7/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class BaseSpringTest {
    @Test
    public void textFilePath(){
        String path="upload/images/1.jpg";
        File file=new File(path);
        System.out.println(file.exists());
        FileSystemResource resource=new FileSystemResource(path);
        System.out.println(resource.getFile().exists());
    }
}
