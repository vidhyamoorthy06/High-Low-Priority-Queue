
public class Customer implements BinaryPrioritizable {
 
    private String name;
    private int prevPurchaseAmt;
 
    public Customer(String name, int prevPurchaseAmt) {
        this.name = name;
        this.prevPurchaseAmt = prevPurchaseAmt;
    }

    // assign customers who have purchases over $1000 in the past high 
    // priority
    @Override public int getPriority() {
        if (prevPurchaseAmt>= 1000) {
            //System.out.println(prevPurchaseAmt);
            return 1;
        }
        else
            return 0;
    }
 
    @Override public String toString() {
        return "(" + name + ", Prev Purchases: $" + prevPurchaseAmt + ")";
    }  
}