<div class="row">
	<div class="col-sm-6">
		<table class="table table-hover table-striped table-bordered">
			<tr>
				<th>Family Head</th>
				<td>${family.familyHead}</td>
			</tr>
			<tr>
				<th>Family Code</th>
				<td>${family.familyCode}</td>
			</tr>
			<tr>
				<th>Contact No</th>
				<td>${family.contactNum}</td>
			</tr>
			<tr>
				<th>Registered Date</th>
				<td>${family.regDate}</td>
			</tr>
			<tr>
				<th>Expired Date</th>
				<td>${family.expiryDate}</td>
			</tr>
			<tr>
				<th>Applied From</th>
				<td>${family.appliedMonth}</td>
			</tr>
			<tr>
				<th>No of Members</th>
				<td>${family.numOfMembers}</td>
			</tr>
			<tr>
				<th>Amount</th>
				<td>Rs : ${family.amount}</td>
			</tr>
		</table>
	</div>
	<div class="col-sm-6">
		<table class="table table-hover table-striped table-bordered">
			<tr>
				<th>Member Name</th>
				<th>Member Code</th>
			</tr>
			<c:forEach items="${members}" var="member">
				<tr>
					<td>${member.memberName}</td>
					<td>${member.memberCode}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>