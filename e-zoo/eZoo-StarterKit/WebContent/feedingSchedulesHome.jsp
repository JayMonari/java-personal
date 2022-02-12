	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
<!-- 	Just some stuff you need -->
	<header>
	  <div class="container">

	<c:choose>
	<c:when test="${not empty message }">
	  <p class="alert ${messageClass}">${message }</p>
	<%
	  session.setAttribute("message", null);
	  session.setAttribute("messageClass", null);
	%>
	</c:when>
	</c:choose>
 
		<h1>eZoo <small>Feeding Schedules</small></h1>
		<hr class="paw-primary">
		
		<table class="table table-striped table-hover table-responsive ezoo-datatable">
			<thead>
				<tr>
					<th class="text-center">Options</th>
					<th class="text-center">Feeding Time</th>
					<th class="text-center">Recurrence</th>
					<th class="text-center">Food</th>
					<th class="text-center">Notes</th>
					<th class="text-center">Assigned Animals</th>
					<th class="text-center">Remove</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="feedingSchedule" items="${feedingSchedules}">
					<tr>
                        <td>
                            <a class="btn btn-sm btn-success"
                            href="updateFeedingSchedule?scheduleId=${feedingSchedule.scheduleId}"
                            >Update</a>
                            
							<div class="dropdown">
							  <button class="btn btn-sm btn-info dropdown-toggle" type="button" data-toggle="dropdown">
							    Assign
							  </button>
							  <div class="dropdown-menu">
								  <c:forEach var="animal" items="${animals}">
									  <form action="assignFeedingSchedule?scheduleID=${feedingSchedule.scheduleId}&animalID=${animal.animalID}"
									  method="post" class="dropdown-item">
									  	<button style="width:200px;padding:8px;border:none" type="submit"><c:out value="${animal.name}" /></button>
									  </form>
								  </c:forEach>
							  </div>
							</div>
                        </td>
						<td><c:out value="${feedingSchedule.feedingTime}" /></td>
						<td><c:out value="${feedingSchedule.recurrence}" /></td>
						<td><c:out value="${feedingSchedule.food}" /></td>
						<td style="max-width:20ch;word-wrap:break-word"><c:out value="${feedingSchedule.notes}" /></td>
						<td>
							<c:forEach var="animal" items="${animals}">
								<c:if test="${animal.feedingScheduleID == feedingSchedule.scheduleId}" >
									<c:out value="${animal.name}" />
									<br />
								</c:if>
							</c:forEach>
						</td>
						<td>
							<form action="deleteFeedingSchedule?scheduleId=${feedingSchedule.scheduleId}"
                            method="post">
								<button type="submit" class="btn btn-sm btn-danger">X</button>
							</form>
						</td>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>	

	  </div>
	</header>
	
	<!-- Footer -->
	<jsp:include page="footer.jsp" />
