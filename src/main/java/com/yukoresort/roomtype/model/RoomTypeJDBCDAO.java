package com.yukoresort.roomtype.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomTypeJDBCDAO implements RoomTypeDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/yuko?serverTimezone=Asia/Taipei";
	String userid = "root";
	String password = "";
	
	
	private static final String INSERT_STMT =
		"INSERT INTO room_type (room_type_name, room_type_amount, room_type_content, room_sale_status, room_type_pic, room_type_price) VALUES (?,?,?,?,?,?)";
	private static final String UPDATE = 
			"UPDATE room_type SET room_type_name=?, room_type_amount=?, room_type_content=?, room_sale_status=?, room_type_pic=?, room_type_price=? WHERE room_type_id = ?";
	private static final String GET_ONE_STMT =
			"SELECT room_type_id, room_type_name, room_type_amount, room_type_content, room_sale_status,room_type_pic, room_type_price FROM room_type WHERE room_type_id = ?";
	private static final String  GET_ALL_STMT =
		"SELECT * FROM room_type ORDER BY room_type_id";
	private static final String DELETE = 
			"DELETE FROM room_type WHERE room_type_id = ?";
	
	
	@Override
	public void insert(RoomTypeVO roomTypeVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);//載入驅動
			con = DriverManager.getConnection(url, userid, password);//建立連線
			System.out.print("連線成功");
			pstmt = con.prepareStatement(INSERT_STMT);//送出指令
			
			//將收到的roomTypeVO物件內容取出，並用對應的型別設定到sql指令的?內
			pstmt.setString(1, roomTypeVO.getRoomTypeName());
			pstmt.setInt(2, roomTypeVO.getRoomTypeAmount());
			pstmt.setString(3, roomTypeVO.getRoomTypeContent());
			pstmt.setInt(4, roomTypeVO.getRoomSaleStatus());
			pstmt.setBytes(5, roomTypeVO.getRoomTypePic());
			pstmt.setInt(6, roomTypeVO.getRoomTypePrice());
			
			pstmt.executeUpdate();
			
			//載入驅動的錯誤
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {//關閉資源
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
	public void update(RoomTypeVO roomTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			System.out.print("連線成功");
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, roomTypeVO.getRoomTypeName());
			pstmt.setInt(2, roomTypeVO.getRoomTypeAmount());
			pstmt.setString(3, roomTypeVO.getRoomTypeContent());
			pstmt.setInt(4, roomTypeVO.getRoomSaleStatus());
			pstmt.setBytes(5, roomTypeVO.getRoomTypePic());
			pstmt.setInt(6, roomTypeVO.getRoomTypePrice());
			pstmt.setInt(7, roomTypeVO.getRoomTypeId());
			
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
	public RoomTypeVO findByPrimaryKey(Integer roomTypeId) {
		RoomTypeVO roomTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			System.out.print("連線成功");
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, roomTypeId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomTypeVO = new RoomTypeVO();
				roomTypeVO.setRoomTypeId(rs.getInt("room_type_id"));
				roomTypeVO.setRoomTypeName(rs.getString("room_type_name"));
				roomTypeVO.setRoomTypeAmount(rs.getInt("room_type_amount"));
				roomTypeVO.setRoomTypeContent(rs.getString("room_type_content"));
				roomTypeVO.setRoomSaleStatus(rs.getInt("room_sale_status"));
				roomTypeVO.setRoomTypePic(rs.getBytes("room_type_pic"));
				roomTypeVO.setRoomTypePrice(rs.getInt("room_type_price"));	
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
		return roomTypeVO;
	}
	@Override
	public List<RoomTypeVO> getAll() {
		List<RoomTypeVO> roomTypes = new ArrayList<RoomTypeVO>();
		RoomTypeVO roomTypeVO = null;
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			System.out.print("連線成功");
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
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
		return roomTypes;
	}
	@Override
	public void delete(Integer roomTypeId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			System.out.print("連線成功");
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, roomTypeId);
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
	/*
	public static void main(String[] args) {
		RoomTypeJDBCDAO dao = new RoomTypeJDBCDAO();
		
		//新增
		RoomTypeVO roomTypeVO1 = new RoomTypeVO();
		roomTypeVO1.setRoomTypeName("豪華四人房");
		roomTypeVO1.setRoomTypeAmount(5);
		roomTypeVO1.setRoomTypeContent("豪華四人房介紹測試文字");
		roomTypeVO1.setRoomSaleStatus(true);
		roomTypeVO1.setRoomTypePrice(12000);
		dao.insert(roomTypeVO1);
		
		//修改
		RoomTypeVO roomTypeVO2 = new RoomTypeVO();
		roomTypeVO2.setRoomTypeId(5);
		roomTypeVO2.setRoomTypeName("豪華四人房修改測試");
		roomTypeVO2.setRoomTypeAmount(6);
		roomTypeVO2.setRoomTypeContent("介紹內文修改");
		roomTypeVO2.setRoomSaleStatus(false);
		roomTypeVO2.setRoomTypePrice(20000);
		dao.update(roomTypeVO2);
		
		//刪除
		dao.delete(5);
		
		//單筆查詢
		RoomTypeVO roomTypeVO3 = dao.findByPrimaryKey(6);
		System.out.print(roomTypeVO3.getRoomTypeId() +",");
		System.out.print(roomTypeVO3.getRoomTypeName() +",");
		System.out.print(roomTypeVO3.getRoomTypeAmount() +",");
		System.out.print(roomTypeVO3.getRoomTypeContent() + ",");
		System.out.print(roomTypeVO3.getRoomSaleStatus() +",");
		System.out.print(roomTypeVO3.getRoomTypePrice());
		System.out.println("----------------------------------");
		
		//全部查詢
		List<RoomTypeVO> list = dao.getAll();
		for(RoomTypeVO aRoomType : list) {
			System.out.print(aRoomType.getRoomTypeId() +",");
			System.out.print(aRoomType.getRoomTypeName() +",");
			System.out.print(aRoomType.getRoomTypeAmount() +",");
			System.out.print(aRoomType.getRoomTypeContent() + ",");
			System.out.print(aRoomType.getRoomSaleStatus() +",");
			System.out.print(aRoomType.getRoomTypePrice());
			System.out.println();
		}
	}
	*/
	
}
