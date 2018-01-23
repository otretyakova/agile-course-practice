package ru.unn.agile.ConvertNumeral.view.legacy;

import ru.unn.agile.ConvertNumeral.ViewModel.legacy.ViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class Converter {


    public static void main(final String[] args) {
        JFrame frame = new JFrame("Converter");

        frame.setContentPane(new Converter(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private Converter(final ViewModel viewModel) {
        this.viewModel = viewModel;

        backBind();

        btnConvert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                viewModel.convert();
                backBind();
            }
        });

        txtNumberInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(final DocumentEvent e) {
                bind();
                backBind();
            }

            @Override
            public void removeUpdate(final DocumentEvent e) {
                bind();
                backBind();
            }

            @Override
            public void changedUpdate(final DocumentEvent e) {
                bind();
                backBind();
            }
        });

    }

    private void bind() {
        viewModel.setInputNumber(txtNumberInput.getText());
    }

    private void backBind() {
        btnConvert.setEnabled(viewModel.isConvertButtonEnabled());
        txtNumberOutput.setText(viewModel.getOutputNumber());
        txtMessage.setText(viewModel.getMessageText());
    }

    private JTextField txtNumberInput;
    private JTextField txtNumberOutput;
    private JButton btnConvert;
    private JTextArea txtMessage;
    private final ViewModel viewModel;
    private JPanel mainPanel;
}
