package levenshtein;

import java.util.List;

import org.apache.log4j.chainsaw.Main;

import main.entities.Equipas;

public class LevenshteinDistance {                                               
    private static int minimum(int a, int b, int c) {                            
        return Math.min(Math.min(a, b), c);                                      
    }                                                                            
                                                                                 
    public static int computeLevenshteinDistance(CharSequence lhs, CharSequence rhs) {      
        int[][] distance = new int[lhs.length() + 1][rhs.length() + 1];        
                                                                                 
        for (int i = 0; i <= lhs.length(); i++)                                 
            distance[i][0] = i;                                                  
        for (int j = 1; j <= rhs.length(); j++)                                 
            distance[0][j] = j;                                                  
                                                                                 
        for (int i = 1; i <= lhs.length(); i++)                                 
            for (int j = 1; j <= rhs.length(); j++)                             
                distance[i][j] = minimum(                                        
                        distance[i - 1][j] + 1,                                  
                        distance[i][j - 1] + 1,                                  
                        distance[i - 1][j - 1] + ((lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1));
                                                                                 
        return distance[lhs.length()][rhs.length()];                           
    }
    public static void main(String[] args) {
    List<Equipas> equipas = Equipas.listAll();
    String a = "Sporting";
    String b="Sp Lisbon";
  /*  for (int i = 0; i < equipas.size(); i++) {
    	a=equipas.get(i).getNome();
    	for (int j = i+1; j < equipas.size(); j++) {
    		b=equipas.get(j).getNome();
    		double levDis = computeLevenshteinDistance(a, b);
			double bigger = Math.max(a.length(),b.length());
			double pct = ((bigger - levDis) / bigger)*100;
			if(pct>40){
				System.out.println(a + "   "+b+"  "+ pct);
			}
		}
		
	}*/
    double levDis = computeLevenshteinDistance(a, b);
	double bigger = Math.max(a.length(),b.length());
	double pct = ((bigger - levDis) / bigger)*100;
	System.out.println(pct);
	}
}