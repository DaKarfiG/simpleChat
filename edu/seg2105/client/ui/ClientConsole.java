package edu.seg2105.client.ui;
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;
import java.util.Scanner;

import edu.seg2105.client.backend.ChatClient;
import edu.seg2105.client.common.*;

/**
 * This class constructs the UI for a chat client.  It implements the
 * chat interface in order to activate the display() method.
 * Warning: Some of the code here is cloned in ServerConsole 
 *
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Dr Timothy C. Lethbridge  
 * @author Dr Robert Lagani&egrave;re
 */
public class ClientConsole implements ChatIF 
{
  //Class variables *************************************************
  
  /**
   * The default port to connect on.
   */
  final public static int DEFAULT_PORT = 5555;
  
  //Instance variables **********************************************
  
  /**
   * The instance of the client that created this ConsoleChat.
   */
  ChatClient client;
  
  
  
  /**
   * Scanner to read from the console
   */
  Scanner fromConsole; 

  
  //Constructors ****************************************************

  /**
   * Constructs an instance of the ClientConsole UI.
   *
   * @param host The host to connect to.
   * @param port The port to connect on.
   */
  public ClientConsole(String host, int port) 
  {
    try 
    {
      client= new ChatClient(host, port, this);
      
      
    } 
    catch(IOException exception) 
    {
      System.out.println("Error: Can't setup connection!"
                + " Terminating client.");
      System.exit(1);
    }
    
    // Create scanner object to read from console
    fromConsole = new Scanner(System.in); 
  }

  
  //Instance methods ************************************************
  
  /**
   * This method waits for input from the console.  Once it is 
   * received, it sends it to the client's message handler.
   */
  public void accept() {
	    try {
	        String message;
	        while (true) {
	            message = fromConsole.nextLine();
	            if (message.startsWith("#")) {
	            	//call method to handle next input after #
	                handleCommand(message);
	            } else {
	                client.handleMessageFromClientUI(message);
	            }
	        }
	    } catch (Exception ex) {
	        System.out.println("Unexpected error while reading from console");
	        ex.printStackTrace();
	    }
	}
  
//Handle commands starting with '#'
  private void handleCommand(String message) {
      String[] tokens = message.split(" ");
      String command = tokens[0];

      switch (command) {
          case "#quit":
              client.quit();
              break;

          case "#logoff":
              client.logoff();
              break;

          case "#sethost":
              if (tokens.length < 2) {
                  display("Usage: #sethost <host>");
              } else {
                  if (client.isConnected()) {
                      display("Cannot set host while connected. Please log off first.");
                  } else {
                      client.setHost(tokens[1]);
                      display("Host set to " + client.getHost());
                  }
              }
              break;

          case "#setport":
              if (tokens.length < 2) {
                  display("Usage: #setport <port>");
              } else {
                  if (client.isConnected()) {
                      display("Cannot set port while connected. Please log off first.");
                  } else {
                      try {
                          int port = Integer.parseInt(tokens[1]);
                          client.setPort(port);
                          display("Port set to " + client.getPort());
                      } catch (NumberFormatException e) {
                          display("Port must be a number.");
                      }
                  }
              }
              break;

          case "#login":
              client.login();
              break;

          case "#gethost":
              display("Current host: " + client.getHost());
              break;

          case "#getport":
              display("Current port: " + client.getPort());
              break;

          default:
              display("Unknown command: " + command);
              break;
      }
  }

  /**
   * This method overrides the method in the ChatIF interface.  It
   * displays a message onto the screen.
   *
   * @param message The string to be displayed.
   */
  public void display(String message) 
  {
    System.out.println("> " + message);
  }

  
  //Class methods ***************************************************
  
  /**
   * This method is responsible for the creation of the Client UI.
   *
   * @param args[0] The host to connect to.
   */
  /**
   * This method is responsible for the creation of the Client UI.
   *
   * @param args[0] The host to connect to.
   */
  public static void main(String[] args) 
  {
      String host = "localhost"; // Default host
      int port = DEFAULT_PORT;   // Default port

      try
      {
          if (args.length > 0)
          {
        	//First argument is host
              host = args[0]; 

              if (args.length > 1)
              {
            	//Second argument is port
                  port = Integer.parseInt(args[1]); 

                  //Validate port number range
                  if (port < 1 || port > 65535)
                  {
                      System.out.println("Port number must be between 1 and 65535.");
                      System.exit(1);
                  }
              }
          }
      }
      catch (ArrayIndexOutOfBoundsException e)
      {
          //No arguments provided; defaults are already set
      }
      catch (NumberFormatException e)
      {
          System.out.println("Invalid port number. Please enter a valid integer.");
          System.exit(1);
      }

      ClientConsole chat = new ClientConsole(host, port);
      chat.accept();  // Wait for console data
  }
}
//End of ConsoleChat class
