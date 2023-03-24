package com.streaming.aws.sns.amazonsnsmodule;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishBatchRequest;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AmazonSnsModuleApplication {

    @Autowired
    private AmazonSNSClient snsClient;

    String TOPIC_ARN = "arn:aws:sns:us-east-1:124461032214:coe-streaming-amazon-sns";

    @GetMapping("/addSuscription/{sendTo}")
    public String addSubscription(@PathVariable String sendTo) {
        SubscribeRequest request = new SubscribeRequest(TOPIC_ARN ,"sms", sendTo);
        snsClient.subscribe(request);
        return "A very important message has been sent!";
    }

    @GetMapping("/sendMessage")
    public String publishMessageToTopic() {
        PublishRequest request = new PublishRequest(TOPIC_ARN, buildMessage(), buildSubject());
        snsClient.publish(request);
        return "Hey dude, you have a new notification right now";
    }

    private String buildMessage() {
        return "Te la kreizte we xdxd";
    }

    private String buildSubject() {
        return "An important messsage has been received!";
    }
    public static void main(String[] args) {
        System.out.println("Hello World!");
        SpringApplication.run(AmazonSnsModuleApplication.class, args);
    }

}
