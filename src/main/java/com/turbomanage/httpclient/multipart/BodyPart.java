package com.turbomanage.httpclient.multipart;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 11/9/12
 * Time: 2:11 PM
 * Interface that defines the methods needed to upload data using multipart/form-data
 */
public interface BodyPart
{
    String getFormName();
    String getFileName();
    long getLength();
    String getMimeType();
    String getTransferEncoding();

    //todo these should handle exceptions better than just catching Exception
    void writeBodyPart(PrintWriter writer, OutputStream out, String boundary, boolean shouldWriteContent) throws Exception;
    void writeContent(OutputStream out) throws Exception;
}
