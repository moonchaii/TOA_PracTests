# LeetCode Patterns & Algorithm Design Guide

---

## 1. Core Brute-Force & Search Patterns

### Pattern A: Backtracking & State Space Search

* **Used For:** Permutations, Subsets / 0-1 Knapsack, Combinations, N-Queens.
* **Problem Indicators:** *"Find all combinations/permutations"*, *"Can we partition the array into equal subsets?"*, or tight constraints ($N \le 15$ or $N \le 20$).
* **Standard Template:**

```java
private void backtrack(int[] nums, int start, List<Integer> path, List<List<Integer>> result) {
    // 1. Base case / Target condition reached
    if (/* path meets target requirement */) {
        result.add(new ArrayList<>(path)); // Always deep copy!
        return;
    }

    // 2. Loop over candidate options
    for (int i = start; i < nums.length; i++) {
        // [Optional] Constraint check / Pruning (e.g., skip duplicates)
        
        // Step 1: Choose
        path.add(nums[i]);
        
        // Step 2: Explore
        // Use 'i + 1' for unique combinations/subsets, or 'start' if elements can be reused
        backtrack(nums, i + 1, path, result);
        
        // Step 3: Un-choose (Backtrack)
        path.remove(path.size() - 1);
    }
}

```

---

### Pattern B: Fixed-Size Sliding Window

* **Used For:** Fixed-length substring search, Repeated DNA Sequences, fixed sub-array queries.
* **Problem Indicators:** *"Find substrings of length $K$"*, *"Count distinct sequences of fixed size $L$"*.
* **Standard Template:**

```java
int k = 10; // Fixed window size
for (int i = 0; i <= s.length() - k; i++) {
    String window = s.substring(i, i + k);
    // Process window or add to HashSet / HashMap
}

```

---

### Pattern C: Pairwise & Geometric Distance Checks

* **Used For:** $K$ Closest Points, Two Sum, Pairwise point comparisons.
* **Problem Indicators:** *"Points on a 2D plane"*, *"Distance to origin"*, or *"Find pairs matching a target sum"*.
* **Optimization Rule:** Omit `Math.sqrt()` or `Math.pow()` when comparing Euclidean distances:

$$\text{Dist}_1 < \text{Dist}_2 \iff (x_1^2 + y_1^2) < (x_2^2 + y_2^2)$$


* **Standard Template:**

```java
// Custom sort using squared Euclidean distance: x^2 + y^2
Arrays.sort(points, (a, b) -> Integer.compare(
    a[0] * a[0] + a[1] * a[1], 
    b[0] * b[0] + b[1] * b[1]
));

```

---

### Pattern D: Grid Traversal (DFS / BFS)

* **Used For:** Matrix exploration, Number of Islands, Connected components.
* **Problem Indicators:** 2D grid containing boundaries and connectivity rules (e.g., adjacent `'1'`s).
* **Standard Template:**

```java
private void dfs(char[][] grid, int r, int c) {
    // 1. Boundary check and visited/validity check
    if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] != '1') {
        return;
    }
    
    // 2. Mark visited in-place
    grid[r][c] = '0';
    
    // 3. Explore 4 orthogonal directions
    dfs(grid, r + 1, c);
    dfs(grid, r - 1, c);
    dfs(grid, r, c + 1);
    dfs(grid, r, c - 1);
}

```

---

## 2. Decision Framework for Algorithm Design

Follow these three steps when evaluating a new problem statement:

### Step 1: Analyze Input Constraints ($N$)

* **$N \le 10$:** $\mathcal{O}(N!)$ — Permutations, N-Queens.
* **$N \le 20$:** $\mathcal{O}(2^N)$ — Subsets, 0-1 Knapsack backtracking.
* **$N \le 10^3$:** $\mathcal{O}(N^2)$ — Nested loops, 2D matrix iteration, pairwise distance calculations.
* **$N \le 10^5$:** $\mathcal{O}(N)$ or $\mathcal{O}(N \log N)$ — Linear scans, HashSets, Sorting, Sliding Window, Two Pointers.

### Step 2: Classify Choice Type

* **Binary Choice (Include / Exclude item $i$):** Subset / Knapsack pattern.
* **Ordered Selection Without Replacement:** Permutation pattern.
* **Contiguous Subsegment:** Sliding Window or Two Pointers pattern.

### Step 3: Define Recursive State Variables

Determine what parameters must be tracked across function calls:

* `remainingWeight` / `currentSum` (Knapsack)
* `used[]` boolean array (Permutations)
* `visited[][]` grid / `path` stack (Graph / Matrix search)

---

## 3. Essential Java Standard Library Utilities

### Sets & Maps

* **`Set<String> seen = new HashSet<>()`**
`seen.add(item)` returns `false` if `item` already exists. Useful for single-pass duplicate detection.
* **`Map<K, V> map = new HashMap<>()`**
Use `map.getOrDefault(key, defaultValue)` to simplify frequency counting.

### Arrays & Sorting

* **`Arrays.sort(arr, (a, b) -> Integer.compare(valA, valB))`**
Custom comparator sorting for 2D arrays. Use `Integer.compare()` to prevent arithmetic overflow from subtraction (`a - b`).
* **`Arrays.copyOfRange(arr, start, end)`**
Extract sub-arrays directly.

### Heaps & Priority Queues

* **`PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]))`**
Maintains top-$K$ min/max elements without needing full array sorts ($\mathcal{O}(N \log k)$ runtime).