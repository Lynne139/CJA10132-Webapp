<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Yukoresort RoomTypeSchedule: Home</title>

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
   <tr><td><h3>Yukoresort RoomTypeSchedule: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Yukoresort RoomTypeSchedule: Home</p>

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
  <li><a href='listAllRoomTS.jsp'>List</a> all RoomTypes.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="roomTS.do" >
        <b>輸入房型預定編號 (如1):</b>
        <input type="text" name="roomTypeScheduleId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="roomTSSvc" scope="page" class="com.yukoresort.roomtypeschedule.model.RoomTypeScheduleService" />
   
  <li>
     <FORM METHOD="post" ACTION="roomTS.do" >
       <b>選擇房型預定編號:</b>
       <select size="1" name="roomTypeScheduleId">
         <c:forEach var="roomTSVO" items="${roomTSSvc.all}" > 
          <option value="${roomTSVO.roomTypeScheduleId}">${roomTSVO.roomTypeScheduleId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="roomTS.do" >
       <b>選擇查詢日期:</b>
       <input type="date" name="roomOrderDate" required />
       <input type="hidden" name="action" value="get_By_Date">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>房型管理</h3>

<ul>
  <li><a href='addRoomTS.jsp'>Add</a> a new RoomTypeSchedule.</li>
</ul>

</body>
</html>