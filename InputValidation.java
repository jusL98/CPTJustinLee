import arc.*;

public class InputValidation{
	/*
	 * isValidInteger method:
	 * Checks if a string contains only digits and can be converted to an integer.
	 * Returns true if valid, false if not valid.
	 * Does not support + or - signs. Does not validate for numbers that exceed what int can hold.
	 */
	public static boolean isValidInteger(String strInput){
		// Check if string is empty
		if(strInput.trim().isEmpty()) {
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
		System.out.println(isValidInteger("25252525252525252525")); // true but can't convert to int
	}
}
