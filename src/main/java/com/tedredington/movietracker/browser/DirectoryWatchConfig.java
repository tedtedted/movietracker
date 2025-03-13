package com.tedredington.movietracker.browser;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Paths;

@Configuration
public class DirectoryWatchConfig {

    private final String directoryPath = "data/";

    @Bean
    public DirectoryWatcherService directoryWatcherService() throws IOException {
        DirectoryWatcherService service = new DirectoryWatcherService();
        service.watchDirectory(Paths.get(directoryPath));
        return service;
    }

}
