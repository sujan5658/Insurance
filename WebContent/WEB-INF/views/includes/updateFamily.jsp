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
	<form:form method="post" action="renewFamily" modelAttribute="family"
		onsubmit=" return setFamilyMembers()">
		<div class="row">
			<div class="col-sm-6">
				<table class="table table-hover table-striped">
					<tr>
						<th colspan="2"><h4 style="color: green">Renew Family</h4></th>
					</tr>
					<tr>
						<th>
							<div class="form-group">
								<label for="familyHead">Family Head</label>
								<form:errors path="familyHead" cssClass="error"></form:errors>
								<form:input path="familyHead" type="text"
									placeholder="Name of Family Head" class="form-control" />
							</div>
						</th>
						<th>
							<div class="form-group">
								<label for="uniqueCode">Family Code</label>
								<form:errors path="familyCode" cssClass="error"></form:errors>
								<form:input path="familyCode" type="text"
									placeholder="Family Code" class="form-control" readonly="true" />
							</div>
						</th>
					</tr>
					<tr>
						<th>
							<div class="form-group">
								<label for="contactNo">Contact Number</label>
								<form:errors path="contactNum" cssClass="error"></form:errors>
								<form:input path="contactNum" type="text"
									placeholder="Contact Number" class="form-control" />
							</div>
						</th>
						<th>
							<div class="form-group">
								<label for="memberNo">Number of Members</label>
								<form:errors path="numOfMembers" cssClass="error"></form:errors>
								<form:select path="numOfMembers" class="form-control">
									<c:forEach begin="1" end="30" varStatus="familyNo">
										<option value="${familyNo.index}">${familyNo.index}</option>
									</c:forEach>
								</form:select>
							</div>
						</th>
					</tr>
					<tr>
						<th colspan="2">
							<div class="form-group">
								<label for="registeredDate">Registered Date</label>
								<div class="row">
									<div class="col-sm-4">
										<select class="form-control" id="regYear"
											onchange="setRegisteredDate()">
											<c:forEach begin="2072" end="2099" varStatus="year">
												<option value="${year.index}">${year.index}</option>
											</c:forEach>
										</select>
									</div>
									<div class="col-sm-4">
										<select id="regMonth" class="form-control"
											onchange="setRegisteredDate()">
											<option value="01">Baishak</option>
											<option value="02">Jestha</option>
											<option value="03">Ashar</option>
											<option value="04">Shrawan</option>
											<option value="05">Bhadra</option>
											<option value="06">Ashoj</option>
											<option value="07">Kartik</option>
											<option value="08">Mangsir</option>
											<option value="09">Poush</option>
											<option value="10">Magh</option>
											<option value="11">Falgun</option>
											<option value="12">Chaitra</option>
										</select>
									</div>
									<div class="col-sm-4">
										<select class="form-control" id="regDay"
											onchange="setRegisteredDate()">
											<c:forEach begin="1" end="32" varStatus="day">
												<option value="${day.index}">${day.index}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
						</th>
					</tr>
					<tr>
						<th colspan="2">
							<div class="form-group">
								<label for="registeredDate">Expired Date</label>
								<div class="row">
									<div class="col-sm-4">
										<select class="form-control" id="expYear"
											onchange="setExpiryDate()">
											<c:forEach begin="2072" end="2099" varStatus="year">
												<option value="${year.index}">${year.index}</option>
											</c:forEach>
										</select>
									</div>
									<div class="col-sm-4">
										<select id="expMonth" class="form-control"
											onchange="setExpiryDate()">
											<option value="01">Baishak</option>
											<option value="02">Jestha</option>
											<option value="03">Ashar</option>
											<option value="04">Shrawan</option>
											<option value="05">Bhadra</option>
											<option value="06">Ashoj</option>
											<option value="07">Kartik</option>
											<option value="08">Mangsir</option>
											<option value="09">Poush</option>
											<option value="10">Magh</option>
											<option value="11">Falgun</option>
											<option value="12">Chaitra</option>
										</select>
									</div>
									<div class="col-sm-4">
										<select class="form-control" id="expDay"
											onchange="setExpiryDate()">
											<c:forEach begin="1" end="32" varStatus="day">
												<option value="${day.index}">${day.index}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
						</th>
					</tr>
					<tr>
						<th>
							<div class="form-group">
								<label for="amount">Amount</label>
								<form:errors path="amount" cssClass="error"></form:errors>
								<form:input path="amount" type="text" class="form-control"
									placeholder="Amount Payed" />
							</div>
						</th>
						<th>
							<div class="form-group">
								<label for="applied">Applied From</label>
								<form:errors path="appliedMonth" cssClass="error"></form:errors>
								<form:select path="appliedMonth" class="form-control">
									<option value="Jesth">Jesth</option>
									<option value="Bhadra">Bhadra</option>
									<option value="Mangsir">Mangsir</option>
									<option value="Falgun">Falgun</option>
								</form:select>
							</div>
						</th>
					</tr>
					<tr>
						<th colspan="2">
							<button type="submit" class="btn btn-primary">Renew
								Family</button>
						</th>
					</tr>
				</table>
			</div>
			<div class="col-sm-6">
				<table class="table table-hover table-striped" id="memberTable">
					<tr>
						<th>
							<h4 style="color: green">Family Members</h4>
						</th>
						<th colspan="2">
							<button type="button" class="btn btn-success" onclick="addRows()">+
								ADD ROW</button>
							<button type="button" class="btn btn-danger" onclick="delRows()">-
								DEL ROW</button>
						</th>
						
					</tr>
					<tr>
						<th>Member Name</th>
						<th>Member Code</th>
						<th>Del</th>
					</tr>
					<c:set var="tableRow" value="1"></c:set>
					<c:forEach items="${members}" var="member">
						<tr>
							<th><input type="text" name="memberName"
								class="form-control" placeholder="Member Name"
								value="${member.memberName}" /></th>
							<th><input type="text" name="memberCode"
								class="form-control" placeholder="Member Code"
								value="${member.memberCode}" />
								</th>
								<td><input type="checkbox" value="${member.memberCode}" name="memberStatuss"></td>
						</tr>
						<c:set var="tableRow" value="${tableRow+1}"></c:set>
					</c:forEach>
					<tr>
						<th id="col0"><input type="text" name="memberName"
							class="form-control" placeholder="Member Name" /></th>
						<th id="col1"><input type="text" name="memberCode"
							class="form-control" placeholder="Member Code" oninput="checkMemberCode(this.value)"/></th>
							<td><input type="checkbox" value="notDel" name="delete" disabled></td>
					</tr>
				</table>
				<span id="showError" value=""></span>
			</div>
		</div>
		<input type="hidden" name="familyMembers" id="familyMembers" value="" />
		<input type="hidden" name="memberStatus" id="viewMemberStatus" value="" />
		<form:errors path="regDate" cssClass="error" />
		<form:errors path="expiryDate" cssClass="error" />
		<form:hidden path="regDate" value="" id="regDate" />
		<form:hidden path="expiryDate" value="" id="expiryDate" />
	</form:form>
	<c:forEach items="${memberCodes}" var="codes">
		<input type="hidden" name="mCode" value="${codes.memberCode}"></span>
	</c:forEach>
