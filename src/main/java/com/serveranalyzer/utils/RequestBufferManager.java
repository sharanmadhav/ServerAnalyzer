package com.serveranalyzer.utils;

import com.serveranalyzer.services.FirebaseService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RequestBufferManager {
    private List<String> bufferA;
    private List<String> bufferB;
    private List<String> currentBuffer;
    private final ScheduledExecutorService scheduler;
    private final FirebaseService firebaseService;

    public RequestBufferManager(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
        this.bufferA = new ArrayList<>();
        this.bufferB = new ArrayList<>();
        this.currentBuffer = bufferA;
        this.scheduler = Executors.newScheduledThreadPool(1);
        startScheduler();
    }

    private void startScheduler() {
        scheduler.scheduleAtFixedRate(this::swapAndSendBuffer, 1, 1, TimeUnit.MINUTES);
    }

    public synchronized void addRequest(String requestData) {
        currentBuffer.add(requestData);
    }

    private synchronized void swapAndSendBuffer() {
        List<String> bufferToSend;
        
        // Swap buffers
        if (currentBuffer == bufferA) {
            currentBuffer = bufferB;
            bufferToSend = bufferA;
            bufferA = new ArrayList<>();
        } else {
            currentBuffer = bufferA;
            bufferToSend = bufferB;
            bufferB = new ArrayList<>();
        }
        if (!bufferToSend.isEmpty()) {
            firebaseService.sendData(bufferToSend);
        }
    }

    public void shutdown() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
            swapAndSendBuffer();
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
} 