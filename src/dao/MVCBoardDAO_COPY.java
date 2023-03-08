package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.DBConnPool;
import fileupload.MyfileDTO;
import model1.board.BoardDTO;

public class MVCBoardDAO_COPY extends DBConnPool{

	public MVCBoardDAO_COPY() {
		super();
	}

	public int selectCount(Map<String,Object> map) {
		int totalCount = 0;	
		String sql = "SELECT count(idx) as cnt FROM MVCBOARD ";
		if(map.get("searchWord") != null) {
			sql += " WHERE " + map.get("searchField") + " like ";
			sql += "'%" + map.get("searchWord") + "%'";
		}
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {				
				totalCount = rs.getInt(1);
				//totalCount = rs.getInt("cnt");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return totalCount;	

	}

	public List<MVCBoardDTO> selectListPage(Map<String, Object> map) { // Í≤åÏãúÎ¨? Î™©Î°ù
		List<MVCBoardDTO> board = new ArrayList<MVCBoardDTO>();	

		String sql = "SELECT  * FROM "
				+ " (SELECT tb.* , rownum rNum  FROM "
				+ " (SELECT * FROM MVCBOARD b ORDER BY idx DESC) tb ) "
				+ "	WHERE rNum BETWEEN  ? AND ? ";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, (int)map.get("start"));
			psmt.setInt(2, (int)map.get("end"));
			rs = psmt.executeQuery();
			while(rs.next()) {				
				MVCBoardDTO dto = new MVCBoardDTO();
				dto.setIdx(rs.getString("idx"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setOfile(rs.getString("ofile"));
				dto.setSfile(rs.getString("sfile"));
				dto.setDowncount(rs.getInt("downcount"));
				dto.setPass(rs.getString("pass"));
				dto.setVisitcount(rs.getInt("visitcount"));
				board.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return board;		
	}

	public int insertWrite(MVCBoardDTO dto) {

		int Result = 0;

		String query = "insert into mvcboard( "
				+ " idx,name,title,content,ofile,sfile,pass) "
				+ " values( "
				+ " seq_board_num.nextval,?,?,?,?,?,?)";

		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			psmt.setString(6, dto.getPass());

			Result = psmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Í≤åÏãúÎ¨? ?ûÖ?†• Ï§? ?òà?ô∏ Î∞úÏÉù");
			e.printStackTrace();
		}

		return Result;

	}

	public void updateVisitCount(int idx) {
		String sql = "UPDATE mvcboard SET VISITCOUNT = VISITCOUNT +1 WHERE idx = ?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, idx);			
			psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public MVCBoardDTO selectView(int idx) { // Í≤åÏãúÎ¨? 1Í∞?
		MVCBoardDTO dto  = new MVCBoardDTO();	
		String sql = "SELECT * FROM MVCBOARD ";
		sql += " WHERE idx = ? ";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, idx);
			rs = psmt.executeQuery();
			if(rs.next()) {
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setOfile(rs.getString("ofile"));
				dto.setSfile(rs.getString("sfile"));
				dto.setVisitcount(rs.getInt("visitcount"));	
				dto.setPostdate(rs.getDate("postdate"));
				dto.setPass(rs.getString("pass"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;		
	}

	// ?ã§?ö¥Î°úÎìú ?öü?àòÎ•? 1 Ï¶ùÍ??ãú?Çµ?ãà?ã§.
	public void downCountPlus(String idx) {
		String sql = "UPDATE mvcboard SET "
				+ " downcount=downcount+1 "
				+ " WHERE idx=? "; 
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, idx);
			psmt.executeUpdate();
		}
		catch (Exception e) {}
	}

	public boolean confirmPassword(String pass, String idx) {
		boolean isCorr = true;
		try {
			String sql = "SELECT COUNT(*) FROM mvcboard WHERE pass=? AND idx=?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, pass);
			psmt.setString(2, idx);
			rs = psmt.executeQuery();
			rs.next();
			if (rs.getInt(1) == 0) {
				isCorr = false;
			}
		}
		catch (Exception e) {
			isCorr = false;
			e.printStackTrace();
		}
		return isCorr;
	}

	public int deletePost(String idx) {
		int result = 0;
		try {
			String query = "DELETE FROM mvcboard WHERE idx=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			result = psmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("Í≤åÏãúÎ¨? ?Ç≠?†ú Ï§? ?òà?ô∏ Î∞úÏÉù");
			e.printStackTrace();
		}
		return result;
	}

	public int updatePost(MVCBoardDTO dto) {
		int totalCount = 0;	
		String sql = "update MVCboard set title=?, name=?, content=?, ofile=?, sfile=? "
				+" where idx=? and pass=? ";

		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			psmt.setString(6, dto.getIdx());
			psmt.setString(7, dto.getPass());
			totalCount = psmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("Í≤åÏãúÎ¨? ?àò?†ï Ï§? ?òà?ô∏ Î∞úÏÉù");
			e.printStackTrace();
		}

		return totalCount;		
	}

}	
