import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilFixed2 {
	private static int N = 2;

	public static void main (String[] args) throws Exception {
		Philosopher[] phils = new Philosopher[N];
		Fork[] forks = new Fork[N];

		for (int i = 0; i < N; i++) {
			forks[i] = new Fork(i);
		}

		for (int i = 0; i < N; i++) {
			phils[i] = new Philosopher (i, forks[i], forks[(i+N-1)%N]);
			phils[i].start();
		}
	}
}

class Philosopher2 extends Thread {
    private final int index;
    private final Fork left;
    private final Fork right;
    final ReentrantLock reentrantLock = new ReentrantLock();

    public Philosopher2(int index, Fork left, Fork right) {
        this.index = index;
        this.left = left;
        this.right = right;
    }

    public void run() {
        Random randomGenerator = new Random();
//        boolean flag = false;
        try {
            boolean flag = reentrantLock.tryLock(1000, TimeUnit.MILLISECONDS);

            if (flag) {
                try {
                    while (true) {
//                    for (int i = 0; i < 40; i++) {

                        Thread.sleep(randomGenerator.nextInt(100)); //not sleeping but thinking
                        System.out.println("Phil " + index + " finishes thinking.");
                        left.pickup();
                        System.out.println("Phil " + index + " picks up left fork.");
                        right.pickup();
                        System.out.println("Phil " + index + " picks up right fork.");
                        Thread.sleep(randomGenerator.nextInt(100)); //eating
                        System.out.println("Phil " + index + " finishes eating.");
                        left.putdown();
                        System.out.println("Phil " + index + " puts down left fork.");
                        right.putdown();
                        System.out.println("Phil " + index + " puts down right fork.");

                    }
                } catch (InterruptedException e) {
                    System.out.println("Don't disturb me while I am sleeping, well, thinking.");

                } finally {
                    reentrantLock.unlock();
                }
        }
            } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }
    class Fork2 {
        private final int index;
        private boolean isAvailable = true;

        public Fork2(int index) {
            this.index = index;
        }

        public synchronized void pickup() throws InterruptedException {
            while (!isAvailable) {
                wait();
            }

            isAvailable = false;
            notifyAll();
        }

        public synchronized void putdown() throws InterruptedException {
            while (isAvailable) {
                wait();
            }

            isAvailable = true;
            notifyAll();
        }

        public String toString() {
            if (isAvailable) {
                return "Fork " + index + " is available.";
            } else {
                return "Fork " + index + " is NOT available.";
            }
        }
    }
}