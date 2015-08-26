

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ToDatabase
 */
@WebServlet("/ToDatabase")
public class ToDatabase extends HttpServlet 
{
	static Connection conn;
	static String name, grade, output, ID;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToDatabase() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private void addToDatabase (String ID, String date,  String inputDate, String review, String UserID ) 
    {
     	//URL of Oracle database server
     	 
          String url = "jdbc:oracle:thin:testuser/password@localhost"; 
          try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
          
          //properties for creating connection to Oracle database
          Properties props = new Properties();
          props.setProperty("user", "testdb");
          props.setProperty("password", "password");
        
          //creating connection to Oracle database using JDBC
          try {
  			conn = DriverManager.getConnection(url,props);
			Statement stmt = conn.createStatement();
		 
			String insertQuery = "insert into Reviews";
			//System.out.println(" insert into Reviews values ( '" + ID + "', '"+ rating + "', '" + date + "','"+ review + "')");
			insertQuery += " values ( '" + ID + "', '"+ date + "', '" + inputDate + "','"+ review + "','"+ UserID + "')"; 
			stmt.execute ( insertQuery );   
			
			System.out.println(insertQuery);
			conn.close();    
		} 
		catch ( SQLException ex ) 
		{
			ex.printStackTrace();
		}

    }
    protected void processRequest( HttpServletRequest request, HttpServletResponse response )throws ServletException, IOException 
	{
		String ID, rating, inputDate, review, UserID;
	
		HttpSession session= request.getSession(true);
		ID= session.getAttribute("restaurantID").toString();
		System.out.println("Session Variable " +ID);
		rating=request.getParameter("rating");
		System.out.println(rating);
		review = (String)request.getParameter( "review" );
		System.out.println(review);
		
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("E MM/dd/yyyy hh:mm a");
		
		inputDate = sdf.format(now);
		System.out.println(inputDate);
		
		HttpSession session2= request.getSession(true);
		UserID= session2.getAttribute("UserID").toString();
	
		addToDatabase (ID, rating, inputDate, review, UserID);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ID2, RatingDate, Rating, Review;
		
		HttpSession session= request.getSession(true);
		ID= session.getAttribute("restaurantID").toString();
		
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
	    ResultSet rs2 = stmt.executeQuery("select * from Reviews where ID=" + ID);
         
      
       
         	while(rs2.next())
         	{
         		RatingDate= rs2.getString("ratingDate");
         		//System.out.println(RatingDate);
         		Rating= rs2.getString("Rating");
         		Review= rs2.getString("reviews");
         		ResultSet rs3 = stmt.executeQuery("select * from appusers where ID= '" + rs2.getString("ID") +"'");
           		rs3.next();
         		output+="<nav class=\"navbar navbar-default \">" +
	         		       "<div class=\"form-group\"> <p class=\"navbar-text navbar-left\">Rating date:"+ RatingDate +
	         		        "<p class=\"navbar-text navbar-left\"><b>Commented by: </b>" + rs3.getString("FirstName") + " " + rs3.getString("LastName") +
	         		        " <p class=\"navbar-text navbar-right\">Rating: " + Rating+ "</p> </div>";
		       
		   		output+= "<div>  <p class=\"navbar-text navbar-left\"><b>Review: </b>"+ Review + "</p> </div></nav>"; 
         
         	}
    		conn.close();
	       		
		}
		catch (Exception e) 
      {
  	   e.getMessage();
      }
		 
      request.setAttribute("message", output);
      getServletContext().getRequestDispatcher("/InputReview.jsp").forward(request,response);
      output="";
	}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
		doGet(request,response);
	}

}
