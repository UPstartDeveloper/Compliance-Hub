/*
 * Author: Zain Raza
 *
 * Service.java
 * Represents a service provided at a car wash.
 *
 * Date created: Monday, August 12, 2019
 */
 
 public abstract class Service
 {
 	private double cost;
 	private double duration;
 	private String renewPeriod; 
 	private int serviceNumber;
 	public static int numOfServices;
 	
 	/*------------------------------------------------------------
 	 * Constructor - adding a new Service
 	 * pre: a vehicle has been entered in ServiceCollection with 
 	 * pertinent details (vehicleType, plan)
 	 * post: constructs a Service object (will be inherited)
 	 ------------------------------------------------------------*/
 	public Service(double c, double d, String r)
 	{
 		cost = c;
 		duration = d;
 		renewPeriod = r;
 		numOfServices++;
 		serviceNumber = numOfServices;
 	}
 	
 	/*------------------------------------------------------------
 	 * Constructor - revising a Service
 	 * pre: a vehicle has been entered in ServiceCollection with 
 	 * pertinent details (vehicleType, plan)
 	 * post: constructs a Service object (will be inherited)
 	 ------------------------------------------------------------*/
 	public Service(double c, double d, String r, int n)
 	{
 		cost = c;
 		duration = d;
 		renewPeriod = r;
 		serviceNumber = n;
 	}
 	/*---------------------------------------------------
 	 * sets price of Service based on order information
 	 ---------------------------------------------------*/
 	public abstract double setCost(String plan);
 	
 	/*-----------------------
 	 * returns cost
 	 -----------------------*/
 	 public double getCost()
 	 {
 	 	return cost;
 	 }
 	 
 	/*---------------------------
 	 * returns duration
 	 ---------------------------*/
 	 public double getDuration()
 	 {
 	 	return duration;
 	 }
 	 
 	/*----------------------------------------------------
 	 * return renewPeriod
 	 ----------------------------------------------------*/
 	 public String getRenew()
 	 {
 	 	return renewPeriod;
 	 }
 
 	/*----------------------------------------------------
 	 * return serviceNumber
 	 ----------------------------------------------------*/
 	 public int getServiceNo()
 	 {
 	 	return serviceNumber;
 	 }
 	 	 
 	/*------------------------------------------------------
 	 * displayInfo: displays information about the Service
 	 ------------------------------------------------------*/
 	public String displayInfo()
 	{
 		return "Here are the details for this service: \n" +
 				"Order number: " + getServiceNo() + "\n" +
 	 			"Duration: " + getDuration() + "\n" +
 	 			"Renewal period: " + getRenew() + "\n" +
 	 			"Cost: " + getCost() + "\n";
 	}
 	
 }