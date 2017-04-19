<%
/*
 * Author: Kevin H. Le (kevin@kevinhle.com -- http://kevinhle.com)
 * Sample program specially prepared for the article published in JavaWorld:
 * "Solving the logout problem properly and elegantly - Solutions for JSPs and Struts".
 */
String action = (String) request.getParameter("submit");
if (action.equalsIgnoreCase("OK")) {
	session.removeAttribute("User");
	if (session != null) {
		session.invalidate();
	}
	RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
	rd.forward(request, response);
}
else {
	RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
	rd.forward(request, response);
}

%>