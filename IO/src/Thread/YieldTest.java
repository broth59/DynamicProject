package Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class FIFO_Thread extends Thread{

	private int no;
	
	public FIFO_Thread(int no) {
		this.no = no;
	}

	@Override
	public void run() {
		System.out.println(no);
	}
	
}


public class YieldTest {

	public static void main(String[] args) {

		
		Thread t1 = new FIFO_Thread(1);
		Thread t2 = new FIFO_Thread(2);
		
		Thread t3 = new Thread(()->{
			try {
				t1.join();
				t2.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("is it joined?");
		});
		
		ExecutorService ser = Executors.newFixedThreadPool(2);
	}

}
