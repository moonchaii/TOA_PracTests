### **Conquer Algorithms**

---

## 1. How They Differ

### **Decrease-and-Conquer**

* **Core Idea:** Reduce a problem of size $n$ to a **single smaller subproblem** of size $n - 1$, $n - k$, or $n/k$. Solve that single subproblem, then extend the solution.
* **When to Use:** Problems where removing one element or dividing the search space in half reduces the entire remaining task to a single sub-task.
* **Classic Examples:**
* Binary Search (reduces problem from $n$ to $n/2$ in 1 branch)
* Insertion Sort (reduces problem from $n$ to $n-1$)
* Graph Traversal (DFS/BFS)


* **Code Characteristics:** Usually implemented with a simple `while` loop or single recursive call.

### **Divide-and-Conquer**

* **Core Idea:** Divide a problem of size $n$ into **two or more independent subproblems** of size $n/k$, solve each recursively, and **combine** their solutions.
* **When to Use:** Problems that can be naturally partitioned into independent sub-parts whose individual answers can be merged back together.
* **Classic Examples:**
* Merge Sort (divide into 2 halves, sort both, merge)
* Quick Sort (partition around pivot, sort both sides)
* Strassen's Matrix Multiplication


* **Code Characteristics:** Requires multiple recursive calls (e.g., `solve(left)` and `solve(right)`) plus an explicit **Combine/Merge** step.

### **Transform-and-Conquer**

* **Core Idea:** Transform the problem representation into a form that is easier or faster to solve, then solve it in that new representation.
* **When to Use:** When a direct brute-force solution is too slow, but pre-processing or restructuring the input drastically simplifies computation.
* **Three Common Variants:**
1. **Instance Simplification:** Presort an array to find duplicates in $O(n \log n)$ instead of $O(n^2)$, or use a BST.
2. **Representation Change:** Convert a graph into an adjacency list/matrix, or turn polynomials into Horner’s rule format.
3. **Problem Reduction:** Map a new problem to a well-known solved problem (e.g., reducing Least Common Multiple to GCD).


* **Code Characteristics:** Includes a pre-processing step (like sorting or structure initialization) followed by a simpler loop or query.

---

## 2. Quick Comparison

| Paradigm | Subproblems Created | Key Complexity Bottleneck | Best Used For |
| --- | --- | --- | --- |
| **Decrease-and-Conquer** | **1** smaller subproblem | Decreasing step | Searching, array processing, graph traversals |
| **Divide-and-Conquer** | **$\ge 2$** smaller subproblems | Combination / Merge step | Sorting, divide-and-merge structures, tree operations |
| **Transform-and-Conquer** | **1** transformed instance | Transformation step | Finding duplicates, spatial searching, graph conversions |

---

## 3. Practical Test Strategy (3 Hours, 2 Questions)

For a timed practical exam, **Decrease-and-Conquer** and **Transform-and-Conquer (Instance Simplification via Sorting)** are by far the **easiest and safest** techniques to apply.

### Why Avoid Divide-and-Conquer Unless Explicitly Required?

Divide-and-Conquer algorithms usually require writing recursive calls and custom merge logic. In a timed practical:

* Recursive function signatures and base cases take longer to set up and debug.
* Off-by-one errors in pointer manipulation (e.g., `low`, `mid`, `high` bounds) are common under stress.
* Stack overflow errors can occur if base cases are missed.

### Practical Exam Rule of Thumb

1. **Try Transform-and-Conquer First:**
* Always ask: *"Does sorting the input array first reduce this from $O(n^2)$ to $O(n \log n)$ or make it a single-pass problem?"*
* Sorting takes 1 line of code in most standard libraries (e.g., `Arrays.sort()`, `std::sort`, `.sort()`), giving you a huge head start.


2. **Use Decrease-and-Conquer for Iterative Control:**
* If you need to search or eliminate candidate spaces, implement Decrease-and-Conquer using an **iterative `while` loop** rather than recursion. Iterative loops are easier to print-debug and avoid recursion stack overhead.


