import java.util.*;
import java.io.RandomAccessFile;

/**
 * Write a description of class C1 here
   *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Manager extends EntryLog
{
    Scanner s= new Scanner(System.in);
   class Node
   {
       int id;
       String name1;
       Node next;
       Node prev;
    }

      Node head = new Node();
      Node headS = new Node();
      long fp = 0;
    long recordLength=260;
    long t1 = 0;
    RandomAccessFile rf1;
    RandomAccessFile rf1S;
   public void populate(Node head, RandomAccessFile rf1) throws Exception
   {
       t1=rf1.length()/recordLength;
       Node temp = new Node();
       temp =  head;
        for (long fp1=1; fp1<=t1 ; fp1++)
        {
            Node current = new Node();
            Record b = readRecord1(fp1);
            current.id = b.id;
            current.name1 = b.name;
            current.next = null;
            current.prev = null;
            Node temp1 = new Node();
            temp1 = temp;
            int check = 1;
            while (check == 1)
            {

                if (current.id>temp.id)
                {
                    if (temp1.next!=null)
                    {
                        temp1 = temp.next;
                        temp = temp1;
                    }
                    else
                    {
                        temp1.next = current;
                        check = 0;
                    }
                }
                if (current.id<=temp.id)
                {
                    if (temp1.prev!=null)
                    {
                        temp1 = temp.prev;
                        temp = temp1;
                    }
                    else
                    {
                        temp1.prev = current;
                        check = 0;
                    }
                }
            }
            temp=head;          
        }

    }

     public Record readRecord1(long fp1) throws Exception
    {
        Record a=new Record();
        rf1.seek(fp1*recordLength);
        a.id= rf1.readInt();
        a.name= rf1.readUTF();
        return a;
    }
   
    public Record readRecord1S(long fp1) throws Exception
    {
        Record a=new Record();
        rf1S.seek(fp1*recordLength);
        a.id= rf1S.readInt();
        a.name= rf1S.readUTF();
        return a;
    }
   
    public void WriteRecord1(long fp, Record a) throws Exception
    {
        rf1.seek(fp*recordLength);
        rf1.writeInt(a.id);
        rf1.writeUTF(a.name);
    }
   
    public void DisplayRecord1(Record a)
    {
        System.out.println("ID : " +a.id);
        System.out.println();
    }
  
   
    public void search(Node head, int n)
    {
         Node current = new Node();
        current = head;
        boolean found = false;
        while (current!=null &&  found==false)
        {
            if (current.id>n)
            {
                current=current.prev;
            }
            else if (current.id<n)
            {
                current=current.next;
            }
            else if (current.id==n)
            {
                System.out.println("Element found");
                System.out.println(current.name1);
                searchS(headS, n);
                found=true;
            }
           
        }
        if (found == false)
        System.out.println("Element not found");
    }
   
   
    public void searchS(Node head, int n)
    {
         Node current = new Node();
        current = headS;
        boolean found = false;
        while (current!=null &&  found==false)
        {
            if (current.id>n)
            {
                current=current.prev;
            }
            else if (current.id<n)
            {
                current=current.next;
            }
            else if (current.id==n)
            {
                System.out.println("Member has been signed out");
                found=true;
            }
           
        }
        if (found == false)
        System.out.println("Element not found");
    }

   public void main() throws Exception
   {
        rf1=new RandomAccessFile("/home/family/keshav/EntryLog.txt","rw");
        rf1S=new RandomAccessFile("/home/family/keshav/SignOut.txt","rw");
        Node temp = new Node();
        Record b = readRecord1(0);
       
        Node tempS = new Node();
        Record bS = readRecord1S(0);
       
        temp.id= b.id;
        temp.next = null;
        temp.prev = null;
       
        tempS.id= bS.id;
        tempS.next = null;
        tempS.prev = null;
       
        head = temp;
        headS = tempS;
        populate(head, rf1);
        populate(headS, rf1S);

        int ans1=1;

        while (ans1==1)
        {
            System.out.println("Enter an id you'd like to find");
            int f = s.nextInt();
            search(head,f);
            System.out.println("Enter 1 if you'd like to continue searching");
            ans1=s.nextInt();
        }

   } 
  
   public void reset() throws Exception
    {
        rf1.setLength(0);
    }
}





