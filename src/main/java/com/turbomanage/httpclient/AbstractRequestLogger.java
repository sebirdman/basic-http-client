package com.turbomanage.httpclient;

import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: kgalligan
 * Date: 10/27/12
 * Time: 12:35 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractRequestLogger implements RequestLogger {

    /*
     * (non-Javadoc)
     * @see com.turbomanage.httpclient.RequestLogger#isLoggingEnabled()
     */
    public boolean isLoggingEnabled() {
        return true;
    }

    /* (non-Javadoc)
     * @see com.turbomanage.httpclient.RequestLogger#log(java.lang.String)
     */
    @Override
    public abstract void log(String msg);

    /*
     * (non-Javadoc)
     * @see com.turbomanage.httpclient.RequestLogger#logRequest(java.net.
     * HttpURLConnection, java.lang.Object)
     */
    @Override
    public void logRequest(HttpURLConnection uc, Object content) throws IOException
    {
        Log.d("HTTPRequest", uc.getRequestMethod());
    }

    /*
     * (non-Javadoc)
     * @see com.turbomanage.httpclient.RequestLogger#logResponse(java.net.
     * HttpURLConnection)
     */
    @Override
    public void logResponse(HttpResponse res) {
        if (res != null) {
            Log.d("HTTPResponse", "Code: " + res.getStatus());
        }
    }

    /**
     * Iterate over request or response headers and log them.
     *
     * @param map
     */
    private void logHeaders(Map<String, List<String>> map) {
        if (map != null) {
            for (String field : map.keySet()) {
                List<String> headers = map.get(field);
                for (String header : headers) {
                    log(field + ":" + header);
                }
            }
        }
    }

}

