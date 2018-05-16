package crosser;
import java.util.Random;

public class Crosser {

    static int priceMultiplier = 10000;

    int numTicks;
    double sizeMean;
    double sizeSD;
    double priceMean;
    double priceSD;
    Random rand;
    SimpleOrderBook orderBook;

  


    public Crosser(int numTicks, double sizeMean, double sizeSD, double priceMean, double priceSD){
	this.numTicks = numTicks;
	this.sizeMean = sizeMean;
	this.sizeSD = sizeSD;
	this.priceMean = priceMean;
	this.priceSD = priceSD;
	
	this.rand = new Random();
	this.orderBook = new SimpleOrderBook();

    }
    
    public void run(){
	for (int ticks = 0; ticks < numTicks; ticks++){
	    Order buyOrder = randomOrder(Side.BUY);
	    Order sellOrder = randomOrder(Side.SELL);
	    orderBook.add(buyOrder);
	    orderBook.add(sellOrder);
	}

	Trade trade = orderBook.getCrossTrade();
	System.out.println("Trade generated:" + trade.price + " " + trade.size);
    }

    public Order randomOrder(Side side){
	int  size = Math.max(1, (int) (rand.nextGaussian() * sizeSD + sizeMean));
	double price = Math.max(0.0001, rand.nextGaussian() * priceSD + priceMean); 
	Order order = new Order(side, price, size);
	return order;
    }
	   
    public static void main(String[] args) {
	int numTicks = 30;
	double sizeMean = 100000.0;
	double sizeSD = 20000.0;
	double priceMean = 50.0;
	double priceSD = 6.0;
	
	Crosser crosser = new Crosser(numTicks, sizeMean, sizeSD, priceMean, priceSD);
	crosser.run();
    }

}
