/******************************************************************************************

SelectTech.java

The purpose of this servlet is to process the selected book from the list
and generate a list of technicians and their task counts.

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

public class SelectTech extends SBTS.Control{
    protected DBI dbi;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    //
    HttpSession session = request.getSession(true);
    // 
    SBTS.Shared bean = (SBTS.Shared)session.getAttribute("shared");
    //
    String taskstatus = request.getParameter("Status");
    String setbookID = bean.getBookID();
    
    //
    if(request.getParameter("AssignTask") != null){
    //
    getDesigners(bean);
    getEditors(bean);
    getAdmins(bean);
    //
    bean.setChooseTaskStatus(taskstatus);
    bean.setBookID(setbookID);
    gotoPage("/SelectTech.jsp", request, response); //
    }
    
    //
    else if(request.getParameter("ParallelTask") != null){
    getDesigners(bean);
    getEditors(bean);
    getAdmins(bean);
    bean.setChooseTaskStatus(taskstatus);
    bean.setBookID(setbookID);
    gotoPage("/SelectTechParallel.jsp", request, response); //
    }
    
    }
    
    //method to
    private void getDesigners(SBTS.Shared bean) throws ServletException, IOException{
        String[][] designers;
        SBTS.DBI dbi = null;
try{
    dbi = new SBTS.DBI();
        //
        if(dbi.connect()){
        //    
        designers= dbi.getDesigners();
        bean.setDesigners(designers);
        
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

//Method to 
private void getEditors(SBTS.Shared bean) throws ServletException, IOException{
        String[][] editors;
        SBTS.DBI dbi = null;
try{
    dbi = new SBTS.DBI();
        //
        if(dbi.connect()){
        //    
        editors= dbi.getEditors();
        bean.setEditors(editors);
        
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

//Method 
private void getAdmins(SBTS.Shared bean) throws ServletException, IOException{
        String[][] admins;
        SBTS.DBI dbi = null;
try{
    dbi = new SBTS.DBI();
        //
        if(dbi.connect()){
        //    
        admins= dbi.getAdmins();
        bean.setAdmins(admins);
        
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
 
