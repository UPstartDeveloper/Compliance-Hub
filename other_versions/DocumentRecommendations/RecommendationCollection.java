/*
 * Author: Zain Raza
 *
 * RecommendationCollection.java
 * Driver class for Recommendation objects. Shown to client.
 * Contains all Recommendation objects associated to client
 * in an ArrayList object.
 *
 * Date: Tuesday July 30, 2019
 */

import java.util.ArrayList;
 
 public class RecommendationCollection
 {
 	private ArrayList<Recommendation> objects;
 	
 	//Constructor
 	public RecommendationCollection()
 	{
 		objects = new ArrayList<Recommendation>();
 	} 	 	

   /*///////////////////////////////////////////////
    * get the object reference variable for the
    * ArrayList encapsulated in the invoking object
    *///////////////////////////////////////////////
    
    public ArrayList<Recommendation> getObj()
    {
    	return objects;
    }
    
   /*///////////////////////////////////////
 	* addRec: adds Recommendation to objects
 	*///////////////////////////////////////
 	
 	public void addRec()
 	{
 		objects.add(new Recommendation());
 	}
 	
   /*///////////////////////////////////////////////
 	* getEnd: return last Recommendation in objects
 	*///////////////////////////////////////////////
 	
 	public Recommendation getEnd()
 	{
 		if (objects.size() != 0)
 		{
 			return objects.get(objects.size()-1);
 		}
 		else
 		{
 			return null;
 		}
 	}
	
   /*///////////////////////////////////////
 	* initCar: initialize carType variable
 	*///////////////////////////////////////
 	  
 	public void initCar()
 	{
 	  System.out.print("What type of vehicle do you own? Please enter\n" +
 	  						"1 if it is a car, \n" +
 	  						"2 if it is a truck \n" +
 	  						"3 if it is a bus, or \n" +
 	  						"4 if it is something else: ");
 	 
 	}
 	
   /*//////////////////////////////////////////////////
 	* initQuality: initialize qualityOfService variable
 	*///////////////////////////////////////////////////
 	
 	public void initQuality()
 	{
	  System.out.print("Which service plan would you like? Please enter\n" +
 	  						"1 for Basic, \n" +
 	  						"2 for Advanced, or \n" +
 	  						"3 for Extreme:  ");	 		
 	}
 	 
 	 
   /*///////////////////////////////////////////////////
 	* initDelivery: initialize deliveryDay variable
 	*///////////////////////////////////////////////////
 	
 	public void initDelivery()
 	{
 	  System.out.print("Which day of the week would you like to take delivery? Please enter\n" +
 	  						"1 for Sunday, \n" +
 	  						"2 for Monday, \n" +
 	  						"3 for Tuesday, \n" +
 	  						"4 for Wednesday, \n" +
 	  						"5 for Thursday, \n" +
 	  						"6 for Friday, or \n" +
 	  						"7 for Saturday:  ");	
 	} 
 	
 	/*/////////////////////////////////////////////////////
 	 * prints the ArrayList in entirety
 	 */////////////////////////////////////////////////////
 	 public void printList()
 	 {
 	 	//String out = "";
 	 	if(getObj().size() == 0)
 	 	{
 	 		System.out.println("There are no previous Recommendations.");
 	 	}
 	 	else
 	 	{
 	 		for(Recommendation r: getObj())
 	 		{
 	 			System.out.print(r + "\n");
 	 			System.out.println("");
 	 		}	
 	 	}
 	 	
 	 //	return out;
 	 	
 	 }
 	 
 	/*//////////////////////////////////////////////////////
 	 * searchDoc: returns a Recommendation based
 	 * on docNumber
 	 * pre: int dC stores docNumber to search for
 	 * post: Recommendation object that corresponds, if any
 	 *///////////////////////////////////////////////////////
 	 
 	 public Recommendation searchDoc(int dC)
 	 {
 	 	for(Recommendation r: getObj())
 	 	{
 	 		if(dC == r.getDocNo())
 	 		{
 	 			return r;
 	 		}	
 	 	}
 	 	
 	 	return null;
 	 }
 	 
 	 /*//////////////////////////////////////////////////////////////////
 	  * remove: removes the Recommendation matching the docNo,
 	  * then decrements numOfDocs,
 	  * and adjusts the remaining docNo's of other Recommendation objects
 	  *///////////////////////////////////////////////////////////////////
 	  
 	  public void remove(int docNo)
 	  {
 	  	if(searchDoc(docNo) == null)
 	  	{
 	  		System.out.println("There is no document matching that ID number.");
 	  	}
 	  	else
 	  	{
 	  		getObj().remove(docNo - 1); // removing Recommendation of interest
 	  			// adjust docNo for all the Recommendations to the right of previous removed Recommendation
 	  			for(int i = docNo - 1; i < objects.size(); i++)
 	  			{
 	  				objects.get(i).setDocNum(objects.get(i).getDocNo() - 1);
 	  			}
 	  	
 	  		Recommendation.numOfDocs -= 1;
 	  	} 
 	  }
 }

