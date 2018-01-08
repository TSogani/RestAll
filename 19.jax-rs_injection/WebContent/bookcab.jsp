<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BookCab</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/rest/cab-management/bookcab" method="POST">
		<table border="2">
			<tr>
				<td>Source : </td>
				<td>
					<input type="text" name="source"/>
				</td>
			</tr>
			<tr>
				<td>Destination : </td>
				<td>
					<input type="text" name="destination"/>
				</td>
			</tr>
			<tr>
				<td>CabType : </td>
				<td>
					<select name="cabType">
						<option value="sedan">sedan</option>
						<option value="mini">mini</option>
						<option value="hatchback">hatchback</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="book"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>