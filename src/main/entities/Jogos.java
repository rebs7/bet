package main.entities;
// Generated 27/jul/2016 16:01:44 by Hibernate Tools 5.1.0.Alpha1

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import main.utils.HibernateUtil;

/**
 * Jogos generated by hbm2java
 */
@Entity
@Table(name = "jogos", catalog = "betting")
public class Jogos implements java.io.Serializable {

	private JogosId id;
	private Integer golos1;
	private Integer golos2;
	private Double odds1;
	private Double oddsx;
	private Double odds2;
	private String estado;
	private Date dcria;
	private Date daltera;
	private String competicao;
	private Date dataReal;

	public Jogos() {
	}

	public Jogos(JogosId id) {
		this.id = id;
	}

	public Jogos(JogosId id, Integer golos1, Integer golos2, Double odds1, Double oddsx, Double odds2, String estado,
			Date dcria, Date daltera, String competicao, Date dataReal) {
		this.id = id;
		this.golos1 = golos1;
		this.golos2 = golos2;
		this.odds1 = odds1;
		this.oddsx = oddsx;
		this.odds2 = odds2;
		this.estado = estado;
		this.dcria = dcria;
		this.daltera = daltera;
		this.competicao = competicao;
		this.dataReal=dataReal;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "casa", column = @Column(name = "casa", nullable = false, length = 20)),
			@AttributeOverride(name = "fora", column = @Column(name = "fora", nullable = false, length = 20)),
			@AttributeOverride(name = "data", column = @Column(name = "data", nullable = false, length = 20)) })
	public JogosId getId() {
		return this.id;
	}

	public void setId(JogosId id) {
		this.id = id;
	}

	@Column(name = "golos1")
	public Integer getGolos1() {
		return this.golos1;
	}

	public void setGolos1(Integer golos1) {
		this.golos1 = golos1;
	}

	@Column(name = "golos2")
	public Integer getGolos2() {
		return this.golos2;
	}

	public void setGolos2(Integer golos2) {
		this.golos2 = golos2;
	}

	@Column(name = "odds1", precision = 22, scale = 0)
	public Double getOdds1() {
		return this.odds1;
	}

	public void setOdds1(Double odds1) {
		this.odds1 = odds1;
	}

	@Column(name = "oddsx", precision = 22, scale = 0)
	public Double getOddsx() {
		return this.oddsx;
	}

	public void setOddsx(Double oddsx) {
		this.oddsx = oddsx;
	}

	@Column(name = "odds2", precision = 22, scale = 0)
	public Double getOdds2() {
		return this.odds2;
	}

	public void setOdds2(Double odds2) {
		this.odds2 = odds2;
	}

	@Column(name = "estado", length = 6)
	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dcria", length = 10)
	public Date getDcria() {
		return this.dcria;
	}

	public void setDcria(Date dcria) {
		this.dcria = dcria;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "daltera", length = 10)
	public Date getDaltera() {
		return this.daltera;
	}

	public void setDaltera(Date daltera) {
		this.daltera = daltera;
	}

	@Column(name = "competicao", length = 45)
	public String getCompeticao() {
		return this.competicao;
	}

	public void setCompeticao(String competicao) {
		this.competicao = competicao;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "datareal", length = 10)
	public Date getDataReal() {
		return this.dataReal;
	}

	public void setDataReal(Date datareal) {
		this.dataReal = datareal;
	}

	 public void add(){
	      Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	      
	      try{
	         tx = session.beginTransaction();
	         this.setDcria(new Date());
	         session.save(this);
	         System.out.println("Jogo Adicionado -> "+this.toString());
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	       
	      }
	     
	   }
	 public void update(){
		 Jogos jogo = this.fromDB();
		 String initial = jogo.getEstado();
		 jogo.setDataReal(this.dataReal);
		 jogo.setGolos1(this.golos1);
		 jogo.setGolos2(this.golos2);
		 jogo.setOdds1(this.odds1);
		 jogo.setOddsx(this.oddsx);
		 jogo.setOdds2(this.odds2);
		 jogo.setEstado();
		 if(!jogo.getEstado().equals(initial)){
			 System.out.println(initial+"->"+jogo.getEstado());
		 jogo.setDaltera(new Date());}
		
		 Session session = HibernateUtil.getSessionFactory().openSession();	
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         
	    
			 session.update(jogo); 
			  System.out.println("Jogo Atualizado -> "+this.toString());
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
	 public boolean exists(){
	      Session session = HibernateUtil.getSessionFactory().openSession();	 
	      try{
	      
	         Jogos jogo =   (Jogos)session.get(Jogos.class, this.getId()); 
	         
	         if(jogo != null){
	        	 return true;
	         }else return false;
	      }catch (HibernateException e) {
	         
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		return false;
	   }

public Jogos fromDB(){
	
	
	Session session = HibernateUtil.getSessionFactory().openSession();	 
    try{
    
       Jogos jogo =   (Jogos)session.get(Jogos.class, this.getId()); 
       
       if(jogo != null){
      	 return jogo;
       }else return null;
    }catch (HibernateException e) {
       
       e.printStackTrace(); 
    }finally {
       session.close(); 
    }
	return null;
 }
	
public void setEstado(){
	this.setEstado("PRV");
	if(this.id.getCasa()!=null && this.id.getFora()!=null && this.getId().getData()!=null && !this.getId().getData().isEmpty() ){
		this.setEstado("CONF");
		if(this.getOdds1()!=null && this.getOddsx()!=null && this.getOdds2()!= null){
			this.setEstado("APOST");
		}
		if(this.golos1 !=null && this.golos2 !=null){
			this.setEstado("TRMND");
			
			
			
		} }
	}
	
public static List<String> getCompetitions(){
	 Session session = HibernateUtil.getSessionFactory().openSession();	
 try{
	    
	 
		 String hql = "select distinct(competicao) from Jogos";
	    	Query query = session.createQuery(hql);
	    
	    	List results = query.list();
	    	return results;
   }catch (Exception e) {
      
      e.printStackTrace(); 
   }finally {
      session.close(); 
   }
	return null;
}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getId().getCasa()+" - "+this.getId().getFora();
	}
	
}	
	
	
	
	
