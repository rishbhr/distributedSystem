/******************************************************************************************

ViewTaskDetails.java

The purpose of this servlet is to get the tasks that is assigned to a specific book.

   + This servlet is invoked by ViewShepherdBookRecords.jsp
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

public class ViewTaskDetails extends SBTS.Control{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    // http session
    HttpSession session = request.getSession(true);
    // get the shared bean data
    SBTS.Shared bean = (SBTS.Shared)session.getAttribute("shared");
    // get the info of selected book
    String [][] SelectedBook = bean.getShepherdBookList();
    // the id of the book
    int BookID = Integer.parseInt(request.getParameter("BookID"));
    String setbookID = SelectedBook[BookID][0];
    // set the book id
    bean.setBookID(setbookID); 
    // details of the task
    getTaskDetails(bean, setbookID);
    gotoPage("/ViewTaskDetails.jsp", request, response); 
    
    }
    // details of the task with bookid from the shared bean
private void getTaskDetails(SBTS.Shared bean, String BookID) throws ServletException, IOException{
        String[][] taskdetails;
        SBTS.DBI dbi = null;
try{
    dbi = new SBTS.DBI();
       //db connection
        if(dbi.connect()){
        taskdetails = dbi.getTaskDetails(BookID);
        bean.setTaskDetails(taskdetails);
        } 
}
// exception
catch(Exception e){
    e.printStackTrace();
    bean.setError("Servlet Exception error" +e);
}
// close dbc
finally{
      dbi.close();
}
}
}//End of Class 
 
