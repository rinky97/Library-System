 package rinky.functions;

import java.sql.SQLException;
import java.util.Scanner;

public class Admin extends Person {

	Scanner sc = new Scanner(System.in);
	DBconnection db = new DBconnection();
	String aName, aPass;
	
	
	public Admin() {
		// TODO Auto-generated constructor stub
	}
	
	public Admin(String name, int age) {
		super(name, age);
				
	}
	
	public void approveUser() {
		
	}
	
	public void removeUser() {
		
	}
	
	public void approveRequest() {
		
	}
	
	public void rejectRequst() {
		
	}
	
	public void viewUsers() {
		
	}
	
	public void viewIssuedBooks() {
		
	}

	@Override
	public void Panel() {
		System.out.println("WELCOME TO ADMIN PANEL");
		System.out.println("*********************");
		System.out.println("1. Add User");
		System.out.println("2. Remove User");
		System.out.println("3. Add books in Library");
		System.out.println("4. Remove books in Library");
		System.out.println("5. View Library");
		System.out.println("6. View Users");
		System.out.println("---------------------");
		
		System.out.println("Enter your choice");
		
		int c = sc.nextInt();

		switch(c) {
		
		case 1: try {
				db.addUser();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 break;
		 
		case 2: try {
				db.removeUser();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		       break;
		case 3: try {
				db.addBooks();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     break;
		case 4: try {
				db.removeBooks();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    break;
		case 5: db.userViewBooks();
		        break;
		case 6: try {
				db.viewUsers();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		      
		
		
	}

	@Override
	public void login() {
		DBconnection.session = 0;
		System.out.print("Enter your username");
		//Scanner sc = new Scanner(System.in);
	     aName = sc.nextLine();
		System.out.print("Enter your password");
		 aPass = sc.nextLine();
		
		try {
			db.adminLogin(aName, aPass);
			if(DBconnection.session == 1)System.out.println("Login successful with session id "+ DBconnection.session );
			else {
				System.out.println("Please login again");
				login();
			
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
