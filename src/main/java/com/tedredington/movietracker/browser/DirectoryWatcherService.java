package com.tedredington.movietracker.browser;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class DirectoryWatcherService {

    private final Logger logger = LoggerFactory.getLogger(DirectoryWatcherService.class);

    private final WatchService watchService;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public DirectoryWatcherService() throws IOException {
        this.watchService = FileSystems.getDefault().newWatchService();
    }

    @PostConstruct
    public void startWatching() {
        executorService.submit(this::processEvents);
    }

    @PreDestroy
    public void stopWatching() {
        try {
            watchService.close();
            executorService.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void watchDirectory(Path path) throws IOException {
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
    }

    private void processEvents() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                WatchKey key = watchService.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                        Path filePath = (Path) event.context();
                        logger.info("File created: {}",filePath);
                    }
                }
                key.reset();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
