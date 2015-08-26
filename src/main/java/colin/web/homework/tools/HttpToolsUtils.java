package colin.web.homework.tools;

import com.sun.jndi.toolkit.url.Uri;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.AsyncClientHttpRequest;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.mock.http.client.MockAsyncClientHttpRequest;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by DELL on 2015/8/26.
 */
public class HttpToolsUtils {

    public void testTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders httpHeaders = restTemplate.headForHeaders(new URI("http://192.168.8.51:8080/HomeworkCenter/homework/usernamevalidate.action"));
            Set<String> headerKeys = httpHeaders.keySet();
            for (String key : headerKeys) {
                System.out.println("Header:" + key + ":" + httpHeaders.get(key));
            }
            //获取返回内容
            Map<String, Object> params = new HashMap<>();
            params.put("username", "colin");
            Map<String, Object> headers = new HashMap<>();
            headers.put("Content-Type", "application/json;charset=UTF-8");
            headers.put("Date", "Wed, 26 Aug 2015 02:25:28 GMT");
            headers.put("Server", "Apache-Coyote/1.1");
            HttpEntity<String> entity = restTemplate.getForEntity("http://192.168.8.51:8080/HomeworkCenter/homework/signin.html", String.class, headers);
            System.out.println(entity.getBody());
           /* SimpleClientHttpRequestFactory requestFactory=new SimpleClientHttpRequestFactory();
            MockAsyncClientHttpRequest httpRequest= (MockAsyncClientHttpRequest) requestFactory.createAsyncRequest(new URI("http://192.168.8.51:8080/HomeworkCenter/homework/usernamevalidate.action"), HttpMethod.POST);
            httpRequest.s*/
        } catch (RestClientException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getReason());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HttpToolsUtils utils = new HttpToolsUtils();
        utils.testTemplate();
    }
}
