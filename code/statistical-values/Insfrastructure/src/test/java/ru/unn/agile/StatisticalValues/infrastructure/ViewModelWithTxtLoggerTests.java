package ru.unn.agile.StatisticalValues.infrastructure;

import ru.unn.agile.StatisticalValues.viewmodel.ViewModel;
import ru.unn.agile.StatisticalValues.viewmodel.ViewModelTests;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        StatisticalLogger realLogger =
                new StatisticalLogger("./ViewModel_with_StatisticalLoggerLogger_Tests.log");
        super.setExternalViewModel(new ViewModel(realLogger));
    }
}
