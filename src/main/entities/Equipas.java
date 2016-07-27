package main.entities;
// Generated 27/jul/2016 16:01:44 by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Equipas generated by hbm2java
 */
@Entity
@Table(name = "equipas", catalog = "betting")
public class Equipas implements java.io.Serializable {

	private String nome;
	private Date dcria;
	private Date daltera;

	public Equipas() {
	}

	public Equipas(String nome) {
		this.nome = nome;
	}

	public Equipas(String nome, Date dcria, Date daltera) {
		this.nome = nome;
		this.dcria = dcria;
		this.daltera = daltera;
	}

	@Id

	@Column(name = "nome", unique = true, nullable = false, length = 45)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dcria", length = 10)
	public Date getDcria() {
		return this.dcria;
	}

	public void setDcria(Date dcria) {
		this.dcria = dcria;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "daltera", length = 10)
	public Date getDaltera() {
		return this.daltera;
	}

	public void setDaltera(Date daltera) {
		this.daltera = daltera;
	}

}
