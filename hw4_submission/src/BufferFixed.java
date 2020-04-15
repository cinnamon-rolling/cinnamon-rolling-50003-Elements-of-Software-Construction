import java.util.Random;

public class BufferFixed {
	public static void main (String[] args) throws Exception {
		Buffer buffer = new Buffer (3);
		int numProdandCons = 10;
		MyProducer[] prod = new MyProducer[numProdandCons];
//		System.out.println(prod);
//		prod.start();
		MyConsumer[] cons = new MyConsumer[numProdandCons];
//		cons.start();

		for (int i = 0; i < numProdandCons; i++) {
			prod[i] = new MyProducer(buffer);
			prod[i].start();

			cons[i] = new MyConsumer(buffer);
			cons[i].start();
		}

		for (int i = 0; i < numProdandCons; i++) {
			prod[i].join();
			cons[i].join();
		}

	}
}

class MyProducer extends Thread {
	private Buffer buffer;

	public MyProducer (Buffer buffer) {
		this.buffer = buffer;
	}

	public void run () {
		Random random = new Random();
		try {
			Thread.sleep(random.nextInt(1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		buffer.addItem(new Object());
	}
}

class MyConsumer extends Thread {
	private Buffer buffer;

	public MyConsumer (Buffer buffer) {
		this.buffer = buffer;
	}

	public void run () {
		Random random = new Random();
		try {
			Thread.sleep(random.nextInt(1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		buffer.removeItem();
	}
}

class Buffer {
	public int SIZE;
	private Object[] objects;
	private int count = 0;

	public Buffer (int size) {
		SIZE = size;
		objects = new Object[SIZE];
	}

	public void addItem (Object object) {
		synchronized (this) {
			if (count < SIZE) {
				objects[count] = object;
				count++;
				notifyAll();
			} else {
				try{
					wait();
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	}

	public Object removeItem() {
		synchronized (this) {
			if (count > 0) {
				count--;
				notifyAll();
				return objects[count];
			} else {
				try{
					wait();
				} catch (Exception e){
					e.printStackTrace();
				}
				return null;
			}
		}
	}
}