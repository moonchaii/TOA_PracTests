# Integrated Dynamic Programming Study Guide

## 1. The Core Philosophy of DP

Dynamic Programming (DP) is an optimization over plain recursion. It is used when a problem has two key properties:

1. **Optimal Substructure:** The optimal solution to the main problem can be constructed from optimal solutions of its subproblems.


2. **Overlapping Subproblems:** The recursive solution solves the same subproblems repeatedly.



---

## 2. The Two Main Techniques & Universal Templates

Every DP problem can be implemented in one of two ways.

### A. Top-Down (Memoization)

You write a standard recursive function but use a data structure (usually a dictionary or array) to cache the results of expensive function calls.

* **Pros:** Intuitive if you are comfortable with recursion; only computes subproblems that are strictly necessary.


* **Cons:** Slower due to recursion overhead (function call stack) and risk of stack overflow on massive inputs.



```java
class DPMemoization {
    int[] memo;
    int[] cost; // Assume cost array is initialized elsewhere
    
    public int solveDpMemo(int n) {
        memo = new int[n + 1];
        // Initialize memo array with -1 to indicate uncomputed states
        Arrays.fill(memo, -1); 
        return dp(n);
    }
    
    private int dp(int stateVar) {
        // 1. Base Case(s): The stopping conditions
        if (stateVar == 0) return 0; 
        if (stateVar == 1) return 1;
        
        // 2. Check Cache: Have we solved this subproblem already?
        if (memo[stateVar] != -1) {
            return memo[stateVar];
        }
            
        // 3. Recurrence Relation: Calculate the answer
        int ans = Math.min(dp(stateVar - 1), dp(stateVar - 2)) + cost[stateVar];
        
        // 4. Save and Return
        memo[stateVar] = ans;
        return ans;
    }
}

```

### B. Bottom-Up (Tabulation)

You build a table (array or matrix) iteratively, starting from the smallest base cases up to the final target problem.

* **Pros:** Very fast (no recursion overhead); easy to analyze time and space complexity.


* **Cons:** Might compute states that are never actually needed for the final answer.



```java
public int solveDpTabulation(int n, int[] cost) {
    // 1. Initialize Table: Size is usually (N + 1) to handle the 0th base case
    int[] dp = new int[n + 1]; // Java initializes arrays to 0 by default
    
    // 2. Set Base Case(s)
    dp[0] = 0;
    if (n >= 1) {
        dp[1] = 1;
    }
    
    // 3. Iterate from the bottom up to the target
    for (int i = 2; i <= n; i++) {
        // 4. Recurrence Relation
        dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
    }
        
    // 5. Return final target
    return dp[n];
}

```

---

## 3. The 5-Step Problem Solving Framework

When working through DP problems, apply these 5 steps before coding:

1. **Define the State Variable(s):** What parameters dictate the current state? (e.g., index $i$, remaining weight $w$).


2. **Find the Recurrence Relation:** How do you calculate the current state from previous states?.


3. **Identify the Base Cases:** What are the smallest subproblems?.


4. **Determine the Order of Evaluation (for Tabulation):** Ensure dependencies are computed prior to current state.


5. **Identify Where the Answer Lives:** Is the final answer at $dp[n]$, $dp[r][c]$, or a global maximum?.



---

## 4. Common DP Patterns & Concrete Slide Applications

---

### Pattern 1: The 1D Sequence (Space Optimized)

**How to Recognize:** Finding optimal values along an array or string where state at step $i$ depends only on a fixed number of previous steps (e.g., $i-1$ and $i-2$). Space can be optimized to $O(1)$ by keeping only $k$ variables instead of a full 1D array.

**Pattern Blueprint:**

```java
public int sequenceOptimized(int n) {
    if (n == 0) return 0;
    if (n == 1) return 1;
    
    int prev2 = 0; // Represents dp[i-2]
    int prev1 = 1; // Represents dp[i-1]
    
    for (int i = 2; i <= n; i++) {
        int curr = prev1 + prev2; // Transition
        
        // Shift window
        prev2 = prev1;
        prev1 = curr;
    }
        
    return prev1;
}

```

