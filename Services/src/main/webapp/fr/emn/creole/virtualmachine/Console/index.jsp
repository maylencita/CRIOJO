<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<body>
<h2>CREOLE Console</h2>

<p>Write your script here:</p>
<form name="scriptForm" action="" method="POST">
    <textarea rows="40" cols="100" name="script">
        ${it.script}
    </textarea>
    </br>
    <input type="submit" name="button" value="Execute" />
</form>

</body>
</html>
