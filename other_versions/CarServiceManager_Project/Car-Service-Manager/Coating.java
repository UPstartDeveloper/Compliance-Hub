/*
 * Author: Zain Raza
 *
 * Coating.java
 * Represents a car coating. Subclass of Service.
 *
 * Date created: Monday, August 12, 2019
 */

 public abstract class Coating extends Service
 {
 	private String coatType;
 	
 	/* Constructor */
 	public Coating (double c, double d, String r, String t)
 	{
 		super(c , d , r);
 		coatType = t; 
 	}
 	 	
 	/*----------------------------------------------------------
 	 * setDuration (in hours)
 	 ----------------------------------------------------------*/
 	 public double setDuration(String plan, String vehicleType)
 	 {
 	 	double time = 0;
 	 	
 	 	if(vehicleType.equalsIgnoreCase("Car") || vehicleType.equalsIgnoreCase("Truck"))
 	 	{
 	 		if(plan.equalsIgnoreCase("Basic"))
 	 		{
 	 			time = 4.5;
 	 		}
 	 		else if(plan.equalsIgnoreCase("Advanced"))
 	 		{
 	 			time = 6.5;	
 	 		}
 	 		else if(plan.equalsIgnoreCase("Extreme"))
 	 		{
 	 			time = 8;
 	 		}
 	 	}
 	 	else if(vehicleType.equalsIgnoreCase("Bus"))
 	 	{
 	 		if(plan.equalsIgnoreCase("Basic"))
 	 		{
 	 			time = 6.7;
 	 		}
 	 		else if(plan.equalsIgnoreCase("Advanced"))
 	 		{
 	 			time = 7.0;	
 	 		}
 	 		else if(plan.equalsIgnoreCase("Extreme"))
 	 		{
 	 			time = 9.5;
 	 		}
 	 	}
 	 	else
 	 	{
			return (Double.valueOf(null));
 	 	}
 	 	
 	 	return time;
 	 }
 	 
 	/*----------------------------------------------------
 	 * return coatType
 	 ----------------------------------------------------*/
 	 public String getType()
 	 {
 	 	return coatType;
 	 }
 	 
 	/*----------------------------------------------------
 	 * displayCoat: displays information about the coating
 	 ----------------------------------------------------*/
 	 public String displayCoat()
 	 {
 	 	return "You have ordered a coating for this vehicle. \n" +
 	 			"Type of coat:"+ getType() + "\n" +
 	 			displayInfo();
 	 }
 	 
 }