3. **Save Divide-and-Conquer for Explicit Mandates:**
* Only build multi-branch recursive Divide-and-Conquer solutions if the question explicitly specifies it or if you are dealing with tree structures where recursion is unavoidable.

--- 

Yes, each conquer paradigm follows distinct code structural patterns. The **`low`, `mid`, `high**` structure you noticed is the universal blueprint for **Binary Search**, which is the most prominent form of **Decrease-and-Conquer** (specifically, *decrease-by-a-constant-factor*).

Append the section below directly to your `Conquer.md` file.

---

## Common Code Patterns for Conquer Algorithms

### 1. Decrease-and-Conquer Patterns

The defining feature of Decrease-and-Conquer is that **only one branch is processed at each step**.

#### Pattern A: Binary Search on Answer / Space (Decrease-by-a-Constant-Factor)
Used when searching for an optimal value $M$ over a monotonic range $[\text{low}, \text{high}]$.

```java
long low = MIN_POSSIBLE;
long high = MAX_POSSIBLE;
long ans = -1;

while (low <= high) {
    long mid = low + (high - low) / 2;
    
    if (isValid(mid)) {
        ans = mid;         // Record potential answer
        low = mid + 1;     // Try for a better value in upper half
    } else {
        high = mid - 1;    // Discard upper half, look in lower half
    }
}
return ans;

```

#### Pattern B: Single-Element Removal (Decrease-by-a-Constant)

Used when processing or eliminating one element at a time (e.g., Insertion Sort, Selection, or linear search).

```java
// Iterative Decrease-by-1
for (int i = 1; i < n; i++) {
    int key = arr[i];
    int j = i - 1;
    // Reduce problem of size i to size i-1
    while (j >= 0 && arr[j] > key) {
        arr[j + 1] = arr[j];
        j--;
    }
    arr[j + 1] = key;
}

```

---

### 2. Divide-and-Conquer Patterns

The defining feature of Divide-and-Conquer is **multiple recursive calls** followed by an explicit **combine/merge** step.

#### Pattern A: Split, Solve Both, Merge (e.g., Merge Sort)

Used when subproblem results need to be combined to form the parent solution.

```java
void divideAndConquer(int[] arr, int low, int high) {
    // 1. Base Case
    if (low >= high) return;

    // 2. Divide
    int mid = low + (high - low) / 2;

    // 3. Conquer (Solve BOTH independent subproblems)
    divideAndConquer(arr, low, mid);
    divideAndConquer(arr, mid + 1, high);

    // 4. Combine / Merge
    merge(arr, low, mid, high);
}

```

#### Pattern B: Partition First, Then Solve Both (e.g., Quick Sort)

Used when work is done during the division step, making the combination step trivial.

```java
void quickSort(int[] arr, int low, int high) {
    if (low < high) {
        // 1. Partition step (Divide)
        int pivotIndex = partition(arr, low, high);

        // 2. Conquer subproblems
        quickSort(arr, low, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, high);
        
        // 3. Combine step is implicit (in-place)
    }
}

```

---

### 3. Transform-and-Conquer Patterns

The defining feature is **pre-processing the input representation** before solving.

#### Pattern A: Instance Simplification (Pre-Sorting / Structuring)

Sorting the array first so subsequent queries take $O(1)$ or $O(\log n)$ time.

```java
// Step 1: Transform (Sort in O(n log n))
Arrays.sort(arr);

// Step 2: Conquer (Simple linear pass in O(n))
for (int i = 0; i < arr.length - 1; i++) {
    if (arr[i] == arr[i + 1]) {
        return true; // Duplicate found
    }
}

```

#### Pattern B: Representation Change (Data Structure Swap)

Transforming raw input into a specialized structure (e.g., Hash Map, Frequency Array, Binary Search Tree, or Graph Adjacency List).

```java
// Step 1: Transform (Array to Frequency Map / Lookup Table)
Map<Integer, Integer> freqMap = new HashMap<>();
for (int val : arr) {
    freqMap.put(val, freqMap.getOrDefault(val, 0) + 1);
}

// Step 2: Conquer (Instant O(1) lookups)
if (freqMap.getOrDefault(target, 0) > 0) {
    // Process result
}

```