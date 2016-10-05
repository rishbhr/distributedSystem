/******************************************************************************************

ConfirmTech.java

The purpose is to interact with selecting the technician from the available list of technicians.

   + This servlet is invoked by SelectTech.jsp
   + This servlet dispatches to ConfirmTechParallel.java
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

public class ConfirmTech extends SBTS.Control{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    //Get the current HTTP session from Tomcat
    HttpSession session = request.getSession(true);
    //Gets the bean from session and retrieves shared data
    SBTS.Shared bean = (SBTS.Shared)session.getAttribute("shared");
     //Grabs the array here
     String [][] designers = bean.getDesigners();
     String [][] editors = bean.getEditors();
     String [][] admins = bean.getAdmins();
    //Gets the tech id and assigns it here
    int tech = Integer.parseInt(request.getParameter("TechID"));
    //Get the status of the task assigned to tech
    String status = bean.getChooseTaskStatus();
  
    //if task is to design cover
    if(status.equals("Design a Cover") || status.equals("Design a Promotion")){
    //get the tech id
    String settechID = designers[tech][0];
    bean.setTechID(settechID);
    //get the book id
    String bookID = bean.getBookID();
    String tasktype = bean.getChooseTaskStatus();
    String taskstatus = tasktype + " Started";
    //assign to tech
    AssignTask(bean, bookID, tasktype, taskstatus,settechID);
    TaskAssigned(bean, bookID);
    getConfirmTask(bean);
    }
    
        //If process of the book is in the gallies
    else if (status.equals("Galley 1") || status.equals("Galley 2") || status.equals("Galley 3")){
    String settechID = editors[tech][0];
    bean.setTechID(settechID); 
    String bookID = bean.getBookID();
    String tasktype = bean.getChooseTaskStatus();
    String taskstatus = tasktype + " Started";
    //assign the tech the galley task
    AssignTask(bean, bookID, tasktype, taskstatus,settechID);
    TaskAssigned(bean, bookID);
    getConfirmTask(bean);
    }
    
     //If the task is scanning
    else if (status.equals("Scanning") || status.equals("ISBN") || status.equals("Publish")){
    String settechID = admins[tech][0];
    bean.setTechID(settechID); 
    String bookID = bean.getBookID(); 
    String tasktype = bean.getChooseTaskStatus();
    String taskstatus = tasktype + " Started";
    AssignTask(bean, bookID, tasktype, taskstatus,settechID);
    TaskAssigned(bean, bookID);
    getConfirmTask(bean);
    }
    gotoPage("/ConfirmTech.jsp", request, response); //
    
    }
   
   //Method to keep track of assignments in the database
private void AssignTask(SBTS.Shared bean, String BookID, String TaskType, String TaskStatus, String TechID) throws ServletException, IOException{
        SBTS.DBI dbi = null;
try{
    dbi = new SBTS.DBI();
        //Check if there is a database connection to Tomcat
        if(dbi.connect()){
        //assign task with details of task
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

    //Method to 
private void TaskAssigned(SBTS.Shared bean, String BookID) throws ServletException, IOException{
        SBTS.DBI dbi = null;
try{
    dbi = new SBTS.DBI();
        //Check if there is a database connection to Tomcat
        if(dbi.connect()){
	    //
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

    //Method to cofirm task assignment
private void getConfirmTask(SBTS.Shared bean) throws ServletException, IOException{
        String[][] confirmtask;
        SBTS.DBI dbi = null;
try{
    dbi = new SBTS.DBI();
        //Check if there is a database connection to Tomcat
        if(dbi.connect()){
        //throw the confirmation of task in the database
        confirmtask= dbi.getConfirmTask();
        //throw the confirmation of task in the bean
        bean.setConfirmTask(confirmtask);
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
 
