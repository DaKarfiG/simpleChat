package edu.seg2105.edu.server.backend;
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 


import java.io.IOException;

import ocsf.server.*;

/**
 * This class overrides some of the methods in the abstract 
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 */
public class EchoServer extends AbstractServer 
{
  //Class variables *************************************************
  
  /**
   * The default port to listen on.
   */
  final public static int DEFAULT_PORT = 5555;
  

  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the echo server.
   *
   * @param port The port number to connect on.
   */
  public EchoServer(int port) 
  {
    super(port);
  }

  
  //Instance methods ************************************************
  
  /**
   * This method handles any messages received from the client.
   *
   * @param msg The message received from the client.
   * @param client The connection from which the message originated.
   */
  
  @Override
  protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
      String clientIp = "Unknown";

      // Retrieve the client's IP address using synchronized getInfo
      Object ipObject = client.getInfo("ip");
      if (ipObject != null) {
          clientIp = (String) ipObject;
      }

      System.out.println("Message received from " + clientIp + ": " + msg);

      try {
          client.sendToClient(msg);
      } catch (IOException e) {
          System.out.println("Error sending message to client: " + e.getMessage());
      }
  }
    
  /**
   * This method overrides the one in the superclass.  Called
   * when the server starts listening for connections.
   */
  protected void serverStarted()
  {
    System.out.println
      ("Server listening for connections on port " + getPort());
  }
  
  /**
   * This method overrides the one in the superclass.  Called
   * when the server stops listening for connections.
   */
  protected void serverStopped()
  {
    System.out.println
      ("Server has stopped listening for connections.");
  }
  /**
   * This method is called each time a new client connection is accepted.
   *
   * @param client The connection connected to the client.
   */
  @Override
  protected void clientConnected(ConnectionToClient client) {
      String clientIp = "Unknown";
      if (client != null && client.getInetAddress() != null) {
          clientIp = client.getInetAddress().getHostAddress();
      }

      // Store the client's IP address using synchronized setInfo
      client.setInfo("ip", clientIp);

      System.out.println("A client has connected from IP: " + clientIp);
  }

  /**
   * This method is called each time a client disconnects.
   *
   * @param client The connection to the client that disconnected.
   */
  @Override
  synchronized protected void clientDisconnected(ConnectionToClient client) {
      String clientIp = "Unknown";

      // Retrieve the client's IP address using synchronized getInfo
      Object ipObject = client.getInfo("ip");
      if (ipObject != null) {
          clientIp = (String) ipObject;
      }

      System.out.println("A client has disconnected from IP: " + clientIp);
  }

  /**
   * This method is called when an exception is thrown in a client's thread.
   *
   * @param client    The client that caused the exception.
   * @param exception The exception thrown.
   */
  @Override
  synchronized protected void clientException(ConnectionToClient client, Throwable exception) {
      String clientIp = "Unknown";

      // Retrieve the client's IP address using synchronized getInfo
      Object ipObject = client.getInfo("ip");
      if (ipObject != null) {
          clientIp = (String) ipObject;
      }

      if (exception instanceof IOException) {
          System.out.println("A client has disconnected unexpectedly from IP: " + clientIp);
      } else {
          System.out.println("An exception occurred with client at IP: " + clientIp + " - " + exception.getMessage());
      }
  }


  
  
  //Class methods ***************************************************
  
  /**
   * This method is responsible for the creation of the server instance.
   *
   * @param args[0] The port number to listen on. Defaults to 5555 if no argument is supplied.
   */
  public static void main(String[] args) {
      int port = DEFAULT_PORT; // Port to listen on

      try {
          if (args.length > 0) {
              port = Integer.parseInt(args[0]); // Get port from command line
          }
      } catch (NumberFormatException e) {
          System.out.println("Invalid port number. Using default port " + DEFAULT_PORT);
      }

      EchoServer server = new EchoServer(port);

      try {
          server.listen(); // Start listening for connections
          System.out.println("Server listening on port " + port);
      } catch (Exception ex) {
          System.out.println("Error: Could not start server");
      }
  }
}
//End of EchoServer class
