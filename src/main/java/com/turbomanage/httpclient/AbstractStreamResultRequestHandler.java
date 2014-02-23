package com.turbomanage.httpclient;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: kgalligan
 * Date: 11/9/12
 * Time: 1:53 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractStreamResultRequestHandler extends BasicRequestHandler
{
    public abstract void consumeStream(InputStream in)throws IOException;

    @Override
    public byte[] readStream(InputStream in) throws IOException
    {
        consumeStream(in);

        //For the lulz
        return new byte[0];
    }
}
