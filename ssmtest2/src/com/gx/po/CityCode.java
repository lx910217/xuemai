package com.gx.po;

public class CityCode {
	
	
	private Integer id;
	private String city_name;
	private String ad_code;
	private String city_code;

	
	public CityCode() {
		
	}	
	
	public CityCode(Integer id,String city_name,String ad_code,String city_code) {
		this.id = id;
		this.city_name = city_name;
		this.ad_code = ad_code;
		this.city_code = city_code;
			
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getAd_code() {
		return ad_code;
	}

	public void setAd_code(String ad_code) {
		this.ad_code = ad_code;
	}

	public String getCity_code() {
		return city_code;
	}

	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	
	
	
	
	
	

}
