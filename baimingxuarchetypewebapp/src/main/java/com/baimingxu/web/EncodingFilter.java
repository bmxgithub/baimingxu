package com.baimingxu.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class EncodingFilter implements Filter {
    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //获取请求方式
        HttpServletRequest request = (HttpServletRequest)req;
        String method = request.getMethod();
        if(method.equals("POST")){
            //解决请求的乱码
            req.setCharacterEncoding("UTF-8");
        }
        //解决响应的乱码
        resp.setContentType("text/html;charset=UTF-8");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
