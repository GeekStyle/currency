package com.geekstyle.currency.service.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.geekstyle.currency.service.currency.ExchangeRateService;

@Component
public class ExchangeRateSchedule {
	
	@Autowired
	ExchangeRateService exchangeRateService;
	
    @Scheduled(cron="0 0 0 * * ? ")
    public void getExchangeRate() {
    	exchangeRateService.refreshExchangeRate();
    }
	
}
