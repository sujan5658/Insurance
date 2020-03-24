<c:choose>
	<c:when test="${updateMessage.status eq true}">
		<script>
			swal("Error Occured", "${updateMessage.message}", "error");
		</script>
	</c:when>
	<c:when
		test="${updateMessage.status eq false and updateMessage.message ne 'defaultMessage'}">
		<script>
			swal("Update Succeed", "${updateMessage.message}", "success");
		</script>
	</c:when>
</c:choose>
<div class="container">
	<form method="post" action="searchFamily">
		<table class="table table-hover table-striped table-bordered">
			<tr>
				<th>Family Code : <select name="familyCode">
						<option value="-">none</option>
						<c:forEach items="${familiesList}" var="fam">
							<option value="${fam.familyCode}">${fam.familyCode}</option>
						</c:forEach>
				</select>
				</th>
				<th>Family Head : <select name="familyHead">
						<option value="-">none</option>
						<c:forEach items="${familiesList}" var="fam">
							<option value="${fam.familyHead}">${fam.familyHead}</option>
						</c:forEach>
				</select>
				</th>
				<th>Applied On : <select name="appliedMonth">
						<option value="-">none</option>
						<c:forEach items="${familiesList}" var="fam">
							<option value="${fam.appliedMonth}">${fam.appliedMonth}</option>
						</c:forEach>
				</select>
				<th>Reg Date : <select name="regDate">
						<option value="-">none</option>
						<c:forEach items="${familiesList}" var="fam">
							<option value="${fam.regDate}">${fam.regDate}</option>
						</c:forEach>
				</select>
				</th>
				<th>
					<button class="btn btn-primary" type="submit">Search</button>
				</th>
			</tr>
		</table>
	</form>
	<table class="table table-striped table-hover">
		<tr>
			<th style="background-color: black; color: white">Family Code</th>
			<th style="background-color: black; color: white">Family Head</th>
			<th style="background-color: black; color: white">Registered
				Date</th>
			<th style="background-color: black; color: white">Applied From</th>
			<th style="background-color: black; color: white">Management</th>
		</tr>
		<c:forEach items="${families}" var="family">
			<tr>
				<th>${family.familyCode}</th>
				<th>${family.familyHead}</th>
				<th>${family.regDate}</th>
				<th>${family.appliedMonth}</th>
				<th><a href="singleFamily?familyCode=${family.familyCode}"><button
							type="button" class="btn btn-info">View</button></a> <a
					href="updateFamily?familyCode=${family.familyCode}"><button
							type="button" class="btn btn-success">Update</button></a></th>
			</tr>
		</c:forEach>
	</table>
</div>