</div>
<script>
	function addRows() {
		var table = document.getElementById('memberTable');
		var rowCount = table.rows.length;
		var row = table.insertRow(rowCount);
		for (var i = 0; i <= 1; i++) {
			var cell = 'cell' + i;
			cell = row.insertCell(i);
			var copycel = document.getElementById('col' + i).innerHTML;
			cell.innerHTML = copycel;
		}
	}
	function delRows() {
		var table = document.getElementById('memberTable');
		var rowCount = table.rows.length;
		if (rowCount > '${tableRow+2}') {
			var row = table.deleteRow(rowCount - 1);
			rowCount--;
		} else {
			swal("Alert", "Cannot remove row. ", "info");
		}
	}
	function setRegisteredDate() {
		var regYear = document.getElementById('regYear').value;
		var regMonth = document.getElementById('regMonth').value;
		var regDay = document.getElementById('regDay').value;
		document.getElementById('regDate').value = regYear + "-" + regMonth
				+ "-" + regDay;
	}
	function setExpiryDate() {
		var expYear = document.getElementById('expYear').value;
		var expMonth = document.getElementById('expMonth').value;
		var expDay = document.getElementById('expDay').value;
		document.getElementById('expiryDate').value = expYear + "-" + expMonth
				+ "-" + expDay;
	}
	function setFamilyMembers() {
		setRegisteredDate();
		setExpiryDate();
		setMemberStatus();
		var memberNameArray = document.getElementsByName('memberName');
		var memberNames = [];
		var memberCodeArray = document.getElementsByName('memberCode');
		var memberCodes = [];
		for (var i = 0, n = memberNameArray.length; i < n; i++) {
			memberNames.push(memberNameArray[i].value);
			memberCodes.push(memberCodeArray[i].value);
		}
		document.getElementById('familyMembers').value = memberNames
				.join("%sub%")
				+ "%main%" + memberCodes.join("%sub%");
		return confirm('Confirm Update ? ');
	}
	function setMemberStatus(){
		var memberStatusArray = document.getElementsByName('memberStatuss');
		var memberStatus = [];
		for (var i = 0, n = memberStatusArray.length; i < n; i++) {
			if(memberStatusArray[i].checked == true){
				memberStatus.push(memberStatusArray[i].value);
			}
		}
		document.getElementById('viewMemberStatus').value = memberStatus.join("%status%");
	}
	function checkMemberCode(code){
		var state = "no";
		var memberCode = document.getElementsByName('mCode');
		for (var i = 0, n = memberCode.length; i < n; i++) {
			if(memberCode[i].value == code){
				state = "yes";
				break;
			}
		} 
		switch(state){
		case "yes":
			document.getElementById('showError').style.color = "red";
			document.getElementById('showError').innerHTML = code+" Member Code already exists. It will replace the Member with that code.";
			break;
		case "no":
			document.getElementById('showError').style.color = "green";
			document.getElementById('showError').innerHTML = code+" Member Code is valid.";
			break;
		}
	}
</script>