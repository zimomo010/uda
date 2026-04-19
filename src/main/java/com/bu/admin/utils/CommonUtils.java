package com.bu.admin.utils;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Random;


/**
 * 系统静态辅助方法
 *
 * @author ghostWu
 */
@Slf4j
public class CommonUtils {
    private CommonUtils() {
        throw new IllegalStateException("Utility class");
    }


    public static String getRelativeUrl(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getHeader("host") + request.getContextPath();
    }

    /**
     * 把图片写到磁盘上
     *
     * @param im       输入流
     * @param path     图片写入的文件夹地址
     * @param fileName 写入图片的名字
     * @return
     */

    public static boolean saveFileFromImage(BufferedImage im, String path,
                                            String fileName) {
        File f = new File(path + fileName);
        String fileType = getExtension(fileName);
        try {
            ImageIO.write(im, fileType, f);
            im.flush();
            return true;
        } catch (IOException e) {
            return false;
        }
    }


    /**
     * 返回文件的文件后缀名
     *
     * @param fileName
     * @return
     */
    public static String getExtension(String fileName) {
        try {
            return fileName.split("\\.")[fileName.split("\\.").length - 1];
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * @param destPath 目标路径
     */
    public static void checkPathAndCreate(String destPath) {
        File file = new File(destPath);
        if (!file.exists()) {
            log.info("mkdir [{}]", destPath);
            file.mkdirs();
        } else {
            log.info("dir is already exist....");
        }
    }

    public static Boolean checkFileExist(String filePath) {
        File file = new File(filePath);

        return file.exists();
    }

}
