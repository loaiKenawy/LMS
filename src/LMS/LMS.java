
package LMS;

import java.util.Scanner;

public class LMS {
    
    public static void mainMenu(){
         
        librarian librarian = new librarian();
        Reader Reader = new Reader();
        Scanner s = new Scanner(System.in);
        boolean end = false;
        
        do{
        System.out.println("press #1 to login as reader");
        System.out.println("press #2 to login as librarian");
        System.out.println("press #0 to exit");
        String choice = s.nextLine();
        
        switch(choice){
            case "1":{
                Reader.login();
                break;
            }
            
            case "2":{
                librarian.login();  
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
    
    public static void main(String[] args){
    
        mainMenu();
    
    }
       
}