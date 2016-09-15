<!-- Login dispatches to this jsp -->
<!-- This file displays the different links that are accessible to the manager, shepherd, and tachnician-->
<!DOCTYPE HTML>
<html>
<head>
<jsp:useBean id="shared" scope="session" class="SBTS.Shared" /> 
     <!-- Insert SBTS Logo-->
    <h1 align = "center"><img  align = "center" src= "images/booklogo.png" alt = "Book Logo" style= "width: 270px; height: 150px"></h1> 
</head>
<p>Hello <jsp:getProperty name="shared" property="empFirstName"/>!</p> <!--Get the firstname of the employee that is logged in and display it-->
<body bgcolor = "#00BFFF">
<%
//Get the Manager, Shepherd, and Technician values that were set in the bean from the Login
String Manager = shared.getManager();
String Shepherd = shared.getShepherd();
String Technician = shared.getSkill();
//Assign the positions to an array
String [] position = {Manager, Shepherd, Technician};
//If the Shepherd and Technician are null and the Manager = "Yes", load the manager links
if(position[1] == null && position[2] == null && position[0].equals("Yes")){
%>
<jsp:include page="Manager.html" flush = "true"/>
<%
 
}//End of if

//If the Manager and Technician are null and the Shepherd= "Yes", load the shepherd links
else if(position[0] == null && position[2] == null && position[1].equals("Yes")){
%>
<jsp:include page="Shepherd.html" flush = "true"/>
<%
} //End of else if

//Else, load the technician links
else {
%>
<jsp:include page="Technician.html" flush = "true"/>
<%
} //End of else 
%>
</body>


</html>