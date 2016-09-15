/******************************************************************************************

ViewTaskDetails.java

The purpose of this servlet is to get the tasks that is assigned to a specific book.

   + This servlet is invoked by ??
   + This servlet dispatches to ??
   
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
    
    HttpSession session = request.getSession(true);
    
    SBTS.Shared bean = (SBTS.Shared)session.getAttribute("shared");
    
    String [][] SelectedBook = bean.getShepherdBookList();
  
    int BookID = Integer.parseInt(request.getParameter("BookID"));
    String setbookID = SelectedBook[BookID][0];

    bean.setBookID(setbookID); 

    getTaskDetails(bean, setbookID);
    gotoPage("/ViewTaskDetails.jsp", request, response); 
    
    }
   
private void getTaskDetails(SBTS.Shared bean, String BookID) throws ServletException, IOException{
        String[][] taskdetails;
        SBTS.DBI dbi = null;
try{
    dbi = new SBTS.DBI();
       
        if(dbi.connect()){
        taskdetails = dbi.getTaskDetails(BookID);
        bean.setTaskDetails(taskdetails);
        } 
}

catch(Exception e){
    e.printStackTrace();
    bean.setError("Servlet Exception error" +e);
}

finally{
      dbi.close();
}
}
}//End of Class 
 
