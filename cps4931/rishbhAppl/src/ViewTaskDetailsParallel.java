/******************************************************************************************

ViewTaskDetailsParallel.java

The purpose of 

   + This servlet is invoked by
   + This servlet dispatches to 
   
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
    //Get     
    String [][] SelectedBook = bean.getShepherdBookList();
    String bookID = bean.getBookID();
    //   
    bean.setBookID(bookID); 
    //   
    getTaskDetails(bean, bookID);
    gotoPage("/ViewTaskDetails.jsp", request, response); //
    
    }
   
    //Method    
private void getTaskDetails(SBTS.Shared bean, String BookID) throws ServletException, IOException{
        String[][] taskdetails;
        SBTS.DBI dbi = null;
try{
    dbi = new SBTS.DBI();
        //Check if there is a database connection to Tomcat
        if(dbi.connect()){
	//
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
 
