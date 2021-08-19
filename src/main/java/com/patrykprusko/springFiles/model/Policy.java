package com.patrykprusko.springFiles.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;


@Entity
@Table(name="policy")
public class Policy {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private Long id;
	@Column
	private String policyNumber;
	@Column
	private String typeOfPolicy;
	@Column
	private String sumPolicy;
	@Column
	private String name;
	@Column
	private String surname;
	@Column
	private String insuredItem;
	@Column
	private String fileType; //dodatkowo pokazuje typ danego pliku wczytanego
	
	@Transient
	private MultipartFile file;
	
	public Policy() {}

	public Policy(String policyNumber, String typeOfPolicy, String sumPolicy, String name, String surname,
			String insuredItem, String fileType) {
		super();
		this.policyNumber = policyNumber;
		this.typeOfPolicy = typeOfPolicy;
		this.sumPolicy = sumPolicy;
		this.name = name;
		this.surname = surname;
		this.insuredItem = insuredItem;
		this.fileType = fileType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getTypeOfPolicy() {
		return typeOfPolicy;
	}

	public void setTypeOfPolicy(String typeOfPolicy) {
		this.typeOfPolicy = typeOfPolicy;
	}

	public String getSumPolicy() {
		return sumPolicy;
	}

	public void setSumPolicy(String sumPolicy) {
		this.sumPolicy = sumPolicy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getInsuredItem() {
		return insuredItem;
	}

	public void setInsuredItem(String insuredItem) {
		this.insuredItem = insuredItem;
	}

	
	
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}


	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	public MultipartFile getFile() {
		return file;
	}
	
	
	
}
