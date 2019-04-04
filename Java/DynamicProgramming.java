import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DynamicProgramming {

    private Map<String, Integer> memoize;
    private Map<String, String> pred;

    public void eggDrop(int floors, int eggs) {
        memoize = new HashMap<>();
        pred = new HashMap<>();
        System.out.println("It would take a max of " + eggDropDP(floors, eggs) + " attempts to find breaking point in a building with " + floors + " floors and " + eggs + " eggs!");
        // TODO - figure out how to backtrack in this case
    }
    
    /**
     * N - number of floors (height)
     * k - number of eggs
     */
    private int eggDropDP(int N, int k) {
        int minWorstCase = (int) Double.POSITIVE_INFINITY;
        if (k == 1) {
            minWorstCase = N;
        } else if (N == 0) {
            minWorstCase = 0;
        } else if (N == 1) {
            minWorstCase = 1;
        } else if (memoize.containsKey(N + "_" + k)) {
            minWorstCase = memoize.get(N + "_" + k);
        } else {
            int minH = -1, minK = -1;
            for (int h = 1; h <= N; h++) {
                int case1 =  1 + eggDropDP(h-1, k-1);
                int case2 =  1 + eggDropDP(N-h, k);
                int worstCase =  Math.max(case1, case2);
                if (case1 > case2 && case1 < minWorstCase) {
                    minH = h-1;
                    minK = k-1;
                }
                if (case2 > case1 && case2 < minWorstCase) {
                    minH = N-h;
                    minK = k;
                }
                minWorstCase = Math.min(minWorstCase, worstCase);
            }
            pred.put(N + "_" + k, minH + "_" + minK);
            System.out.println("Drop at (" + minH + ", "+ minK + ") for finding from (" + N + ", " + k + ")");
        }
        memoize.put(N + "_" + k, minWorstCase);
        // System.out.println("Min for " + N + " floors and " + k + " eggs is " + minWorstCase);
        return minWorstCase;
    }

    public void eggDropBottomUp(int N, int k) {
        int[][] W = new int[N+1][k+1];
        for (int floor=0; floor <= N; floor++) {
            W[floor][0] = 0;  // Cant solve - mostly we will never need this
            W[floor][1] = floor; // 1 egg and n floors
        }
        for (int egg=0; egg <= k; egg++) {
            W[0][egg] = 0;  // 0th floor
        }
        for (int egg=1; egg <= k; egg++) {
            W[1][egg] = 1; // 1 floor left with egg > 0
        }
        for (int floor=2; floor <= N; floor++) {
            for (int egg=2; egg <= k; egg++) {
                // Solving for (floor, egg) -> we guess and drop for all floors 
                int minNumDrops = (int) Double.POSITIVE_INFINITY;
                for (int dropFloor=1; dropFloor < floor; dropFloor++) {
                    int numDrops = 1 + Math.max(W[dropFloor-1][egg-1], W[floor-dropFloor][egg]);
                    minNumDrops = Math.min(minNumDrops, numDrops);
                }
                W[floor][egg] = minNumDrops;
            }
        }
        System.out.println("Minimum number of drops needed for " + N + " floors with " + k + " eggs is " + W[N][k]);
    }

    // Find the maximum value contiguous subsequence
    public void maxValueContSubsequence(int[] array) {
        int max_so_far = array[0];
        int max_ending_here = array[0];
        int start = 0, end = 0;
        for (int index=1; index < array.length; index++) {
            // Two choices - either array[index] part of optimal
            // solution or its not
            // - If array[index] > 0 and prev is part of optimal,
            //   it also has to be optimal
            if (array[index] >= (max_ending_here + array[index]))
                start = index;
            max_ending_here = Math.max(array[index], max_ending_here + array[index]);
            if (max_ending_here >= max_so_far)
                end = index;
            max_so_far = Math.max(max_so_far, max_ending_here);
        }
        System.out.println("Max Value : "+ max_so_far);
        System.out.println("Longest Subsequence for max sum : (" + start + ", " + end + ")");
    }

    public void DPProblem1(int n) {
        int[] mem = new int[n+1];
        mem[0] = 2;
        mem[1] = 2;
        System.out.println("Value of T(" + n + ") from memoized recursion : " + DP1(n, mem));
        System.out.println("Value of T(" + n + ") from memoized bottom-up : " + DP1BottomUp(n));
    }

    private int DP1(int n, int[] mem) {
        if (n < 2)
            return 2;
        if (mem[n] != 0)
            return mem[n];
        for (int index=1; index < n; index++) {
            mem[n] = mem[n] + 2 * DP1(index, mem) * DP1(index-1, mem);
        }
        return mem[n];
    }

    private int DP1BottomUp(int n) {
        int[] mem = new int[n+1];
        mem[0] = 2;
        mem[1] = 2;
        for (int i=2; i <= n; i++) {
            for (int j=1; j < i; j++) {
                mem[i] = mem[i] + 2 * mem[j] * mem[j-1];
            }
        }
        return mem[n];
    }

    // mem[i] represents the max val cont subseq such that a[i] is included
    public void maxValContSubSeq(int[] array) {
        int[] mem = new int[array.length+1];
        mem[0] = 0;
        mem[1] = array[0];
        int max = mem[1];
        for (int i=2; i <= array.length; i++) {
            mem[i] = Math.max(array[i-1] + mem[i-1], array[i-1]);
            max = Math.max(max, mem[i]);
        }
        System.out.println("Longest subsequence with max sum is " + max);
    }

    // NOTE that we can solve this using the prefix or the suffix
    public void maxValContSubSeqOpt(int[] array) {
        int prev_max = array[0];
        int max = prev_max;
        for (int i=2; i <= array.length; i++) {
            prev_max = Math.max(array[i-1] + prev_max, array[i-1]);
            max = Math.max(max, prev_max);
        }
        System.out.println("Longest subsequence with max sum is " + max);
    }

    // Max value subsequence where no two elements are adjacent
    public void maxValCSNoTwoOptimal(int[] array) {
        int[] S = new int[array.length];
        S[0] = array[0];
        S[1] = Math.max(array[0], array[1]);
        int max = Math.max(S[0], S[1]);
        for (int i=2; i < array.length; i++) {
            S[i] = Math.max(array[i], S[i-2] + array[i-2]);
            max = Math.max(max, S[i]);
        }
        System.out.println("Max value of longest subsequence such that no 3 numbers are together " + max);
    }

    // Max value subsequence where we should not select 2 contiguous numbers
    // Can be viewed as which element we want to skip (i-1) or (i-2) or the whole prefix
    public void maxValCSNoThreeOptimal(int[] array) {
        int[] S = new int[array.length];
        S[0] = array[0];
        S[1] = Math.max(array[0] + array[1], array[1]);
        S[2] = Math.max(array[2], Math.max(array[2] + S[0], array[1] + array[2]));
        int max = Math.max(S[0], Math.max(S[1], S[2]));
        for (int i=0; i < array.length; i++) {
            S[i] = Math.max(array[i-1], Math.max(array[i-1] + S[i-2], array[i-1] + array[i-2] + S[i-3]));
            max = Math.max(max, S[i]);
        }
        System.out.println("Max value of longest subsequence such that no 3 numbers are together " + max);
    }

    public void countAllBSTs(int vertices) {
        int[] N = new int[vertices+1];
        N[0] = 1;
        N[1] = 1;
        for (int i=2; i <= vertices; i++) {
            for (int v=1; v <= i; v++) {
                N[i] = N[i] + N[v-1] * N[i-v];
            }
        }
        System.out.println("Number of BSTs with " + vertices + " vertices is " + N[vertices]);
    }

    // Given a bunch of matrices with (x,y) dim
    public void matrixPdtParenthesization(int[] xdim, int[] ydim) {
        // Define subproblem E[i,j] which is the expense of spliting matrix pdt from ith to jth pos
        // E[i,j] will have an associated Cost function C[i,k,j] for each point we chose to draw brackets
        int[][] E = new int[xdim.length+1][ydim.length+1];
        for (int j=0; j <= xdim.length; j++) {
            E[0][j] = (int) Double.POSITIVE_INFINITY;
        }

        for (int i=1; i <= xdim.length; i++) {
            for (int j=i; j <= ydim.length; j++) {
                E[i][j] = (int) Double.POSITIVE_INFINITY;
                if (i == j || (j-i) == 1)
                    E[i][j] = 0;
                for (int k = i+1; k < j; k++) {
                    E[i][j] = Math.min(E[i][j], E[i][k] + E[i][j] + xdim[i] * ydim[k-1] * ydim[j]);
                }
            }
        }
    }

    public void matrixPdtBracketsRecurse(int[] xdim, int[] ydim) {
        int[][] E = new int[xdim.length+1][ydim.length+1];
        int[][] pred = new int[xdim.length+1][ydim.length+1];
        matrixPdtBracketsRecurse(0, xdim.length, xdim, ydim, E, pred);
        printBrackets(0, xdim.length, pred);
    }

    private void printBrackets(int i, int j, int[][] pred) {
        if (i == j || (j-i) == 1)
            return;
        int k = pred[i][j];
        System.out.println("Draw bracket at pos " + k);
        if (k > i && k < j) {
            printBrackets(i, k, pred);
            printBrackets(k, j, pred);
        }
    }

    private int matrixPdtBracketsRecurse(int i, int j, int[] xdim, int[] ydim, int[][] E, int[][] pred) {
        if (i == j || (j-i) == 1)
            return 0;
        if (E[i][j] > 0)
            return E[i][j];
        int min_expense = 9999999;
        int mink = -1;
        for (int k = i+1; k < j; k++) {
            int expense = matrixPdtBracketsRecurse(i, k, xdim, ydim, E, pred) + 
                matrixPdtBracketsRecurse(k, j, xdim, ydim, E, pred) +
                xdim[i] * ydim[k-1] * ydim[j-1];
            if (expense < min_expense)
                mink = k;
            min_expense = Math.min(expense, min_expense);
            System.out.println("Expense : " + min_expense);
        }
        E[i][j] = min_expense;
        pred[i][j] = mink;
        System.out.println("Added expense of (" + i + ", " + j + ") : " + E[i][j] + " at pos " + mink);
        return E[i][j];
    }

    // Duplicate items allowed
    public void infiniteKnapsack(int[] weight, int[] value, int S) {
        // Let M[j] be the max value with a knapsack of size j
        int[] M = new int[S+1];
        M[0] = 0;
        for(int currSize = 0; currSize <= S; currSize++) {
            for (int item=0; item < value.length; item++) {
                if (weight[item] <= currSize) {
                    M[currSize] = Math.max(M[currSize], M[currSize - weight[item]] + value[item]);
                }
            }
        }
        System.out.println("Max value that can be fit in knapsack of size " + S + " picking infinitely is " + M[S]);
    }

    public void finiteKnapsack(int[] weight, int[] value, int S) {
        // Previous approach wont wont size we cant repeat items
        // Define new subproblem M[i,j] : the max knapsack value such that
        // we selected items from = 0 ... i
        int[][] M = new int[S+1][value.length+1];
        for (int item = 0; item <= value.length; item++)
            M[0][item] = 0;
        for (int size = 0; size <= S; size++)
            M[size][0] = 0;
        for (int size = 1; size <= S; size++) {
            for (int item = 1; item <= value.length; item++) {
                if (weight[item-1] <= size) {
                    M[size][item] = Math.max(M[size - weight[item-1]][item-1] + value[item-1], M[size][item-1]);
                }
            }
        }
        int max = 0;
        for (int item = 1; item <= value.length; item++) {
            max = Math.max(max, M[S][item]);
        }
        System.out.println("Max value that can be fit in knapsack of size " + S + " picking finitely is " + max);
    }

    public void coinchange(int[] coins, int sum) {
        // Let N[s] be the min num of coins needed to make a sum of s
        int[] N = new int[sum+1];
        N[0] = 0; // Not possible to make sum 0 but to terminate DP we put as 0
        N[1] = 1; // Assume we are always given unit value coin
        for (int amount = 2; amount <= sum; amount++) {
            N[amount] = (int) Double.POSITIVE_INFINITY;
            for (int index=0; index < coins.length; index++) {
                if (coins[index] <= amount) {
                    N[amount] = Math.min(N[amount], amount/coins[index] + N[amount%coins[index]]);
                }
            }
        }
        System.out.println("Min number of coins needed to make up a sum of " + sum + " is " + N[sum]);
    }

    public void longestIncreasingSubsequence(int[] array) {
        // Let N[i] be the length of the LIS in sequence S[0:i] including S[i]
        int[] S = new int[array.length];
        int[] pred = new int[array.length];
        for (int length = 0; length < S.length; length++) // Init with worst case, decreasing array
            S[length] = 1;
        pred[0] = -1;
        for (int length = 1; length < array.length; length++) {
            int maxPred = -1;
            for (int prefix = 0; prefix <= length; prefix++) {
                if (array[length] > array[prefix]) { // Because strictly increasing
                    if (S[prefix] + 1 > S[length])
                        maxPred = prefix;
                    S[length] = Math.max(S[length], S[prefix] + 1);
                }
            }
            pred[length] = maxPred;
        }
        int maxlen = -1;
        int max = -1;
        for (int length=0; length < array.length; length++) {
            if (maxlen < S[length])
                max = length;
            maxlen = Math.max(maxlen, S[length]);
        }
        System.out.println("Length of the LIS is " + maxlen);
        System.out.println("LIS Sequence : " + printSequence(array, max, pred));
    }

    private String printSequence(int[]array, int max, int[] pred) {
        if (max < 0)
            return "";
        return printSequence(array, pred[max], pred) + " " + array[max];
    }

    public void boxStacking() {
        // Let H[i] be the tallest stack of boxes with box i on top and 
        // sort by decreasing order of base area and solve it as LIS
        
        // DOUBT: Once we sort by decreasing order of base area, and 
        // since rep is allowed why can't we just adding the heights 
        // in O(n) and if there are clash use the largest height block!
    }

    public void boxStackingWithoutRepeat() {
        // How do we do this??
    }

    public void buildingBridge(int[] citiesLeft, int[] citiesRight) {
        int[] citiesPos = new int[citiesLeft.length];
        for (int left = 0; left < citiesLeft.length; left++) {
            for (int right = 0; right < citiesRight.length; right++) {
                if (citiesLeft[left] == citiesRight[right]) {
                    citiesPos[left] = right;
                }
            }
        }
        System.out.println("Mapping cities pos array wrt first city : " + Arrays.toString(citiesPos));
        // Now that we have the match array, the min number of bridges is the 
        // same as the longest increasing subsequence
        longestIncreasingSubsequence(citiesPos);
    }

    // Given an array check if there is a subset which equals the given sum
    public void subsetSum(int[] array, int sum) {
        // Let S[i][j] be the subproblem where it returns 1 if subsequence (i,j) has 
        // subset with sum 1 else returns 0
        // Model it as a knapsack problem with size j and prefix (0,i)
        int[][] S = new int[array.length+1][sum+1];
        for (int i=0; i <= sum; i++) {
            S[0][i] = 0;
        }
        for (int i=1; i <= array.length; i++) {
            for (int j=0; j <= sum; j++) {
                if (array[i-1] < j) {
                    S[i][j] = Math.max(S[i-1][j], S[i-1][j - array[i-1]]);
                } else if (array[i-1] == j) { 
                    S[i][j] = 1;
                } else {
                    S[i][j] = S[i-1][j];
                }
            }
        }
        System.out.println("Does the subset contain a sum of " + sum + " : " + S[array.length][sum]);
        // TODO : Need to try and solve the nearest subset sum to k
    }

    public void halfSubsetSum() {
        // Basically the same as the partition problem
    }

    // Split the input set into two equal halves by value (sum)
    public void partitionSubset(int[] array) {
        // Problem can be reduced to finding a subset in array with sum
        // as half of the total sum of elements in array
        int total = 0;
        for (int i=0; i < array.length; i++)
            total += array[i];
        int sum = total/2; // matches with sum and sum +/- 1
        int[][] DP = new int[array.length+1][sum+1];
        int[][] pred = new int[array.length+1][sum+1];
        for (int j=0; j <= sum; j++) {
            DP[0][j] = 0;
        }
        for (int i=1; i <= array.length; i++) {
            for (int j=0; j <= sum; j++) {
                if (array[i-1] < j) {
                    if (DP[i-1][j] < DP[i-1][j-array[i-1]]) {
                        pred[i][j] = i;
                    }
                    DP[i][j] = Math.max(DP[i-1][j], DP[i-1][j-array[i-1]]);
                } else if (array[i-1] == j) {
                    DP[i][j] = 1;
                    pred[i][j] = i;
                } else {
                    DP[i][j] = DP[i-1][j];
                }
            }
        }
        System.out.println("Half Subset : " + findSubsets(array.length, sum, pred, array));
        System.out.println("Does a half splitting subset exist : " + DP[array.length][sum]);
    }

    private String findSubsets(int i, int j, int[][] pred, int[] array) {
        if (i < 0)
            return "";
        if (pred[i][j] == 0)
            return "" + findSubsets(i-1, j, pred, array);
        return array[i-1] + " " + findSubsets(i-1, j-array[i-1], pred, array);
    }

    public void booleanParenthesization(boolean[] bool, char[] op) {
        // Let T[i,j] be the subproblem which gives the max num of ways 
        // the subsequence [i,j] evaluates to 1
        // Since we need to get all max iterations as well, we will evaluate
        // F[i,j] the subproblem which gives the max num of ways 
        // the subsequence [i,j] evaluates to 0
        int[][] T = new int[bool.length][bool.length];
        int[][] F = new int[bool.length][bool.length];
        for (int i=0; i < bool.length; i++) {
            T[i][i] = bool[i] ? 1 : 0;
            F[i][i] = bool[i] ? 0 : 1;
        }
        for (int j=1; j < bool.length; j++) {
            for (int i=j-1; i >= 0; i--) {
                for (int k=i; k < j; k++) {
                    // case based on symbol of k
                    if (op[k] == '|') {
                        T[i][j] = T[i][j] +
                                    (T[i][k] + F[i][k]) * (T[k+1][j] + F[k+1][j]) - 
                                    F[i][k] * F[k+1][j]; // Atleast one of the subproblems must be true
                        F[i][j] = F[i][j] +
                                    F[i][k] * F[k+1][j]; // Both subproblems must be false
                    } else if (op[k] == '&') {
                        T[i][j] = T[i][j] + T[i][k] * T[k+1][j]; // Both subproblems must be true
                        F[i][j] = F[i][j] +
                                    (T[i][k] + F[i][k]) * (T[k+1][j] + F[k+1][j]) - 
                                    T[i][k] * T[k+1][j]; // Atleast one of the subproblems must be false
                    } else {
                        T[i][j] = T[i][j] +
                                    T[i][k] * F[k+1][j] + F[i][k] * T[k+1][j]; // Both the subproblems must differ
                        F[i][j] = F[i][j] +
                                    T[i][k] * T[k+1][j] + F[i][k] * F[k+1][j]; // Both the subproblems must be same
                    }
                }
            }
        }
        System.out.println("Max number of ways to put bracket such that expression evaluates to true : " 
            + T[0][bool.length-1]);
        System.out.println("Max number of ways to put bracket such that expression evaluates to false : " 
            + F[0][bool.length-1]);
    }

    // Transform A -> B
    public void editDistance(String A, String B) {
        // Let N[i,j] be the min num of corrections needed to correct/match
        // upto the ith character in A and jth character in B
        int[][] N = new int[A.length()+1][B.length()+1];

        // Fill d[][] in bottom up manner 
        for (int i=0; i <= A.length(); i++) { 
            for (int j=0; j <= B.length(); j++) { 
                // If first string is empty, only option is to 
                // insert all characters of second string 
                if (i==0) 
                    N[i][j] = j;  // Min. operations = j 
       
                // If second string is empty, only option is to 
                // remove all characters of second string 
                else if (j==0) 
                    N[i][j] = i; // Min. operations = i 
       
                // If last characters are same, ignore last char 
                // and recur for remaining string 
                else if (A.charAt(i-1) == B.charAt(j-1)) 
                    N[i][j] = N[i-1][j-1]; 
       
                // If the last character is different, consider all 
                // possibilities and find the minimum 
                else
                    N[i][j] = 1 + min(N[i][j-1],  // Insert 
                                      N[i-1][j],  // Remove 
                                      N[i-1][j-1]); // Replace 
            }
        }
        System.out.println("Mininum number of changes to convert " + A + " to " + B + " is " + N[A.length()][B.length()]);
    }

    private int min(int x,int y,int z) 
    { 
        if (x <= y && x <= z) return x; 
        if (y <= x && y <= z) return y; 
        else return z; 
    } 

    public void floydAlgorithm() {

    }

    // Consider a row of n coins of values v1 . . . vn, where n is even. We play a game against an opponent 
    // by alternating turns. In each turn, a player selects either the first or last coin from the row, 
    // removes it from the row permanently, and receives the value of the coin. Determine the maximum possible 
    // amount of money we can definitely win if we move first.
    // Ofcourse num of coins must be even
    public void optimalGameStrategy(int[] coins) {
        // M[i][j] - maximum money we can get picking from ith coin to the jth coin
        int[][] M = new int[coins.length][coins.length];
        for (int i=0; i < coins.length; i++) {
            M[i][i] = coins[i];
            if (i < coins.length-1)
                M[i][i+1] = Math.max(coins[i], coins[i+1]);
        }
        for (int i=2; i < coins.length; i++) {
            for (int j=0; (j + i) < coins.length; j++) {
                int x = j;
                int y = j + i;
                M[x][y] = Math.max(
                    coins[x] + Math.min(M[x+2][y], M[x+1][y-1]),  // key point to why min is what we want to find the max money
                    coins[y] + Math.min(M[x][y-2], M[x+1][y-1])   // we can DEFINITELY win, given the opponent plays smart too!
                );
                // System.out.format("M[%d][%d] = %d\n", x, y, M[x][y]);
            }
        }
        System.out.println("The max sum possible with the given coin sequence is " + M[0][coins.length-1]);
    }

    // Given a “2 x n” board and tiles of size “2 x 1”, count the number of ways to tile the given board using the 2 x 1 tiles. 
    // A tile can either be placed horizontally i.e., as a 1 x 2 tile or vertically i.e., as 2 x 1 tile.
    public void tilingProblem(int n) {
        // Let M[i] be the max num of tiles with i spaces available
        int[] M = new int[n+1];
        M[0] = 0;
        M[1] = 1;
        M[2] = 2;
        for (int i=3; i <=n; i++) {
            M[i] = M[i-1] + M[i-2];
        }
        // How cool, basically its the fibonnaci! Numbers eh!
        System.out.println("Max number of tile patterns possible with " + n + " spaces is " + M[n]);
    }

    public void longestPalindromeSubsequence(String s) {
        // P[i][j] - length of largest palindrome in the substring s[i:j]
        int[][] P = new int[s.length()][s.length()];
        for (int i=0; i < s.length(); i++) {
            P[i][i] = 1;
        }
        int max = 0;
        for (int i=1; i < s.length(); i++) {
            for (int j=0; (j+i) < s.length(); j++) {
                int x = j;
                int y = i+j;
                if (s.charAt(x) == s.charAt(y)) {
                    P[x][y] = 2 + P[x+1][y-1];
                } else {
                    P[x][y] = Math.max(P[x+1][y], P[x][y-1]); // we skip one of the ending strings and try again
                }
                max = Math.max(max, P[x][y]);
            }
        }
        System.out.println("Length of the longest palindrome subsequence : " + max);
    }

    public void longestPalindromeSubstring(String s) {
        // P[i][j] - length of largest palindrome in the substring s[i:j] INCLUDING i and j
        int[][] P = new int[s.length()][s.length()];
        for (int i=0; i < s.length(); i++) {
            P[i][i] = 1;
        }
        int max = 0;
        String maxStr = "";
        for (int i=1; i < s.length(); i++) {
            for (int j=0; (j+i) < s.length(); j++) {
                int x = j;
                int y = i+j;
                if (s.charAt(x) == s.charAt(y)) {
                    P[x][y] = 2 + P[x+1][y-1];
                }
                if (P[x][y] > max)
                    maxStr = s.substring(x, y+1);
                max = Math.max(max, P[x][y]);
                // System.out.format("P[%d][%d] = %d\n", x, y, P[x][y]);
            }
        }
        System.out.println("Length of the longest palindrome substring : " + max);
        System.out.println("Longest palindrome : " + maxStr);
    }

    // Given two strings, find the number of times the second string occurs in the first string, whether continuous or discontinuous.
    public void countStringAppearences(String s, String p) {
        // N[i][j] = Number of times pattern p[0:j] appears in s[0:i]
        int[][] N = new int[s.length()+1][p.length()];
        for (int i=1; i <= s.length(); i++) {
            if (s.charAt(i-1) == p.charAt(0)) {
                N[i][0] = 1 + N[i-1][0];
            } else {
                N[i][0] = N[i-1][0];
            }
        }
        for (int j=1; j < p.length(); j++) {
            for (int i=1; i <= s.length(); i++) {
                if (s.charAt(i-1) == p.charAt(j)) {
                    N[i][j] = N[i-1][j] + N[i-1][j-1];
                } else {
                    N[i][j] = N[i-1][j];
                }
                // System.out.format("N[%d][%d] = %d\n", i, j, N[i][j]);                
            }
        }
        System.out.println("Max num of appearences of " + p + " in " + s + " : " + N[s.length()][p.length()-1]);
    }

    // A table composed of N x M cells, each having a certain quantity of apples, is given. 
    // You start from the upper-left corner. At each step you can go down or right one cell. 
    // Find the maximum number of apples you can collect.
    public void maximizeApples(int[][] matrix) {
        // M[i][j] - be the maximum apples we can collect at pos(i,j)
        int[][] M = new int[matrix.length][matrix[0].length];
        M[0][0] = matrix[0][0];
        for (int i=1; i <= (matrix.length + matrix[0].length); i++) {
            for (int j=0; j <= i; j++) {
                int x = j;
                int y = i - j;
                if (x >= matrix.length || y >= matrix[0].length)
                    continue;
                if (x == 0) {
                    M[x][y] = M[x][y-1] + matrix[x][y];
                } else if (y == 0) {
                    M[x][y] = M[x-1][y] + matrix[x][y];
                } else {
                    M[x][y] = Math.max(M[x-1][y], M[x][y-1]) + matrix[x][y];
                }
                // System.out.format("M[%d][%d] = %d\n", x, y, M[x][y]);                
            }
        }
        System.out.println("Max num of apples that can be collected : " + M[matrix.length-1][matrix[0].length-1]);
    }

    // A table composed of N x M cells, each having a certain quantity of apples, is given. 
    // You start from the upper-left corner. At each step you can go down or right or diagonal one cell. 
    // Find the maximum number of apples you can collect.
    public void maximizeApplesWithDiag(int[][] matrix) {
        // M[i][j] - be the maximum apples we can collect at pos(i,j)
        int[][] M = new int[matrix.length][matrix[0].length];
        M[0][0] = matrix[0][0];
        for (int i=1; i <= (matrix.length + matrix[0].length); i++) {
            for (int j=0; j <= i; j++) {
                int x = j;
                int y = i - j;
                if (x >= matrix.length || y >= matrix[0].length)
                    continue;
                if (x == 0) {
                    M[x][y] = M[x][y-1] + matrix[x][y];
                } else if (y == 0) {
                    M[x][y] = M[x-1][y] + matrix[x][y];
                } else {
                    M[x][y] = matrix[x][y] + Math.max(M[x-1][y], Math.max(M[x][y-1], M[x-1][y-1]));
                }
                // System.out.format("M[%d][%d] = %d\n", x, y, M[x][y]);                
            }
        }
        System.out.println("Max num of apples that can be collected with diagonal moves : " + M[matrix.length-1][matrix[0].length-1]);
    }

    public void maxSizeSquareSubMatrix(int[][] matrix) {
        // A[i,j] -> Size of square matrix with bottom right edge at (i,j)
        int[][] A = new int [matrix.length][matrix[0].length];
        int max = 0;
        for (int i=0; i <= (matrix.length + matrix[0].length); i++) {
            for (int j=0; j <= i; j++) {
                int x = j;
                int y = i-j;
                if (x >= matrix.length || y >= matrix[0].length)
                    continue;
                if (x == 0 || y ==0) {
                    A[x][y] = matrix[x][y];
                } else if (matrix[x][y] == 1) {
                    A[x][y] = 1 + Math.min(A[x-1][y], Math.min(A[x-1][y-1], A[x][y-1]));
                }
                // System.out.format("A[%d][%d] = %d\n", x, y, A[x][y]);                
                max = Math.max(max, A[x][y]);
            }
        }
        System.out.println("Max size of the square submatrix of 1s : " + max);
    }

    public void maxSizeSubMatrix(int[][] matrix) {
        // L[i,j] - max length rectangle with bottom right edge at (i,j)
        // B[i,j] - max breadth rectangle with bottom right edge at (i,j)
        int[][] L = new int[matrix.length][matrix[0].length];
        int[][] B = new int[matrix.length][matrix[0].length];
        L[0][0] = B[0][0] = matrix[0][0];
        int max = 0;
        for (int i=1; i <= (matrix.length + matrix[0].length); i++) {
            for (int j=0; j <= i; j++) {
                int x = j;
                int y = i-j;
                if (x >= matrix.length || y >= matrix[0].length)
                    continue;
                if (matrix[x][y] == 1) {
                    if (x == 0) {
                        L[x][y] = matrix[x][y];
                        B[x][y] = matrix[x][y] + B[x][y-1];
                    } else if (y==0) {
                        L[x][y] = matrix[x][y] + L[x-1][y];
                        B[x][y] = matrix[x][y];
                    } else {
                        L[x][y] = matrix[x][y] + L[x-1][y];
                        B[x][y] = matrix[x][y] + B[x][y-1];
                    }
                }
                // System.out.format("L[%d][%d] = %d\n", x, y, L[x][y]);
                // System.out.format("B[%d][%d] = %d\n", x, y, B[x][y]);
                // System.out.println("--------------------------------");
                max = Math.max(max, L[x][y] * B[x][y]);
            }
        }
        // Note - this approach is wrong, use the other one only!
        System.out.println("Max size of the submatrix of 1s : " + max);
        // Another cool way to solve this is to transform the matrix to represent L,
        // then we go through every row and use the max rectangle area using stacks
    }

    public void maxSumSubMatrix(int[][] matrix) {

    }

    // Alice is a kindergarten teacher. She wants to give some candies to the children in her class.  
    // All the children sit in a line and each of them has a rating score according to his or her 
    // performance in the class.  Alice wants to give at least 1 candy to each child. If two children 
    // sit next to each other, then the one with the higher rating must get more candies. Alice wants 
    // to minimize the total number of candies she must buy.
    // For example, assume her students' ratings are [4, 6, 4, 5, 6, 2]. She gives the students candy 
    // in the following minimal amounts: [1, 2, 1, 2, 3, 1]. She must buy a minimum of 10 candies.
    public void candyProblem() {

    }

    public void optimalNumJumps(int[] jumps) {
        // M[i] - Min num of jumps needed to reach end from ith pos
        int[] M = new int[jumps.length];
        M[jumps.length-1] = 0;
        for (int i=jumps.length-2; i >=0; i--) {
            M[i] = (int) Double.POSITIVE_INFINITY;
            int dist = jumps.length-1-i;
            System.out.println(jumps[i]);
            for (int j=jumps[i]; j > 0; j--) {
                if (j <= dist) {
                    M[i] = Math.min(1 + M[i+j], M[i]);
                }
            }
            // System.out.format("M[%d] = %d\n", i, M[i]);
        }
        System.out.println("Optimal num of jumps to reach the end : " + M[0]);
    }

    public void giftDistribution() {

    }

    public static void main(String[] args) {
        DynamicProgramming dp = new DynamicProgramming();
        // dp.eggDrop(100, 2);
        dp.eggDropBottomUp(100, 2);
        dp.maxValueContSubsequence(new int[] {-2, 11, -4, -1, 13, -5, 2});
        // dp.maxValueContSubsequence(new int[] {-2, -4, -3, -2, -5, -1});
        dp.DPProblem1(3);
        dp.maxValContSubSeqOpt(new int[] {-2, 11, -4, -1, 13, -5, 2});
        dp.maxValContSubSeqOpt(new int[] {-2, -4, -3, -2, -5, -1});
        dp.countAllBSTs(5);
        dp.matrixPdtBracketsRecurse(new int[] {10, 30, 5}, new int[] {30, 5, 60});
        dp.infiniteKnapsack(new int[] {1, 3, 4, 5}, new int[] {10, 40, 50, 70}, 8);
        dp.finiteKnapsack(new int[] {24, 10, 10, 7}, new int[] {24, 18, 18, 10}, 25);
        dp.coinchange(new int[] {9, 6, 5, 1}, 11);
        // dp.longestIncreasingSubsequence(new int[] {10, 22, 9, 33, 21, 50, 41, 60, 80});
        dp.longestIncreasingSubsequence(new int[] {5, 6, 2, 3, 4, 1, 9, 9, 8, 9, 5});
        dp.buildingBridge(new int[] {8, 1, 4, 3, 5, 2, 6, 7}, new int[] {1, 2, 3, 4, 5, 6, 7, 8});
        dp.subsetSum(new int[] {1, 1, 5, 5}, 2);
        dp.partitionSubset(new int[] {3, 1, 5, 9, 12});
        dp.booleanParenthesization(new boolean[] {true, true, false, true}, new char[] {'|', '&', '^'});
        dp.editDistance("allen", "lens");
        dp.optimalGameStrategy(new int[] {1, 2, 3, 4, 5, 6});
        dp.tilingProblem(4);
        dp.longestPalindromeSubstring("forgeeksskeegfor");
        dp.longestPalindromeSubsequence("BBABCBCAB");
        dp.longestPalindromeSubsequence("GEEKSFORGEEKS");
        dp.countStringAppearences("GeeksforGeeks", "Gks");
        dp.maximizeApples(new int[][] {{1, 3, 2, 4},
                                       {3, 1, 2, 2},
                                       {2, 1, 3, 2}});
        dp.maximizeApplesWithDiag(new int[][] {{1, 3, 2, 4},
                                               {3, 1, 2, 2},
                                               {2, 1, 3, 2}});
        dp.maxSizeSquareSubMatrix(new int[][] {{1, 0, 1, 1, 0},
                                               {0, 1, 1, 1, 1},
                                               {1, 1, 1, 1, 1},
                                               {0, 1, 1, 1, 1},
                                               {1, 1, 0, 0, 1}});
        dp.maxSizeSubMatrix(new int[][] {{1, 0, 1, 1, 0},
                                         {0, 1, 1, 1, 1},
                                         {1, 1, 1, 1, 1},
                                         {0, 1, 1, 1, 1},
                                         {1, 1, 0, 0, 1}});
        dp.optimalNumJumps(new int[] {2, 3, 1, 1, 4});
    }
}