#### Slide Application 1A: Fibonacci Numbers

* **Slide Problem:** Compute $F(n) = F(n-1) + F(n-2)$.


* **Connection:** Direct 1D linear sequence looking back 2 steps.


* **Recurrence:** $F[i] = F[i-1] + F[i-2]$ with $F[0] = 0, F[1] = 1$.



#### Slide Application 1B: The Coin-Row Problem

* **Slide Problem:** Given a row of $n$ coins with values $C_1, C_2, \dots, C_n$, pick coins to maximize total value under the restriction that **no two adjacent coins can be picked**. (This is equivalent to *House Robber* or *Climbing Stairs*).


* **How it Links to Pattern 1:** Yes, this is **"the coin one"** matching Pattern 1. At coin $i$, you have a binary decision:
1. **Pick coin $i$:** Gain value $C[i]$, skip coin $i-1$, and add optimal value $F(i-2)$.


2. **Skip coin $i$:** Take maximum value accumulated up to $F(i-1)$.




* **Recurrence Relation:**

$$F(i) = \max(C[i] + F(i-2), \; F(i-1))$$



with base cases $F(0) = 0$ and $F(1) = C[1]$.


* **Java Implementation:**

```java
public int coinRow(int[] C) {
    int n = C.length - 1; // 1-based indexing for coins
    if (n == 0) return 0;
    if (n == 1) return C[1];
    
    int[] F = new int[n + 1];
    F[0] = 0;
    F[1] = C[1];
    
    for (int i = 2; i <= n; i++) {
        F[i] = Math.max(C[i] + F[i - 2], F[i - 1]);
    }
    return F[n];
}

```

---

### Pattern 2: 2D Grid Traversal

**How to Recognize:** Moving through an $n \times m$ matrix with directional movement constraints (e.g., only right or down). State $(r, c)$ depends on $(r-1, c)$ (above) and $(r, c-1)$ (left).

**Pattern Blueprint:**

```java
public int gridDp(int[][] grid) {
    int rows = grid.length;
    int cols = grid[0].length;
    int[][] dp = new int[rows][cols];
    
    dp[0][0] = grid[0][0];
    
    for (int c = 1; c < cols; c++) dp[0][c] = dp[0][c - 1] + grid[0][c];
    for (int r = 1; r < rows; r++) dp[r][0] = dp[r - 1][0] + grid[r][0];
        
    for (int r = 1; r < rows; r++) {
        for (int c = 1; c < cols; c++) {
            dp[r][c] = Math.min(dp[r - 1][c], dp[r][c - 1]) + grid[r][c];
        }
    }
    return dp[rows - 1][cols - 1];
}

```

#### Slide Application 2: Coin Collecting Robot

* **Slide Problem:** A robot on an $n \times m$ board collects coins placed at various cells while moving only **one cell right** or **one cell down** from $(1,1)$ to $(n,m)$.


* **How it Links to Pattern 2:** Cell $(i,j)$ can only be reached from $(i-1, j)$ or $(i, j-1)$.


* **Recurrence Relation:**

$$F(i,j) = \max(F(i-1,j), \; F(i,j-1)) + C_{ij}$$



with boundary conditions $F(i,0) = 0$ and $F(0,j) = 0$.


* **Java Implementation:**

```java
public int robotCoinCollection(int[][] C) {
    int n = C.length;
    int m = C[0].length;
    int[][] F = new int[n + 1][m + 1];
    
    // Base cases (row 0 and col 0 are default 0 in Java)
    F[1][1] = C[0][0];
    for (int j = 2; j <= m; j++) F[1][j] = F[1][j - 1] + C[0][j - 1];
    for (int i = 2; i <= n; i++) F[i][1] = F[i - 1][1] + C[i - 1][0];
    
    for (int i = 2; i <= n; i++) {
        for (int j = 2; j <= m; j++) {
            F[i][j] = Math.max(F[i - 1][j], F[i][j - 1]) + C[i - 1][j - 1];
        }
    }
    return F[n][m];
}

```

