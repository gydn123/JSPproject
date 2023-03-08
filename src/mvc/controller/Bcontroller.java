package mvc.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Product;
import mvc.controller.JDBConnect;

@WebServlet("/Bcontroller.do")
public class Bcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		ArrayList<Product> listOfProducts = new ArrayList<Product>();
		JDBConnect jdbc = new JDBConnect();
		Product dto = new Product();

		PreparedStatement psmt = null;
		ResultSet rs = null;
		String id = request.getParameter("id");
		
		String sql = "select*from product where p_id=?";
		System.out.println(id);
		try {

			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection(url, user, password);
			psmt = jdbc.con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setProductId(rs.getString("p_id"));
				dto.setPname(rs.getString("p_name"));
				dto.setUnitPrice(rs.getString("p_unitPrice"));
				dto.setCategory(rs.getString("p_category"));
				dto.setManufacturer(rs.getString("p_manufacturer"));
				dto.setFilename(rs.getString("p_filename"));
//				String ca =dto.getCategory();
//				System.out.println(ca);
				
			}
		} catch (Exception e) {
			System.out.println("�뜲�씠�꽣踰좎씠�뒪 �뿰寃곗씠 �떎�뙣�릺�뿀�뒿�땲�떎.<br>");
			System.out.println("Exception: " + e.getMessage());
		}
		
		request.setAttribute("dto", dto);
		System.out.println(dto);
		request.getRequestDispatcher("/product.jsp?id="+id).forward(request, response);
		//response.sendRedirect("/WebMarket/product.jsp?id="+id);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
