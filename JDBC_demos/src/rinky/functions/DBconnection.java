package rinky.functions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class DBconnection {

	private Connection conn;
	private Statement stat1, stat2, stat3;
	private ResultSet rs, rs3;
	boolean rs1;
	public static int session= 0;
	static int i,j;
	int c=0;
	public static List<Book> issuedBooks = new ArrayList<Book>();
	public static List<Book> returnedBooks = new ArrayList<Book>();
	
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/libmanage";
	String username = "root";
	String password = "root";
	
	public DBconnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,username,password);
			stat1 = conn.createStatement();
			stat2 = conn.createStatement();
			stat3 = conn.createStatement();
			System.out.println("Mysql database connected");
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				System.out.println("Couldn't delay");
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Couldn't establish connection");
			e.printStackTrace();
			return;
		}
	}
	
	
	
/*	public void getData() {
		String query1 = "INSERT INTO books (name, callno, author, publisher, issued ,quantity) VALUES ('John','D@4','rinky', 'cgi', '4', 6) ON DUPLICATE KEY UPDATE callno='D@4'";
		String query2 = "SELECT * FROM BOOKS";
		try {
			rs1 = stat1.execute(query1);
			rs = stat2.executeQuery(query2);
			if(rs1 == true) {
			System.out.println("The records are: ");
			while(rs.next()) {
				System.out.println("Name: "+ rs.getString("name") + "   Quantity: "+ rs.getString("quantity"));
			}
		}else {
			System.out.println("Not true");
		}
		}catch (SQLException e) {
			System.out.println("invalid query");
			e.printStackTrace();
		}
	}
*/	
	public void userLogin(String uName, String uPass) throws SQLException  {
		if(uName != null && uPass != null) {
			String query = "SELECT * FROM users";
				rs = stat1.executeQuery(query);
				while(rs.next()) {
					if (rs.getString("username").equals(uName) && rs.getString("password").equals(uPass)) {
						System.out.println("Valid User");
						session = 1;
					}
					
				}
				
			
				try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				System.out.println("Couldn't delay");
				e.printStackTrace();
			}
		}
	}


	public void userViewBooks() {
		String query = "SELECT * FROM books";
		try {
			rs = stat1.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("Error in executing query");
			e.printStackTrace();
		}
		System.out.println("The books are: ");
		try {
			while(rs.next()) {
				System.out.print("Id: "+ rs.getInt("id") +"\t" + "Name: "+ rs.getString("B_Name") + "\t" + "Author: "+ rs.getString("Author")+ "\t" + "Quantity: "+ rs.getString("NoCopies"));
				System.out.print("\n");
			}
		} catch (SQLException e) {
			System.out.println("Error in printing results");
			e.printStackTrace();
		}
	}



	public void userIssueBooks() throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter book id");
		int id = sc.nextInt();
		String query = "SELECT * FROM BOOKS WHERE id = '" + id +"'";
		try {
			rs = stat1.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("set is empty");
			e.printStackTrace();
		}
		int size =0;
		if (rs != null) 
		{
		  rs.last();    // moves cursor to the last row
		  size = rs.getRow(); // get row id 
		}else
			return;
		
		if (rs.getInt("NoCopies") >0) {
			int count = rs.getInt("NoCopies");
			String query1 = "UPDATE BOOKS SET NOCOPIES = '" + --count + "' WHERE ID = '" + id + "'";
			rs1 = stat1.execute(query1);
					
		}
		
		String query2 = "SELECT * FROM BOOKS WHERE id = '" + id +"'";
             
		rs = stat1.executeQuery(query2);
		
	        while (rs.next()) {

	            Book b = new Book();
	            b.setId(rs.getInt("id"));
	            b.setB_name(rs.getString("B_Name"));
	            b.setAuthor(rs.getString("Author"));
	            b.setQuantity(1);
	            issuedBooks.add(i, b);
	            i++;
	           
	        }

	   
	
	}


public void userViewIssuedBooks() {
		for(Book book : issuedBooks) {
			book.display();
		}
		
	}



