package org.camunda.bpm.getstarted.checkweather;

import java.util.Random;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("CheckWeatherDelegate")
public class CheckWeatherDelegate implements JavaDelegate {

	public void execute(DelegateExecution execution) throws Exception {
		
		Random rando = new Random();
		
		System.out.println("first service task is executed");
		
		
		execution.setVariable("weatherOk", rando.nextBoolean());

	}

}
