<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.yukoresort.roomtypeschedule.model.*"%>

<% //見com.yukoresort.roomtypeschedule.controller.roomTypeScheduleServlet.java第163行存入req的roomTypeScheduleVO物件 (此為從資料庫取出的roomTypeScheduleVO, 也可以是輸入格式有錯誤時的roomTypeScheduleVO物件)
 RoomTypeScheduleVO roomTypeScheduleVO = (RoomTypeScheduleVO) request.getAttribute("roomTypeScheduleVO");
%>
--<%= roomTypeScheduleVO==null %>--${roomTypeScheduleVO.roomTypeId}--<!-- for line 81 -->

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>房型預定資料修改 - update_roomTS_input.jsp</title>

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
		 <h3>房型預定資料修改 - update_roomTS_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

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
	<tr>
		<td>房型預定編號:<font color=red><b>*</b></font></td>
		<td><%=roomTypeScheduleVO.getRoomTypeScheduleId()%></td>
	</tr>
	<jsp:useBean id="roomTypeSvc" scope="page" class="com.yukoresort.roomtype.model.RoomTypeService" />
	<tr>
		<td>房型編號:</td>
		<td><select size="1" name="roomTypeId">
			<c:forEach var="roomTypeVO" items="${roomTypeSvc.all}">
				<option value="${roomTypeVO.roomTypeId}" ${(roomTypeScheduleVO.roomTypeId==roomTypeVO.roomTypeId)? 'selected':''}>${roomTypeVO.roomTypeName}
				</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td>房間數量:</td>
		<td><input type="TEXT" name="roomAmount"   value="<%=roomTypeScheduleVO.getRoomAmount()%>" size="45"/></td>
	</tr>
	<tr>
		<td>已訂房間數量:</td>
		<td><input type="TEXT" name="roomRSVBooked"   value="<%=roomTypeScheduleVO.getRoomRSVBooked()%>" size="45"/></td>
	</tr>
	<tr>
		<td>日期:</td>
		<td><input type="date" name="roomOrderDate" value="<%=roomTypeScheduleVO.getRoomOrderDate()%>" required /></td>
	</tr>

	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="roomTSId" value="<%=roomTypeScheduleVO.getRoomTypeScheduleId()%>">
<input type="submit" value="送出修改"></FORM>
</body>
</html>