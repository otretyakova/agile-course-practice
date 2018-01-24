package ru.unn.agile.ConvertNumeral.view.legacy;

import ru.unn.agile.ConvertNumeral.ViewModel.legacy.ViewModel;
import ru.unn.agile.ConvertNumeral.infrastructure.FileLogger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public final class Converter {

    public static void main(final String[] args) {
        JFrame frame = new JFrame("Converter");

        FileLogger logger = new FileLogger("Converter.log");
        frame.setContentPane(new Converter(new ViewModel(logger)).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    private Converter(final ViewModel viewModel) {
        this.viewModel = viewModel;

        backBind();

        btnConvert.addActionListener(
                ae -> {
                    bind();
                    viewModel.convert();
                    backBind();
                }
        );

        txtNumberInput.setDocument(new PlainDocument() {
            @Override
            public void insertString(final int offs,
                                     final String str,
                                     final AttributeSet a) throws BadLocationException {
                if (viewModel.isAvailableInsertInput(offs, str)) {
                    super.insertString(offs, str, a);
                }
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
    private JPanel mainPanel;
    private final ViewModel viewModel;
}
