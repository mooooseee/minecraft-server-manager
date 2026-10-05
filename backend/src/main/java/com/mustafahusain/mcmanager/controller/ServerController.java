package com.mustafahusain.mcmanager.controller;

import com.mustafahusain.mcmanager.service.MinecraftServerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@RestController
@RequestMapping("/api/server")
public class ServerController {

    private final MinecraftServerService minecraftServerService;

    public ServerController(MinecraftServerService minecraftServerService) {
        this.minecraftServerService = minecraftServerService;
    }

    @GetMapping("/status")
    public Map<String, String> getServerStatus() {

        boolean running = minecraftServerService.isRunning();

        return Map.of(
                "name", "Minecraft Server",
                "status", running ? "online" : "offline"
        );
    }

    @PostMapping("/start")
    public Map<String, String> startServer() {
	boolean success = minecraftServerService.startServer();

	if (success) {
	   return Map.of(
		   "status", "started"
	   );
	} else {
	   return Map.of(
		   "status", "failed"
	   );
	}
    }

    @PostMapping("/stop")
    public Map<String, String> stopServer() {
        boolean success = minecraftServerService.stopServer();

        if (success) {
           return Map.of(
                   "status", "stopped"
           );
        } else {
           return Map.of(
                   "status", "failed"
           );
        }
    }
}
