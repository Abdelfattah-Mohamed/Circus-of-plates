package iterator;

public interface Iterator {
	public boolean hasNext();
	public boolean hasPrev();
	public boolean hasCurrent();
	public Object next();
	public Object current();
	public Object previous();
}
