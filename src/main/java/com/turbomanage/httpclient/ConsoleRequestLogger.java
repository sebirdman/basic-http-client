
package com.turbomanage.httpclient;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

/**
 * Default {@link RequestLogger} used by {@link BasicHttpClient}. In recent
 * versions of Android, log() gets directed to LogCat so this can
 * work for Android, too.
 * http://stackoverflow.com/questions/2220547/why-doesnt-system
 * -out-println-work-in-android
 * 
 * @author David M. Chandler
 */
public class ConsoleRequestLogger extends AbstractRequestLogger {

    @Override
    public void log(String msg)
    {
        System.out.println(msg);
    }
}
