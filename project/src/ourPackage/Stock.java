package ourPackage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Stock class
 * each stock object has a collection of items stored in a hashmap
 * 
 * @author Vaibhav Vachhani
 */
public class Stock {
	private HashMap<Item, Integer> itemsInStock;

	public Stock() {
		itemsInStock = new HashMap<>();
	
	}
	
	/**
	 * returns an integer representing the total number of items currently in the stock
	 * 
	 * 
	 * @author Vaibhav Vachhani
	 * @return integer representing size of the stock
	 */
	public int getStockSize() {
		int size=0;
		
		Collection<Integer> list = new ArrayList<Integer>();
		list = itemsInStock.values();
		for (int i: list)
		{
			size = size + i;
		}
		
		
		return size;
		
	}
	
	/**
	 * adds the item to the stock, updates quantity if the item already exists in the stock
	 * 
	 * @param item - item to be added to the stock
	 * @param quantity - quantity of the item to be added to the stock
	 * 
	 * @author Vaibhav Vachhani
	 */
	
	public void addItem(Item item, int quantity) {
		
		
		if(itemsInStock.containsKey(item)){
			int quan = itemsInStock.get(item);
			quan = quan + quantity;
			itemsInStock.put(item, quan);
			
			
			
		}
		else {
		
		
		itemsInStock.put(item, quantity);
		}
	}
	
	
	/**
	 * decreases the quantity of the an item
	 *  
	 * @param item - item to be decreased in stock
	 * @param quantity - quantity to be removed
	 * 
	 * @author Vaibhav Vachhani
	 */
	
	public void removeItem(Item item, int quantity)
	{
		int quan = itemsInStock.get(item);
		if(quan >= quantity) {
		quan = quan - quantity;
		}
		
		itemsInStock.put(item, quan);
		
	}
	
	
	
	/**
	 * returns an integer representing the quantity of the item
	 *  
	 * @param item - item in the stock
	 * 
	 * @author Vaibhav Vachhani
	 * @return int representing quantity of the item
	 */	
	public int getItemCount(Item item)	{
		
		
		int count = itemsInStock.get(item);
		return count;
		
	}


	/**
	 * returns true if the item exists in the stock, false otherwise
	 *  
	 * @param item - An item
	 * 
	 * @author Vaibhav Vachhani
	 * @return returns true if items exists, false otherwise
	 */	
	public boolean itemExists(Item item) {
		
		boolean exists= false;
		
		if (itemsInStock.containsKey(item))
		{
			exists = true;
		}
		
		return exists;
	}
	
	
	/**
	 * Returns a HashMap containing all the items currently in stock with their quantities as values.
	 * @return HashMap - items currently in the stock
	 */
	public HashMap<Item, Integer> getStock() {
		return itemsInStock;
	}
	
	public double getTotalManCost() {
		double totalManCost = 0;
		for (Item key : itemsInStock.keySet()) {
			int count = itemsInStock.get(key);
			double manCost = key.getManCost();
			totalManCost += count*manCost;
		}
		
		return totalManCost;
	}
	
	
	/**
	 * loops through the current stock of items and returns an ArrayList of type Item
	 * @return - ArrayList of type Item.
	 */
	public ArrayList<Item> loop() {
		ArrayList<Item> arr = new ArrayList<Item>(this.itemsInStock.keySet());		
		return arr;
		
	}
	
}
