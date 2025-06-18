import java.util.HashMap;

public class FinancialForecast {

    // Naive Recursive Approach
    public static double futureValueRecursive(double presentValue, double annualGrowthRate, int years) {
        if (years == 0) {
            return presentValue;
        }
        return (1 + annualGrowthRate) * futureValueRecursive(presentValue, annualGrowthRate, years - 1);
    }

    // Optimized Recursive Approach using Memoization
    public static double futureValueMemo(double presentValue, double annualGrowthRate, int years, HashMap<Integer, Double> memo) {
        if (years == 0) {
            return presentValue;
        }
        if (memo.containsKey(years)) {
            return memo.get(years);
        }
        double result = (1 + annualGrowthRate) * futureValueMemo(presentValue, annualGrowthRate, years - 1, memo);
        memo.put(years, result);
        return result;
    }

    // Iterative Approach
    public static double futureValueIterative(double presentValue, double annualGrowthRate, int years) {
        double result = presentValue;
        for (int i = 0; i < years; i++) {
            result *= (1 + annualGrowthRate);
        }
        return result;
    }

    public static void main(String[] args) {
        double presentValue = 1000.0;
        double growthRate = 0.08;
        int years = 35;

        long start, end;

        // Naive Recursive
        start = System.nanoTime();
        double resultRecursive = futureValueRecursive(presentValue, growthRate, years);
        end = System.nanoTime();
        long timeRecursive = end - start;

        // Optimized Recursive
        start = System.nanoTime();
        double resultMemo = futureValueMemo(presentValue, growthRate, years, new HashMap<>());
        end = System.nanoTime();
        long timeMemo = end - start;

        // Iterative
        start = System.nanoTime();
        double resultIterative = futureValueIterative(presentValue, growthRate, years);
        end = System.nanoTime();
        long timeIterative = end - start;

        // Output
        System.out.printf("Naive Recursive Result: %.2f | Time: %d ns\n", resultRecursive, timeRecursive);
        System.out.printf("Memoized Recursive Result: %.2f | Time: %d ns\n", resultMemo, timeMemo);
        System.out.printf("Iterative Result: %.2f | Time: %d ns\n", resultIterative, timeIterative);
    }
}
