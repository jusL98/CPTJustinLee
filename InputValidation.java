import arc.*;
import java.awt.Color;

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
		int intCount;
		for(intCount = 0; intCount < strInput.trim().length(); intCount++){
			char charCharacter = strInput.trim().charAt(intCount);
			if(charCharacter < '0' || charCharacter > '9'){
				return false;
			}
		}
		
		return true;
	}
	
	
	/*
	 * stringToInteger method:
	 * Converts a string to integer.
	 * Assumes the string is a valid integer.
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
	 * stringToColor method: 
	 * Used to convert an String in the format of an RGB colour code (xxx, xxx,  xxx  ) into a Color object.
	 * Assumes the string is a valid RGB format.
	 */
	public static Color stringToColor(String strRGB){
		String strCleanedRGB = strRGB.replace(" ", ""); // removes spaces before, after, in between
		
		String strR = strCleanedRGB.substring(0, strCleanedRGB.indexOf(","));
		strCleanedRGB = strCleanedRGB.replaceFirst(strR+",", "");
		int intR = Integer.parseInt(strR);
		
		String strG = strCleanedRGB.substring(0, strCleanedRGB.indexOf(","));
		strCleanedRGB = strCleanedRGB.replaceFirst(strG+",", "");
		int intG = Integer.parseInt(strG);
		
		String strB = strCleanedRGB.substring(0);
		strCleanedRGB = strCleanedRGB.replaceFirst(strB, "");
		int intB = Integer.parseInt(strB);
		
		Color clrRGB = new Color(intR, intG, intB);
		
		System.out.println(intR + "|" + intG + "|" + intB + " USED TO CONVERT TO COLOR"); // CONFIRMATION
		System.out.println();
		
		return clrRGB;
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
		
		System.out.println();
		
		Color c1 = stringToColor("255, 255, 255");
		Color c2 = stringToColor("2, 255, 255");
		Color c3 = stringToColor("22, 255, 255");
		Color c4 = stringToColor("255, 2, 255");
		Color c5 = stringToColor("255, 22, 255");
		Color c6 = stringToColor("255, 255, 2");
		Color c7 = stringToColor("255, 255, 22");
		Color c8 = stringToColor("2, 2, 2");
		Color c9 = stringToColor("22, 22, 22");
	}
}
