package main.in;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import main.entities.Jogos;
import main.utils.HibernateUtil;

public class Algoritmo  {

	History history;
	double probCasa;
	double probx;
	double probFora;
/*	System.out.println(history.getJogo().toString());
	System.out.println("PROB CASA");
	System.out.println(home*100);
	System.out.println(1/history.getJogo().getOdds1()*100);
	System.out.println("ODDS-> "+history.getJogo().getOdds1());

	System.out.println("PROB EMPATE" );
	System.out.println(tie*100);
	System.out.println(1/history.getJogo().getOddsx()*100);	
	System.out.println("ODDS-> "+history.getJogo().getOddsx());

	System.out.println("PROB FORA");
	System.out.println(away*100);
	System.out.println(1/history.getJogo().getOdds2()*100);	
	System.out.println("ODDS-> "+history.getJogo().getOdds2());
	*/
	public static List<Algoritmo> getList() {
		
		List<History> historicoLista = getActualHistory();
		List<Algoritmo> histAlgoritmo = new ArrayList<>();
		
		
		for (History history : historicoLista) {
			if(history.getCasaAtaque()==0 || history.getForaAtaque()==0){
				continue;
			}
		//	System.out.println("INICIANDO O ALGORITMO PARA -> "+history.getJogo().toString() );
			double casa = (history.getCasaAtaque()*0.60+history.getForaDefesa()*0.40);
			double fora = (history.getForaAtaque()*0.60+history.getCasaDefesa()*0.40);
			
			//System.out.println(casa+" - " +fora);
			
			histAlgoritmo.add(calculate(casa, fora,history));
			
			
			
		}
		
		return histAlgoritmo;
		
		
		
		
		
		
		
		
		
		
		
	}
public static Algoritmo calculate(double casa,double fora, History history){
	HashMap<Integer, Double> casalist = new HashMap<>();
	HashMap<Integer, Double> foralist = new HashMap<>();
double home=0.0;
double tie=0.0;
double away=0.0;
	for (int i = 0; i < 7; i++) {
		casalist.put(i, poisson(casa, i));
	//	System.out.println("PROB CASA= "+i+" ->"+casalist.get(i));
		foralist.put(i, poisson(fora, i));
		//System.out.println("PROB FORA= "+i+" ->"+foralist.get(i));
		
	}

	for (int i = 0; i <7; i++) {
		for (int j = 0; j < 7; j++) {
			double probCasa=casalist.get(i);
			double probFora=foralist.get(j);
			if(i==j){//empate
				
				tie=tie+ (probCasa*probFora);
			}else if(i>j){//Casa
				home=home+(probCasa*probFora);
			}else{//Fora
				away+=(probCasa*probFora);
			}
			
		}
	}
	Algoritmo a = new Algoritmo();
	a.setHistory(history);
	a.setProbCasa(home);
	a.setProbx(tie);
	a.setProbFora(away);
	return a;
/*System.out.println(history.getJogo().toString());
System.out.println("PROB CASA");
System.out.println(home*100);
System.out.println(1/history.getJogo().getOdds1()*100);
System.out.println("ODDS-> "+history.getJogo().getOdds1());

System.out.println("PROB EMPATE" );
System.out.println(tie*100);
System.out.println(1/history.getJogo().getOddsx()*100);	
System.out.println("ODDS-> "+history.getJogo().getOddsx());

System.out.println("PROB FORA");
System.out.println(away*100);
System.out.println(1/history.getJogo().getOdds2()*100);	
System.out.println("ODDS-> "+history.getJogo().getOdds2());

System.out.println("-------------------------------------------------------------------------------");*/
}

	public  static ArrayList<History> getActualHistory(){
		
		 Session session = HibernateUtil.getSessionFactory().openSession();	
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         ArrayList<History> lista= new ArrayList<>();  
	       
	         @SuppressWarnings("rawtypes")
			List jogos = session.createQuery("FROM Jogos where estado = 'APOST' and datareal> now() ").list(); 
for (Object object : jogos) {
	lista.add(new History((Jogos) object));
	
}
return lista;
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   return null;
	 }
		
	public static void main(String[] args) {
	//	new Algoritmo().run();
	/*
		double casa = 1.429;
		double fora = 1.022;
		for (int i = 0; i < 6; i++) {
			System.out.println(new Algoritmo().poisson(casa, i));
		}
		for (int i = 0; i < 6; i++) {
			System.out.println(new Algoritmo().poisson(fora, i));
		}
		
	*/

	}
	public static double poisson(double media , int golos){
		return Math.pow(2.71828, -media)*Math.pow(media, golos)/factorial(golos);
	}
	public static int factorial( int x){
		int fact = 1;
		
		 if ( x < 0 )
	       return fact;
	      else
	      {
	         for (int c = 1 ; c <= x ; c++ )
	            fact = fact*c;
	 
	        
	      }
		 return fact;
	}
	public History getHistory() {
		return history;
	}
	public void setHistory(History history) {
		this.history = history;
	}
	public double getProbCasa() {
		return probCasa;
	}
	public void setProbCasa(double probCasa) {
		this.probCasa = probCasa;
	}
	public double getProbx() {
		return probx;
	}
	public void setProbx(double probx) {
		this.probx = probx;
	}
	public double getProbFora() {
		return probFora;
	}
	public void setProbFora(double probFora) {
		this.probFora = probFora;
	}
	
}
