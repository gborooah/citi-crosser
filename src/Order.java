package crosser;

public class Order {
    Side side;
    double price;
    int priceTicks;
    int size;
    
    public Order(Side side, double price, int size){
	this.side = side;
	this.price = price;
	this.priceTicks = (int) (this.price * Crosser.priceMultiplier);
	this.size = size;
    }
}

