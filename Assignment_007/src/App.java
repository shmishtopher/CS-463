import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;


public class App {
    private static Map<Character, String> compute_coding(Map<Character, Integer> character_counts) {
        // Instantiate the priority queue
        Queue<Tree> queue = new PriorityQueue<Tree>((x, y) -> x.data - y.data);

        // Insert each leaf node into the queue
        for (Map.Entry<Character, Integer> entry : character_counts.entrySet()) {
            Character character = entry.getKey();
            Integer frequency = entry.getValue();

            queue.add(Tree.leaf(character, frequency));
        }

        // Assemble tree
        while (queue.size() > 1) {
            Tree lhs = queue.remove();
            Tree rhs = queue.remove();

            queue.add(Tree.combine(lhs, rhs));
        }

        // Compute codes
        Map<Character, String> map = new HashMap<Character, String>();
        codes(map, queue.peek(), "");

        return map;
    }

    private static void codes(Map<Character, String> map, Tree tree, String code) {
        if (tree.isLeaf()) {
            map.put(tree.ch, code);
        }

        if (tree.leftChild != null) {
            codes(map, tree.leftChild, code + "1");
        }

        if (tree.rightChild != null) {
            codes(map, tree.rightChild, code + "0");
        }
    }

    public static void main(String[] args) throws Exception {
        Map<Character, Integer> freqs = new HashMap<Character, Integer>() {{
            put('a', 15);
            put('e', 7);
            put('i', 30);
            put('o', 120);
            put('u', 11);
        }};

        Map<Character, String> codes = compute_coding(freqs);

        for (Character ch : freqs.keySet()) {
            String code_word = codes.get(ch);
            System.out.println(ch + ": " + code_word);
        }
    }
}
