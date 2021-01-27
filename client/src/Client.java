import java.rmi.Naming;

import java.rmi.RemoteException;



public class Client {

 static String message = "blank";
 // The intfHelloWorld object "obj" is the identifier that is
 // used to refer to the remote object that implements
 // the intfHelloWorld interface.
 static ServerInterface obj = null;

 public static void main(String args[]) {
     
    double test1 = 3.1415;
    String test2[] = {"alfa", "Beta", "gamma"};    
    Object thing = null;
    int time = 5;

    System.setProperty("java.rmi.server.hostname","192.168.1.100");

 
 try {

 	obj = (ServerInterface) Naming.lookup("//192.168.1.100:3099"
	+ "/CALLBACK");

        
        
        System.out.println("Lookup completed " );

	System.out.println("Message from the RMI-server (no param): \""
	 + obj.sayHello() + "\"");

        System.out.println("Message from the RMI-server (double param): \""
	 + obj.sayDouble(test1) + "\"");
        
	obj.justPing(test2);
        System.out.println("After calling RMI-server (ping Server) ");



        CallbackClientInterface callbackObj = new CallbackClientImpl();
        System.out.println("\ncallbackObj created " );

       
        
        // register for callback
        //System.out.println("\ncallbackObj reference transmitted - " + obj.registerForCallback(callbackObj));

        System.out.println("\nTransmitting callbackObj reference");
        obj.registerForCallback(callbackObj);
        System.out.println("Registered for callback.");
        
        
        try {
            Thread.sleep(time * 100);
        }
            catch (InterruptedException ex){ // sleep over
        }
        
        
        obj.unregisterForCallback(callbackObj);
        System.out.println("Unregistered for callback.");

        
        
 } catch (Exception e) {
	 System.out.println("Exception in CallBackClient: "
	 + e.getMessage());
	 e.printStackTrace();
 }
 
 System.out.println("\nClient is ready!\n");
 System.exit(0);
 }
}


