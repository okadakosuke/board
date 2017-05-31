package board.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.beans.User;

@WebFilter(urlPatterns = {"/manage", "/signup", "/setting/*"})
public class ManagerFilter implements Filter{
	public void doFilter(ServletRequest request, ServletResponse response,
		FilterChain chain){

		try{

			String target = ((HttpServletRequest)request).getRequestURI();
			HttpSession session = ((HttpServletRequest)request).getSession();
			String topURL ="/board";

			User user = (User) session.getAttribute("loginUser");


			if(user.getDepartment_id() !=1) {

				List<String> messages = new ArrayList<String>() ;
				messages.add("権限がありません");
				session.setAttribute("errorMessages", messages);

				session.setAttribute("target", target);
				((HttpServletResponse) response).sendRedirect(topURL);
				return;

			}
			chain.doFilter(request, response);

		}catch (ServletException se) {
		}catch (IOException e){
		}
}

	public void init(FilterConfig filterConfig){
	}

	public void destroy(){
	}
}