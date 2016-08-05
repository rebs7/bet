import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import main.entities.Jogos;
import main.utils.HibernateUtil;

public class Algoritmo implements  Runnable {

	
	public void run() {
		
		List<History> historicoLista = getActualHistory();
		
		
		for (History history : historicoLista) {
			if(history.getCasaAtaque()==0 || history.getForaAtaque()==0){
				continue;
			}
			System.out.println("INICIANDO O ALGORITMO PARA -> "+history.getJogo().toString() );
			double casa = history.getCasaAtaque()*history.getForaDefesa();
			double fora = history.getForaAtaque()*history.getCasaDefesa();
			System.out.println(casa+" - " +fora);
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}


	public  static ArrayList<History> getActualHistory(){
		
		 Session session = HibernateUtil.getSessionFactory().openSession();	
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         ArrayList<History> lista= new ArrayList<>();  
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
		//new Algoritmo().run();
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
	public double poisson(double media , int golos){
		return Math.pow(2.71828, -media)*Math.pow(media, golos)*100/factorial(golos);
	}
	public int factorial( int x){
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
	
}
