import java.util.*;

class Item {
    int weight, value;
    double ratio;

    Item(int w, int v) {
        weight = w;
        value = v;
        ratio = (double) v / w;
    }
}

class Node {
    int level, profit, weight;
    double bound;

    Node(int l, int p, int w) {
        level = l;
        profit = p;
        weight = w;
    }
}

public class knapsackBranch {
    static int W = 10;
    static Item[] items = {
            new Item(2, 40),
            new Item(3, 50),
            new Item(4, 60)
    };

    public static void main(String[] args) {
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));
        System.out.println("Maximum profit = " + knapsack());
    }

    static int knapsack() {
        Queue<Node> pq = new PriorityQueue<>((a, b) -> Double.compare(b.bound, a.bound));
        Node u, v;
        v = new Node(-1, 0, 0);
        v.bound = bound(v);
        pq.offer(v);

        int maxProfit = 0;
        while (!pq.isEmpty()) {
            v = pq.poll();
            if (v.bound <= maxProfit)
                continue;

            u = new Node(v.level + 1, v.profit, v.weight);
            if (u.level >= items.length)
                continue;

            // include
            u.weight += items[u.level].weight;
            u.profit += items[u.level].value;

            if (u.weight <= W && u.profit > maxProfit)
                maxProfit = u.profit;

            u.bound = bound(u);
            if (u.bound > maxProfit)
                pq.offer(new Node(u.level, u.profit, u.weight));

            // exclude
            u = new Node(v.level + 1, v.profit, v.weight);
            u.bound = bound(u);
            if (u.bound > maxProfit)
                pq.offer(u);
        }
        return maxProfit;
    }

    static double bound(Node u) {
        if (u.weight >= W)
            return 0;
        double result = u.profit;
        int j = u.level + 1;
        int totWeight = u.weight;
        while (j < items.length && totWeight + items[j].weight <= W) {
            totWeight += items[j].weight;
            result += items[j].value;
            j++;
        }
        if (j < items.length)
            result += (W - totWeight) * items[j].ratio;
        return result;
    }
}
