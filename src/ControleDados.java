import java.util.Random;

public class ControleDados {
    public static ControleDados instance = null;
	public static String mensagem;
	public static String mensagemLetraMaiuscula;
	
	public static int idThreadAtual = 1;
    
    public ControleDados () {
    	mensagem = getNomeAleatorio(80);
		mensagemLetraMaiuscula = mensagem.toUpperCase();
    }
    
    public static synchronized ControleDados getInstance() {
        if (instance == null) {
            instance = new ControleDados();
        }

        return instance;
    }

	public synchronized boolean temLetraMaiuscula() {
		return !mensagem.equals(mensagemLetraMaiuscula);
	}

	private String getNomeAleatorio (int nCaracteres) {
		Random rand = new Random();
		char[] letras = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < nCaracteres; i++) {
	        int ch = rand.nextInt (letras.length);
	        sb.append (letras [ch]);
	    }    

	    return sb.toString();    
	}

	public synchronized int modificaCaractere() {
		int position = 0;
		
		for (int i = 0; i < mensagem.length(); i++) {
			if (mensagem.charAt(i) == Character.toLowerCase(mensagem.charAt(i))) {
				char palavra [] = mensagem.toCharArray();
				palavra [i] = Character.toUpperCase(mensagem.charAt(i));
				mensagem = new String(palavra);
				position = i;
				break;
			}
		}

		return position;
	}

	public synchronized int getIdThreadAtual() {
		return idThreadAtual;
	}
	
	public synchronized void setIdThreadAtual() {
		idThreadAtual = idThreadAtual == 30 ? 1 : idThreadAtual + 1;
	}

	public synchronized String getMensagem() {
		return mensagem;
	}
	
	public synchronized char getCaractereFromMessage(int position) {
		return mensagem.charAt(position);
	}
}