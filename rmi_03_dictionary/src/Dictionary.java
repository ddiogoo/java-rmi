import java.util.List;

public interface Dictionary extends java.rmi.Remote {
	public void add(String palavra, String significado) throws java.rmi.RemoteException;

	public String get(String palavra) throws java.rmi.RemoteException;

	public void delete(String palavra) throws java.rmi.RemoteException;

	public List<String[]> list() throws java.rmi.RemoteException;

	public void writeFile() throws java.rmi.RemoteException;

	public void readFile() throws java.rmi.RemoteException;
}
