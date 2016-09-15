/******************************************************************************************

AssignShepherd.java

The purpose of this servlet is to process the selected book from the list
and generate a list of shepherds and their book counts.

   + This servlet is invoked by BookList.jsp
   + This servlet dispatches to AssignShepherd.jsp
   
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

public class AssignShepherd extends SBTS.Control{
    protected DBI dbi;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    //Get the current HTTP session from Tomcat
    HttpSession session = request.getSession(true);
    //Gets the bean from session and retrieves shared data 
    SBTS.Shared bean = (SBTS.Shared)session.getAttribute("shared");
    //Get the list of books from the bean
    String [][] books = bean.getBooks();
    //Retrieve the ID of the book that was selected and convert to integer to use as an index
    int BookID = Integer.parseInt(request.getParameter("BookID"));
    String setbookID = books[BookID][0];
    //Call the method to get the list of shepherds
    getShepherds(bean); 
    bean.setBookID(setbookID); //Set the book ID
    gotoPage("/AssignShepherd.jsp", request, response); //Dispatcher to AssignShepherd.jsp 
    
    }
    
//Method to retrieve the list of shepherds from the database
private void getShepherds(SBTS.Shared bean) throws ServletException, IOException{
        String[][] shepherds;
        SBTS.DBI dbi = null;
try{
    dbi = new SBTS.DBI();
        //Check if there is a database connection to Tomcat
        if(dbi.connect()){
         //Call the method from the DBI   
        shepherds= dbi.getShepherds();
        //Set the list of Shepherds so that it can be called in the jsp page
        bean.setShepherds(shepherds);
        
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
 