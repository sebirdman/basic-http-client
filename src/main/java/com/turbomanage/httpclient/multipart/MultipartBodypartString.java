package com.turbomanage.httpclient.multipart;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 11/21/12
 * Time: 11:55 AM
 * Used for multipart/form-data. Represents a part of the form that takes a String input rather than a file or data
 */
public class MultipartBodypartString extends AbstractMultipartBodypart
{
    private String string;

    private static final String TRANSFER_ENCODING_8BIT = "8bit";

    public MultipartBodypartString(String formName, String mimeType, String string)
    {
        super(formName, null, mimeType);
        this.string = string;
    }

    @Override
    public long getLength()
    {
        return string.length();
    }

    @Override
    public String getTransferEncoding()
    {
        return TRANSFER_ENCODING_8BIT;
    }

    @Override
    public void writeContent(OutputStream out) throws Exception
    {
        ByteArrayInputStream input = null;
        try {
            input = new ByteArrayInputStream(string.getBytes());
            byte[] buffer = new byte[1024];
            for (int length = 0; (length = input.read(buffer)) > 0;) {
                out.write(buffer, 0, length);
            }
            out.flush();
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }
}
