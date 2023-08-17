package com.jni.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.logging.logback.LogbackLoggingSystem;
import org.springframework.util.FileCopyUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author duanshouzhi
 * @create 2023-08-17 14:06
 */
@Slf4j
public class JarUtil {
    public static void extractFilesFromJar(String targetFolderInJar, String destDir) {

        //获取jar包所在路径
        String jarFile = System.getProperty("java.class.path");
        System.out.println("当前jar包所在路径：" + jarFile);

        if (StringUtils.isBlank(jarFile) || !jarFile.endsWith(".jar")) {
            System.out.println("定位jar失败");
            return;
        }
        try (JarFile jar = new JarFile(jarFile)) {
            Enumeration enumeration = jar.entries();
            while (enumeration.hasMoreElements()) {
                JarEntry jarEntry = (JarEntry) enumeration.nextElement();
                //BOOT-INF/classes/linux_so/libcryptography_helper_blake3.so
                String name = jarEntry.getName();
                //根据路径定位目标文件，BOOT-INF/classes/开头
                //targetFolderInJar = BOOT-INF/classes/
                if (!name.startsWith(targetFolderInJar)) {continue;}
                String fixedName = jarEntry.getName().replace(targetFolderInJar, "");
                //String destPath = destDir + File.separator + fixedName;
                //destPath = so/linux_so/libcryptography_helper_blake3.so
                String destPath = destDir + fixedName;
                System.out.println( "destPath = " + destPath );
                File file = new File(destPath);
                //是目录
                if( jarEntry.isDirectory() ) {
                    file.mkdirs();
                }else {
                    // 是文件
                    try (InputStream is = jar.getInputStream( jar.getEntry(name) );
                         FileOutputStream fos = new FileOutputStream(file)) {
                         FileCopyUtils.copy( is,fos );
                         fos.close();;
                    }
                }
            }
        } catch (IOException e) {
            log.error("提取JAR文件异常", e);
        }
    }

}
