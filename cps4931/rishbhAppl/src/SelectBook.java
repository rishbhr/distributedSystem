/******************************************************************************************

SelectBook.java


   + This servlet is invoked by
   + This servlet dispatches to SelectBook.jsp
   
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

public class SelectBook extends SBTS.Control{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    //Get the current HTTP session from Tomcat
    HttpSession session = request.getSession(true);
    //Gets the bean from session and retrieves shared data 
    SBTS.Shared bean = (SBTS.Shared)session.getAttribute("shared");
    String [][] SelectedBook = bean.getShepherdBookList();
    int BookID = Integer.parseInt(request.getParameter("BookID"));
    String setbookID = SelectedBook[BookID][0];
    bean.setBookID(setbookID); 
    bean.setError(setbookID);
    getSelectedBook(bean, setbookID);
    gotoPage("/SelectBook.jsp", request, response);
    
    }
   
  /* 
   private void setContractID(SBTS.Shared bean, String id) throws ServletException, IOException{
    SBTS.DBI dbi = new SBTS.DBI();
try{
    dbi = new SBTS.DBI();
        //Check if there is a database connection to Tomcat
        if(dbi.connect()){
            
        bean.setContractID(id);
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
*/
    
private void getSelectedBook(SBTS.Shared bean, String BookID) throws ServletException, IOException{
        String[][] selectedbook;
        SBTS.DBI dbi = null;
try{
    dbi = new SBTS.DBI();
        //Check if there is a database connection to Tomcat
        if(dbi.connect()){
        selectedbook = dbi.getSelectedBook(BookID);
        bean.setSelectedBook(selectedbook);
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
 