package SBTS;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.sql.*;
import SBTS.DBI;
import SBTS.Control;
import SBTS.Shared;

public class Logout extends SBTS.Control{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    //Get the current HTTP session from Tomcat
    HttpSession session = request.getSession(true);
    //Gets the bean from session and retrieves shared data 
    SBTS.Shared bean = (SBTS.Shared)session.getAttribute("shared");
    //Empty the email address
    bean.setemailAddress("");
    //Invalidate the session and unbinds any object attached to it
    session.invalidate();
    this.gotoPage("/Control", request, response); //Dispatch to Control.java
    }
}//End of Class