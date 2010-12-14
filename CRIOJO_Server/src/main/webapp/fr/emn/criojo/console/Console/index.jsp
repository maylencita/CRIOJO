<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<body>
<h2>CRIOJO Console</h2>
Example:<BR>
<code>
(public:P; private:S@"http://criojo.appspot.com/relation")

!T => new(x)S(x,P)
</code>

<p>Write your script here, then click execute:</p>
<form name="scriptForm" action="" method="POST">
    <textarea rows="35" cols="100" name="script">${it.script}</textarea>
    </br>
    <input type="submit" name="button" value="Execute" />
</form>

</body>
</html>
