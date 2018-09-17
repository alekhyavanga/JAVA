//Project Name: Database for universities recruitment management

//By Sachin Kumar Korenga and Rochita Thakkallapally.

import java.sql.*;
import java.util.Scanner;
import java.io.*;
import java.math.BigInteger;
class Project
{
	static BufferedReader br;  // Needed for keyboard I/O.
	static Connection conn; // A connection to the DB must be established
	// before requests can be handled.  You should
	// have only one connection.
	static Statement stmt;  // Requests are sent via Statements.  You need
	// one statement for every request you have
	// open at the same time.

	// "main" is where the connection to the database is made, and
	// where I/O is presented to allow the user to direct requests to
	// the methods that actually do the work.

	public static void main (String args []) throws IOException
	{
		String username="rt133", password = "rochita923";
		String ename;

		//keyboard = new BufferedReader(new InputStreamReader (System.in));

		try { //Errors will throw a "SQLException" (caught below)

			// Load the Oracle JDBC driver
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

			System.out.println("Registered the driver...");

			// Connect to the database.  The first argument is the
			// connection string, the second is your username, the third is
			// your password.
			conn = DriverManager.getConnection (
					"jdbc:oracle:thin:@oracle1.wiu.edu:1521/toolman.wiu.edu",
					username, password);

			conn.setAutoCommit(false);

			System.out.println("logged into oracle as " + username);

			// Create a Statement; again, you may have/need more than one.
			stmt = conn.createStatement ();

			//function call to inputSelection method where the logic is
			inputSelection();

			//committing the transaction
			conn.commit();

			//closing the connection
			conn.close();

		} //end of try block

		catch(Exception e)
		{
			System.out.println("Caught SQL Exception: \n     " + e);
		}//end of catch block
	}//end of main function


	public static void inputSelection() throws Exception{

		int inputEntered=0;
		br = new BufferedReader(new InputStreamReader(System.in));

		do  //beginning of do while loop
		{
			//Menu Items
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println("     DATABASE FOR UNIVERSITIES RECRUITMENT MANAGEMENT      ");
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println("                CHOOSE THE OPTIONS BELOW                   ");
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println("1.PRESS 1 For COMPANY");
			System.out.println("2.PRESS 2 For STUDENTS");
			System.out.println("3.PRESS 3 for Exit");
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

			try  { //Errors will throw a "SQLException" (caught below)
				inputEntered = Integer.valueOf(br.readLine());

			}///end of try

			catch (Exception ex){
				System.out.println("Caught SQL Exception: \n " + ex);
				inputSelection(); //calling back the function to display the Menu for the user to select
				System.out.println("Please select options from 1 to 3");



			}//end of catch

			//begin of switch case
			switch (inputEntered){

			case 1 :
				try {
					getcompanyActions();
				}
				catch (Exception Ex){
					System.out.println("Caught Exception: "+Ex);
				}
				break;

			case 2 :
				try {
					getstudentActions();
				}
				catch (Exception Ex){
					System.out.println("Caught Exception: "+Ex);
				}
				break;

			case 3:  System.out.println("Passez une bonne journee (Have a nice day in French)");
			System.exit(0); //Invoking thE System.exit call to exit from the program

			default:
				System.out.println("You entered a wrong input. Please select options from 1 to 3");
				inputSelection();

			}//end of switch-case
		}while(inputEntered!=3);

	}


