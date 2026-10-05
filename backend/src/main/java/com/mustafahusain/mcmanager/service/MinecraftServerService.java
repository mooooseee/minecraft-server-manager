package com.mustafahusain.mcmanager.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class MinecraftServerService {

    private static final String CONTAINER_NAME = "minecraft";

    public boolean isRunning() {
        try {
            Process process = new ProcessBuilder(
                    "docker",
                    "inspect",
                    "-f",
                    "{{.State.Running}}",
                    CONTAINER_NAME
            ).start();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            String output = reader.readLine();
            int exitCode = process.waitFor();

            return exitCode == 0 && "true".equalsIgnoreCase(output);

        } catch (Exception e) {
            return false;
        }
    }

    public boolean startServer() {
        try {
            Process process = new ProcessBuilder(
                    "docker",
		    "start",
		    CONTAINER_NAME
	    ).start();

	   int exitCode = process.waitFor();

	   return exitCode == 0;

	} catch (Exception e) {
	   return false;
	}
    }

    public boolean stopServer() {        
        try {
            Process process = new ProcessBuilder(
                    "docker",
                    "stop",
                    CONTAINER_NAME
            ).start();

           int exitCode = process.waitFor();

           return exitCode == 0;

        } catch (Exception e) {
           return false;
        }
    }
}
