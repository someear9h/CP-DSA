public class VideoTagGenerator {
    public static String generateTag(String caption) {
        if (caption.trim().isEmpty()) return "#";

        String[] arr = caption.trim().split("\\s+");
        String res = "#";

        String first_word = arr[0];
        first_word = first_word.substring(0, 1).toLowerCase() +
                     (first_word.length() > 1 ? first_word.substring(1).toLowerCase() : "");
        res += first_word;

        for (int i = 1; i < arr.length; i++) {
            String w = arr[i];
            if (w.isEmpty()) continue;
            w = w.substring(0, 1).toUpperCase() +
                (w.length() > 1 ? w.substring(1).toLowerCase() : "");
            res += w;
            if (res.length() >= 100) break;
        }

        return res.length() > 100 ? res.substring(0, 100) : res;
    }

    public static void main(String[] args) {
        String caption = " fPysaRtLQLiMKVvRhMkkDLNedQKffPnCjbITBTOVhoVjiKbfSawvpisDaNzXJctQkn";
        System.out.println(generateTag(caption));
        // Output: #fpysartlqlimkvvrhmkkdlnedqkffpncjbitbtovhovjikbfsawvpisdanzxjctqkn
    }
}
