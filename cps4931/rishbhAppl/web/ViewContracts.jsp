<!-- ViewContracts dispatches to this jsp -->
<!-- This file displays the list of contracts that do not have a book record->
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

<a href="MainPage.jsp"><button type="button" style="float:left;">Main Page</button></a>

<h3 align="center">Select a Contract to Assign a Book Record to</h3>
<%
//Get the list of contracts that were set in the bean
String [][] newContracts = shared.getNewContracts();
if(newContracts != null && newContracts.length != 0){
%>
<!-- Table used to display the information-->
<table align = "center" border = "2"  bgcolor="#F0F8FF" >
<tr>
<!-- Table headers-->
	 <th>Author First Name</th>
     <th>Author Last Name</th>
     <th>Salesman First Name</th>
     <th>Salesman Last Name</th>
	 <th>Initial Title</th>
	 <th>Contract Status</th>
	 <th>Select Contract</th>
</tr>
<%
int count =0;
//Create an array for the contracts
for(String[] con : newContracts){
%>
<tr>
<!-- Display information from array that correlates to the columns used in the query -->
<td name="authorfirstname" align="center"><%=con[2]%></td>
<td name="authorlastname" align="center"><%=con[3]%></td>
<td name="salesmanfirstname" align="center"><%=con[5]%></td>
<td name="salesmanlastname" align="center"><%=con[6]%></td>
<td name="initialtitle" align="center"><%=con[7]%></td>
<td name="contractstatus" align="center"><%=con[8]%></td>
<td name = "ContractID" align="center"> 
					<form id="selectContract<%=count%>" method="POST" action="SelectContract"> <!-- each contract has its own form -->
					       <input type="submit" name="Submit" value="Select"/> <!-- submit contract selected to Select Contract servlet-->
					       <input type="hidden" name="ContractID" value="<%=count%>" /> <!-- send row index of the selected contract -->
						   </form>
</td>
</tr>
<%
count++;
}//end of for loop
%>
</table>
<%
} //end of if
else
{
%>
<p>There are no new contracts available.</p>
<%
} //end of else
%>



</body>


</html>