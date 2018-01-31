package ru.unn.agile.BookSale.infrastructure_lab3_legacy;


import ru.unn.agile.BookSale.ViewModel.legacy.ViewModel;
import ru.unn.agile.BookSale.ViewModel.legacy.ViewModelTest;

public class ViewModelWithTxtLoggerTests extends ViewModelTest {
    @Override
    public void setUp() {
        TxtLogger realLogger =
                new TxtLogger("./ViewModelWithTxtLoggerTests-lab3-legacy.log");
        super.setViewModel(new ViewModel(realLogger));
    }
}