	public static void getcompanyActions() throws Exception{

		int inputEntered=0;
		br = new BufferedReader(new InputStreamReader(System.in));


		do 
		{
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			System.out.println("                WELCOME TO COMPANY                          ");
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			System.out.println("              ENTER THE BELOW OPTION                        ");
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			System.out.println("1. PRESS 1 To INSERT INTO COMPANY.");
			System.out.println("2. PRESS 2 FOR NUMBERS OF STUDENTS APPLIED JOBS.");
			System.out.println("3. PRESS 3 TO UPDATE JOB_REQUIREMENT.");
			System.out.println("4. PRESS 4 TO CHECK WHICH STUDENT APPLIED TO WHICH COMPANY.");
			System.out.println("5. PRESS 5 To UPDATE THE INTEVIEWER NAME.");
			System.out.println("6. PRESS 6 TO TO CHECK STUDENT NAME ON PARTICULAR INTERVIEW DATE.");
			System.out.println("7. PRESS 7 TO DELETE OLD RECORD FROM INTERVIEW");
			System.out.println("8. PRESS 8 To DELETE INTERVIEWER");
			System.out.println("9. PRESS 9 To Exit !!");
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

			try  { //Errors will throw a "SQLException" (caught below)
				inputEntered = Integer.valueOf(br.readLine());

			}///end of try

			catch (Exception ex){
				System.out.println("Caught SQL Exception: \n " + ex);
				System.out.println("You entered a wrong input. Please select options from 1 to 9");

				inputSelection(); //calling back the function to display the Menu for the user to select

			}//end of catch

			//begin of switch case
			switch (inputEntered){


			case 1:       insertcompany();
			break;
			case 2:       numofstudentsapplied();
			break;
			case 3:       updatejob_requirement();
			break;
			case 4:       studentappliedcompany();
			break;
			case 5:       updateInterviewername();
			break;
			case 6:       studentslist();
			break;
			case 7:      deletefrominterview();
			break;
			case 8:       deleteInterview();
			break;
			case 9:       System.out.println("ENJOY THE REST OF THE DAY");
			break;
			default :     System.out.println("OH NO INCORRECT OPTION SELECT FROM 1 TO 9");
			break;
			}
		}while(inputEntered!=9);

		inputSelection();
	}


	public static void insertcompany() throws Exception{

		int cid;
		String cname="";
		String clocation="";
		String cemail="";
		BigInteger cphone;

		br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter COMPANY ID ");
		System.out.println(" [Ex:250 (3 NUMBERS ONLY)]");
		cid = Integer.valueOf(br.readLine());

		System.out.println("PLEASE PROVIDE THE COMPANY NAME : [Ex:INFOSYS (10 CHARACTERS ONLY)]");
		cname = br.readLine();

		System.out.println("PLEASE ENTER THE LOCATION OF COMPANY : [Ex:MISSOURI (10 CHARACTERS ONLY)] ");
		clocation = br.readLine();

		System.out.println("PROVIDE THE EMAIL OF COMPANY : [Ex:INFO@gmail (10 CHARACTERS ONLY)] ");
		cemail = br.readLine();

		System.out.println("ENTER THE PHONE NUMBER OF COMPANY :  [Ex: 5423167895 (10 NUMBERS)]");
		cphone = new BigInteger(br.readLine());

		String SQLQuery= "INSERT INTO COMPANY " +
				"VALUES("+cid+",'"+cname+"','"+clocation+"','"+cemail+"',"+cphone+")";

		System.out.println(SQLQuery);

		stmt.executeUpdate(SQLQuery);
		System.out.println("---------------------------------");
		System.out.println("       1 row Updated"            );
		System.out.println("----------------------------------");
		conn.commit();
	}



	public static void numofstudentsapplied() throws Exception{

		String SQLQuery = " SELECT JOB_REQUIREMENT.R_ID, "+
				" COUNT(*) AS TOTAL_STUDENTS "+ 
				" FROM JOB_REQUIREMENT, APPLIED_BY_STUDENT, STUDENT_APPLICANT  "+ 
				" WHERE  JOB_REQUIREMENT.R_ID=APPLIED_BY_STUDENT.R_ID  "+
				" AND APPLIED_BY_STUDENT.S_ID=STUDENT_APPLICANT.S_ID  "+
				" GROUP  BY JOB_REQUIREMENT.R_ID  ";
		System.out.println(SQLQuery);

		ResultSet rset = stmt.executeQuery(SQLQuery);

		System.out.printf("%-18s%-12s \n","r_id","Number Of Students");

		System.out.printf("-----------------------------------------\n");

		while(rset.next()){
			int i= rset.getInt("r_id");
			int num= rset.getInt(2);
			System.out.printf("%-18s%-12s \n",i,num);
		}
		System.out.println("");

		rset.close();

	}

