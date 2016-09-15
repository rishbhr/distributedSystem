<!-- SelectContract dispatches to this jsp -->
<!-- This file displays the contract that was selected and a form that enables the creation of a new book record-->
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

<p>Hello <jsp:getProperty name="shared" property="empFirstName"/>!</p>  <!--Get the firstname of the employee that is logged in and display it-->

<!-- Display options to the user -->

<!-- Script is used to bring up an alert when the the Send back to salesman button is clicked -->
<script>
function email(){
alert("Contract sent back to Salesman");
}
</script>

<!-- Button will redirect user to ViewContracts.jsp -->
<a href="ViewContracts.jsp"> <button type = "button" style = "float:right;" onclick= "email()">Send back to Salesman </button></a>
<a href="ViewContracts.jsp"><button type="button" style="float:right;">Back to List of Contracts</button></a>
<a href="MainPage.jsp"><button type="button" style="float:left;">Main Page</button></a>
<br><h3 align = "center">Contract Information</h3>
<%
//Get the contract that was selected and set in the bean
String [][] selectedContract = shared.getSelectedContract();
if(selectedContract != null && selectedContract.length != 0){
%>
<!-- Table used to display the information-->
<table align = "center" border = "2"  bgcolor="#F0F8FF" >
<tr>
	 <!-- Table headers -->
     <th>Author First Name</th>
	 <th>Author Last Name</th>
	 <th>Salesman First Name</th>
     <th>Salesman Last Name</th>
	 <th>Initial Title</th>
	 <th>Book Doctor</th>
	 <th>Promotion Weeks</th>
	 <th>Contract Date</th>
	 <th>Contract Status</th>
</tr>
<%
int count =0;

//Create an array for the contract
for(String[] con : selectedContract){
%>
<tr>
<!-- Display information from array that correlates to the columns used in the query -->						
<td name="authorfirstname" align="center"><%=con[3]%></td>
<td name="authorlastname" align="center"><%=con[4]%></td>
<td name="empfirstname" align="center"><%=con[5]%></td>
<td name="emplastname" align="center"><%=con[6]%></td>
<td name="initialtitle" align="center"><%=con[7]%></td>
<td name="bookdoctor" align="center"><%=con[10]%></td>
<td name="promotionweeks" align="center"><%=con[11]%></td>
<td name="contractdate" align="center"><%=con[12]%></td>
<td name="contractstatus" align="center"><%=con[13]%></td>
</td>
</tr>
<%
count++;
%>

<!-- Form to create a new book and send info to CreateBook servlet -->
<form align = "center" method = "POST" name= "bookinfo" action = "CreateBook">
<table align="center">
<br> <h2 align ="center">Input Book Information</h2>
<tr>
<td>Title:</td><td><input type="text" id= "Title" name="Title" required="required" value="<%=con[7]%>"></td> <!-- Preset the text with information from database -->
</tr>
<tr>
<td>Start Date:</td><td><input type="date" id= "Date" name="Date" required="required">mm/dd/yyyy</td>
</tr>
<tr>
<td colspan=25>Book Format:
<select id = "BookFormat" name ="BookFormat" align ="center">
<option value="Email Attachment" id="Email Attachment">Email Attachment</option>
<option value="CD ROM" id="CD ROM">CD ROM</option>
<option value="Flash Drive" id="Flash Drive">Flash Drive</option>
<option value="Paper" id="Paper">Paper</option>
</select>
</td>
</tr>
<td align="center"><td><input type="submit" name="Submit" value="Submit"></td></td>
</table>
</form>
<%
}//end of for loop
%>
</table>
<%
} //end of if
else
{
%>
<p>There are no contracts available.</p>
<%
} //end of else
%>


</body> 

</html>