package com.yukoresort.roomtypeschedule.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RoomTypeScheduleJNDIDAO implements RoomTypeScheduleDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/TestDByuko");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO room_type_schedule (room_type_id, room_amount, room_rsv_booked, room_order_date) VALUES (?,?,?,?)";
	private static final String UPDATE = "UPDATE room_type_schedule SET room_type_id=?, room_amount=?, room_rsv_booked=?, room_order_date=? WHERE room_type_schedule_id=?";
	private static final String GET_ONE_STMT = "SELECT room_type_schedule_id, room_type_id, room_amount, room_rsv_booked, room_order_date FROM room_type_schedule WHERE room_type_schedule_id = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM room_type_schedule ORDER BY room_type_schedule_id";
	private static final String DELETE = "DELETE FROM room_type_schedule WHERE room_type_schedule_id=?";
	private static final String GET_BY_ORDER_DATE = "SELECT * FROM room_type_schedule WHERE room_order_date = ? ORDER BY room_order_date = ?";
	

	@Override
	public void insert(RoomTypeScheduleVO roomTypeScheduleVO) {
		Connection con =null;
		PreparedStatement pstmt =null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, roomTypeScheduleVO.getRoomTypeId());
			pstmt.setInt(2, roomTypeScheduleVO.getRoomAmount());
			pstmt.setInt(3, roomTypeScheduleVO.getRoomRSVBooked());
			pstmt.setDate(4, roomTypeScheduleVO.getRoomOrderDate());
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(RoomTypeScheduleVO roomTypeScheduleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, roomTypeScheduleVO.getRoomTypeId());
			pstmt.setInt(2, roomTypeScheduleVO.getRoomAmount());
			pstmt.setInt(3, roomTypeScheduleVO.getRoomRSVBooked());
			pstmt.setDate(4, roomTypeScheduleVO.getRoomOrderDate());
			pstmt.setInt(5, roomTypeScheduleVO.getRoomTypeScheduleId());
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public RoomTypeScheduleVO findByPrimaryKey(Integer roomTypeScheduleId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RoomTypeScheduleVO roomTypeScheduleVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, roomTypeScheduleId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomTypeScheduleVO = new RoomTypeScheduleVO();
				roomTypeScheduleVO.setRoomTypeScheduleId(rs.getInt("room_type_schedule_id"));
				roomTypeScheduleVO.setRoomTypeId(rs.getInt("room_type_id"));
				roomTypeScheduleVO.setRoomAmount(rs.getInt("room_amount"));
				roomTypeScheduleVO.setRoomRSVBooked(rs.getInt("room_rsv_booked"));
				roomTypeScheduleVO.setRoomOrderDate(rs.getDate("room_order_date"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return roomTypeScheduleVO;
	}

	@Override
	public List<RoomTypeScheduleVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RoomTypeScheduleVO> scdls = new ArrayList<RoomTypeScheduleVO>();
		RoomTypeScheduleVO roomTypeScheduleVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomTypeScheduleVO = new RoomTypeScheduleVO();
				roomTypeScheduleVO.setRoomTypeScheduleId(rs.getInt("room_type_schedule_id"));
				roomTypeScheduleVO.setRoomTypeId(rs.getInt("room_type_id"));
				roomTypeScheduleVO.setRoomAmount(rs.getInt("room_amount"));
				roomTypeScheduleVO.setRoomRSVBooked(rs.getInt("room_rsv_booked"));
				roomTypeScheduleVO.setRoomOrderDate(rs.getDate("room_order_date"));
				scdls.add(roomTypeScheduleVO);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return scdls;
	}

	@Override
	public void delete(Integer roomTypeScheduleId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, roomTypeScheduleId);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null) {
				try {
					pstmt.close();					
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public List<RoomTypeScheduleVO> findByOrderDate(Date roomOrderDate) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RoomTypeScheduleVO> scdls = new ArrayList<RoomTypeScheduleVO>();
		RoomTypeScheduleVO roomTypeScheduleVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_ORDER_DATE);
			
			pstmt.setDate(1, roomOrderDate);
			pstmt.setDate(2, roomOrderDate);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomTypeScheduleVO = new RoomTypeScheduleVO();
				roomTypeScheduleVO.setRoomTypeScheduleId(rs.getInt("room_type_schedule_id"));
				roomTypeScheduleVO.setRoomTypeId(rs.getInt("room_type_id"));
				roomTypeScheduleVO.setRoomAmount(rs.getInt("room_amount"));
				roomTypeScheduleVO.setRoomRSVBooked(rs.getInt("room_rsv_booked"));
				roomTypeScheduleVO.setRoomOrderDate(rs.getDate("room_order_date"));
				scdls.add(roomTypeScheduleVO);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return scdls;
		
	}

//	public static void main(String[] args) {
//		RoomTypeScheduleJDBCDAO dao = new RoomTypeScheduleJDBCDAO();
		
		//新增
//		RoomTypeScheduleVO roomTSVO1 = new RoomTypeScheduleVO();
//		roomTSVO1.setRoomTypeId(5);
//		roomTSVO1.setRoomAmount(10);
//		roomTSVO1.setRoomRSVBooked(1);
//		roomTSVO1.setRoomOrderDate(Date.valueOf("2025-10-01"));
//		dao.insert(roomTSVO1);
		
		//修改
//		RoomTypeScheduleVO roomTSVO2 = new RoomTypeScheduleVO();
//		roomTSVO2.setRoomTypeScheduleId(3);
//		roomTSVO2.setRoomTypeId(5);
//		roomTSVO2.setRoomAmount(11);
//		roomTSVO2.setRoomRSVBooked(2);
//		roomTSVO2.setRoomOrderDate(Date.valueOf("2025-10-02"));
//		dao.update(roomTSVO2);
		
		//刪除
//		dao.delete(3);
		
		//單筆查詢
//		RoomTypeScheduleVO roomTSVO3 = dao.findByPrimaryKey(3);
//		System.out.print(roomTSVO3.getRoomTypeScheduleId() +",");
//		System.out.print(roomTSVO3.getRoomTypeId() +",");
//		System.out.print(roomTSVO3.getRoomAmount() +",");
//		System.out.print(roomTSVO3.getRoomRSVBooked() +",");
//		System.out.print(roomTSVO3.getRoomOrderDate());
//		System.out.println("----------------------------------");
		
		
		//全部查詢
//		List<RoomTypeScheduleVO> list = dao.getAll();
//		for(RoomTypeScheduleVO aRoomTS : list) {
//			System.out.print(aRoomTS.getRoomTypeScheduleId() +",");
//			System.out.print(aRoomTS.getRoomTypeId() +",");
//			System.out.print(aRoomTS.getRoomAmount() +",");
//			System.out.print(aRoomTS.getRoomRSVBooked() +",");
//			System.out.print(aRoomTS.getRoomOrderDate());
//			System.out.println();
//		}
		 
//	}
}
