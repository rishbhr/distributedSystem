/******************************************************************************************

ViewTaskDetailsParallel.java

The purpose of 

   + This servlet is invoked by ViewTaskDetails.java
   + This servlet dispatches to ViewTaskDetails.jsp
   
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

public class ViewTaskDetailsParallel extends SBTS.Control{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    //Get the current HTTP session from Tomcat
    HttpSession session = request.getSession(true);
    //Gets the bean from session and retrieves shared data 
    SBTS.Shared bean = (SBTS.Shared)session.getAttribute("shared");
    //Get the details of the selected book
    String [][] SelectedBook = bean.getShepherdBookList();
    String bookID = bean.getBookID();
    // the book id
    bean.setBookID(bookID); 
    // the task details of bookid and bean
    getTaskDetails(bean, bookID);
    gotoPage("/ViewTaskDetails.jsp", request, response); //send the retrieved information to viewtaskDetails.jsp
    
    }
   
    //Method to get the deatails of the task assigned
private void getTaskDetails(SBTS.Shared bean, String BookID) throws ServletException, IOException{
        String[][] taskdetails;
        SBTS.DBI dbi = null;
try{
    dbi = new SBTS.DBI();
        //Check if there is a database connection to Tomcat
        if(dbi.connect()){
	// get the task details from database
        taskdetails = dbi.getTaskDetails(BookID);
        bean.setTaskDetails(taskdetails);
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
 
