/*
 * Author: Zain Raza
 *
 * Initial.java
 * Represents a type of Coating for a vehicle.
 *
 * Date created: Tuesday, August 13, 2019
 */
 
 public class Initial extends Coating
 {
 	/* Constructor */
 	
 	public Initial(double c, double d, String r, String t)
 	{
 		super(c, d, r, t);
 	}
 	
 	/*--------------------------------
 	 * setCost (in dollars)
 	 --------------------------------*/
 	 public double setCost(String plan)
 	 {
 	 	double price = 0; 
 	 	
 	 	if(plan.equalsIgnoreCase("Basic"))
 	 	{
 	 		price = 7;
 	 	}
 	 	else if(plan.equalsIgnoreCase("Advanced"))
 	 	{
 	 		price = 11;	
 	 	}
 	 	else if(plan.equalsIgnoreCase("Extreme"))
 	 	{
 	 		price = 13;
 	 	}
 	 	
 	 	return price;
 	 }
 }