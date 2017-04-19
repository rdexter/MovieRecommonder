<title>Logout Sample JSP 3</title>
<%@ page import="java.lang.Long" %>

<% 
/*
 * Author: Kevin H. Le (kevin@kevinhle.com -- http://kevinhle.com)
 * Sample program specially prepared for the article published in JavaWorld:
 * "Solving the logout problem properly and elegantly - Solutions for JSPs and Struts".
 */
String error = (String) request.getAttribute("Error");
if (error != null) {
	out.write("<center><strong>");
	out.write("<font color=\"Red\">");
	out.write(error);
	out.write("</font>");
	out.write("</strong></center>");
}
%>
<form action="loginAction.jsp" focus="userName" method="POST">
<input name="lastLogon" type="hidden" value="<%= new java.lang.Long(System.currentTimeMillis()).toString() %>"/>
<table class="tborder" width="50%" cellspacing="1" cellpadding="3" align="center">
	<tr>
		<td colspan="2">Please login with a valid user name and password</td>
	</tr>
	<tr>
		<td><font color="Red">*</font>User Name: </td>
		<td> 
			<input id="userName" name="userName" type="text" size="20" value=""></input>
		</td>
	</tr>
	<tr>
		<td><font color="Red">*</font>Password: </td>
		<td> 
			<input id="password" name="password" type="password" size="20" value=""></input>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center"> 
			<input type="submit" id="submit" name="submit" value="Logon"></input>
		</td>
	</tr>
</table>
<br/>

</form>