package com.jni.demo;

import com.jni.demo.call.JniCaller;
import io.github.rctcwyvrn.blake3.Blake3;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Base64;

@SpringBootApplication
public class JavaJniDemoApplication {


    public static void main(String[] args) throws InterruptedException {

        /**
         * 启动SpringBoot
         */
        SpringApplication.run(JavaJniDemoApplication.class, args);

        /**
         * 生成秘钥
         */
        byte[] pri = new byte[32];
        JniCaller.jniCaller.ed25519_gen_priv_key(pri);
        System.out.println("======生成秘钥======");
        String priStr = "";
        StringBuilder st2 = new StringBuilder("");
        for (byte b : pri) {
            st2.append(b & 0xFF).append(",");
        }
        priStr = st2.toString();
        System.out.println(priStr.substring(0,priStr.length()-1));


        /**
         * 加密
         */
        System.out.println("======身份证HASH======");
        String idCard = "123456";
        byte[] idCardBytes = idCard.getBytes();


        //idCardBytes = digest(32, idCardBytes);
        byte[] digestRst = new byte[32];
        JniCaller.jniCaller.cal_digest(idCardBytes,6,digestRst, 32);


        String idCardValue = "";
        StringBuilder st3 = new StringBuilder("");
        for (byte idCardByte : digestRst) {
            st3.append(idCardByte & 0xFF).append(",");
        }
        idCardValue = st3.toString();
        System.out.println(idCardValue.substring(0,idCardValue.length()-1));


        System.out.println("======加密结果======");
        byte[] result = new byte[32];
        JniCaller.jniCaller.ed25519_encrypt(digestRst, pri, result);
        String rstStr = "";
        StringBuilder st5 = new StringBuilder("");
        for (byte rst : result) {
            st5.append(rst & 0xFF).append(",");
        }
        rstStr = st5.toString();
        System.out.println(rstStr.substring(0, rstStr.length()-1));

        /**
         * 解密
         */
        System.out.println("======解密结果======");
        byte[] resultReal = new byte[32];
        JniCaller.jniCaller.ed25519_decrypt(result, pri, resultReal);
        String resultRealStr = "";
        StringBuilder st6 = new StringBuilder("");
        for (byte rst : resultReal) {
            st6.append(rst & 0xFF).append(",");
        }
        resultRealStr = st6.toString();
        System.out.println(resultRealStr.substring(0, resultRealStr.length()-1));
        Base64.getEncoder().encode(resultReal);

        while (true){
        }

    }




}
