package rinky.programs;

import java.util.Scanner;

import rinky.functions.Admin;
import rinky.functions.DBconnection;
import rinky.functions.Person;
import rinky.functions.User;

public class Program {

	public static void main(String[] args) {
		
		while(true) {
		
		System.out.println("WELCOME TO LIBRARY MANAGEMENT SYSTEM");
		System.out.println("************************************");
		System.out.println("1. Login as a user");
		System.out.println("2. Administrative settings");
		System.out.println("3. View Library");
		System.out.println("************************************ ");
		
   
		System.out.println("Enter your choice");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		
		switch(choice) {
		case 1:
			Person user = new User();
			user.login();
			if(DBconnection.session == 1)user.Panel();
			break;
		case 2:
			Person admin = new Admin();
			admin.login();
			if(DBconnection.session == 1)admin.Panel();
			break;
		case 3: 
			DBconnection db = new DBconnection();
			db.userViewBooks();
			break;
		default:
			System.out.println("Choose from available options");
		       
		}
		
		
		
	}

}
}
