package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dto.MemberOrder;
import mvc.controller.JDBConnect;

public class MVCBoardDAO extends JDBConnect{

	public MVCBoardDAO() {
		super();
	}

	public int insertWrite(MemberOrder memo) {
		
		System.out.println("배송정보 업데이트 시작!");
		int Result = 0;

		String query = "insert into mem_order( "
				+ " id,addressee,name,memberAddr,p_id,orderId) "
				+ " values( "
				+ " ?,?,?,?,?,?)";

		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, memo.getId());
			psmt.setString(2, memo.getAddressee());
			psmt.setString(3, memo.getName());
			psmt.setString(4, memo.getMemberAddr());
			psmt.setString(5, memo.getP_id());
			psmt.setString(6, memo.getOrderId());
		
			Result = psmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("배송정보 업데이트 중 오류발생");
			e.printStackTrace();
		}
		
		System.out.println("배송정보 업데이트 성공!");
		return Result;

	}
	
	public MemberOrder selectView(String orderId) { 
		MemberOrder memo  = new MemberOrder();	
		String sql = "SELECT * FROM mem_order ";
		sql += " WHERE orderId = ? ";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, orderId);
			rs = psmt.executeQuery();
			if(rs.next()) {
				memo.setOrderId(rs.getString("orderId"));
				memo.setAddressee(rs.getString("addressee"));
				memo.setId(rs.getString("id"));
				memo.setMemberAddr(rs.getString("memberAddr"));
				memo.setMemberAddr(rs.getString("p_id"));
				memo.setName(rs.getString("name"));	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return memo;		
	}
}	
