package com.yukoresort.roomtype.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RoomTypeJNDIDAO implements RoomTypeDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDByuko");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO room_type (room_type_name, room_type_amount, room_type_content, room_sale_status, room_type_pic, room_type_price) VALUES (?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE room_type SET room_type_name=?, room_type_amount=?, room_type_content=?, room_sale_status=?, room_type_pic=?, room_type_price=? WHERE room_type_id = ?";
	private static final String GET_ONE_STMT = "SELECT room_type_id, room_type_name, room_type_amount, room_type_content, room_sale_status,room_type_pic, room_type_price FROM room_type WHERE room_type_id = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM room_type ORDER BY room_type_id";
	private static final String DELETE = "DELETE FROM room_type WHERE room_type_id = ?";

	@Override
	public void insert(RoomTypeVO roomTypeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();// 建立連線
			pstmt = con.prepareStatement(INSERT_STMT);// 送出指令

			// 將收到的roomTypeVO物件內容取出，並用對應的型別設定到sql指令的?內
			pstmt.setString(1, roomTypeVO.getRoomTypeName());
			pstmt.setInt(2, roomTypeVO.getRoomTypeAmount());
			pstmt.setString(3, roomTypeVO.getRoomTypeContent());
			pstmt.setInt(4, roomTypeVO.getRoomSaleStatus());
			pstmt.setBytes(5, roomTypeVO.getRoomTypePic());
			pstmt.setInt(6, roomTypeVO.getRoomTypePrice());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {// 關閉資源
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(RoomTypeVO roomTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, roomTypeVO.getRoomTypeName());
			pstmt.setInt(2, roomTypeVO.getRoomTypeAmount());
			pstmt.setString(3, roomTypeVO.getRoomTypeContent());
			pstmt.setInt(4, roomTypeVO.getRoomSaleStatus());
			pstmt.setBytes(5, roomTypeVO.getRoomTypePic());
			pstmt.setInt(6, roomTypeVO.getRoomTypePrice());
			pstmt.setInt(7, roomTypeVO.getRoomTypeId());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public RoomTypeVO findByPrimaryKey(Integer roomTypeId) {
		RoomTypeVO roomTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, roomTypeId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomTypeVO = new RoomTypeVO();
				roomTypeVO.setRoomTypeId(rs.getInt("room_type_id"));
				roomTypeVO.setRoomTypeName(rs.getString("room_type_name"));
				roomTypeVO.setRoomTypeAmount(rs.getInt("room_type_amount"));
				roomTypeVO.setRoomTypeContent(rs.getString("room_type_content"));
				roomTypeVO.setRoomSaleStatus(rs.getInt("room_sale_status"));
				roomTypeVO.setRoomTypePic(rs.getBytes("room_type_pic"));
				roomTypeVO.setRoomTypePrice(rs.getInt("room_type_price"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return roomTypeVO;
	}

	@Override
	public List<RoomTypeVO> getAll() {
		List<RoomTypeVO> roomTypes = new ArrayList<RoomTypeVO>();
		RoomTypeVO roomTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomTypeVO = new RoomTypeVO();
				roomTypeVO.setRoomTypeId(rs.getInt("room_type_id"));
				roomTypeVO.setRoomTypeName(rs.getString("room_type_name"));
				roomTypeVO.setRoomTypeAmount(rs.getInt("room_type_amount"));
				roomTypeVO.setRoomTypeContent(rs.getString("room_type_content"));
				roomTypeVO.setRoomSaleStatus(rs.getInt("room_sale_status"));
				roomTypeVO.setRoomTypePic(rs.getBytes("room_type_pic"));
				roomTypeVO.setRoomTypePrice(rs.getInt("room_type_price"));
				roomTypes.add(roomTypeVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return roomTypes;
	}

	@Override
	public void delete(Integer roomTypeId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, roomTypeId);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
