<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="dto.Product"%>
<%@ page import="dao.ProductRepository"%>
<%@ page errorPage="exceptionNoProductId.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%//문덕용 님이 보낸 파일 %>
<html>
<head>
<link rel="stylesheet" href="./resources/css/bootstrap.min.css" />
<title>상품 상세 정보</title>
<script type="text/javascript">
	function addToCart() {
		if (confirm("상품을 장바구니에 추가하시겠습니까?")) {
			document.addForm.submit();
		} else {		
			document.addForm.reset();
		}
	}
</script>
<style type="text/css">
	#h3h3h3{
		clear:both;
	}
</style>
</head>
<body>
	<jsp:include page="menu.jsp" />
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">상품 정보</h1>
		</div>
	</div>
	<%
		Product dto = (Product)request.getAttribute("dto");
	
	%>
	<div class="container">
		<div class="row">
			<div class="col-md-5">
				<img src="c:/upload/${dto.filename}>" style="width: 100%" />
			</div>
			<div class="col-md-6">
		<h3>${dto.pname}</h3>
			<p><b>상품 코드 : </b><span class="badge badge-danger"> ${dto.productId}</span>
			<p><b>제조사</b> : ${dto.manufacturer}
			<p><b>분류</b> : ${dto.category}
			<h4>${dto.unitPrice}</h4>
			<p><form name="addForm" action="./addCart.jsp?id=${dto.productId}" method="post">
				<a href="#" class="btn btn-info" onclick="addToCart()"> 상품 주문 &raquo;</a>
				<a href="./cart.jsp" class="btn btn-warning"> 장바구니 &raquo;</a> 
				<a href="./products.jsp" class="btn btn-secondary"> 상품 목록 &raquo;</a>
			</form>
			</div>
		</div>
		<hr>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>
