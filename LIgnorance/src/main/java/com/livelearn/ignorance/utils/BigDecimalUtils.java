package com.livelearn.ignorance.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 小数算法操作
 * Created on 2017/4/25.
 */

public class BigDecimalUtils {

    /**
     * 分转换成元
     *
     * @return 大数据类型
     */
    public static BigDecimal centConvertIntoYuanReturnBigDecimal(String cent) {
        String val = cent == null || cent.isEmpty() ? "0" : cent;
        return (new BigDecimal(val)).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
    }

    /**
     * 分转换成元
     *
     * @return 字符串类型
     */
    public static String centConvertIntoYuanReturnString(String cent) {
        return String.valueOf(centConvertIntoYuanReturnBigDecimal(cent));
    }
}
