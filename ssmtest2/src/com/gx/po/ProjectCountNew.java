package com.gx.po;

public class ProjectCountNew {

	private Integer id;
	private String camp_id;
	private String count_num;
	private String count_percent;
	private String token;
	private String set_num;
	private String place;
	
	private String campyname;

	public ProjectCountNew() {

	}
	public ProjectCountNew(Integer id, String camp_id, String count_num, String count_percent, String token,
			String set_num,String place,String campyname) {
		super();
		this.id = id;
		this.camp_id = camp_id;
		this.count_num = count_num;
		this.count_percent = count_percent;

		this.token = token;
		this.set_num = set_num;
		this.place = place;
		this.campyname = campyname;
	}
	
	
	

	
	
	public String getCampyname() {
		return campyname;
	}
	public void setCampyname(String campyname) {
		this.campyname = campyname;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getSet_num() {
		return set_num;
	}

	public void setSet_num(String set_num) {
		this.set_num = set_num;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCamp_id() {
		return camp_id;
	}

	public void setCamp_id(String camp_id) {
		this.camp_id = camp_id;
	}

	public String getCount_num() {
		return count_num;
	}

	public void setCount_num(String count_num) {
		this.count_num = count_num;
	}

	public String getCount_percent() {
		return count_percent;
	}

	public void setCount_percent(String count_percent) {
		this.count_percent = count_percent;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
