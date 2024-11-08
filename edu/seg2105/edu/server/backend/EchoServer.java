package edu.seg2105.edu.server.backend;
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 


import java.io.IOException;
import edu.seg2105.client.common.ChatIF;
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
  
  private ChatIF serverUI;

  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the echo server.
   *
   * @param port The port number to connect on.
   */
  public EchoServer(int port, ChatIF serverUI) 
  {
    super(port);
    this.serverUI = serverUI;
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
      if (msg instanceof String) {
          String message = (String) msg;

          // Check if the client has a login ID
          String loginID = (String) client.getInfo("loginID");

          // If the client has not logged in yet
          if (loginID == null) {
              if (message.startsWith("#login ")) {
                  // Extract login ID
                  loginID = message.substring(7).trim();

                  if (loginID.isEmpty()) {
                      try {
                          client.sendToClient("ERROR - Login ID cannot be empty. Connection closing.");
                          client.close();
                      } catch (IOException e) {
                          // Ignore exception on close
                      }
                  } else {
                      // Save the login ID
                      client.setInfo("loginID", loginID);
                      
                      //send confirmation to client
                      try {
                          client.sendToClient(loginID + " has logged on.");
                      } catch (IOException e) {
                          // Handle exception
                      }

                      // Display login message on server console
                      serverUI.display("Message received: #login " + loginID + " from null.");
                      serverUI.display(loginID + " has logged on.");
                  }
                  
              
              } else {
                  // First message is not #login, send error and close connection
                  try {
                      client.sendToClient("ERROR - You must login first. Connection closing.");
                      client.close();
                  } catch (IOException e) {
                      // Ignore exception on close
                  }
              }
          } else {
              
              serverUI.display("Message received: " + message + " from " + loginID);

              // Echo the message to all clients, prefixed with the login ID
              String fullMessage = loginID + "> " + message;
              sendToAllClients(fullMessage);
          }
      }
  }
  
  
  // Handle messages from the server UI
  public void handleMessageFromServerUI(String message) {
      String fullMessage = "SERVER MESSAGE> " + message;
      serverUI.display(fullMessage);
      sendToAllClients(fullMessage);
  }
    
  /**
   * This method overrides the one in the superclass.  Called
   * when the server starts listening for connections.
   */
  protected void serverStarted()
  {
	  serverUI.display("Server listening for clients on port " + getPort());
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
  
  @Override
  protected void serverClosed() {
      serverUI.display("Server closed.");
  }

  /**
   * This method is called each time a new client connection is accepted.
   *
   * @param client The connection connected to the client.
   */
  @Override
  synchronized protected void clientConnected(ConnectionToClient client) {
      
      serverUI.display("A new client has connected to the server.");
  }
  /**
   * This method is called each time a client disconnects.
   *
   * @param client The connection to the client that disconnected.
   */
  @Override
  synchronized protected void clientDisconnected(ConnectionToClient client) {
      String loginID = (String) client.getInfo("loginID");
      if (loginID == null) {
          loginID = "Unknown";
      }
      serverUI.display(loginID + " has disconnected.");
  }

  /**
   * This method is called when an exception is thrown in a client's thread.
   *
   * @param client    The client that caused the exception.
   * @param exception The exception thrown.
   */
  @Override
  synchronized protected void clientException(ConnectionToClient client, Throwable exception) {
      String loginID = (String) client.getInfo("loginID");
      if (loginID == null) {
          loginID = "Unknown";
      }

      if (exception instanceof IOException) {
          serverUI.display(loginID + " has disconnected unexpectedly.");
      } else {
          serverUI.display("An exception occurred with client " + loginID + ": " + exception.getMessage());
      }
  }



  
  
  //Class methods ***************************************************
  
  /**
   * This method is responsible for the creation of the server instance.
   *
   * @param args[0] The port number to listen on. Defaults to 5555 if no argument is supplied.
   */
  /*public static void main(String[] args) {
      int port = DEFAULT_PORT; // Port to listen on

      try {
          if (args.length > 0) {
              port = Integer.parseInt(args[0]); // Get port from command line
          }
      } catch (NumberFormatException e) {
          System.out.println("Invalid port number. Using default port " + DEFAULT_PORT);
      }

      ChatIF serverUI = new ChatIF() {
          @Override
          public void display(String message) {
              System.out.println(message);
          }
      };
      
      EchoServer server = new EchoServer(port, serverUI);

      try {
          server.listen(); // Start listening for connections
          System.out.println("Server listening on port " + port);
      } catch (Exception ex) {
          System.out.println("Error: Could not start server");
      }
  }
  */
//Quit the server
  public void quit() {
      try {
          close();
      } catch (IOException e) {
          // Ignore exceptions on close
      }
      System.exit(0);
  }
}
//End of EchoServer class
