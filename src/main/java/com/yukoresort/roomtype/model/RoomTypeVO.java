package com.yukoresort.roomtype.model;

import java.io.Serializable;

public class RoomTypeVO implements Serializable{
	private Integer roomTypeId;
	private String roomTypeName;
	private Integer roomTypeAmount;
	private String roomTypeContent;
	private Boolean roomSaleStatus;
	private byte[] roomTypePic;
	private Integer roomTypePrice;
	public Integer getRoomTypeId() {
		return roomTypeId;
	}
	public void setRoomTypeId(Integer roomTypeId) {
		this.roomTypeId = roomTypeId;
	}
	public String getRoomTypeName() {
		return roomTypeName;
	}
	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}
	public Integer getRoomTypeAmount() {
		return roomTypeAmount;
	}
	public void setRoomTypeAmount(Integer roomTypeAmount) {
		this.roomTypeAmount = roomTypeAmount;
	}
	public String getRoomTypeContent() {
		return roomTypeContent;
	}
	public void setRoomTypeContent(String roomTypeContent) {
		this.roomTypeContent = roomTypeContent;
	}
	public Boolean getRoomSaleStatus() {
		return roomSaleStatus;
	}
	public void setRoomSaleStatus(Boolean roomSaleStatus) {
		this.roomSaleStatus = roomSaleStatus;
	}
	public byte[] getRoomTypePic() {
		return roomTypePic;
	}
	public void setRoomTypePic(byte[] roomTypePic) {
		this.roomTypePic = roomTypePic;
	}
	public Integer getRoomTypePrice() {
		return roomTypePrice;
	}
	public void setRoomTypePrice(Integer roomTypePrice) {
		this.roomTypePrice = roomTypePrice;
	}
	
	

}
