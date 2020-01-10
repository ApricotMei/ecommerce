package com.ecommerce.account.kafka;

import org.springframework.stereotype.Component;

@Component
public class AccountConsumer implements Runnable{

    @Override
    public void run(){
        try{
            while (true) {

            }
        } catch (Exception e) {
            System.out.println("error");
        }
    }

}
