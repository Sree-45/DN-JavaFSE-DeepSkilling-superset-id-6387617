# Recursive Algorithms for Financial Forecasting

## 📊 Overview

This project demonstrates different approaches to implementing recursive algorithms for financial forecasting, specifically calculating future value with compound interest. We explore three different implementation strategies and analyze their performance characteristics.

## 🎯 Understanding Recursive Algorithms

Recursion solves problems by calling the same function on smaller inputs. For financial forecasting, recursion models compound growth neatly by breaking down the calculation into smaller time periods.

## 🔧 Setup

### The Mathematical Foundation

The future value calculation follows this recursive pattern:

```java
FV(P, r, n) = (1 + r) * FV(P, r, n - 1)
```

Where:
- `P` = Principal amount
- `r` = Interest rate per period
- `n` = Number of periods

### Base Case

```kotlin
if n == 0, return P
```

This ensures the recursion terminates when we reach the initial time period.

## 💻 Implementation

We implemented three different approaches to solve the same problem:

### 1. Naive Recursive
- **`futureValueRecursive`**: Simple recursive implementation
- Directly follows the mathematical definition
- Easy to understand and implement

### 2. Memoized Recursive
- **`futureValueMemo`**: Optimized recursive with memoization
- Caches previously computed results
- Avoids redundant calculations

### 3. Iterative
- **`futureValueIterative`**: Space-efficient loop-based solution
- Eliminates recursion overhead
- Most memory-efficient approach

## 📈 Performance Analysis

### Time & Space Complexity

| Approach | Time Complexity | Space Complexity |
|----------|----------------|------------------|
| Naive Recursive | O(n) | O(n) |
| Memoized Recursive | O(n) | O(n) |
| Iterative | O(n) | O(1) |

### Execution Time Comparison

**Test Case**: 35 years @ 8% annual growth rate

```sql
Naive Recursive Result: ₹14778.86 | Time: 21600 ns
Memoized Recursive Result: ₹14778.86 | Time: 5800 ns
Iterative Result: ₹14778.86 | Time: 2800 ns
```

> **Note**: Actual timings may vary depending on the system architecture and current load.

## 📋 Method Comparison

| Method | Pros | Cons |
|--------|------|------|
| **Naive Recursive** | • Simple and readable<br>• Mirrors mathematical definition | • Stack overflow risk<br>• Slower performance<br>• Higher memory usage |
| **Memoized Recursive** | • Avoids recomputation<br>• Better performance<br>• Still readable | • More memory usage<br>• Implementation complexity |
| **Iterative** | • Fastest execution<br>• Least memory used<br>• No stack overflow risk | • Less elegant<br>• Doesn't mirror math definition |

## 🎯 Recommendations

### When to Use Each Approach

- **Use Iterative** for:
  - Large inputs (1000+ periods)
  - Production systems requiring optimal performance
  - Memory-constrained environments

- **Use Memoized Recursion** for:
  - Balance of clarity and performance
  - Educational purposes
  - Moderate input sizes (< 1000 periods)

- **Use Naive Recursion** for:
  - Learning and understanding concepts
  - Small input sizes only
  - Prototyping and initial implementations

## 🔍 Key Insights

### Performance Optimization
Optimizing recursion with memoization drastically reduces execution time by avoiding repeated calculations. The memoized version shows **73% improvement** over naive recursion in our test case.

### Safety Considerations
Iteration remains the safest and fastest approach for deep recursions (e.g., 1000+ years), eliminating the risk of stack overflow while providing optimal performance.

### Trade-offs
While recursion provides elegant, mathematically intuitive code, iteration offers superior performance characteristics for production financial applications.

## 🚀 Getting Started

1. Clone the repository
2. Choose your preferred implementation based on your requirements
3. Adjust the parameters (principal, rate, periods) for your specific use case
4. Run performance benchmarks to validate results in your environment

## 📚 Further Reading

- Stack overflow prevention in recursive algorithms
- Memoization patterns and cache optimization
- Financial mathematics and compound interest calculations
- Performance profiling techniques for recursive vs. iterative solutions

---

**💡 Pro Tip**: Start with the memoized recursive approach for development, then switch to iterative for production deployment to get the best of both worlds!