import org.hibernate.Session;
import org.hibernate.Transaction;

import main.entities.Equipas;
import main.utils.HibernateUtil;

public class app {

	public static void main(String[] args) {
		
	
	 Session session = HibernateUtil.getSessionFactory().openSession();
	 session.beginTransaction();
	 Equipas f = new Equipas(2, "benfica", null, null);
	 session.save(f);
	 session.getTransaction().commit();
	}
}
