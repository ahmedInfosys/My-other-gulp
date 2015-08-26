

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Review
 */
@WebServlet("/Review")
public class Review extends HttpServlet 
{
	static Connection conn;
	static String name, output="", output2="", output3="";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Review() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("E MM/dd/yyyy 'at' mm:hh a");
		
		
		String ID,Name, Address, Description, Rating, RatingDate,Review;
		double calc=0, ave=0;
		int count=0;
	
		name= request.getParameter("name");
		// TODO Auto-generated method stub
		try {
	       	//URL of Oracle database server
	       	 
	            String url = "jdbc:oracle:thin:testuser/password@localhost"; 
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
	            System.out.println(name);
	            ResultSet rs = stmt.executeQuery("select * from Restaurants where ID=" + name);
	            
//	        output+="<table border=2 color=white>";
//	        output+="<tr><th>ID </th><th>Restuarant Name</th><th>Restaurant Address </th><th> Description</th></tr> "; //
//	       
	            
	        rs.next();
	        	ID= rs.getString("ID");
	       		Name= rs.getString("NAME");
	       		Address= rs.getString("ADDRESS");
	       		Description= rs.getString("description");
	       		
	       		output+="<nav class=\"navbar navbar-default \">" +
		         		"<div class=\"form-group\"> <p class=\"navbar-text navbar-left\"><b>Restaurant Name: </b>"+ Name +
		         		" <p class=\"navbar-text navbar-default\"><b>Address: </b>" + Address+ "</p>"
		         		+ "<p class=\"navbar-text navbar-left\"><b>Number of reviews: </b>"+ count 
		         		+ " <p class=\"navbar-text navbar-right\"><b>Average Rating: </b>" + ave + " </div>";
	       		output+= "<div><p  align=\"left\" class=\"navbar-text navbar-left\"><b>Description: </b>"+ Description + "</p> </div></nav>"; 
	       		
	       		
	       		//output+= "<tr><td>" + ID + "</td><td>"+ Name + "</td><td>"+ Address+ "</td><td>"+ Description+ "</td><td></table>"; 
	     
           
	      //conn.close();
		}
		catch (Exception e) 
        {
    	   e.getMessage();
        }
		
		try
		{
	       String url = "jdbc:oracle:thin:testuser/password@localhost"; 
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
	       ResultSet rs2 = stmt.executeQuery("select * from Reviews where ID= '" + name +"'");
	       
	       
	       System.out.println("select * from Reviews where ID= '" + name +"'");
//           output2+="<table border=2 color=white>";
//           output2+="<tr><th>Rating Date </th><th>Rating</th><th>Review</th></tr> "; 
            
	       
           //System.out.println(rs2.next());
           	while(rs2.next())
           	{
           		RatingDate= rs2.getString("ratingDate");
           		System.out.println(RatingDate);
           		Rating= rs2.getString("Rating");
           		System.out.println(Rating);
           		Review= rs2.getString("reviews");
           		System.out.println(Review);
           		
           		calc+= Double.parseDouble(Rating);
           		count++;
           		System.out.println(RatingDate + " "+ Rating+ " "+ Review);
           		//output2+= "<tr><td>" + RatingDate + "</td><td>"+ Rating+ "</td><td>"+ Review+ "</td></tr>"; 
           		ResultSet rs3 = stmt.executeQuery("select * from appusers where ID= '" + rs2.getString("ID") +"'");
           		rs3.next();
           		output2+="<nav class=\"navbar navbar-default \">" +
	         		       "<div class=\"form-group\"> <p class=\"navbar-text navbar-left\">Rating date:"+ RatingDate +
	         		        "<p class=\"navbar-text navbar-left\"><b>Commented by: </b>" + rs3.getString("FirstName") + " " + rs3.getString("LastName") +
	         		        " <p class=\"navbar-text navbar-right\">Rating: " + Rating+ "</p> </div>";
		       
		   		output2+= "<div>  <p class=\"navbar-text navbar-left\"><b>Review: </b>"+ Review + "</p> </div></nav>"; 
           	}
           	if(count==0)
           	{
           		ave=0;
           	}
           	else
           	{
           		ave= calc/count;
           	}
           	
            
           	System.out.println(ave);
           	System.out.println(count); 
 		
		}
		catch (Exception e) 
        {
    	   e.getMessage();
        }
		 
        request.setAttribute("message", output);
        request.setAttribute("message2", output2);
        getServletContext().getRequestDispatcher("/InputReview.jsp").forward(request,response);
        output="";
        output2="";
        output3="";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
