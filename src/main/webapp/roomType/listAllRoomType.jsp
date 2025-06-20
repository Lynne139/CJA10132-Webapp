<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.yukoresort.roomtype.model.*"%>
<%@ page import="java.util.Base64"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
RoomTypeService roomTypeSvc = new RoomTypeService();
List<RoomTypeVO> list = roomTypeSvc.getAll();
pageContext.setAttribute("list", list);
%>



<html>
<head>
<title>所有房型資料 - listAllRoomType.jsp</title>

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
				<h3>所有房型資料 - listAllRoomType.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>房型編號</th>
			<th>房型名稱</th>
			<th>房型數量</th>
			<th>房型介紹</th>
			<th>房型狀態</th>
			<th>房型照片</th>
			<th>房型金額</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="roomTypeVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<%
			RoomTypeVO currentRoomType = (RoomTypeVO) pageContext.getAttribute("roomTypeVO");
			String base64Image = "";
			if (currentRoomType != null && currentRoomType.getRoomTypePic() != null) {
				byte[] imageBytes = currentRoomType.getRoomTypePic();
				base64Image = Base64.getEncoder().encodeToString(imageBytes);
			}
			pageContext.setAttribute("base64Image", base64Image);
			%>

			<tr>
				<td>${roomTypeVO.roomTypeId}</td>
				<td>${roomTypeVO.roomTypeName}</td>
				<td>${roomTypeVO.roomTypeAmount}</td>
				<td>${roomTypeVO.roomTypeContent}</td>
				<%-- 				<td>${roomTypeVO.roomSaleStatus}</td> --%>
				<td><c:choose>
						<c:when test="${roomTypeVO.roomSaleStatus == 1}">上架</c:when>
						<c:otherwise>下架</c:otherwise>
					</c:choose></td>
				<td><c:if test="${not empty base64Image}">
						<img src="data:image/jpeg;base64,${base64Image}"
							style="max-width: 200px;" />
					</c:if></td>
				<td>${roomTypeVO.roomTypePrice}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/roomType/roomType.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="roomTypeId" value="${roomTypeVO.roomTypeId}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/roomType/roomType.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="roomTypeId" value="${roomTypeVO.roomTypeId}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>