package com.masprogtechs.converter;

public class NumberConverter {
	
	
	public static Double converterToDouble(String strNumber) {
		if(strNumber == null) return 0d;
		// BR 10,25 US 10.25
		String number = strNumber.replaceAll(",", ".");
		
		if(isNumeric(number)) return Double.parseDouble(number);
		
		return 1.0d;
	}
	

	public static boolean isNumeric(String strNumber) {
	
		if(strNumber == null) return false ;
		
		String number = strNumber.replaceAll(",", ".");
		
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
		
		//return false;
	}

}
