import funcs.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {
    final double eps = 0.0001;

    static Sin sinStub = Mockito.mock(Sin.class);
    static Cos cosStub = Mockito.mock(Cos.class);
    static Cot cotStub = Mockito.mock(Cot.class);
    static Ln lnStub = Mockito.mock(Ln.class);
    static Log log3Stub = Mockito.mock(Log.class);
    static Log log10Stub = Mockito.mock(Log.class);
    static Log log5Stub = Mockito.mock(Log.class);
    static Log log2Stub = Mockito.mock(Log.class);

    @BeforeClass
    public static void setupStubs(){
        log3Stub.setBase(3);
        log5Stub.setBase(5);
        log10Stub.setBase(10);
        log2Stub.setBase(2);
        Mockito.when(sinStub.calc(Mockito.anyDouble())).thenAnswer(i -> Math.sin((Double) i.getArguments()[0]));
        Mockito.when(cosStub.calc(Mockito.anyDouble())).thenAnswer(i -> Math.cos((Double) i.getArguments()[0]));
        Mockito.when(cotStub.calc(Mockito.anyDouble())).thenAnswer(i -> {
            if ((Double) i.getArguments()[0] % Math.PI != 0){
                return Math.cos((Double) i.getArguments()[0])/Math.sin((Double) i.getArguments()[0]);
            }
            else {
                return Double.NaN;
            }
        });
        Mockito.when(lnStub.calc(Mockito.anyDouble())).thenAnswer(i -> Math.log((Double) i.getArguments()[0]));
        Mockito.when(log3Stub.calc(Mockito.anyDouble())).thenAnswer(i -> Math.log((Double) i.getArguments()[0]) / Math.log(3));
        Mockito.when(log5Stub.calc(Mockito.anyDouble())).thenAnswer(i -> Math.log((Double) i.getArguments()[0]) / Math.log(5));
        Mockito.when(log10Stub.calc(Mockito.anyDouble())).thenAnswer(i -> Math.log((Double) i.getArguments()[0]) / Math.log(10));
        Mockito.when(log2Stub.calc(Mockito.anyDouble())).thenAnswer(i -> Math.log((Double) i.getArguments()[0]) / Math.log(2));
    }

    @Test
    public void stubsFuncTestOnlyStubs(){
        FinalFunction finalFunction = new FinalFunction(sinStub, cosStub, cotStub, log3Stub, log5Stub, log10Stub, log2Stub);
        assertEquals(Double.NaN, finalFunction.calc(0), eps);
        assertEquals(0.5822601396937379, finalFunction.calc(-1.0), eps);
        assertEquals(0.09516245519816924, finalFunction.calc(-5), eps);
        assertEquals(0.0, finalFunction.calc(1.0), eps);
        assertEquals(0.21589225429261363, finalFunction.calc(1.5), eps);
        assertEquals(3.401563176021008, finalFunction.calc(5.0), eps);
    }

    @Test
    public void stubsFuncTestWithCot(){
        Cot cot = new Cot(eps, sinStub, cosStub);
        FinalFunction finalFunction = new FinalFunction(sinStub, cosStub, cot, log3Stub, log5Stub, log10Stub, log2Stub);
        assertEquals(Double.NaN, finalFunction.calc(0), eps);
        assertEquals(0.5822601396937379, finalFunction.calc(-1.0), eps);
        assertEquals(0.09516245519816924, finalFunction.calc(-5), eps);
        assertEquals(0.0, finalFunction.calc(1.0), eps);
        assertEquals(0.21589225429261363, finalFunction.calc(1.5), eps);
        assertEquals(3.401563176021008, finalFunction.calc(5.0), eps);
    }

    @Test
    public void stubsFuncTestWithCosCot(){
        Cos cos = new Cos(eps, sinStub);
        Cot cot = new Cot(eps, sinStub);
        FinalFunction finalFunction = new FinalFunction(sinStub, cos, cot, log3Stub, log5Stub, log10Stub, log2Stub);
        assertEquals(Double.NaN, finalFunction.calc(0), eps);
        assertEquals(0.5822601396937379, finalFunction.calc(-1.0), eps);
        assertEquals(0.09516245519816924, finalFunction.calc(-5), eps);
        assertEquals(0.0, finalFunction.calc(1.0), eps);
        assertEquals(0.21589225429261363, finalFunction.calc(1.5), eps);
        assertEquals(3.401563176021008, finalFunction.calc(5.0), eps);
    }

    @Test
    public void stubsFuncTestSinCosCot() {
        Sin sin = new Sin(eps);
        Cos cos = new Cos(eps);
        Cot cot = new Cot(eps);
        FinalFunction finalFunction = new FinalFunction(sin, cos, cot, log3Stub, log5Stub, log10Stub, log2Stub);
        assertEquals(Double.NaN, finalFunction.calc(0), eps);
        assertEquals(0.5822601396937379, finalFunction.calc(-1.0), eps);
        assertEquals(0.09516245519816924, finalFunction.calc(-5), eps);
        assertEquals(0.0, finalFunction.calc(1.0), eps);
        assertEquals(0.21589225429261363, finalFunction.calc(1.5), eps);
        assertEquals(3.401563176021008, finalFunction.calc(5.0), eps);
    }

    @Test
    public void stubsFuncTestSinCosCotLogs(){
        Sin sin = new Sin(eps);
        Cos cos = new Cos(eps);
        Cot cot = new Cot(eps);
        Log log3 = new Log(3,eps, lnStub);
        Log log10 = new Log(10,eps, lnStub);
        Log log5 = new Log(5, eps, lnStub);
        Log log2 = new Log(2, eps, lnStub);
        FinalFunction finalFunction = new FinalFunction(sin, cos, cot, log3, log5, log10, log2);
        assertEquals(Double.NaN, finalFunction.calc(0), eps);
        assertEquals(0.5822601396937379, finalFunction.calc(-1.0), eps);
        assertEquals(0.09516245519816924, finalFunction.calc(-5), eps);
        assertEquals(0.0, finalFunction.calc(1.0), eps);
        assertEquals(0.21589225429261363, finalFunction.calc(1.5), eps);
        assertEquals(3.401563176021008, finalFunction.calc(5.0), eps);
    }

    @Test
    public void finalFuncTest(){
        Sin sin = new Sin(eps);
        Cos cos = new Cos(eps);
        Cot cot = new Cot(eps);
        Ln ln = new Ln(eps);
        Log log3 = new Log(3,eps, ln);
        Log log10 = new Log(10,eps, ln);
        Log log5 = new Log(5, eps, ln);
        Log log2 = new Log(2, eps, ln);
        FinalFunction finalFunction = new FinalFunction(sin, cos, cot, log3, log5, log10, log2);
        assertEquals(Double.NaN, finalFunction.calc(0), eps);
        assertEquals(0.5822601396937379, finalFunction.calc(-1.0), eps);
        assertEquals(0.09516245519816924, finalFunction.calc(-5), eps);
        assertEquals(0.0, finalFunction.calc(1.0), eps);
        assertEquals(0.21589225429261363, finalFunction.calc(1.5), eps);
        assertEquals(3.401361480268469, finalFunction.calc(5.0), eps);
    }
}
