import java.util.*;
/**
 * Write a description of class Final here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Final
{
    Scanner s = new Scanner (System.in);
    Basketball obb = new Basketball();
    Tennis obt = new Tennis();
    Pool obp = new Pool();
    Gym obg = new Gym();
    Badminton obba = new Badminton();
    EntryLog obe = new EntryLog();
    Manager obm = new Manager();
   
   public void main() throws Exception
   {
       int ans = 1;
       while (ans==1)
       {
       System.out.println("Enter 1 to sign on the Entry Log");
       System.out.println("Enter 2 to check the facility database");
       System.out.println("Enter 3 for Managerial Access (Employees only)");
       int a = s.nextInt();
       switch(a)
       {
           case 1:
           obe.main();
           break;
           case 2:
           System.out.println("Which facility would you like to check?");
           System.out.println("1. Basketball");
           System.out.println("2. Tennis");
           System.out.println("3. Badminton");
           System.out.println("4. Pool");
           System.out.println("5. Gym");
           int b= s.nextInt();
           switch(b)
           {
               case 1:
               obb.main();
               break;
               case 2:
               obt.main();
               break;
               case 3:
               obba.main();
               break;
               case 4:
               obp.main();
               break;
               case 5:
               obg.main();
               break;
               default:
               System.out.println("Invalid Option");
               break;
              
            }
            break;
            case 3:
            obm.main();
            break;
       
        }
        System.out.println("Would you like to continue? Press 1 if yes");
        ans=s.nextInt();
    }
    }
}
