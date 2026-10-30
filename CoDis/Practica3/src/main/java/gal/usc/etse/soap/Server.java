package gal.usc.etse.soap;

import gal.usc.etse.soap.calculator.Calculator;
import gal.usc.etse.soap.calculator.CalculatorImpl;
import javax.xml.ws.Endpoint;

public class Server {
    public static void main(String[] args) {
        Calculator c = new CalculatorImpl();
        String address = "http://localhost:8080/calculator";
        Endpoint.publish(address, c);
    }
}