	public static void updatejob_requirement() throws Exception{

		String intern;
		int r_id;
		br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("PLEASE GIVE US THE R_ID FOR WHICH YOU WANT TO CHANGE THE INTERN_EXP [Ex: 43]");
		r_id=Integer.valueOf(br.readLine());
		System.out.println("PLEASE ENTER THE UPDATED INTERN_EXP VALUE [Ex: 15 MONTHS]");
		intern=br.readLine();
		String SQLQuery="UPDATE JOB_REQUIREMENT "+
				" SET INTERN_EXPERIENCE ="+ "'"+intern+"'"+ 
				" WHERE R_ID ="+ r_id; 
		System.out.println(SQLQuery);

		stmt.executeUpdate(SQLQuery);

		System.out.println("--------------------------------------");
		System.out.println("      INTERN_EXP IS UPDATED           ");
		System.out.println("--------------------------------------");
		conn.commit();

	}

	public static void studentappliedcompany() throws Exception{

		try {
			String SQLQuery= "SELECT STUDENT_APPLICANT.SNAME, COMPANY.CNAME, UNIVERSITY.UNINAME"
					+" FROM STUDENT_APPLICANT, COMPANY, UNIVERSITY, JOB_REQUIREMENT, APPLIED_BY_STUDENT, VISITS"
					+" WHERE UNIVERSITY.U_ID=VISITS.U_ID AND VISITS.C_ID=COMPANY.C_ID AND COMPANY.C_ID=JOB_REQUIREMENT.C_ID"
					+" AND JOB_REQUIREMENT.R_ID=APPLIED_BY_STUDENT.R_ID"
					+" AND APPLIED_BY_STUDENT.S_ID=STUDENT_APPLICANT.S_ID"
					+" GROUP BY STUDENT_APPLICANT.SNAME, COMPANY.CNAME, UNIVERSITY.UNINAME";

			System.out.println(SQLQuery);


			ResultSet rset = stmt.executeQuery(SQLQuery);

			System.out.printf("%-13s%-22s%-14s \n","SNAME","CNAME","UNINAME");

			System.out.printf("----------------------------------------------------\n");

			while(rset.next()){
				String studentname  = rset.getString("SNAME");
				String companyname = rset.getString("CNAME");
				String universityname = rset.getString("UNINAME");
				System.out.printf("%-13s%-22s%-14s \n",studentname ,companyname,universityname);
			}
			rset.close();

		}
		catch(Exception e){
			e.printStackTrace();
		}

	}


	public static void updateInterviewername() throws Exception{

		int i_id;
		int c_id;
		String Name="";

		br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("PROVIDE THE I_ID [Ex: 604]");
		i_id = Integer.valueOf(br.readLine());

		System.out.println("PROVIDE THE C_ID [Ex:225]");
		c_id= Integer.valueOf(br.readLine());

		System.out.println("GIVE THE NEW INAME: [Ex: ROCHITA (10 CHARACTERS ONLY) ");
		Name= br.readLine();

		String SQLQuery=" UPDATE INTERVIEW  "+
				" SET INTERVIEW.INAME ="+ "'"+Name+"'"+ 
				"WHERE  INTERVIEW.I_ID ="+ i_id + 
				"AND  INTERVIEW.C_ID ="+ c_id;

		System.out.println(SQLQuery);

		stmt.executeUpdate(SQLQuery);

		System.out.println("-----------------------------");
		System.out.println("     UPDATED NEW INAME       ");
		System.out.println("-----------------------------");
		conn.commit();

	}


