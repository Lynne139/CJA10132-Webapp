package com.yukoresort.roomtype.model;

import java.util.List;

public class RoomTypeService {
	private RoomTypeDAO_interface dao;
	
	public RoomTypeService() {
		dao = new RoomTypeJNDIDAO();
	}
	
	public RoomTypeVO addRoomType(String roomTypeName, Integer roomTypeAmount, String roomTypeContent, Integer roomSaleStatus, byte[] roomTypePic, Integer roomTypePrice) {
		RoomTypeVO roomTypeVO = new RoomTypeVO();
		
		roomTypeVO.setRoomTypeName(roomTypeName);
		roomTypeVO.setRoomTypeAmount(roomTypeAmount);
		roomTypeVO.setRoomTypeContent(roomTypeContent);
		roomTypeVO.setRoomSaleStatus(roomSaleStatus);
		roomTypeVO.setRoomTypePic(roomTypePic);
		roomTypeVO.setRoomTypePrice(roomTypePrice);
		dao.insert(roomTypeVO);
		
		return roomTypeVO;
	}
	
	public RoomTypeVO updateRoomType(Integer roomTypeId, String roomTypeName, Integer roomTypeAmount, String roomTypeContent, Integer roomSaleStatus, byte[] roomTypePic, Integer roomTypePrice) {
		RoomTypeVO roomTypeVO = new RoomTypeVO();
		
		roomTypeVO.setRoomTypeId(roomTypeId);
		roomTypeVO.setRoomTypeName(roomTypeName);
		roomTypeVO.setRoomTypeAmount(roomTypeAmount);
		roomTypeVO.setRoomTypeContent(roomTypeContent);
		roomTypeVO.setRoomSaleStatus(roomSaleStatus);
		roomTypeVO.setRoomTypePic(roomTypePic);
		roomTypeVO.setRoomTypePrice(roomTypePrice);
		dao.update(roomTypeVO);
		
		return roomTypeVO;
	}
	
	public RoomTypeVO getOneRoomType(Integer roomTypeId) {
		return dao.findByPrimaryKey(roomTypeId);
	}
	
	public List<RoomTypeVO> getAll() {
		return dao.getAll();
	}
	
	public void deleteRoomType(Integer roomTypeId) {
		dao.delete(roomTypeId);
	}

}
