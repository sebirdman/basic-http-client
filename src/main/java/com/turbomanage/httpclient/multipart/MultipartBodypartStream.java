package com.turbomanage.httpclient.multipart;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 11/9/12
 * Time: 5:06 PM
 * Wrapper for an input stream for data to be sent using multipart/form-data
 */
public class MultipartBodypartStream extends AbstractMultipartBodypart
{
    private InputStream stream;

    public MultipartBodypartStream(String formName, String fileName, String mimeType, InputStream stream)
    {
        super(formName, fileName, mimeType);
        this.stream = stream;
    }

    @Override
    public long getLength()
    {
        //todo - how to get length of the stream
        return -1;
    }

    @Override
    public void writeContent(OutputStream out) throws Exception
    {
        try {
            byte[] buffer = new byte[1024];
            for (int length = 0; (length = stream.read(buffer)) > 0;) {
                out.write(buffer, 0, length);
            }
            out.flush();
        } finally {
            stream.close();
        }
    }
}
