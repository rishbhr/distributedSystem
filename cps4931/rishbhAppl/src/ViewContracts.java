/******************************************************************************************

ViewContracts.java

The purpose of this servlet is to get the list of contracts that still need a book record.

   + This servlet is invoked by MainPage.jsp
   + This servlet dispatches to ViewContracts.jsp
   
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

public class ViewContracts extends SBTS.Control{
    protected DBI dbi;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    //Get the current HTTP session from Tomcat
    HttpSession session = request.getSession(true);
    //Gets the bean from session and retrieves shared data 
    SBTS.Shared bean = (SBTS.Shared)session.getAttribute("shared");
    //Call the method to get the list of contracts from the bean
    getNewContracts(bean);
    gotoPage("/ViewContracts.jsp", request, response);
    
    }
    
    //Method to get the list of contracts
private void getNewContracts(SBTS.Shared bean) throws ServletException, IOException{
        String[][] newContracts;
        SBTS.DBI dbi = null;
try{
    dbi = new SBTS.DBI();
        //Check if there is a database connection to Tomcat
        if(dbi.connect()){
            
        newContracts = dbi.getNewContracts();
        bean.setNewContracts(newContracts);
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
 