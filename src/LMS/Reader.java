package LMS;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Reader extends Person {
     
    private String UserBlockCheker;
    
    
    private void rentBook(){
        try{
            String Q1 ="select isblocked  from "+userTableName+" where ID = "+this.ID+" ";
            
            resultSet = statement.executeQuery(Q1);
                       
            while(resultSet.next())
            {              
                 UserBlockCheker = resultSet.getString("isblocked");        
            }
            

            if(UserBlockCheker.equals("false") )
            {
            System.out.print("Enter book name : ");
            String  N = s.next();
            String Q ="select * from  Books where book_name = '" + N + "' ";
            resultSet = statement.executeQuery(Q);
      
            while(resultSet.next())
            {
               
            String name = resultSet.getString("book_name");
            System.out.println("book name : "+ name);
            
            String LNAME = resultSet.getString("author");     
            System.out.println("author name :"+LNAME);
                                  
            String price = resultSet.getString("price");     
            System.out.println("price :"+Float.valueOf(price));
             
            int ID = resultSet.getInt("ID");     
            System.out.println("ID :"+ID);
            
            String status = resultSet.getString("is_rented");     
            System.out.println("Is rented : "+status);
            
            if (status.equals("false"))
            {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                System.out.println("to rent the book press 1");
                int X = s.nextInt();
                
                 if( X == 1)
                {                     
               System.out.println("enter book ID :");
               int bID = s.nextInt();
               
                    Calendar cal = Calendar.getInstance();
                    Calendar cal1 = Calendar.getInstance();                 
                    cal1.add(Calendar.DATE, 14);
                    
                    System.out.println("You rent the book at : " +  dateFormat.format(cal.getTime()) );   
                    String AT = dateFormat.format(cal.getTime());
                    System.out.println("you must return this book after 14 day ");
                    System.out.println("At : "+ dateFormat.format(cal1.getTime()));
                    String rAT = dateFormat.format(cal1.getTime());                   
                    statement.executeUpdate("UPDATE books SET is_rented = true where ID = "+ID+"");                    
                    statement.executeUpdate("UPDATE "+userTableName+" SET RENTED_BOOK = '"+N+"' , BOOK_RENTED_AT = '"+AT+"' , BOOK_RETURN_AT = '"+rAT+"' , RENTED_BOOK_ID = "+bID+" where ID = "+this.ID+"");
                    System.out.println("the book has been rented successfully ");
                    
                }
             
            }
            else 
            {
                System.out.println("the book is not available");
            }
            }
        }else{
                System.out.println("Sorry you can't rent books");
            }
        }
        catch(Exception e ){
            System.out.println("rent book"+e);
        }
    }
    
    private void orderList(){           
        String rented_book;
        try{
            System.out.println("YOUR ORDER LIST ");

            String Q ="select * from "+userTableName+" where id = "+this.ID+"";
            resultSet = statement.executeQuery(Q);
      
            while(resultSet.next()){
                rented_book = resultSet.getString("rented_book");
                if(rented_book == null){
                    System.out.println("you dont have any book");
                }else{
                System.out.println("Your order list:"+rented_book);
                }
            }       
        }catch(Exception e ){
            System.out.println("Order_list"+e);
        }
    }
    
    @Override
    public void readerSearch(){
        try{
        super.readerSearch();
              
            while(resultSet.next()){
               
            String Fname = resultSet.getString("name");
            System.out.println("name : "+ Fname);
            
            String LNAME = resultSet.getString("lname");     
            System.out.println("last name :"+LNAME);
                       
             
            String rented_book = resultSet.getString("rented book");     
            System.out.println("rented books :"+rented_book);
                        
            }
        }catch(Exception e){           
            System.out.println("Reader search Erorr" + e);
        }
    } 
    
    @Override
    public void login(){
        do{
        super.login();    
        if(validIdAndPassword == true){
             boolean end = false;
        
        do{
        System.out.println("press #1 to login as reader");
        System.out.println("press #2 to login as librarian");
        System.out.println("press #0 to exit");
        String choice = s.nextLine();
        
        switch(choice){
            case "1":{
     
                break;
            }
            
            case "2":{
   
                break;
            }
            case "0":{
                end = true;
                break;
            }
        
            default:{
                System.out.println("Option not found !!");
                break;          
            }
        }
        }while(end == false);          
        }
        }while(validIdAndPassword == false);
    }
}
