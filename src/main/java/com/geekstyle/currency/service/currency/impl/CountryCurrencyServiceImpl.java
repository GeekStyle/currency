package com.geekstyle.currency.service.currency.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekstyle.currency.dao.currency.CountryCurrencyDao;
import com.geekstyle.currency.model.currency.CountryCurrency;
import com.geekstyle.currency.service.currency.CountryCurrencyService;

@Service
public class CountryCurrencyServiceImpl implements CountryCurrencyService{
	
	@Autowired
	CountryCurrencyDao countryCurrencyDao; 
	
	@Override
	public CountryCurrency getCountryCurrency(String countryCode) {
		return countryCurrencyDao.findByCountryCode(countryCode);
	}
	
}
