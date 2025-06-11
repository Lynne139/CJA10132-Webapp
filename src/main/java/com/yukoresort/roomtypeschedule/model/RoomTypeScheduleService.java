package com.yukoresort.roomtypeschedule.model;

import java.sql.Date;
import java.util.List;

public class RoomTypeScheduleService {
	private RoomTypeScheduleDAO_interface dao;
	
	public RoomTypeScheduleService() {
		dao = new RoomTypeScheduleJNDIDAO();
	}
	
	public RoomTypeScheduleVO addRoomTS(Integer roomTypeId, Integer roomAmount, Integer roomRSVBooked, Date roomOrderDate) {
		RoomTypeScheduleVO roomTypeScheduleVO = new RoomTypeScheduleVO();
		roomTypeScheduleVO.setRoomTypeId(roomTypeId);
		roomTypeScheduleVO.setRoomAmount(roomAmount);
		roomTypeScheduleVO.setRoomRSVBooked(roomRSVBooked);
		roomTypeScheduleVO.setRoomOrderDate(roomOrderDate);
		dao.insert(roomTypeScheduleVO);
		return roomTypeScheduleVO;
	}
	
	public RoomTypeScheduleVO updateRoomTS(Integer roomTypeScheduleId, Integer roomTypeId, Integer roomAmount, Integer roomRSVBooked, Date roomOrderDate) {
		RoomTypeScheduleVO roomTypeScheduleVO = new RoomTypeScheduleVO();
		roomTypeScheduleVO.setRoomTypeScheduleId(roomTypeScheduleId);
		roomTypeScheduleVO.setRoomTypeId(roomTypeId);
		roomTypeScheduleVO.setRoomAmount(roomAmount);
		roomTypeScheduleVO.setRoomRSVBooked(roomRSVBooked);
		roomTypeScheduleVO.setRoomOrderDate(roomOrderDate);
		dao.update(roomTypeScheduleVO);
		return roomTypeScheduleVO;
	}
	
	public RoomTypeScheduleVO getOneRoomTS(Integer roomTypeScheduleId) {
		return dao.findByPrimaryKey(roomTypeScheduleId);
	}
	
	public List<RoomTypeScheduleVO> getAll() {
		return dao.getAll();
	}

	public void deleteRoomTS(Integer roomTypeScheduleId) {
		dao.delete(roomTypeScheduleId);
	}
	
	public List<RoomTypeScheduleVO> getByOrderDate(Date roomOrderDate) {
		return dao.findByOrderDate(roomOrderDate);
	}
}
