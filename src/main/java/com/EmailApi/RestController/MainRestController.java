package com.EmailApi.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.EmailApi.Model.EmailRequest;
import com.EmailApi.Model.EmailResponse;
import com.EmailApi.service.EmailService;
import com.fasterxml.jackson.annotation.JsonProperty;

@RestController
@CrossOrigin
public class MainRestController {
	
	@Autowired
	private EmailService emailService;
	
	//welcome
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to the mail API";
	}
	
	//Api to send mail
	
	@JsonProperty
	@PostMapping(value = "/sendemail", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> SendEmail(@RequestBody EmailRequest request)
	{
		boolean check = this.emailService.sendEmail(request.getTo(),request.getSubject(),request.getMessage());
		
		if(check) {
			System.out.println(request);
			return ResponseEntity.ok(new EmailResponse("Email Sent Succssfully.."));
		}
		else {
			System.out.println(request);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Email not sent.."));
		}
	}
}
