package com.serveranalyzer.filters;

import com.serveranalyzer.models.RequestData;
import com.serveranalyzer.services.FirebaseService;
import com.serveranalyzer.utils.RequestBufferManager;
import com.serveranalyzer.utils.ResponseSizeWrapper;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class RequestPayloadFilter implements Filter {
    private static final Logger logger = Logger.getLogger(RequestPayloadFilter.class.getName());
    private static String FIREBASE_URL = "https://logmanager-2ff65-default-rtdb.firebaseio.com/GlobalLogManager.json";
    
    private RequestBufferManager bufferManager;
    private FirebaseService firebaseService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String CUSTOM_FIREBASE_URL = filterConfig.getInitParameter("firebaseUrl");
        if(CUSTOM_FIREBASE_URL != null && CUSTOM_FIREBASE_URL.length() != 0){
            FIREBASE_URL = CUSTOM_FIREBASE_URL;
        }
        firebaseService = new FirebaseService(FIREBASE_URL);
        bufferManager = new RequestBufferManager(firebaseService);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        ResponseSizeWrapper responseWrapper = new ResponseSizeWrapper(httpResponse);
        chain.doFilter(request, responseWrapper);
        RequestData requestData = new RequestData(httpRequest.getMethod(), httpRequest.getRequestURI(), request.getContentLength(), responseWrapper.getDataSize());
        bufferManager.addRequest(requestData.toJson());
        byte[] responseData = responseWrapper.getDataStream().toByteArray();
        response.getOutputStream().write(responseData);
    }

    @Override
    public void destroy() {
        if (bufferManager != null) {
            bufferManager.shutdown();
        }
    }
} 