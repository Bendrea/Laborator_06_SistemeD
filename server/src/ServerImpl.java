/*
  update: 15.ian.2017
*/

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

import java.util.Vector;


public class ServerImpl extends UnicastRemoteObject
     implements ServerInterface {

   private Vector clientList;

   public ServerImpl() throws RemoteException {
      super();
      clientList = new Vector();
   }


  public String sayHello( )
    throws java.rmi.RemoteException {
	try{
    		System.out.println("\tclient " + getClientHost() + " call sayHello()"); 
	}catch(Exception e){}
	return("Hello from server!");
  }


  public String sayDouble(double param)
    throws java.rmi.RemoteException {
	System.out.println("\tclient calling sayDouble("+param+")");
	return ("Ok - double param");
  }


  public void justPing(String[] params)
    throws java.rmi.RemoteException {
	System.out.println("\tclient calling justPing(String[])");
	System.out.println("\tparameters: "+params[0]+","+params[1]+","+params[2]);
  }



  public synchronized void registerForCallback(
    CallbackClientInterface callbackClientObject)
    throws java.rmi.RemoteException {
      System.out.println("\nmessage registerForCallback(CallbackClientInterface)\n");

      // store the callback object into the vector
      if (!(clientList.contains(callbackClientObject))) {
         	clientList.addElement(callbackClientObject);
      		System.out.println("Registered new client ");

      		doCallbacks();
      } // end if
  }




// This remote method allows an object client to 
// cancel its registration for callback
// @param id is an ID for the client; to be used by
// the server to uniquely identify the registered client.
  public synchronized void unregisterForCallback(
    CallbackClientInterface callbackClientObject) 
    throws java.rmi.RemoteException {

    if (clientList.removeElement(callbackClientObject)) {
      System.out.println("Unregistered client ");
    } else {
       System.out.println(
         "unregister: client wasn't registered.");
    }

  }





 private synchronized void doCallbacks() throws java.rmi.RemoteException
 {
    // make callback to each registered client
    System.out.println("**************************************\n"
        + "Callbacks initiated ---");

    for (int i = 0; i < clientList.size(); i++){
      System.out.println("doing "+ i +"-th callback\n");
		// convert the vector object to a callback object
      		CallbackClientInterface nextClient = 
        		(CallbackClientInterface)clientList.elementAt(i);


		// invoke the callback method
        	nextClient.notifyMe("Number of registered clients="
	           +  clientList.size());

    }// end for

    System.out.println("********************************\n" +
                       "Server completed callbacks ---");
  } // doCallbacks




}// end ServerImpl class   
