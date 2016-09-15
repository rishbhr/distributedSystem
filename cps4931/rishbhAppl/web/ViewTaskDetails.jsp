<!-- ?? dispatches to this jsp -->
<!-- This file ??  -->
<!DOCTYPE HTML>
<html>
<head>
<jsp:useBean id="shared" scope="session" class="SBTS.Shared" /> 
     <!-- -->
    <h1 align = "center"><img  align = "center" src= "images/booklogo.png" alt = "Book Logo" style= "width: 270px; height: 150px"></h1> 
</head>
<body bgcolor = "#00BFFF">
                        <jsp:getProperty name="shared" property="message"/>  <!--retrieves the error message from the shared bean -->
                        <jsp:getProperty name="shared" property="error"/>  <!--retrieves the error data from the shared bean -->
                        <jsp:setProperty name="shared" property="message" value=""/><!-- empty error message from the shared bean -->
                        <jsp:setProperty name="shared" property="error" value=""/> <!-- empty error data from the shared bean -->

<p>Hello <jsp:getProperty name="shared" property="empFirstName"/>!</p> <!-- -->

<!--  -->

<a href="MainPage.jsp"><button type="button" style="float:left;">Main Page</button></a>
<br><h2 align ="center"> Tasks Assigned</h2>
<%
// 
String [][] taskdetails = shared.getTaskDetails();
if(taskdetails != null && taskdetails.length != 0){
%>
<!--  -->
<table align = "center" border = "2"  bgcolor="#F0F8FF" >
<tr>
<!-- Table headers-->
     <th>Task Type</th>
	 <th>Task Status</th>
     <th>Start Date</th>
     <th>End Date</th>
	 <th>Task Notes</th>
	 <th>Technician First Name</th>
	 <th>Technician Last Name</th>
</tr>
<%
int count = 0;
//
for(String[] details : taskdetails){
%>
<tr>
<!--   -->
<td name="tasktype" align="center"><%=details[4]%></td>
<td name="taskstatus" align="center"><%=details[6]%></td>
<td name="startdate" align="center"><%=details[1]%></td>
<td name="enddate" align="center"><%=details[2]%></td>
<td name="tasknotes" align="center"><%=details[5]%></td>
<td name="Techfirstname" align="center"><%=details[8]%></td>
<td name="Techlastname" align="center"><%=details[9]%></td>
</tr>
<%
count++;
}//end of for loop
%>
</table>
<%
}//end of if
else
{
%>
<p>There are no tasks available.</p>
<%
}//end of else
%>

<h2 align = "center">Assign a task for the book</h2>

<!--   -->
<form id = "AssignTask" name="AssignTask" method ="Post" action="SelectTech" align = "center">
<select id = "Status" name ="Status">
<option value="Scanning" id="Scanning">Scanning</option>
<option value="Galley 1" id="Galley1">Galley 1</option>
<option value="Galley 2" id="Galley2">Galley 2</option>
<option value="Galley 3" id="Galley3">Galley 3</option>
<option value="ISBN" id="ISBN">Create ISBN</option>
<option value="Design a Cover" id="DesignCover">Design a Cover</option>
<option value="Design a Promotion" id="DesignPromotion">Design a Promotion</option>
<option value="Publish" id="Publish">Publish</option>
</select>
<input type="submit" name="AssignTask" value="Assign 1 Task"> <!--  -->
<input type="submit" name="ParallelTask" value="Assign 1 Task and Assign Another"> <!--  -->
</form>

<!--  -->
<script>
function sendback(){
alert("Book sent back to Production Manager");
}
</script>

<!--   -->
<form align="center" method ="POST" name = "SendBookBack" action ="SendBookBack" onsubmit= "sendback();">
<input type = "submit" name="sendback" value ="Send Book back to Production Manager">
</form>
</body>


</html>
