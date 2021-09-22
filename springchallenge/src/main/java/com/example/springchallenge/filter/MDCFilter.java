package com.example.springchallenge.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/*
  Class that writes a request-id header to further improve traceability of requests.
  It also include important information in the logs such as headers, http statys codes and parameters
*/
@Order(1)
@Component
public class MDCFilter implements Filter {

  private final Logger LOGGER = LoggerFactory.getLogger(MDCFilter.class);
  private final String X_REQUEST_ID = "X-Request-ID";

  @Override
  public void doFilter(ServletRequest request,
                       ServletResponse response,
                       FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;
             
    
    try {
      addXRequestId(req);             
      MDC.put("Os_browser_info", req.getHeader("User-Agent"));   
      MDC.put("statusCode", String.valueOf(res.getStatus()));  
      LOGGER.info("path: {}, method: {}, query {}",
              req.getRequestURI(), req.getMethod(), req.getQueryString());
      res.setHeader(X_REQUEST_ID, MDC.get(X_REQUEST_ID));


      Enumeration<String> enumeration = req.getHeaderNames();
      Map<String, Object> modelMap = new HashMap<>();
      while(enumeration.hasMoreElements()){
        String parameterName = enumeration.nextElement();
        modelMap.put(parameterName, req.getHeader(parameterName));
      }      
      MDC.put("reqHeaders", modelMap.toString());
                  
      Enumeration<String> enumerationParam = req.getParameterNames();
      Map<String, Object> modelMapParams = new HashMap<>();
      while(enumerationParam.hasMoreElements()){
        String parameterName = enumerationParam.nextElement();
        modelMapParams.put(parameterName, req.getParameter(parameterName));
      }
      MDC.put("reqParams", modelMapParams.toString());
      
      Map<String, Object> modelMapHeaders = new HashMap<>();
      res.getHeaderNames().forEach((value) ->{
        modelMapHeaders.put(value, res.getHeader(value));
      });           
      MDC.put("resHeaders", modelMapHeaders.toString());      
      
      chain.doFilter(request, response);
    } finally {    
      MDC.put("statusCode", String.valueOf(res.getStatus()));  
      
      Enumeration<String> enumerationParam = req.getParameterNames();
      Map<String, Object> modelMapParams = new HashMap<>();
      while(enumerationParam.hasMoreElements()){
        String parameterName = enumerationParam.nextElement();
        modelMapParams.put(parameterName, req.getParameter(parameterName));
      }
      MDC.put("reqParams", modelMapParams.toString());
      
      Map<String, Object> modelMapHeaders = new HashMap<>();
      res.getHeaderNames().forEach((value) ->{
        modelMapHeaders.put(value, res.getHeader(value));
      });           
      MDC.put("resHeaders", modelMapHeaders.toString()); 

      LOGGER.info("statusCode {}, path: {}, method: {}, query {}",
        res.getStatus(), req.getRequestURI(), req.getMethod(), req.getQueryString());
        
      MDC.clear();
    }
  }

  private void addXRequestId(HttpServletRequest request) {
    String xRequestId = request.getHeader(X_REQUEST_ID);
    if (xRequestId == null) {
      MDC.put(X_REQUEST_ID, UUID.randomUUID().toString());
    } else {
      MDC.put(X_REQUEST_ID, xRequestId);
    }
  }

}