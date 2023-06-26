package com.example.SriKart.Controller;

import com.example.SriKart.ServiceClasses.SMSserviceClass;
import com.example.SriKart.SriClass.SMSClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SMSController {


    @Autowired
    SMSserviceClass smSserviceClass;
    @PostMapping("/send-sms")
    //http://localhost:8080/send-sms
    public String sendSms(@RequestBody SMSClass smsClass) {
        return smSserviceClass.sendSms(smsClass.getTo(), smsClass.getBody());
    }
}
