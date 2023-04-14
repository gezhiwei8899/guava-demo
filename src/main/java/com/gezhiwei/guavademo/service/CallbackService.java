package com.gezhiwei.guavademo.service;

import com.gezhiwei.guavademo.dto.GuavaCallBackResult;
import com.google.common.util.concurrent.FutureCallback;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CallbackService implements FutureCallback<GuavaCallBackResult> {
    @Override
    public void onSuccess(GuavaCallBackResult result) {
        Integer id = result.getId();
        try {
            System.out.println("call back " + id);
            TimeUnit.SECONDS.sleep(id);
            System.out.println("call back " + id + " done");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onFailure(Throwable t) {

    }
}
