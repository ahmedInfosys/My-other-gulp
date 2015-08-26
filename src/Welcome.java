

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
/**
 * Servlet implementation class Welcome
 */
@WebServlet("/Welcome")
public class Welcome extends HttpServlet {
	static Connection conn;
	static String output="";

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Welcome() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		String name, companyName, coID= "";
		name=request.getParameter("query");
		System.out.println(name);
		
	
	       	//URL of Oracle database server
	       	 
	            String url = "jdbc:oracle:thin:testuser/password@localhost"; 
	        try {
					
	        	Class.forName("oracle.jdbc.driver.OracleDriver");
				
	            //properties for creating connection to Oracle database
	            Properties props = new Properties();
	            props.setProperty("user", "testdb");
	            props.setProperty("password", "password");
	          
	            //creating connection to Oracle database using JDBC
	            try {
	    			conn = DriverManager.getConnection(url,props);
	    		} catch (SQLException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	          
	            Statement stmt = conn.createStatement();
	            //System.out.println("select * from Restaurants where name Like '%" + name +"%'");
	            ResultSet rs = stmt.executeQuery("select * from Restaurants where name Like '%" + name +"%'");
	            if(rs==null)
	            {
	            	System.out.println("here");//display selected restaurant is unavailable, please create the new restaurant in new jsp.
	            }
	            else
	            {
	            	System.out.println("in else");
//	            	 output+="<p></p><table border=1 color=white bgcolor=white>";
//	                 output+="<tr><th>Restaurant Names</th></tr> ";
		            output+="<div class=\"container-fluid col-sm-4 col-sm-offset-4\"> <table class=\"table table-striped table-bordered\">";
		            output+="<tr><th>Restaurant Name </th></tr> "; 
		            
		            
	                 while(rs.next())
	                 {
	              	   companyName=rs.getString("name");
	              	   coID=rs.getString("ID");
	              	   output+= "<tr><td><a href= Review?name="+ coID + ">"+ companyName +"</a></td></tr>";
	                 }
	                HttpSession session = request.getSession();
	 	        	session.setAttribute("restaurantID", coID);
	            }
	            conn.close();
	        }
	        
	        
	           catch (Exception e) 
	            {
	        	   e.getMessage();
	            }
	        
          	 
	        request.setAttribute("message", output);
	        getServletContext().getRequestDispatcher("/NewOutput.jsp").forward(request,response);
	        output="";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
