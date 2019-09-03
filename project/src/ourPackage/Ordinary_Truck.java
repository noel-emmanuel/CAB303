package ourPackage;

public class Ordinary_Truck extends Truck {
	
	private Stock truckContents = new Stock();
	private int capacityLimit = 1000;
	private int capacity;
	
	/**
	 * Creates a new empty truck
	 * 
	 * @author Boyd Brosnan
	 */
	public Ordinary_Truck() {
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
		cost += truckContents.getTotalManCost(); // the cost of the cargo
		cost += 750 + 0.25*truckContents.getStockSize(); // the cost of hiring the truck
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
	public int getCapacity() {
		// TODO Auto-generated method stub
		return 1000;
	}

}
