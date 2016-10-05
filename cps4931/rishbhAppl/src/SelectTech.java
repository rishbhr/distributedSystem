/******************************************************************************************

SelectTech.java

The purpose of this servlet is to process the selected book from the list
and generate a list of technicians and their task counts.

   + This servlet is invoked by SelectTech.jsp
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

public class SelectTech extends SBTS.Control{
    protected DBI dbi;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    // start the http session
    HttpSession session = request.getSession(true);
    // get the shared bean
    SBTS.Shared bean = (SBTS.Shared)session.getAttribute("shared");
    // get the status of task
    String taskstatus = request.getParameter("Status");
    String setbookID = bean.getBookID();
    
    // if assign task is clicked
    if(request.getParameter("AssignTask") != null){
    //
    getDesigners(bean);
    getEditors(bean);
    getAdmins(bean);
    // set the information in the bean
    bean.setChooseTaskStatus(taskstatus);
    bean.setBookID(setbookID);
    gotoPage("/SelectTech.jsp", request, response); //
    }
    
    // check if paralleltask
    else if(request.getParameter("ParallelTask") != null){
    getDesigners(bean);
    getEditors(bean);
    getAdmins(bean);
    bean.setChooseTaskStatus(taskstatus);
    bean.setBookID(setbookID);
    gotoPage("/SelectTechParallel.jsp", request, response); //
    }
    
    }
    
    //method to get the designers from the bean
    private void getDesigners(SBTS.Shared bean) throws ServletException, IOException{
        String[][] designers;
        SBTS.DBI dbi = null;
try{
    dbi = new SBTS.DBI();
        // start the database connection
        if(dbi.connect()){
        // get the information from the database
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

//Method to get editors from the bean
private void getEditors(SBTS.Shared bean) throws ServletException, IOException{
        String[][] editors;
        SBTS.DBI dbi = null;
try{
    dbi = new SBTS.DBI();
        // db connection
        if(dbi.connect()){
        // get the information from the database
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

//Method get the admin from the bean
private void getAdmins(SBTS.Shared bean) throws ServletException, IOException{
        String[][] admins;
        SBTS.DBI dbi = null;
try{
    dbi = new SBTS.DBI();
        // db connection
        if(dbi.connect()){
        // get the information from the database
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
 
