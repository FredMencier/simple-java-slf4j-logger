package finance;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class FinancialTestWithLombok {

    private Financial financial;
    private List<Double> doubleList;

    @Before
    public void init() {
        log.info("Initialisation du test");
        financial = new Financial();
        doubleList = Arrays.asList(1D, 2D, 3D, 4D, 5D, 6D, 7D, 8D, 9D, 10D);
    }

    @After
    public void teardown() {
        log.info("Libération des resources");
        financial = null;
        doubleList = null;
    }

    @Test
    public void calculateAverage() {
        log.info("Test calculate average");
        double average = financial.calculateAverage(doubleList);
        Assert.assertEquals(5.5D, average, 0D);
    }

    @Test
    public void calculateSum() {
        log.info("Test calculate sum");
        double sum = financial.calculateSum(doubleList);
        Assert.assertEquals(55D, sum, 0D);
    }

    @Test
    public void calculateChange() throws RateUnavailableException {
        log.info("Test calculate change");
        double change = financial.calculateChange("CHF", "EUR", 1000D);
        Assert.assertEquals(1060D, change, 0D);
    }

    @Test(expected = RateUnavailableException.class)
    public void calculateChangeWithException() throws RateUnavailableException {
        log.info("Test calculate change with exception");
        financial.calculateChange("CHF", "JPY", 1000D);
    }

    @Test(timeout = 5000)
    public void getAvailableCurrencies() {
        log.info("Test get available currencies");
        Assert.assertEquals(3, financial.getAvailableCurrencies().size());
    }
}