package iterator;

import java.util.ArrayList;

public class Iterator_concrete implements Container {

	private ArrayList al;

	// here we make the given array list equals to the one used here
	public Iterator_concrete(ArrayList x) {
		this.al = x;
	}

	@Override
	public Iterator getIterator(int x) {
		return new NameIterator(x);
	}
	

	private class NameIterator implements Iterator {

		private int index ;
		public NameIterator(int  x) {
			this.index = x;
		}
		

		@Override
		public boolean hasNext() {

			if (index < al.size()) {
				return true;
			}
			return false;
		}

		@Override
		public Object next() {

			if (this.hasNext()) {
				return al.get(index++);
			}
			return null;
		}

		@Override
		public boolean hasPrev() {
			if (index > -1) {
				return true;
			}
			return false;
		}

		@Override
		public Object current() {

			if (this.hasCurrent()) {
				return al.get(index);
			}
			return null;
		}

		@Override
		public Object previous() {

			if (this.hasPrev()) {
				return al.get(index--);
			}
			return null;
		}

		@Override
		public boolean hasCurrent() {
			if (al.size() > 0) {
				return true;
			}
			return false;
		}
	}
}
