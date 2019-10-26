import java.io.BufferedReader;
import java.util.Scanner;
import java.io.RandomAccessFile;
/**
 * Write a description of class IA2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Basketball extends EntryLog
{
    Scanner s = new Scanner (System.in);
    class Record
    {
        int id;
       
    }
    long fp1 = 0;
    String g;
    long recordLength=260;
    RandomAccessFile rf1;
    public void add1(int n) throws Exception
    {
         boolean have=true;
         for (int c=1;c<=n;c++)
         {
             Record a=new Record();
             System.out.println("Enter id number "+c);
             int temp=s.nextInt();
             while(!validityCheck(temp))
             {
                 System.out.println("Invalid ID. The ID you've entered is incorrect. Please reenter");
                 temp = s.nextInt();
                }
             a.id = temp;
             enqueue(head, temp);
             WriteRecord1(fp1,a);
             fp1=fp1+1;
          }
               
    }
   
   
    public void updateQueue() throws Exception
    {
        t1=rf1.length()/recordLength;
        Record b=readRecord1(0);
        Node temp = new Node();
        temp.id = b.id;
        temp.next = null;
        head = temp;
        for (long fp1=1; fp1<=t1; fp1++)
        {
            Node current = new Node();
            Record a = readRecord1(fp1);
            current.id = a.id;
            current.next = null;
            temp.next = current;
            temp = current;
           
        }
    }
    long t1 = 0;
   public void main() throws Exception
    {
        rf1=new RandomAccessFile("/home/family/keshav/Basketball.txt","rw");

        if(rf1.length()==0)
        {
            System.out.println("This file is empty");
            fp1 = 0;
        }
        else
           {
            t1=rf1.length()/recordLength + 1;
            System.out.println("Number of records "+t1);
            fp1 = t1;
            updateQueue(); 
        }
                  System.out.println("Basketball has a limit of 10 people on the court. After the 10th member, the waiting list begins");
      System.out.println("Enter 1 to add your name to the queue, or 2 to display the queue currently");
      int c=s.nextInt();
      switch(c)
      {
          case 1:
          System.out.println("How many members would you like to add");
          int no = s.nextInt();
          add1(no);

          System.out.println("Thank you for entering your names to the queue. Please have a good time!");
          break;
          case 2:
          display(head);
          break;
        }

    }
   
    public Record readRecord1(long fp1) throws Exception
    {
        Record a=new Record();
        rf1.seek(fp1*recordLength);
        a.id= rf1.readInt();
        return a;
    }
   
    public void WriteRecord1(long fp, Record a) throws Exception
    {
        rf1.seek(fp*recordLength);
        rf1.writeInt(a.id);

    }
   
    public void DisplayRecord1(Record a)
    {
        System.out.println("ID : " +a.id);
        System.out.println();
    }
  

   class Node
   {
       private int id;
       private Node next;
    }
    Node head = new Node();
   
    public void display(Node head)
    {
        Node current=new Node();
        current=head;
        int c=1;
        while(current!=null)
        {
            System.out.println(current.id);
            current=current.next;
            if (c==10)
            System.out.println("-----Waiting List-----");
            c++;
        }
       
    }
  
    public void enqueue(Node head, int a)
    {
        Node current;
        current = head;
         while(current.next!=null)
        {
            current=current.next;
        }
        Node temp = new Node();
        temp.id=a;
        current.next=temp;
        temp.next=null;

    }
   public Node dequeue(Node head)
   {
       Node current;
       current = head;
       head = current.next;
       current.next = null;
       return head;
      
    }
   
    public void reset() throws Exception
    {
        rf1.setLength(0);
    }
}






