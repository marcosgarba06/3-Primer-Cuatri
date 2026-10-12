import java.io.*;
import java.rmi.*;

/**
 * This class represents the object client for a distributed
 * object of class Hello, which implements the remote interface
 * MonteCarloInterface.
 * @author M. L. Liu
 */

public class MonteCarloClient {

   public static void main(String args[]) {
      try {
         int RMIPort;         
         String hostName;
         InputStreamReader is = new InputStreamReader(System.in);
         BufferedReader br = new BufferedReader(is);
         System.out.println("Enter the RMIRegistry host namer:");
         hostName = br.readLine();
         System.out.println("Enter the RMIregistry port number:");
         String portNum = br.readLine();
         RMIPort = Integer.parseInt(portNum);
         String registryURL = 
            "rmi://" + hostName+ ":" + portNum + "/hello";  
         // find the remote object and cast it to an interface object
         MonteCarloInterface h =
           (MonteCarloInterface)Naming.lookup(registryURL);
         System.out.println("Lookup completed " );
         // invoke the remote method
         String message = h.sayHello("Donald Duck");
         System.out.println("MonteCarloClient: " + message);
      } // end try 
      catch (Exception e) {
         System.out.println("Exception in MonteCarloClient: " + e);
      } 
   } //end main
}//end class
