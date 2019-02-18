package com.kh.spring.demo.model.vo;

import java.io.Serializable;

public class Dev implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2049549352615947866L;
	private int devNo;
	private String devName;
	private int devAge;
	private String devEmail;
	private String devGender;
	private String[] devLang;
	
	public Dev() {
		// TODO Auto-generated constructor stub
	}

	public Dev(String devName, int devAge, String devEmail, String devGender, String[] devLang) {
		super();
		this.devName = devName;
		this.devAge = devAge;
		this.devEmail = devEmail;
		this.devGender = devGender;
		this.devLang = devLang;
	}

	public Dev(int devNo, String devName, int devAge, String devEmail, String devGender, String[] devLang) {
		super();
		this.devNo = devNo;
		this.devName = devName;
		this.devAge = devAge;
		this.devEmail = devEmail;
		this.devGender = devGender;
		this.devLang = devLang;
	}



	public int getDevNo() {
		return devNo;
	}

	public void setDevNo(int devNo) {
		this.devNo = devNo;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public int getDevAge() {
		return devAge;
	}

	public void setDevAge(int devAge) {
		this.devAge = devAge;
	}

	public String getDevEmail() {
		return devEmail;
	}

	public void setDevEmail(String devEmail) {
		this.devEmail = devEmail;
	}

	public String getDevGender() {
		return devGender;
	}

	public void setDevGender(String devGender) {
		this.devGender = devGender;
	}

	public String[] getDevLang() {
		return devLang;
	}

	public void setDevLang(String[] devLang) {
		this.devLang = devLang;
	}
	
	
	
	
}
