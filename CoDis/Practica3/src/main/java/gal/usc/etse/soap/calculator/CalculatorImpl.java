package gal.usc.etse.soap.calculator;

import javax.jws.WebService;
import java.util.Arrays;

@WebService(
        endpointInterface = "gal.usc.etse.soap.calculator.Calculator",
        serviceName = "Calculator"
)
public class CalculatorImpl implements Calculator {
    @Override
    public int sum(int a, int b) {
        return a+b;
    }

    @Override
    public int sum(int[] nums) {
        return Arrays.stream(nums).sum();
    }
}
