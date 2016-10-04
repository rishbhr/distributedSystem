<!-- SelectTech dispatches to this jsp -->
<!-- This file displays the list of Technicians and their task count, and gives the user the option to select a Tech to assign to a task-->
<!DOCTYPE HTML>
<html>
<head>
<jsp:useBean id="shared" scope="session" class="SBTS.Shared" /> 
     <!-- Insert SBTS Logo-->
    <h1 align = "center"><img  align = "center" src= "images/booklogo.png" alt = "Book Logo" style= "width: 270px; height: 150px"></h1> 
</head>
<body bgcolor = "#00BFFF">
                        <jsp:getProperty name="shared" property="message"/>  <!--retrieves the error message from the shared bean -->
                        <jsp:getProperty name="shared" property="error"/>  <!--retrieves the error data from the shared bean -->
                        <jsp:setProperty name="shared" property="message" value=""/><!-- empty error message from the shared bean -->
                        <jsp:setProperty name="shared" property="error" value=""/> <!-- empty error data from the shared bean -->

<p>Hello <jsp:getProperty name="shared" property="empFirstName"/>!</p> <!--  -->

<!-- Buttons to redirect users to either ViewTaskDetails or MainPage  -->

<a href="ViewTaskDetails.jsp"><button type="button" style="float:right;">Back to Assign Task</button></a>
<a href="MainPage.jsp"><button type="button" style="float:left;">Main Page</button></a>
<%
//
String [][] designers = shared.getDesigners();
String [][] editors = shared.getEditors();
String [][] admins = shared.getAdmins();
String status = shared.getChooseTaskStatus();

//
if(status.equals("Design a Cover") || status.equals("Design a Promotion")){
%>
<h2 align="center">Designers</h2>

<%
if(designers != null && designers.length != 0){
%>
<!-- Table used to display information -->
<table align = "center" border = "2"  bgcolor="#F0F8FF" >
<tr>
<!-- Table headers -->
     <th>Employee ID</th>
     <th>Designer First Name</th>
     <th>Designer Last Name</th>
	 <th>Number of Tasks Assigned to</th>
     <th>Select</th>
<tr>
<%
int count =0;
//
for(String[] design : designers){
%>
<tr>
<!-- Display information from array that correlates to the columns used in the query, this is for Designers -->
<td name="designerID" align="center"><%=design[0]%></td>
<td name="firstname" align="center"><%=design[2]%></td>
<td name="lastname" align="center"><%=design[1]%></td>
<td name="designcount" align="center"><%=design[3]%></td>
<td name = "TechID" align="center"> 
					<form id="TechID<%=count%>" method="POST" action="ConfirmTech"> <!-- Forms for each Tech (designer) -->
					       <input type="submit" name="Submit" value="Select"/> <!-- Submit selected Tech (designer) to ConfirmTech servlet -->
					       <input type="hidden" name="TechID" value="<%=count%>" /> <!-- send row index of the selected Tech (designer) -->
						   </form>
</td>
</tr>
<%
count++;
}//end of for loop
%>
</table> 
<%
}//End of inner if statement
else
{
%>
<p>There are no Designers</p>
<%
}//end of else
}//End of outer if statement

//If 
else if(status.equals("Galley 1") || status.equals("Galley 2") || status.equals("Galley 3")){
%>

<h2 align="center">Editors</h2>
<%
if(editors != null && editors.length != 0){
%>
<!-- Table used to display information -->
<table align = "center" border = "2"  bgcolor="#F0F8FF" >
<tr>
<!-- Table headers -->
     <th>Employee ID</th>
     <th>Editor First Name</th>
     <th>Editor Last Name</th>
	 <th>Number of Tasks Assigned to</th>
     <th>Select</th>
<tr>
<%
int count =0;
// 
for(String[] edit : editors){
%>
<tr>
<!-- Display information from array that correlates to the columns used in the query, this is for Editors -->
<td name="editorID" align="center"><%=edit[0]%></td>
<td name="firstname" align="center"><%=edit[2]%></td>
<td name="lastname" align="center"><%=edit[1]%></td>
<td name="editcount" align="center"><%=edit[3]%></td>
<td name = "TechID" align="center"> 
					<form id="TecID<%=count%>" method="POST" action="ConfirmTech"> <!-- Form for each Tech (editor) -->
					       <input type="submit" name="Submit" value="Select"/> <!-- Submit selected Tech (editor) to ConfirmTech servlet -->
					       <input type="hidden" name="TechID" value="<%=count%>" /> <!-- send row index of the selected Tech (editor) -->
						   </form>
</td>
</tr>
<%
count++;
}//end of for loop
%>
</table> 
<%
}//end of inner if statement
else
{
%>
<p>There are no Editors</p>
<%
} //end of else
}//end of outer else if statement

//If
else if(status.equals("Scanning") || status.equals("ISBN") || status.equals("Publish")){
%>


<h2 align="center">Admins</h2>
<%
if(admins != null && admins.length != 0){
%>
<!-- Table used to display information -->
<table align = "center" border = "2"  bgcolor="#F0F8FF" >
<tr>
<!-- Table headers -->
     <th>Employee ID</th>
     <th>Administrator First Name</th>
     <th>Administrator Last Name</th>
	 <th>Number of Tasks Assigned to</th>
     <th>Select</th>
<tr>
<%
int count =0;
// 
for(String[] admin : admins){
%>
<tr>
<!-- Display information from array that correlates to the columns used in the query, this is for Admins -->
<td name="adminID" align="center"><%=admin[0]%></td>
<td name="firstname" align="center"><%=admin[2]%></td>
<td name="lastname" align="center"><%=admin[1]%></td>
<td name="admincount" align="center"><%=admin[3]%></td>
<td name = "TechID" align="center"> 
					<form id="TechID<%=count%>" method="POST" action="ConfirmTech"> <!-- Form for each Tech (admin) -->
					       <input type="submit" name="Submit" value="Select"/> <!-- Submit selected Tech (admin) to ConfirmTech servlet -->
					       <input type="hidden" name="TechID" value="<%=count%>" /> <!-- send row index of the selected Tech (admin) -->
						   </form>
</td>
</tr>
<%
count++;
}//end of for loop
%>
</table> 
<%
} //end of inner if statement
else
{
%>
<p>There are no Admins</p>
<%
}//end of else
}//end of outer else if statement
%>

</body>


</html>
