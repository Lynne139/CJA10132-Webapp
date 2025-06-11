package com.yukoresort.room.model;

import java.io.Serializable;

public class RoomVO implements Serializable{
	private Integer roomId;
	private Integer roomTypeId;
	private String roomGuestName;
	private Integer roomSaleStatus;
	private Integer roomStatus;
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public Integer getRoomTypeId() {
		return roomTypeId;
	}
	public void setRoomTypeId(Integer roomTypeId) {
		this.roomTypeId = roomTypeId;
	}
	public String getRoomGuestName() {
		return roomGuestName;
	}
	public void setRoomGuestName(String roomGuestName) {
		this.roomGuestName = roomGuestName;
	}
	public Integer getRoomSaleStatus() {
		return roomSaleStatus;
	}
	public void setRoomSaleStatus(Integer roomSaleStatus) {
		this.roomSaleStatus = roomSaleStatus;
	}
	public Integer getRoomStatus() {
		return roomStatus;
	}
	public void setRoomStatus(Integer roomStatus) {
		this.roomStatus = roomStatus;
	}
}
