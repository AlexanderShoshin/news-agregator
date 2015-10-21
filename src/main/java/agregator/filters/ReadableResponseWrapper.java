package agregator.filters;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class ReadableResponseWrapper extends HttpServletResponseWrapper {
    private ByteArrayOutputStream outputStream;
    private String responseStr;


    public ReadableResponseWrapper(HttpServletResponse response) {
        super(response);
        responseStr = "";
        outputStream = new ByteArrayOutputStream();
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(getOutputStream());
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new ServletOutputStream() {
            private WriteListener writeListener = null;

            @Override
            public void write(int b) throws IOException {
                responseStr += (char)b;
                outputStream.write(b);
                if (writeListener != null) {
                    writeListener.notify();
                }
            }

            @Override
            public void setWriteListener(WriteListener writeListener) {
                this.writeListener =  writeListener;

            }

            @Override
            public boolean isReady() {
                return true;
            }
        };     
    };
    
    public String getData() {
        return responseStr; 
    }
}