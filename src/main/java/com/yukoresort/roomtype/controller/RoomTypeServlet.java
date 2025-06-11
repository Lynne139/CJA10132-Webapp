package com.yukoresort.roomtype.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;

import com.yukoresort.roomtype.model.*;

public class RoomTypeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");// 避免傳來的中文變成亂碼
		String action = req.getParameter("action");

		// 新增
		if ("insert".equals(action)) { // 來自addRoomType.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			// ----------1.接收請求參數:輸入格式錯誤處理-------------
			// 房型名稱
			String roomTypeName = req.getParameter("roomTypeName");
			String roomTypeNameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (roomTypeName == null || (roomTypeName.trim()).length() == 0) {
				errorMsgs.add("請輸入房型名稱");
			} else if (!roomTypeName.trim().matches(roomTypeNameReg)) {
				errorMsgs.add("房型名稱 : 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			// 房型數量
			String amountStr = req.getParameter("roomTypeAmount");
			Integer roomTypeAmount = null;
			if (amountStr == null || (amountStr.trim()).length() == 0) {
				errorMsgs.add("請輸入房型數量");
			} else {
				try {
					roomTypeAmount = Integer.valueOf(amountStr.trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("房型數量格式不正確，須為阿拉伯數字");
				}
			}

			// 房型介紹
			String roomTypeContent = req.getParameter("roomTypeContent");
			if (roomTypeContent == null || (roomTypeContent.trim()).length() == 0) {
				errorMsgs.add("請輸入房型介紹");
			}

			// 房型狀態
			String statusStr = req.getParameter("roomSaleStatus");
			Integer roomSaleStatus = Integer.valueOf(statusStr);

			// 房間照片
			InputStream in = req.getPart("roomTypePic").getInputStream();
			byte[] roomTypePic = null;
			if (in.available() != 0) {
				roomTypePic = new byte[in.available()];
				in.read(roomTypePic);
				in.close();
			} else {
				errorMsgs.add("請上傳房型照片");
			}

			// 房型價格
			String priceStr = req.getParameter("roomTypePrice");
			Integer roomTypePrice = null;
			if (priceStr == null || (priceStr.trim()).length() == 0) {
				errorMsgs.add("請輸入房型價格");
			} else {
				try {
					roomTypePrice = Integer.valueOf(priceStr.trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("房型金額格式不正確，須為阿拉伯數字");
				}
			}

			RoomTypeVO roomTypeVO = new RoomTypeVO();
			roomTypeVO.setRoomTypeName(roomTypeName);
			roomTypeVO.setRoomTypeAmount(roomTypeAmount);
			roomTypeVO.setRoomTypeContent(roomTypeContent);
			roomTypeVO.setRoomSaleStatus(roomSaleStatus);
			roomTypeVO.setRoomTypePic(roomTypePic);
			roomTypeVO.setRoomTypePrice(roomTypePrice);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("roomTypeVO", roomTypeVO);
				// 將圖片 byte[] 轉成 base64 字串
				byte[] imageBytes = roomTypeVO.getRoomTypePic();
				if (imageBytes != null) {
					String base64Image = Base64.getEncoder().encodeToString(imageBytes);
					req.setAttribute("base64Image", base64Image);
				}
				RequestDispatcher failureView = req.getRequestDispatcher("addRoomType.jsp");
				failureView.forward(req, res);
				return;
			}
			// -----------2.開始查詢資料-----------------------
			RoomTypeService roomTypeSvc = new RoomTypeService();
			roomTypeVO = roomTypeSvc.addRoomType(roomTypeName, roomTypeAmount, roomTypeContent, roomSaleStatus,
					roomTypePic, roomTypePrice);

			// -------------3.查詢完成，準備轉交------------------
			req.setAttribute("roomTypeVO", roomTypeVO);
			// 將圖片 byte[] 轉成 base64 字串
			byte[] imageBytes = roomTypeVO.getRoomTypePic();
			if (imageBytes != null) {
				String base64Image = Base64.getEncoder().encodeToString(imageBytes);
				req.setAttribute("base64Image", base64Image);
			}
			String url = "/roomType/listAllRoomType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		// 修改
		// 第一步:先從員工名單點選修改按鈕，先從資料庫中查詢點選的員工資料顯示於修改頁面
		if ("getOne_For_Update".equals(action)) { // 來自listAllRoomType.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			// ----------1.接收請求參數:輸入格式錯誤處理-------------
			Integer roomTypeId = Integer.valueOf(req.getParameter("roomTypeId"));

			// -----------2.開始查詢資料-----------------------
			RoomTypeService roomTypeSvc = new RoomTypeService();
			RoomTypeVO roomTypeVO = roomTypeSvc.getOneRoomType(roomTypeId);

			// -------------3.查詢完成，準備轉交------------------
			req.setAttribute("roomTypeVO", roomTypeVO);
			// 將圖片 byte[] 轉成 base64 字串
			byte[] imageBytes = roomTypeVO.getRoomTypePic();
			if (imageBytes != null) {
				String base64Image = Base64.getEncoder().encodeToString(imageBytes);
				req.setAttribute("base64Image", base64Image);
			}
			String url = "/roomType/update_roomType_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}
		// 第二步:在修改頁面輸入資料並送出，並進行驗證
		if ("update".equals(action)) { // 來自update_roomType_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			// ----------1.接收請求參數:輸入格式錯誤處理-------------
			// 房型編號
			String roomTypeIdStr = req.getParameter("roomTypeId");
			Integer roomTypeId = Integer.valueOf(roomTypeIdStr);
			// 房型名稱
			String roomTypeName = req.getParameter("roomTypeName");
			String roomTypeNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (roomTypeName == null || (roomTypeName.trim()).length() == 0) {
				errorMsgs.add("請輸入房型名稱");
			} else if (!roomTypeName.trim().matches(roomTypeNameReg)) {
				errorMsgs.add("房型名稱 : 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}
			// 房型數量
			String amountStr = req.getParameter("roomTypeAmount");
			Integer roomTypeAmount = null;
			if (amountStr == null || (amountStr.trim()).length() == 0) {
				errorMsgs.add("請輸入房型數量");
			} else {
				try {
					roomTypeAmount = Integer.valueOf(amountStr.trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("房型數量格式不正確，須為阿拉伯數字");
				}
			}
			// 房型介紹
			String roomTypeContent = req.getParameter("roomTypeContent");
			if (roomTypeContent == null || (roomTypeContent.trim()).length() == 0) {
				errorMsgs.add("請輸入房型介紹");
			}
			// 房型狀態
			String StatusStr = req.getParameter("roomSaleStatus");
			Integer roomSaleStatus = Integer.valueOf(StatusStr);

			// 房間照片
			Part newImage = req.getPart("roomTypePic");
			InputStream in = newImage.getInputStream();					
			byte[] roomTypePic = null;
			if(in.available()!=0) {
				//有上傳新圖片
				roomTypePic = new byte[in.available()];
				in.read(roomTypePic);
				in.close();
			}else {//沒有新圖片，使用舊圖片
				RoomTypeService roomTypeSvc = new RoomTypeService(); // 你自己的 Service 類別
			    RoomTypeVO oldVO = roomTypeSvc.getOneRoomType(roomTypeId);
			    roomTypePic = oldVO.getRoomTypePic(); // 這裡就是舊的圖片 byte[]
			}

			// 房型價格
			String priceStr = req.getParameter("roomTypePrice");
			Integer roomTypePrice = null;
			if (priceStr == null || (priceStr.trim()).length() == 0) {
				errorMsgs.add("請輸入房型價格");
			} else {
				try {
					roomTypePrice = Integer.valueOf(priceStr.trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("房型金額格式不正確，須為阿拉伯數字");
				}
			}
			// 放進VO物件
			RoomTypeVO roomTypeVO = new RoomTypeVO();
			roomTypeVO.setRoomTypeId(roomTypeId);
			roomTypeVO.setRoomTypeName(roomTypeName);
			roomTypeVO.setRoomTypeAmount(roomTypeAmount);
			roomTypeVO.setRoomTypeContent(roomTypeContent);
			roomTypeVO.setRoomSaleStatus(roomSaleStatus);
			roomTypeVO.setRoomTypePic(roomTypePic);
			roomTypeVO.setRoomTypePrice(roomTypePrice);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("roomTypeVO", roomTypeVO);
				// 將圖片 byte[] 轉成 base64 字串
				byte[] imageBytes = roomTypeVO.getRoomTypePic();
				if (imageBytes != null && imageBytes.length > 0) {
					String base64Image = Base64.getEncoder().encodeToString(imageBytes);
					req.setAttribute("base64Image", base64Image);
				}
				RequestDispatcher failureView = req.getRequestDispatcher("/roomType/update_roomType_input.jsp");
				failureView.forward(req, res);
				return;
			}

			// -----------2.開始查詢資料-----------------------
			RoomTypeService roomTypeSvc = new RoomTypeService();
			roomTypeVO = roomTypeSvc.updateRoomType(roomTypeId, roomTypeName, roomTypeAmount, roomTypeContent,
					roomSaleStatus, roomTypePic, roomTypePrice);

			// -------------3.查詢完成，準備轉交------------------
			req.setAttribute("roomTypeVO", roomTypeVO);
			// 將圖片 byte[] 轉成 base64 字串
			byte[] imageBytes = roomTypeVO.getRoomTypePic();
			if (imageBytes != null) {
				String base64Image = Base64.getEncoder().encodeToString(imageBytes);
				req.setAttribute("base64Image", base64Image);
			}
			String url = "/roomType/listOneRoomType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}
		// 查詢
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			// ----------1.接收請求參數:輸入格式錯誤處理-------------
			String str = req.getParameter("roomTypeId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入房型編號");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/roomType/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			Integer roomTypeId = null;
			try {
				roomTypeId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("房型編號格式不正確，須為阿拉伯數字");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/roomType/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			// -----------2.開始查詢資料-----------------------
			RoomTypeService roomTypeSvc = new RoomTypeService();
			RoomTypeVO roomTypeVO = roomTypeSvc.getOneRoomType(roomTypeId);
			if (roomTypeVO == null) {
				errorMsgs.add("查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/roomType/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			// -------------3.查詢完成，準備轉交------------------
			req.setAttribute("roomTypeVO", roomTypeVO);
			// 將圖片 byte[] 轉成 base64 字串
			byte[] imageBytes = roomTypeVO.getRoomTypePic();
			if (imageBytes != null) {
				String base64Image = Base64.getEncoder().encodeToString(imageBytes);
				req.setAttribute("base64Image", base64Image);
			}
			String url = "/roomType/listOneRoomType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		// 刪除
		if ("delete".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			// ----------1.接收請求參數:輸入格式錯誤處理-------------
			Integer roomTypeId = Integer.valueOf(req.getParameter("roomTypeId"));
			// -----------2.開始查詢資料-----------------------
			RoomTypeService roomTypeSvc = new RoomTypeService();
			roomTypeSvc.deleteRoomType(roomTypeId);

			// -------------3.查詢完成，準備轉交------------------
			String url = "/roomType/listAllRoomType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

	}

}
