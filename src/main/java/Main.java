//import br.sc.senai.domain.Conta;
//import br.sc.senai.domain.Usuario;
import br.sc.senai.domain.Transacao;

public class Main {

	public int soma (int a, int b) {
		return a + b;
	}
	
	public float dividir(int num, int den) {
		return (float) num / den;
	}
	
	public static void main(String[] args) {
		new BuilderMaster().gerarCodigoClasse(Transacao.class);
	}
	
}
