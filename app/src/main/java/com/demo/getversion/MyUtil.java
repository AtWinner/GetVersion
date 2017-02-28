package com.demo.getversion;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * 我的工具类
 * Created by HuHu on 2017-02-28.
 */

public class MyUtil {
    /**
     * 比较version1和version2的大小
     *
     * @param v1
     * @param v2
     * @return 0, version1=version2;1,version1>version2;2,version1<version2
     * @throws Exception
     */
    public static int versionCompare(String v1, String v2) throws Exception {
        /*验证数据合法性*/
        if (isNull(v1)) {
            throw new Exception("版本号1不可为空");
        }
        if (isNull(v2)) {
            throw new Exception("版本号2不可为空");
        }
        String regex = "\\d+(\\.\\d+){0,4}";//验证规则：由最多5个数字组成的版本号
        if (!v1.matches(regex)) {
            throw new Exception("版本号1格式非法");
        }
        if (!v2.matches(regex)) {
            throw new Exception("版本号2格式非法");
        }
        /*验证数据合法性END*/

        String[] version1s = v1.split("\\.");
        String[] version2s = v2.split("\\.");
        int activeLength = version1s.length >= version2s.length ? version1s.length : version2s.length;//选取较短的版本号为有效长度

        /*比较在有效长度内的版本号*/
        for (int i = 0; i < activeLength; i++) {
            long version1 = 0;
            long version2 = 0;
            try {
                version1 = i >= version1s.length ? 0 : Long.parseLong(version1s[i]);
                version2 = i >= version2s.length ? 0 : Long.parseLong(version2s[i]);
            } catch (Exception e) {
                throw e;
            } finally {
                if (version1 > version2) {
                    return 1;
                }
                if (version1 < version2) {
                    return 2;
                }
            }
        }
        return 0;
    }

    public static boolean isNull(String s) {
        if (s == null || "".equals(s)) {
            return true;
        }
        return false;
    }
}
