/**
  * Copyright 2018 bejson.com 
  */
package com.gx.po;

/**
 * Auto-generated: 2018-06-11 15:21:28
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class RobotContact {

	private Integer id;
	private String phone;
	private String name;
	private String question;


    
    public RobotContact() {
//		super();
	}
    
    public RobotContact(Integer id,String phone,String name,String question) {
    	super();
    	this.id = id;
    	this.phone = phone;
    	this.name = name;
    	this.question = question;

    	
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

    
    
 
    

}