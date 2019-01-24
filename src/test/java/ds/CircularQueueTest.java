package ds;

import org.junit.Test;

import junit.framework.Assert;

public class CircularQueueTest {

	@Test
	public void emptyQueueTests(){
		CircularQueue q = new CircularQueue(20);
		try{
			q.dequeue();
			Assert.assertTrue(false); //should not reach here
		}catch(Exception e) {
			
		}
		
		q.enqueue(10);
		Assert.assertEquals(1, q.size());
		int item = q.dequeue();
		Assert.assertEquals(10, item);
		Assert.assertEquals(0, q.size());
	}
}
