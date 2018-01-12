package ds;

public class CircularQueue {
	public static final int MIN_CAPACITY = 25;
	int capacity = MIN_CAPACITY;
	
	int[] arr;
	int head;
	int tail;
	int size;
	public CircularQueue(int capacity) {
		if(capacity > this.capacity) {
			this.capacity = capacity;
		}
		arr = new int[this.capacity];
		this.head = 0;
		this.tail = -1;
		this.size = 0;
	}
	
	public void enqueue(int item) {
		arr[(++tail) % this.capacity] = item;
		size = ++size % this.capacity;
	}
	
	public int dequeue() throws Exception{
		if(size == 0) {
			throw new Exception("Circular queue is empty");
		}
		int item = arr[head--];
		size--;
		return item;
	}
}
