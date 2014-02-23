package com.turbomanage.httpclient.multipart;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 11/9/12
 * Time: 5:04 PM
 * Wrapper for data to be sent using multipart/form-data
 */
public class MultipartBodypartBytes extends AbstractMultipartBodypart
{
    private byte[] data;

    public MultipartBodypartBytes(String formName, String fileName, String mimeType, byte[] data)
    {
        super(formName, fileName, mimeType);
        this.data = data;
    }

    @Override
    public long getLength()
    {
        return data.length;
    }

    @Override
    public void writeContent(OutputStream out) throws Exception
    {
        out.write(data);
    }
}
