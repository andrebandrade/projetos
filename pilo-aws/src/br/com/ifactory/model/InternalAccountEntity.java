package br.com.ifactory.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name="containterna")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class InternalAccountEntity {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String externalId;
	private String email;
	private String payerAccountId;
	private String payerAccountName;
	
	public InternalAccountEntity() {}
	
	public InternalAccountEntity(String externalId, String email,
			String payerAccountId, String payerAccountName) {
		setExternalId(externalId);
		setEmail(email);
		setPayerAccountId(payerAccountId);
		setPayerAccountName(payerAccountName);
	}
	
	public InternalAccountEntity(Long id, String externalId, String email,
			String payerAccountId, String payerAccountName) {
		this(externalId, email, payerAccountId, payerAccountName);
		setId(id);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPayerAccountId() {
		return payerAccountId;
	}
	public void setPayerAccountId(String payerAccountId) {
		this.payerAccountId = payerAccountId;
	}
	public String getPayerAccountName() {
		return payerAccountName;
	}
	public void setPayerAccountName(String payerAccountName) {
		this.payerAccountName = payerAccountName;
	}
	
	@Override
	public String toString() {
		return getId() + " - " + getPayerAccountName() + " - " + getEmail() + " - " + getExternalId();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((externalId == null) ? 0 : externalId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((payerAccountId == null) ? 0 : payerAccountId.hashCode());
		result = prime
				* result
				+ ((payerAccountName == null) ? 0 : payerAccountName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InternalAccountEntity other = (InternalAccountEntity) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (externalId == null) {
			if (other.externalId != null)
				return false;
		} else if (!externalId.equals(other.externalId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (payerAccountId == null) {
			if (other.payerAccountId != null)
				return false;
		} else if (!payerAccountId.equals(other.payerAccountId))
			return false;
		if (payerAccountName == null) {
			if (other.payerAccountName != null)
				return false;
		} else if (!payerAccountName.equals(other.payerAccountName))
			return false;
		return true;
	}
	
} // fim da classe
