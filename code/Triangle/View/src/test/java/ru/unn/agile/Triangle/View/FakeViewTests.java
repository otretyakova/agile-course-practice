package ru.unn.agile.Triangle.View;

import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.fail;

public class FakeViewTests {
    @Ignore @Test
    public void fakeTest() {
        fail("No tests for view");
    }

    @Ignore @Test
    public void fakeCalculatorClassTest() {
        Calculator a = new Calculator();
        a.initialize();
        Assert.assertTrue(true);
    }

    @Ignore @Test
    public void fakeMainClassTest() throws Exception {
        Main a = new Main();
        Stage stage = new Stage();
        a.start(stage);
        Assert.assertTrue(true);
    }
}
