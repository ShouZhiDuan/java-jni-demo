package com.jni.demo.call;

import com.jni.demo.utils.JarUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author duanshouzhi
 * @create 2023-08-17 14:14
 */
@Data
@Slf4j
public class JarResource {

    static {
        System.out.println("======加载JAR包中的SO库======");
        //JAR包中so库路径
        String targetFolderInJar = "BOOT-INF/classes/";
        //jna库文件输出路径：
        String destDir = "so";
        File file = new File(destDir);
        if(file.exists()){
            System.out.println("======移除老版本jna库，然后更新库======");
            file.delete();
            file.mkdir();
            //执行更新操作
            JarUtil.extractFilesFromJar(targetFolderInJar, destDir);
        }else {
            System.out.println("======首次加载jna库======");
            file.mkdir();
            JarUtil.extractFilesFromJar(targetFolderInJar, destDir);
        }
    }
}
