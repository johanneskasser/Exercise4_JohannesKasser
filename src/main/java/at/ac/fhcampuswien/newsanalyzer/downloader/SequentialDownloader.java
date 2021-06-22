package at.ac.fhcampuswien.newsanalyzer.downloader;

import java.util.List;
import java.util.Timer;

public class SequentialDownloader extends Downloader {

    @Override
    public int process(List<String> urls, List<String> titles) {
        long start = System.nanoTime();
        int count = 0;
        System.out.println("Starting to download URLs to Files: ");
        for (String url : urls) {
            String fileName = saveUrl2File(url, titles.get(count));
            if(fileName != null) {
                count++;
                System.out.println(fileName);
            }
        }
        long end = System.nanoTime();
        System.out.println("Time elapsed: " + (end - start)*0.000000001 + "secs");
        return count;
    }
}
