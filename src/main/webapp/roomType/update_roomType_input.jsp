<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.yukoresort.roomtype.model.*"%>

<%
//見com.yukoresort.roomtype.controller.roomTypeServlet.java第163行存入req的roomTypeVO物件 (此為從資料庫取出的roomTypeVO, 也可以是輸入格式有錯誤時的roomTypeVO物件)
RoomTypeVO roomTypeVO = (RoomTypeVO) request.getAttribute("roomTypeVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>房型資料修改 - update_roomType_input.jsp</title>

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
		<tr>
			<td>
				<h3>房型資料修改 - update_roomType_input.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="roomType.do" name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>房型編號:<font color=red><b>*</b></font></td>
				<td><%=roomTypeVO.getRoomTypeId()%></td>
			</tr>
			<tr>
				<td>房型名稱:</td>
				<td><input type="TEXT" name="roomTypeName"
					value="<%=roomTypeVO.getRoomTypeName()%>" size="45" /></td>
			</tr>
			<tr>
				<td>房型數量:</td>
				<td><input type="TEXT" name="roomTypeAmount"
					value="<%=roomTypeVO.getRoomTypeAmount()%>" size="45" /></td>
			</tr>
			<tr>
				<td>房型介紹:</td>
				<td><input type="TEXT" name="roomTypeContent"
					value="<%=roomTypeVO.getRoomTypeContent()%>" size="45" /></td>
			</tr>
			<tr>
				<td>房型狀態:</td>
				<td><input type="radio" name="roomSaleStatus" value="1"
					<%=(roomTypeVO == null || roomTypeVO.getRoomSaleStatus() == 1) ? "checked" : ""%>
					size="45" />上架 <input type="radio" name="roomSaleStatus" value="0"
					<%=(roomTypeVO != null && roomTypeVO.getRoomSaleStatus() == 0) ? "checked" : ""%>
					size="45" />下架</td>
			</tr>
			<tr>
				<td>房型金額:</td>
				<td><input type="TEXT" name="roomTypePrice"
					value="<%=roomTypeVO.getRoomTypePrice()%>" size="45" /></td>
			</tr>
			<tr>
				<td>房型照片:</td>
				<td><input type="file" name="roomTypePic" id="roomTypePicInput" accept="image/*">
					<input type="hidden" name="roomTypeId" value="<%=roomTypeVO.getRoomTypeId()%>">
					<c:if test="${not empty base64Image}">
						<img id="previewImg" src="data:image/*;base64,${base64Image}" style="max-width: 200px; display: block;" /></td>
					</c:if>
				<td>				
			</tr>



		</table>
		<br>
		<input type="hidden" name="action" value="update">
		<input type="hidden" name="roomTypeId" value="<%=roomTypeVO.getRoomTypeId()%>">
		<input type="submit" value="送出修改">
	</FORM>

	<script>
		//抓img標籤
		var previewImg_el = document.getElementById("previewImg");
		var preview_img = function(file) {
			let reader = new FileReader();
			reader.readAsDataURL(file);
			reader.addEventListener("load", function() {
				previewImg_el.setAttribute("src", reader.result);
				previewImg_el.style.display = "block";
			});
		}
		//input標籤有變更
		var roomTypePicInput_el = document.getElementById("roomTypePicInput");
		roomTypePicInput_el.addEventListener("change", function(e) {
			if (this.files.length > 0) {
				preview_img(this.files[0]);
			} else {//點選取消回原介面
				previewImg_el.setAttribute("src", "");
				previewImg_el.style.display = "none";
			}
		});
	</script>
</body>
</html>