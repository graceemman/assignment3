import java.util.*;

public class BST<K extends Comparable<K>, V> implements Iterable<BST.Entry<K, V>> {

    private class Node {
        K key;
        V val;
        Node left, right;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K k, V v) {
            key = k;
            value = v;
        }

        public K getKey() { return key; }
        public V getValue() { return value; }
    }

    private Node root;
    private int size = 0;

    public int size() {
        return size;
    }

    // ✅ ITERATIVE PUT (NO RECURSION)
    public void put(K key, V val) {
        if (root == null) {
            root = new Node(key, val);
            size++;
            return;
        }

        Node parent = null;
        Node curr = root;

        while (curr != null) {
            parent = curr;
            int cmp = key.compareTo(curr.key);

            if (cmp < 0) {
                curr = curr.left;
            } else if (cmp > 0) {
                curr = curr.right;
            } else {
                curr.val = val;
                return;
            }
        }

        int cmp = key.compareTo(parent.key);
        if (cmp < 0) {
            parent.left = new Node(key, val);
        } else {
            parent.right = new Node(key, val);
        }

        size++;
    }

    // ✅ ITERATIVE GET
    public V get(K key) {
        Node curr = root;

        while (curr != null) {
            int cmp = key.compareTo(curr.key);

            if (cmp < 0) curr = curr.left;
            else if (cmp > 0) curr = curr.right;
            else return curr.val;
        }

        return null;
    }

    // ✅ SIMPLE ITERATIVE DELETE (basic version)
    public void delete(K key) {
        root = deleteIter(root, key);
    }

    private Node deleteIter(Node root, K key) {
        Node parent = null;
        Node curr = root;

        // find node
        while (curr != null && !curr.key.equals(key)) {
            parent = curr;
            if (key.compareTo(curr.key) < 0)
                curr = curr.left;
            else
                curr = curr.right;
        }

        if (curr == null) return root;

        // case: 0 or 1 child
        if (curr.left == null || curr.right == null) {
            Node newCurr;

            if (curr.left == null)
                newCurr = curr.right;
            else
                newCurr = curr.left;

            if (parent == null)
                return newCurr;

            if (parent.left == curr)
                parent.left = newCurr;
            else
                parent.right = newCurr;
        } else {
            // 2 children
            Node p = null;
            Node temp = curr.right;

            while (temp.left != null) {
                p = temp;
                temp = temp.left;
            }

            curr.key = temp.key;
            curr.val = temp.val;

            if (p != null)
                p.left = temp.right;
            else
                curr.right = temp.right;
        }

        size--;
        return root;
    }

    // ✅ ITERATIVE INORDER (NO RECURSION)
    public Iterator<Entry<K, V>> iterator() {
        List<Entry<K, V>> list = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node curr = root;

        while (curr != null || !stack.isEmpty()) {

            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            list.add(new Entry<>(curr.key, curr.val));
            curr = curr.right;
        }

        return list.iterator();
    }
}