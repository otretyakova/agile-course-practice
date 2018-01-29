package ru.unn.agile.Range.view.legacy;

import ru.unn.agile.Range.ViewModel.legacy.ViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public final class RangeCalculator {

    public static void main(final String[] args) {
        JFrame frame = new JFrame("RangeCalculator");

        frame.setContentPane(new RangeCalculator(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    private RangeCalculator(final ViewModel viewModel) {
        this.viewModel = viewModel;

        backBind();

        loadListOfOperations();

        cbOperation.addActionListener(
                ae -> {
                    bind();
                    backBind();
                });

        btnCalculate.addActionListener(
                ae -> {
                    bind();
                    viewModel.calculate();
                    backBind();
                }
        );

        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(final DocumentEvent de) {
                bind();
                backBind();
            }

            @Override
            public void removeUpdate(final DocumentEvent de) {
                bind();
                backBind();
            }

            @Override
            public void changedUpdate(final DocumentEvent de) {
                bind();
                backBind();
            }
        };
        txtInputRange.getDocument().addDocumentListener(documentListener);
        txtInputRangeOrSet.getDocument().addDocumentListener(documentListener);
    }

    private void loadListOfOperations() {
        ViewModel.Operation[] operations = ViewModel.Operation.values();
        cbOperation.setModel(new JComboBox<>(operations).getModel());
    }

    private void bind() {
        viewModel.setInputRange(txtInputRange.getText());
        viewModel.setInputRangeOrSet(txtInputRangeOrSet.getText());
        viewModel.setOperation((ViewModel.Operation) cbOperation.getSelectedItem());
    }

    private void backBind() {
        btnCalculate.setEnabled(viewModel.isCalculateButtonEnabled());
        txtInputRangeOrSet.setEnabled(viewModel.isInputRangeOrSetTextFieldEnabled());
        txtResult.setText(viewModel.getResult());
        txtMessage.setText(viewModel.getMessageText());
    }

    private JTextField txtInputRange;
    private JTextField txtInputRangeOrSet;
    private JTextField txtResult;
    private JButton btnCalculate;
    private JComboBox<ViewModel.Operation> cbOperation;
    private JTextArea txtMessage;
    private JPanel mainPanel;
    private ViewModel viewModel;
}
