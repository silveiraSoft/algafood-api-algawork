package projeto01;
/**
 * 
 * @author Adalberto Silveira
 *
 */
public class TesteInicial {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello");
		Integer i = 10;
		imprimir(i);
		System.out.println("Valor de i:"+i);
	}
	public static void imprimir(Integer p1) {
		p1 +=1;
		System.out.println("Integer impreso:"+p1);
	}
}
