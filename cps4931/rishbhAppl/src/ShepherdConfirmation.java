/******************************************************************************************

ShepherdConfirmation.java

The purpose of this servlet is to assign the shepherd to the book, change the status, and get the
information of the shepherd to display on the confirmation page.

   + This servlet is invoked by AssignShepherd.jsp
   + This servlet dispatches to ShepherdConfirmation.jsp
   
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

public class ShepherdConfirmation extends SBTS.Control{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    //Get the current HTTP session from Tomcat
    HttpSession session = request.getSession(true);
    //Gets the bean from session and retrieves shared data 
    SBTS.Shared bean = (SBTS.Shared)session.getAttribute("shared");
     String [][] shepherds = bean.getShepherds();
    //Get the ID of the shepherd and book that was selected
    int ShepherdID = Integer.parseInt(request.getParameter("ShepherdID"));
    String bookID = bean.getBookID();
    String setshepID = shepherds[ShepherdID][0];
    //Set the Shepherd ID
    bean.setShepherdID(setshepID); 
    //Call the methods to assign a book to a shepherd, and change the status of the book
    AssignShepherd(bean, setshepID, bookID);
    ChangeBookStatus(bean, bookID);
    getConfirmShepherd(bean, setshepID);
    gotoPage("/ShepherdConfirmation.jsp", request, response); //Dispatch to ShepherdConfirmation
    
    }
   
   //Method to assign a shepherd to the selected book
private void AssignShepherd(SBTS.Shared bean, String ShepherdID, String BookID) throws ServletException, IOException{
        SBTS.DBI dbi = null;
try{
    dbi = new SBTS.DBI();
        //Check if there is a database connection to Tomcat
        if(dbi.connect()){
        dbi.AssignShepherd(ShepherdID, BookID);
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

//Method to change the status of the book to 'Shepherd Assigned'
private void ChangeBookStatus(SBTS.Shared bean, String BookID) throws ServletException, IOException{
        SBTS.DBI dbi = null;
try{
    dbi = new SBTS.DBI();
        //Check if there is a database connection to Tomcat
        if(dbi.connect()){
        dbi.ChangeBookStatus(BookID);
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

//Method to get information of the shepherd that was just assigned
private void getConfirmShepherd(SBTS.Shared bean, String shepherdid) throws ServletException, IOException{
        String[][] confirmshepherd;
        SBTS.DBI dbi = null;
try{
    dbi = new SBTS.DBI();
        //Check if there is a database connection to Tomcat
        if(dbi.connect()){
            
        confirmshepherd= dbi.getConfirmShepherd(shepherdid);
        bean.setConfirmShepherd(confirmshepherd);
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
 