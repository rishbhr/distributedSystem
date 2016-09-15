<!-- This file displays the login form-->
<!DOCTYPE HTML>
<html>
<head>
<jsp:useBean id="shared" scope="session" class="SBTS.Shared" /> 
     <!-- Insert SBTS Logo-->
    <h1 align = "center"><img  align = "center" src= "images/booklogo.png" alt = "Book Logo" style= "width: 270px; height: 150px"></h1> 

<!-- Javascript form to check for invalid characters-->
<script> 
    function ValidateForm(form) {
    
    //Get the values from the user input
    var Email = document.login.email.value;
    var Password = document.login.password.value;
    
    //Checks that alphanumerics are entered for email and password 
    var enumeric = Email.search(/\w+/);
    var pnumeric = Password.search(/\w/);
    if (enumeric != 0) {
        alert('You have entered an invalid character for email');
        document.login.email.focus();
        return false;
    }
    
    if (pnumeric != 0) {
        alert('You have entered an invalid character for password');
        document.login.password.focus();
        return false;
    }
    return true;
    }
</script>
</head>
                            <jsp:getProperty name="shared" property="message"/>  <!--retrieves the error message from the shared bean -->
                            <jsp:getProperty name="shared" property="error"/>  <!--retrieves the error data from the shared bean -->
                            <jsp:setProperty name="shared" property="message" value=""/><!-- empty error message from the shared bean -->
                            <jsp:setProperty name="shared" property="error" value=""/> <!-- empty error data from the shared bean -->

 <!-- Change Background color-->
<body bgcolor = "#00BFFF">

<h3 align = "center">Login</h3>

<!-- Form to retrieve user input and send it to the Login servlet -->
<form  Method="POST" name = "login" onSubmit= "return ValidateForm(this);" action= "Login">
         <table align="center">
         <tr>
         <td>Email:</td>
         <td><input type="text" id= "Email" name="Email" required="required">@kean.edu</td><!-- Send email as a parameter-->
         </tr>
         <tr>
        <td>Password:</td>
        <td><input type="password" id= "Password" name="Password" required="required"></td>  <!-- Send password as a parameter-->
        </tr>
        <td align="center"><td><input type="submit" value="Submit" name= "Submit"></td> </td>
                 <!-- Insert Kean Logo-->
        <tr>
        <td align="center" colspan="100"><img  align = "center" src= "images/logo.png" alt = "Kean Logo" style= "width: 65px; height: 65px"></td>
        </tr>
        </table> 
        
</form>

</body>
 
<p>
    Kean University <br>
    Department of Computer Science<br>
    CPS 4931: Distributed Systems <br>
    Prof. Stewart-Gardiner
</p>
</html>