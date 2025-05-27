<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yukoresort.roomtype.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  RoomTypeVO roomTypeVO = (RoomTypeVO) request.getAttribute("roomTypeVO"); //RoomTypeServlet.java(Concroller), 存入req的RoomTypeVO物件
%>

<html>
<head>
<title>房型資料 - listOneRoomType.jsp</title>

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
	<tr><td>
		 <h3>房型資料 - listOneRoomType.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>房型編號</th>
		<th>房型名稱</th>
		<th>房型數量</th>
		<th>房型介紹</th>
		<th>房型狀態</th>
		<th>房型金額</th>
	</tr>
	<tr>
		<td><%=roomTypeVO.getRoomTypeId()%></td>
		<td><%=roomTypeVO.getRoomTypeName()%></td>
		<td><%=roomTypeVO.getRoomTypeAmount()%></td>
		<td><%=roomTypeVO.getRoomTypeContent()%></td>
		<td><%=roomTypeVO.getRoomSaleStatus()%></td>
		<td><%=roomTypeVO.getRoomTypePrice()%></td>
	</tr>
</table>

</body>
</html>