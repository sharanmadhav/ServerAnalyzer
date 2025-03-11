package com.serveranalyzer.utils;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseSizeWrapper extends HttpServletResponseWrapper {
    private final ByteArrayOutputStream dataStream;
    private ServletOutputStream outputStream;
    private PrintWriter writer;

    public ResponseSizeWrapper(HttpServletResponse response) {
        super(response);
        dataStream = new ByteArrayOutputStream();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (writer != null) {
            throw new IllegalStateException("getWriter() has already been called!");
        }
        if (outputStream == null) {
            outputStream = new ServletOutputStreamWrapper(dataStream);
        }
        return outputStream;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (outputStream != null) {
            throw new IllegalStateException("getOutputStream() has already been called!");
        }
        if (writer == null) {
            writer = new PrintWriter(dataStream);
        }
        return writer;
    }

    public ByteArrayOutputStream getDataStream() {
        try {
            if (writer != null) {
                writer.flush();
            }
            if (outputStream != null) {
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataStream;
    }

    public int getDataSize() {
        return dataStream.size();
    }

    private static class ServletOutputStreamWrapper extends ServletOutputStream {
        private final ByteArrayOutputStream outputStream;

        public ServletOutputStreamWrapper(ByteArrayOutputStream outputStream) {
            this.outputStream = outputStream;
        }

        @Override
        public void write(int b) throws IOException {
            outputStream.write(b);
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {
            // Not implemented for this simple wrapper
        }
    }
} 