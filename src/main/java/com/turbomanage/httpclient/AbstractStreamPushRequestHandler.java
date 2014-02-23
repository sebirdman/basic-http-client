package com.turbomanage.httpclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: kgalligan
 * Date: 11/9/12
 * Time: 1:53 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractStreamPushRequestHandler extends BasicRequestHandler
{
    public abstract void consumeStream(InputStream in)throws IOException;

    @Override
    public void writeStream(OutputStream out, byte[] content) throws IOException
    {
        super.writeStream(out, content);
    }
}
