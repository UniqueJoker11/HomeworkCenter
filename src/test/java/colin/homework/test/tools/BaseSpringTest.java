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
    @Test
    public void testPath(){
        String path="upload\\images\\2.txt";
        String path2="upload"+File.separator+"images"+File.separator+"1.jpg";
        FileSystemResource resource2=new FileSystemResource(path2);
        File file=new File(path);
        File file2=new File(path2);
        System.out.println(file.exists());
        FileSystemResource resource=new FileSystemResource(path);
        System.out.println(resource.getPath().toString());
        System.out.println(resource2.getPath());
        System.out.println(resource2.exists()+"---"+file2.exists());
    }
}
