package ourPackage;

import static java.lang.Math.pow;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Singleton implementation for the class Store.
 * 
 * 
 * 
 */


public class Store {
	
	double capital;
	String name;
	Stock inventory;

	/**
	 * Singleton constructor for Store
	 * @author Vaibhav Vachhani & Boyd Brosnan
	 * @name - Name of the store.
	 * @capital - Store's initial captial.
	 * @inventory - Collection of items currently in the store.
	 */
	protected Store(String name) {
		 this.name = name;
		 this.capital = 100000.00;
		 inventory = new Stock();
		
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instance generator method
	 * @author Vaibhav Vachhani
	 * 
	 * 
	 */
	private static class StoreSingletonHolder{
		private final static Store INSTANCE = new Store("myStore");
		}
	
	/**
	 * Instance returning method
	 * @author Vaibhav Vachhani
	 * 
	 * @return returns an instance of the store
	 */
	public static Store getInstance() {
		return StoreSingletonHolder.INSTANCE;
		}
	
	/**
	 * Return the current capital
	 * 
	 * @author Boyd Brosnan
	 * @return returns the current capital of the store
	 */
	public double getCurrentCapital() {
		return getInstance().capital;
	}
	
	/**
	 * Change the capital by the given value
	 * 
	 * @author Boyd Brosnan
	 * @param amount  A real double representing a change in capital.
	 * 
	 */
	public void decreaseCapital(double amount) {
		getInstance().capital -= amount;
		// this operation should never occur
		
	}
	
	/**
	 * Increases capital by amount
	 * 
	 * @param amount in dollars
	 * 
	 * @author Vaibhav Vachhani
	 * 
	 */
	public void increaseCapital(double amount) {
		getInstance().capital += amount;
		
		
	}
	
	/*
	 * Load a manifest into the system
	 * 
	 * @author Boyd Brosnan
	 * @manifest - A Manifest containing trucks containing stock
	 
	public void loadManifest(Manifest manifest) {
		// subtract capital equivalent to total cost
		getInstance().capital -= manifest.getTotalCost();
		// add the items contained in the manifest to the inventory
		getInstance().inventory.add(manifest.items());
	}
	
	*/
	
	
	
	/**
	 * Return the size of the stores inventory
	 * 
	 * @author Boyd Brosnan
	 * @return current inventory size
	 */
	public double getCurrentInventorySize() {
		return getInstance().inventory.getStockSize();
	}
	
	/*
	public void loadSalesLog(Stock stock) {
		getInstance().capital += stock.getTotalSalesCost();
		getInstance().inventory.remove(stock.items());
	}
	*/
	
	/**
	 * Load the item properties fiel at the specified filepath to initialise all the items in the stock.
	 * @param filepath - Filepath of the properties file to be loaded
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @author Vaibhav Vachhani
	 * 
	 * 
	 */	
	public void loadItemProperties(String filepath) {
		
		String line;
		String spiltLineBy = ",";
		
		FileReader fr = null;
		try {
			fr = new FileReader(filepath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);
		try {
			
			while ((line = br.readLine()) != null)
			{
			
			
				String name;
				double manufacturingCost;
			    double sellPrice;
				int reorderPoint;
				int reorderAmount;
				int temperature;
				String[] currentLine = line.split(spiltLineBy);
				
				name = currentLine[0];
				manufacturingCost = Double.parseDouble(currentLine[1]);
				sellPrice = Double.parseDouble(currentLine[2]);
				reorderPoint = Integer.parseInt(currentLine[3]);
				reorderAmount = Integer.parseInt(currentLine[4]);
				// handling the items which don't need a safe temperature
				
				if(currentLine.length ==6)
				{
				temperature = Integer.parseInt(currentLine[5]);
				}
				else {
					temperature = 11;
				}
				
				
				//String test = (name + ","  + manufacturingCost + ","  + sellPrice + ","  + reorderPoint +  ","  + reorderAmount + ","  + temperature);
				
				//System.out.println(test);
				
				
				Item currentItem = new Item(name, manufacturingCost, sellPrice, reorderPoint, reorderAmount, temperature);
				this.inventory.addItem(currentItem, 0);
				
				//this.decreaseCapital(manufacturingCost);
			}
			
			
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		
	}
	
	
	
/**
 * Imports sales log at the specified filpath
 * 
 * @param filepath - sales log file to be uploaded
 * @author Vaibhav Vachhani
 */
public void loadSalesLog(String filepath) {
		
		String line;
		String spiltLineBy = ",";
		//Define a hashmap here
		HashMap<String, Integer> itemsSalesLog = new HashMap<String, Integer>();
		ArrayList<Item> items = new ArrayList<Item>(this.inventory.loop());
		
		
		FileReader fr = null;
		try {
			fr = new FileReader(filepath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot open file.");
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);
		try {
			
			while ((line = br.readLine()) != null)
			{
			
				//reading the string containing item and its quantity
				
				String[] currentLine = line.split(spiltLineBy);
				
				String currentItem_name = currentLine[0];
				int quantity = Integer.parseInt(currentLine[1]);
				
				itemsSalesLog.put(currentItem_name, quantity);
				//Add data into hashmap here
			
				
				
				
			}
			
			for (Item ci : items)
			{
				if(itemsSalesLog.containsKey(ci.getName())) {
					
					this.inventory.removeItem(ci, itemsSalesLog.get(ci.getName()));
					//this.increaseCapital(itemsSalesLog.get(ci.getName()));
					this.increaseCapital(ci.getSellPrice()*itemsSalesLog.get(ci.getName()));
				}
			}
			 
			
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			

			e.printStackTrace();
		}
		
	
		
		
	}

public void importManifest(String filepath) {
	
	String line;
	String spiltLineBy = ",";
	int coldTemp = 10;
	int normalItems = 0;
	double cost = 0;
	//Define a hashmap here
	HashMap<String, Integer> coldItemsInManifest = new HashMap<String, Integer>();
	HashMap<String, Integer> hotItemsInManifest = new HashMap<String, Integer>();
	ArrayList<Item> items = new ArrayList<Item>(this.inventory.loop());
	
	
	FileReader fr = null;
	try {
		fr = new FileReader(filepath);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		System.out.println("Cannot open file.");
		e.printStackTrace();
	}
	BufferedReader br = new BufferedReader(fr);
	try {
		line = br.readLine();
		while (line != null)
		{
			
			String[] currentLine = line.split(spiltLineBy);
			if(currentLine[0].equalsIgnoreCase(">Ordinary"))
			{
				while(!(currentLine[0].equalsIgnoreCase(">Refrigerated")))
				{
					line = br.readLine();
					if (line != null)
					{
						currentLine = line.split(spiltLineBy);
					}
					
					if(line == null || currentLine[0].equalsIgnoreCase(">Refrigerated"))
					{
						break;
					}
					else if (currentLine[0].equalsIgnoreCase(">Ordinary"))
					{
						continue;
					}
					else 
					{
						hotItemsInManifest.put(currentLine[0], Integer.parseInt(currentLine[1]));
						normalItems = normalItems+1;
						System.out.println(currentLine[0]);
						
					}
				}
				if(line != null)
				{
					currentLine[0]=">Refrigerated";
				}
			}
			
			
			else if(currentLine[0].equalsIgnoreCase(">Refrigerated"))
			{
				while (!(currentLine[0].equalsIgnoreCase(">Ordinary")))
				{
					line = br.readLine();
					
					if (line != null) 
					{
						currentLine = line.split(spiltLineBy);
					}
					
					if(line == null || currentLine[0].equalsIgnoreCase(">Ordinary"))
					{
						break;
					}
					else if (currentLine[0].equalsIgnoreCase(">Refrigerated"))
					{
						continue;
					}
					else 
					{
						coldItemsInManifest.put(currentLine[0], Integer.parseInt(currentLine[1]));
						System.out.println(currentLine[0]);
					}
				}
				
				if(line != null)
				{
					currentLine[0] = ">Ordinary";
					
				}
			}
	
				
				
		}
		
			
		}
	
	catch (NumberFormatException | IOException e) {
		// TODO Auto-generated catch block
		

		e.printStackTrace();
	}
	
	
	for (Item ci : items)
	{
		if(coldItemsInManifest.containsKey(ci.getName())) {
			
			
				if(ci.getTempControl()< coldTemp)
				{
				coldTemp = ci.getTempControl();
				}
			
			this.inventory.addItem(ci, coldItemsInManifest.get(ci.getName()));
			//this.increaseCapital(itemsSalesLog.get(ci.getName()));
			this.decreaseCapital(ci.getManCost()*coldItemsInManifest.get(ci.getName()));
			
		}
		
		if(hotItemsInManifest.containsKey(ci.getName())) {
		
		this.inventory.addItem(ci, hotItemsInManifest.get(ci.getName()));
		//this.increaseCapital(itemsSalesLog.get(ci.getName()));
		this.decreaseCapital(ci.getManCost()*hotItemsInManifest.get(ci.getName()));
		
	}
		
	
		
	
	}
	double colCost = 900 + 200*(pow(0.7,(coldTemp/5)));
	double normalCost = 750 + 0.25*normalItems;
	cost = colCost + normalCost;
	this.decreaseCapital(cost);
	
	
}

	/* leaving this here in case something got overlooked
	 *
	public void exportManifest() {
		/*
		 * loop through the stock array
		 * create 2 stocks, hot and cold
		 * have a counter to keep count of the items in the truck
		 * write hot stuff in the file, 
		 * if the truck is full, create a new cold truck, 
		 * continue till done with the cold items, 
		 * fill the cold truck with the hot items,
		 * create new hot truck if needed
		 * 
		 *
		 * 
		 */
			
		/*
		
		
		try {
			FileWriter writer = new FileWriter("exported_manifest.csv");
			ArrayList<Item> itemsList = this.inventory.loop();
			writer.write(">Refrigerated\n");
			for (Item I: itemsList) {
				
				if(this.inventory.getStock().get(I) <= I.getReorderPoint() )
				{
					
					if(I.getTempControl()<11) {
					//String quan = String.valueOf(I.getReorderAmount());
					String line = String.format("%s, %d\n", I.getName(), I.getReorderAmount());
					writer.write(line);
					this.decreaseCapital(I.getManCost()* I.getReorderAmount());
					}
					
				}
			}
			writer.write(">Ordinary\n");
			for (Item I: itemsList) {
				
				if(this.inventory.getStock().get(I) <= I.getReorderPoint() )
				{
					
					if(I.getTempControl()>=11) {
					//String quan = String.valueOf(I.getReorderAmount());
					String line = String.format("%s, %d\n", I.getName(), I.getReorderAmount());
					writer.write(line);
					this.decreaseCapital(I.getManCost() * I.getReorderAmount());
					}
					
				}
			}
			writer.close();	
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/


	/**
	 * Exports a manifest based on current store stock levels.
	 * Loops through the store inventory and finds all items below reorder points.
	 * It then adds adds these items to either hotStock or coldStock hashmaps depending 
	 * on whether the item is temperature controlled or not. A manifest is then created,
	 * and the items in the hashmaps are optimally sorted into trucks, which are then added
	 * to the manifest. Finally, the contents of each truck are looped through and added
	 * exported to the .csv file.
	 * 
	 * @author Vaibhav Vachhani
	 * 
	 * 
	 */
	public void exportManifest(String filepath) {

		try { // setup the writer
			FileWriter writer = new FileWriter(filepath);
			
			// get the current inventory
			Stock currentStock = this.inventory;
			HashMap<Item, Integer> thisStock = new HashMap<>(); // create a hashmap for the inventory
			thisStock = currentStock.getStock();
			// create hashmaps for the two stock lists
			HashMap<Item, Integer> hotStock = new HashMap<>();
			HashMap<Item, Integer> coldStock = new HashMap<>();
			// sort the stock in need of ordering into either normal or cold
			for (Item key: thisStock.keySet())
			{
				 int quantity_current = thisStock.get(key);
				 int reorder = key.getReorderPoint();
				 
				 // check if below reorder points
				 if (quantity_current <= reorder)
					 
				 {
					 // check if temperature controlled
					 if (key.getTempControl() > 10){
						 // add to normal stock
						 hotStock.put(key, key.getReorderAmount());
					 }
					 else {
						 if(key.getTempControl() < 10)
						 {
							 // add to cold stock
							 coldStock.put(key, key.getReorderAmount());
						 }
					 }
				 }
			}
			
			// create the manifest
			Manifest newManifest = new Manifest();
			// populate the manifest
			generateManifest(coldStock, hotStock, newManifest);
			System.out.println("manifest generated");
			// subtract Capital based on manifest cost (done on import)
			// this.decreaseCapital(newManifest.getTotalCost());
			
			// export the manifest into the csv file
			for (Truck truck : newManifest.getTrucks()) {
				if (truck instanceof Refrigerated_Truck) { // truck is refrigerated
					// write to file
					writer.write(">Refrigerated\n");
					Stock stock = truck.getTruckStock();
					for (Item item : stock.getStock().keySet()) {
						
						String line = String.format("%s, %d\n", item.getName(), stock.getStock().get(item) );
						
						writer.write(line);
					}
					
				} else if (truck instanceof Ordinary_Truck) { // truck is normal
					// write to file
					writer.write(">Ordinary\n");
					Stock stock = truck.getTruckStock();
					for (Item item : stock.getStock().keySet()) {
						
						String line = String.format("%s, %d\n", item.getName(), stock.getStock().get(item) );
						
						writer.write(line);
					}
				}
			}
			writer.close(); // close the writer
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
	/**
	 * Generate a manifest given hashmaps of hot and cold items, and a manifest
	 * @param coldStockMap - a hashmap of all the cold items and their reorder amounts
	 * @param hotStockMap - a hashmap of all the ordinary items and their reorder amounts
	 * @param manifest - the manifest to add the trucks to
	 * @author Boyd Brosnan
	 */
	 
	public void generateManifest(HashMap<Item, Integer> coldStockMap, HashMap<Item, Integer> hotStockMap, Manifest manifest) {
		System.out.println("creating manifest");
		// get the quantity of cold and normal items
		int coldQuantity = 0;
		for (Item key : coldStockMap.keySet()) {
			coldQuantity += key.getReorderAmount();
		}
		
		System.out.println("cold quantity " + coldQuantity);
		
		int hotQuantity = 0;
		for (Item key : hotStockMap.keySet()) {
			hotQuantity += key.getReorderAmount();
		}
					
		System.out.println("hot quantity " + hotQuantity);
		
		// create the trucks and add them to the manifest
		createTrucksFromStock(coldStockMap, hotStockMap, coldQuantity, hotQuantity, manifest);
		
		
	}
	
	/**
	 * Create successive stock objects of correct size and optimal distribution and add them
	 * to trucks, which are then added to the manifests
	 * @param coldStockMap - a hashmap of all the cold items and their reorder amounts
	 * @param hotStockMap - a hashmap of all the ordinary items and their reorder amounts
	 * @param coldQuantity - the total number of cold items
	 * @param hotQuantity - the total number of hot items
	 * @param manifest - the manifest to add the trucks to
	 * @author Boyd Brosnan
	 */
	 
	public void createTrucksFromStock(HashMap<Item, Integer> coldStockMap,HashMap<Item, Integer> hotStockMap, int coldQuantity, int hotQuantity, Manifest manifest) {
		
		// get the total number of items to be added
		int colditems = coldQuantity;
		int hotitems = hotQuantity;
		Refrigerated_Truck unfullFridge = null;
		
		System.out.println("creating trucks");
		
		// FOR COLD ITEMS
		while (colditems > 0) {
			System.out.println("cold items " + colditems);
			// get the number of items to be added to the truck
			int n;
			if (colditems > 800) {
				n = 800;
			} else {
				n = colditems;
			}
			
			Stock stock = new Stock();
			
			// subtract the amount of cold items added to the truck
			// from the total number of cold items
			colditems -= organiseCargo(coldStockMap, stock, n);
			
			Refrigerated_Truck truck = new Refrigerated_Truck();
			truck.addStock(stock);	  // add the stock to the truck
			
			// if the truck isnt full, we need to fill it with cold items
			if (stock.getStockSize() < 800) {
				System.out.println("truck wasnt full-----------------------------------------");
				unfullFridge = truck; //save a reference to the truck
				//colditems -= n;
			} else {
				manifest.addTruck(truck); // add the truck to the manifest
			}
			
		} // end cold stuff
		
		
		while (hotitems > 0) {
			int n;
	
			if (hotitems > 1000) {
				n = 1000;
			} else {
				n = hotitems;
			}
			
			Stock stock = new Stock();
			
			if (unfullFridge != null) { // if there is a partially filled cold truck
										// it needs to be filled
				
				
				// set the current stock to be filled to that of the cold truck
				stock = unfullFridge.getTruckStock();
				n = 800 - stock.getStockSize(); // get the remaining space in the truck
				
				// subtract the number of ordinary items added to the truck
				// from the total number of ordinary items
				hotitems -= organiseCargo(hotStockMap, stock, n);
				
				// this fridge truck will contain both cold and normal items
				Refrigerated_Truck truck = new Refrigerated_Truck();
				truck.addStock(stock);
				manifest.addTruck(truck);
				
				unfullFridge = null; // the partially full truck is now full
				
				
			} else {
				//System.out.println("normal truck");
				
				// subtract the number of ordinary items added to the truck
				// from the total number of ordinary items
				hotitems -= organiseCargo(hotStockMap, stock, n);
				//System.out.println("n "+ n);
				//System.out.println("hotitems " + hotitems);
				
				Ordinary_Truck truck = new Ordinary_Truck();
				truck.addStock(stock);	  // add the stock to the truck
				//System.out.println("items in truck " + truck.getTruckStock().getStockSize());
				manifest.addTruck(truck);
				// does not matter if the final truck is partially full
				// as at this point there is no stock left to add anyway				
			}			
	
		} // end hot stuff
	
	}
	
	/**
	 * Organises the given items into truck sized stocks by modifying the given stock,
	 * then returns the number of items added, so that they can removed from the total
	 * count in the parent function. If the items have temperature control, the coldest
	 * items are added to a truck first so that they arent spread across multiple trucks,
	 * which would increase manifest cost.
	 * 
	 * @param stockMap - hashmap containing items and quantities
	 * @param stock - the stock to add the items to
	 * @param n - the max number of items in the stock
	 * @return int - quantity
	 * @author Boyd Brosnan
	 */
	 
	public int organiseCargo(HashMap<Item, Integer> stockMap, Stock stock, int n) {
		
		// change this to a while loop over an iterator.hasNext()
		// while there are iterators remaining, check if iterator
		// is the coldest item, if so add and delete as before
		// otherwise dont delete and continue to loop
		
		Iterator<Item> iter = stockMap.keySet().iterator();
		System.out.println("keyset = " + stockMap.keySet());
		
		int qnt = 0;
		
		while (iter.hasNext()) {
			Item item = iter.next();
			
			int lowestTemp = 11;
			
			// find the coldest temperature of an item in the stock
			for (Item key : stockMap.keySet()) {
				if (key.getTempControl() < lowestTemp) {
					lowestTemp = key.getTempControl();
				}
			}
			
			
			// if the iterator is currently on the coldest item
			if (item.getTempControl() <= lowestTemp) {
				
				if (qnt < n) {
					System.out.println("n " + n);
					
					if ((stockMap.get(item) + qnt) < n) {
						System.out.println("item being added " + item.getName() + " qnty " + stockMap.get(item));
						stock.addItem(item, stockMap.get(item)); // add the reorder amount
						qnt += stockMap.get(item); // add the item quantity to the total cargo quantity
						boolean lastit = iter.hasNext();
						iter.remove();						
						System.out.println("has next = " + lastit);
						
					} else {
						int partialFill = n - qnt; // get remaining cargo space
						int remainingStock = stockMap.get(item) - partialFill; // get the remaining stock are partial adding
						System.out.println("item being partially added " + item.getName() + " qnty " + partialFill);
						stock.addItem(item, partialFill);
						qnt += partialFill;
						stockMap.replace(item, remainingStock); // update the stock count for the partially filled stock
					}
				}
			}
		}
		System.out.println("S T O C K   F I L L E D          " + qnt);
		// return the quantity of items contained in the truck
		return qnt;
	}
}
