package main.utils;

import java.util.ArrayList;

import org.hibernate.hql.internal.ast.tree.BooleanLiteralNode;

public class Utils {

	
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	  public static final String DB_URL = "jdbc:mysql://localhost/betting";
	 public static final String USER = "root";
	  public static final String PASS = "mysql";
	
	public static String competitionsFilter(String competitons){
		
		
	
		
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
		ArrayList<String> comptlList = new ArrayList<>();
			comptlList.add("B1");
			comptlList.add("Jupiler Pro League");
			
			comptlList.add("D1");
			comptlList.add("1. Bundesliga");
			
			comptlList.add("E0");
			comptlList.add("Premier League");
			
			comptlList.add("F1");
			comptlList.add("Ligue 1");
			
			comptlList.add("G1");
			comptlList.add("Ethniki Katigoria");

			comptlList.add("I1");
			comptlList.add("Serie A");

			comptlList.add("N1");
			comptlList.add("Holland");

			comptlList.add("P1");
			comptlList.add("Liga NOS");

			comptlList.add("SC0");
			comptlList.add("Scott. PL");

			comptlList.add("T1");
			comptlList.add("Süper Lig");
			 
		
			
	
		
		for (int i = 0; i < comptlList.size(); i++) {
			if(competitons.contains(comptlList.get(i))){
				return competitons;
			}
		}
		return null;
		}
	}
	
	

