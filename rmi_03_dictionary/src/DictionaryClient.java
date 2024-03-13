
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.util.Scanner;

public class DictionaryClient {
	public static void menu(Dictionary c) {
		Scanner s = new Scanner(System.in);
		int opcao = 0;
		do {
			System.out.println("1 - Adicionar palavra");
			System.out.println("2 - Consultar palavra");
			System.out.println("3 - Remover palavra");
			System.out.println("4 - Listar palavras");
			System.out.println("5 - Escrever arquivo");
			System.out.println("6 - Ler arquivo");
			System.out.println("7 - Sair");
			System.out.print("Opção: ");
			opcao = s.nextInt();
			s.nextLine();
			try {
				switch (opcao) {
					case 1:
						System.out.print("Palavra: ");
						String palavra = s.nextLine();
						System.out.print("Significado: ");
						String significado = s.nextLine();
						c.add(palavra, significado); // Adiciona a palavra e seu significado no dicionário
						System.out.println("Palavra adicionada.");
						break;
					case 2:
						System.out.print("Palavra: ");
						palavra = s.nextLine();
						significado = c.get(palavra); // Busca o significado da palavra no dicionário
						if (significado != null) {
							System.out.println("Significado: " + significado);
						} else {
							System.out.println("Palavra não encontrada.");
						}
						break;
					case 3:
						System.out.print("Palavra: ");
						palavra = s.nextLine();
						c.delete(palavra); // Remove a palavra do dicionário
						System.out.println("Palavra removida.");
						break;
					case 4:
						for (String[] p : c.list()) { // Lista todas as palavras e seus significados
							System.out.println(p[0] + " - " + p[1]);
						}
						break;
					case 5:
						c.writeFile(); // Escreve o dicionário em um arquivo
						break;
					case 6:
						c.readFile(); // Lê o dicionário de um arquivo
						break;
					case 7:
						System.out.println("Saindo...");
						break;
					default:
						System.out.println("Opção inválida.");
				}
			} catch (RemoteException e) {
				System.out.println("Erro na invocação remota.");
				e.printStackTrace();
			}
		} while (opcao != 7);
		s.close();
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		try {
			System.out.println("Digite o endereço do servidor:");
			String servidor = s.nextLine();
			Dictionary c = (Dictionary) Naming.lookup("rmi://" + servidor + "/DicionarioService");
			menu(c);
		} catch (MalformedURLException e) {
			System.out.println("URL mal formatada.");
		} catch (RemoteException e) {
			System.out.println("Erro na invocação remota.");
			e.printStackTrace();
		} catch (NotBoundException e) {
			System.out.println("Objeto remoto não está disponível.");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			s.close();
		}
	}
}
