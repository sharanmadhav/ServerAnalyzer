package com.serveranalyzer.filters;

import com.serveranalyzer.models.RequestData;
import com.serveranalyzer.services.FirebaseService;
import com.serveranalyzer.utils.RequestBufferManager;
import com.serveranalyzer.utils.ResponseSizeWrapper;
import com.serveranalyzer.utils.ServerUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestPayloadFilter implements Filter {
    private static final Logger logger = Logger.getLogger(RequestPayloadFilter.class.getName());
    private RequestBufferManager bufferManager;

    @Override
    public void init(FilterConfig filterConfig) {
        String FIREBASE_URL = "https://logmanager-2ff65-default-rtdb.firebaseio.com";
        String URLJSONPATH;
        if((filterConfig.getInitParameter(ServerUtils.USERKEY) != null) && (filterConfig.getInitParameter(ServerUtils.URLJSONFILE) != null)){
            FIREBASE_URL = FIREBASE_URL+"/"+filterConfig.getInitParameter(ServerUtils.USERKEY);
            URLJSONPATH = filterConfig.getInitParameter(ServerUtils.URLJSONFILE);
        }
        else {
            return;
        }
        FirebaseService firebaseService = new FirebaseService(FIREBASE_URL);
        bufferManager = new RequestBufferManager(firebaseService,URLJSONPATH);
        logger.log(Level.INFO,"RequestPayloadFilter Initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        ResponseSizeWrapper responseWrapper = new ResponseSizeWrapper(httpResponse);
        chain.doFilter(request, responseWrapper);
        RequestData requestData = new RequestData(httpRequest,responseWrapper);
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
