package rinky.functions;

import java.sql.SQLException;
import java.util.Scanner;

public class User extends Person {
	Scanner sc = new Scanner(System.in);
	DBconnection db = new DBconnection();
	String uName, uPass;
	
	public User(String name, int age) {
		super(name, age);
		
	}
	public User() {
	}
	
	public void login() {
		DBconnection.session = 0;
		System.out.print("Enter your username");
	     uName = sc.nextLine();
		System.out.print("Enter your password");
		 uPass = sc.nextLine();
		
		try {
			db.userLogin(uName, uPass);
			if(DBconnection.session == 1)System.out.println("Login successful with session id "+ DBconnection.session );
			else {
				System.out.println("Please login again");
				login();
			
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void Panel() {
		System.out.println("WELCOME TO USER PANEL");
		System.out.println("*********************");
		System.out.println("1. View Library");
		System.out.println("2. Issue Books");
		System.out.println("3. View Issued Books");
		System.out.println("4. Return Books");
		System.out.println("5. View Returned Books");
		System.out.println("6. Update profile");
		
		System.out.println("---------------------");
		
		System.out.println("Enter your choice");
		
		int c = sc.nextInt();
		
		switch(c) {
		case 1: 
			    try {
				this.viewLibrary();
			    }catch (SQLException e) {
				System.out.println("Method not called properly");
				e.printStackTrace();
		     	}
		        break;
		case 2: 
			    try {
				this.issueBooks();
			    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
			    break;
		
		case 3: this.viewIssuedBooks();
		        break;
		case 4: 
			try {
				this.returnBooks();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		        break;
		case 5: db.userViewReturnedBooks();     
		        break;
		case 6: try {
				db.userUpdateProfile(uName, uPass);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    break;
		}
		
	}
	
	private void returnBooks() throws SQLException {
		db.userReturnBooks();
		
	}
	private void viewIssuedBooks() {
		db.userViewIssuedBooks();
		
	}
	private void issueBooks() throws SQLException {
		db.userIssueBooks();
		
	}
	private void viewLibrary() throws SQLException {
	     db.userViewBooks();
				
	}

}
