<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Recovery</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/admin/bootstrap.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/admin/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/admin/bootstrapp.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/admin/popper.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/admin/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/admin/jquery-3.3.1.slim.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/admin/ownStyle.css">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/images/user.png" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/admin/sweetalert.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/admin/sweetalert-min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/admin/sweetalert.js"></script>
<style type="text/css">
body {
	background-image:
		url("${pageContext.request.contextPath}/resources/img/background.png");
	padding: 50px;
}

.error {
	color: red;
	font-size: small;
	font-style: italic;
}

.success {
	color: green;
	font-style: italic;
	font-size: small;
}
</style>
</head>
<body>
	<c:choose>
		<c:when
			test="${message.status eq false and message.message ne 'defaultMessage'}">
			<script type="text/javascript">
				swal("Success.!!!", "${message.message}", "success");
			</script>
		</c:when>
	</c:choose>
	<center>
		<div class="card" style="width: 380px; opacity:0.7">
			<img class="card-img-bottom"
				src="${pageContext.request.contextPath}/resources/images/admin.png"
				alt="Card image" style="width: 100%">
			<div class="card-body">
				<h4 class="card-title">Enter Recovery Code</h4>
				<p class="card-text">
					<form action="checkRecoveryCode" method="post">
						<div class="row" style="padding: 8px">
							<div class="col-sm-6">
								<label>Recovery Code : </label>
							</div>
							<div class="col-sm-6">
								<input type="text" class="form-control"
									placeholder="Enter Recovery Code" name="code" />
							</div>
						</div>
						<button class="btn btn-primary" type="submit">Verify</button>
					</form>
			</div>
		</div>
	</center>
</body>
</html>