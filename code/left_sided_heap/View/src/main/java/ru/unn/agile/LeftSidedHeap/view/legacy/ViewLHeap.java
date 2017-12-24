package ru.unn.agile.LeftSidedHeap.view.legacy;

import ru.unn.agile.LeftSidedHeap.viewmodel.legacy.ViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class ViewLHeap {

    private ViewModel viewModel;
    private JTextField fieldAdd;
    private JButton buttonAdd;
    private JTextField fieldRemove;
    private JButton buttonRemove;
    private JTextArea textResult;
    private JPanel mainPanel;


    private ViewLHeap() {

    }

    private ViewLHeap(final ViewModel viewModel) {
        this.viewModel = viewModel;
        backBind();

        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                ViewLHeap.this.viewModel.add();
                backBind();
            }
        });

        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                ViewLHeap.this.viewModel.remove();
                backBind();
            }
        });

        KeyAdapter keyListener = new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                bind();
                ViewLHeap.this.viewModel.processKeyInTextField(e.getKeyCode());
                backBind();
            }
        };
        fieldAdd.addKeyListener(keyListener);
        fieldRemove.addKeyListener(keyListener);
    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("Left Heap");

        frame.setContentPane(new ViewLHeap(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void bind() {
        viewModel.setTextAdd(fieldAdd.getText());
        viewModel.setTextRemove(fieldRemove.getText());
    }

    private void backBind() {
        buttonAdd.setEnabled(viewModel.isButtonAddEnabled());
        buttonRemove.setEnabled(viewModel.isButtonRemoveEnabled());

        textResult.setText(viewModel.getResult());
    }
}
