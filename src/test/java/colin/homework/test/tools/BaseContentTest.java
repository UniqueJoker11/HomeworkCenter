package colin.homework.test.tools;

import org.junit.Test;

/**
 * Created by DELL on 2016/1/28.
 */

public class BaseContentTest {
    @Test
    public void testFilename() {
        String fileName = "duocai_huabi.jpg";
        System.out.println((fileName.substring(fileName.lastIndexOf("."),fileName.length())));
    }
}
