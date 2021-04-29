
package LMS;

import java.sql.*;

import java.util.Scanner;

public abstract class Person {

    protected  final String userTableName = "READERS";
    

   protected Connection connection ;
   protected  Statement statement ;       
   protected ResultSet resultSet;
                
   protected Scanner s = new Scanner(System.in);  
   protected boolean validIdAndPassword ;     
   protected String userName , userPassword;
   protected int ID , searchByID ;

 

    
    public Person() {
                
        try{
            this.connection = DriverManager.getConnection("jdbc:derby://localhost:1527/LMS","lms","lms");
            this.statement = connection.createStatement();
            this.resultSet = null;        
        }
        catch(Exception e){
            System.out.println("userSeaerch Erorr" + e);
        }
        this.userName = null;
        this.ID = -1;
        this.validIdAndPassword = false;
    }

    protected void login(){
                
        System.out.println("Enter ID ");       
        this.ID = s.nextInt();         
        
        System.out.println("Enter password ");       
        String pass = s.next();
        try{            
            
            String Q ="SELECT NAME , password from "+userTableName+" WHERE ID = "+ this.ID +"";
            resultSet = statement.executeQuery(Q);
          
            while(resultSet.next()){
                 this.userName = resultSet.getString("name");
                 this.userPassword = resultSet.getString("password");
            }    
        }catch(Exception e){
            System.out.println("LOGIN EXCEPTION" + e);
        }
        
            if(userPassword == null){           
                System.out.println("Invalid ID or Password");
                validIdAndPassword = false;     
          
            }
            else if (userPassword.equals(pass)){                          
                System.out.println("@Welcom "+ userName );
                validIdAndPassword = true;
           
            }
            else{
                System.out.println("Invalid ID or Password");
            }
   
        }
    

    protected void readerSearch(){
                    
        System.out.println("Enter user ID : "); 
        searchByID = s.nextInt();
        
        try{          
            String Q ="select * from "+userTableName+" where ID = "+searchByID+" ";
            resultSet = statement.executeQuery(Q);
        
        }catch(Exception e){
            System.out.println("userSeaerch Erorr" + e);
        }
    }  
    
    protected void bookSearch(){           
                   
        System.out.println("Enter book name : ");
        String bname = s.next();
        
        try{
            String Q ="select * from BOOKS where book_name = '" + bname + "'";
            resultSet = statement.executeQuery(Q);
      
            while(resultSet.next()){
              
            System.out.println("Book Title : "+ resultSet.getString("book_name"));
            System.out.println("Author Name :"+resultSet.getString("author"));               
            System.out.println("Serial Number :"+resultSet.getInt("id"));   
            System.out.println("price :"+resultSet.getString("price"));  
            System.out.println("Is rented : "+resultSet.getString("is_rented"));
           
            }
        }catch(Exception e){
            System.out.println("Book search Erorr" + e);
        }
        
    }   
}
