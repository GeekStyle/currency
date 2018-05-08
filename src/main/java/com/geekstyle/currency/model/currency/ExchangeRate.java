package com.geekstyle.currency.model.currency;

import java.math.BigDecimal;
import java.util.Date;

public class ExchangeRate {
	
	private Long id;
	private String base;
	private String currency;
	private BigDecimal rate;
	private Date updateTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public java.math.BigDecimal getRate() {
		return rate;
	}
	public void setRate(java.math.BigDecimal rate) {
		this.rate = rate;
	}
	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
