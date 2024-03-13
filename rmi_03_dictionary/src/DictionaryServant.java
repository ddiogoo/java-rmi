import java.util.LinkedList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.rmi.server.UnicastRemoteObject;

public class DictionaryServant extends UnicastRemoteObject implements Dictionary {
	private static final long serialVersionUID = 1L;
	private HashMap<String, String> dicionario = new HashMap<String, String>();

	public DictionaryServant() throws java.rmi.RemoteException {
		super();
	}

	public void add(String palavra, String significado) throws java.rmi.RemoteException {
		dicionario.put(palavra, significado);
	}

	public String get(String palavra) throws java.rmi.RemoteException {
		return dicionario.get(palavra);
	}

	public void delete(String palavra) throws java.rmi.RemoteException {
		dicionario.remove(palavra);
	}
	
	public List<String[]> list() throws java.rmi.RemoteException {
		List<String[]> lista = new LinkedList<>();
		for (String palavra : dicionario.keySet()) {
			lista.add(new String[] { palavra, dicionario.get(palavra) });
		}
		return lista;
	}

	public void escreverArquivo() throws java.rmi.RemoteException {
		try{
			FileWriter fw = new FileWriter("dicionario.txt");
			PrintWriter pw = new PrintWriter(fw);
			for (String palavra : dicionario.keySet()) {
				pw.println(palavra + " - " + dicionario.get(palavra));
			}
			pw.close();
			fw.close();
		} catch (IOException e) {
			System.out.println("Erro ao escrever arquivo.");
		}
	}

	public void lerArquivo() throws java.rmi.RemoteException {
		try {
			java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader("dicionario.txt"));
			String linha;
			while ((linha = br.readLine()) != null) {
				String[] partes = linha.split(" - ");
				dicionario.put(partes[0], partes[1]);
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Erro ao ler arquivo.");
		}
	}
}
