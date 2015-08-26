

import java.io.IOException;
import java.sql.Connection;
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

/**
 * Servlet implementation class SignIn
 */
@WebServlet("/SignIn")
public class SignIn extends HttpServlet {
	static Connection conn;
	static String name, grade, output, ID;
	private static final long serialVersionUID = 1L;
     
	private String page;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignIn() 
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
		// TODO Auto-generated method stub
				String UserID, Email, RatingDate, Rating, Review, Password,user_output = "";
					output="";
				
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

			    
			    Email=request.getParameter("email");
			    System.out.println(Email);
				Password= request.getParameter("password");
				System.out.println(Password);
				
		        Statement stmt = conn.createStatement();
			    ResultSet rs2 = stmt.executeQuery("select * from AppUsers where email='" + Email + "' and zipcode = '" + Password + "'");
			    
			    
			    
			    
		         if(rs2.next()==false)
		         {
		        	 output= " invalid email and/or zip code";
		        	 request.setAttribute("message", output);
		        	 page = "/SignInJSP.jsp";
		        	 request.setAttribute("invalid_user", output);
				     //getServletContext().getRequestDispatcher("/ReviewsOutput.jsp").forward(request,response);
				     output="";
		         }
		         else
		         {
		      
		        	 page = "/Profile.jsp";
		        	user_output = rs2.getString("FirstName") + " " + rs2.getString("LastName");
		        	
		        	request.setAttribute("user", user_output);
		        	ID= rs2.getString("ID");
		        	HttpSession session = request.getSession();
		        	session.setAttribute("UserID", ID);
		         
		    		conn.close();
		    		
		    		url = "jdbc:oracle:thin:testuser/password@localhost"; 
		    		Class.forName("oracle.jdbc.driver.OracleDriver");
					
		            //properties for creating connection to Oracle database
		            props = new Properties();
		            props.setProperty("user", "testdb");
		            props.setProperty("password", "password");
		          
		            //creating connection to Oracle database using JDBC
		            try {
		    			conn = DriverManager.getConnection(url,props);
		    		} catch (SQLException e) {
		    			// TODO Auto-generated catch block
		    			e.printStackTrace();
		    		}	
		            stmt = conn.createStatement();
		            ResultSet rs = stmt.executeQuery("select * from Reviews where UserID= '" + ID + "'");
//		            output+="<div class=\"container-fluid \"> <table class=\"table table-striped table-bordered\">";
//		            output+="<tr><th>Rating Date </th><th>Rating</th><th>Review</th></tr> "; 
		            
		            while(rs.next())
		            {
		            	RatingDate= rs.getString("ratingDate");
		            	System.out.println(RatingDate);
		         		Rating= rs.getString("Rating");
		         		Review= rs.getString("reviews");
		         		ResultSet rs3 = stmt.executeQuery("select * from appusers where ID= '" + rs2.getString("ID") +"'");
		           		rs3.next();
		         		
		         		output+="<nav class=\"navbar navbar-default \">" +
		         		       "<div class=\"form-group\"> <p class=\"navbar-text navbar-left\">Rating date:"+ RatingDate +
		         		        "<p class=\"navbar-text navbar-left\"><b>Commented by: </b>" + rs3.getString("FirstName") + " " + rs3.getString("LastName") +
		         		        " <p class=\"navbar-text navbar-right\">Rating: " + Rating+ "</p> </div>";
		         		
		         		
		         		output+= "<div>  <p class=\"navbar-text navbar-left\">Review:"+ Review + "</p> </div></nav>"; 
//		         		output+= "<tr><td>" + RatingDate + "</td><td>"+ Rating+ "</td><td>"+ Review+ "</td></tr>"; 
		            }
		            //output += "</table></div>";
		            
		          System.out.println(output);
		            
		         }
			    }
  
				catch (Exception e) 
		      {
		  	   e.getMessage();
		      }
			
		      request.setAttribute("message", output);
		      getServletContext().getRequestDispatcher(page).forward(request,response);
		      
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request,response);
	}

}
