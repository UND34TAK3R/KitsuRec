package org.example.kitsurecs.db;

import org.h2.tools.Server;

import java.util.Scanner;

public class H2ServerMain {
    public static void main(String[] args) throws Exception {
        Server server = Server.createTcpServer("-tcpAllowOthers", "-ifNotExists").start();
        System.out.println("H2 TCP Server running at: " + server.getURL());
        System.out.println("Press ENTER to stop the server...");

        // Block the thread so server stays running
        new Scanner(System.in).nextLine(); // Wait for user input

        server.stop();
        System.out.println("H2 TCP Server stopped.");
    }
}
