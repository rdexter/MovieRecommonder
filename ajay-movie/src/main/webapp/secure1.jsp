<title>Logout Sample JSP 3</title>
<%
/*
 * Author: Kevin H. Le (kevin@kevinhle.com -- http://kevinhle.com)
 * Sample program specially prepared for the article published in JavaWorld:
 * "Solving the logout problem properly and elegantly - Solutions for JSPs and Struts".
 */
response.setHeader("Cache-Control","no-cache"); //forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control","no-store"); //directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //causes the proxy cache to see the page as "stale"
response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility

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
This is Secure1 page.  This page is pretended to contain secure information.
</p>