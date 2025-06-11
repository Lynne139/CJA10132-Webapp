<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.yukoresort.roomtypeschedule.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
RoomTypeScheduleVO roomTypeScheduleVO = (RoomTypeScheduleVO) request.getAttribute("roomTypeScheduleVO"); //RoomTypeScheduleServlet.java(Concroller), 存入req的RoomTypeScheduleVO物件
%>

<html>
<head>
<title>房型預定資料 - listOneRoomTS.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>

	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>房型預定資料 - listOneRoomTS.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>房型預定編號</th>
			<th>房型編號</th>
			<th>房間數量</th>
			<th>已訂房間數量</th>
			<th>日期</th>
		</tr>
		<tr>
			<td><%=roomTypeScheduleVO.getRoomTypeScheduleId()%></td>
			<td><%=roomTypeScheduleVO.getRoomTypeId()%></td>
			<td><%=roomTypeScheduleVO.getRoomAmount()%></td>
			<td><%=roomTypeScheduleVO.getRoomRSVBooked()%></td>
			<td><%=roomTypeScheduleVO.getRoomOrderDate()%></td>
		</tr>
	</table>

</body>
</html>