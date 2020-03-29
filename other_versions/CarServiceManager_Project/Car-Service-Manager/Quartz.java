/*
 * Author: Zain Raza
 *
 * Quartz.java
 * Represents a type of Coating for a vehicle.
 *
 * Date created: Tuesday, August 13, 2019
 */
 
 public class Quartz extends Coating
 {
 	/* Constructor */
 	
 	public Quartz(double c, double d, String r, String t)
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
 	 		price = 14;
 	 	}
 	 	else if(plan.equalsIgnoreCase("Advanced"))
 	 	{
 	 		price = 22;	
 	 	}
 	 	else if(plan.equalsIgnoreCase("Extreme"))
 	 	{
 	 		price = 45;
 	 	}
 	 	
 	 	return price;
 	 }
 }