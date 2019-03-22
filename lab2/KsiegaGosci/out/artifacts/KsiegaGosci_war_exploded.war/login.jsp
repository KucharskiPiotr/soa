<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login page</title>
</head>
<body>
<h2>Login Page</h2>
<h3 style="color: red">${errorMsg}</h3>
    <form action="authorize" method="post">
        <p>Login: <input type="text" name="login"></p>
        <p>Password: <input type="password" name="password"></p>
        <p><button type="submit">Login</button></p>
    </form>
</body>
</html>