	public static void studentslist() throws Exception{

		String idate="";
		String SQLQuery=null;
		br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("PROVIDE THE INTERVIEWDATE [MM-DD-YYYY HUR:MIN] [Ex: 02-DEC-2018] \n");
		idate=br.readLine();

		SQLQuery= "SELECT I_ID,INTERVIEWDATE, STUDENT_APPLICANT.S_ID, SNAME, SEMAIL"
				+" FROM STUDENT_APPLICANT INNER JOIN INTERVIEW"
				+" ON STUDENT_APPLICANT.S_ID=INTERVIEW.S_ID AND INTERVIEWDATE<'"+idate+"'";

		System.out.println(SQLQuery);
		ResultSet rset = stmt.executeQuery(SQLQuery);

		System.out.printf("%-13s%-22s%-28s%-12s%-18s \n","I_ID","INTERVIEWDATE","S_ID","SNAME","SEMAIL");

		System.out.printf("---------------------------------------------------------------------------\n");

		while(rset.next()){
			int i_id = rset.getInt("i_id");
			String Idate= rset.getString("INTERVIEWDATE");
			int s_id = rset.getInt("s_id");
			String sname  = rset.getString("sname");
			String semail = rset.getString("semail");
			System.out.printf("%-13s%-22s%-28s%-12s%-18s \n",i_id,Idate,s_id,sname,semail);
		}
		rset.close();
	}

	public static void deletefrominterview() throws Exception{

		String idate="";
		br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("ENTER THE DATE TO DELETE THE OLD RECORD [EX : 08-DEC-2005]");
		idate=br.readLine();
		String SQLQuery="DELETE FROM INTERVIEW "+
				"WHERE  INTERVIEWDATE ="+"'"+idate+"'";
		System.out.println(SQLQuery);

		stmt.executeUpdate(SQLQuery);

		System.out.println("----------------------------------------------");
		System.out.println("OLD DATA IS REMOVED FROM DATABASE OF INTERVIEW");
		System.out.println("----------------------------------------------");
		conn.commit();
	}

	public static void deleteInterview() throws Exception{
		int id;
		br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("ENTER THE I_ID WHICH YOU WANT TO TAKE REMOVE FROM THE DATABASE [Ex: 602]");
		id=Integer.valueOf(br.readLine());

		String SQLQuery="DELETE FROM INTERVIEW "+
				"WHERE I_ID ="+ id +"";

		System.out.println(SQLQuery);

		stmt.executeUpdate(SQLQuery);

		System.out.println("-------------------------------------------");
		System.out.println(" INTERVIEWER IS REMOVED FROM THE DARTABASE ");
		System.out.println("-------------------------------------------");
		conn.commit();

	}



	public static void getstudentActions() throws Exception{

		int inputEntered=0;
		br = new BufferedReader(new InputStreamReader(System.in));

		do
		{
			System.out.println("##########################################");
			System.out.println("       WELCOME TO STUDENTS APPLICANT    ");
			System.out.println("########################### ##############");
			System.out.println("              GET YOUR OPTION           ");
			System.out.println("##########################################");
			System.out.println("1. PRESS 1 TO INSERT STUDENT_APPLICANT.");
			System.out.println("2. PRESS 2 TO CHECK LOCATION OF THE JOB.");
			System.out.println("3. PRESS 3 TO CHECK REQUIRED SKILL TO JOB.");
			System.out.println("4. PRESS 4 GET THE SKILLS CLASSIFICATION.");
			System.out.println("5. PRESS 5 TO CHECK STUDENT APPLIED JOB.");
			System.out.println("6. PRESS 6 To Exit !!");
			System.out.println("##########################################");

			try { //try block begins
				inputEntered = Integer.valueOf(br.readLine());

			}///end of try
			catch (Exception ex){
				System.out.println("Caught SQL Exception: \n " + ex);
				System.out.println("You entered a wrong input. Please select options from 1 to 5");
				getstudentActions();
			}//end of catch
			switch(inputEntered){
			case 1:  insertingtostudent_applicant();
			break;
			case 2:  jobareaandlocation();
			break;
			case 3:  reqskilltojob();
			break;
			case 4 :  classifiedskills();
			break;
			case 5:  studentappliedjob();
			break;
			case 6:  System.out.println("Que te vaya bien (Have a nice day in Spanish)");
			break;
			
			default :  System.out.println("choose the correct option");
			break;
			}
		}while(inputEntered!=6);

		inputSelection();
	}


