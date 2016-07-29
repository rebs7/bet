package main.utils;

import java.util.ArrayList;

public class Utils {

	
	
	
	
	public static String competitionsFilter(String competitons){
		
		
		ArrayList<String> toignoreList = new ArrayList<>();
			toignoreList.add("Fem");
			toignoreList.add("Praia");	
			toignoreList.add("S19");	
			toignoreList.add("S20");	
			toignoreList.add("S21");	
			toignoreList.add("S17");	
		
		for (int i = 0; i < toignoreList.size(); i++) {
			if(competitons.contains(toignoreList.get(i))){
				return null;
			}
		}
		
		ArrayList<String> toReplace = new ArrayList<>();
		toReplace.add("2015/2016");
		toReplace.add("2016/2017");
		toReplace.add("2015/16");
		toReplace.add("2016/17");
		toReplace.add("16/17");
		toReplace.add("2016");
		toReplace.add("16");
		toReplace.add("17");
		for (int i = 0; i < toReplace.size(); i++) {
			competitons=competitons.replace(toReplace.get(i), "");
			}
		return competitons;
		}
	}
	
	

