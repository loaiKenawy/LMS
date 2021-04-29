
package LMS;

public class librarian extends Person{

    private void addReader(){
        
           try{
              
               System.out.println("enter Fname :");
               String FNAME = s.next();
              
               System.out.println("enter Lname :");
               String LNAME = s.next();
               
               System.out.println("enter phone :");
               int CELLPOHONE = s.nextInt();
             
               System.out.println("enter type :");
               String TYPE = s.nextLine();
            
               System.out.println("enter Adress:");
               String ADDRESS = s.nextLine();
               
               System.out.println("enter email:");
               String EMAIL = s.next();
               
               System.out.println("enter Password:");
               String PASSWORD = s.next();
 
               System.out.println("your ID :"+ CELLPOHONE);

                      
              statement.executeUpdate("INSERT INTO "+userTableName+" " + "VALUES ('"+FNAME+"' , "+CELLPOHONE+",'"+LNAME+"','"+PASSWORD+"',"+CELLPOHONE+",'"+ADDRESS+"','"+EMAIL+"','"+TYPE+"' ,false , null , null ,null ,null)");    
           
           }catch(Exception e){          
            System.out.println("Erorr->1"+e);
           }
    }

    private void removeReader(){
    
    try{
            System.out.println("Enter user ID : ");
            int id = s.nextInt();
          
            String Q ="select * from "+userTableName+" where ID = "+id+" ";
            resultSet = statement.executeQuery(Q);
      
            while(resultSet.next()){
               
            String Fname = resultSet.getString("name");
            System.out.println("name : "+ Fname);
            
            String LNAME = resultSet.getString("lname");
            System.out.println("last name :"+LNAME);
            
                       
            String phone = resultSet.getString("cellphone");
            System.out.println("Phone :"+phone);
                                
               
            System.out.println("Press #1 to remove user");      
           
            System.out.println("Press #0 to cancel");
            
            int x =s.nextInt();
                
                if(x == 1){
            
                    statement.executeUpdate("DELETE FROM "+userTableName+" WHERE id = "+id+"");    
                    System.out.println("Reader has been removed !!");
            }else{
                    
                }
            }
           }catch(Exception e){          
            System.out.println("removeReader "+e);
           }
    }
    
    private void addBook(){
        
           try{
               System.out.println("enter book name :");
               String FNAME = s.next();
              
               System.out.println("enter autor name :");
               String aNAme = s.next();
               
               System.out.println("enter book id :");
               int ID = s.nextInt();
             
               System.out.println("enter price :");
               float price  = s.nextFloat();
               
               System.out.println("enter category :");
               String cat = s.next();
               statement.executeUpdate("INSERT INTO BOOKS " + "VALUES ('"+FNAME+"' , "+ID+",'"+aNAme+"',"+price+",'"+cat+"',false)");    
           
           }catch(Exception e){          
            System.out.println("Add book" + e);
           }
    }
    
    private void returnBook(){
        try{
            System.out.println("enter reader ID");
            int id = s.nextInt();
 
            String Q ="select * from "+userTableName+" where ID = "+id+"";
            resultSet = statement.executeQuery(Q);
            while(resultSet.next())
            {
                String name1 = resultSet.getString("name");
                System.out.println("Name : "+name1);
                //8ayer al sout ya m3rs
                String at = resultSet.getString("book_rented_at");
                System.out.println("BOOK RENTED AT : "+at);
                               
                String rat = resultSet.getString("book_return_at");
                System.out.println("BOOK RENTED AT : "+rat);
                                
                String bname = resultSet.getString("rented_book");
                System.out.println("BOOK RENTED AT : "+bname);
                                
                int bid = resultSet.getInt("rented_book_id");
                System.out.println("BOOK RENTED AT : "+bid);
                
                statement.executeUpdate("UPDATE books SET is_rented = false where ID = "+bid+"");                     
                statement.executeUpdate("UPDATE "+userTableName+" SET RENTED_BOOK = null , BOOK_RENTED_AT = null , BOOK_RETURN_AT = null , RENTED_BOOK_ID = null where ID = "+id+"");
            
                System.out.println("the book is returned");
            }
            
            
        }catch(Exception e){
            System.out.println("userSeaerch Erorr" + e);
         
        }
    }
       
    @Override
    protected void bookSearch(){
        super.bookSearch();
    try{
        
            System.out.println("Press #1 to remove book");      
            System.out.println("Press #0 to cancel");
            
            int x =s.nextInt();
                
                if(x == 1){
                    System.out.println("Enter the Serial Number : ");
                    int id = s.nextInt();
                    statement.executeUpdate("DELETE FROM books WHERE id = "+id+"");    
                    System.out.println("book has been removed !!");
                }
            
           }catch(Exception e){          
            System.out.println("removeBook "+e);
           }
    }
          
    @Override
    protected void readerSearch(){
        try{
                   
            super.readerSearch();
            int yesORno;
            while(resultSet.next()){
               
            System.out.println("name : "+ resultSet.getString("name"));               
            System.out.println("last name : " + resultSet.getString("lname"));                   
            System.out.println("EMAIL :"+ resultSet.getString("email"));                                         
            System.out.println("Phone :"+resultSet.getInt("cellphone"));                          
            System.out.println("rented books :"+resultSet.getString("rented_book"));                   
            System.out.println("book_rented_at :"+resultSet.getString("book_rented_at"));                         
            System.out.println("book_return_at :"+resultSet.getString("book_return_at"));
            
            String isBlocked = resultSet.getString("isblocked");                  
            System.out.println("is blocked : " + isBlocked );
                                            
            if(isBlocked.equals("false")){
                System.out.println("Press #1 to Block Reader");
                System.out.println("Press #0 to cancel");    
                yesORno = s.nextInt();
                           
                switch(yesORno){
            case 1:{
                statement.executeUpdate("UPDATE "+userTableName+" SET isblocked = true where ID = "+searchByID+"");
                break;
            }
            default:{
                System.out.println("Canceed");
                break;
            }
                   }
            }
            else if(isBlocked.equals("true")){
                System.out.println("Press #1 to unBlock Reader");
                System.out.println("Press #0 to cancel");   
                yesORno = s.nextInt();
                
                if(yesORno == 1){
                    statement.executeUpdate("UPDATE "+userTableName+" SET isblocked = false where ID = "+searchByID+"");
                }else{
                    
                }       
            }
            }
        }catch(Exception e){           
            System.out.println("Reader search Erorr" + e);
        }
    }

    @Override
    protected void login(){
         
        System.out.println("Enter ID ");       
        int Id = s.nextInt();         
        
        System.out.println("Enter password ");       
        String pass = s.next();
        
        if(Id == 7797 && pass.equals("admin")){
            boolean end = false;
            do{
               
                System.out.println("#menu");
               
                System.out.println("Press #1 to Search for reader");
                System.out.println("Press #2 to Add new reader");
                System.out.println("Press #3 to Add new book");
                System.out.println("Press #4 to Remove reader");
                System.out.println("Press #5 to search for book");
                System.out.println("Press #6 to Return rented book");
                System.out.println("Press #0 to Logout");
           
                String choice = s.next();
                switch(choice){
                    case "1":{           
                        readerSearch();
                        break;
                    }
                    case "2":{
                           
                        addReader();
                        break;            
                    }        
                    case "3":{
                        addBook();
                        break;
                    }                   
                    case "4":{  
                        removeReader();     
                        break;
                    }
                    case "5":{  
                        bookSearch();     
                        break;
                    }
                    case "6":{  
                        returnBook();     
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
        }else{
            System.out.println("Invaled ID or password");       
        }
    }
}
