package com.yukoresort.room.model;

import java.util.List;

public class RoomService {
	private RoomDAO_interface dao;
	
	public RoomService() {
		dao = new RoomJNDIDAO();
	}
	
	public RoomVO addRoom(Integer roomId, Integer roomTypeId, String roomGuestName, Integer roomSaleStatus, Integer roomStatus) {
		RoomVO roomVO = new RoomVO();
		
		roomVO.setRoomId(roomId);
		roomVO.setRoomTypeId(roomTypeId);
		roomVO.setRoomGuestName(roomGuestName);
		roomVO.setRoomSaleStatus(roomSaleStatus);
		roomVO.setRoomStatus(roomStatus);
		dao.insert(roomVO);
		
		return roomVO;
	}
	
	public RoomVO updateRoom(Integer roomId, Integer roomTypeId, String roomGuestName, Integer roomSaleStatus, Integer roomStatus) {
		RoomVO roomVO = new RoomVO();
		
		roomVO.setRoomId(roomId);
		roomVO.setRoomTypeId(roomTypeId);
		roomVO.setRoomGuestName(roomGuestName);
		roomVO.setRoomSaleStatus(roomSaleStatus);
		roomVO.setRoomStatus(roomStatus);
		dao.update(roomVO);
		
		return roomVO;
	}
	
	public RoomVO getOneRoom(Integer roomId) {
		return dao.findByPrimaryKey(roomId);
	}
	
	public List<RoomVO> getAll() {
		return dao.getAll();
	}
	
	public void delete(Integer roomId) {
		dao.delete(roomId);
	}
}
