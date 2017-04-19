<%@ page import="java.sql.*" %>

<%
/*
 * Author: Kevin H. Le (kevin@kevinhle.com -- http://kevinhle.com)
 * Sample program specially prepared for the article published in JavaWorld:
 * "Solving the logout problem properly and elegantly - Solutions for JSPs and Struts".
 */
String userName = request.getParameter("userName");
String password = request.getParameter("password");
String lastLogon = request.getParameter("lastLogon");
long lastLogonForm = Long.parseLong(lastLogon);

Connection con = null;
Statement stmt = null;
ResultSet rs = null;
RequestDispatcher rd = request.getRequestDispatcher("home.jsp"); //forward to home page by default
try {
	//Change the next 3 lines to use correct values in your own environment
	String dbURL = "jdbc:mysql://localhost/logoutSamplesdb";
	Class.forName("com.mysql.jdbc.Driver"); 
	con = DriverManager.getConnection(dbURL, "root", "");
	
	stmt = con.createStatement();
	rs = stmt.executeQuery("select password, lastLogon from USER where userName = '" + userName + "'");	
	
	if (rs.next()) { //query only returns 1 record in the result set
		
		if (rs.getString("password").equals(password)) { //if valid password
			long lastLogonDB = rs.getLong("lastLogon");
			if (lastLogonForm > lastLogonDB) {
				session.setAttribute("User", userName); //Saves user name string in the session object
				stmt.executeUpdate("update USER set lastLogon= " + lastLogonForm + " where userName = '" + userName + "'");
			}
			else {
				request.setAttribute("Error", "Session has ended.  Please login.");			
				rd = request.getRequestDispatcher("login.jsp");
			}
		}
		else	{ //password does not match,i.e. invalid user password
			request.setAttribute("Error", "Invalid password.");			
			rd = request.getRequestDispatcher("login.jsp");			
		}
	} //no record in the result set,i.e. invalid user name
	else {
		request.setAttribute("Error", "Invalid user name.");			
		rd = request.getRequestDispatcher("login.jsp");
	}
}	
catch (Exception e) { //database problem
	request.setAttribute("Error", "Problem accessing security realm.");
	rd = request.getRequestDispatcher("login.jsp");
	e.printStackTrace();	
}
finally {		
	try {
		stmt.close();
		con.close();
	}
	catch (Exception ignore) {
	}
}

rd.forward(request, response);

%>


