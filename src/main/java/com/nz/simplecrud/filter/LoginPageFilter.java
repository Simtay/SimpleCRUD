package com.nz.simplecrud.filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *  To prevent user from going back to Login page if the user already logged in
 * @author Emre Simtay <emre@simtay.com>
 */
public class LoginPageFilter implements Filter{
   @Override
   public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,   FilterChain filterChain) throws IOException, ServletException{
       HttpServletRequest request = (HttpServletRequest) servletRequest;
       HttpServletResponse response = (HttpServletResponse) servletResponse;

       if(request.getUserPrincipal() != null){ //If user is already authenticated
                String navigateString = "";
                if(request.isUserInRole("Administrator")){
                        navigateString = "/admin/AdminHome.xhtml";
                }else if(request.isUserInRole("Manager")){
                        navigateString = "/manager/ManagerHome.xhtml";
                }else if(request.isUserInRole("User")){
                        navigateString = "/user/UserHome.xhtml";
                }
                response.sendRedirect(request.getContextPath()+navigateString);
       } else{
           filterChain.doFilter(servletRequest, servletResponse);
       }
   }

   @Override
   public void destroy(){
   }
   
   @Override
   public void init(FilterConfig filterConfig) throws ServletException{
   }
}