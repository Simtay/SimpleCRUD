package com.nz.simplecrud.controller;

import com.nz.simplecrud.util.DateUtility;
import java.io.IOException;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class LoginController {
	
	private String username;
	private String password;
	
	public LoginController(){
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void login(ActionEvent actionEvent){
                FacesContext context = FacesContext.getCurrentInstance();  
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
                request.getContextPath();
                
		try {
                    	String navigateString = "";
			request.login(username, password);
			Principal principal = request.getUserPrincipal();
			if(request.isUserInRole("Administrator")){
				navigateString = "/admin/AdminHome.xhtml";
			}else if(request.isUserInRole("Manager")){
				navigateString = "/manager/ManagerHome.xhtml";
			}else if(request.isUserInRole("User")){
				navigateString = "/user/UserHome.xhtml";
			}
                        try {
                            Logger.getLogger(LoginController.class.getName()).log(Level.INFO, "User ({0}) loging in #" +DateUtility.getCurrentDateTime(), request.getUserPrincipal().getName());
                            context.getExternalContext().redirect(request.getContextPath() + navigateString);
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, "IOException, Login Controller" + "Username : " + principal.getName(), ex);
                            context.addMessage(null, new FacesMessage("Error!", "Exception occured")); 
                        }
		} catch (ServletException e) {
                        context.addMessage(null, new FacesMessage("Error!", "The username or password you provided does not match our records."));                        
                }
	}
	
	public void logout(){
            
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                Logger.getLogger(LoginController.class.getName()).log(Level.INFO, "User ({0}) loging out #" +DateUtility.getCurrentDateTime(), request.getUserPrincipal().getName());
                if(session != null){
                    session.invalidate();
		}
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/Login.xhtml?faces-redirect=true");
	}
	
}
