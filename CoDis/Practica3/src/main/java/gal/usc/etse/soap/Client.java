package gal.usc.etse.soap;

import gal.usc.etse.soap.calculator.Calculator;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class Client {
    public static void main(String[] args) throws MalformedURLException {
        URL wsdlURL = new URL("http://localhost:8080/calculator?wsdl");
        QName SERVICE_NAME = new QName("http://calculator.soap.etse.usc.gal/", "Calculator");
        Service service = Service.create(wsdlURL, SERVICE_NAME);
        Calculator client = service.getPort(Calculator.class);
        int result = client.sum(1, 2);
        System.out.println(result);
        result = client.sum(new int[] {1, 2, 3, 4, 5});
        System.out.println(result);
    }
}
