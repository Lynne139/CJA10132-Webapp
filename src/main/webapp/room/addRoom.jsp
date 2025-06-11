<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.yukoresort.room.model.*"%>

<% //見com.yukoresort.roomtype.controller.RoomTypeServlet.java第238??行存入req的RoomTypeVO物件 (此為輸入格式有錯誤時的RoomTypeVO物件)
RoomVO roomVO = (RoomVO) request.getAttribute("roomVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>房間資料新增 - addRoom.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>房間資料新增 - addRoom.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="room.do" name="form1">
<table>
	
	
	
	
	<tr>
		<td>房間編號:</td>
		<td><input type="TEXT" name="roomId" value="<%= (roomVO==null)? "999" : roomVO.getRoomId()%>" size="45"/></td>
	</tr>
	
	<jsp:useBean id="roomTypeSvc" scope="page" class="com.yukoresort.roomtype.model.RoomTypeService" />
	<tr>
		<td>房型編號:</td>
		<td><select size="1" name="roomTypeId">
			<c:forEach var="roomTypeVO" items="${roomTypeSvc.all}">
				<option value="${roomTypeVO.roomTypeId}" ${(roomVO.roomTypeId==roomTypeVO.roomTypeId)? 'selected':''}>${roomTypeVO.roomTypeName}
				</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td>住客姓名:</td>
		<td><input type="TEXT" name="roomGuestName" value="<%= (roomVO==null)? "" : roomVO.getRoomGuestName()%>" size="45"/></td>
	</tr>
	<tr>
		<td>上下架狀態:</td>
		<td><input type="radio" name="roomSaleStatus"   value="1" <%= (roomVO == null || roomVO.getRoomSaleStatus() == 1)? "checked" : ""%> size="45"/>上架
			<input type="radio" name="roomSaleStatus"   value="0" <%= (roomVO != null && roomVO.getRoomSaleStatus() == 0)? "checked" : ""%> size="45"/>下架</td>
	</tr>
	<tr>
		<td>房間狀態:</td>
		<td><select name="roomStatus" size="1">
			<option value="0" ${(roomVO.roomStatus==0)? 'selected':'' }>未入住</option>
			<option value="1" ${(roomVO.roomStatus==1)? 'selected':'' }>已入住</option>
			<option value="2" ${(roomVO.roomStatus==2)? 'selected':'' }>待清潔</option>
		</select></td>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>

</body>
</html>