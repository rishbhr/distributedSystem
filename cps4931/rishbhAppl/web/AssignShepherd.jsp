<!-- AssignShepherd dispatches to this jsp -->
<!-- This file displays the list of Shepherds and their book count, and gives the user the option to select a shepherd to assign to a book-->
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

<p>Hello <jsp:getProperty name="shared" property="empFirstName"/>!</p> <!--Get the firstname of the employee that is logged in and display it-->

<!-- Display options to the user -->

<a href="BookList"><button type="button" style="float:right;">Back to List of Books</button></a>
<a href="MainPage.jsp"><button type="button" style="float:left;">Main Page</button></a>

<h2 align="center">Select a Shepherd</h2>
<%
//Get the list of shepherds that were set in the bean
String [][] shepherds = shared.getShepherds();
if(shepherds != null && shepherds.length != 0){
%>
<!-- Table used to display the information-->
<table align = "center" border = "2"  bgcolor="#F0F8FF" >
<tr>
	 <!-- Table headers -->
     <th>Employee ID</th>
     <th>Shepherd First Name</th>
     <th>Shepherd Last Name</th>
	 <th>Number of Books Assigned</th>
	 <th>Assign Book</th>
</tr>
<%
int count =0;

//Create an array for the shepherds
for(String[] shep : shepherds){

%>
<tr>
<!-- Display information from array that correlates to the columns used in the query -->
<td name="shepherdID" align="center"><%=shep[0]%></td>
<td name="shepherdfirstname" align="center"><%=shep[1]%></td>
<td name="shepherdlastname" align="center"><%=shep[2]%></td>
<td name="shepherdcount" align="center"><%=shep[3]%></td>
<td name = "ShepherdID" align="center"> 
					<form id="ShepherdID<%=count%>" method="POST" action="ShepherdConfirmation"> <!-- each shepherd has its own form -->
					       <input type="submit" name="Submit" value="Select"/> <!-- submit shepherd selected to Shepherd Confirmation servlet-->
					       <input type="hidden" name="ShepherdID" value="<%=count%>" /> <!-- send row index of the selected shepherd -->
						   </form>
</td>
</tr> 
<%
count++;
}//end of for loop
%>
</table>
<%
} //End of if
else
{
%>
<p>There are no new shepherds available.</p>
<%
} //End of else
%>

</body>


</html>