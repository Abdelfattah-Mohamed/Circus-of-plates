package iterator;

public interface Container {
	//here we should determine iterator start in case of traversal forward or backward
	public Iterator getIterator(int x);
}
