package by.bobruisk.homework.service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import by.bobruisk.homework.model.SMSReport;


@Component
public class SMSServiceImpl {
	private final String URL_SMS_HOSTING = "http://app.sms.by/api/v1/sendQuickSms";
	private final String TOKEN_SMS_HOSTING = "b8575ed304cd4282208d2a2628bd30b9";
	private final String PHONE_NUMBER = "375296715130";
	// private final String ALPHA_NAME_ID = "1141";

	public SMSReport sendSMS(String message) {

		String url = URL_SMS_HOSTING + "?token=" + TOKEN_SMS_HOSTING + "&message=" + message + "&phone=" + PHONE_NUMBER;
		RestTemplate restTemplate = new RestTemplate();
		try {
			return restTemplate.getForObject(url, SMSReport.class);
		} catch (HttpClientErrorException e) {
			throw e;			
		}
	}

}
