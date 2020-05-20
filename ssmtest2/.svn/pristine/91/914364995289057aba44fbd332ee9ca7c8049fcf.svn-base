package com.gx.timetask.util;
import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.gx.web.APIexternalAction;
import com.gx.web.OperationAction; 



@Component
public class MainTask{ 
	
	@Autowired
	public APIexternalAction apiexternalAction;
	@Autowired
	public OperationAction operationAction;
	
	public String ct =  "0 0 4 * * ?";
//	
//@Scheduled(cron = "0 */5 * * * ?") 
 public void run() throws Exception { 
//  System.out.println("推送消息来了"); 
  

	  try {
		  
		  operationAction.setDate();
		  operationAction.setDetailNEW();
		  operationAction.getAjaxJsonTest();
		  operationAction.insertReportResult();
		  apiexternalAction.setTokenCode();
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	 
	 
	  
	  
	  
	  
  
  
  
 } 
  
}