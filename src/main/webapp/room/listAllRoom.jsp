<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.yukoresort.room.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
RoomService roomSvc = new RoomService();
List<RoomVO> list = roomSvc.getAll();
pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有房間資料 - listAllRoom.jsp</title>

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
	width: 800px;
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

	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有房間資料 - listAllRoom.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>房間編號</th>
			<th>房型編號</th>
			<th>住客姓名</th>
			<th>上下架狀態</th>
			<th>房間狀態</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="roomVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${roomVO.roomId}</td>
				<td>${roomVO.roomTypeId}</td>
				<td>${roomVO.roomGuestName}</td>
				<td><c:choose>
						<c:when test="${roomVO.roomSaleStatus == 1}">上架</c:when>
						<c:otherwise>下架</c:otherwise>
					</c:choose></td>
				<td><c:choose>
						<c:when test="${roomVO.roomStatus == 0}">未入住</c:when>
						<c:when test="${roomVO.roomStatus == 1}">已入住</c:when>
						<c:otherwise>待清潔</c:otherwise>
					</c:choose></td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/room/room.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="roomId" value="${roomVO.roomId}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/room/room.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="roomId" value="${roomVO.roomId}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>