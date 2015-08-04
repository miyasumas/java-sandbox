package com.github.miyasum.sandbox;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * タスクの追加をブロッキングするスレッドプールが作れるか
 * @author MIYASAKA Yasumasa
 * @since 2015/07/21
 */
public class ThreadPoolSample {

	public static void main(String[] args) {
		ExecutorService threadPool = new BlockingThreadPoolExecutor();
		try {
			for (int i = 0; i < Integer.MAX_VALUE; i++) {
				threadPool.execute(new WorkerThread());
				System.out.println(i);
			}
		} finally {
			threadPool.shutdown();
		}
	}

	private static class BlockingThreadPoolExecutor extends ThreadPoolExecutor {

		private static final int THREADS = 8;

		private static final int QUEUE_SIZE = 8;

		public BlockingThreadPoolExecutor() {
			super(THREADS, THREADS, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(QUEUE_SIZE),
				(r, t) -> {
					try {
						System.out.println("blocking");
						t.getQueue().put(r);
					} catch (InterruptedException e) {
						throw new CancellationException(e.getMessage());
					}
				});
		}
	}

	private static class WorkerThread implements Runnable {

		@Override
		public void run() {
			try {
				TimeUnit.SECONDS.sleep(10);
				System.out.println("a");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
