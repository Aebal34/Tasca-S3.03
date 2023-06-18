package n1exercici1;

import java.util.*;
public class Input {

	static Scanner input = new Scanner(System.in);
	
	//READ BYTE
	public static byte readByte(String message) {
		
		boolean correct = false;
		byte finalByte = 0;
		
		//It will keep asking for a byte until the type of data is correctly typed.
		do {
			try {
			System.out.println(message);
			finalByte = input.nextByte();
			correct = true;
			}catch(InputMismatchException e) {
				System.out.println("El tipo de dato introducido no es correcto.");
			}
			input.nextLine(); //We clean the buffer so we can introduce the data again.
		}while(!correct);
			
		return finalByte;
	}
	
	//READ INT
	public static int readInt(String message) {
		
		boolean correct = false;
		int finalInt = 0;
		
		//It will keep asking for an integer until the type of data is correctly typed.
		do {
			try {
				
			System.out.println(message);
			finalInt = input.nextInt();
			correct = true;
			
			}catch(InputMismatchException e) {
				System.out.println("El tipo de dato introducido no es correcto.");
			}
			input.nextLine(); //We clean the buffer so we can introduce the data again.
		}while(!correct);
			
		return finalInt;
	}
	
	//READ FLOAT
	public static float readFloat(String message) {
		
		boolean correct = false;
		float finalFloat = 0.0f;
		
		//It will keep asking for a float until the type of data is correctly typed.
		do {
			try {
				
			System.out.println(message);
			finalFloat = input.nextFloat();
			correct = true;
			
			}catch(InputMismatchException e) {
				System.out.println("El tipo de dato introducido no es correcto.");
			}
			input.nextLine(); //We clean the buffer so we can introduce the data again.
		}while(!correct);
			
		return finalFloat;
	}
	
	//READ DOUBLE
	public static double readDouble(String message) {
		
		boolean correct = false;
		double finalDouble = 0.0;
		
		//It will keep asking for a double until the type of data is correctly typed.
		do {
			try {
				
			System.out.println(message);
			finalDouble = input.nextDouble();
			correct = true;
			
			}catch(InputMismatchException e) {
				System.out.println("El tipo de dato introducido no es correcto.");
			}
			input.nextLine(); //We clean the buffer so we can introduce the data again.
		}while(!correct);
			
		return finalDouble;
	}
	
	//READ CHAR
	public static char readChar(String message) {
		
		boolean correct = false;
		char finalChar = 0;
		
		//It will keep asking for a character until the type of data is correctly typed.
		do {
			try {
			System.out.println(message);
			finalChar = input.nextLine().charAt(0);
			correct = true;
			
			}catch(InputMismatchException e) {
				System.out.println("El tipo de dato introducido no es correcto.");
			}catch(Exception e) {
				System.out.println(e.toString());
			}
		}while(!correct);
			
		return finalChar;
	}
	
	//READ STRING
	public static String readString(String message) {
		
		boolean correct = false;
		String finalString = "";
		
		//It will keep asking for a String until the type of data is correctly typed.
		do {
			try {
			System.out.println(message);
			finalString = input.nextLine();
			correct = true;
			
			}catch(Exception e) {
				System.out.println("Ha habido un error al introducir el String.");
			}
		}while(!correct);
			
		return finalString;
	}
	
	//READ YES-NO
	
	public static boolean readYesOrNo(String message) {
		
		boolean correct = false;
		String yesOrNo = "";
		boolean yn = false;
		
		do {
			try {
				System.out.println(message);
				yesOrNo = input.nextLine().toLowerCase();
				if(yesOrNo.charAt(0) == 'y') {
					yn = true;
					correct = true;
				}else if(yesOrNo.charAt(0) == 'n'){
					yn = false;
					correct = true;
				}else {
					System.out.println("Input is not valid.");
				}
			}catch(Exception e) {
				System.out.println("Ha habido un error al introducir el String.");
			}
		}while(!correct);
		return yn;
	}
}