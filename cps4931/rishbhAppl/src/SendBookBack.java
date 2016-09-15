/******************************************************************************************

SendBookBack.java

The purpose of this servlet is to enable the shepherd to send a book back to the manager if they cannot work on it.

   + This servlet is invoked by ViewTaskDetails.jsp
   + This servlet dispatches to MainPage.jsp
   
******************************************************************************************/

package SBTS;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.sql.*;
import SBTS.DBI;
import SBTS.Control;
import SBTS.Shared;

public class SendBookBack extends SBTS.Control{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    //Get the current HTTP session from Tomcat
    HttpSession session = request.getSession(true);
    //Gets the bean from session and retrieves shared data 
    SBTS.Shared bean = (SBTS.Shared)session.getAttribute("shared");
    //Get the ID of the book that was selected from the bean
    String bookID = bean.getBookID();
    //Call the methods to change the book status and reset the shepherd ID
    EscalateBook(bean, bookID);
    SendBookBack(bean, bookID);
    gotoPage("/MainPage.jsp", request, response);
    
    }
   
   //Method to change the status of the book to 'Escalated'
private void EscalateBook(SBTS.Shared bean, String BookID) throws ServletException, IOException{
        SBTS.DBI dbi = null;
try{
    dbi = new SBTS.DBI();
        //Check if there is a database connection to Tomcat
        if(dbi.connect()){
        dbi.EscalateBook(BookID);
        } 
}

catch(Exception e){
    e.printStackTrace();
    bean.setError("Servlet Exception error" +e);
}

finally{
      dbi.close();//Close connection to database
}
}

//Method to reset the ShepherdID of the Book selected
private void SendBookBack(SBTS.Shared bean, String BookID) throws ServletException, IOException{
        SBTS.DBI dbi = null;
try{
    dbi = new SBTS.DBI();
        //Check if there is a database connection to Tomcat
        if(dbi.connect()){
        dbi.SendBookBack(BookID);
        } 
}

catch(Exception e){
    e.printStackTrace();
    bean.setError("Servlet Exception error" +e);
}

finally{
      dbi.close();//Close connection to database
}
}
}//End of Class 
 