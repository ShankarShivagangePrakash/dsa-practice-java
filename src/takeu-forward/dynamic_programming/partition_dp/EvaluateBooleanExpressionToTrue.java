package dynamic_programming.partition_dp;

/*
Problem:
    https://takeuforward.org/data-structure/evaluate-boolean-expression-to-true-partition-dp-dp-52/
    https://leetcode.com/problems/parsing-a-boolean-expression/description/
 */
public class EvaluateBooleanExpressionToTrue {

    static final int MOD = 1000000007;

    /*
    Approach:
        Explained in notes, check

        Time Complexity: Exponential

        Space Complexity: O(n)
            stack space
     */
    static int evaluateExpWaysRecursiveMain(String exp) {
        int n = exp.length();

        return (int) evaluateExpressionWaysRecursive(0, n-1, 1, exp);
    }

    public static long evaluateExpressionWaysRecursive(int i, int j, int isTrue, String exp){
        // Base case 1: When the start index is greater than the end index, no ways to evaluate.
        if (i > j) {
            return 0;
        }
        // Base case 2: When the start and end indices are the same.
        if (i == j) {
            if (isTrue == 1) {
                return exp.charAt(i) == 'T' ? 1 : 0;
            } else {
                return exp.charAt(i) == 'F' ? 1 : 0;
            }
        }

        long ways = 0;
        // operators will be after every two characters, so make a jump of two
        for (int ind = i + 1; ind <= j - 1; ind += 2) {
            long lT = evaluateExpressionWaysRecursive( i, ind - 1, 1, exp);
            long lF = evaluateExpressionWaysRecursive( i, ind - 1, 0, exp);
            long rT = evaluateExpressionWaysRecursive(ind + 1, j, 1, exp);
            long rF = evaluateExpressionWaysRecursive(ind + 1, j, 0, exp);

            char operator = exp.charAt(ind);
            if (operator == '&') {
                if (isTrue == 1) {
                    ways = (ways + (lT * rT) % MOD) % MOD;
                } else {
                    ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD + (lF * rF) % MOD) % MOD;
                }
            } else if (operator == '|') {
                if (isTrue == 1) {
                    ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD + (lT * rT) % MOD) % MOD;
                } else {
                    ways = (ways + (lF * rF) % MOD) % MOD;
                }
            }
            // else means ^ operator
            else {
                /* T ^ T = T
                 F ^ F = F
                 T ^ F = T
                 F ^ T = T*/
                if (isTrue == 1) {
                    ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD) % MOD;
                } else {
                    ways = (ways + (lF * rF) % MOD + (lT * rT) % MOD) % MOD;
                }
            }
        }

        return ways;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*N*2 * N) ~ O(N^3)
            There are a total of 2*N^2 no. of states.
            And for each state, we are running a partitioning loop roughly for N times.

        Space Complexity: O(2*N^2) + Auxiliary stack space of O(N)
            2*N^2 for the dp array we are using.
     */
    static int evaluateExpWaysMemoizationMain(String exp) {
        int n = exp.length();

        // first two parameters for i and j, third parameter for isTrue
        // dp[i][j][k] stores the number of ways to evaluate the subexpression from index i to j with the result k (0 or 1).
        Long[][][] dp = new Long[n][n][2];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                for(int isTrue = 0; isTrue < 2; isTrue++)
                    dp[i][j][isTrue] = Long.valueOf(-1);
            }
        }

        return (int) evaluateExpressionWaysMemoization(0, n-1, 1, exp, dp);
    }

    public static long evaluateExpressionWaysMemoization(int i, int j, int isTrue, String exp, Long[][][] dp){
        // Base case 1: When the start index is greater than the end index, no ways to evaluate.
        if (i > j) {
            return 0;
        }
        // Base case 2: When the start and end indices are the same.
        if (i == j) {
            if (isTrue == 1) {
                return exp.charAt(i) == 'T' ? 1 : 0;
            } else {
                return exp.charAt(i) == 'F' ? 1 : 0;
            }
        }

        if(dp[i][j][isTrue] != -1)
            return dp[i][j][isTrue];

        long ways = 0;
        // operators will be after every two characters, so make a jump of two
        for (int ind = i + 1; ind <= j - 1; ind += 2) {
            long lT = evaluateExpressionWaysMemoization( i, ind - 1, 1, exp, dp);
            long lF = evaluateExpressionWaysMemoization( i, ind - 1, 0, exp, dp);
            long rT = evaluateExpressionWaysMemoization(ind + 1, j, 1, exp, dp);
            long rF = evaluateExpressionWaysMemoization(ind + 1, j, 0, exp, dp);

            char operator = exp.charAt(ind);
            if (operator == '&') {
                if (isTrue == 1) {
                    ways = (ways + (lT * rT) % MOD) % MOD;
                } else {
                    ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD + (lF * rF) % MOD) % MOD;
                }
            } else if (operator == '|') {
                if (isTrue == 1) {
                    ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD + (lT * rT) % MOD) % MOD;
                } else {
                    ways = (ways + (lF * rF) % MOD) % MOD;
                }
            }
            // else means ^ operator
            else {
                /* T ^ T = T
                 F ^ F = F
                 T ^ F = T
                 F ^ T = T*/
                if (isTrue == 1) {
                    ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD) % MOD;
                } else {
                    ways = (ways + (lF * rF) % MOD + (lT * rT) % MOD) % MOD;
                }
            }
        }

        return dp[i][j][isTrue] = ways;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*N*2 * N) ~ O(N^3)
            There are a total of 2*N^2 no. of states.
            And for each state, we are running a partitioning loop roughly for N times.

        Space Complexity: O(2*N^2)
            2*N^2 for the dp array we are using.
     */
    public static int evaluateExpWaysTabulation(String exp) {
        int mod = 1000000007;
        // Write your code here.
        int n = exp.length();
        int[][][] dp = new int[n][n][2];

        // Base case 2:
        for(int i = 0; i < n; i++) {
            dp[i][i][1] = exp.charAt(i) == 'T' ? 1 : 0;
            dp[i][i][0] = exp.charAt(i) == 'F' ? 1 : 0;
        }
        for(int i = n-1; i >= 0; i--) {
            for(int j = i+1; j < n; j++) {
                for(int isTrue = 0; isTrue < 2; isTrue++) {
                    // Base case 1:
                    if(i > j)
                        continue;

                    long ans = 0;
                    for(int idx = i+1; idx < j; idx+=2) {
                        long leftFalse = dp[i][idx-1][0];
                        long leftTrue = dp[i][idx-1][1];
                        long rightFalse = dp[idx+1][j][0];
                        long rightTrue = dp[idx+1][j][1];
                        if(exp.charAt(idx) == '&') {
                            if(isTrue == 1)
                                ans = (ans + (leftTrue*rightTrue)%mod) % mod;
                            else
                                ans = (ans + (leftFalse*rightFalse +
                                        leftFalse*rightTrue + leftTrue*rightFalse)%mod) % mod ;
                        }
                        else if(exp.charAt(idx) == '|') {
                            if(isTrue == 1)
                                ans = (ans + (leftTrue*rightTrue +
                                        leftTrue*rightFalse + leftFalse*rightTrue)%mod) % mod;
                            else
                                ans = (ans + (leftFalse*rightFalse)%mod) % mod;
                        }
                        else if(exp.charAt(idx) == '^') {
                            if(isTrue == 1)
                                ans = (ans + (leftTrue*rightFalse
                                        + rightTrue*leftFalse)%mod) % mod;
                            else
                                ans = (ans + (leftFalse*rightFalse
                                        + leftTrue*rightTrue)%mod) % mod;
                        }
                    }
                    dp[i][j][isTrue] = (int)ans;
                }
            }
        }
        return dp[0][n-1][1];
    }
}
