package ourPackage;


/**
* Item class initialises an item and contains methods for item.
* 
* 
* 
* 
*/
public class Item {
	
	private String name;
	private  double manufacturingCost;
	private double sellPrice;
	private int reorderPoint;
	private int reorderAmount;
	private int temperature;

	/**
	 * Initialising an Item 
	 * 
	 * @param name - Name of the item
	 * @param manufacturingCost - the manufacturing cost of the item
	 * @param sellPrice - the selling price of the item
	 * @param reorderPoint - the amount at which the item is added to the manifest
	 * @param reorderAmount - the quantity of item to be re-ordered
	 * @param temperature - the temperature at which the item must be kept
	 * 
	 * 
	 * @author Vaibhav Vachhani
	 * 
	 * 
	 */
	
	public Item(String name, double manufacturingCost, double sellPrice, int reorderPoint, int reorderAmount, int temperature ) { 
		
		this.name = name;
		this.manufacturingCost = manufacturingCost;
		this.sellPrice = sellPrice;
		this.reorderPoint = reorderPoint;
		this.reorderAmount = reorderAmount;
		this.temperature = temperature;
		
		
		
		
		
		
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Returns the name of the item.
	 * 
	 * @author Vaibhav Vachhani
	 * @return name of the item
	 */
	
	public String getName() {
		String name = this.name;
		return name;
	}
	
	/**
	 * Returns the manufacturing cost of the item.
	 * 
	 * @author Vaibhav Vachhani
	 * @return the manufacturing cost of the item
	 */
	public double getManCost() {
		
		double manCost = this.manufacturingCost;
		return manCost;
	}
	
	/**
	 * Returns the selling price of the item.
	 * 
	 * @author Vaibhav Vachhani
	 * @return the selling price of the item
	 */
	public double getSellPrice() {
		
		double price = this.sellPrice;
		return price;
	}
	
	/**
	 * Returns the amount at which the item is to be re ordered.
	 * 
	 * @author Vaibhav Vachhani
	 * @return the reorder point of the item
	 */
	public int getReorderPoint () {
		int reorderPoint = this.reorderPoint;
		return reorderPoint;
		
	}
	
	/**
	 * Returns the quantity of the item to be re-ordered.
	 * 
	 * @author Vaibhav Vachhani
	 * @return the reorder quantity of the item
	 */
	public int getReorderAmount()
	{
		int reorderAmount = this.reorderAmount;
		return reorderAmount;
	}
	
	
	
	
	/**
	 * Returns the temperature control of the item
	 * 
	 * 
	 * @author Boyd Brosnan
	 * @return the control temperature of the item
	 */
	public int getTempControl() {
		
		return(this.temperature);
	}
	

}
