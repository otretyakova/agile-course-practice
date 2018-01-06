package ru.unn.agile.PrimeNumber.ViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import ru.unn.agile.PrimeNumber.ViewModel.Status;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
/*import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import ru.unn.agile.MetricsDistance.Model.Metric;*/

public class StatusTests {
    @Test
    public void statusesDefined()
    {
        Status status = Status.WAITING;
        status = Status.BAD_FORMAT;
        status = Status.SUCCESS;
        status = Status.READY;
    }

    @Test
    public void canGetStatusName()
    {
        Status status = Status.WAITING;
        assertEquals(status.name(), "Please provide input data");
    }
}
