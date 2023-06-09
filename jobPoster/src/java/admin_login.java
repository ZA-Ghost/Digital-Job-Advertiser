    
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;



@WebServlet(urlPatterns = {"/admin_login"})
public class admin_login extends HttpServlet {

        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String Username = request.getParameter("Username");
        // We are retrieving the data from the input type with the name "Username"
        String Password = request.getParameter("Password");
        // We are retrieving the data from the input type with the name "Password"
        
        //Whenever making use of databases, we must have a try and catch whereby the processing
        // happens under the try, errors of the processing are caught in the catch.
        try{
             Class.forName("com.mysql.jdbc.Driver"); //
             Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/jobposter","root","");
 
             String sql= "SELECT * from logindetails where username='"+Username+"' and password='"+Password+"'"; 
             PreparedStatement pst = connection.prepareStatement(sql);
 
              ResultSet rs=pst.executeQuery(); 
           
            if(rs.next()){
              response.sendRedirect("admin-job-post.jsp");  ;            
            }else{
            out.println("Invalid Credentials");
            } 
            } 
        catch(ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
            }   
    }
}
