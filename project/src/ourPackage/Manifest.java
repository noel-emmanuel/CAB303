package ourPackage;

import java.util.ArrayList;
import java.util.List;

public class Manifest {

	
	private List<Truck> listOfTrucks;
	
	/**
	 * Initialising a manifest
	 * A manifest is simply a collection of trucks
	 * @author Vaibhav Vachhani
	 */
	public Manifest() {
		this.listOfTrucks = new ArrayList<Truck>();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * return number of trucks in the manifest
	 * 
	 * @author Vaibhav Vachhani
	 * @return the number of trucks in the manifest
	 */
	public int numTrucks()
	{
		return this.listOfTrucks.size();
	}
	
	/**
	 * adds truck to the manifest
	 * @param truck - truck to be added
	 * 
	 * @author Vaibhav Vachhani
	 */
	public void addTruck(Truck truck) {
		this.listOfTrucks.add(truck);
	}
	
	/**
	 * removes truck from the manifest
	 * @param truck - truck to be removed
	 * 
	 * @author Vaibhav Vachhani
	 */
	public void removeTruck(Truck truck) {
		
		this.listOfTrucks.remove(truck);
	}
	
	/**
	 * Gets the total cost of the manifest
	 * 
	 * @author Boyd Brosnan
	 * @return the total cost of the manifest
	 */
	public double getTotalCost() {
		double cost = 0;
		for (Truck truck : listOfTrucks) {
			cost += truck.getTotalCost();
		}
		return cost;
	}
	
	/**
	 * Gets the trucks in the manifest
	 * 
	 * @author Boyd Brosnan
	 * @return all the trucks in the manifest
	 */
	public List<Truck> getTrucks() {
		return this.listOfTrucks;
	}

}
