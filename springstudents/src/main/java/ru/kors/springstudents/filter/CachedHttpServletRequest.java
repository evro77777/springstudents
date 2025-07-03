package ru.kors.springstudents.filter;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class CachedHttpServletRequest extends HttpServletRequestWrapper {
    private final byte[] cachedBody;
    private final Map<String, String[]> parameterMap;
    private final Map<String, Object> attributes = new HashMap<>();
    
    public CachedHttpServletRequest(HttpServletRequest request) throws IOException {
        super(request);
        this.parameterMap = new HashMap<>(request.getParameterMap());
        ServletInputStream requestInputStream  = request.getInputStream();
        this.cachedBody = requestInputStream.readAllBytes();

        Enumeration<String> attrNames = request.getAttributeNames();
        while (attrNames.hasMoreElements()) {
            String name = attrNames.nextElement();
            attributes.put(name, request.getAttribute(name));
        }
    }

    @Override
    public ServletInputStream getInputStream() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.cachedBody);
        return new ServletInputStream() {
            @Override
            public int read() {
                return byteArrayInputStream.read();
            }
            @Override
            public boolean isFinished() { return byteArrayInputStream.available() == 0; }
            @Override
            public boolean isReady() { return true; }

            @Override
            public void setReadListener(ReadListener readListener) {

            }


        };
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public String getParameter(String name) {
        String[] values = parameterMap.get(name);
        return values != null && values.length > 0 ? values[0] : null;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return parameterMap;
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return Collections.enumeration(parameterMap.keySet());
    }

    @Override
    public String[] getParameterValues(String name) {
        return parameterMap.get(name);
    }

    @Override
    public Object getAttribute(String name) {
        return attributes.get(name);
    }

    @Override
    public void setAttribute(String name, Object o) {
        attributes.put(name, o);
    }
}
