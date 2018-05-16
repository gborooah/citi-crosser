package crosser;

public class Trade {
    double price;
    int size;

    public int size(){
	return this.size;
    }

    public double price(){
	return this.price;
    }
    
    public Trade(double price, int size){
	this.price = price;
	this.size = size;
    }
    
    public Trade(){
	this.price = 0.0;
	this.size = 0;
	}
}



