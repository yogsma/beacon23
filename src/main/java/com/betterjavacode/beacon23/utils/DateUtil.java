package com.betterjavacode.beacon23.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil
{
    public static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

    public static Date formatDate (String publishedDate)
    {
        try
        {
            return formatter.parse(publishedDate);
        }
        catch (ParseException e)
        {
            LOGGER.debug("Unable to parse the date", e.getMessage());
        }

        return null;
    }
}
