package ru.unn.agile.LTree.Model;

import java.util.Collection;

public class LeftSidedHeap<T> {

    public static class EmptyHeapException extends Exception {
    }

    public static class KeyValuePair<T> {
        public KeyValuePair(final int key, final T value) {
            this.key = key;
            this.value = value;
        }

        public boolean compareWith(final KeyValuePair<T> pair) {
            return pair != null && key == pair.key && value.equals(pair.value);
        }

        public int getKey() {
            return key;
        }

        private final int key;
        private final T value;
    }

    public LeftSidedHeap() {
        size = 0;
        root = null;
    }

    public LeftSidedHeap(final Collection<KeyValuePair<T>> array) {
        size = 0;
        root = null;
        if (array != null) {
            for (KeyValuePair<T> kvp : array) {
                add(kvp);
            }
        }
    }

    public final void add(final KeyValuePair<T> kvp) {
        if (size() == 0) {
            root = new Node<T>(kvp, 1, null, null, null);
            size++;
        } else {
            merge(new LeftSidedHeap<T>(kvp));
        }
    }

    public KeyValuePair<T> getMin() throws EmptyHeapException {
        if (size() == 0) {
            throw new EmptyHeapException();
        }
        return new KeyValuePair<T>(root.kvp.key, root.kvp.value);
    }

    public KeyValuePair<T> remove(final int key) throws EmptyHeapException {
        KeyValuePair<T> retVal = null;
        if (size() == 0) {
            throw new EmptyHeapException();
        } else {
            Node<T> nodeToRemove = search(root, key);

            if (nodeToRemove == null) {
                retVal = null;
            } else if (nodeToRemove == root) {
                retVal = root.kvp;
                root = merge(root.left, root.right);
                size--;
            } else {
                retVal = nodeToRemove.kvp;

                Node<T> parentRemovingNode = nodeToRemove.parent;
                Node<T> newHeap = merge(nodeToRemove.left, nodeToRemove.right);
                if (parentRemovingNode.left == nodeToRemove) {
                    parentRemovingNode.left = newHeap;
                } else {
                    parentRemovingNode.right = newHeap;
                }
                Node<T> tmp = parentRemovingNode;
                while (tmp != root && dist(tmp.left) < dist(tmp.right)) {
                    tmp.swapChildren();
                }
                size--;
            }
        }
        return retVal;
    }

    public boolean merge(final LeftSidedHeap<T> heap) {
        boolean bResult = true;
        if (heap == null) {
            bResult = false;
        } else if (size() == 0) {
            root = heap.root;
            size = heap.size;
        } else if (heap.root != null) {
            root = merge(root, heap.root);
            size += heap.size;
        }
        return bResult;
    }

    public final int size() {
        return size;
    }

    private static final class Node<T> {
        Node(final KeyValuePair<T> kvp, final int rank,
                     final Node<T> left, final Node<T> right, final Node<T> parent) {
            this.kvp = kvp;
            this.rank = rank;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        private void swapChildren() {
            Node<T> tmp = left;
            left = right;
            right = tmp;
        }

        private final KeyValuePair<T> kvp;
        private int rank;
        private Node<T> left;
        private Node<T> right;
        private Node<T> parent;
    }

    private LeftSidedHeap(final KeyValuePair<T> kvp) {
        size = 0;
        add(kvp);
    }

    private int dist(final Node<T> x) {
        int ret;
        if (x == null) {
            ret = 0;
        } else {
            ret = x.rank;
        }
        return ret;
    }

    private Node<T> search(final Node<T> head, final int key) {
        Node<T> currentNode = null;
        if (head != null) {
            if (head.kvp.key == key) {
                currentNode = head;
            } else if (key >= head.kvp.key) {
                currentNode = search(head.left, key);
                if (currentNode == null) {
                    currentNode = search(head.right, key);
                }
            }
        }
        return currentNode;
    }

    private Node<T> merge(final Node<T> heap1, final Node<T> heap2) {
        Node<T> x = heap1;
        Node<T> y = heap2;
        if (x == null) {
            return y;
        }
        if (y == null) {
            return x;
        }
        if (x.kvp.key > y.kvp.key) {
            Node<T> tmp = x;
            x = y;
            y = tmp;
        }
        x.right = merge(x.right, y);
        if (dist(x.right) > dist(x.left)) {
            x.swapChildren();
        }

        if (x.left != null) {
            x.left.parent = x;
        }
        if (x.right != null) {
            x.right.parent = x;
        }
        x.rank = Math.min(dist(x.right), dist(x.left)) + 1;
        return x;
    }

    private int size;
    private Node<T> root;
}
