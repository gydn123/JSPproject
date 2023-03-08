package mvc.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MVCBoardDAO;
import dto.MemberOrder;

@WebServlet("/controller/ShippingInfo.do")
public class ShippingInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ShippingInfo.do/doGet()");
		String cartId = request.getParameter("cartId");
		
//		request.setAttribute("cartId",cartId);
//		request.getRequestDispatcher("/WebMarket/shippingInfo.jsp").forward(request, response);
		
		response.sendRedirect("/WebMarket/shippingInfo.jsp?cartId="+cartId);
	}
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("ShippingInfo.do/doPost()");
    	
//    	/WebMarket/processShippingInfo.jsp 내용
    	request.setCharacterEncoding("UTF-8");
    	
    	MemberOrder memo = new MemberOrder();
    	MVCBoardDAO dao = new MVCBoardDAO();
    	
    	String addressee = request.getParameter("addressee");
    	String id = request.getParameter("id");
    	String orderId = addressee + id;
    	
//    	id = 유저 아이디,addressee = 배송 수신자이름,name = 배송자이름,
//    	memberAddr=배송주소,
//    	oderId = 주문번호,P_id=장바구니번호(물건번호)
    	memo.setId(request.getParameter("id"));
    	memo.setAddressee(request.getParameter("addressee"));
    	memo.setName(request.getParameter("name"));
    	memo.setMemberAddr(request.getParameter("memberAddr"));
    	memo.setOrderId(orderId);
    	memo.setP_id(request.getParameter("cartId"));
     	dao.insertWrite(memo);    	
     	
     	request.setAttribute("orderId", orderId);
    	request.getRequestDispatcher("/controller/OrderConfirmation.do").forward(request, response);
    	
	}

}
