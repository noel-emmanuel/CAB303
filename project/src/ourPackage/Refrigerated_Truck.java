package ourPackage;

import static java.lang.Math.pow;

public class Refrigerated_Truck extends Truck {
	
	private Stock truckContents = new Stock();
	private int capacity;
	private int capacityLimit = 800;

	/**
	 * Creates a new empty truck
	 * 
	 * @author Boyd Brosnan
	 */
	public Refrigerated_Truck() {
		this.capacity = 0;
	}
	
	/**
	 * Adds a stock item to the truck and sets
	 * the capacity to the number of items in the stock
	 * 
	 * @author Boyd Brosnan
	 */
	@Override
	public void addStock(Stock stock) {
		this.truckContents = stock;
		this.capacity = truckContents.getStockSize();
	}
	
	/**
	 * Gets the total cost of the truck and its cargo
	 * 
	 * @author Boyd Brosnan
	 * @return the total cost of the truck
	 */
	@Override
	public double getTotalCost() {
		double cost = 0;
		int lowestTemp = this.getLowestTemp();
		cost += truckContents.getTotalManCost();
		cost += 900 + 200*(pow(0.7,lowestTemp/5));
		return cost;
	}
	
	/**
	 * Gets the stock the truck contains:
	 * it returns the stock object attached to the truck
	 * 
	 * @author Boyd Brosnan
	 * @return the stock object associated to this truck
	 */
	@Override
	public Stock getTruckStock() {
		return this.truckContents;
	}
	
	/**
	 * Gets the control temperature of the item requiring the lowest
	 * temperature contained in the truck's cargo
	 * 
	 * @author Boyd Brosnan
	 * @return the lowest control temperature
	 */
	public int getLowestTemp() {
		int lowestTemp = 10;
		
		for (Item item : this.truckContents.getStock().keySet()) {
			if (item.getTempControl() < lowestTemp) {
				lowestTemp = item.getTempControl();
			}
		}
		
		return lowestTemp;
	}

	public int getCapacity() {
		return 800;
	}

}