public void userReturnBooks() throws SQLException {
	Scanner sc =  new Scanner(System.in);
	System.out.println("Enter book id to be returned");
	int o = sc.nextInt();
	String query = "SELECT * FROM BOOKS WHERE id = '" + o +"'";
	try {
		rs = stat1.executeQuery(query);
		rs.next();
		c = rs.getInt("NoCopies");
	} catch (SQLException e) {
		System.out.println("set is empty");
		e.printStackTrace();
	}
	
	String query1 = "UPDATE BOOKS SET NoCopies = " + ++c + " WHERE ID = '" + o + "'";
	rs1 = stat2.execute(query1);
	
	
	String query3 = "SELECT * FROM BOOKS WHERE id = '" + o +"'";
	
		rs = stat3.executeQuery(query3);
	    
	    
	    while(rs.next()) {
			 Book b = new Book();
	         b.setId(rs.getInt("id"));
	         b.setB_name(rs.getString("B_Name"));
	         b.setAuthor(rs.getString("Author"));
	         b.setQuantity(1);
	         returnedBooks.add(j, b);
	         j++;
		}
		
	    for(Book book : issuedBooks) {
			if (book.getId() == o) {
				int val = book.getQuantity();
				book.setQuantity(--val);
			}
		}
	
	
	
}



public void userViewReturnedBooks() {
	for(Book book : returnedBooks) {
		book.display();
	}
}



public void userUpdateProfile(String uName, String uPass) throws SQLException {
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter new password");
	String i = sc.nextLine();
	String query1 = "UPDATE USERS SET PASSWORD ='"+ i + "' WHERE USERNAME = '" + uName + "'";
	rs1 = stat1.execute(query1);
	System.out.println("New details are:");
	String query = "SELECT * FROM USERS WHERE USERNAME = '" + uName + "'";
	rs = stat2.executeQuery(query);
	rs.next();
	System.out.println("username: " + rs.getString("username") + "  password: "+ rs.getString("password") );
	
}



public void adminLogin(String aName, String aPass) throws SQLException {
	if(aName != null && aPass != null) {
		String query = "SELECT * FROM admin";
			rs = stat1.executeQuery(query);
			while(rs.next()) {
				if (rs.getString("name").equals(aName) && rs.getString("password").equals(aPass)) {
					System.out.println("Valid User");
					session = 1;
				}
				
			}
			
		
			try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			System.out.println("Couldn't delay");
			e.printStackTrace();
		}
	}
	
}



public void addUser() throws SQLException {
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter user id");
	int idn = sc.nextInt();
	System.out.println("Enter username");
	String name = sc.next();
	System.out.println("Enter password");
	String pass = sc.next();
	String query = "INSERT INTO USERS VALUES (" + idn + ",'"+ name+ "','"+ pass+ "')";
	rs1 = stat1.execute(query);
	
}



public void removeUser() throws SQLException {
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter user id to be deleted");
	int idn = sc.nextInt();
	String query = "DELETE FROM USERS WHERE ID = " + idn;
	rs1 = stat1.execute(query);
	
	
}



public void addBooks() throws SQLException {
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter book id:");
	int idn = sc.nextInt();
	System.out.println("Enter book name:");
	String name = sc.next();
	System.out.println("Enter book author:");
	String author = sc.next();
	System.out.println("Enter book quantity:");
	int quan = sc.nextInt();
	String query = "INSERT INTO BOOKS VALUES (" + idn + ",'" +name + "','" + author + "'," + quan +")";
	rs1 = stat1.execute(query);
}



public void removeBooks() throws SQLException {
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter book id to be deleted");
	int idn = sc.nextInt();
	String query = "DELETE FROM BOOKS WHERE ID = " + idn;
	rs1 = stat1.execute(query);
	
}



public void viewUsers() throws SQLException {
	String query = "SELECT * FROM USERS";
	rs = stat1.executeQuery(query);
	while(rs.next()) {
		System.out.println("[ Id: "+ rs.getInt("id") +"\t" + "Name: "+ rs.getString("username") + "\t" + "Password: "+ rs.getString("password")+ "]");
	}
	
}





 
	
	
}


