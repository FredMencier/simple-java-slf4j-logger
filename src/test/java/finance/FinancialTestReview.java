package finance;

import org.slf4j.Logger;
import org.junit.*;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class FinancialTestReview {

    private static final Logger LOG = LoggerFactory.getLogger(FinancialTestReview.class);

    private static Financial financial;
    private static List<Double> doubleList;

    @BeforeClass
    public static void init() {
        LOG.info("Initialisation du test");
        financial = new Financial();
        doubleList = Arrays.asList(1D, 2D, 3D, 4D, 5D, 6D, 7D, 8D, 9D, 10D);
    }

    @AfterClass
    public static void teardown() {
        LOG.info("Libération des resources");
        financial = null;
        doubleList = null;
    }

    @Test
    @Ignore
    public void calculateAverage() {
        LOG.info("Test calculate average");
        double average = financial.calculateAverage(doubleList);
        Assert.assertEquals(5.5D, average, 0D);
    }

    @Test
    public void calculateSum() {
        LOG.info("Test calculate sum");
        double sum = financial.calculateSum(doubleList);
        Assert.assertEquals(55D, sum, 0D);
    }

    @Test
    public void calculateChange() throws RateUnavailableException {
        LOG.info("Test calculate change");
        double change = financial.calculateChange("CHF", "EUR", 1000D);
        Assert.assertEquals(1060D, change, 0D);
    }

    @Test(expected = RateUnavailableException.class)
    public void calculateChangeWithException() throws RateUnavailableException {
        LOG.info("Test calculate change with exception");
        financial.calculateChange("CHF", "JPY", 1000D);
    }

    @Test(timeout = 5000)
    public void getAvailableCurrencies() {
        LOG.info("Test get available currencies");
        Assert.assertEquals(3, financial.getAvailableCurrencies().size());
    }
}