---

### Pattern 3: Knapsack & Subset Selection (0/1 vs Unbounded)

**How to Recognize:** Selecting elements from a set subject to capacity or target value constraints.

#### Sub-Pattern 3A: 0/1 Knapsack (Include / Exclude)

**Constraint:** Each item can be used **at most once**.

**Pattern Blueprint:**

```java
public int knapsack01(int[] weights, int[] values, int maxCapacity) {
    int n = weights.length;
    int[][] dp = new int[n + 1][maxCapacity + 1];
    
    for (int i = 1; i <= n; i++) {
        for (int w = 1; w <= maxCapacity; w++) {
            if (weights[i - 1] <= w) {
                dp[i][w] = Math.max(dp[i - 1][w], values[i - 1] + dp[i - 1][w - weights[i - 1]]);
            } else {
                dp[i][w] = dp[i - 1][w];
            }
        }
    }
    return dp[n][maxCapacity];
}

```

#### Slide Application 3A: Classical 0/1 Knapsack

* **Slide Problem:** Given items with weights $w_1, \dots, w_n$ and values $v_1, \dots, v_n$, maximize value inside a knapsack of capacity $W$.


* **Recurrence Relation:**

$$V[i,j] = \begin{cases} V[i-1, j] & \text{if } j < w_i \\ \max(V[i-1, j], \; v_i + V[i-1, j - w_i]) & \text{if } j \ge w_i \end{cases}$$



* **Java Top-Down Implementation (Memory Function / Memoization):**

```java
public class KnapsackMemoryFunction {
    int[][] memo;
    int[] w, v;

    public int solve(int n, int W, int[] weights, int[] values) {
        this.w = weights;
        this.v = values;
        memo = new int[n + 1][W + 1];
        for (int[] row : memo) Arrays.fill(row, -1);
        for (int j = 0; j <= W; j++) memo[0][j] = 0;
        for (int i = 0; i <= n; i++) memo[i][0] = 0;
        return sack(n, W);
    }

    private int sack(int i, int j) {
        if (memo[i][j] < 0) {
            int val;
            if (j < w[i - 1]) {
                val = sack(i - 1, j);
            } else {
                val = Math.max(sack(i - 1, j), v[i - 1] + sack(i - 1, j - w[i - 1]));
            }
            memo[i][j] = val;
        }
        return memo[i][j];
    }
}

```

#### Sub-Pattern 3B: Unbounded Knapsack / Target Sum

**Constraint:** Items (e.g., coin denominations $D = [d_1, d_2, \dots, d_m]$) have **unlimited supply**.

#### Slide Application 3B: Change-Making Problem

* **Slide Problem:** Given coin denominations $d_1 < d_2 < \dots < d_m$, make exact change for amount $n$ using the **minimum number of coins**.


* **How it Links to Knapsack:** Target amount $n$ acts as capacity; denominations are reusable items.


* **Recurrence Relation:**

$$F(n) = \min_{j: n \ge d_j} \{ F(n - d_j) \} + 1$$



with base case $F(0) = 0$.


* **Java Implementation:**

```java
public int makeChange(int n, int[] D) {
    int[] F = new int[n + 1];
    F[0] = 0;
    
    for (int i = 1; i <= n; i++) {
        int temp = Integer.MAX_VALUE - 1; // Prevent overflow on addition
        for (int coin : D) {
            if (i >= coin) {
                temp = Math.min(temp, F[i - coin]);
            }
        }
        F[i] = temp + 1;
    }
    return F[n];
}

```

---

### Pattern 4: Graph DP & Intermediate States (Warshall & Floyd)

**How to Recognize:** Computations over graph matrices where subproblems permit paths using only a subset of intermediate vertices $\{1, 2, \dots, k\}$.

#### Slide Application 4A: Warshall's Algorithm (Transitive Closure)

* **Slide Problem:** Determine reachability (Boolean matrix $T$) between all vertex pairs.


* **Recurrence Relation:**

