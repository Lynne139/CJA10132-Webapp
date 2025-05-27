package com.yukoresort.roomtype.model;

import java.util.List;

public interface RoomTypeDAO_interface {
	public void insert(RoomTypeVO roomTypeVO);
	public void update(RoomTypeVO roomTypeVO);
	public RoomTypeVO findByPrimaryKey(Integer roomTypeId);
	public List<RoomTypeVO> getAll();
	public void delete(Integer roomTypeId); 
}
