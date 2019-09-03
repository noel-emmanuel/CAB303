package ourPackage;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

public class Tests_tests {

	
	
	
	/*
	 * Initial Store Capital before exporting or importing any manifests
	 * 
	 * @author Vaibhav Vachhani
	 * 
	 */
	@Test
	public void initialStoreCapital() {
		Store newStore = new Store("myStore");		
		assert(100000.00== newStore.getCurrentCapital());
		
		
	}
	
	
	/*
	 * Initial Empty Store Inventory before initialising any items.
	 * 
	 * @author Vaibhav Vachhani
	 * 
	 */
	@Test
	public void initialStoreInventory() {
		Store newStore = new Store("myStore");		
		assert(0 == newStore.getCurrentInventorySize());
		
		
	}
	
	
	
	
	
	/*
	 * Store Inventory decreases after importing Sales Log
	 * 
	 * @author  Vaibhav Vachhani
	 * 
	 */
	@Test
	public void stockInventoryAfterImportingSalesLog() {
		Store newStore = new Store("myStore");
		double inventory = newStore.getCurrentInventorySize();
		// what sales log is being referred to here
		// a Stock class containing items needs to be referenced
		newStore.loadSalesLog("C:\\Users\\Vaibhav Vachhani\\Downloads\\SEM-3\\CAB302\\assesment\\project\\302-assignment\\project\\src\\ourPackage\\sales_log_1.csv");
		double inventory2 = newStore.getCurrentInventorySize();
		assertTrue(inventory2 < inventory);
		
		
	}
	
	
	/*
	 * Store Capital increases after importing Sales Log
	 * 
	 * @author  Vaibhav Vachhani
	 * 
	 */
	@Test
	public void stockCapitalAfterImportingSalesLog() {
		Store newStore = new Store("My Store");
		double capital = newStore.getCurrentCapital();
		newStore.loadSalesLog("C:\\Users\\Vaibhav Vachhani\\Downloads\\SEM-3\\CAB302\\assesment\\project\\302-assignment\\project\\src\\ourPackage\\sales_log_1.csv");
		double capital2 = newStore.getCurrentCapital();
		assertTrue(capital2 > capital);
		
		
	}
	
	
	
	
	
	/*
	 * Every refrigerated truck has fixed capacity.
	 * 
	 * @author  Vaibhav Vachhani
	 * 
	 */
	@Test
	public void capacityOfARefrigeratedTruck() {
		Refrigerated_Truck newTruck = new Refrigerated_Truck();
		int cap = newTruck.getCapacity();
		assertEquals(cap, 800);
		
		
		
	}
	
	/*
	 * Every ordinary truck has fixed capacity.
	 * 
	 * @author  Vaibhav Vachhani
	 * 
	 */
	@Test
	public void capacityOfAOrdinaryTruck() {
		Ordinary_Truck newTruck = new Ordinary_Truck();
		int cap = newTruck.getCapacity();
		assertEquals(cap, 1000);
		
		
		
	}
	
	/*
	 * Ordinary truck has fixed capacity on multiple instances.
	 * 
	 * @author  Vaibhav Vachhani
	 * 
	 */
	@Test
	public void capacityOfAOrdinaryTruckOnMultipleInstances() {
		Ordinary_Truck newTruck = new Ordinary_Truck();
		Ordinary_Truck newTruck2 = new Ordinary_Truck();
		assertEquals(newTruck.getCapacity(), newTruck2.getCapacity());
		
		
		
	}
	
