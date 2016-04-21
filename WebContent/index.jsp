<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SQL Web GUI</title>
</head>
<body>
	<div id="sqlFormDiv" style="float:left;">
		<form name="sqlForm" method="post" action="sql.do">
			<textarea name="sqlText" cols="40" rows="6"
				placeholder="Enter a SQL statement">${results.getSql()}</textarea>
			<br /> <input type="submit" value="Execute" />
		</form>
	</div>
	<div id="metadata">
		<select id="tableNames">
			<c:forEach items="${tables}" var="table">
				<option value="${table}" label="${table}">${table}</option>
			</c:forEach>
		</select>
	</div>
	<div class="clear" style="clear:both;"></div>
	<div id="errors">
		<c:if test="${results.getException() != null}">
		${results.getException().toString()}
		</c:if>
	</div>
	<div id="results">
		<c:choose>
			<c:when test="${results.getResultSet() != null}">
				<c:set var="rows" value="${results.getResultSet()}" />
		        ${rows.size()-1} rows retrieved
				<table id="resultRows">
					<tr class="resultRowHeader resultRow">
						<c:forEach items="${rows[0]}" var="col">
							<th>${col}</th>
						</c:forEach>
					</tr>
					<c:forEach items="${rows}" var="row" begin="1">
						<tr class="resultRow">
							<c:forEach items="${row}" var="col">
								<td>${col}</td>
							</c:forEach>
						</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:when test="${results.getUpdateCount() != null}">
				<c:set var="updateCount" value="${results.getUpdateCount()}" />
			${results.getUpdateCount()} row<c:if test="${updateCount > 1}">s</c:if> affected.
			</c:when>
		</c:choose>
	</div>

</body>
</html>