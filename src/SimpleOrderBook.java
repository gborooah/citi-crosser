package crosser;
import java.util.TreeMap;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;
import java.util.Collections;



public class SimpleOrderBook {
    //keeps price, qty only
    int totalSharesBuy = 0;
    int totalSharesSell = 0;
    TreeMap<Integer, Integer> buyLevels = new TreeMap<Integer, Integer>(Collections.reverseOrder());
    TreeMap<Integer, Integer> sellLevels = new TreeMap<Integer, Integer>();
    
    public SimpleOrderBook(){}
    
    public void add(crosser.Order order){
	if(order.side== crosser.Side.BUY){
	    totalSharesBuy += order.size;
	    int count = buyLevels.containsKey(order.priceTicks) ? buyLevels.get(order.priceTicks) : 0;
	    buyLevels.put(order.priceTicks, count + order.size);
	}else{
	    totalSharesSell += order.size;
	    int count = sellLevels.containsKey(order.priceTicks) ? sellLevels.get(order.priceTicks) : 0;
	    sellLevels.put(order.priceTicks, count + order.size);
	}
    }	
    
    public Trade getCrossTrade(){
	int crossPriceTicks = 0;
	int crossSize = 0;
	
	
	if(buyLevels.isEmpty() || sellLevels.isEmpty() || totalSharesBuy <= 0 || totalSharesSell <=0){
	    return new Trade();
	}
	
	int currentBuyMatched = 0;
	int currentSellMatched = totalSharesSell;
	
	int currentBid = 0;
	int currentAsk = 0;
	int currentAskSize = 0;
	
	Iterator<Map.Entry<Integer, Integer>> sell_it = sellLevels.descendingMap().entrySet().iterator();
	if(sell_it.hasNext()){
	    Map.Entry<Integer, Integer> sellPair = sell_it.next();
	    currentAsk = sellPair.getKey();
	    currentAskSize = sellPair.getValue();
	}else{
	    currentSellMatched = 0;
	}
	
	for(Map.Entry<Integer, Integer> buyPair : buyLevels.entrySet()){
	    currentBid = buyPair.getKey();
	    int currentSize = buyPair.getValue();
	    currentBuyMatched += currentSize;
	    
	    while(currentSellMatched > 0 && currentAsk > currentBid){
		currentSellMatched -= currentAskSize;
		if(sell_it.hasNext()){
		    Map.Entry<Integer, Integer> sellPair = sell_it.next();
		    currentAsk = sellPair.getKey();
		    currentAskSize = sellPair.getValue();
		    }
		else{
		    break;
		}
	    }
	    
	    int matchedSize = Math.min(currentBuyMatched, currentSellMatched);
	    if(matchedSize > crossSize){
		crossSize = matchedSize;
		crossPriceTicks = currentBid;
	    }
	    if(matchedSize==0){
		break;
	    }    
	}
	Trade cross = new Trade((double)(crossPriceTicks)/Crosser.priceMultiplier, crossSize);
	return cross;
    }
}