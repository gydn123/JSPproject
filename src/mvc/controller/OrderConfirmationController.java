package mvc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MVCBoardDAO;
import dto.MemberOrder;

@WebServlet("/controller/OrderConfirmation.do")
public class OrderConfirmationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderConfirmationController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("OrderConfirmation.do 진입");
		MVCBoardDAO dao = new MVCBoardDAO();
		MemberOrder memo = new MemberOrder();
		
		memo = dao.selectView(request.getParameter("orderId"));
		
		request.setAttribute("memo", memo);
		
//		디저패쳐는 루트경로를 쓰면 안된다.루트경로를 제외하고 경로를 적어줘야 한다. 또한
//		포워드된 페이지에 상대경로가 있다면 그 상대경로 시작점이 포워드된 페이지가 아니라, 디스패쳐를 시작한 페이지가 되어서, 상대경로 설정할때 주의해야 한다.
		request.getRequestDispatcher("/orderConfirmation.jsp").forward(request, response);
	}

}
