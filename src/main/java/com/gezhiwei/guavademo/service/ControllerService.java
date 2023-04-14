package com.gezhiwei.guavademo.service;

import com.gezhiwei.guavademo.dto.GuavaCallBackResult;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class ControllerService {


    @Autowired
    private CallbackService callbackService;

    @Autowired
    private ListeningExecutorService service;




    @Autowired
    @Qualifier("guavaCallbackThreadPool")
    private ThreadPoolTaskExecutor guavaCallbackPool;


    public void test() throws InterruptedException {

        int i = 30;
        for (int a = 0; a < i; a++) {
            ListenableFuture<GuavaCallBackResult> future =  indexes(a);
            Futures.addCallback(future, callbackService, guavaCallbackPool);
        }

        while (guavaCallbackPool.getActiveCount() > 0) {
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println("still is running " + guavaCallbackPool.getActiveCount());
        }
        System.out.println("all done");


    }

    private ListenableFuture<GuavaCallBackResult> indexes(int i) {
        return service.submit(() -> {
            Random random = new Random(i);
            int i1 = random.nextInt();
            try {
                System.out.println("start to indexes " + i + " random exec " + i1);
                TimeUnit.SECONDS.sleep(i1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return new GuavaCallBackResult(i);
        });
    }


}
