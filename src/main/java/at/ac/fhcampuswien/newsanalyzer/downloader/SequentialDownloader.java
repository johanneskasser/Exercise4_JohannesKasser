package at.ac.fhcampuswien.newsanalyzer.downloader;

import java.util.List;

public class SequentialDownloader extends Downloader {

    @Override
    public int process(List<String> urls) {
        int count = 0;
        System.out.println("Starting to download URLs to Files: ");
        for (String url : urls) {
            String fileName = saveUrl2File(url);
            if(fileName != null) {
                count++;
                System.out.println(fileName);
            }
        }
        return count;
    }
}
