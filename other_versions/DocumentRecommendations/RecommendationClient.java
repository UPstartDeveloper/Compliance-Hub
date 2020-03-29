/*
 * Author: Zain Raza
 *
 * RecommendationClient.java
 * Driver class for DocumentRecommendations project.
 *
 * Date: Wednesday, July 31, 2019
 */

 import java.util.Scanner;

 public class RecommendationClient
 {
 	/*------------------------------------
	// main:
	// Represents a dashboard where user
	// may have multiple recommendations.
	-------------------------------------*/
 	public static void main(String[] args)
 	{
 		RecommendationCollection docs = new RecommendationCollection();
 		Scanner input = new Scanner(System.in);
 		int optionChoice = 0; // control variable for which operation user does
 		String choice = ""; // user needs to choose to see all Recommendations or a few
 		int docChoice = 0; // variable to search for a particular Recommendation by its docNumber
		int infoChoice; // used in option 3

 		do
 		{
 			System.out.print("Welcome to your Recommendation Manager! Here are your options: \n" + 
 							   "1: Get a new recommendation \n" +
 							   "2: Look at a previous recommendation \n" +
 							   "3: Edit a recommendation  \n" +
 							   "4: Remove a recommendation \n" +
 							   "5: Leave Recommendation Manager \n" +

 							   "Enter the number of the operation you would like to do: ");
 			optionChoice = input.nextInt();
 			System.out.println("");

 			switch(optionChoice)
 			{
 			case 1:
 			docs.addRec();
 			System.out.println("Now you will be asked to provide information on your vehicle, \n" +
 	  					 "as well as the quality of service you would like, and \n" +
 	  					 "which day of the week you would like your vehicle delivered.");

 	  		System.out.println("");
 	  		docs.initCar();
 	  		docs.getEnd().setCarType(input.nextInt());
 	  		System.out.println("");

 	  		docs.initQuality();
			docs.getEnd().setQuality(input.nextInt());
			System.out.println("");

			docs.initDelivery();
			docs.getEnd().setDelivery(input.nextInt());
 			System.out.println("");

 			optionChoice = 0;
 			break;

 			case 2:
 			System.out.print("Would you like to view all previous documents (y/n)? ");
 			choice = input.next();
 			System.out.println("");

 			if(choice.equalsIgnoreCase("y"))
 			{
 				docs.printList();
 				//System.out.println("");
 			}
 			else if(choice.equalsIgnoreCase("n"))
 			{
 				// perform a doc search
 				System.out.print("Enter the ID number of the document you would like to see: ");
 				docChoice = input.nextInt();
 				System.out.println("");

 				if(docs.searchDoc(docChoice) != null)
 				{
 					System.out.println(docs.searchDoc(docChoice));
 				}
 				else
 				{
 					System.out.println("That document cannot be located.");
 				}
 				System.out.println("");
 			}

 			optionChoice = 0;
 			break;

 			case 3:
 			// perform a doc search
 				System.out.print("Enter the ID number of the document you would like to edit: ");
 				docChoice = input.nextInt();
 				System.out.println("");

 				if(docs.searchDoc(docChoice) == null)
 				{
 					System.out.println("That document cannot be located.");
 				}
 				else
 				{
 					Recommendation temp = docs.searchDoc(docChoice); // temp is alias to selected Recommendation

 					int docPlace = temp.getDocNo();

 					//new Recommendation added to collection
 					docs.getObj().add(docPlace, new Recommendation(
 												temp.getCarType(),
 												temp.getDelivery(),
 												temp.getQuality(),
 												temp.getDocNo()));
 					//old Recommendation deleted
 					docs.getObj().remove(docPlace + 1);

 					//user allowed to make edits
 					System.out.print("What would you like to change in this documents information? \n" +
 										"1) Change the car type \n" +
 										"2) Change the delivery day \n" +
 										"3) Change the service plan \n" +
 										"Enter the number that corresponds: ");
 					infoChoice = input.nextInt();
 					System.out.println("");

 					switch(infoChoice)
 					{
 						case 1:
 						docs.initCar();
 						docs.getObj().get(docPlace).setCarType(input.nextInt());
 						System.out.println("");
 						System.out.println("Your change has been processed sucessfully.");
 						break;

 						case 2:
 						docs.initDelivery();
 						docs.getObj().get(docPlace).setDelivery(input.nextInt());
 						System.out.println("");
 						System.out.println("Your change has been processed sucessfully.");
 						break;

 						case 3:
 						docs.initQuality();
 						docs.getObj().get(docPlace).setQuality(input.nextInt());
 						System.out.println("");
 						System.out.println("Your change has been processed sucessfully.");
 						break;

 						default:
 						System.out.println("Sorry, I was unable to process changes.");
 					}

 				}
 				System.out.println("");

 			optionChoice = 0;
 			break;

 			case 4:
 			// perform a doc search
 				System.out.print("Enter the ID number of the document you would like to remove: ");
 				docChoice = input.nextInt();

 				if(docs.searchDoc(docChoice) == null)
 				{
 					System.out.println("That document cannot be located.");
 				}
 				else
 				{
 					docs.remove(docChoice);
					System.out.println("The document is no longer in the system.");
 				}

 			System.out.println("");
 			optionChoice = 0;
 			break;

 			case 5:
 			System.out.println("Thank you for using Recommendation Manager. Come back soon!");
 			break;

 			default:
 			System.out.println("Sorry, that option is unavailable.");
 			}

 		} while(optionChoice != 5);
 	}
 }
