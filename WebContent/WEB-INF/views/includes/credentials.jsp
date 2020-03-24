<c:choose>
		<c:when test="${message.status eq true}">
			<script type="text/javascript">
				swal("Error Occured", "${message.message}", "error");
			</script>
		</c:when>
		<c:when
			test="${message.status eq false and message.message ne 'defaultMessage'}">
			<script type="text/javascript">
				swal("Success", "${message.message}", "success");
			</script>
		</c:when>
	</c:choose>
<div class="container">
	<form:form method="post" modelAttribute="admin" action="updateAdmin" onsubmit="return confirm('Confirm Change Credential ?')">
	<table class="table table-hover table-striped">
	<tr>
		<th colspan="2">
			<h5 style="color:green">You can enter old Email as a new email</h5>
		</th>
	</tr>
		<tr>
			<th>Old Password :</th>
			<td>
				<input  type="password" class="form-control" name="oldPass"/>
			</td>
		</tr>
		<tr>
			<th>New Email :<form:errors path="email" cssClass="error"></form:errors> </th>
			<td>
				<form:input path="email" type="email" class="form-control" placeholder="New Email" />
			</td>
		</tr>
		<tr>
			<th>New Password : <form:errors path="password" cssClass="error"></form:errors></th>
			<td>
				<form:input path="password" type="password" class="form-control" id="newPass" />
			</td>
		</tr>
		<tr>
			<th>Retype New Password :</th>
			<td>
				<input type="password" class="form-control" id="rePass" oninput="checkPassword()"/>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="submit" class="btn btn-primary">Change Credentials</button>
				&emsp;
				<small id="viewError"></small>
			</td>
		</tr>
	</table>
	</form:form>
</div>
<script>
	function checkPassword(){
		var pass1 = document.getElementById('newPass').value;
		var pass2 = document.getElementById('rePass').value;
		if(pass1!=pass2){
			document.getElementById('viewError').innerHTML = "Retyped Password Not Matched.!!!!";
			document.getElementById('viewError').style.color="red";
		}
		else{
			document.getElementById('viewError').innerHTML = "Retyped Password Matched.!!!!";
			document.getElementById('viewError').style.color="green";
		}
	}
</script>