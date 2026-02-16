package tree;

import java.util.ArrayList;

public class BinTree {
    record BTree(String value, BTree left, BTree right) {
        public BTree(String value) {
            this(value, null, null);
        }
    }

    public static void main(String[] args) {
        var root = new BTree("A",
                new BTree("B",
                        new BTree("D"),
                        new BTree("E")),
                new BTree("C",
                        new BTree("F"),
                        new BTree("G"))
        );
        printDfs(root);
        System.out.println();
        printBfs(root);

        System.out.println("Iter Dfs");
        printDfsIter(root);
    }

    public static void printDfs(BTree tree) {
        if (tree == null) {
            return;
        }
        System.out.print(tree.value + " ");
        printDfs(tree.left);
        printDfs(tree.right);
    }

    public static void printDfsIter(BTree tree) {
        if (tree == null) {
            return;
        }
        var nodes = new ArrayList<BTree>(); // used as queue
        nodes.add(tree);
        while (!nodes.isEmpty()) {
            var curr = nodes.remove(nodes.size() - 1);
            System.out.print(curr.value + " ");
            if (curr.right != null) nodes.add(curr.right);
            if (curr.left != null) nodes.add(curr.left);
        }
    }

    public static void printBfs(BTree tree) {
        if (tree == null) {
            return;
        }
        var nodes = new ArrayList<BTree>(); // used as queue
        nodes.add(tree);
        while (!nodes.isEmpty()) {
            var curr = nodes.remove(0);
            System.out.print(curr.value + " ");
            if (curr.left != null) nodes.add(curr.left);
            if (curr.right != null) nodes.add(curr.right);
        }
    }
}

