package at.ac.fhcampuswien.newsanalyzer.downloader;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ParallelDownloader extends Downloader {

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Override
    public int process(List<String> urls) {
        long start = System.nanoTime();
        int count = 0;
        for (String url : urls) {
            try {
                Future<?> taskStatus = executorService.submit(() -> {
                    System.out.printf("Starting Thread %s\n", Thread.currentThread().getName());
                    System.out.println("Downloaded: " + url);
                    saveUrl2File(url);
                });
                count++;
            } catch (Exception e) {
                System.out.println("Error");
            }
        }
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException ex) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }

        return count;

    }
}
