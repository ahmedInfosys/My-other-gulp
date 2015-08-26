

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet 
{
	static Connection conn;
	static String name, grade, output, ID;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private void addToDatabase (String ID, String FirstName,  String LastName, String Email, String zipCode) 
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
		 
			String insertQuery = "insert into AppUsers";
			insertQuery += " values ( '" + ID + "', '"+ FirstName + "', '" + LastName + "','"+ Email + "','"+ zipCode+ "')"; 
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
		String ID, FirstName, LastName, Email, zipCode;
		
		Random r = new Random();
		ID= String.valueOf(r.nextInt(1000));
		HttpSession session = request.getSession();
    	session.setAttribute("UserID", ID);
    	
	
		FirstName=request.getParameter("fName");
		
		LastName = (String)request.getParameter( "lName" );
	
		Email=request.getParameter("email");
		zipCode= request.getParameter("zip");
	
		addToDatabase (ID, FirstName, LastName, Email, zipCode);
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		String UserID, FirstName, LastName, Email, zipCode, RatingDate, Rating, Review;
		
		HttpSession session2= request.getSession(true);
		UserID= session2.getAttribute("UserID").toString();
		
		
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
	    ResultSet rs2 = stmt.executeQuery("select * from Reviews where UserID=" + UserID);
         
         output+="<table border=2 color=white>";
         output+="<tr><th>Rating Date </th><th>Rating</th><th>Review</th></tr> "; 
       
         	while(rs2.next())
         	{
         		RatingDate= rs2.getString("ratingDate");
         		//System.out.println(RatingDate);
         		Rating= rs2.getString("Rating");
         		Review= rs2.getString("reviews");
    		
         		output+= "<tr><td>" + RatingDate + "</td><td>"+ Rating+ "</td><td>"+ Review+ "</td></tr>"; 
         	}
    		conn.close();
	       		
		}
		catch (Exception e) 
      {
  	   e.getMessage();
      }
		 
      request.setAttribute("message", output);
      getServletContext().getRequestDispatcher("/ReviewsOutput.jsp").forward(request,response);
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
