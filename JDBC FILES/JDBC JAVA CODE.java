package javatest;

//Osman Khan
// Gustavo Cisernos
// March 17, 2020
// CECS 323

import java.sql.*;
import java.util.*;
public class JAVATEST
{ 
   static Scanner in = new Scanner(System.in);
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
   static final String DB_URL = "jdbc:derby://localhost:1527/JDBC REAL";

   public static void main(String[] args) 
   {
   Connection conn = null;
   Statement stmt = null;
   try
   {
      Class.forName(JDBC_DRIVER);

      conn = DriverManager.getConnection(DB_URL);

      stmt = conn.createStatement();
      int choice = 0; 
      
      while (choice != 10)
       {
           System.out.println("Menu For JDBC");
           System.out.println("1): List all Writing Groups");
           System.out.println("2): List all the data for a specified group");
           System.out.println("3): List all publishers");
           System.out.println("4): List all the data for a specified publisher");
           System.out.println("5): List all book titles");
           System.out.println("6): List the Data for a single book");
           System.out.println("7): Insert a new book");
           System.out.println("8): Insert a new publisher and update all books with new publisher");
           System.out.println("9): Remove a specific book");
           System.out.println("10): Quit");
           System.out.println("Enter you choice: ");
           
           try 
           {
               choice = in.nextInt();
           }
           
           catch(InputMismatchException e)
           {
               System.out.println("Not a menu option! \nTry Again!\n");
               System.out.println("Menu For JDBC");
               System.out.println("1): List all Writing Groups");
               System.out.println("2): List all the data for a specified group");
               System.out.println("3): List all publishers");
               System.out.println("4): List all the data for a specified publisher");
               System.out.println("5): List all book titles");
               System.out.println("6): List the Data for a single book");
               System.out.println("7): Insert a new book");
               System.out.println("8): Insert a new publisher and update all books with new publisher");
               System.out.println("9): Remove a specific book");
               System.out.println("10): Quit");
               System.out.println("Enter you choice: ");
               in.next();
               choice = in.nextInt();
           }
            switch (choice) 
            {
                case 1: //Display all Writing Group Names
                 String sql;
                 sql = "SELECT GroupName FROM WritingGroups";
                 ResultSet rs1 = stmt.executeQuery(sql);
                 System.out.println("\nDisplaying Writing Groups:");
                 
                 while(rs1.next())
                 {
                  String groupname  = rs1.getString("GroupName");
                  System.out.println(groupname);
                 }
                 System.out.print("\n");
                 rs1.close();
                 break;
                 case 2: //Displaying all data for Writing Group
                  String sql_disp = "SELECT GroupName FROM WritingGroups";
                  ResultSet result = stmt.executeQuery(sql_disp);
                  System.out.println("\nDisplaying Writing Groups:");
                 
                  while(result.next())
                  {
                    String groupname  = result.getString("GroupName");
                    System.out.println(groupname);
                  }
                  System.out.print("\n");
                  result.close();

                  System.out.println("Enter the name of the writing group");
                  in.nextLine();
                  String group = in.nextLine();
                  String sql_2 = "SELECT * FROM WritingGroups NATURAL JOIN Publishers NATURAL JOIN Books WHERE GroupName = ?";
               
                  PreparedStatement PSMT2 = conn.prepareStatement(sql_2,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                  PSMT2.setString(1, group);
                  ResultSet rs2 = PSMT2.executeQuery();
                  
                  if(!rs2.next())
                  {
                      System.out.println("There is no Writing Group in the table named " + group + "\n" + "Returing to main menu...\n" );
                  }
                  else
                  {
                      rs2.beforeFirst();
                      while(rs2.next())
                      {

                          String groupname = rs2.getString("GroupName");
                          String headwriter = rs2.getString("HeadWriter");
                          String yearformed = rs2.getString("YearFormed");
                          String subject = rs2.getString("Subject");
                          String booktitle = rs2.getString("BookTitle");
                          String pubname = rs2.getString("PublisherName");
                          String yearpub = rs2.getString("YearPublished");
                          String numpages = rs2.getString("NumberPages");
                          String pubadd = rs2.getString("PublisherAddress");
                          String pubphone = rs2.getString("PublisherPhone");
                          String pubemail = rs2.getString("PublisherEmail");

                          System.out.println("Group Name: " + groupname);
                          System.out.println("Head Writer: " + headwriter);
                          System.out.println("Year Formed: " + yearformed);
                          System.out.println("Subject: " + subject);
                          System.out.println("Book Title: " + booktitle);
                          System.out.println("Publisher Name: " + pubname);
                          System.out.println("Year Published: " + yearpub);
                          System.out.println("Number of Pages: " + numpages);
                          System.out.println("Publisher Address: " + pubadd);
                          System.out.println("Publisher Phone: " + pubphone);
                          System.out.println("Publisher Email: " + pubemail + "\n");

                      }
                  }
                  rs2.close();
                  break;
                 case 3: //List all Publishers
                   String sql_3;
                   sql_3 = "SELECT PublisherName FROM Publishers";
                   ResultSet rs3 = stmt.executeQuery(sql_3);
                   System.out.println("Displaying Publisher Names:");
                   
                   while(rs3.next())
                   {
                    String pubname  = rs3.getString("PublisherName");
                    System.out.println(pubname);
                   }
                   System.out.print("\n");
                   rs3.close();
                  break;
                 case 4: //List all the Data for a specified publisher
                      String sql_12;
                      sql_12 = "SELECT PublisherName FROM Publishers";
                      ResultSet rs12 = stmt.executeQuery(sql_12);
                      System.out.println("Displaying Publisher Names:");
                   
                      while(rs12.next())
                      {
                       String pubn  = rs12.getString("PublisherName");
                       System.out.println(pubn);
                      }
                      System.out.print("\n");
                      rs12.close();
                      
                      System.out.println("Enter the name of the Publisher you are searching for:");
                      in.nextLine();
                      System.out.println("PublisherName: ");
                      String pubname = in.nextLine();
              
                      String sql_4 = "SELECT * from Publishers NATURAL JOIN Books NATURAL JOIN WritingGroups WHERE PublisherName = ?";
                      PreparedStatement pstmt_4 = conn.prepareStatement(sql_4, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                      pstmt_4.setString(1, pubname);
                      ResultSet rs4 = pstmt_4.executeQuery();
                      
                     if(!rs4.next())
                      {
                          System.out.println("There are no Publishers in the table named " + pubname + "\n" +
                          "Or the table has no books and writing groups associated" + " returing to main menu...\n");
                      }
                     else{
                         rs4.beforeFirst();
                         System.out.println("Displaying Data for Publisher\n");
                         while(rs4.next())
                         {
                             String pubNAME = rs4.getString("PublisherName");
                             String GNAME = rs4.getString("GroupName");
                             String pubAddy = rs4.getString("PublisherAddress");
                             String pubPhone = rs4.getString("PublisherPhone");
                             String pubEmail = rs4.getString("PublisherEmail");
                             String BookTitle = rs4.getString("BookTitle");
                             String yrPub = rs4.getString("YearPublished");
                             String pageNum = rs4.getString("NumberPages");
                             String HeadWriter = rs4.getString("HeadWriter");
                             String yFormed = rs4.getString("YearFormed");
                             String sub = rs4.getString("Subject");
                             System.out.print("PublisherName: " + pubNAME + "\nGroupName: " + GNAME 
                             + "\nPublisherAddress: " + pubAddy + "\nPublisherPhone: " + pubPhone
                             + "\nPublisherEmail: " + pubEmail + "\nBookTitle: " + BookTitle
                             + "\nYearPublished: " + yrPub + "\nNumberPages: " + pageNum 
                             + "\nHead Writer: " + HeadWriter + "\nYearFormed: " + yFormed
                             + "\nSubject: " + sub + "\n\n");
                         }
                     }
                      rs4.close();
                   break;
                 case 5: //List all Book Titles
                   String sql_5;
                   sql_5 = "SELECT BookTitle FROM Books";
                   ResultSet rs_5 = stmt.executeQuery(sql_5);
                   System.out.println("Displaying Book Titles:");
                   
                   while(rs_5.next())
                   {
                    String booktitle  = rs_5.getString("BookTitle");
                    System.out.println(booktitle);
                   }
                   System.out.println("\n");
                   rs_5.close();
                  break;
                 case 6: //List Data for a single book
                    System.out.println("Names of the Writing Groups: \n");
                    System.out.println("CoronaChan Writing Group");
                    System.out.println("Big Homie Writing Coalition");
                    System.out.println("A Pimp Named Slickback Writing");
                    System.out.println("FGC Writing Corporation");
                    System.out.println("CSULB Writing");
                    System.out.println("Flat Earth Writing");
                    System.out.println("Cooking Gods \n");
                    System.out.println("Enter the WritingGroup Name: ");

                    in.nextLine();
                    String groupname = in.nextLine();
                    String sql_11;
                    sql_11 = "SELECT BookTitle FROM Books WHERE GroupName = ?";
                   
                    PreparedStatement PSMT_name = conn.prepareStatement(sql_11, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    PSMT_name.setString(1, groupname);
                    ResultSet rs_11 = PSMT_name.executeQuery();
                   
                    if(rs_11.next())
                    {
                        rs_11.beforeFirst();
                        System.out.println("Displaying Book Titles:");
                        while(rs_11.next())
                        {
                         String bookt  = rs_11.getString("BookTitle");
                         System.out.println(bookt);
                        }
                        System.out.print("\n");
                        rs_11.close();
                        
                        System.out.println("Enter the title of the Book: ");
                        String book = in.nextLine();


                        String sql_6 = "SELECT * FROM Books NATURAL JOIN Publishers NATURAL JOIN WritingGroups WHERE " +
                        "GroupName = ? AND BookTitle = ?"; 
                        PreparedStatement PSMT6 = conn.prepareStatement(sql_6,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                        PSMT6.setString(1,groupname);
                        PSMT6.setString(2,book);
                        ResultSet rs6 = PSMT6.executeQuery();
                        if(!rs6.next())
                        {
                            System.out.println("There are no books titled " + book + " related to " + groupname + "\n" + "Returing to main menu...\n");
                        }
                        else
                        {
                           rs6.beforeFirst();
                           while(rs6.next())
                            {
                              String group_5 = rs6.getString("GroupName");
                              String headwriter = rs6.getString("HeadWriter");
                              String yearformed = rs6.getString("YearFormed");
                              String subject = rs6.getString("Subject");
                              String booktitle = rs6.getString("BookTitle");
                              String pubname6 = rs6.getString("PublisherName");
                              String yearpub = rs6.getString("YearPublished");
                              String numpages = rs6.getString("NumberPages");
                              String pubadd = rs6.getString("PublisherAddress");
                              String pubphone = rs6.getString("PublisherPhone");
                              String pubemail = rs6.getString("PublisherEmail"); 

                              System.out.println("\nGroup Name: " + group_5);
                              System.out.println("Head Writer: " + headwriter);
                              System.out.println("Year Formed: " + yearformed);
                              System.out.println("Subject: " + subject);
                              System.out.println("Book Title: " + booktitle);
                              System.out.println("Publisher Name: " + pubname6);
                              System.out.println("Year Published: " + yearpub);
                              System.out.println("Number of Pages: " + numpages);
                              System.out.println("Publisher Address: " + pubadd);
                              System.out.println("Publisher Phone: " + pubphone);
                              System.out.println("Publisher Email: " + pubemail + "\n");
                            } 
                        }

                        rs6.close();
                    }
                    else
                    {
                       System.out.println("There is no group named " + groupname + "\n"); 
                    }
                    break;
                 case 7: //Insert a new book
                     System.out.println("Enter the book information\n");
                     String gnames = "SELECT GroupName FROM WritingGroups";
                     ResultSet rs_gname = stmt.executeQuery(gnames);
                     System.out.println("Select from these WritingGroups ");
                     while(rs_gname.next())
                     {
                        String gname_disp  = rs_gname.getString("GroupName");
                        System.out.println(gname_disp);
                     }
                     rs_gname.close();
                     
                     in.nextLine();
                     System.out.println("GroupName: ");
                     String GroupName = in.nextLine();
                     
                     String group_tst = "SELECT GroupName FROM WritingGroups WHERE GroupName = ?";
                     PreparedStatement PSMT_GNAME = conn.prepareStatement(group_tst, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                     PSMT_GNAME.setString(1,GroupName);
                     ResultSet rs_GNAME = PSMT_GNAME.executeQuery();
                     
                     if(!rs_GNAME.next())
                     {
                         System.out.println("There is no Group named " + GroupName+ "\nReturing to menu...");
                     }
                     else
                     {
                         String sql_pub;
                         sql_pub = "SELECT PublisherName FROM Publishers";
                         ResultSet rs_pub = stmt.executeQuery(sql_pub);
                         System.out.println("Select from these Publisher Names:");

                         while(rs_pub.next())
                         {
                           String pubn = rs_pub.getString("PublisherName");
                           System.out.println(pubn);
                         }
                         System.out.print("\n");
                         rs_pub.close();
                         
                         System.out.println("PublisherName: ");
                         String PublisherName = in.nextLine();
                         
                         String pub_tst = "SELECT PublisherName FROM Publishers WHERE PublisherName = ?";
                         PreparedStatement PSMT_PNAME = conn.prepareStatement(pub_tst, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                         PSMT_PNAME.setString(1, PublisherName);
                         ResultSet rs_PNAME = PSMT_PNAME.executeQuery();
                         
                         if(!rs_PNAME.next())
                         {
                             System.out.println("There are no Publishers named " + PublisherName + "\nReturing to menu...");
                         }
                         else
                         {
                            System.out.println("BookTitle: ");
                            String BookTitle = in.nextLine();

                            String sql_tst = "SELECT * FROM Books WHERE BookTitle = ? AND GroupName = ?";
                            PreparedStatement PSMT_tst = conn.prepareCall(sql_tst);
                            PSMT_tst.setString(1, BookTitle);
                            PSMT_tst.setString(2, GroupName); 
                            ResultSet rs_tst = PSMT_tst.executeQuery();

                            if(rs_tst.next())
                            {
                                System.out.println("The book title " + BookTitle + " and the group name " + GroupName + " are already taken!");
                                System.out.println("Returning to menu...");
                            }
                            else
                            {
                              int YearPublished, NumberPages;
                              System.out.println("YearPublished: ");
                              try
                              {
                                 YearPublished = in.nextInt(); 
                              }
                              catch(InputMismatchException e) 
                              {
                                 System.out.println("Error!\nPlease type in an int value: ");
                                 in.next();
                                 YearPublished = in.nextInt();
                              }

                              System.out.println("NumberPages: ");
                              try
                              {
                                 NumberPages = in.nextInt(); 
                              }
                              catch(InputMismatchException e) 
                              {
                                 System.out.println("Error!\nPlease type in an int value: ");
                                 in.next();
                                 NumberPages = in.nextInt();
                              }

                              PreparedStatement PSMT;
                              String sql_7 = "INSERT INTO Books (GroupName, BookTitle, PublisherName, YearPublished, NumberPages) " 
                                 + "VALUES(?,?,?,?,?)";
                              PSMT = conn.prepareStatement(sql_7,Statement.RETURN_GENERATED_KEYS);
                              PSMT.setString(1, GroupName);
                              PSMT.setString(2, BookTitle);
                              PSMT.setString(3, PublisherName);
                              PSMT.setInt(4, YearPublished);
                              PSMT.setInt(5, NumberPages);
                              PSMT.executeUpdate();
                              System.out.println("Inserted into table!");     
                            }
                            rs_tst.close();
                         }
                         rs_PNAME.close(); 
                     }
                     rs_GNAME.close();
                  break;
                 case 8: //Insert a publisher
                     in.nextLine();
                     String sql_10;
                     sql_10 = "SELECT PublisherName FROM Publishers";
                     ResultSet rs10 = stmt.executeQuery(sql_10);
                     System.out.println("Displaying Publisher Names:");

                     while(rs10.next())
                     {
                       String pub  = rs10.getString("PublisherName");
                       System.out.println(pub);
                     }
                     System.out.print("\n");
                     rs10.close();

                     System.out.println("Which of these Publishers would you liked to be replaced by the new Publisher?");
                     System.out.println("Enter old Publisher Name: ");
                     String OldPubName = in.nextLine(); 
                    
                     String sql_test = "SELECT * FROM Publishers WHERE PublisherName = ?";
                     PreparedStatement PSMT_test = conn.prepareStatement(sql_test,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                     PSMT_test.setString(1, OldPubName);
                     ResultSet rs_test = PSMT_test.executeQuery();
                     if(!rs_test.next())
                     {
                         System.out.println("The Publisher Name " + OldPubName + " already exists!");
                     } 
                     else
                     {
                       rs_test.close();
                     
                        System.out.println("Enter new Publisher information ");
                        System.out.println("New PublisherName: ");
                        String NewPubName = in.nextLine(); //1

                        String Exists = "SELECT * FROM Publishers WHERE PublisherName = ?";
                        PreparedStatement PSMT_exists = conn.prepareStatement(Exists,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                        PSMT_exists.setString(1, NewPubName);
                        ResultSet rs_exists = PSMT_exists.executeQuery();
                        if(rs_exists.next())
                        {
                            System.out.println("The Publisher Name " + NewPubName + " already exists!");
                        }
                        else
                        {
                           rs_exists.close();
                           System.out.println("New PublisherAddress: ");
                           String NewPubAddr = in.nextLine(); //2
                           System.out.println("New PublisherPhone: ");
                           String NewPubPhone = in.nextLine(); //3
                           System.out.println("New PublisherEmail: ");
                           String NewPubEmail = in.nextLine(); //4

                           PreparedStatement PSMT8;
                           String sql_8 = "INSERT INTO Publishers (PublisherName, PublisherAddress, PublisherPhone, PublisherEmail) "
                                          + "VALUES(?,?,?,?)";

                           PSMT8 = conn.prepareStatement(sql_8,Statement.RETURN_GENERATED_KEYS);
                           PSMT8.setString(1, NewPubName);
                           PSMT8.setString(2, NewPubAddr);
                           PSMT8.setString(3, NewPubPhone);
                           PSMT8.setString(4, NewPubEmail);
                           PSMT8.executeUpdate();

                           String sql_update = "UPDATE Books SET PublisherName = ? WHERE PublisherName = ?";
                           PreparedStatement PSMT10;
                           PSMT10  = conn.prepareStatement(sql_update,Statement.RETURN_GENERATED_KEYS);
                           PSMT10.setString(1, NewPubName);
                           PSMT10.setString(2, OldPubName);
                           PSMT10.executeUpdate();

                           String sql_13 = "SELECT * FROM Books WHERE PublisherName = ?";
                           PreparedStatement PSMT11 = conn.prepareStatement(sql_13,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                           PSMT11.setString(1, NewPubName);
                           ResultSet rs13 = PSMT11.executeQuery();

                           if(!rs13.next())
                           {
                               System.out.println("Incorrect Publisher Named picked!");
                           }
                           else
                           {
                               rs13.beforeFirst();
                               while(rs13.next()) 
                               {
                                   String group_5 = rs13.getString("GroupName");
                                   String booktitle = rs13.getString("BookTitle");
                                   String pubname6 = rs13.getString("PublisherName");
                                   String yearpub = rs13.getString("YearPublished");
                                   String numpages = rs13.getString("NumberPages");

                                   System.out.println("\nGroup Name: " + group_5);
                                   System.out.println("Book Title: " + booktitle);
                                   System.out.println("Publisher Name: " + pubname6);
                                   System.out.println("Year Published: " + yearpub);
                                   System.out.println("Number of Pages: " + numpages + "\n");
                               }
                           }                       
                           rs13.close();
                        }  
                     }
                  break;
                 case 9: //Remove a book 
                     String sql_9 = "DELETE FROM Books WHERE GroupName = ? AND BookTitle = ?";
                     String showBooks = "SELECT BookTitle FROM Books where GroupName = ?";
                     String showPubs = "SELECT GroupName From WritingGroups";
                     Statement show_p = conn.createStatement();
                     ResultSet pubs = show_p.executeQuery(showPubs);
                     
                     System.out.println("List of writing groups:\n");
                     while(pubs.next())
                     {
                         String writeGroup = pubs.getString("GroupName");
                         System.out.println(writeGroup);
                     }
                     in.nextLine();
                     System.out.println("\nEnter the Writing Group Name: ");
                     GroupName = in.nextLine();
                     
                     PreparedStatement showBookTitles = conn.prepareStatement(showBooks);
                     showBookTitles.setString(1, GroupName);
                     ResultSet rs9 = showBookTitles.executeQuery();
                     System.out.println("Showing list of books for " + GroupName + "\n");
                     while(rs9.next())
                     {
                         String bTitle = rs9.getString("BookTitle");
                         System.out.println(bTitle);
                     }
                  
                     System.out.println("\nEnter the name of the book: ");
                     String BookTitle = in.nextLine();
                     
                     PreparedStatement psmt_9 = conn.prepareStatement(sql_9);
                     psmt_9.setString(1, GroupName);
                     psmt_9.setString(2, BookTitle);
                     int rowsAffected = psmt_9.executeUpdate();
                     
                     if(rowsAffected !=0){
                        System.out.println("Successful deletion of " + BookTitle + "\n");
                        System.out.println("List of books under " + GroupName + " left:");
                        showBookTitles = conn.prepareStatement(showBooks);
                         showBookTitles.setString(1, GroupName);
                        rs9 = showBookTitles.executeQuery();
                        while(rs9.next())
                        {
                            String bTitle = rs9.getString("BookTitle");
                            System.out.println(bTitle);
                        }
                        System.out.print("\n");
                     }
                     else
                         System.out.println("No book found under the name " + BookTitle + ". Try again!\n");
                     pubs.close();
                     rs9.close();
                  break;
                 case 10:
                  System.out.println("\nQuitting Menu!");
                  break;
                 default:
		  System.out.println("Incorrect choice: Try again!\n");
             }
         }
      stmt.close();
      conn.close(); 
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
}//end main
}//end 