	public static void insertingtostudent_applicant() throws Exception
	{

		int s_id;
		BigInteger phone;
		String sname, sbranch,saddress, semail;


		br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("PROVIDE THE STUDENT_APPLICANT INFORMATION");
		System.out.println("GIVE THE STUDENT ID : [Ex: 415 (3 NUMBERS ONLY)]");
		s_id = Integer.valueOf(br.readLine());

		System.out.println("STUDENT NAME PLEASE : [Ex: MARTIN (10 CHARECTER ONLY)]");
		sname = br.readLine();

		System.out.println("STUDENT BRANCH : [Ex: AERO (10 CHARECTER ONLY)]");
		sbranch = br.readLine();

		System.out.println("STUDENT ADDRESS (CITY NAME) : [Ex: PEORIA (10 CHARECTER ONLY)] ");
		saddress = br.readLine();

		System.out.println("EMAIL ADDRESS OF THE STUDENT : [Ex: MAR@GMAIL (10 CHARECTER ONLY)] ");
		semail = br.readLine();

		System.out.println("PHONE NUMBER OF THE STUDENT PLEASE : [Ex: 8143564398]");
		phone= new BigInteger(br.readLine());

		String SQLQuery= " INSERT INTO student_applicant " +
				" VALUES ("+s_id+"," +
				"'"+sname+"',"+
				"'"+saddress+"'," +
				"'"+sbranch+"'," +					
				"'"+semail+"'," +
				phone+")";
		System.out.println(SQLQuery);

		stmt.executeUpdate(SQLQuery);

		System.out.println("-------------------------------------");
		System.out.println("INSERTED INTO STUDENT_APPLICANT TABLE");
		System.out.println("-------------------------------------");
		conn.commit();

	}

	public static void jobareaandlocation() throws Exception{
		try {
			int rid;
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("ENTER THE R_ID  [Ex: 42]");
			rid=Integer.parseInt(br.readLine());
			String SQLQuery= " SELECT R_ID, RNAME, "+
					" RLOCATION "+
					" FROM JOB_REQUIREMENT "+
					" WHERE R_ID="+rid;

			System.out.println(SQLQuery);

			ResultSet rset=stmt.executeQuery(SQLQuery);

			ResultSetMetaData rsmd= rset.getMetaData();
			int colCount=rsmd.getColumnCount();
			for (int i=1;i<=colCount;i++){
				System.out.print(rsmd.getColumnName(i)+ "\t \t \t");

			}
			System.out.println();
			System.out.println("-----------------------------------------------------------");

			//logic for printing the Rows of the table
			while (rset.next()){
				int countCal=rsmd.getColumnCount();
				for (int i=1;i<=countCal;i++)
					System.out.print(rset.getString(i)+ "\t \t");
				System.out.println();
			}
			System.out.println("-----------------------------------------------------------");

			//closing the result set
			rset.close();
		} 

		catch(Exception e){
			e.printStackTrace();
		}
	}



