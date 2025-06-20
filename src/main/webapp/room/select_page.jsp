<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Yukoresort Room: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>Yukoresort Room: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Yukoresort Room: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllRoom.jsp'>List</a> all Rooms.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="room.do" >
        <b>輸入房間編號 (如1):</b>
        <input type="text" name="roomId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="roomSvc" scope="page" class="com.yukoresort.room.model.RoomService" />
   
  <li>
     <FORM METHOD="post" ACTION="room.do" >
       <b>選擇房間編號:</b>
       <select size="1" name="roomId">
         <c:forEach var="roomVO" items="${roomSvc.all}" > 
          <option value="${roomVO.roomId}">${roomVO.roomId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="room.do" >
       <b>選擇住客姓名:</b>
       <select size="1" name="roomId">
         <c:forEach var="roomVO" items="${roomSvc.all}" > 
         	<c:if test="${not empty roomVO.roomGuestName}">
	          <option value="${roomVO.roomId}">${roomVO.roomGuestName}
         	</c:if>
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>房間管理</h3>

<ul>
  <li><a href='addRoom.jsp'>Add</a> a new Room.</li>
</ul>

</body>
</html>