<%@ page import="model.Users" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit product</title>
</head>
<body>
<h3>Edit User</h3>
<form method="post">
    <input type="hidden" value="${user.getId()}" name="id" />
    <label>Name</label><br>
    <input name="name" value="${user.getName()}" /><br><br>
    <label>Password</label><br>
    <input name="password" value="${user.getPass()}" /><br><br>
    <label>Email</label><br>
    <input name="email" value="${user.getEmail()}" type="text" /><br><br>
    <input type="submit" value="Send" />
</form>
</body>
</html>