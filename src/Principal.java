import java.util.HashMap;
import java.util.Map;

public class Principal {
	public static void main(String[] args) {
		Map<Integer, Thread> mapThreads = new HashMap<Integer, Thread>();
		Principal p = new Principal();
		
		for (int i = 1; i <= 30; i++) {
			ThreadRunnable tr = p.new ThreadRunnable();
			
			tr.setId(i);
			Thread t = new Thread(tr);

			mapThreads.put(i, t);
		}
	
		for (Map.Entry<Integer, Thread> map : mapThreads.entrySet()) {
			Thread t = map.getValue();
			t.start();
		}
	}

	public class ThreadRunnable implements Runnable {
		private int id;
		private ControleDados controle = ControleDados.getInstance();
		
		public int getId() {
			return id;
		}
		
		public void setId(int id) {
			this.id = id;
		}

		
		@Override
		public void run() {
			while (controle.temLetraMaiuscula()) {
				if (id == controle.getIdThreadAtual()) {
					int posicaoCaractereAlterado = controle.modificaCaractere();

					System.out.println("Thread " + id + " alterando caractere " + controle.getCaractereFromMessage(posicaoCaractereAlterado));
					System.out.println("Mensagem: " + controle.getMensagem());
					
					try {
						Thread.sleep(1000);
						controle.setIdThreadAtual();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}