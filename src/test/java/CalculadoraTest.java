import org.junit.jupiter.api.*;

public class CalculadoraTest {

    @BeforeEach
    public void setup() {

    }

    @AfterEach
    public void tearDown() {

    }

    @BeforeAll
    public static void setupAll() {
        _calculadora = new Calculadora();
    }

    @AfterAll
    public static void tearDownAll() {
        _calculadora = null;
    }

    @Test
    public void testSoma() {
        Assertions.assertEquals(5, _calculadora.soma(2,3));
    }

    @Test
    public void deveRetornarNumeroInteiroNaDivisao() {
        float divisao = _calculadora.divisao(2,1);

        Assertions.assertEquals(2, divisao);
    }

    @Test
    public void deveRetornarNumeroNegativoNaDivisao() {
        float divisao = _calculadora.divisao(-6, 2);

        Assertions.assertEquals(-3, divisao);
    }

    @Test
    public void deveRetornarNumeroDecimalNaDivisao() {
        float divisao = _calculadora.divisao(10, 3);

        Assertions.assertEquals(3.33, divisao, 0.01);
    }

    @Test
    public void deveRetornarZeroComNumeradorZeroNaDivisao() {
        float divisao = _calculadora.divisao(0, 2);

        Assertions.assertEquals(0, divisao);
    }

    @Test
    public void deveLancarExcecaoQuandoDividirPorZero_JUnit4() {
        try {
            float divisao = 10 / 0;
            Assertions.fail();
        } catch (ArithmeticException arithmeticException) {
            Assertions.assertEquals("/ by zero", arithmeticException.getMessage());
        }
    }

    @Test
    public void deveLancarExcecaoQuandoDividirPorZero_JUnit5() {
        ArithmeticException arithmeticException = Assertions.assertThrows(
                ArithmeticException.class, () -> {
                    float divisao = 10 / 0;
                });
        Assertions.assertEquals("/ by zero", arithmeticException.getMessage());
    }

    private static Calculadora _calculadora;
}
