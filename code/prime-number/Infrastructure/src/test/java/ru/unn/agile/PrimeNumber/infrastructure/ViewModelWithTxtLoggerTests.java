package ru.unn.agile.PrimeNumber.infrastructure;

import ru.unn.agile.PrimeNumber.viewmodel.ViewModel;
import ru.unn.agile.PrimeNumber.viewmodel.ViewModelTests;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TxtLogger realLogger =
            new TxtLogger("./ViewModel_with_TxtLogger_Tests-lab3.log");
        super.setExternalViewModel(new ViewModel(realLogger));
    }
}
