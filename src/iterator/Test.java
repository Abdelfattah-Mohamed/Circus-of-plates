package iterator;

import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		Iterator_concrete xx ;
		ArrayList z = new ArrayList();
		z.add(1);
		z.add(2);
		z.add(3);
		z.add(4);
		xx = new Iterator_concrete(z);

	      for(Iterator iter = xx.getIterator(z.size()-1); iter.hasPrev();){
	         int name = (int)iter.previous();
	         //int name1 = (int)iter.previous();
	         System.out.println("Name : " + name);
	         //System.out.println("Name : " + name1);
	      } 	
	   
	      for(Iterator iter = xx.getIterator(0); iter.hasNext();){
        int name = (int)iter.next();
        //int name1 = (int)iter.previous();
        System.out.println("Name : " + name);
        //System.out.println("Name : " + name1);
     } 	
  }
}
