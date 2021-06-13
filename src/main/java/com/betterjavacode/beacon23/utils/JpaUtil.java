package com.betterjavacode.beacon23.utils;

public class JpaUtil
{
    public static String truncate (String value, int length)
    {
        return value != null && value.length() > length ? value.substring(0, length) : value;
    }
}
