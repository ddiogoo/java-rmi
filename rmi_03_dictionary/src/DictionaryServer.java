import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class DictionaryServer {

	public DictionaryServer() {
		try {
			Dictionary c = new DictionaryServant();
			LocateRegistry.createRegistry(1099);
			Naming.rebind("DicionarioService", c);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String args[]) {
		new DictionaryServer();
		System.out.println("Servidor Dicionario em execução.");
	}
}