	public static void reqskilltojob() throws Exception{

		int r_id;
		br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("GIVE R_ID [EX : 42]");
		r_id=Integer.valueOf(br.readLine());


		String SQLQuery= " SELECT SKILLSET.SSNAME, "+
				" JOB_REQUIREMENT.RNAME "+
				" FROM SKILLSET, "+
				" REQUIRED_SKILLSET, "+
				" JOB_REQUIREMENT "+
				" WHERE  JOB_REQUIREMENT.R_ID = REQUIRED_SKILLSET.R_ID "+
				" AND REQUIRED_SKILLSET.SS_ID = SKILLSET.SS_ID "+
				" AND JOB_REQUIREMENT.R_ID ="+r_id; 

		ResultSet rset = stmt.executeQuery(SQLQuery);

		System.out.printf("%-13s%-22s \n ","SSNAME", "RNAME");

		System.out.printf("------------------------------\n");

		while(rset.next()){
			String ssname = rset.getString("ssname");
			String rname  = rset.getString("Rname");
			System.out.printf("%-13s%-22s \n",ssname,rname);
		}
		rset.close();


	}


	public static void classifiedskills() throws Exception{

		String SQLQuery= "SELECT  SSNAME, "+
				"COUNT(Skillset.ss_id) AS"+" Total#ofApplicants"+","+ 
				"DECODE(COUNT(*), "+
				" 1, 'Tough Skill', "+
				" 2, 'Intermediate Skill', "+
				" 3, 'Top Skill', "+
				" 'Most Available') classification "+
				" FROM student_applicant student, "+ 
				" has_skillset, "+ 
				" skillsET "+
				" WHERE    has_skillset.s_id=student.s_id \n "+
				" AND      skillset.ss_id=has_skillset.ss_id \n "+
				" GROUP BY skillset.ssname";

		System.out.println(SQLQuery);

		ResultSet rset=stmt.executeQuery(SQLQuery);

		ResultSetMetaData rsmd= rset.getMetaData();
		int colCount=rsmd.getColumnCount();
		for (int i=1;i<=colCount;i++){
			System.out.print(rsmd.getColumnName(i)+ "\t \t");

		}
		System.out.println();
		System.out.println("-----------------------------------------------------------");

		//logic for printing the Rows of the table
		while (rset.next()){
			int countCal=rsmd.getColumnCount();
			for (int i=1;i<=countCal;i++)
				System.out.print(rset.getString(i)+ "\t \t");
			System.out.println();
		}
		System.out.println("-----------------------------------------------------------");

		//closing the result set
		rset.close();

	}


	public static void studentappliedjob() throws Exception{

		int rid;
		br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("PLEASE ENTER R_ID FOR WHICH STUDENT APPLIED: [Ex: 45]");
		rid=Integer.valueOf(br.readLine());

		String SQLQuery= " SELECT STUDENT_APPLICANT.SNAME, "+
				" STUDENT_APPLICANT.SEMAIL, "+
				" INTERVIEW.INAME "+
				" FROM STUDENT_APPLICANT, APPLIED_BY_STUDENT, INTERVIEW "+
				" WHERE APPLIED_BY_STUDENT.S_ID=STUDENT_APPLICANT.S_ID "+
				" AND STUDENT_APPLICANT.S_ID=INTERVIEW.S_ID "+
				" AND APPLIED_BY_STUDENT.R_ID=" +rid+ "\n"+
				" GROUP BY STUDENT_APPLICANT.SNAME, "+
				" STUDENT_APPLICANT.SEMAIL, "+
				" INTERVIEW.INAME ";

		System.out.println(SQLQuery);

		ResultSet rset = stmt.executeQuery(SQLQuery);

		System.out.printf("%-13s%-22s%-14s \n","STUDENTNAME","STUDENTEMAIL","INAME");

		System.out.printf("-----------------------------------------------------\n");

		while(rset.next()){
			String Sname  = rset.getString("Sname");
			String semail = rset.getString("semail");
			String iname = rset.getString("iname");

			System.out.printf("%-13s%-22s%-14s \n",Sname,semail,iname);
		}
		rset.close();
	}

}//end of class