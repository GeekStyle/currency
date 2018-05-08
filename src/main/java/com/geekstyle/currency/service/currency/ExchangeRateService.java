package com.geekstyle.currency.service.currency;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import com.geekstyle.currency.model.currency.ExchangeRate;

public interface ExchangeRateService {
	
	public static final String EXCHANGE_RATE_URL = "https://openexchangerates.org/api/latest.json?app_id=200a5483dcf64e51a6db83fa556a5ff5";
	
	public BigDecimal getExchangeRate(ExchangeRate exchangeRate);
	
	public List<ExchangeRate> getAllExchangeRate();
	
	@SuppressWarnings("rawtypes")
	public HashMap refreshExchangeRate();
	
}
