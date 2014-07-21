package questions.iteration;


import java.util.LinkedList;
import java.util.Queue;

public class MyFolder<T, U> implements Folder<T, U>
{
    public U fold2(U u, Queue<T> ts, Function2<T, U, U> function)
    {
        if(u == null || ts == null || function == null)
            throw new IllegalArgumentException();

        if (ts.isEmpty()) {
            return u;
        }

        // The recursive implementation will overflow the stack for
        // any data set of real size, your job is to implement a
        // non-recursive solution
        return fold(function.apply(ts.poll(), u), ts, function);
        //return null;
    }
    
    
    public U fold(U u, Queue<T> ts, Function2<T, U, U> function) {
        if(u == null || ts == null || function == null)
            throw new IllegalArgumentException();

        // if queue is empty return seed value 
        if (ts.isEmpty()) {
            return u;
        }

        // reduce by applying the function on each element in the queue
        for (T element : ts) {
            u = function.apply(element, u);
        }
        
        return u;
    }

	public static void main(String[] args) {
		Folder<Integer,Integer> fold = new MyFolder<Integer, Integer>();
	    Queue<Integer> queue = new LinkedList<Integer>();
	    queue.add(1);
	    queue.add(2);
	    queue.add(3);

	    Integer r = fold.fold(0, queue, new Function2<Integer, Integer, Integer>() {
	        public Integer apply(Integer a, Integer b) {
	            return a + b;
	        }
	    });
	    System.out.println(r);
	}

}
