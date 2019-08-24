import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorApp {
	public static void main(String[] args) throws IOException {

		int porta = 8080;
		// inicia a conexão na porta 8080
		ServerSocket serverSocket = new ServerSocket(porta);
		System.out.println("Server aberto na porta: " + porta);

		while (true) {
			// cliente conectado
			Socket clientSocket = serverSocket.accept();
			System.out.println("Novo cliente conectado");

			// stream de entrada e saída
			BufferedReader streamEntrada = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			BufferedWriter streamSaida = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

			// imprimi na tela as informações mandadas pelo navegador
			String str;
			while ((str = streamEntrada.readLine()) != null) {
				System.out.println(str);
				if (str.isEmpty()) {
					break;
				}
			}

			// imprimi no navegador essas informações
			streamSaida.write("HTTP/1.0 200 OK\r\n");
			streamSaida.write("Date: Fri, 31 Dec 1999 23:59:59 GMT\r\n");
			streamSaida.write("Server: Apache/0.8.4\r\n");
			streamSaida.write("Content-Type: text/html\r\n");
			streamSaida.write("\r\n");
			streamSaida.write("<TITLE>Ola mundo!</TITLE>");
			streamSaida
					.write("<html>\r\n" + "<body>\r\n" + "<p>Ola mundo!</p>\r\n" + "</body>\r\n" + "</html>\r\n" + "");
			streamSaida.flush();

			System.out.println("Conexão finalizada");

			// fecha a conexão do servidor e do cliente
			clientSocket.close();
		}
	}
}

//Abrir um soquete do tipo servidor (escolha porta 8080) - OK
//Aguarde conexão - OK
//Quando receber conexão, capture o soquete da conexão - OK
//Obtenha stream de entrada - OK 
//Leia e imprima os dados da entrada - OK  
//Obtenha a stream de saída - OK 
//Escreva uma mensagem HTTP de resposta (cabeçalho + página web) - OK
//
//<html>
//<body>
//<p>Ola mundo!</p>
//</body>
//</html>
