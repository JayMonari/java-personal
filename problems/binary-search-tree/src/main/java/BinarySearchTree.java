import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;

class BinarySearchTree<T extends Comparable<T>> {
    private Node<T> root;

    void insert(T value) {
        if (root == null) {
            root = new Node<T>(value);
            return;
        }

        Node<T> curr = root;
        while (curr != null) {
            if (value.compareTo(curr.data) > 0) {
                if (curr.right == null) {
                    curr.right = new Node<T>(value);
                    break;
                } else {
                    curr = curr.right;
                }
            } else {
                if (curr.left == null) {
                    curr.left = new Node<T>(value);
                    break;
                } else {
                    curr = curr.left;
                }
            }
        }
    }

    List<T> getAsSortedList() {
        List<T> sorted = getAsLevelOrderList();
        sorted.sort(Comparator.naturalOrder());
        return sorted;
    }

    List<T> getAsLevelOrderList() {
        List<T> values = new ArrayList<>();
        Queue<Node<T>> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();
            values.add(node.data);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return values;
    }

    Node<T> getRoot() {
        return root;
    }

    static class Node<T> {
        private T data;
        private Node<T> left;
        private Node<T> right;

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, Node<T> left, Node<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        Node<T> getLeft() {
            return left;
        }

        Node<T> getRight() {
            return right;
        }

        T getData() {
            return data;
        }

    }
}
