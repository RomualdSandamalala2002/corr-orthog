package mg.sanda.corrorthog.services;

import java.util.Arrays;

/**
 * Service pour gérer l'algorithme de Levenshtein
 */
public class LDService {
    /**
     * Implementation de l'algorithme
     * @param str1
     * @param str2
     * @return
     */
    public static int calculateDistance(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = min(dp[i - 1][j - 1] + coutSubstitution(str1.charAt(i - 1), str2.charAt(j - 1)),
                                   dp[i - 1][j] + 1,
                                   dp[i][j - 1] + 1);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * Coût de substition
     * @param a
     * @param b
     * @return
     */
    private static int coutSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    private static int min(int... numbers) {
        return Arrays.stream(numbers)
                .min()
                .orElse(Integer.MAX_VALUE);
    }
}
