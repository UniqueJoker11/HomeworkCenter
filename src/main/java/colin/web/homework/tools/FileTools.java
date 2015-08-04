package colin.web.homework.tools;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;
import org.zeroturnaround.zip.ZipUtil;

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
     *
     * @param propertyVal
     * @return
     * @throws IOException
     */
    public String fetchMailConfigVal(String propertyVal) throws IOException {
        //加载属性文件
        ClassPathResource mailConfig = new ClassPathResource("mail.properties");
        Properties properties = new Properties();
        properties.load(mailConfig.getInputStream());
        return properties.get(propertyVal).toString();
    }

    /**
     * 根据命令获取当前工程或系统的信息
     *
     * @param commond
     * @return
     */
    public static String fetchSystemInfo(String commond) {
        return System.getProperty(commond);
    }

    /**
     * 返回一个随机的图片名称
     *
     * @return
     */
    public static String fetchImageFileName() {
        String pattern = "YYYYMMddhhmmss";
        String dateResult = DateToolsUtils.getSpecificDate(pattern);
        Random random = new Random();
        return (dateResult + Math.floor(random.nextDouble() * 10000)).replace(".0", "");
    }

    /**
     * 返回一个随机的资源名称
     *
     * @return
     */
    public static String fetchResourceFileName() {
        String pattern = "YYYYMMddhhmmss";
        String dateResult = DateToolsUtils.getSpecificDate(pattern);
        Random random = new Random();
        return (dateResult + Math.floor(random.nextDouble() * 100000)).replace(".0", "");
    }

    /**
     * 压缩文件
     *
     * @param srcfile File[] 需要压缩的文件列表
     * @param zipfile File 压缩后的文件
     */
    public static void ZipFiles(java.io.File[] srcfile, java.io.File zipfile) {
       ZipUtil.packEntries(srcfile,zipfile);
    }

    /**
     * zip解压缩
     *
     * @param zipfile File 需要解压缩的文件
     * @param descDir String 解压后的目标目录
     */
    public static void unZipFiles(java.io.File zipfile, String descDir) {
        File outputDir=new File(descDir);
        ZipUtil.unpack(zipfile,outputDir);
    }


    /**
     * 根据原始rar路径，解压到指定文件夹下.
     *
     * @param srcRarPath       原始rar路径
     * @param dstDirectoryPath 解压到的文件夹
     */
    public static void unRarFile(String srcRarPath, String dstDirectoryPath) {
        if (!srcRarPath.toLowerCase().endsWith(".rar")) {
            System.out.println("非rar文件！");
            return;
        }
        File dstDiretory = new File(dstDirectoryPath);
        if (!dstDiretory.exists()) {// 目标目录不存在时，创建该文件夹
            dstDiretory.mkdirs();
        }
        Archive a = null;
        try {
            a = new Archive(new File(srcRarPath));
            if (a != null) {
                a.getMainHeader().print(); // 打印文件信息.
                FileHeader fh = a.nextFileHeader();
                while (fh != null) {
                    if (fh.isDirectory()) { // 文件夹
                        File fol = new File(dstDirectoryPath + File.separator
                                + fh.getFileNameString());
                        fol.mkdirs();
                    } else { // 文件
                        File out = new File(dstDirectoryPath + File.separator
                                + fh.getFileNameString().trim());
                        //System.out.println(out.getAbsolutePath());
                        try {// 之所以这么写try，是因为万一这里面有了异常，不影响继续解压.
                            if (!out.exists()) {
                                if (!out.getParentFile().exists()) {// 相对路径可能多级，可能需要创建父目录.
                                    out.getParentFile().mkdirs();
                                }
                                out.createNewFile();
                            }
                            FileOutputStream os = new FileOutputStream(out);
                            a.extractFile(fh, os);
                            os.close();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    fh = a.nextFileHeader();
                }
                a.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
