<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Yukoresort RoomType: Home</title>

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
   <tr><td><h3>Yukoresort RoomType: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Yukoresort RoomType: Home</p>

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
  <li><a href='listAllRoomType.jsp'>List</a> all RoomTypes.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="roomType.do" >
        <b>輸入房型編號 (如1):</b>
        <input type="text" name="roomTypeId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="roomTypeSvc" scope="page" class="com.yukoresort.roomtype.model.RoomTypeService" />
   
  <li>
     <FORM METHOD="post" ACTION="roomType.do" >
       <b>選擇房型編號:</b>
       <select size="1" name="roomTypeId">
         <c:forEach var="roomTypeVO" items="${roomTypeSvc.all}" > 
          <option value="${roomTypeVO.roomTypeId}">${roomTypeVO.roomTypeId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="roomType.do" >
       <b>選擇房型名稱:</b>
       <select size="1" name="roomTypeId">
         <c:forEach var="roomTypeVO" items="${roomTypeSvc.all}" > 
          <option value="${roomTypeVO.roomTypeId}">${roomTypeVO.roomTypeName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>房型管理</h3>

<ul>
  <li><a href='addRoomType.jsp'>Add</a> a new RoomType.</li>
</ul>

</body>
</html>