package ru.unn.agile.NumberSystemConverter.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import ru.unn.agile.NumberSystemConverter.model.NumberSystemConverter;
import ru.unn.agile.NumberSystemConverter.model.NumberSystemBase;

public class NumberSystemConverterViewModel {

    public StringProperty numberInBaseNumberSystemProperty() {
        return this.numberInBaseNumberSystem;
    }

    public StringProperty numberInTargetNumberSystemProperty() {
        return this.numberInTargetNumberSystem;
    }

    public ObjectProperty<NumberSystemBase> baseNumberSystemProperty() {
        return this.baseNumberSystem;
    }

    public ObjectProperty<NumberSystemBase> targetNumberSystemProperty() {
        return this.targetNumberSystem;
    }

    public ObjectProperty<ObservableList<NumberSystemBase>> availableNumberSystemsProperty() {
        return this.availableNumberSystems;
    }

    public final ObservableList<NumberSystemBase> getAvailableNumberSystems() {
        return this.availableNumberSystems.get();
    }

    public BooleanProperty conversionEnabledProperty() {
        return this.conversionEnabled;
    }

    public final boolean isConversionEnabled() {
        return this.conversionEnabled.get();
    }

    public BooleanProperty errorMessageIsShownProperty() {
        return this.errorMessageIsShown;
    }

    public final boolean isErrorMessageShown() {
        return this.errorMessageIsShown.get();
    }

    public StringProperty errorMessageProperty() {
        return this.errorMessage;
    }

    public final String getErrorMessage() {
        return this.errorMessage.get();
    }


    public NumberSystemConverterViewModel() {
        this.numberInBaseNumberSystem.set("");
        this.numberInTargetNumberSystem.set("");
        this.baseNumberSystem.set(NumberSystemBase.DEC);
        this.targetNumberSystem.set(NumberSystemBase.DEC);
        this.errorMessageIsShown.set(false);
        this.errorMessage.set("");

        this.bindConversionAvailability();
    }

    public void convert() {
        if (this.isConversionEnabled()) {
            try {
                String result = NumberSystemConverter.convert(this.numberInBaseNumberSystem.get(),
                        this.baseNumberSystem.get(), this.targetNumberSystem.get());
                this.numberInTargetNumberSystem.set(result);
            } catch (IllegalArgumentException illegalArgumentException) {
                this.errorMessage.set(illegalArgumentException.getMessage());
                this.errorMessageIsShown.set(true);
            }
        }
    }

    private void bindConversionAvailability() {
        BooleanBinding couldConvert = new BooleanBinding() {
            {
                super.bind(numberInBaseNumberSystem);
            }

            @Override
            protected boolean computeValue() {
                return numberInBaseNumberSystem.get().length() > 0;
            }
        };
        this.conversionEnabled.bind(couldConvert);
    }

    private final StringProperty numberInBaseNumberSystem = new SimpleStringProperty();

    private final StringProperty numberInTargetNumberSystem = new SimpleStringProperty();

    private final ObjectProperty<ObservableList<NumberSystemBase>> availableNumberSystems =
            new SimpleObjectProperty<>(
                    FXCollections.observableArrayList(NumberSystemBase.values())
            );

    private final ObjectProperty<NumberSystemBase> baseNumberSystem =
            new SimpleObjectProperty<>();

    private final ObjectProperty<NumberSystemBase> targetNumberSystem =
            new SimpleObjectProperty<>();

    private final BooleanProperty conversionEnabled = new SimpleBooleanProperty();

    private final BooleanProperty errorMessageIsShown = new SimpleBooleanProperty();

    private final StringProperty errorMessage = new SimpleStringProperty();
}
