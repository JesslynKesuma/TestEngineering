import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


public class TE1 {
    static Program program;

    @BeforeAll
    static void init(){
        program = new Program();
    }

    @AfterAll
    static void destroy(){
        program = null;
    }

    @ParameterizedTest
    @CsvSource({"0,3250000","10,12500000","22,25000000","40f,50000000","-1,-1500000","-1,999999999999f"})
    public void parameterizedEC(double pajak, double gaji){
        Assertions.assertEquals(pajak, program.getPajak(gaji));
    }

    private static Stream<Arguments> BVA1Parameters(){
        return Stream.of(
            Arguments.of(true, 3999999),
            Arguments.of(true, 4000000),
            Arguments.of(false, 4000001)
        );
    }

    @ParameterizedTest
    @MethodSource("BVA1Parameters")
    public void parameterizedBVA1(boolean expected, double gaji){
        Assertions.assertNotNull(program);
        Assertions.assertEquals(expected, program.getPajak(gaji) == 0);
    }

    private static Stream<Arguments> BVA2Parameters(){
        return Stream.of(
            Arguments.of(true, 14999999),
            Arguments.of(true, 15000000),
            Arguments.of(false, 15000001)
        );
    }

    @ParameterizedTest
    @MethodSource("BVA2Parameters")
    public void parameterizedBVA2(boolean expected, double gaji){
        Assertions.assertNotNull(program);
        Assertions.assertEquals(expected, program.getPajak(gaji) == 10);
    }

    private static Stream<Arguments> BVA3Parameters(){
        return Stream.of(
            Arguments.of(true, 39999999),
            Arguments.of(true, 40000000),
            Arguments.of(false, 40000001)
        );
    }

    @ParameterizedTest
    @MethodSource("BVA3Parameters")
    public void parameterizedBVA3(boolean expected, double gaji){
        Assertions.assertNotNull(program);
        Assertions.assertEquals(expected, program.getPajak(gaji) == 22);
    }
}
