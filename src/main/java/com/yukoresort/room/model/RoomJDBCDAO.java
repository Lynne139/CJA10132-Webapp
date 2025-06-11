package com.yukoresort.room.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomJDBCDAO implements RoomDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/yuko?serverTimezone=Asia/Taipei";
	String userid = "root";
	String password = "";
	
	private static final String INSERT_STMT =
			"INSERT INTO room (room_id, room_type_id, room_guest_name, room_sale_status, room_status) VALUES (?,?,?,?,?)";
	private static final String UPDATE = 
			"UPDATE room SET room_id=?, room_type_id=?, room_guest_name=?, room_sale_status=?, room_status=? WHERE room_id = ?";
	private static final String GET_ONE_STMT =
			"SELECT room_id, room_type_id, room_guest_name, room_sale_status, room_status FROM room WHERE room_id = ?";
	private static final String  GET_ALL_STMT =
		"SELECT * FROM room ORDER BY room_id";
	private static final String DELETE = 
			"DELETE FROM room WHERE room_id = ?";

	@Override
	public void insert(RoomVO roomVO) {
		Connection con =null;
		PreparedStatement pstmt =null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, roomVO.getRoomId());
			pstmt.setInt(2, roomVO.getRoomTypeId());
			pstmt.setString(3, roomVO.getRoomGuestName());
			pstmt.setInt(4, roomVO.getRoomSaleStatus());
			pstmt.setInt(5, roomVO.getRoomStatus());
			
			pstmt.executeUpdate();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
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
	public void update(RoomVO roomVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, roomVO.getRoomId());
			pstmt.setInt(2, roomVO.getRoomTypeId());
			pstmt.setString(3, roomVO.getRoomGuestName());
			pstmt.setInt(4, roomVO.getRoomSaleStatus());
			pstmt.setInt(5, roomVO.getRoomStatus());
			pstmt.setInt(6, roomVO.getRoomId());
			
			pstmt.executeUpdate();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
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
	public RoomVO findByPrimaryKey(Integer roomId) {
		RoomVO roomVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, roomId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomVO = new RoomVO();
				roomVO.setRoomId(rs.getInt("room_id"));
				roomVO.setRoomTypeId(rs.getInt("room_type_id"));
				roomVO.setRoomGuestName(rs.getString("room_guest_name"));
				roomVO.setRoomSaleStatus(rs.getInt("room_sale_status"));
				roomVO.setRoomStatus(rs.getInt("room_status"));
			}
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
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
		return roomVO;
	}

	@Override
	public List<RoomVO> getAll() {
		List<RoomVO> rooms = new ArrayList<RoomVO>();
		RoomVO roomVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomVO = new RoomVO();
				roomVO.setRoomId(rs.getInt("room_id"));
				roomVO.setRoomTypeId(rs.getInt("room_type_id"));
				roomVO.setRoomGuestName(rs.getString("room_guest_name"));
				roomVO.setRoomSaleStatus(rs.getInt("room_sale_status"));
				roomVO.setRoomStatus(rs.getInt("room_status"));
				rooms.add(roomVO);
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
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
		return rooms;
	}

	@Override
	public void delete(Integer roomId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, roomId);
			pstmt.executeUpdate();
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
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
//	public static void main(String[] args) {
//		RoomJDBCDAO dao = new RoomJDBCDAO();
		
		//新增
//		RoomVO roomVO1 = new RoomVO();
//		roomVO1.setRoomId(301);
//		roomVO1.setRoomTypeId(5);
//		roomVO1.setRoomGuestName("王小明");
//		roomVO1.setRoomSaleStatus(1);
//		roomVO1.setRoomStatus(1);
//		dao.insert(roomVO1);
		
		//修改
//		RoomVO roomVO2 = new RoomVO();
//		roomVO2.setRoomId(301);
//		roomVO2.setRoomTypeId(2);
//		roomVO2.setRoomGuestName("王大明");
//		roomVO2.setRoomSaleStatus(1);
//		roomVO2.setRoomStatus(2);
//		dao.update(roomVO2);
		
		//刪除
//		dao.delete(301);
		
		//單筆查詢
//		RoomVO roomVO3 = dao.findByPrimaryKey(301);
//		System.out.print(roomVO3.getRoomId() +",");
//		System.out.print(roomVO3.getRoomTypeId() +",");
//		System.out.print(roomVO3.getRoomGuestName() +",");
//		System.out.print(roomVO3.getRoomSaleStatus() +",");
//		System.out.print(roomVO3.getRoomStatus());
//		System.out.println("----------------------------------");
		
		
		//全部查詢
//		List<RoomVO> list = dao.getAll();
//		for(RoomVO aRoomType : list) {
//			System.out.print(aRoomType.getRoomId() +",");
//			System.out.print(aRoomType.getRoomTypeId() +",");
//			System.out.print(aRoomType.getRoomGuestName() +",");
//			System.out.print(aRoomType.getRoomSaleStatus() +",");
//			System.out.print(aRoomType.getRoomStatus());
//			System.out.println();
//		}
		 
//	}
}
