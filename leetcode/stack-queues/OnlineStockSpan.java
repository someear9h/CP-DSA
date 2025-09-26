import java.util.*;

class StockSpanner {
    Stack<int[]> st;

    public StockSpanner() {
        st = new Stack<>();    
    }
    
    public int next(int price) {
        int span = 1;
        while(!st.isEmpty() && price >= st.peek()[0]) {
            span += st.pop()[1];
        }

        st.push(new int[] {price, span});
        return span;
    }
}


public class OnlineStockSpan {
    public static void main(String[] args) {
        StockSpanner obj = new StockSpanner();
        int p1 = obj.next(100);
        int p2 = obj.next(80);
        int p3 = obj.next(60);
        int p4 = obj.next(70);
        int p5 = obj.next(60);
        int p6 = obj.next(75);
        int p7 = obj.next(85);

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);
        System.out.println(p5);
        System.out.println(p6);
        System.out.println(p7);
    }
}