/*
 * Author: Zain Raza
 *
 * Client.java
 * Represents a kiosk used by a client
 * at a car service provider.
 *
 * Date created: Tuesday, August 13, 2019
 */
 
 import java.util.Scanner;
 import java.util.ArrayList;
 
 public class Client
 {
 	/*--------------------------------------------------------------------
 	 * getVehicle: 
 	 * pre: ArrayList of Vehicle objects, idNum represents a vehicleNumber 
 	 * post: Vehicle output has matching vehicleNumber to idNum
 	 --------------------------------------------------------------------*/
 	public static Vehicle getVehicle(ArrayList<Vehicle> list, int idNum)
 	{
 		Vehicle output;
 		for(Vehicle v: list)
 		{
 			if(v.getVehicleNo() == idNum)
 			{
 				output = v;
 				return output;
 			}
 		}
 		
 		return null;
 	}
 	
 	/*------------------------------------
 	 * main method:
 	 * UI for car service customers 
 	 * to enter their vehicle info and
 	 * order services for the vehicle
 	 ------------------------------------*/
 	public static void main(String[] args)
 	{
 		int choice = 6; // user choooses operation
 		Scanner scan = new Scanner(System.in);
 		ArrayList<Vehicle> cars = new ArrayList<Vehicle>();
 		
 		/*
 		 * 0: quit
 		 * 1: add vehicle
 		 * 2: order a service
 		 * 3: edit vehicle or order info
 		 * 4: delete a vehicle or order
 		 * 5: view order history (gives option to see all vehicles or just one)
 		 */
 		do
 		{
 			System.out.print("What would you like to do? Enter the corresponding number: ");
 			choice = scan.nextInt();
 			System.out.println("");
 			int carID = 0; // stores vehicleNumber user is looking for
 			int serviceChoice = 0; // stores the number of the service user selects
			String wordChoice = ""; // stores literal input from users

 			switch(choice)
 			{
 				case 0:
 				System.out.println("Thank you for using the Car Service Manager!");
 				
 				break;
 				
 				case 2:
 				/* Check to make sure there are vehicles in the system */
 				if(cars.size() == 0)
 				{
 					System.out.println("Sorry, you cannot order a service until you enter in a vehicle.");
 					System.out.println("");
 					break;
 				}
 				else
 				{
 					System.out.print("Enter the ID number of the vehicle you are servicing: ");
 					carID = scan.nextInt();
 					System.out.println("");
 					
 					// Check to ensure ID number is valid
 					if(getVehicle(cars, carID) == null)
 					{
 						System.out.println("Sorry, that is not a valid ID number.");
 					}
 					else
 					{
 						// prompt user to choose service
 						System.out.print("Which service would you like to order? \n\n" +
 											"1: Car wash \n" +
 											"2: Ceramic coating \n\n" +
 											"Enter the number of the service you would like: ");
 						serviceChoice = scan.nextInt();
 						System.out.println("");
 						switch(serviceChoice)
 						{
 							case 1: 
 							System.out.print("You are about to buy a one-time wash for $2.99. \n" +
 												"Would you like to pay for a subscription? Here are your options: \n" +
 												"0: No subscription \n" +
 												"1: Seasonal: 3 months for one charge of $8.99 \n" +
 												"2: Bi-annual: 6 months for one charge of $11.99 \n" +
 												"3: Yearly: 12 months for -one charge of $24.99 \n" +
												"4: No thanks, I don't really need a wash today\n\n" +
 												"Enter the number of your choice: ");	
 							if(scan.nextInt() == 4) // user cancels on wash order
							{
								System.out.println("No problem. We hope to see you again soon!");
								break;
							}
 							else // user picks a wash
							{
								getVehicle(cars, carID).initWash(scan.nextInt());
								System.out.println("");
								break;
							}
 							
 							case 2:
								// user selects a coat, system scans the vehicle for previous coats
								if(!(getVehicle(cars, carID).searchForCoating())) // no previous coats done on the car
								{
									System.out.print("Are you ready to order an initial coating for $20 on your vehicle? \n" +
													 "Please enter 1 to confirm, or 0 to decline: ");
													 serviceChoice = scan.nextInt();
													 System.out.println();

									if(serviceChoice == 1) // user accepts inital, now system offers user a second purchase right away
									{
										// add Inital object to the vehicle here
										System.out.print("Thank you for confirming your purchase. Would you like to add " +
															"a quartz coating onto your vehicle as well, for only $10 more?\n" +
															"Please enter 1 to confirm, or 0 to decline: ");
															serviceChoice = scan.nextInt();
															System.out.println();
										if(serviceChoice == 1) // user accepts second purchase right away
										{
											//add Quartz object here to vehicle
											System.out.println("Thanks for doing business with us. Enjoy your new shine!");
											break;
										}
										else // user declines second purchase right away
										{
											System.out.println("You're a wise spender. Enjoy your new shine!");
											break;
											// Issue here: find out how to follow up user by capturing contact info.
											// to be implemented for the business account version
										}
									}
							 		else // user declines initial coating
									{
										System.out.println("No problem, we wish you an awesome day!");
										// Issue here: find out how to follow up user by capturing contact info.
										// to be implemented for the business account version
										break;
									}
								}
								else // initial coating already detected on car, Quartz coating is offered
								{
									System.out.print("Thank you for your previous purchase. Would you like to add " +
											"a quartz coating onto your vehicle as well for $20?\n" +
											"Please enter 1 to confirm, or 0 to decline: ");
									serviceChoice = scan.nextInt();
									System.out.println();
									if (serviceChoice == 1) // user accepts second purchase
									{
										//add Quartz object here to vehicle
										System.out.println("Thanks for doing business with us. Enjoy your new shine!");
										break;
									}
									else // user declines second purchase
									{
										System.out.println("No problem, we wish you have an awesome day!");
										break;
									}
								}
 							
 							default:
 							System.out.println("Sorry, we do not currently offer that option.");
 						}
 						
 					}
 						
 				}
 				
 				// break;
 				
 				default:
 				System.out.println("Sorry, that is not a valid option. Please try again.\n");
 			}
 		} while(choice != 0);
 	}
 }