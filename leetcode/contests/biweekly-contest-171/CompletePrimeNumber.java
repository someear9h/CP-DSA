import java.io.*;

public class CompletePrimeNumber {
    public static boolean completePrime(int num) {
        String s = String.valueOf(num);

        // find prefix and suffix substrings
        for(int i = 1; i <= s.length(); i++) {
            int n = Integer.parseInt(s.substring(0, i)); // prefix
            if(!fun(n)) return false;
        }

        for(int i = 0; i < s.length(); i++) {
            int n = Integer.parseInt(s.substring(i)); // suffix
            if(!fun(n)) return false;
        }

        return true;
    }

    private static boolean fun(int num) {
        if(num <= 1) return false;
        if(num <= 3) return true;

        if(num % 2 == 0 || num % 3 == 0) return false;

        for(int i = 5; i * i <= num; i += 6) {
            // i + 2 -> 6k + 1
            // i -> 6k - 1
            if((num % i == 0) || (num % (i + 2) == 0)) return false;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());

            while(t-- > 0) {
                int num = Integer.parseInt(br.readLine());

                out.println(completePrime(num));
            } 
        }
    }
}
