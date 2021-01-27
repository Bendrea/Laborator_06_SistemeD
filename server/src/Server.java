
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;


public class Server  {

  public static void main(String args[]) throws Exception {

	String myIP = "192.168.1.100";
	System.setProperty("java.rmi.server.hostname","192.168.1.100");


	//System.setProperty("java.rmi.server.hostname", myIP);
	System.out.println("RMI server "+ myIP +" started on port 3099");

	try { 
		//special exception handler for registry creation
 		LocateRegistry.createRegistry(3099);
 		System.out.println("java RMI registry created.");

	} catch (RemoteException e) {
 		//do nothing, error means registry already exists
 		System.out.println("java RMI registry already exists.");
	}

	// Create an object of the HelloWorldServer class.
	ServerImpl obj = new ServerImpl();

	// Bind this object instance to the name "HelloServer".
	Naming.rebind("rmi://" + myIP + ":3099/CALLBACK", obj);
	System.out.println("CALLBACK bound in registry");

  }

  
} // end class
