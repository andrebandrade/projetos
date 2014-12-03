package br.com.jm.dbunit.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cartao implements Serializable {
	private static final long serialVersionUID = 1747479611641575838L;
	
	@Id
	private String numero;
	private String nome;
	private float saldo;
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	
	public void carregar(float valor) {
		saldo += valor;
	}
	
	public void comprar(float valor) {
		saldo -= valor;
	}
	
}
