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

@WebFilter("/*")
public class LoginFilter implements Filter{
	public void doFilter(ServletRequest request, ServletResponse response,
		FilterChain chain){
		try{

			String target = ((HttpServletRequest)request).getRequestURI();
			System.out.println(target);
			HttpSession session = ((HttpServletRequest)request).getSession();

			String loginURL = "/board/login";


			if(!target.equals(loginURL) && !target.matches("^.*css$")) {

				if (session == null) {

					session = ((HttpServletRequest)request).getSession(true);
					session.setAttribute("target", target);

					List<String> messages = new ArrayList<String>() ;
					messages.add("ログインしてください");
					session.setAttribute("errorMessages", messages);
					((HttpServletResponse) response).sendRedirect(loginURL);
					return;

				} else {
					Object loginCheck = session.getAttribute("loginUser");
					if (loginCheck == null){

						List<String> messages = new ArrayList<String>() ;
						messages.add("ログインしてください");
						session.setAttribute("errorMessages", messages);

						session.setAttribute("target", target);
						((HttpServletResponse) response).sendRedirect(loginURL);
						return;
					}
				}
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