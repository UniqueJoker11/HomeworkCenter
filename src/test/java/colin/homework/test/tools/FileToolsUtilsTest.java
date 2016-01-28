package colin.homework.test.tools;

import colin.web.homework.tools.FileToolsUtils;
import org.apache.commons.vfs2.provider.http.HttpClientFactory;
import org.junit.Test;
import sun.net.www.http.HttpClient;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by DELL on 2015/8/14.
 */
public class FileToolsUtilsTest extends BaseSpringTest{
    @Test
    public void loadPropertiesTest() throws IOException {

        System.out.println(FileToolsUtils.fetchPropertiesResources("openfire-server.properties", "server")+"--"+ FileToolsUtils.fetchPropertiesResources("openfire-server.properties", "serverHost")+"---"+ FileToolsUtils.fetchPropertiesResources("openfire-server.properties", "serverPort"));
    }
    @Test
    public void getMusicFile() throws IOException {
        String apiPath="http://s.music.163.com/search/get/?src=lofter&type=1&s=linken&limit=10&offset=0";
        URL url=new URL(apiPath);
        HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection(Proxy.NO_PROXY);
        urlConnection.setRequestMethod("GET");
        System.out.println(urlConnection.getResponseMessage());
    }

    @Test
    public void testFileName(){
        String fileName=".jpg";
        Pattern pattern = Pattern.compile("^.jpg");
        Matcher matcher = pattern.matcher(fileName);
        System.out.println(matcher.matches());
    }
}
