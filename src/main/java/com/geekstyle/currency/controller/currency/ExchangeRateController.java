package com.geekstyle.currency.controller.currency;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.geekstyle.currency.model.common.Response;
import com.geekstyle.currency.model.currency.CountryCurrency;
import com.geekstyle.currency.model.currency.ExchangeRate;
import com.geekstyle.currency.service.common.ResponseService;
import com.geekstyle.currency.service.currency.CountryCurrencyService;
import com.geekstyle.currency.service.currency.ExchangeRateService;
import com.geekstyle.currency.util.StringUtil;

@RestController
@RequestMapping("/exchangerate")
public class ExchangeRateController {
	
	@Autowired
	ExchangeRateService exchangeRateService;
	@Autowired
	CountryCurrencyService countryCurrencyService; 
	
	private Logger log = LoggerFactory.getLogger(ExchangeRateController.class);
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody Response getExchangeRate(@RequestBody ExchangeRate exchangeRate) {
		//TODO
		//user input back-end validation
		
		
		//Exception handling
		BigDecimal rate = null;
		try{
			rate = exchangeRateService.getExchangeRate(exchangeRate);
		}catch(Exception e) {
			e.printStackTrace(); //TODO log will be integrated with ELK
			Response response = new Response();
			response.setCode(ResponseService.SERVER_ERROR);
			response.setData(null);
			return response;
		}
		
		//wrap response message
		Response response = new Response();
		response.setCode(ResponseService.OK);
		response.setData(rate);
		return response;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody Response getAllExchangeRate() {
		//TODO
		//user input back-end validation
		
		
		//Exception handling
		List<ExchangeRate> exchangeRateList = null;
		try{
			exchangeRateList = exchangeRateService.getAllExchangeRate();
		}catch(Exception e) {
			e.printStackTrace(); //TODO log will be integrated with ELK
			Response response = new Response();
			response.setCode(ResponseService.SERVER_ERROR);
			response.setData(null);
			return response;
		}
		
		//wrap response message
		Response response = new Response();
		response.setCode(ResponseService.OK);
		response.setData(exchangeRateList);
		return response;
	}
	
	@RequestMapping(value="/{countryCode}",method=RequestMethod.GET)
	public @ResponseBody Response getDefaultCurrency(@PathVariable("countryCode") String countryCode) {
		//TODO
		//user input back-end validation
		
		
		//Exception handling
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			CountryCurrency countryCurrency = countryCurrencyService.getCountryCurrency(countryCode);
			resultMap.put("countryCode", countryCode);
			resultMap.put("countryName", countryCurrency.getCountryName());
			resultMap.put("defaultCurrency", countryCurrency.getDefaultCurrency());
			if(StringUtil.isNotEmpty(countryCurrency.getDefaultCurrency())) {
				ExchangeRate exchangeRate = new ExchangeRate();
				exchangeRate.setBase("USD");
				exchangeRate.setCurrency(countryCurrency.getDefaultCurrency());
				resultMap.put("rate", exchangeRateService.getExchangeRate(exchangeRate));
			}
		}catch(Exception e) {
			e.printStackTrace(); //TODO log will be integrated with ELK
			Response response = new Response();
			response.setCode(ResponseService.SERVER_ERROR);
			response.setData(null);
			return response;
		}
		
		//wrap response message
		Response response = new Response();
		response.setCode(ResponseService.OK);
		response.setData(resultMap);
		return response;
	}
	
	@RequestMapping(value="/refresh",method=RequestMethod.GET)
	public @ResponseBody Response refreshExchangeRate() {
		System.out.println("into exchange rate refresh");
		Response response = new Response();
		response.setCode(ResponseService.OK);
		try {
			exchangeRateService.refreshExchangeRate();
			return response;
		} catch (Exception e) {
			log.error("refresh exchange rate error", e);
			response.setCode(ResponseService.SERVER_ERROR);
			return response;
		}
	}
	
}
