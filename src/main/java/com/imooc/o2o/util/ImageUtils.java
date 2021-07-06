package com.imooc.o2o.util;

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
public class ImageUtils {
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

    public static String generateThumbnail(InputStream thumbnailInputStream, String fileName, String targetAddr) {
        //随机的文件名
        String realFileName = getRandomFileName();
        //文件的拓展名
        String extension = getFileExtention(fileName);
        //去生成目录，如果存在，直接return,不存在，则递归的创建目录
        makeDirPath(targetAddr);
        //目录+文件名+拓展名(这个是相对路径，因为没有盘符)
        String relativeAddr = targetAddr + realFileName + extension;
        log.debug("current relativeAddr is :" + relativeAddr);
        //这里就是根据盘符加上相对地址生成的固定文件
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        log.debug("current complete is :" + dest.toString());
        try {//这个of里面，能接受File，也能接受inputStream
            Thumbnails.of(thumbnailInputStream).size(200, 200)
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

}
