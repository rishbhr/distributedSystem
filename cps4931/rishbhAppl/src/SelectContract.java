/******************************************************************************************

SelectContract.java

The purpose of this servlet is to get the information of the contract that was selected by the manager.

   + This servlet is invoked by ViewContracts.jsp
   + This servlet dispatches to SelectContract.jsp
   
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

public class SelectContract extends SBTS.Control{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    //Get the current HTTP session from Tomcat
    HttpSession session = request.getSession(true);
    //Gets the bean from session and retrieves shared data 
    SBTS.Shared bean = (SBTS.Shared)session.getAttribute("shared");
    //Get the list contracts from the bean
    String [][] SelectedContract = bean.getNewContracts();
    //Retrieve the selected contract ID from View Contracts and set it as the index
    int ContractID = Integer.parseInt(request.getParameter("ContractID"));
    String setconID = SelectedContract[ContractID][0];
    //Set the contract ID
    bean.setContractID(setconID); 
    //Call the method to get the selected contract
    getSelectedContract(bean, setconID);
    gotoPage("/SelectContract.jsp", request, response); //Dispatch to SelectContract.jsp
    
    }

//Method to get the selected contract     
private void getSelectedContract(SBTS.Shared bean, String ContractID) throws ServletException, IOException{
        String[][] selectedContract;
        SBTS.DBI dbi = null;
try{
    dbi = new SBTS.DBI();
        //Check if there is a database connection to Tomcat
        if(dbi.connect()){
        //Call the method from the DBI
        selectedContract = dbi.getSelectedContract(ContractID);
        //Set the selected contract       
        bean.setSelectedContract(selectedContract);
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
 