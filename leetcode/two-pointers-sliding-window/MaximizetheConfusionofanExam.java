public class MaximizetheConfusionofanExam {
    // tc: O(N): we go through each element once
    // sc: O(1): we dont use any data structure here only variables
    public static int maxConsecutiveAnswers(String answerKey, int k) {
        int left = 0;
        int cntF = 0, cntT = 0, res = 0;

        for(int right = 0; right < answerKey.length(); right++) {
            
            // count the character's frequency in the window
            if(answerKey.charAt(right) == 'F') cntF++;
            if(answerKey.charAt(right) == 'T') cntT++;
            
            // get the most frequent element in the window 'F' or 'T'
            int greater = Math.max(cntF, cntT);

            // shrink when window length - most frequent element is > k
            while((right - left + 1) - greater > k) {
                // reduce frequency of elements when shrinking
                if(answerKey.charAt(left) == 'T') cntT--;
                if(answerKey.charAt(left) == 'F') cntF--;

                left++;
            }

            res = Math.max(res, right - left + 1);
        }

        return res;
    }

    public static void main(String[] args) {
        String s = "TTFF";
        int k = 2;

        System.out.println(maxConsecutiveAnswers(s, k));
    }
}
