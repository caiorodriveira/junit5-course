import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {
	
	Main calc = new Main();
	//static pra poder manter o valor em todos os métodos
	private static int contador = 0;
	
	@BeforeEach
	public void input() {
		System.out.println("^^^");
	}
	
	@AfterEach
	public void output() {
		System.out.println("VVV");
	}
	
	//before all executa antes mesmo das instancias dos objetos, deve ser static
	//after all executa depois de tudo, tbm deve ser static
	
	
	@Test
	public void testSomar() {
		System.out.println(++contador);
		Assertions.assertTrue(calc.soma(2, 3) == 5);
		Assertions.assertEquals(5, calc.soma(2, 3));
	}
	
	@Test
	public void testesComAssertions() {
		Assertions.assertNotEquals("casas", "Casas");
		Assertions.assertTrue("casa".equalsIgnoreCase("Casa"));
		
//		Verifica se são a mesma instância
//		Assertions.assertSame
		
//		Assertions.fail("mensagem"); faz o teste falhar
	}
	
	@Test
	public void deveRetornarNumeroInteiroNaDivisao() {
		Main calc = new Main();
		float resultado = calc.dividir(6,2);
		Assertions.assertEquals(3, resultado);
	}
	
	
	@Test
	public void deveRetornarNumeroNegativoNaDivisao() {
		
		float resultado = calc.dividir(-6,2);
		Assertions.assertEquals(-3, resultado);
	}
	
	@Test
	public void deveRetornarNumeroDecimalNaDivisao() {
		
		float resultado = calc.dividir(10,3);
		//margem de erro por causa das casas decimais
		Assertions.assertEquals(3.33, resultado, 0.01);
	}
	
	@Test
	public void deveRetornarZeroComNumeradorZeroNaDivisao() {
		
		float resultado = calc.dividir(0,3);
		Assertions.assertEquals(0, resultado);
	}
	
	@Test
	public void deveLancarExecaoQuandoDividirPorZero_JUnit4(){
		try {
			
			float resultado = 10/0;
			Assertions.fail("Deveria lançar a exeção");
		} catch (ArithmeticException e) {
			Assertions.assertEquals("/ by zero", e.getMessage());
		}
	}
	
	@Test
	public void deveLancarExecaoQuandoDividirPorZero_JUnit5(){
		ArithmeticException ex = Assertions.assertThrows(ArithmeticException.class, () -> {
			float resultado = 10 / 0;
		}); 
		//não obrigatório mas garante
		Assertions.assertEquals("/ by zero", ex.getMessage());
			
	}
	
	
}
