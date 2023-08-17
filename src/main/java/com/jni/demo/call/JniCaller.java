package com.jni.demo.call;


import com.sun.jna.Library;
import com.sun.jna.Native;


/**
 * @author duanshouzhi
 * @create 2023-08-16 19:37
 */
public interface JniCaller extends Library {

    /**
     * classpath file
     */
    String SO_PATH = "linux_so/libcryptography_helper.so";

    JniCaller jniCaller = Native.load(SO_PATH,JniCaller.class);

    /**
     * Hash处理
     * @param data
     * @param lenth 原字符串长度
     * @param digest
     */
    void cal_digest(byte[] data, int lenth, byte[] digest, int digestLenth);

    /**
     * 生成秘钥
     * ptr：私钥指针(内容空间32字节)
     * void ed25519_gen_priv_key(uint8_t *ptr);
     */
    int ed25519_gen_priv_key(byte[] priKey);

    /**
     * 加密
     * data：身份证原文
     * priv_key：私钥指针
     * data_enc：加密后的内存指针
     * int32_t ed25519_encrypt(const char *data, const uint8_t *priv_key, uint8_t *data_enc);
     */
    int ed25519_encrypt(byte[] data, byte[] priv_key, byte[] data_enc);


    /**
     * 解密
     * data：加密后的内容
     * priv_key：私钥内存指针
     * result：解密内存指针
     * int32_t ed25519_decrypt(const uint8_t *data, const uint8_t *priv_key, uint8_t *result);
     */
    int ed25519_decrypt(byte[] data, byte[] priv_key, byte[] result);


    /**
     * 乘法
     * data：前端加密后的身份证内容内存指针
     * priv_key：私钥指针
     * result：解密之后内容内存指针
     * int32_t ed25519_mul(const uint8_t *data, const uint8_t *priv_key, uint8_t *result);
     */
    int ed25519_mul(byte[] data, byte[] priv_key, byte[] result);


}
