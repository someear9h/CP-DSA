import java.util.Arrays;

class Item {
    int value, weight;

    Item(int v, int w) {
        this.value = v;
        this.weight = w;
    }
}

public class FractionalKnapsackProblem {
    public static double solve(int W, Item[] arr) {
       
        // Sort by value/weight ratio in descending order using lambda
        Arrays.sort(arr, (a, b) -> {
            double ra = (double) a.value / a.weight;
            double rb = (double) b.value / b.weight;
            return Double.compare(rb, ra);  // descending order
        });

        int currWeight = 0;
        double finalValue = 0.0;

        for(int i = 0; i < arr.length; i++) {
            if(currWeight + arr[i].weight <= W) {
                currWeight += arr[i].weight;
                finalValue += arr[i].value;
            } else {
                int remaining = W - arr[i].weight;
                finalValue += ((double) arr[i].value / arr[i].weight) * remaining;
                break;
            }
        }
        return finalValue;
    }

    public static void main(String[] args) {
        int weight = 50;
        Item arr[] = {
            new Item(100, 20),
            new Item(60, 10),
            new Item(120, 30)
        };

        double ans = solve(weight, arr);
        System.out.println("The maximum value is " + ans);
    }
}