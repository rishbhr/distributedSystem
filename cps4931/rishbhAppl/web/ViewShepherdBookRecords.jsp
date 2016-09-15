<!-- ViewShepherdBookRecords servlet dispatches to this jsp -->
<!-- This file displays the list of books that belong to a shepherd-->
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

<!-- Display options to continue from this screen to the user -->

<a href="MainPage.jsp"><button type="button" style="float:left;">Main Page</button></a>
<br><h2 align ="center">Assign a Task to a Book</h2>
<%
//Get the list of books that were set in the bean
String [][] books = shared.getShepherdBookList();
// if the books list is not empty
if(   ){
%>
<!-- Table labels used to display the book information-->
<table align = "center" border = "2"  bgcolor="#F0F8FF" >
<tr>
<!-- Table headers-->
     <th>Book ID</th>
     <th>Title</th>
	 <th>Start Date</th>
	  <th>Book Format</th>
	 <th>View Task Details</th>
</tr>
<%
int count = 0;
//Create an array for the books
for(String[] book : books){
%>
<tr>
<!-- Display data fields from book array that correlates to the columns used in the query -->
<td name="BookID" align="center"><%=book[0]%></td>
<td
<td
<td
<td name = "BookID" align="center"> 
				<form id="selectBook<%=count%>" method="POST" action="ViewTaskDetails"> <!-- each book has its own form -->
				<input type="submit" name="Submit" value="Select"/> <!-- submit book selected to ViewTaskDetails servlet-->
				<input type="hidden" name="BookID" value="<%=count%>" /> <!-- send row index of the selected book -->
				</form>
</td>
</tr>
<%
count++; // next row
}//end of for loop
%>
</table>
<%
} //end of if
else // the books list matrix was empty
{
%>
<p>There are no new books available.</p>
<%
} //end of else
%>

</body>


</html>
