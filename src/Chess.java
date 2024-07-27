import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chess {

    private static final int BOARD_SIZE = 8;
    private static JButton[][] squares = new JButton[BOARD_SIZE][BOARD_SIZE];
    private static BinaryTree<String> moveTree = new BinaryTree<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess Game");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));

        initializeBoard(frame);

        frame.setVisible(true);
    }

    private static void initializeBoard(JFrame frame) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                squares[row][col] = new JButton();
                if ((row + col) % 2 == 0) {
                    squares[row][col].setBackground(Color.WHITE);
                } else {
                    squares[row][col].setBackground(Color.GRAY);
                }
                squares[row][col].addActionListener(new MoveActionListener(row, col));
                frame.add(squares[row][col]);
            }
        }
    }

    static class MoveActionListener implements ActionListener {
        private int row;
        private int col;

        MoveActionListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String move = "(" + row + ", " + col + ")";
            moveTree.insert(move);
            System.out.println("Move made: " + move);
        }
    }

    static class BinaryTree<T> {
        private Node<T> root;

        public void insert(T value) {
            root = insertRec(root, value);
        }

        private Node<T> insertRec(Node<T> root, T value) {
            if (root == null) {
                root = new Node<>(value);
                return root;
            }
            if (value.hashCode() < root.value.hashCode()) {
                root.left = insertRec(root.left, value);
            } else if (value.hashCode() > root.value.hashCode()) {
                root.right = insertRec(root.right, value);
            }
            return root;
        }

        static class Node<T> {
            T value;
            Node<T> left, right;

            public Node(T value) {
                this.value = value;
                left = right = null;
            }
        }
    }
}
