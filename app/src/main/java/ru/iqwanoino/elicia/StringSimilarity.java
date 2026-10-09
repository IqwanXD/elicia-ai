package ru.iqwanoino.elicia;

public class StringSimilarity {
    private static final double SIMILARITY_THRESHOLD = 0.7;
    private static final double SHORT_WORD_THRESHOLD = 0.6;

    public static boolean isSimilar(String input, String key) {
        if (input == null || key == null) return false;
        input = normalize(input);
        key = normalize(key);

        String[] tokens = input.split("\\s+");
        for (String token : tokens) {
            if (token.equals(key)) return true;
        }

        if (input.contains(key)) return true;

        double threshold = (input.length() < 4 || key.length() < 4) ? SHORT_WORD_THRESHOLD : SIMILARITY_THRESHOLD;
        return jaroWinklerSimilarity(input, key) >= threshold;
    }

    private static String normalize(String s) {
        return s.toLowerCase().replaceAll("[^a-z0-9\\s]", " ").replaceAll("\\s+", " ").trim();
    }

    private static double jaroWinklerSimilarity(String s1, String s2) {
        double jaro = jaroSimilarity(s1, s2);
        int prefixLength = commonPrefixLength(s1, s2, 4);
        return jaro + (prefixLength * 0.1 * (1 - jaro));
    }

    private static double jaroSimilarity(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 == 0 || len2 == 0) return 0.0;

        int matchDistance = Math.max(len1, len2) / 2 - 1;
        boolean[] s1Matches = new boolean[len1];
        boolean[] s2Matches = new boolean[len2];

        int matches = 0, transpositions = 0;
        for (int i = 0; i < len1; i++) {
            int start = Math.max(0, i - matchDistance);
            int end = Math.min(len2 - 1, i + matchDistance);
            for (int j = start; j <= end; j++) {
                if (!s2Matches[j] && s1.charAt(i) == s2.charAt(j)) {
                    s1Matches[i] = true;
                    s2Matches[j] = true;
                    matches++;
                    break;
                }
            }
        }
        if (matches == 0) return 0.0;
        int k = 0;
        for (int i = 0; i < len1; i++) {
            if (s1Matches[i]) {
                while (!s2Matches[k]) k++;
                if (s1.charAt(i) != s2.charAt(k)) transpositions++;
                k++;
            }
        }
        transpositions /= 2.0;
        return ((matches / (double) len1) +
                (matches / (double) len2) +
                ((matches - transpositions) / (double) matches)) / 3.0;
    }

    private static int commonPrefixLength(String s1, String s2, int maxLength) {
        int n = Math.min(Math.min(s1.length(), s2.length()), maxLength);
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) != s2.charAt(i)) return i;
        }
        return n;
    }
}