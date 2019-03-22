<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html">
<h:head>
    <title>Facelets Hello Request</title>
</h:head>
<h:body>
    <h2>Hello, Whats your name?</h2>
    <h:inputText id="username"
                 title="Name: "
                 value="#{hello.name}"
                 required="true"
                 requiredMessage="Error: A name is required"
                 maxlength="25" ></h:inputText>
    <p></p>
    <h:commandButton id="submit" value="Submit" action="yo"></h:commandButton>
    <h:commandButton id="reset" value="Reset" type="reset"></h:commandButton>
</h:body>
</html>
