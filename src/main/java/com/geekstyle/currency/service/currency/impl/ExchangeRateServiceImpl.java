package com.geekstyle.currency.service.currency.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekstyle.currency.dao.currency.ExchangeRateDao;
import com.geekstyle.currency.model.currency.ExchangeRate;
import com.geekstyle.currency.service.currency.ExchangeRateService;
import com.geekstyle.currency.service.httpclient.RestHttpClient;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService{

	@Autowired
	private ExchangeRateDao exchangeRateDao;
	
	@Autowired
	RestHttpClient restHttpClient;
	
	@Override
	public BigDecimal getExchangeRate(ExchangeRate exchangeRate) {
		return exchangeRateDao.getExchangeRate(exchangeRate).getRate();
	}
	
	@Override
	public List<ExchangeRate> getAllExchangeRate() {
		return exchangeRateDao.getAllExchangeRate();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashMap refreshExchangeRate() {
		
		HashMap data = restHttpClient.simpleGetData(EXCHANGE_RATE_URL);
		HashMap<String,Object> rates = (HashMap<String,Object>)data.get("rates");
		List<ExchangeRate> exchangeRateList = new ArrayList<ExchangeRate>();
		for(String key : rates.keySet()) {
			ExchangeRate exchangeRate = new ExchangeRate();
			exchangeRate.setBase("USD");
			exchangeRate.setCurrency(key);
			exchangeRate.setRate(BigDecimal.valueOf(Double.parseDouble(rates.get(key).toString())));
			exchangeRate.setUpdateTime(new Date());
			exchangeRateList.add(exchangeRate);
		}
		exchangeRateDao.truncateExchangeRate();
		exchangeRateDao.batchInsertExchangeRate(exchangeRateList);
		return data;
	}
	
}
