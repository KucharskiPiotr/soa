<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Test</title>
  </head>
  <body>
  <h1>Witaj świecie</h1>
  <%
    out.println(new Date().toString());
  %>
  </body>
</html>
