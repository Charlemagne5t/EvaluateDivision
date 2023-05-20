import org.example.Solution;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestSolution {
    @Test
    public void calcEquationTest1() {
        List<String> e1 = new ArrayList<>(List.of("a", "b"));
        List<String> e2 = new ArrayList<>(List.of("b", "c"));
        List<List<String>> equations = new ArrayList<>(List.of(e1, e2));

        double[] values = {2.0, 3.0};
        List<String> q1 = new ArrayList<>(List.of("a", "c"));
        List<String> q2 = new ArrayList<>(List.of("b", "a"));
        List<String> q3 = new ArrayList<>(List.of("a", "e"));
        List<String> q4 = new ArrayList<>(List.of("a", "a"));
        List<String> q5 = new ArrayList<>(List.of("x", "x"));

        List<List<String>> queries = new ArrayList<>(List.of(q1, q2, q3, q4, q5));

        double[] output = {6.00000, 0.50000, -1.00000, 1.00000, -1.00000};

        Assert.assertArrayEquals(output, new Solution().calcEquation(equations, values, queries), 0.00001);
    }
}
