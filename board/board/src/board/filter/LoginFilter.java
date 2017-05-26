package board.filter;

import java.io.IOException;

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

import org.apache.commons.lang.StringUtils;

import board.beans.User;

@WebFilter("/*")
public class LoginFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		HttpSession session = request.getSession();
		
		
		if (isLogined(request) == false) {
			redirectToLoginPage(req, res);
			return;
		}
		chain.doFilter(request, response);

	}


	public boolean isLogined(HttpServletRequest request){
		HttpSession session = request.getSession();

		User loginUser = (User) session.getAttribute("loginUser")
			if(loginUser ==null ) {
				return false;
		}
	}

	@Override
	public void destroy() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@
	@Override
	public void init(FilterConfig paramFilterConfig) throws ServletException {
		// TODO 自動生成されたメソッド・スタブ

	}
}