package com.myself.shiroproject.util.shiro;

import com.myself.shiroproject.model.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @ClassName PasswordHelper
 * @Description
 * @Author vici
 * @Date 2020/3/24 9:57
 * @Version V2.0
 **/
public class PasswordHelper {
    /*//随机字符串生成器
    private RandomNumberGenerator randomOb = new RandomNumberGenerator();

    //散列算法名（md5方式）
    private static final String ALGORITHM_NAME = "md5";

    //散列迭代次数
    private static final int HASH_ITERATION = 2;

    *//**
     *  加密用户
     * @param //user 用户，用户名(name)、密码(pwd)、盐\加密因子(salt)
     *//*
    public void encryptPassword(User user){
        //生成加密因子，保存盐。
        user.setSalt(randomOb.nextBytes().toHex());
        //加密密码 SimpleHash（算法名，密码，盐的byte，次数）.toHex()
        String newPassword = new SimpleHash(ALGORITHM_NAME ,user.getPwd, ByteSource.Util.bytes(user.getSalt()),HASH_ITERATION).toHex();
        //更新密码
        user.setPwd(newPassword );
    }
*/


    public static void main(String[] args) {
        String hashAlgorithName = "MD5";
        String password = "123456";//登录时的密码
        int hashIterations = 1024;//加密次数
        ByteSource credentialsSalt = ByteSource.Util.bytes("admin");//登录时输入的用户名
        Object obj = new SimpleHash(hashAlgorithName, password, credentialsSalt,hashIterations);
        System.out.println(obj);
    }
}
