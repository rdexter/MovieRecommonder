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

<form action="logoutAction.jsp">
<table class="tborder" width="50%" cellspacing="1" cellpadding="3" align="center">	
	<tr>
		<td colspan="2"> Are you sure you would like to logout?</td>			
	</tr>		
	<tr>
		<td> 
			<input type="submit" id="submit" name="submit" value="OK"></input>
		</td>
		<td> 
			<input type="submit" id="submit" name="submit" value="Cancel"></input>
		</td>
	</tr>
</table>
<br/>

</form>	