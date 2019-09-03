// generate the manifest
/*
Manifest newManifest = new Manifest();
			
generateManifest(hotStock, coldStock, newManifest);

/*
 * Generate a manifest given hashmaps of hot and cold items, and a manifest
 * 
 * @author Boyd Brosnan
 
public void generateManifest(HashMap<Item, Integer> coldStockMap, HashMap<Item, Integer> hotStockMap, Manifest manifest) {

	int coldQuantity = 0;
	for (Item key : coldStockMap.keySet()) {
		coldQuantity += key.getReorderAmount();
	}
	
	int hotQuantity = 0;
	for (Item key : hotStockMap.keySet()) {
		hotQuantity += key.getReorderAmount();
	}
				
	// create the trucks and add them to the manifest
	createTrucksFromStock(coldStockMap, hotStockMap, coldQuantity, hotQuantity, manifest);
	
	
}

/*
 * Create successive stock objects of correct size and optimal distribution and add them
 * to trucks, which are then added to the manifests
 * 
 * @author Boyd Brosnan
 
public void createTrucksFromStock(HashMap<Item, Integer> coldStockMap,HashMap<Item, Integer> hotStockMap, int coldQuantity, int hotQuantity, Manifest manifest) {
	
	// get the total number of items to be added
	int colditems = coldQuantity;
	int hotitems = hotQuantity;
	Refrigerated_Truck unfullFridge = null;
	
	// FOR COLD ITEMS
	while (colditems > 0) {
		// get the number of items to be added to the truck
		int n;
		if (colditems > 800) {
			n = 800;
		} else {
			n = colditems;
		}
		
		Stock stock = new Stock();
		
		colditems -= organiseCargo(coldStockMap, stock, n);			
		
		Refrigerated_Truck truck = new Refrigerated_Truck();
		truck.addStock(stock);	  // add the stock to the truck
		
		// if the truck isnt full, we need to fill it with cold items
		if (stock.getStockSize() < 800) {
			unfullFridge = truck; //save a reference to the truck
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
		
		if (unfullFridge != null) {
			
			stock = unfullFridge.getTruckStock();
			n = stock.getStockSize();
			
			hotitems -= organiseCargo(hotStockMap, stock, n);
			
			// this fridge truck will contain both cold and normal items
			Refrigerated_Truck truck = new Refrigerated_Truck();
			truck.addStock(stock);
			manifest.addTruck(truck);
			
			
		} else {
			
			hotitems -= organiseCargo(hotStockMap, stock, n);
			
			Ordinary_Truck truck = new Ordinary_Truck();
			truck.addStock(stock);	  // add the stock to the truck
			manifest.addTruck(truck);
			// does not matter if the final truck is partially full
			// as at this point there is no stock left to add anyway
		}			

	} // end hot stuff

}

/*
 * Organises the given items into truck sized stocks by modifying the given stock,
 * then returns the number of items added, so that they can removed from the total
 * count in the parent function
 * 
 * @author Boyd Brosnan
 
public int organiseCargo(HashMap<Item, Integer> stockMap, Stock stock, int n) {
	int qnt = 0;

	for (Item key : stockMap.keySet()) {
		if (qnt < n) {
			if (key.getReorderAmount() < (n - qnt)) {
				stock.addItem(key, key.getReorderAmount());
				stockMap.remove(key);
				qnt += key.getReorderAmount();

			} else {
				int partialFill = n - qnt; // get remaining cargo space
				int remainingStock = key.getReorderAmount() - partialFill; // get the remaining stock are partial adding
				stock.addItem(key, partillFill);
				qnt += partialFill;
				stockMap.put(key, remainingStock); // update the stock count for the partially filled stock

			}
		}
	}

	return qnt;
}
*/
