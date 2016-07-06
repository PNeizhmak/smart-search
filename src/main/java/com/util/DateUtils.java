package com.util;

import java.sql.Timestamp;

/**
 * @author Pavel Neizhmak
 */
public class DateUtils {

    public static Timestamp newTimestamp() {
        return new Timestamp(new java.util.Date().getTime());
    }
}
