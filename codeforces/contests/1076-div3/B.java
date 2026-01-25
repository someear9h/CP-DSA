import java.io.*;
import java.util.*;

public class B {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t--> 0) {
            int n = Integer.parseInt(br.readLine());
            var st = new StringTokenizer(br.readLine());
            int[] arr = new int[n];
            for(int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            
            solve(arr, n);
        }
    }    

    static void solve(int[] arr, int n) {
        int[] pos = new int[n+1];

        for(int i = 0; i < n; i++) {
            pos[arr[i]] = i;
        }

        int exIdx = 0;
        for(int val = n; val>= 1; val--) {
            if(exIdx == pos[val]) {
                exIdx++;
                continue;
            }

            reverse(arr, exIdx, pos[val]);
            break;
        }

        for(int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    static void reverse(int[] arr, int l, int r) {
        while(l < r) {
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }
    }
}