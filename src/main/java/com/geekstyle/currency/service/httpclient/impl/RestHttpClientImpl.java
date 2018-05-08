package com.geekstyle.currency.service.httpclient.impl;

import java.util.HashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.geekstyle.currency.service.httpclient.RestHttpClient;

@Service
public class RestHttpClientImpl implements RestHttpClient{
	
	@Override
	public HashMap postForm(String url,MultiValueMap<String, String> formData) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(formData, headers);
		ResponseEntity<HashMap> response = restTemplate.postForEntity(url, entity, HashMap.class);
		HashMap responseBody = response.getBody();
		return responseBody;
	}
	
	@Override
	public HashMap simpleGetData(String url) {
		RestTemplate restTemplate = new RestTemplate();
		HashMap map = restTemplate.getForObject(url, HashMap.class);
		return map;
	}
	
}
