package com.imooc.o2o.util;

import com.imooc.o2o.dto.ImageHolder;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@Slf4j
public class ImageUtil {
    private static final String basePath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();

    public static File trasferCommonsMultipartFileToFile(CommonsMultipartFile cFile) {
        File newFile = new File(cFile.getOriginalFilename());
        try {
            cFile.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.toString());
            throw new RuntimeException(e);
        }
        return newFile;
    }

    public static String generateThumbnail(ImageHolder thumbnail, String targetAddr) {
        //随机的文件名
        String realFileName = getRandomFileName();
        //文件的拓展名
        String extension = getFileExtention(thumbnail.getImageName());
        //去生成目录，如果存在，直接return,不存在，则递归的创建目录
        makeDirPath(targetAddr);
        //目录+文件名+拓展名(这个是相对路径，因为没有盘符)
        String relativeAddr = targetAddr + realFileName + extension;
        log.debug("current relativeAddr is :" + relativeAddr);
        //这里就是根据盘符加上相对地址生成的固定文件
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        log.debug("current complete is :" + dest.toString());
        try {//这个of里面，能接受File，也能接受inputStream
            Thumbnails.of(thumbnail.getImage()).size(200, 200)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
                    .outputQuality(0.8f).toFile(dest);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("error :{}", e.toString());
            throw new RuntimeException(e);
        }
        return relativeAddr;

    }
    /**
     * 处理详情图，并返回新生成图片的相对值路径
     *
     * @param thumbnail
     * @param targetAddr
     * @return
     */
    public static String generateNormalImg(ImageHolder thumbnail, String targetAddr) {
        // 获取不重复的随机名
        String realFileName = getRandomFileName();
        // 获取文件的扩展名如png,jpg等
        String extension = getFileExtention(thumbnail.getImageName());
        // 如果目标路径不存在，则自动创建
        makeDirPath(targetAddr);
        // 获取文件存储的相对路径(带文件名)
        String relativeAddr = targetAddr + realFileName + extension;
        log.debug("current relativeAddr is :" + relativeAddr);
        // 获取文件要保存到的目标路径
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        log.debug("current complete addr is :" + PathUtil.getImgBasePath() + relativeAddr);
        // 调用Thumbnails生成带有水印的图片
        try {
            Thumbnails.of(thumbnail.getImage()).size(337, 640)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
                    .outputQuality(0.9f).toFile(dest);
        } catch (IOException e) {
            log.error(e.toString());
            throw new RuntimeException("创建缩图片失败：" + e.toString());
        }
        // 返回图片相对路径地址
        return relativeAddr;
    }

    /**
     * 创建目标路径所涉及到的目录，即 d:/dev/image/xxx.jpg
     * 那么dev image，这两个文件夹都得创建
     *
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (dirPath.exists()) {
            return;
        }
        if (!dirPath.mkdirs()) {
            log.error("create directory file!!!");
        }
    }

    /**
     * 获取输入文件流的拓展名
     *
     * @param fileName
     * @return
     */
    private static String getFileExtention(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }


    /**
     * 生成随机文件名，当前年月日小时分钟秒 + 五位随机数
     *
     * @return
     */
    public static String getRandomFileName() {
        //获取随机的五位数
        int ranNum = r.nextInt(89999) + 10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + ranNum;
    }


    public static void main(String[] args) throws IOException {

        Thumbnails.of(new File("D:\\dev\\xiaohuangren.jpg")).size(200, 200)
                .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
                .outputQuality(0.8f).toFile("D:\\dev\\xiaohuangren2.jpg");
    }

    /*
     * storePath是文件的路径还是目录的路径
     * 如果storePath是文件路径则删除文件
     * 如果storePath是目录路径则删除目录
     * @param storePath
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void deleteFileOrPath(String storePath){
        File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
        if(fileOrPath.exists()){
            if(fileOrPath.isDirectory()){
                File[] files= fileOrPath.listFiles();
                //必须不能为null，否则会空指针异常
                assert files != null;
                for (File file : files) {
                    file.delete();
                }
            }
            //不管是文件还是文件夹，都是要删除的，所以这行代码放到最后
            fileOrPath.delete();
        }
    }
}
