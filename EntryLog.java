import java.io.BufferedReader;
import java.util.Scanner;
import java.io.RandomAccessFile;
/**
 * Write a description of class IA2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class EntryLog
{
    Scanner s = new Scanner (System.in);
    class Record
    {
        int id;
        String name;
        int inTime;
       
    }
    long fp = 0;
    long fps = 0;
    String g;
    long recordLength=260;
    public RandomAccessFile rf;
    public RandomAccessFile rfS;
    public void add(int n) throws Exception
    {
         boolean have=true;
         for (int c=1;c<=n;c++)
         {
             Record a=new Record();
             System.out.println("Enter your first name "+c);
             a.name= s.next();
             System.out.println("Enter id number "+c);
             int temp=s.nextInt();
             while(!validityCheck(temp))
             {
                 System.out.println("Invalid ID. The ID you've entered is incorrect. Please reenter");
                 temp = s.nextInt();
                }
             a.id = temp;
             System.out.println("Enter sign in time (in 2400 format)");
             a.inTime=s.nextInt();
             WriteRecord(fp,a);
             fp=fp+1;
          }
               
    }
   
    public void addS(int n) throws Exception
    {
         boolean have=true;
         for (int c=1;c<=n;c++)
         {
             Record a=new Record();
             System.out.println("Enter your first name "+c);
             a.name= s.next();
             System.out.println("Enter id number "+c);
             int temp=s.nextInt();
             while(!validityCheckS(temp))
             {
                 System.out.println("Invalid ID. The ID you've entered is incorrect. Please reenter");
                 temp = s.nextInt();
                }
             a.id = temp;
             System.out.println("Enter sign out time (in 2400 format)");
             a.inTime=s.nextInt();
             WriteRecordS(fps,a);
             fps=fps+1;
          }
               
    }
   
    public boolean validityCheck(int r) throws Exception
    {
        boolean valid = false;
        boolean found=false;
        t = fp;
        for(long fp1=0;fp1<t;fp1++)
        {
            Record a=readRecord(fp1);
            if(a.id==r)
            {
                found=true;
                break;
            }
        }
        if (found==false && r>=1000 && r<=3600)
        {
            valid = true;
        }
        else
        {
            valid = false;
        }
        return valid;
    }
   
    public boolean validityCheckS(int r) throws Exception
    {
        boolean valid = false;
        boolean found=false;
        tS = fp;
        for(long fp1=0;fp1<t;fp1++)
        {
            Record a=readRecord(fp1);
            if(a.id==r)
            {
                found=true;
                break;
            }
        }
        if (found==true && r>=1000 && r<=3600)
        {
            valid = true;
        }
        else
        {
            valid = false;
        }
        return valid;
    }
   
    public void viewAll() throws Exception
    {
        t=rf.length()/recordLength;
        for (long fp1=0; fp1<=t; fp1++)
        {
            Record a=readRecord(fp1);
            DisplayRecord(a);
        }
    }
   
    public void viewAllS() throws Exception
    {
        tS=rfS.length()/recordLength;
        for (long fp1=0; fp1<=tS; fp1++)
        {
            Record a=readRecordS(fp1);
            DisplayRecordS(a);
        }
    }
    long t = 0;
    long tS = 0;
    void main() throws Exception
    {
        rf=new RandomAccessFile("/home/family/keshav/EntryLog.txt","rw");
        rfS=new RandomAccessFile("/home/family/keshav/SignOut.txt","rw");
        if(rf.length()==0)
        {
            System.out.println("This file is empty");
            fp = 0;
        }
        else
           {
            t=rf.length()/recordLength + 1;
            System.out.println("Number of records "+t);
            fp = t;
        }
       
        if(rfS.length()==0)
        {
            System.out.println("This file is empty");
            fps = 0;
        }
        else
           {
            tS=rfS.length()/recordLength + 1;
            System.out.println("Number of records "+tS);
            fps = tS;
        }

      System.out.println("Enter 1 if you want to sign in, or 2 if you want to sign out");
      int c=s.nextInt();
      switch(c)
      {
          case 1:
          System.out.println("How many members would you like to sign in");
          int no = s.nextInt();
          add(no);
          System.out.println("Current List:");
          viewAll();
          break;
          case 2:
          if (rf.length()!=0)
          {
              System.out.println("How many members would you like to sign out");
              int no2 = s.nextInt();
              addS(no2);
            }
            viewAllS();
            break;
         
         
        }

    }
   
    public Record readRecord(long fp1) throws Exception
    {
        Record a=new Record();
        rf.seek(fp1*recordLength);
        a.id= rf.readInt();
        a.name = rf.readUTF();
        a.inTime = rf.readInt();
        return a;
    }
   
    public void WriteRecord(long fp, Record a) throws Exception
    {
        rf.seek(fp*recordLength);
        rf.writeInt(a.id);
        rf.writeUTF(a.name);
        rf.writeInt(a.inTime);
    }
   
    public void DisplayRecord(Record a)
    {
        System.out.println("ID : " +a.id);
        System.out.println("Name: " +a.name);
        System.out.println("In time: " +a.inTime);
        System.out.println();
    }
  
   
    public Record readRecordS(long fp1) throws Exception
    {
        Record a=new Record();
        rfS.seek(fp1*recordLength);
        a.id= rfS.readInt();
        a.name = rfS.readUTF();
        a.inTime = rfS.readInt();
        return a;
    }
   
    public void WriteRecordS(long fp, Record a) throws Exception
    {
        rfS.seek(fp*recordLength);
        rfS.writeInt(a.id);
        rfS.writeUTF(a.name);
        rfS.writeInt(a.inTime);
    }
   
    public void DisplayRecordS(Record a)
    {
        System.out.println("ID : " +a.id);
        System.out.println("Name: " +a.name);
        System.out.println("In time: " +a.inTime);
        System.out.println();
    }
  
}





