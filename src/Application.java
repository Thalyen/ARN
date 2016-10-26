import java.util.Scanner;

public class Application {   

	public static void main(String[] args) throws InvalidValueException {      
		Scanner sc = new Scanner(System.in);      
		ARN avl = new ARN();     
		int valor = 0;
		int resp = -1;   
		//int[] valores = {30, 40, 24, 58, 48, 26, 11, 13};// rotação d a esquerda
		//int[] valores = {30, 40, 24, 11, 13};//rotação d a direita
		//int[] valores = {20, 30, 40};// teste raiz esq
		//	int[] valores = {10, 20,30,40,50};// teste raiz dir
		int[] valores = {10,11, 18,15,25,5,20,22};		// teste prof
		//int[] valores = {10,11, 18,15,25,5,26,30};		

		while (resp != 0) {     
			System.out.println("    Escolha a opção:"
					+ "\n1 - Inserir nó:"
					+ "\n2 - Pesquisar um valor:"
					+ "\n3 - Listar valores da árvore:"
					+ "\n4 - Remover nó:"
					+ "\n5 - Quantidade de nós da árvore"
					+ "\n6 - Familia"
					//	+ "\n7 - Sucessor"
					+ "\n0 - Sair");   

			resp = sc.nextInt();   

			switch (resp) {
			case 1:
				/*
				System.out.println("Digite um valor:");
				valor = sc.nextInt();
				avl.insere(valor);
				 */
				for(int  v : valores) {
					avl.insere(v);
					avl.preOrder(avl.getRaiz());
					System.out.println();
				}
				System.out.println();
				break;
			case 2:
				System.out.println("Digite um valor:");
				valor = sc.nextInt();
				try{
					No search = avl.pesquisa(valor);
					System.out.println("Chave " + search.getChave() + " encontrada");
				}catch (InvalidValueException e){
					e.printStackTrace();  
				}
				System.out.println();
				break;
			case 3:
				avl.preOrder(avl.getRaiz());
				System.out.println();
				break;
			case 4:
				System.out.println("Digite um valor:");
				valor = sc.nextInt();
				try {
					avl.remove(valor);
					System.out.println("Valor removido com sucesso");
				}catch (EmptyTreeException e) {
					e.printStackTrace();               
				}catch (InvalidValueException e){
					e.printStackTrace();  
				}
				System.out.println();
				break;
			case 5:
				System.out.println("Total de nos " +avl.size());
				System.out.println();
				break;
			case 6:
				System.out.println("Digite um valor:");
				valor = sc.nextInt();
				No search = avl.busca(valor, avl.getRaiz());
				avl.familia(search);
				break;
			case 7:
				valor = sc.nextInt();
				try{
					search = avl.pesquisa(valor);
					System.out.println("sucessor" + avl.sucessor(search).getChave());
					System.out.println();
				}catch (InvalidValueException e){
					e.printStackTrace();  
				}
				break;
			case 0:
				break;
			default:
				System.out.println("Opção inválida!");
				break;
			}
		}
		sc.close();
	}
}