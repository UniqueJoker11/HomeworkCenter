package colin.web.homework.tools;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.vfs2.*;
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
import org.springframework.web.context.support.ServletContextResource;
import org.zeroturnaround.zip.ZipUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

/**
 * Created by ASUS on 2015/7/19.
 * 读取配置文件的值
 */
@Component
public class FileToolsUtils {
    private static FileSystemManager fsm = null;

    static {
        try {
            fsm = VFS.getManager();
        } catch (FileSystemException e) {
            e.printStackTrace();
        }
    }

    //获取文件对象
    public static FileObject getFile(String path) {
        try {
            return fsm.resolveFile(path);
        } catch (FileSystemException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void delete(String path) {
        try {
            FileObject fo = fsm.resolveFile(path);
            fo.delete();
        } catch (FileSystemException e) {
            e.printStackTrace();
        }
    }
    public static boolean isDirectory(String path){
        try {
            FileObject fo=fsm.resolveFile(path);
            return fo.getType().equals(FileType.FOLDER);
        } catch (FileSystemException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static InputStream getInputStream(String path){
        try {
            FileObject fo=fsm.resolveFile(path);
            return fo.getContent().getInputStream();
        } catch (FileSystemException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static OutputStream getOutputStream(String path){
        try {
            FileObject fo=fsm.resolveFile(path);
            return fo.getContent().getOutputStream();
        } catch (FileSystemException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isFile(String path){
        try {
            FileObject fo=fsm.resolveFile(path);
            return fo.getType().equals(FileType.FILE);
        } catch (FileSystemException e) {
            e.printStackTrace();
            return false;
        }

    }
    /**
     * 函数描述：根据传入的文件路径创建文件夹(包括各级父文件夹)。如果路径中有文件，会自动去掉文件名。 （文件的判断是
     * 以最后一个"/"之后是否有"."为标识的，）
     *
     * @param path
     * @return 如果创建成功，返回true；否则，返回false;
     */
    public static boolean mkdirs(String path) {
        String realPath = "";
        path = path.replaceAll("\\\\", "/");
        // 如果该路径已"/"结尾，则整个字符串都是路径
        if (path.endsWith("/")) {
            realPath = path;
        } else {
            int fileNamePoint = path.lastIndexOf("/");
            // 获取真正的路径
            if (fileNamePoint >= 0) {
                realPath = path.substring(0, fileNamePoint);
            }
            // 读取文件名
            String fileName = path.substring(fileNamePoint + 1);
            // 如果读取的文件名中没有"."，说明整个字符串都是路径
            if (fileName.indexOf(".") < 0) {
                realPath = path;
            }
        }
        try {
            FileObject fo = fsm.resolveFile(realPath);
            fo.createFolder();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 函数描述：对文件进行copy
     *
     * @param sourceFilePath
     *            源文件的全部路径+文件名
     * @param targetFilePath
     *            目标文件的全部路径+文件名
     * @param overWrite
     *            如果目标文件存在，是否覆盖。true:覆盖；false:不覆盖(当源文件和目标文件都存在并且不覆盖时,返回true)。
     * @return true:成功；false:失败; (当源文件和目标文件都存在并且不覆盖时,返回true)。
     */
    public static boolean copyFile(String sourceFilePath, String targetFilePath, boolean overWrite) throws IOException {
        if (StringUtils.isBlank(sourceFilePath) || StringUtils.isBlank(targetFilePath)) {
            throw new IOException("源文件或者目标文件为空");
        }
        FileObject from = fsm.resolveFile(sourceFilePath);
        FileObject to = fsm.resolveFile(targetFilePath);
        if (to.exists()) {
            if (to.getType() == FileType.FILE) {
                if (overWrite && !to.delete()) {
                    throw new IOException("目标文件[" + targetFilePath + "]被保护，不能被覆盖！");
                } else if (!overWrite) {
                    throw new IOException("目标文件[" + targetFilePath + "]已经存在！");
                }
            }
        }
        to.copyFrom(from, Selectors.SELECT_ALL);
        return true;
    }
    /**
         * 获取属性配置文件的值
         */
    public static String fetchPropertiesResources(String fileName, String propertyName) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(fileName);
        Properties properties = new Properties();
        properties.load(classPathResource.getInputStream());
        return properties.get(propertyName).toString();
    }

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
        ZipUtil.packEntries(srcfile, zipfile);
    }

    /**
     * zip解压缩
     *
     * @param zipfile File 需要解压缩的文件
     * @param descDir String 解压后的目标目录
     */
    public static void unZipFiles(HttpServletRequest request, java.io.File zipfile, String descDir) throws IOException {
        File outputDir = new ServletContextResource(request.getServletContext(), descDir).getFile();
        ZipUtil.unpack(zipfile, outputDir);
    }


    /**
     * 根据原始rar路径，解压到指定文件夹下.
     *
     * @param srcRarPath       原始rar路径
     * @param dstDirectoryPath 解压到的文件夹
     */
    public static void unRarFile(HttpServletRequest request, String srcRarPath, String dstDirectoryPath) throws IOException {
        if (!srcRarPath.toLowerCase().endsWith(".rar")) {
            System.out.println("非rar文件！");
            return;
        }
        ServletContextResource dstDiretory = new ServletContextResource(request.getServletContext(), dstDirectoryPath);
        if (!dstDiretory.exists()) {// 目标目录不存在时，创建该文件夹
            dstDiretory.getFile().mkdirs();
        }
        Archive a = null;
        try {
            a = new Archive(new ServletContextResource(request.getServletContext(), srcRarPath).getFile());
            if (a != null) {
                a.getMainHeader().print(); // 打印文件信息.
                FileHeader fh = a.nextFileHeader();
                while (fh != null) {
                    if (fh.isDirectory()) { // 文件夹
                        File fol = new ServletContextResource(request.getServletContext(), dstDirectoryPath + File.separator
                                + fh.getFileNameString()).getFile();
                        fol.mkdirs();
                    } else { // 文件
                        File out = new ServletContextResource(request.getServletContext(), dstDirectoryPath + File.separator
                                + fh.getFileNameString().trim()).getFile();
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
