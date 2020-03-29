/*
 * Author:  Zain Raza
 *
 * Recommendation.java
 * Represents a users' information, and computes their 
 * recommendation using it.
 *
 * Date: Tuesday July 30, 2019
 */
 
 import java.text.NumberFormat;
 
 public class Recommendation
 {
 	private int carType;
 	private int deliveryDay;
 	private int qualityOfService;
 	private int docNumber;
 	public static int numOfDocs = 0;
 	NumberFormat money = NumberFormat.getCurrencyInstance();
 	
 	//Constructor
 	public Recommendation()
 	{

 		carType = 0;
 		deliveryDay = 0;
 		qualityOfService = 0;
 		numOfDocs++;
 		docNumber = numOfDocs;
 		
 	}
 	
 	public Recommendation(String c, String d, String q, int dN) // intended to construct objects for replacing other objects
 	{
 		setCarType(c);
 		setDelivery(d);
 		setQuality(q);
 		setDocNum(dN);
 	}
 
 	
 	/*////////////////////////////////
 	 * set carType
 	 *////////////////////////////////
 	 
 	 public void setCarType(int c)
 	 {
 	 	carType = c;
 	 }
 	 
 	 public void setCarType(String c)
 	 {
 	 	
 	 	if(c.equalsIgnoreCase("car"))
 	 	{
 	 		carType = 1;	
 	 	}
 	 	else if(c.equalsIgnoreCase("truck"))
 	 	{
 	 		carType = 2;	
 	 	} 
 	 	else if(c.equalsIgnoreCase("bus"))
 	 	{
 	 		carType = 3;	
 	 	}
 	 	else if(c.equalsIgnoreCase("other"))
 	 	{
 	 		carType = 4;	
 	 	}  
 	 }
 	 
 	/*////////////////////////////////
 	 * get carType
 	 *////////////////////////////////
 	 
 	 public String getCarType()
 	 {
 	 	String out = "";
 	 	if (carType == 1)
 	 	{
 	 		out = "car";
 	 	}
 	 	else if (carType == 2)
 	 	{
 	 		out = "truck";
 	 	}
 	 	else if(carType == 3)
 	 	{
 	 		out = "bus";
 	 	}
 	 	else if(carType == 4)
 	 	{
 	 		out = "other";
 	 	}
 	 	
 	 	return out;
 	 }
 	 
 	/*////////////////////////////////
 	 * set deliveryDay
 	 *////////////////////////////////
 	 
 	 public void setDelivery(int d)
 	 {
 	 	deliveryDay = d;
 	 }
 	 
 	 public void setDelivery(String d)
 	 {
 	 	if(d.equalsIgnoreCase("Sunday"))
 	 	{
 	 		deliveryDay = 1;
 	 	}
 	 	else if(d.equalsIgnoreCase("Monday"))
 	 	{
 	 		deliveryDay = 2;
 	 	}
 	 	else if(d.equalsIgnoreCase("Tuesday"))
 	 	{
 	 		deliveryDay = 3;
 	 	}
 	 	else if(d.equalsIgnoreCase("Wednesday"))
 	 	{
 	 		deliveryDay = 4;
 	 	}
 	 	else if(d.equalsIgnoreCase("Thursday"))
 	 	{
 	 		deliveryDay = 5;
 	 	}
 	 	else if(d.equalsIgnoreCase("Friday"))
 	 	{
 	 		deliveryDay = 6;
 	 	}
 	 	else if(d.equalsIgnoreCase("Saturday"))
 	 	{
 	 		deliveryDay = 7;
 	 	}
 	 }
 	/*/////////////////////////////// 
 	 * get deliveryDay
 	 *///////////////////////////////
 	 
 	 public String getDelivery()
 	 {
 	 	String out = "";
 	 	
 	 	switch(deliveryDay)
 	 	{
 	 		case 1:
 	 		out = "Sunday";
 	 		break;
 	 		
 	 		case 2:
 	 		out = "Monday";
 	 		break;
 	 		
 	 		case 3:
 	 		out = "Tuesday";
 	 		break;
 	 		
 	 		case 4:
 	 		out = "Wednesday";
 	 		break;
 	 		
 	 		case 5:
 	 		out = "Thursday";
 	 		break;
 	 		
 	 		case 6:
 	 		out = "Friday";
 	 		break;
 	 		
 	 		case 7:
 	 		out = "Saturday";
 	 		break;
 	 		
 	 		default:
 	 		out = "No delivery day selected.";
 	 	}
 	 	
 	 	return out;
 	 }
 	 
 	/*/////////////////////////////// 
 	 * set qualityOfService
 	 *///////////////////////////////
 	 
 	 public void setQuality(int q)
 	 {
 	 	qualityOfService = q;
 	 }
 	 
 	 public void setQuality(String q)
 	 {
 	 	if(q.equalsIgnoreCase("Basic"))
 	 	{
 	 		qualityOfService = 1; 
 	 	}
 	 	else if(q.equalsIgnoreCase("Advanced"))
 	 	{
 	 		qualityOfService = 2; 
 	 	}
 	 	else if(q.equalsIgnoreCase("Extreme"))
 	 	{
 	 		qualityOfService = 1; 
 	 	}
 	 	
 	 }
 	 
 	/*/////////////////////////////// 
 	 * get qualityOfService
 	 *///////////////////////////////
 	 
 	 public String getQuality()
 	 {
 	 	String out = "";
 	 	
 	 	switch(qualityOfService)
 	 	{
 	 		case 1:
 	 		out = "Basic";
 	 		break;
 	 		
 	 		case 2:
 	 		out = "Advanced";
 	 		break;
 	 		
 	 		case 3:
 	 		out = "Extreme";
 	 		break;
 	 		
 	 		default:
 	 		out = "No service plan selected";
 	 	}
 	 	
 	 	return out;
 	 }
 	 
 	 /*//////////////////////////////
 	  * set docNum
 	  *//////////////////////////////
 	  
 	  public void setDocNum(int dN)
 	  {
 	  	docNumber = dN;
 	  }
 	
 	/*/////////////////////////////// 
 	 * get docNumber
 	 *///////////////////////////////
 	 
 	 public int getDocNo()
 	 {
 	 	return docNumber;
 	 }
 	 
 	 /*/////////////////////////////////////////////
 	  * computePAndT: computes output
 	  * includes both price of service,
 	  * and the amount of time it will take.
 	  */////////////////////////////////////////////
 	  
 	  public String computePAndT()
 	  {
 	  	int cost = 0;
 	  	double time = 0;
 	  	
 	  	//compute cost on car
 	  	if (carType == 1)
 	  	{
 	  		cost += 1000; 
 	  	}
 	  	else if (carType == 2)
 	  	{
 	  		cost += 2000;
 	  	}
 	  	else if (carType == 3)
 	  	{
 	  		cost += 5000;
 	  	}
 	  	else if (carType == 4)
 	  	{
 	  		cost += 10000;
 	  	}
 	  	
 	  	 //compute cost and time on quality, Basic service adds no additional cost	  	 
 	  	if (qualityOfService == 1)
 	  	{
 	  		time = 2;
 	  	}
 	  	else if (qualityOfService == 2)
 	  	{
 	  		cost += 500;
 	  		time = 4;
 	  	}
 	  	else if(qualityOfService == 3)
 	  	{
 	  		cost += 1000;
 	  		time = 0.5;
 	  	}
 	  	
 	  	return "The time of service is: " + time + " hours. \n" +
 	  			"The cost of service is: " + money.format(cost);	  
 	  	
 	  	}
 	  
 	 /*/////////////////////////////////////////////
 	  * toString: information about current value
 	  * of carType, deliveryDay, and qualityOfSerice
 	  */////////////////////////////////////////////
 	  
 	  public String toString()
 	  {
 	  	return "This is the recommendation for Document No.: " + getDocNo() + "\n" +
 	  			"The vehicle type is: " + getCarType() + "\n" +
 	  			"The delivery day is: " + getDelivery() + "\n" +
 	  			"The service plan is: " + getQuality() + "\n" +
 	  			computePAndT();
 	  } 
 }
 