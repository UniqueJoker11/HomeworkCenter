package colin.homework.test.tools;

import colin.web.homework.tools.FileToolsUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by DELL on 2015/8/14.
 */
public class FileToolsUtilsTest extends BaseSpringTest{
    @Test
    public void loadPropertiesTest() throws IOException {

        System.out.println(FileToolsUtils.fetchPropertiesResources("openfire-server.properties", "server")+"--"+ FileToolsUtils.fetchPropertiesResources("openfire-server.properties", "serverHost")+"---"+ FileToolsUtils.fetchPropertiesResources("openfire-server.properties", "serverPort"));
    }
}
