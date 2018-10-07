package com.song.sample.filter.wrapper;

import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.InputStream;

public class ModifyRequestWrapper extends HttpServletRequestWrapper {

    private byte[] b;

    public ModifyRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    public String getRequestBody() throws IOException {

        InputStream is = super.getInputStream();
        b = IOUtils.toByteArray(is);
        return new String(b);
    }

}
