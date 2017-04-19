<title>Logout Sample JSP 1</title>
<%
/*
 * Author: Kevin H. Le (kevin@kevinhle.com -- http://kevinhle.com)
 * Sample program specially prepared for the article published in JavaWorld:
 * "Solving the logout problem properly and elegantly - Solutions for JSPs and Struts".
 */
String userName = (String) session.getAttribute("User");
if (null == userName) {
	request.setAttribute("Error", "Session has ended.  Please login.");
	RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
	rd.forward(request, response);
}
%>

<a href="secure1.jsp">Secure 1</a>
<br/>
<a href="secure2.jsp">Secure 2</a>
<br/>
<a href="logout.jsp">Logout</a>
<br/>
<p>
This is home page.  This page is pretended to contain secure information.
</p>