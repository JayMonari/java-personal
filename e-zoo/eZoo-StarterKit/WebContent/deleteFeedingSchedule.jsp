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
	
		<h1>eZoo <small>Add Feeding Schedule</small></h1>
		<hr class="paw-primary">
		
		<form action="deleteFeedingSchedule?scheduleId=${feedingSchedule.scheduleId}"
        method="post" class="form-horizontal">
        <h1><c:out value="${feedingSchedule.feedingTime}"/></h1>
        <h1><c:out value="${feedingSchedule.food}" /></h1>
        <h1><c:out value="${feedingSchedule.notes}"/></h1>
		  <div class="form-group">
		    <label for="feedingTime" class="col-sm-4 control-label">Feeding Time</label>
		    <div class="col-sm-4">
		      <input type="text" readonly="readonly" class="form-control" 
               name="feedingTime" value="${feedingSchedule.feedingTime}" />
		    </div>
		  </div>

		  <div class="form-group">
		  	<label for="recurrence" class="col-sm-4 control-label">Recurrence</label>
			  <div class="col-sm-4">
			  	<input type="text" class="form-control" id="recurrence" name="recurrence" placeholder="${feedingSchedule.recurrence}" required="required"/>
			  </div>
		  </div>

		  <div class="form-group">
		    <label for="food" class="col-sm-4 control-label">food</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="food" name="food" placeholder="food" required="required"/>
		    </div>
		  </div>

		  <div class="form-group">
		    <label for="notes" class="col-sm-4 control-label">notes</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="notes" name="notes" placeholder="notes" required="required"/>
		    </div>
		  </div>

		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-1">
		      <button type="submit" class="btn btn-danger">Delete</button>
		    </div>
		  </div>
		</form>
	  </div>
	</header>

	<!-- Footer -->
	<jsp:include page="footer.jsp" />