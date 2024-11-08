// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package edu.seg2105.client.backend;

import ocsf.client.*;

import java.io.*;

import edu.seg2105.client.common.*;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 */
public class ChatClient extends AbstractClient
{
  //Instance variables **********************************************
  
  /**
   * The interface type variable.  It allows the implementation of 
   * the display method in the client.
   */
  ChatIF clientUI; 

  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the chat client.
   *
   * @param host The server to connect to.
   * @param port The port number to connect on.
   * @param clientUI The interface type variable.
   */
  
  public ChatClient(String host, int port, ChatIF clientUI) 
    throws IOException 
  {
    super(host, port); //Call the superclass constructor
    this.clientUI = clientUI;
    /*/
     * create socket
     * create output, outputstream
     * create input, input stream
     * creates client reader to read data from server
     */
    openConnection();
  }

  
  //Instance methods ************************************************
    
  /**
   * This method handles all data that comes in from the server.
   *
   * @param msg The message from the server.
   */
  public void handleMessageFromServer(Object msg) 
  {
    clientUI.display(msg.toString());
    
    
  }
  
  @Override
  protected void connectionClosed() {
	  if(!isConnected()) {
      clientUI.display("Server has shut down. Client is exiting.");
      System.exit(0);
	  }
  }
  
  @Override
  protected void connectionException(Exception exception) {
	  if(!isConnected()) {
          clientUI.display("Connection exception: " + exception.getMessage());
          System.exit(0);
      }
  }

  //print when logoff called
  public void logoff() {
      try {
          closeConnection();
          clientUI.display("You have logged off.");
      } catch (IOException e) {
          clientUI.display("Error logging off: " + e.getMessage());
      }
  }
  
  //print when called on existing connection and when logging in on disconnected client
  public void login() {
      if (isConnected()) {
          clientUI.display("You are already connected.");
      } else {
          try {
              openConnection();
              clientUI.display("You have logged in.");
          } catch (IOException e) {
              clientUI.display("Error logging in: " + e.getMessage());
          }
      }
  }
  /**
   * This method handles all data coming from the UI            
   *
   * @param message The message from the UI.    
   */
  public void handleMessageFromClientUI(String message)
  {
    try
    {
    	//the only way a client should interact with server through a method
      sendToServer(message);
    }
    catch(IOException e)
    {
      clientUI.display
        ("Could not send message to server.  Terminating client.");
      quit();
    }
  }
 
  
  /**
   * This method terminates the client.
   */
  public void quit()
  {
    try
    {
      closeConnection();
    }
    catch(IOException e) {}
    System.exit(0);
  }
}
//End of ChatClient class
