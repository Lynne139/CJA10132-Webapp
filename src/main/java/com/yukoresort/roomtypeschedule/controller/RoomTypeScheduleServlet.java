package com.yukoresort.roomtypeschedule.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import com.yukoresort.roomtypeschedule.model.RoomTypeScheduleService;
import com.yukoresort.roomtypeschedule.model.RoomTypeScheduleVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RoomTypeScheduleServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		//新增
		if("insert".equals(action)) {// 從/roomTypeSchedule/addRoomTS.jsp
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			// ----------1.接收請求參數:輸入格式錯誤處理-------------
			//房型編號(外來鍵)下拉式選單
			Integer roomTypeId = Integer.valueOf(req.getParameter("roomTypeId").trim());
			
			//房間數量
			String roomAmountStr = req.getParameter("roomAmount");
			Integer roomAmount = null;
			if(roomAmountStr == null || roomAmountStr.trim().length() == 0) {
				errorMsgs.add("請輸入房間數量");
			}else {
				try {
					roomAmount = Integer.valueOf(roomAmountStr.trim());
				}catch(NumberFormatException e) {
					roomAmount = 0;
					errorMsgs.add("房間數量格式錯誤，請輸入阿拉伯數字");
				}
			}
			//房間總預定量(由相同訂單明細數量加總得之，需要可以人工修改)
			String roomRSVBookedStr = req.getParameter("roomRSVBooked");
			Integer roomRSVBooked = null;
			if(roomRSVBookedStr == null || roomRSVBookedStr.trim().length() == 0) {
				errorMsgs.add("請輸入房型總預定量");
			}else {
				try {
					roomRSVBooked = Integer.valueOf(roomRSVBookedStr);
				}catch(NumberFormatException e) {
					roomRSVBooked = 0;
					errorMsgs.add("房型總預定量格式錯誤，請輸入阿拉伯數字。");
				}
			}
			//房間預定日期
			Date roomOrderDate = null;
			try {
				roomOrderDate = Date.valueOf(req.getParameter("roomOrderDate").trim());
			}catch(IllegalArgumentException e) {
				roomOrderDate = new Date(System.currentTimeMillis());
				errorMsgs.add("請選擇日期");
			}
			
			RoomTypeScheduleVO roomTSVO = new RoomTypeScheduleVO();
			roomTSVO.setRoomTypeId(roomTypeId);
			roomTSVO.setRoomAmount(roomAmount);
			roomTSVO.setRoomRSVBooked(roomRSVBooked);
			roomTSVO.setRoomOrderDate(roomOrderDate);
			
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("roomTSVO", roomTSVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/roomTypeSchedule/addRoomTS.jsp");
				failureView.forward(req, res);
				return;
			}
			// -----------2.開始查詢資料-----------------------
			RoomTypeScheduleService roomTSSvc = new RoomTypeScheduleService();
			roomTSVO = roomTSSvc.addRoomTS(roomTypeId, roomAmount, roomRSVBooked, roomOrderDate);
			// -------------3.查詢完成，準備轉交------------------
			String url = "/roomTypeSchedule/listAllRoomTS.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		//修改
		//第一步要線跳轉到更新頁面
		if("getOne_For_Update".equals(action)) {//從listAllRoomTS.jsp
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// ----------1.接收請求參數:輸入格式錯誤處理-------------
			Integer roomTSId = Integer.valueOf(req.getParameter("roomTypeScheduleId").trim());
			// -----------2.開始查詢資料-----------------------
			RoomTypeScheduleService roomTSSvc = new RoomTypeScheduleService();
			RoomTypeScheduleVO roomTSVO = roomTSSvc.getOneRoomTS(roomTSId);
			// -------------3.查詢完成，準備轉交------------------
			req.setAttribute("roomTypeScheduleVO", roomTSVO);
			String url = "/roomTypeSchedule/update_roomTS_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		//第二步更新玩跳回預定表列表
		if("update".equals(action)) {//從/roomTypeSchedule/update_roomTS_input.jsp
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// ----------1.接收請求參數:輸入格式錯誤處理-------------
			//房型預定編號
			Integer roomTSId = Integer.valueOf(req.getParameter("roomTSId").trim());
			//房型編號
			Integer roomTypeId = Integer.valueOf(req.getParameter("roomTypeId").trim());
			//房間數量
			String roomAmountStr = req.getParameter("roomAmount");
			Integer roomAmount = null;
			if(roomAmountStr == null || roomAmountStr.trim().length() == 0) {
				errorMsgs.add("請輸入房間數量");
			}else {
				try {
					roomAmount = Integer.valueOf(roomAmountStr.trim());
				}catch(NumberFormatException e) {
					roomAmount = 0;
					errorMsgs.add("房間數量格式錯誤，請輸入阿拉伯數字。");
				}
			}
			//房間預定總數量
			String roomRSVBookedStr = req.getParameter("roomRSVBooked");
			Integer roomRSVBooked = null;
			if(roomRSVBookedStr == null || roomRSVBookedStr.trim().length() == 0) {
				errorMsgs.add("請輸入房型總預定量");
			}else {
				try {
					roomRSVBooked = Integer.valueOf(roomRSVBookedStr.trim());
				}catch(NumberFormatException e) {
					roomRSVBooked = 0;
					errorMsgs.add("房型總預定量格式錯誤，請輸入阿拉伯數字。");
				}
			}
			//房間預定日期
			Date roomOrderDate = null;
			try {
				roomOrderDate = Date.valueOf(req.getParameter("roomOrderDate"));
			}catch(IllegalArgumentException e) {
				roomOrderDate = new Date(System.currentTimeMillis());
				errorMsgs.add("請選擇日期");
			}
			
			RoomTypeScheduleVO roomTSVO = new RoomTypeScheduleVO();
			roomTSVO.setRoomTypeScheduleId(roomTSId);
			roomTSVO.setRoomTypeId(roomTypeId);
			roomTSVO.setRoomAmount(roomAmount);
			roomTSVO.setRoomRSVBooked(roomRSVBooked);
			roomTSVO.setRoomOrderDate(roomOrderDate);
			
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("roomTypeScheduleVO", roomTSVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/roomTypeSchedule/update_roomTS_input.jsp");
				failureView.forward(req, res);
				return;
			}
			// -----------2.開始查詢資料-----------------------
			RoomTypeScheduleService roomTSSvc = new RoomTypeScheduleService();
			roomTSVO = roomTSSvc.updateRoomTS(roomTSId, roomTypeId, roomAmount, roomRSVBooked, roomOrderDate);
			// -------------3.查詢完成，準備轉交------------------
			String url = "/roomTypeSchedule/listAllRoomTS.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		//查詢
		if("getOne_For_Display".equals(action)) {//從select_page.jsp
			List<String> errorMsgs =new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// ----------1.接收請求參數:輸入格式錯誤處理-------------
			//房型預定編號
			String roomTSIdStr = req.getParameter("roomTypeScheduleId");
			if(roomTSIdStr == null || roomTSIdStr.trim().length() == 0) {
				errorMsgs.add("請輸入房型預定編號");
			}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/roomTypeSchedule/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			Integer roomTSId = null;
			try {
				roomTSId = Integer.valueOf(req.getParameter("roomTypeScheduleId").trim());
			}catch(NumberFormatException e) {
				roomTSId = 0;
				errorMsgs.add("房型預定編號格式錯誤，請輸入阿拉伯數字。");
			}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/roomTypeSchedule/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			// -----------2.開始查詢資料-----------------------
			RoomTypeScheduleService roomTSSvc = new RoomTypeScheduleService();
			RoomTypeScheduleVO roomTSVO = roomTSSvc.getOneRoomTS(roomTSId);
			if(roomTSVO == null) {
				errorMsgs.add("查無資料");
			}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/roomTypeSchedule/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			// -------------3.查詢完成，準備轉交------------------
			req.setAttribute("roomTypeScheduleVO", roomTSVO);
			String url="/roomTypeSchedule/listOneRoomTS.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		//刪除
		if("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// ----------1.接收請求參數:輸入格式錯誤處理-------------
			//房型預定編號
			Integer roomTSId = Integer.valueOf(req.getParameter("roomTypeScheduleId").trim());
			// -----------2.開始查詢資料-----------------------
			RoomTypeScheduleService roomTSSvc = new RoomTypeScheduleService();
			roomTSSvc.deleteRoomTS(roomTSId);
			// -------------3.查詢完成，準備轉交------------------
			String url="/roomTypeSchedule/listAllRoomTS.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		//日期多筆查詢
		if("get_By_Date".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// ----------1.接收請求參數:輸入格式錯誤處理-------------
			Date roomOrderDate = null;
			try {
				roomOrderDate = Date.valueOf(req.getParameter("roomOrderDate"));
			}catch(IllegalArgumentException e) {
				roomOrderDate = new Date(System.currentTimeMillis());
				errorMsgs.add("請選擇日期");
			}
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("roomOrderDate", roomOrderDate);
				RequestDispatcher failureView = req.getRequestDispatcher("/roomTypeSchedule/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			
			// -----------2.開始查詢資料-----------------------
			RoomTypeScheduleService RoomTSSvc = new RoomTypeScheduleService();
			List<RoomTypeScheduleVO> scdls = RoomTSSvc.getByOrderDate(roomOrderDate);
			
			if(scdls == null || scdls.isEmpty()) {
				errorMsgs.add("查無資料");
			}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/roomTypeSchedule/select_page.jsp");
				failureView .forward(req, res);
				return;
			}
			// -------------3.查詢完成，準備轉交------------------
			req.setAttribute("list", scdls);
			String url = "/roomTypeSchedule/listByOrderDate.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}
}
