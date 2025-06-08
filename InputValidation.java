import arc.*;

public class InputValidation{
	/*
	 * isValidInteger method:
	 * Checks if a string contains only digits and can be converted to an integer.
	 * Returns true if valid, false if not valid.
	 * Does not support + or - signs.
	 */
	public static boolean isValidInteger(String strInput){
		// Check if string is empty
		if(strInput.trim().isEmpty() || strInput.length() >= 10){ // filters out numbers too large to fit in int - numbers with 10 digits but fit within int are lost but are not within range anyway for use case
			return false;
		}
		
		// Check if string contains only digits
		for(int i = 0; i < strInput.trim().length(); i++) {
			char charCharacter = strInput.trim().charAt(i);
			if(charCharacter < '0' || charCharacter > '9') {
				return false;
			}
		}
		
		return true;
	}
	
	
	/*
	 * stringToInteger method:
	 * Converts a string to integer.
	 * Assumes the string has already been validated as an integer.
	 */
	public static int stringToInteger(String str){
		return Integer.parseInt(str.trim());
	}
	
	
	/*
	 * isInRange method:
	 * Checks if an integer value is within a range of intMin-intMax, inclusive.
	 */
	public static boolean isInRange(int intNum, int intMin, int intMax){
		boolean boolIsInRange = (intNum >= intMin && intNum <= intMax);
		
		return boolIsInRange;
	}
	
	
	/*
	 * main method:
	 * Used for temporary testing only within this file.
	 */
	public static void main(String[] args){
		System.out.println(isValidInteger("")); // false
		System.out.println(isValidInteger("25")); // true
		System.out.println(isValidInteger("2.5")); // false
		System.out.println(isValidInteger("25 ")); // true
		System.out.println(isValidInteger(" 25")); // true
		System.out.println(isValidInteger(" 25 ")); // true
		System.out.println(isValidInteger(" 2 5 ")); // false
		System.out.println(isValidInteger("25252525252525252525")); // false bc can't convert to int
		
		System.out.println();
		
		System.out.println(stringToInteger("25"));
		System.out.println(stringToInteger("25 "));
		System.out.println(stringToInteger(" 25"));
		System.out.println(stringToInteger(" 25 "));
		
		System.out.println();
		
		System.out.println(isInRange(5, 2, 5)); // true
		System.out.println(isInRange(2, 2, 5)); // true
		System.out.println(isInRange(3, 2, 5)); // true
		System.out.println(isInRange(0, 2, 5)); // false
	}
}
