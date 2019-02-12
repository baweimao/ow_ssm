package com.dongzhi.ow.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    /**
     * 判断是否是对应的格式的日期字符串
     * @param dateStr
     * @param datePattern
     * @return
     */
    public static boolean isRightDateStr(String dateStr,String datePattern){
        DateFormat dateFormat  = new SimpleDateFormat(datePattern);
        try {
            //采用严格的解析方式，防止类似 “2017-05-35” 类型的字符串通过
            dateFormat.setLenient(false);
            dateFormat.parse(dateStr);
            Date date = (Date)dateFormat.parse(dateStr);
//            //重复比对一下，防止类似 “2017-5-15” 类型的字符串通过
//            String newDateStr = dateFormat.format(date);
//            if(dateStr.equals(newDateStr)){
//                return true;
//            }else {
//                return false;
//            }
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}