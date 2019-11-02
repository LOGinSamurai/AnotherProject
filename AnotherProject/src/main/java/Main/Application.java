 package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Application {
	
	

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
	 
		
		
		
		Connection connection = null;
		try {
			String url="jdbc:postgresql://localhost:5432/university";
			 connection = DriverManager.getConnection(url, "postgres", "k12345321K");
			 Statement st = connection.createStatement();
			 st.execute("drop table if exists Students;");
			 st.executeUpdate("create table if not exists Students(\r\n" + 
			 		"	ID Serial primary key,\r\n" + 
			 		"	fullname varchar(30),\r\n" + 
			 		"	dob date,\r\n" + 
			 		"	mark numeric\r\n" + 
			 		"); ");	
			 int n;
			 System.out.println("Choose number of the next rows");
			 try {
			  n =Integer.parseInt(in.nextLine());
		 
			 System.out.println("Installed table Students");
			  for (int i = 0; i < n; i++) {	
				System.out.println();
					System.out.println("Please insert data(Student_fullname)");
					String fullnameIN =in.nextLine();
				System.out.println();	
					System.out.println("Please insert data(Student_DateOfBerth)(required format: yyyy-mm-dd)");
					String dobIN =in.nextLine();
				System.out.println();	
					System.out.println("Please insert data(Student_mark)");
					 Float markIN;
				//	try {
					     markIN = Float.parseFloat(in.nextLine());
			//		}catch( NumberFormatException e){    
				//		e.printStackTrace();}
					
					
			 st.executeUpdate("insert into students(fullname,dob,mark) values(' "+fullnameIN+"','"+dobIN+"',"+markIN+");");
			 }
	 
			 System.out.println("inserted rows");
			 st.executeUpdate("update students set dob = '1799-06-06' where id =1;");
			 System.out.println("update row with id =1");
			 
			 ResultSet resultSet= st.executeQuery("select * from Students;");
			 
			 while(resultSet.next()) {
				 System.out.println(resultSet.getInt("id")+" | "+
						 			resultSet.getString("fullname")+" | "+
						 			resultSet.getDate("dob")+" | "+
						 			resultSet.getFloat("mark")
						 			);
			 }
			 }catch( NumberFormatException e){    
					e.printStackTrace();}
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

	}

}
