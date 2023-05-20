package org.example;

import java.text.DecimalFormat;
import java.util.*;

public class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, List<Edge>> graph = createGraph(equations, values);
        double[] answers = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            answers[i] = pathWeight(queries.get(i), graph);
        }
        System.out.println(Arrays.toString(answers));
        return answers;
    }


    private HashMap<String, List<Edge>> createGraph(List<List<String>> equations, double[] values) {
        HashMap<String, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            Edge edge = new Edge();
            String node = equations.get(i).get(0);
            edge.destination = equations.get(i).get(1);
            edge.weight = values[i];

            if (!graph.containsKey(node)) {
                graph.put(node, new ArrayList<>(List.of(edge)));
            } else {
                graph.get(node).add(edge);
            }

            if (values[i] != 0) {
                Edge reverseCase = new Edge();
                String nodeR = equations.get(i).get(1);
                reverseCase.destination = equations.get(i).get(0);
                reverseCase.weight = 1 / values[i];
                if (!graph.containsKey(nodeR)) {
                    graph.put(nodeR, new ArrayList<>(List.of(reverseCase)));
                } else {
                    graph.get(nodeR).add(reverseCase);
                }
            }
        }
        return graph;
    }

    private double pathWeight(List<String> query, HashMap<String, List<Edge>> graph) {
        String from = query.get(0);
        String to = query.get(1);
        if (from.equals(to) && graph.containsKey(to)) return 1;

        if (!graph.containsKey(from)) {
            return -1.0;
        } else {
            return weightedDFS(from, to, graph, new HashSet<>(), 1.0);
        }

    }

    private double weightedDFS(String current, String target, HashMap<String, List<Edge>> graph, Set<String> visited, double currentProd) {
        visited.add(current);

        if (!graph.containsKey(current)) {
            return -1.0;
        }

        List<Edge> edges = graph.get(current);

        for (Edge edge : edges) {
            if (!visited.contains(edge.destination)) {
                double newProd = currentProd * edge.weight;

                if (edge.destination.equals(target)) {
                    return newProd;
                }

                double result = weightedDFS(edge.destination, target, graph, visited, newProd);

                if (result != -1.0) {
                    return result;
                }
            }
        }

        return -1.0;
    }
}

class Pair<String, Double> {
    String node;
    Double currentProd;

    public Pair(String node, Double currentProd) {
        this.node = node;
        this.currentProd = currentProd;
    }
}

class Edge {
    String destination;
    double weight;
}
