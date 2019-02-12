package com.dongzhi.ow.interceptor;

import java.net.InetAddress;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dongzhi.ow.pojo.User;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
        String contextPath=session.getServletContext().getContextPath();
        String[] noNeedAuthPage = new String[]{
                "admin",
                "admin_user_login"};
 
        String uri = request.getRequestURI();
        uri = StringUtils.remove(uri, contextPath);
//        System.out.println(uri);
        if(uri.startsWith("/")){
            String method = StringUtils.substringAfterLast(uri,"/" );
            if(!Arrays.asList(noNeedAuthPage).contains(method)){
                User user =(User) session.getAttribute("user");
                if(null==user){
                    response.sendRedirect("admin");
                    return false;
                }
            }
        }
 
        return true;
 
    }
	
	/**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
     * 可在modelAndView中加入数据，比如当前时间
     */
 
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,   
            ModelAndView modelAndView) throws Exception {

    }
  
    /** 
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等  
     *  
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion() 
     */
      
    public void afterCompletion(HttpServletRequest request,   
            HttpServletResponse response, Object handler, Exception ex) 
    throws Exception { 
            
    } 
}
