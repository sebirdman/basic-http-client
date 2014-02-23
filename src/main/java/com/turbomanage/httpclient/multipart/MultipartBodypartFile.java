package com.turbomanage.httpclient.multipart;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 11/9/12
 * Time: 2:23 PM
 * Wrapper for a file to be uploaded using multipart/form-data
 */
public class MultipartBodypartFile extends AbstractMultipartBodypart
{
    private File file;

    public MultipartBodypartFile(String formName, String fileName, String contentType, File file)
    {
        super(formName, fileName, contentType);
        this.file = file;
    }

    @Override
    public long getLength()
    {
        return file.length();
    }

    @Override
    public void writeContent(OutputStream out) throws Exception
    {
        FileInputStream input = null;
        try {
            input = new FileInputStream(file);
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
