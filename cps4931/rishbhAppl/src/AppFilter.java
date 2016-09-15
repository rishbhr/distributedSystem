/******************************************************************************************

AppFilter.java

The purpose of this servlet is to filter each request/response ensuring that a valid session
still exists. If the user tries to submit data or go to a page while using an invalid session
then the user will be immediately redirected to the login page for your web app.
   
******************************************************************************************/

package SBTS;
 
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class AppFilter implements Filter
{
   //The path to access your files on eve
   public static String contextPath = "SBTS"; // ********CHANGE THIS VALUE TO YOUR CONTEXT NAME********

   public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException
   {
      HttpServletRequest request = (HttpServletRequest) req;
      HttpServletResponse response = (HttpServletResponse) res;
      String requestURI = request.getRequestURI();
      
      if((((HttpServletRequest)request).getSession(false)==null) && (!requestURI.startsWith("/"+contextPath+"/Login")))
      {
         // the session has expired (or does not exist) and the user is not on the login page.
         ((HttpServletResponse)response).sendRedirect("/"+contextPath+"/Login");
      } 
      else // continue doing what you normally would
      {
         chain.doFilter(request,response);
      }
        
   }
   public void init(FilterConfig config) throws ServletException
   {   
       // add init code if needed  
       // example: String testParam = config.getInitParameter("test-param");
   }
   public void destroy()
   {
        //add code to release any resource if needed
   }
}
