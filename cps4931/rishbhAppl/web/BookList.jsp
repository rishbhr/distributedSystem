<!-- BookList dispatches to this jsp -->
<!-- This file displays the list of books that still need a shepherd -->
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
<a href="MainPage.jsp"><button type="button" style="float:left;">Main Page</button></a>

<!-- Display options to the user -->

<h2 align ="center"> Select a Book to Assign</h2>
<%
//Get the list of books that were set in the bean
String [][] books = shared.getBooks();
if(books != null && books.length != 0){
%>
<!-- Table used to display information-->
<table align = "center" border = "2"  bgcolor="#F0F8FF" >
<tr>
<!-- Table headers-->
     <th>Book ID</th>
     <th>Title</th>
	 <th>Start Date</th>
	 <th>Book Format</th>
	 <th>Select Book</th>
</tr>
<%
int count = 0;
//Create an array for the books
for(String[] book : books){
%>
<tr>
<!-- Display information from array that correlates to the columns used in the query -->
<td name="bookID" align="center"><%=book[0]%></td>
<td name="title" align="center"><%=book[1]%></td>
<td name="startdate" align="center"><%=book[2]%></td>
<td name="bookformat" align="center"><%=book[3]%></td>
<td name = "BookID" align="center"> 
					<form id="selectBook<%=count%>" method="POST" action="AssignShepherd"> <!-- each book has its own form -->
					       <input type="submit" name="Submit" value="Select"/> <!-- submit book selected to Assign Shepherd servlet-->
					       <input type="hidden" name="BookID" value="<%=count%>" /> <!-- send row index of the selected book -->
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
<p>There are no new books available.</p>
<%
} //End of else
%>

</body>
</html>