	/*
	 * Refrigerated truck has fixed capacity on multiple instances.
	 * 
	 * @author  Vaibhav Vachhani
	 * 
	 */
	@Test
	public void capacityOfARefrigeratedTruckOnMultipleInstances() {
		Refrigerated_Truck newTruck = new Refrigerated_Truck();
		Refrigerated_Truck newTruck2 = new Refrigerated_Truck();
		assertEquals(newTruck.getCapacity(), newTruck2.getCapacity());
		
		
		
	}
	
	
	/*
	 * Comparing capacity on multiple instances.
	 * 
	 * @author  Vaibhav Vachhani
	 * 
	 */
	@Test
	public void comparingCapacitiesOfTrucks() {
		Refrigerated_Truck newTruck = new Refrigerated_Truck();
		Ordinary_Truck newTruck2 = new Ordinary_Truck();
		assertEquals(newTruck2.getCapacity(), newTruck.getCapacity() + 200);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	/*
	 * Item name test
	 * 
	 * @author Boyd Brosnan
	 * 
	 */
	@Test
	public void itemHasCorrectName() {
		
		String actualName = "Chocolate";
		Item item = new Item(actualName, 2, 3, 225, 300, 10);
		String itemName = item.getName();
		assert (itemName == actualName); 		
		
	}
	
	/*
	 * Item name is a string
	 * 
	 * @author Boyd Brosnan
	 */
	@Test
	public void itemHasAStringName() {
		
		Item item = new Item("Milk", 2, 3, 225, 300, 10);
		String itemName = item.getName();
		String string = "aa";
		assert (itemName.getClass().getName() == string.getClass().getName()); 
		
	}
	
	/*
	 * Item has a manufacturing cost
	 * 
	 * @author Boyd Brosnan
	 */
	@Test
	public void itemHasManufacturingCost() {
		double cost = 10;
		Item item = new Item("Milk", cost, 3, 225, 300, 10);
		double itemCost = item.getManCost();
		assert (itemCost == cost);
		
		
	}
	
	/*
	 * Item manufacturing cost is a real value
	 * 
	 * @author Boyd Brosnan
	 */
	@Test
	public void itemHasARealManCost() {
		//double cost = 10;
		Item item = new Item("Milk", 2, 3, 225, 300, 10);
		double itemCost = item.getManCost();
		assert (itemCost > 0);
	}
	
	/*
	 * Item has a sell price
	 * 
	 * @author Boyd Brosnan
	 */
	@Test
	public void itemHasSellPrice() {
		double sp = 10;
		Item item = new Item("Milk", 2, sp, 225, 300, 10);
		double sellPrice = item.getSellPrice();
		assert (sellPrice == sp);
		
		
	}
	
	/*
	 * Item sell price is a real value
	 * 
	 * @author Boyd Brosnan
	 */
	@Test
	public void itemHasARealSellPrice() {
		Item item = new Item("Milk", 2, 3, 225, 300, 10);
		double itemCost = item.getSellPrice();
		assert (itemCost > 0);
	}
	
	/*
	 * Item has a reorder point
	 * 
	 * @author Boyd Brosnan
	 */
	@Test
	public void itemHasReorderPoint() {
		int reorderPoint = 10;
		Item item = new Item("Milk", 2, 3, reorderPoint, 300, 10);
		int itemReorderPoint = item.getReorderPoint();
		assert (reorderPoint == itemReorderPoint);
		
		
	}
	
	/*
	 * Item reorder point
	 * 
	 * @author Boyd Brosnan
	 */
	@Test
	public void itemHasARealReorderPoint() {
		int reorderPoint = 10;
		Item item = new Item("Milk", 2, 3, 225, 300, 10);
		double itemReorderPoint = item.getReorderPoint();
		assert (itemReorderPoint > 0);
	}
	
	
	/*
	 * Item has a real reorder amount
	 * 
	 * @author Boyd Brosnan
	 */
	@Test
	public void itemHasARealReorderAmount() {
		//int reorderAmount = 50;
		Item item = new Item("Milk", 2, 3, 225, 300, 10);
		int itemReorderAmount = item.getReorderAmount();
		assert (itemReorderAmount > 0);
	}
	
	/*
	 * Item has a temperature control it needs
	 * 
	 * @author Boyd Brosnan
	 */
	@Test
	public void itemNeedsATemperature() {
		int temperatureControl = -5;
		Item item = new Item("Chocolate",10,15,10,50,temperatureControl);
		int itemTempControl = item.getTempControl();
		assertTrue (itemTempControl <= 10 && itemTempControl >= -20);
	}
	
	/*
	 * Stock class checking item count
	 * 
	 * @author Boyd Brosnan
	 */
	@Test
	public void checkStockItemCount() {
		Item item = new Item("Chocolate",10,15,10,50,-5);
		Stock stock = new Stock();
		stock.addItem(item,1);
		int numItems = stock.getStockSize();
		assertTrue (numItems == 1);
		
	}
	
	/*
	 * Stock class adding an item
	 * 
	 * @author Boyd Brosnan
	 */
	@Test
	public void addStockItem() {
		Item item = new Item("Chocolate",10,15,10,50,-5);
		Stock stock = new Stock();
		stock.addItem(item,1);
		stock.addItem(item,1);
		assert (stock.getStockSize() == 2);
		
	}
	

	/*
	 * Stock class removing an item
	 * 
	 * @author Boyd Brosnan
	 */
	@Test
	public void removeStockItem() {
		Item item = new Item("Chocolate",10,15,10,50,-5);
		Stock stock = new Stock();
		stock.addItem(item,10);
		stock.removeItem(item,3);
		assert (stock.getStockSize() == 7);
		
	}
	
	/*
	 * Stock get item count
	 * 
	 * @author Boyd Brosnan
	 */
	@Test
	public void stockGetItemCount() {
		Stock stock = new Stock();
		Item item1 = new Item("Chocolate",10,15,10,50,-5);
		stock.addItem(item1,10);
		int numChoc = stock.getItemCount(item1);
		assert (numChoc == 10);
	}
	
	/*
	 * Stock removeItem check if specific actual item count decreases
	 * 
	 * @authoer Boyd Brosnan
	 */
	@Test
	public void stockGetItemCountAfterRemoval() {
		Stock stock = new Stock();
		Item item1 = new Item("Chocolate",10,15,10,50,-5);
		Item item2 = new Item("Candy",10,15,10,50,-5);
		stock.addItem(item1,10);
		stock.addItem(item2,3);
		stock.removeItem(item1,3);
		int numChoc = stock.getItemCount(item1);
		assert (numChoc == 7);
	}
	
	// two choices here, have item count increase by adding that item
	// with addItem(item, count), or with increaseCount(item, count)
	
	/*
	 * Stock class increasing the number of an item a
	 * 
	 * @author Boyd Brosnan
	 */
	@Test
	public void addStockItem_hard() {
		Item item1 = new Item("Chocolate",10,15,10,50,-5);
		Item item2 = new Item("Candy",10,15,10,50,-5);
		Stock stock = new Stock();
		stock.addItem(item1,10);
		stock.addItem(item2,5);
		int initialChocNum = stock.getItemCount(item1);
		stock.addItem(item1,10);
		int finalChocNum = stock.getItemCount(item1);
		assert ( (finalChocNum - initialChocNum) == 10);
		
	}
	
	
	
	/*
	 * Stock class test check existence of item
	 * 
	 * @author Boyd Brosnan
	 */
	@Test
	public void stockCheckItemExist() {
		Item item1 = new Item("Chocolate",10,15,10,50,-5);
		Stock stock = new Stock();
		stock.addItem(item1,10);
		boolean itemExists = stock.itemExists(item1);
		assert (itemExists == true);
	}
	
	
	
	/* 
	 * Manifest valid number of trucks
	 * 
	 * @author Boyd Brosnan
	 */
	@Test
	public void manifestCheckNumberOfTrucks() {
		Refrigerated_Truck newTruck = new Refrigerated_Truck();
		Ordinary_Truck newTruck2 = new Ordinary_Truck();
		Manifest manifest = new Manifest();
		manifest.addTruck(newTruck);
		manifest.addTruck(newTruck2);
		int numTrucks = manifest.numTrucks();
		assert (numTrucks == 2);
		
	}
	
	/*
	 * Manifest add truck test
	 * 
	 * @author Boyd Brosnan
	 */
	@Test
	public void manifestCheckAddTruck() {
		Ordinary_Truck newTruck = new Ordinary_Truck();
		Manifest manifest = new Manifest();
		manifest.addTruck(newTruck);
		int numTrucks = manifest.numTrucks();
		assert (numTrucks == 1);
	}
	
	/*
	 * Manifest remove truck test (this functionality may not be necessary
	 * 
	 * @author Boyd Brosnan
	 */
	@Test
	public void manifestCheckRemoveTruck() {
		Ordinary_Truck newTruck = new Ordinary_Truck();
		Manifest manifest = new Manifest();
		manifest.addTruck(newTruck);
		manifest.removeTruck(newTruck);
		int numTrucks = manifest.numTrucks();
		assert (numTrucks == 00);
	}
	
	/*
	 * Manifest, get total cost
	 * 
	 * @author Boyd Brosnan
	 */
	@Test
	public void manifestCheckTotalCost() {
		Refrigerated_Truck newTruck = new Refrigerated_Truck();
		Ordinary_Truck newTruck2 = new Ordinary_Truck();
		Manifest manifest = new Manifest();
		manifest.addTruck(newTruck);
		manifest.addTruck(newTruck2);
		int temperature = 10;
		double cost = manifest.getTotalCost();
		double actualCost = 998 + 750;
		
		// cost is minimum as trucks have no items
		assert (cost == actualCost);
	}
	
	
	
	
	
	/*
	 * Stock, test that stock returns the correct total purchase price
	 * 
	 * @author Boyd Brosnan
	 */
	@Test
	public void stockCheckTotalManCost() {
		Stock stock1 = new Stock();
		Item item1 = new Item("Chocolate",10,15,10,50,-5);
		stock1.addItem(item1,10);
		double actualCost = 10*10;
		assert (actualCost == stock1.getTotalManCost());
	}
	
	/*
	 * Stock, test that items() returns the contents of the stock
	 * 
	 * @author Boyd Brosnan
	 */
	@Test
	public void stockCheckItemList() {
		Stock stock1 = new Stock();
		Item item1 = new Item("Chocolate",10,15,10,50,-5);
		stock1.addItem(item1,10);
		
		// create a list identical to what the stock should contain
		HashMap<Item, Integer> list = new HashMap<Item, Integer>();
		list.put(item1, 10);
		
		// get the items in the stock and check if it is correct
		assertEquals (list, stock1.getStock());
		
	}

}
