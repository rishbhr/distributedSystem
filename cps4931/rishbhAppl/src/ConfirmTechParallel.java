/******************************************************************************************

ConfirmTechParallel.java

The purpose of

   + This servlet is invoked by ConfirmTech.java
   + This servlet dispatches to ConfirmTech.jsp
   
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

public class ConfirmTechParallel extends SBTS.Control{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    //Get the current HTTP session from Tomcat
    HttpSession session = request.getSession(true);
    //Gets the bean from session and retrieves shared data 
    SBTS.Shared bean = (SBTS.Shared)session.getAttribute("shared");
        //what is in the bean
     String [][] designers = bean.getDesigners();
     String [][] editors = bean.getEditors();
     String [][] admins = bean.getAdmins();
    //what is the tech id that task is being assigned to
    int tech = Integer.parseInt(request.getParameter("TechID2"));
    String status = bean.getChooseTaskStatus();
  
    //If task is to design a cover
    if(status.equals("Design a Cover") || status.equals("Design a Promotion")){
    String settechID = designers[tech][0];
    bean.setTechID(settechID); 
    String bookID = bean.getBookID();
    String tasktype = bean.getChooseTaskStatus();
    String taskstatus = tasktype + " Started";
    AssignTask(bean, bookID, tasktype, taskstatus,settechID);
    TaskAssigned(bean, bookID);
    }

    //If task is edit the manuscript
    else if (status.equals("Galley 1") || status.equals("Galley 2") || status.equals("Galley 3")){
    String settechID = editors[tech][0];
    bean.setTechID(settechID); 
    String bookID = bean.getBookID();
    String tasktype = bean.getChooseTaskStatus();
    String taskstatus = tasktype + " Started";
    AssignTask(bean, bookID, tasktype, taskstatus,settechID);
    TaskAssigned(bean, bookID);
    }

    //If the task is scanning the manuscript
    else if (status.equals("Scanning") || status.equals("ISBN") || status.equals("Publish")){
    String settechID = admins[tech][0];
    bean.setTechID(settechID); 
    String bookID = bean.getBookID(); 
    String tasktype = bean.getChooseTaskStatus();
    String taskstatus = tasktype + " Started";
    AssignTask(bean, bookID, tasktype, taskstatus,settechID);
    TaskAssigned(bean, bookID);
    }
    gotoPage("/ViewTaskDetailsParallel", request, response); //
    
    }
   
   //Method to assign task
   private void AssignTask(SBTS.Shared bean, String BookID, String TaskType, String TaskStatus, String TechID) throws ServletException, IOException{
        SBTS.DBI dbi = null;
    try{
    dbi = new SBTS.DBI();
        //Check if there is a database connection to Tomcat
        if(dbi.connect()){
	    //throw the assignment in the database
        dbi.AssignTask(BookID, TaskType, TaskStatus,TechID);
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

  //Method to throw the assigned task into database
private void TaskAssigned(SBTS.Shared bean, String BookID) throws ServletException, IOException{
        SBTS.DBI dbi = null;
try{
    dbi = new SBTS.DBI();
        //Check if there is a database connection to Tomcat
        if(dbi.connect()){
	    //throw task in database
        dbi.TaskAssigned(BookID);
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
 
