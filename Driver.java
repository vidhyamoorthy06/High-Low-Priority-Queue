/**
Trial Run:
B:\TAMUC Study Material\OOP Spring 18\HiLoPriorityQueue>java Driver
The customer list
Customer - A     Previous purchase amount:1624
Customer - P     Previous purchase amount:1495
Customer - P     Previous purchase amount:1677
Customer - F     Previous purchase amount:981
Customer - C     Previous purchase amount:170
Customer - E     Previous purchase amount:801
Customer - W     Previous purchase amount:1861
Customer - N     Previous purchase amount:1192

Customers are served in the following order based on the priority as purchase above $1000 is high priority
Served in order :: 1 is customer (Customer - A, Prev Purchases: $1624)
Served in order :: 2 is customer (Customer - P, Prev Purchases: $1495)
Served in order :: 3 is customer (Customer - P, Prev Purchases: $1677)
Served in order :: 4 is customer (Customer - W, Prev Purchases: $1861)
Served in order :: 5 is customer (Customer - N, Prev Purchases: $1192)
Served in order :: 6 is customer (Customer - F, Prev Purchases: $981)
Served in order :: 7 is customer (Customer - C, Prev Purchases: $170)
Served in order :: 8 is customer (Customer - E, Prev Purchases: $801)

B:\TAMUC Study Material\OOP Spring 18\HiLoPriorityQueue>
 */
import java.util.*;

public class Driver{
/**
 a driver class that generates multiple customer objects (of various priority levels), adds them to a priority queue,
  and then uses an enhanced for loop (or equivalently, an Iterator object) to loop through these customer objects and print them 
  out in the order that they areserved (with high priority customers being served first).
 */

    public static void main(String[] args) { 
        HiLoPriorityQueue<Customer> hlq = new HiLoPriorityQueue(5,3);
        Random random = new Random();
    	System.out.println("The customer list");
        for(int i = 0; !hlq.isFull(); i++)
	{
            String customer_name = "";
            customer_name += "Customer - "+ Character.toString((char) (random.nextInt(26) + 65));
	//generates a character that is assigned as a name to a customer
            int purchase_amt = random.nextInt(2000);
	//generates a random number as a purchase amount within the range of $2000
            Customer customer = new Customer(customer_name,purchase_amt);
            System.out.println(customer_name+"\t Previous purchase amount:"+purchase_amt);
            hlq.add(customer);
        }

        System.out.println();
        System.out.println("Customers are served in the following order based on the priority as purchase above $1000 is high priority");
        for(int i = 0;!(hlq.highIsEmpty() && hlq.lowIsEmpty()); i++)
	{
            Customer customer_removed = hlq.remove();
            System.out.println("Served in order :: "+(i+1)+" is customer "+customer_removed.toString());
        }
    }   
}