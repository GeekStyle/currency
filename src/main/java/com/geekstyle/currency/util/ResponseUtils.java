package com.geekstyle.currency.util;

import com.geekstyle.currency.model.common.Response;
import com.geekstyle.currency.service.common.ResponseService;

public class ResponseUtils {

	private ResponseUtils(){}
	
	/**
	 * return response with ok code
	 * @return
	 */
	public static Response createOKResponse(){
		Response response = new Response();
		response.setCode(ResponseService.OK);
		return response;
	}
	
	/**
	 *return response with error code 
	 * @return
	 */
	public static Response createErrorResponse(String code){
		Response response = new Response();
		response.setCode(code);
		return response;
	}
	
}
