package ca.jbrains.pos.test;

import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.Display;
import ca.jbrains.pos.Price;
import ca.jbrains.pos.SellOneItemController;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class SellOneItemControllerTest {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    @Test
    public void productFound() throws Exception {
        final Catalog catalog = context.mock(Catalog.class);
        final Display display = context.mock(Display.class);
        final Price foundPrice = Price.cents(795);

        context.checking(new Expectations() {{
            allowing(catalog).findPrice(with("12345"));
            will(returnValue(foundPrice));

            oneOf(display).displayPrice(with(foundPrice));
        }});

        final SellOneItemController controller = new SellOneItemController(catalog, display);
        controller.onBarcode("12345");
    }

    @Test
    public void productNotFound() throws Exception {
        final Catalog catalog = context.mock(Catalog.class);
        final Display display = context.mock(Display.class);

        context.checking(new Expectations() {{
            allowing(catalog).findPrice(with("12345"));
            will(returnValue(null));

            oneOf(display).displayProductNotFoundMessage(with("12345"));
        }});

        final SellOneItemController controller = new SellOneItemController(catalog, display);
        controller.onBarcode("12345");
    }

    @Test
    public void emptyBarcode() throws Exception {
        final Display display = context.mock(Display.class);

        context.checking(new Expectations() {{
            oneOf(display).displayScannedEmptyBarcodeMessage();
        }});
        
        final SellOneItemController controller
                = new SellOneItemController(null, display);
        controller.onBarcode("");
    }

}
