<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.yukoresort.roomtypeschedule.model.*"%>

<% //見com.yukoresort.roomtypeschedule.controller.RoomTypeScheduleServlet.java第238??行存入req的RoomTypeScheduleVO物件 (此為輸入格式有錯誤時的RoomTypeScheduleVO物件)
RoomTypeScheduleVO roomTypeScheduleVO = (RoomTypeScheduleVO) request.getAttribute("roomTypeScheduleVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>房型資料新增 - addRoomTS.jsp</title>

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
		 <h3>房型資料新增 - addRoomTS.jsp</h3></td><td>
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

<FORM METHOD="post" ACTION="roomTS.do" name="form1">
<table>
	
	
	
	<jsp:useBean id="roomTypeSvc" scope="page" class="com.yukoresort.roomtype.model.RoomTypeService" />
	<tr>
		<td>房型編號:<font color=red><b>*</b></font></td>
		<td><select size="1" name="roomTypeId">
			<c:forEach var="roomTypeVO" items="${roomTypeSvc.all}">
				<option value="${roomTypeVO.roomTypeId}" ${(roomTypeScheduleVO.roomTypeId==roomTypeVO.roomTypeId)? 'selected':'' }>${roomTypeVO.roomTypeName}
				</c:forEach>
				</select></td>
	</tr>
	<tr>
		<td>房間數量:</td>
		<td><input type="TEXT" name="roomAmount"   value="<%= (roomTypeScheduleVO==null)? "10" : roomTypeScheduleVO.getRoomAmount()%>" size="45"/></td>
	</tr>
	<tr>
		<td>已訂房間數量:</td>
		<td><input type="TEXT" name="roomRSVBooked" value="<%= (roomTypeScheduleVO==null)? "0" : roomTypeScheduleVO.getRoomRSVBooked()%>" size="45"/></td>
	</tr>
	<tr>
		<td>日期:</td>
		<td><input type="date" name="roomOrderDate" value="<%= (roomTypeScheduleVO==null)? System.currentTimeMillis() : roomTypeScheduleVO.getRoomOrderDate()%>" required /></td>
	</tr>
	


</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>

</body>
</html>