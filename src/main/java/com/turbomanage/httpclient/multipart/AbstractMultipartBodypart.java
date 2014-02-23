package com.turbomanage.httpclient.multipart;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 11/20/12
 * Time: 11:23 AM
 * Handles some of the more general method implementations for BodyPart
 */
public abstract class AbstractMultipartBodypart implements BodyPart
{
    String formName, fileName, mimeType;

    private static String TRANSFER_ENCODING_BINARY = "binary"; //The default Transfer-Encoding

    protected AbstractMultipartBodypart(String formName, String fileName, String mimeType)
    {
        this.formName = formName;
        this.fileName = fileName;
        this.mimeType = mimeType;
    }

    @Override
    public String getFormName()
    {
        return formName;
    }

    @Override
    public String getFileName()
    {
        return fileName;
    }

    @Override
    public String getMimeType()
    {
        return mimeType;
    }

    @Override
    public String getTransferEncoding()
    {
        return TRANSFER_ENCODING_BINARY;
    }

    @Override
    public void writeBodyPart(PrintWriter writer, OutputStream out, String boundary, boolean shouldWriteContent) throws Exception {
        writer.append(MultipartWrapper.DASHDASH).append(boundary).append(MultipartWrapper.CRLF);

        writer.append("Content-Disposition: form-data; name=\"").append(getFormName()).append("\"");
        if(getFileName() != null) {
            writer.append("; filename=\"").append(getFileName()).append("\"");
        }
        writer.append(MultipartWrapper.CRLF);

        writer.append("Content-Type: ").append(getMimeType()).append(MultipartWrapper.CRLF);
        writer.append("Content-Transfer-Encoding: ").append(getTransferEncoding()).append(MultipartWrapper.CRLF);

        writer.append(MultipartWrapper.CRLF).flush(); // End of headers, beginning of content

        if(shouldWriteContent) {
            writeContent(out); // Write the content of the BodyPart
        }

        writer.append(MultipartWrapper.CRLF).flush(); // CRLF here indicates end of part.
    }
}
