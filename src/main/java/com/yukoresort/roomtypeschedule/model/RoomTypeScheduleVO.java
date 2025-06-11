package com.yukoresort.roomtypeschedule.model;

import java.io.Serializable;
import java.sql.Date;

public class RoomTypeScheduleVO implements Serializable{
	private Integer roomTypeScheduleId;
	private Integer roomTypeId;
	private Integer roomAmount;
	private Integer roomRSVBooked;
	private Date roomOrderDate;
	public Integer getRoomTypeScheduleId() {
		return roomTypeScheduleId;
	}
	public void setRoomTypeScheduleId(Integer roomTypeScheduleId) {
		this.roomTypeScheduleId = roomTypeScheduleId;
	}
	public Integer getRoomTypeId() {
		return roomTypeId;
	}
	public void setRoomTypeId(Integer roomTypeId) {
		this.roomTypeId = roomTypeId;
	}
	public Integer getRoomAmount() {
		return roomAmount;
	}
	public void setRoomAmount(Integer roomAmount) {
		this.roomAmount = roomAmount;
	}
	public Integer getRoomRSVBooked() {
		return roomRSVBooked;
	}
	public void setRoomRSVBooked(Integer roomRSVBooked) {
		this.roomRSVBooked = roomRSVBooked;
	}
	public Date getRoomOrderDate() {
		return roomOrderDate;
	}
	public void setRoomOrderDate(Date roomOrderDate) {
		this.roomOrderDate = roomOrderDate;
	}
	

}
