package br.com.jway.claudio.util;

import java.util.Arrays;
import java.util.List;

public class MainTest {

	public static void main(String args[]) {
		
	}
	
	public List<String> split(String linha){
		StringBuilder builder = new StringBuilder(linha);
		boolean inQuotes = false;
		for (int currentIndex = 0; currentIndex < builder.length(); currentIndex++) {
		    char currentChar = builder.charAt(currentIndex);
		    if (currentChar == '\"') inQuotes = !inQuotes;
		    if (currentChar == ',' && inQuotes) {
		        builder.setCharAt(currentIndex, ';');
		    }
		}
		List<String> result = Arrays.asList(builder.toString().split(","));
		for (String a : result){
			System.out.println(a);
		}
		return result;
	}
	
	

}
