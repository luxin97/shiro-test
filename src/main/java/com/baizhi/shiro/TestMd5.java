package com.baizhi.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;

public class TestMd5 {
    public static void main(String[] args) {
        Md5Hash hash=new Md5Hash("123456","abcd",1024);
        String s=hash.toHex();
        System.out.println(s);
    }

}
