package com.myideaway.easyapp.core.lib;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: cdm
 * Date: 12-4-21
 * Time: AM11:53
 */
public class Config {
    public static Config instance = new Config();
    public static int REMOTE_SERVICE_TIME_OUT = 20000;

    private Config() {

    }

    public static Config getInstance() {
        return instance;
    }

    public void readConfig(InputStream inStream) throws IOException {
        Properties props = new Properties();
        props.load(inStream);

        String timeOutStr = props.getProperty("remote_service_time_out");
        if (!StringUtils.isNotEmpty(timeOutStr)) {
            REMOTE_SERVICE_TIME_OUT = Integer.parseInt(timeOutStr);
        }

    }

}
