package eip.camel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import eip.common.services.StockService;

@ContextConfiguration(locations={"classpath:META-INF/deliverynote.spring.xml", "classpath:META-INF/jpa.spring.xml"})
public class DeliveryNoteCsvImportTest extends AbstractTransactionalTestNGSpringContextTests {
	
	private static final String STOCK_NR = "1935182366";
	@Autowired
	private StockService stock;
	
	@Test
	public void check() throws InterruptedException
	{
       // Given a delivery note is processed
		for (int i = 0; i < 3; i++)
		{
			Thread.sleep(200);
	          //Then there are two items on stock
			Integer itemsOnStock = stock.getItemsOnStock(STOCK_NR);
			if (itemsOnStock > 0)
			{
				Assert.assertEquals(itemsOnStock, Integer.valueOf(2));
				return;
			}
		}
		Assert.fail("Expected a stock item "+STOCK_NR);
	}

}
