<!-- AssignShepherd dispatches to this jsp -->
<!-- This file displays the shepherd that was just assigned to a book->
<!DOCTYPE HTML>
<html>
<head>
<jsp:useBean id="shared" scope="session" class="SBTS.Shared" /> 
     <!-- Insert SBTS Logo-->
    <h1 align = "center"><img  align = "center" src= "images/booklogo.png" alt = "Book Logo" style= "width: 270px; height: 150px"></h1> 
</head>
<body bgcolor = "#00BFFF">

<p>Hello <jsp:getProperty name="shared" property="empFirstName"/>!</p><!--Get the firstname of the employee that is logged in and display it-->

<!-- Display options to the user -->

<a href="BookList"><button type="button" style="float:right;">Back to List of Books</button></a>
<a href="MainPage.jsp"><button type="button" style="float:left;">Main Page</button></a>

<p align = "center">Shepherd has been assigned!</p>

<%
//Get the shepherd that was just assigned to a book
String [][] confirmshepherd = shared.getConfirmShepherd();
if(confirmshepherd != null && confirmshepherd.length != 0){
%>
<!-- Table used to display the information-->
<table align = "center" border = "2"  bgcolor="#F0F8FF" >
<tr>
<!-- Table headers-->
     <th>Employee ID</th>
     <th>Shepherd First Name</th>
     <th>Shepherd Last Name</th>
</tr>
<%
int count =0;
//Create an array for the shepherd
for(String[] shep : confirmshepherd){

%>
<tr>
<!-- Display information from array that correlates to the columns used in the query -->
<td name="shepherdID" align="center"><%=shep[0]%></td>
<td name="shepherdfirstname" align="center"><%=shep[1]%></td>
<td name="shepherdlastname" align="center"><%=shep[2]%></td>
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
<p>There are no new shepherds available.</p>
<%
}//end of else
%>
</body>


</html>