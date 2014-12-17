package com.myideaway.easyapp.core;

import android.util.Log;

/**
 * http://www.myideayway.com
 * Created by Tommy Chen on 14/12/17.
 */
public class L {
    public static final int LOG_OFF = -1;
    public static final int LOG_INFO = 1;
    public static final int LOG_DEBUG = 2;
    public static final int LOG_ERROR = 3;
    public static final int LOG_ALL = 4;

    private static String tag;
    private static int logLevel = LOG_INFO;

    public static void d(String msg) {
        String appendedMsg = appendMsgAndInfo(msg, getCurrentInfo());
        if (logLevel >= LOG_DEBUG) {
            Log.d(tag, appendedMsg);
        }

    }

    public static void e(String msg, Throwable tr) {
        String appendedMsg = appendMsgAndInfo(msg, getCurrentInfo());
        if (logLevel >= LOG_ERROR) {
            Log.e(tag, appendedMsg, tr);
        }
    }

    public static void i(String msg) {
        String appendedMsg = appendMsgAndInfo(msg, getCurrentInfo());
        if (logLevel >= LOG_INFO) {
            Log.i(tag, appendedMsg);
        }
    }

    private static String getCurrentInfo() {

        StackTraceElement[] eles = Thread.currentThread().getStackTrace();
        StackTraceElement targetEle = eles[5];
        String info = "(" + targetEle.getClassName() + "."
                + targetEle.getMethodName() + ":" + targetEle.getLineNumber()
                + ")";
        return info;
    }

    private static String appendMsgAndInfo(String msg, String info) {
        return msg + " " + getCurrentInfo();
    }

    public static String getTag() {
        return tag;
    }

    public static void setTag(String tag) {
        L.tag = tag;
    }

    public static int getLogLevel() {
        return logLevel;
    }

    public static void setLogLevel(int logLevel) {
        L.logLevel = logLevel;
    }
}
