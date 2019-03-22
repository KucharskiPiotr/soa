<%--
  Created by IntelliJ IDEA.
  User: Piotr
  Date: 19.03.2019
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Guest book</title>
</head>
<body>
    <h2>Welcome, ${name} ${surname}</h2>
    <h3>Please, submit your opinion:</h3>
    <p style="color:red">${errorMsg}</p>
<form action="guestBook" method="post">
    <p>Your email: <input type="text" name="email"></p>
    <p>Comment: <input type="text" name="comment"></p>
    <button type="submit">Send Feedback</button>
</form>
<hr/>
<c:forEach items="${feedbacks}" var="feedback">
    <p><b>${feedback.user.name} ${feedback.user.surname}</b> (${feedback.email}) says:</p>
    <p style="padding-left:1.5cm">${feedback.comment}</p>
</c:forEach>
</body>
</html>
