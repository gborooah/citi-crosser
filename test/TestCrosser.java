import junit.framework.TestCase;
import crosser.*;

/*****************************
Unit tests for crosser class
Tests various scenarios including empty order book
Checks the price and size of the cross trade
 **************************/

public class TestCrosser extends TestCase {
    //@Test
    public void testTrue() {
	assertTrue(true);
    }

    //@Test
    public void testCrosser0(){
	SimpleOrderBook ob = new SimpleOrderBook();
	Trade trade = ob.getCrossTrade();
	assertEquals(0, trade.size());
	assertEquals(0.0, trade.price());
    }

    //@Test
    public void testCrosserBOnly(){
	SimpleOrderBook ob = new SimpleOrderBook();
	Order order = new Order(Side.BUY, 10.0, 1);
	ob.add(order);
	Trade trade = ob.getCrossTrade();
	assertEquals(0, trade.size());
	assertEquals(0.0, trade.price());
    }


    //@Test
    public void testCrosserSOnly(){
	SimpleOrderBook ob = new SimpleOrderBook();
	Order order = new Order(Side.SELL, 11.0, 1);
	ob.add(order);
	Trade trade = ob.getCrossTrade();
	assertEquals(0, trade.size());
	assertEquals(0.0, trade.price());
    }

    //@Test
    public void testCrosserBSNoCross(){
	SimpleOrderBook ob = new SimpleOrderBook();
	Order orderB1 = new Order(Side.BUY, 10.0, 1);
	Order orderS1 = new Order(Side.SELL, 11.0, 1);
	ob.add(orderB1);
	ob.add(orderS1);
	Trade trade = ob.getCrossTrade();
	assertEquals(0, trade.size());
	assertEquals(0.0, trade.price());
    }

    //@Test
    public void testCrosserBSAlmostEqual(){
	SimpleOrderBook ob = new SimpleOrderBook();
	Order orderB1 = new Order(Side.BUY, 10.0, 1);
	Order orderS1 = new Order(Side.SELL, 10.00001, 1);
	ob.add(orderB1);
	ob.add(orderS1);
	Trade trade = ob.getCrossTrade();
	assertEquals(1, trade.size());
	assertEquals(10.0, trade.price());
    }

    //@Test
    public void testCrosserSBEqual(){
	SimpleOrderBook ob = new SimpleOrderBook();
	Order orderB1 = new Order(Side.BUY, 10.0, 1);
	Order orderS1 = new Order(Side.SELL, 10.0, 2);
	ob.add(orderB1);
	ob.add(orderS1);
	Trade trade = ob.getCrossTrade();
	assertEquals(1, trade.size());
	assertEquals(10.0, trade.price());
    }

    //@Test
    public void testCrosserSBCross(){
	SimpleOrderBook ob = new SimpleOrderBook();
	Order orderB1 = new Order(Side.BUY, 10.0, 2);
	Order orderS1 = new Order(Side.SELL, 9.0, 1);
	ob.add(orderB1);
	ob.add(orderS1);
	Trade trade = ob.getCrossTrade();
	assertEquals(1, trade.size());
	assertEquals(10.0, trade.price());
    }

    //@Test
    public void testCrosserB2S2(){
	SimpleOrderBook ob = new SimpleOrderBook();
	Order orderB1 = new Order(Side.BUY, 10.0, 2);
	Order orderB2 = new Order(Side.BUY, 10.0, 3);
	Order orderS1 = new Order(Side.SELL, 10.0, 4);
	Order orderS2 = new Order(Side.SELL, 10.0, 6);
	ob.add(orderB1);
	ob.add(orderB2);
	ob.add(orderS1);
	ob.add(orderS2);
	Trade trade = ob.getCrossTrade();
	assertEquals(5, trade.size());
	assertEquals(10.0, trade.price());
    }

    //@Test
    public void testCrosserBSBS(){
	SimpleOrderBook ob = new SimpleOrderBook();
	Order orderB1 = new Order(Side.BUY, 8.0, 2);
	Order orderB2 = new Order(Side.BUY, 10.0, 3);
	Order orderS1 = new Order(Side.SELL, 9.0, 4);
	Order orderS2 = new Order(Side.SELL, 11.0, 5);
	ob.add(orderB1);
	ob.add(orderB2);
	ob.add(orderS1);
	ob.add(orderS2);
	Trade trade = ob.getCrossTrade();
	assertEquals(3, trade.size());
	assertEquals(10.0, trade.price());
    }

    //@Test
    public void testCrosserBSSB(){
	SimpleOrderBook ob = new SimpleOrderBook();
	Order orderB1 = new Order(Side.BUY, 8.0, 2);
	Order orderB2 = new Order(Side.BUY, 11.0, 5);
	Order orderS1 = new Order(Side.SELL, 9.0, 1);
	Order orderS2 = new Order(Side.SELL, 10.0, 3);
	ob.add(orderB1);
	ob.add(orderB2);
	ob.add(orderS1);
	ob.add(orderS2);
	Trade trade = ob.getCrossTrade();
	assertEquals(4, trade.size());
	assertEquals(11.0, trade.price());
    }

    //@Test
    public void testCrosserSBBS1(){
	SimpleOrderBook ob = new SimpleOrderBook();
	Order orderB1 = new Order(Side.BUY, 9.0, 3);
	Order orderB2 = new Order(Side.BUY, 10.0, 2);
	Order orderS1 = new Order(Side.SELL, 8.0, 4);
	Order orderS2 = new Order(Side.SELL, 11.0, 1);
	ob.add(orderB1);
	ob.add(orderB2);
	ob.add(orderS1);
	ob.add(orderS2);
	Trade trade = ob.getCrossTrade();
	assertEquals(4, trade.size());
	assertEquals(9.0, trade.price());
    }

    //@Test
    public void testCrosserSBBS2(){
	SimpleOrderBook ob = new SimpleOrderBook();
	Order orderB1 = new Order(Side.BUY, 9.0, 2);
	Order orderB2 = new Order(Side.BUY, 10.0, 5);
	Order orderS1 = new Order(Side.SELL, 8.0, 4);
	Order orderS2 = new Order(Side.SELL, 11.0, 1);
	ob.add(orderB1);
	ob.add(orderB2);
	ob.add(orderS1);
	ob.add(orderS2);
	Trade trade = ob.getCrossTrade();
	assertEquals(4, trade.size());
	assertEquals(10.0, trade.price());
    }

    //@Test
    public void testCrosserSSBB(){
	SimpleOrderBook ob = new SimpleOrderBook();
	Order orderB1 = new Order(Side.BUY, 10.0, 2);
	Order orderB2 = new Order(Side.BUY, 11.0, 6);
	Order orderS1 = new Order(Side.SELL, 8.0, 4);
	Order orderS2 = new Order(Side.SELL, 9.0, 1);
	ob.add(orderB1);
	ob.add(orderB2);
	ob.add(orderS1);
	ob.add(orderS2);
	Trade trade = ob.getCrossTrade();
	assertEquals(5, trade.size());
	assertEquals(11.0, trade.price());
    }


}