package com.yukoresort.room.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import com.yukoresort.room.model.*;

public class RoomServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		//新增
		if("insert".equals(action)) {// 來自addRoom.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			// ----------1.接收請求參數:輸入格式錯誤處理-------------
			//房間編號
			String roomIdStr = req.getParameter("roomId");
			Integer roomId = null;
			if(roomIdStr == null || roomIdStr.trim().length() == 0) {
				errorMsgs.add("請輸入房間編號");
			}else {
				try {
					roomId = Integer.valueOf(roomIdStr.trim());
				}catch(NumberFormatException e) {
					errorMsgs.add("房間編號格式不正確，須為阿拉伯數字");
				}
			}
			
			//房型編號(外來鍵)
			Integer roomTypeId = Integer.valueOf(req.getParameter("roomTypeId").trim());
			
			//住客姓名
			String roomGuestName = req.getParameter("roomGuestName");
			String gnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
			if(roomGuestName == null || roomGuestName.trim().length() == 0) {
				errorMsgs.add("請輸入房客姓名");
			}else if(!roomGuestName.trim().matches(gnameReg)) {
				errorMsgs.add("房客姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到50之間");
			}
			
			//上下架狀態
			String roomSaleStatusStr = req.getParameter("roomSaleStatus");
			Integer roomSaleStatus = Integer.valueOf(roomSaleStatusStr);
			
			//房間狀態
			String roomStatusStr = req.getParameter("roomStatus");
			Integer roomStatus = Integer.valueOf(roomStatusStr);
			
			RoomVO roomVO = new RoomVO();
			roomVO.setRoomId(roomId);
			roomVO.setRoomTypeId(roomTypeId);
			roomVO.setRoomGuestName(roomGuestName);
			roomVO.setRoomSaleStatus(roomSaleStatus);
			roomVO.setRoomStatus(roomStatus);
			
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("roomVO", roomVO);
				RequestDispatcher failureView = req.getRequestDispatcher("addRoom.jsp");
				failureView.forward(req, res);
				return;
			}
			// -----------2.開始查詢資料-----------------------
			RoomService roomSvc = new RoomService();
			roomVO = roomSvc.addRoom(roomId, roomTypeId, roomGuestName, roomSaleStatus, roomStatus);
			// -------------3.查詢完成，準備轉交------------------
			req.setAttribute("roomVO", roomVO);
			String url = "/room/listAllRoom.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		//修改
		//第一步:先從房間名單點選修改按鈕，先從資料庫中查詢點選的房間資料顯示於修改頁面
		if("getOne_For_Update".equals(action)) {// 來自listAllRoom.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// ----------1.接收請求參數:輸入格式錯誤處理-------------
			Integer roomId = Integer.valueOf(req.getParameter("roomId"));
			// -----------2.開始查詢資料-----------------------
			RoomService roomSvc = new RoomService();
			RoomVO roomVO = roomSvc.getOneRoom(roomId);
			// -------------3.查詢完成，準備轉交------------------
			req.setAttribute("roomVO", roomVO);
			String url = "/room/update_room_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		//第二步:在修改頁面輸入資料並送出，並進行驗證
		if("update".equals(action)) {// 來自update_room_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// ----------1.接收請求參數:輸入格式錯誤處理-------------
			//房間編號
			String roomIdStr = req.getParameter("roomId");
			Integer roomId = null;
			if(roomIdStr == null || roomIdStr.trim().length() == 0) {
				errorMsgs.add("請輸入房間編號");
			}else {
				try {
					roomId = Integer.valueOf(roomIdStr.trim());
				}catch(NumberFormatException e) {
					errorMsgs.add("房間編號格式不正確，須為阿拉伯數字");
				}
			}
			//房型編號(外來鍵)
			Integer roomTypeId = Integer.valueOf(req.getParameter("roomTypeId"));
			//住客姓名
			String roomGuestName = req.getParameter("roomGuestName");
			String gnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
			if(roomGuestName == null || roomGuestName.trim().length() == 0) {
				errorMsgs.add("請輸入住客姓名");
			}else if(!roomGuestName.matches(gnameReg)){
				errorMsgs.add("房客姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到50之間");
			}
			//上下架狀態
			Integer roomSaleStatus = Integer.valueOf(req.getParameter("roomSaleStatus"));
			//房間狀態
			Integer roomStatus = Integer.valueOf(req.getParameter("roomStatus"));
			
			RoomVO roomVO = new RoomVO();
			roomVO.setRoomId(roomId);
			roomVO.setRoomTypeId(roomTypeId);
			roomVO.setRoomGuestName(roomGuestName);
			roomVO.setRoomSaleStatus(roomSaleStatus);
			roomVO.setRoomStatus(roomStatus);
			
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("roomVO", roomVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/room/update_room_input.jsp");
				failureView.forward(req, res);
			}
			// -----------2.開始查詢資料-----------------------
			RoomService roomSvc = new RoomService();
			roomVO = roomSvc.updateRoom(roomId, roomTypeId, roomGuestName, roomSaleStatus, roomStatus);
			// -------------3.查詢完成，準備轉交------------------
			req.setAttribute("roomVO", roomVO);
			String url = "/room/listOneRoom.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		//查詢
		if("getOne_For_Display".equals(action)) {// 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			// ----------1.接收請求參數:輸入格式錯誤處理-------------
			String roomIdStr = req.getParameter("roomId");
			if(roomIdStr == null || roomIdStr.trim().length() ==0) {
				errorMsgs.add("請輸入查詢條件");
			}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/room/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			
			Integer roomId = null;
			try {
				roomId = Integer.valueOf(roomIdStr);					
			}catch(NumberFormatException e) {
				errorMsgs.add("房間編號格式不正確，須為阿拉伯數字");
			}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/room/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			// -----------2.開始查詢資料-----------------------
			RoomService roomSvc = new RoomService();
			RoomVO roomVO = roomSvc.getOneRoom(roomId);
			if(roomVO == null) {
				errorMsgs.add("查無資料");
			}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/room/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			// -------------3.查詢完成，準備轉交------------------
			req.setAttribute("roomVO", roomVO);
			String url = "/room/listOneRoom.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		//刪除
		if("delete".equals(action)) {// 來自listAllRoom.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// ----------1.接收請求參數:輸入格式錯誤處理-------------
			Integer roomId = Integer.valueOf(req.getParameter("roomId"));
			// -----------2.開始查詢資料-----------------------
			RoomService roomSvc = new RoomService();
			roomSvc.delete(roomId);
			// -------------3.查詢完成，準備轉交------------------
			String url = "/room/listAllRoom.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}
	}

}
