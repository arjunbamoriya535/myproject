<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="db.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Login using JSP example -- Mitrajit's Tech Blog </title>
  <link rel="stylesheet" href="styles.css" />
  </head>
  
  <body>
    <h1>Read the full article -- <a href="" target="_blank">Login using JSP example</a> in <a href="http://www.mitrajit.com" target="_blank"></a>Mitrajit's Tech Blog</a></h1>
  
    <%
      //Remove any session attribute if present
      session.removeAttribute("name");
      String msg = "";
      String button = request.getParameter("submitBtn");
      if(button != null && button.equals("Login")) {
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        
        if(username.length() > 0 && password.length() > 0) {
          Statement st = con.createStatement();
          ResultSet rs = st.executeQuery("select * from users where username='"+username+"' and password='"+password+"'");
          if(rs.next()) {
            session.setAttribute("name", rs.getString("name"));
            response.sendRedirect("welcome.jsp");
          } else {
            msg = "Invalid username and password!";
          }
        } else {
          msg = "Both fields are required!";
        }
      }
    %>
    
    
    
    <div class="loginWrapper">
      <h2>ADMIN PANEL</h2>
      <form action="" method="post">
        <label>Username : </label><input type="text" name="username" id="username" value=""/>
        <label>Password : </label><input type="password" name="password" id="password" value=""/>
        <input type="submit" name="submitBtn" id="submitBtn" value="Login" align="middle"/>
        <span><%=msg %></span>
      </form>
    </div><!-- end of .loginWrapper -->
  </body>
</html>