//
/*****************************************************************************
This servlet is called when the user logs in the system. The screen program that calls this program is Login.jsp
This program has a boolean variable that determines the following:
proceed = TRUE : Call MainPage.jsp
proceed = FALSE: this means login failed and goes back to the Login.jsp


javax.servlet :  Defines an object that receives requests from the client and sends them to any resource
                     (such as a servlet, HTML file, or JSP file) on the server.
                     Defines methods that all servlets must implement.
                     A servlet configuration object used by a servlet container used to pass information to a servlet during initialization.
                     Defines a set of methods that a servlet uses to communicate with its servlet container, for example,
                     to get the MIME type of a file, dispatch requests, or write to a log file
                     Defines an object to provide client request information to a servlet.
                     Def	ines an object to assist a servlet in sending a response to the client


javax.servlet.http: Provides an abstract class to be subclassed to create an HTTP servlet suitable for a Web site.
                          A subclass of HttpServlet must override at least one method, usually one of these:
                                                  - doGet, if the servlet supports HTTP GET requests
                                                  - doPost, for HTTP POST requests
                                                  - doPut, for HTTP PUT requests
                                                  - doDelete, for HTTP DELETE requests
                                                  - init and destroy, to manage resources that are held for the life of the servlet
                                                  - getServletInfo, which the servlet uses to provide information about itself

*****************************************************************************/

package SBTS;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.sql.*;
import SBTS.DBI;
import SBTS.Control;
import SBTS.Shared;

public class Login extends SBTS.Control{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    //Get the email and password that was inputted by the user
    String emailnew = request.getParameter("Email");
    String email = emailnew + "@kean.edu";
    String password = request.getParameter("Password");
    //Get the current HTTP session from Tomcat
    HttpSession session = request.getSession(true);
    //Gets the bean from session and retrieves shared data 
    SBTS.Shared bean = (SBTS.Shared)session.getAttribute("shared");
    
    //Check if the entered information is in the database
    boolean confirm = this.login(email, password, bean);
    //If it is a match, go to the main page
    if(confirm){
        bean.setMessage("");
        
        gotoPage("/MainPage.jsp", request, response);
    }
    //If there is no match, go back to login page
    else
    {   
     //   bean.setError("Invalid email or password");
        gotoPage("/Login.jsp", request, response);
    }
}

//Method to enter the information to check for match
private boolean login(String email, String password, SBTS.Shared bean){

    boolean check = false;
        //DBI object to access database
        SBTS.DBI dbi = new SBTS.DBI();
    try{

        //Check if there is a database connection to Tomcat
        if(dbi.connect()){
            //Check if the login information is correct
            if(dbi.checkEmpLogin(email,password)){
                check = true;
                bean.setemailAddress(email);
                //Set the information of the employee
                String[] empinfo = dbi.getEmpInfo(email);
                bean.setEmpId(Integer.parseInt(empinfo[0]));
                bean.setEmpLastName(empinfo[1]);
                bean.setEmpFirstName(empinfo[2]);
                bean.setTitle(empinfo[4]);
                bean.setDepartment(empinfo[7]);
                bean.setManager(empinfo[8]);
                bean.setCommissionRate(empinfo[9]);
                bean.setShepherd(empinfo[10]);
                bean.setSkill(empinfo[11]);
                
            }
        else
        {
            bean.setError("The email address or password does not match");
            check = false;
        }
    }
    else{
        bean.setError("Could not connect to database");
        check = false;
    }    
}

catch(Exception e){
    e.printStackTrace();
    bean.setError("Servlet Exception error " +e);
    check = false;
}
      finally
      {
         dbi.close(); //close connection to database
      }

return check; //Return result of login
}
}//End of Class