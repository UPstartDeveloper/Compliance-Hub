/*
 * Author: Zain Raza
 *
 * Wash.java
 * Represents a car wash. Subclass of Service.
 *
 * Date created: Monday, August 12, 2019
 */
 
 public class Wash extends Service
 {
 	
 	/* Constructor */
 	public Wash (double c, double d, String r)
 	{
 		super(c, d, r);
 	}
 	
 	/* Implementing abstract parent class methods */
 	
 	/*--------------------------------
 	 * setCost (in dollars)
 	 --------------------------------*/
 	 public double setCost(String plan)
 	 {
 	 	double price = 0; 
 	 	
 	 	if(plan.equalsIgnoreCase("Basic"))
 	 	{
 	 		price = 5;
 	 	}
 	 	else if(plan.equalsIgnoreCase("Advanced"))
 	 	{
 	 		price = 10;	
 	 	}
 	 	else if(plan.equalsIgnoreCase("Extreme"))
 	 	{
 	 		price = 20;
 	 	}
 	 	
 	 	return price;
 	 }
 	  	
 	/*--------------------------------
 	 * setDuration (in minutes)
 	 --------------------------------*/
 	 public double setDuration(String plan)
 	 {
		double time = 0;
		
		if(plan.equalsIgnoreCase("Basic"))
		{
			time = 2;
		}
		else if(plan.equalsIgnoreCase("Advanced"))
		{
			time = 4;
		}
		else if(plan.equalsIgnoreCase("Extreme"))
		{
			time = 5;	
		}
		
		return time;
 	 }

 	/*--------------------------------
 	 * toString
 	 --------------------------------*/
 	 public String toString()
 	 {
 	 	return "You have ordered a wash for this vehicle. \n" +
 	 			displayInfo();
 	 }
 }