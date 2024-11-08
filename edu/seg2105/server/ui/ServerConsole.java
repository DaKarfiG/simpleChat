package edu.seg2105.server.ui;

import edu.seg2105.client.common.ChatIF;
import edu.seg2105.edu.server.backend.EchoServer;

import java.io.*;
import java.util.Scanner;

public class ServerConsole implements ChatIF {
    // Default port number
    final public static int DEFAULT_PORT = 5555;

    // Instance variables
    private EchoServer server;
    private Scanner fromConsole;

    // Constructor
    public ServerConsole(int port) {
        server = new EchoServer(port, this);
        try {
            server.listen(); // Start listening for connections
        } catch (IOException e) {
            System.out.println("Error: Could not start server.");
        }
        // Create scanner object to read from console
        fromConsole = new Scanner(System.in);
    }

    // Accept input from the console
    public void accept() {
        try {
            String message;
            while (true) {
                message = fromConsole.nextLine();
                if (message.startsWith("#")) {
                    handleCommand(message);
                } else {
                    server.handleMessageFromServerUI(message);
                }
            }
        } catch (Exception ex) {
            System.out.println("Unexpected error while reading from console!");
            ex.printStackTrace();
        }
    }

    // Handle commands starting with '#'
    private void handleCommand(String message) {
        String[] tokens = message.split(" ");
        String command = tokens[0];

        switch (command) {
            case "#quit":
                server.quit();
                break;

            case "#stop":
                server.stopListening();
                display("Server has stopped listening for connections.");
                break;

            case "#close":
                try {
                    server.close();
                    display("Server and all client connections are closed.");
                } catch (IOException e) {
                    display("Error closing server: " + e.getMessage());
                }
                break;

            case "#setport":
                if (server.isListening() || server.getNumberOfClients() > 0) {
                    display("Cannot set port while server is open. Please close the server first.");
                } else {
                    if (tokens.length < 2) {
                        display("Usage: #setport <port>");
                    } else {
                        try {
                            int port = Integer.parseInt(tokens[1]);
                            server.setPort(port);
                            display("Port set to " + port);
                        } catch (NumberFormatException e) {
                            display("Port must be a number.");
                        }
                    }
                }
                break;

            case "#start":
                if (server.isListening()) {
                    display("Server is already started.");
                } else {
                    try {
                        server.listen();
                        display("Server started listening for connections.");
                    } catch (IOException e) {
                        display("Error starting server: " + e.getMessage());
                    }
                }
                break;

            case "#getport":
                display("Current port: " + server.getPort());
                break;

            default:
                display("Unknown command: " + command);
                break;
        }
    }

    // Display method to print messages to the console
    public void display(String message) {
        System.out.println("> " + message);
    }

    // Main method to start the server console
    public static void main(String[] args) {
        int port = DEFAULT_PORT;

        try {
            if (args.length > 0) {
                port = Integer.parseInt(args[0]);
            }
        } catch (NumberFormatException e) {
            System.out.println("Port must be a number. Using default port " + DEFAULT_PORT);
        }

        ServerConsole sc = new ServerConsole(port);
        sc.accept(); // Wait for console input
    }
}