package Control.iterator;

public interface Container {
	//here we should determine Control.iterator start in case of traversal forward or backward
	public Iterator getIterator(int x);
}
