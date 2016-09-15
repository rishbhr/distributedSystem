/******************************************************************************************

CreateBook.java

The purpose of this servlet is to create a new book record and assign it to the selected contract.

   + This servlet is invoked by BookList.jsp
   + This servlet dispatches to CreateBookConfirmation.jsp
   
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

public class CreateBook extends SBTS.Control{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    //Get the information that was inputted by the user
    String title = request.getParameter("Title");
    String date = request.getParameter("Date");
     String bookformat = request.getParameter("BookFormat"); 
    //Get the current HTTP session from Tomcat
    HttpSession session = request.getSession(true);
    //Gets the bean from session and retrieves shared data 
    SBTS.Shared bean = (SBTS.Shared)session.getAttribute("shared");
    //Get the selected contract's ID
    String conid = bean.getContractID();
    //Call the methods to create a book record, change the status, and assign it to a contract
    CreateBook(bean, title, date, bookformat);
    InsertBook(bean, conid);
    BookAuthor(bean, conid);
    ChangeContractStatus(bean, conid);
    getConfirmBook(bean);
    gotoPage("/CreateBookConfirmation.jsp", request, response); //Dispatch to CreateBookConfirmation.jsp
    }

//Method to enter the information to create a book record
private void CreateBook( SBTS.Shared bean, String title, String date, String bookformat) throws ServletException, IOException{
    SBTS.DBI dbi = null;
        //DBI object to access database
        
    try{
    dbi = new SBTS.DBI();
        //Check if there is a database connection to Tomcat
        if(dbi.connect()){          
            dbi.CreateBook(title, date, bookformat);
        }
    }
catch(Exception e){
    e.printStackTrace();
    bean.setError("Servlet Exception error" +e);
   
}
finally{
    dbi.close(); //Close connection to database
}
}

//Method to insert BookID to the selected contract
private void InsertBook( SBTS.Shared bean, String ConID) throws ServletException, IOException{
    SBTS.DBI dbi = null;
        //DBI object to access database
        
    try{
    dbi = new SBTS.DBI();
        //Check if there is a database connection to Tomcat
        if(dbi.connect()){          
            dbi.InsertBook(ConID);
        }
    }
catch(Exception e){
    e.printStackTrace();
    bean.setError("Servlet Exception error" +e);
   
}
finally{
    dbi.close(); //Close connection to database
}
}

//Create a new Book-Author record in the database
private void BookAuthor( SBTS.Shared bean, String ConID) throws ServletException, IOException{
    SBTS.DBI dbi = null;
        //DBI object to access database
        
    try{
    dbi = new SBTS.DBI();
        //Check if there is a database connection to Tomcat
        if(dbi.connect()){          
            dbi.BookAuthor(ConID);
        }
    }
catch(Exception e){
    e.printStackTrace();
    bean.setError("Servlet Exception error" +e);
   
}
finally{
    dbi.close(); //Close connection to database
}
}

//Change the status of the contract
private void ChangeContractStatus( SBTS.Shared bean, String ConID) throws ServletException, IOException{
    SBTS.DBI dbi = null;
        //DBI object to access database
        
    try{
    dbi = new SBTS.DBI();
        //Check if there is a database connection to Tomcat
        if(dbi.connect()){          
            dbi.ChangeContractStatus(ConID);
        }
    }
catch(Exception e){
    e.printStackTrace();
    bean.setError("Servlet Exception error" +e);
   
}
finally{
    dbi.close(); //Close connection to database
}
}

//Get the information of the book that was just created
private void getConfirmBook(SBTS.Shared bean) throws ServletException, IOException{
        String[][] confirmbook;
        SBTS.DBI dbi = null;
try{
    dbi = new SBTS.DBI();
        //Check if there is a database connection to Tomcat
        if(dbi.connect()){
            
        confirmbook= dbi.getConfirmBook();
        bean.setConfirmBook(confirmbook);
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