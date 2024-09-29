import br.sc.senai.domain.Usuario;

public class Main {

	public int soma (int a, int b) {
		return a + b;
	}
	
	public float dividir(int num, int den) {
		return (float) num / den;
	}
	
	public static void main(String[] args) {
		new BuilderMaster().gerarCodigoClasse(Usuario.class);
	}
	
}
