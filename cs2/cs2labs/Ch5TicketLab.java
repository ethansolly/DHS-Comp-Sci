package com.ethansolly.cs2labs;

import java.util.Scanner;

public class Ch5TicketLab {
	public static void main (String args[]) {												
		
		double ticket = 0;										//The cost of the ticket.
		
		int speed, limit, diff;									//The speed of the honest driver, the speed limit, and the difference
																//between them respectively.
		
		char zoneChar;											//The inputed character.
		
		boolean zoneKnown = false;								//Whether or not the zone is known.
		
		boolean zone;											//Whether the speeding was recorded in a school zone. 
		
		Scanner keyboard = new Scanner(System.in);				//This is a scanner.
		
		System.out.print("Enter speed traveled\t\t--->\t");
		speed = keyboard.nextInt();								//Gets the speed.
		
		System.out.print("Enter posted speed limit\t--->\t");
		limit = keyboard.nextInt();								//Gets the speed limit.

		
		diff = Math.abs(speed-limit);							//Gets the difference
		
		do {
			System.out.print("Was it a school zone? [Y/N]\t--->\t"); // THE BIG QUESTION //
			zoneChar = keyboard.next().charAt(0);				//Gets the first character of the inputed string.
			if (zoneChar == 'Y') { 								//True or false: the character is Y. If so, then:
				zone = true;									//The driver sped in a school zone
				zoneKnown = true;								//and we now know the answer to our previous question.
			}
			else if (zoneChar == 'N'){ 							//True or false: the character is N. If so, then:
				zone = false; 									//The driver did not speed in a school zone
				zoneKnown = true;								//And we now know the answer to our previous question.
			}
			else {												//If neither was true, then:
				System.out.println("Invalid character. Please answer the question with "
								 + "capital Y meaning 'yes' or capital N meaning 'no'. ");
																//Tell the user that such was the case
				
				zone = false;									//Did this to prevent "The local variable zone may not have been initialized"
																//error.
			}
		} while(zoneKnown != true);								//As long as we don't know the answer to our question, continue asking.
		
		keyboard.close();										//Close the keyboard to save memory.
		
		ticket = 30.0;											//Ticket by default is 30 dollars.
		ticket += (zone ? 6.0 * diff : 3.0 * diff);				//If the speeding was done in a zone, take the difference between the
																//speed and the speed limit, multiply it by 6 and add it to the ticket.
																//If not, then multiply the difference by 3 instead and add it.
		
		if (diff > 30)											//If the difference was greater than 30, add 100 to the ticket.
			ticket += 100.0;
		
		System.out.println("Speed traveled:\t"   + speed);		//All the outputs.
		System.out.println("Posted limit:\t"     + limit);
		System.out.println("School zone:\t"      + zoneChar);
		System.out.println("Ticket amount:\t"    + (int) ticket); //Casted this to eliminate the decimal shown by the double value. There was
																  //no decimal on the lab's example outputs.
	}
}
