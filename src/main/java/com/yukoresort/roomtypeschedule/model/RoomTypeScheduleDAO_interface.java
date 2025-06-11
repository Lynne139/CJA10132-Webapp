package com.yukoresort.roomtypeschedule.model;

import java.sql.Date;
import java.util.List;

public interface RoomTypeScheduleDAO_interface {
	public void insert(RoomTypeScheduleVO roomTypeScheduleVO);
	public void update(RoomTypeScheduleVO roomTypeScheduleVO);
	public RoomTypeScheduleVO findByPrimaryKey(Integer roomTypeScheduleId);
	public List<RoomTypeScheduleVO> getAll();
	public void delete (Integer roomTypeScheduleId);
	public List<RoomTypeScheduleVO> findByOrderDate(Date roomOrderDate);
	
}
