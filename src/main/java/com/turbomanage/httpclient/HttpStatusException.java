package com.turbomanage.httpclient;

/**
 * Created with IntelliJ IDEA.
 * User: kgalligan
 * Date: 11/9/12
 * Time: 1:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class HttpStatusException extends Exception
{
    private int status;

    public HttpStatusException(int status)
    {
        this.status = status;
    }

    public int getStatus()
    {
        return status;
    }
}