$$R^{(k)}[i,j] = R^{(k-1)}[i,j] \; \lor \; \left(R^{(k-1)}[i,k] \; \land \; R^{(k-1)}[k,j]\right)$$



* **Complexity:** Time $\Theta(n^3)$, Space $\Theta(n^2)$.



#### Slide Application 4B: Floyd's Algorithm (All-Pairs Shortest Path)

* **Slide Problem:** Find shortest path distances between every pair of vertices in a weighted graph.


* **Recurrence Relation:**

$$D^{(k)}[i,j] = \min\left( D^{(k-1)}[i,j], \; D^{(k-1)}[i,k] + D^{(k-1)}[k,j] \right)$$



* **Java Implementation:**

```java
public int[][] floydsAlgorithm(int[][] W, int n) {
    int[][] D = new int[n + 1][n + 1];
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            D[i][j] = W[i][j];
        }
    }
    
    for (int k = 1; k <= n; k++) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
            }
        }
    }
    return D;
}

```

---

## 5. Summary Matrix & Pattern Connections

| Slide Problem | Universal Pattern | State Dimension | Core Choice at Each Step | Key Recurrence Formula |
| --- | --- | --- | --- | --- |
| **Fibonacci**<br> | Pattern 1 (1D Sequence) | 1D ($n$) | Sum of previous 2 terms | $F[i] = F[i-1] + F[i-2]$<br> |
| **Coin-Row Problem**<br> | Pattern 1 (1D Sequence / House Robber) | 1D ($i$) | Take Coin $i$ ($+F[i-2]$) OR Skip Coin $i$ ($F[i-1]$) | $F[i] = \max(C[i] + F[i-2], F[i-1])$<br> |
| **Change-Making**<br> | Unbounded Knapsack / Target Value | 1D ($i$) | Try each denomination $d_j \le i$, pick min $+ 1$<br> | $F[i] = \min_{j} \{ F[i-d_j] \} + 1$<br> |
| **Coin Collecting Robot**<br> | Pattern 2 (2D Grid Traversal) | 2D ($i, j$) | Arrive from Top $(i-1, j)$ OR Left $(i, j-1)$<br> | $F[i,j] = \max(F[i-1,j], F[i,j-1]) + C_{ij}$<br> |
| **0/1 Knapsack**<br> | Pattern 3 (Include / Exclude) | 2D ($i, w$) | Take Item $i$ ($+v_i$) OR Leave Item $i$<br> | $V[i,j] = \max(V[i-1,j], v_i + V[i-1,j-w_i])$<br> |
| **Warshall / Floyd**<br> | Graph DP / Intermediate Vertex | 2D/3D ($i, j, k$) | Bridge via intermediate vertex $k$ OR bypass | $D^{(k)}[i,j] = \min(D^{(k-1)}[i,j], D^{(k-1)}[i,k] + D^{(k-1)}[k,j])$<br> |

---

## 6. Pro-Tips for Practical Tests

* **Look at Constraints:**
* $N \le 20 \implies$ Pure recursion or backtracking.


* $N \le 500 \text{ or } 1000 \implies$ 2D DP $O(N^2)$ or $O(N^3)$ (e.g., Grid DP, Knapsack, Warshall/Floyd).


* $N \ge 10^5 \implies$ 1D DP $O(N)$ or space-optimized $O(1)$ (e.g., Coin-Row, Fibonacci).




* **Beware Off-by-One Errors:** Size tables to $N+1$ or $(N+1) \times (M+1)$ so index $0$ represents the empty set or base case.


* **Initialize Sensibly:** Use infinity (`Integer.MAX_VALUE - 1`) for minimums (Change-Making, Floyd) to prevent integer overflow during addition. Use $0$ or negative infinity for maximums.


* **Draw Table & Backtrack:** Draw a $4 \times 4$ grid on paper to verify transitions. To reconstruct actual selected items rather than just maximum values, trace backwards by checking whether $V[i,j] \neq V[i-1,j]$ (item included) or comparing left vs top cells.