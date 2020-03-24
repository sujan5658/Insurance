<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Login</title>
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
		url("${pageContext.request.contextPath}/resources/images/background.png");
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
		<c:when test="${message.status eq true}">
			<script type="text/javascript">
				swal("Error Occured", "${message.message}", "error");
			</script>
		</c:when>
		<c:when
			test="${message.status eq false and message.message ne 'defaultMessage'}">
			<script type="text/javascript">
				swal("Succeeed", "${message.message}", "success");
			</script>
		</c:when>
	</c:choose>
	<center>
		<div class="card" style="width: 380px; opacity: 0.7">
			<img class="card-img-bottom"
				src="${pageContext.request.contextPath}/resources/images/admin.png"
				alt="Card image" style="width: 100%">
			<div class="card-body">
				<h4 class="card-title">Administrator Login</h4>
				<p class="card-text">
					<form:form action="login" method="post" modelAttribute="admin">
						<div class="form-group">
							<label style="float:left">Email : </label>
							<form:errors path="email" cssClass="error" />
							<form:input path="email" cssClass="form-control"
								placeholder="Your Email" />
						</div>
						<div class="form-group">
							<label style="float:left">Password : </label>
							<form:errors path="password" cssClass="error" />
							<form:input path="password" type="password" class="form-control"
								placeholder="*********" />
						</div>
						<a href="#myModal" data-toggle="modal" style="float: left"><small>Forget
								Password ?</small></a>
						<button class="btn btn-primary" type="submit">Login</button>
					</form:form>
			</div>
		</div>
	</center>
	<!-- Button to Open the Modal -->


	<!-- The Modal -->
	<div class="modal fade" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Recover Account Using Email</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<form method="post" action="sendVerification">
						<div class="form-group">
							<label>Email : </label> <input type="email" name="email"
								class="form-control" placeholder="Enter your Registered Email" />
						</div>
						<button class="btn btn-primary" type="submit">Use Email</button>
					</form>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>

	</div>

</body>
</html>