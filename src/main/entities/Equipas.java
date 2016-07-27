package main.entities;
// Generated 27/jul/2016 15:03:10 by Hibernate Tools 5.1.0.Alpha1

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

	private int id;
	private String nome;
	private Date dcria;
	private Date daltera;

	public Equipas() {
	}

	public Equipas(int id) {
		this.id = id;
	}

	public Equipas(int id, String nome, Date dcria, Date daltera) {
		this.id = id;
		this.nome = nome;
		this.dcria = dcria;
		this.daltera = daltera;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "nome", length = 45)
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