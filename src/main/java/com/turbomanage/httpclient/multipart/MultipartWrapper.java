package com.turbomanage.httpclient.multipart;

import com.turbomanage.httpclient.RequestHandler;

import java.io.*;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 11/21/12
 * Time: 1:34 PM
 * A class encapsulating all the information needed to use multipart/form-data
 */
public class MultipartWrapper {
    private String boundary;
    private Collection<BodyPart> parts;

    public static final String CRLF = "\r\n"; // Line separator required by multipart/form-data.
    public static final String DASHDASH = "--";

    public MultipartWrapper(String boundary, Collection<BodyPart> parts) {
        this.boundary = boundary;
        this.parts = parts;
    }

    public String getBoundary() {
        return boundary;
    }

    public long getContentLength() {
        long length = 0;

        for(BodyPart part : parts) {
            //Get the length of the content
            long contentLength = part.getLength();
            if(contentLength < 0) {
                //todo - This is an input stream, not totally sure how to handle this yet
                return -1;
            }
            length += contentLength;

            //Get the length of the header info for the part
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                part.writeBodyPart(new PrintWriter(byteArrayOutputStream), null, boundary, false);
            } catch (Exception e) {
                //IDK, just bomb here for now
                throw new RuntimeException(e);
            }
            byte[] headers = byteArrayOutputStream.toByteArray();
            length += headers.length;
        }

        return length;
    }

    public void writeMultipartBody(OutputStream out) throws Exception {
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new OutputStreamWriter(out, RequestHandler.UTF8), true);

            for(BodyPart part : parts)
            {
                part.writeBodyPart(writer, out, boundary, true);
            }

            // Signal end of multipart/form-data.
            writer.append(DASHDASH).append(boundary).append(DASHDASH).append(CRLF);

        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
