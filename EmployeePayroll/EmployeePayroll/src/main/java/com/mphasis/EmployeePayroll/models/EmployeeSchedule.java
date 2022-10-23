package com.mphasis.EmployeePayroll.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/******************************************EMPLOYEE SCHEDULE***********************************/
@Entity
public class EmployeeSchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	private Long schdId;
	@Column
	private Date fromdate;
	@Column
	private Date todate;

	@Column
	private String timeline;
	
/*****************************************GETTERS AND SETTERS***********************************/
	public Long getSchdId() {
		return schdId;
	}
	public void setSchdId(Long schdId) {
		this.schdId = schdId;
	}
	public Date getFromdate() {
		return fromdate;
	}
	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}
	public Date getTodate() {
		return todate;
	}
	public void setTodate(Date todate) {
		this.todate = todate;
	}
	public String getTimeline() {
		return timeline;
	}
	public void setTimeline(String timeline) {
		this.timeline = timeline;
	}
/*************************************CONSTRUCTORS******************************************/
	public EmployeeSchedule(Date fromdate, Date todate, String timeline) {
		super();
		this.fromdate = fromdate;
		this.todate = todate;
		this.timeline = timeline;
	}
	public EmployeeSchedule() {
		super();
	}
	
}
