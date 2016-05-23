package team.sheldon.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import team.sheldon.person.Person;

public class Server {
  
  private Logger logger;

  public Server() {
    logger = new Logger("adrelilog.txt");
  }

  private static final int PORT = 56789;

  public static void main(String[] args) throws IOException {
    Server serverInstance = new Server();
    serverInstance.start();
  }

  private void start() throws IOException {
    System.out.println(">>> Server wird gestartet");

    final InetSocketAddress myInetSocketAddress = new InetSocketAddress("localhost", PORT);
    final ServerSocket myServerSocket = new ServerSocket(); // unbound
    // ServerSocket
    myServerSocket.bind(myInetSocketAddress); // we bind()it
    // */

    while (true) { // start infinite loop
      final Socket verbindungZumClient = myServerSocket.accept();
      // accept() blocked und liefert Client-Socket

      // zurück; von dem kann gelesen werden,
      // wenn's ein schreibender Client ist

      logger.log(verbindungZumClient, "--> Local Address angemeldet: " + verbindungZumClient.getLocalAddress()); // mit
      // wem?
      logger.log(verbindungZumClient, "--> InetAddress angemeldet: " + verbindungZumClient.getInetAddress());
      new ClientConnectionThread(verbindungZumClient, logger).start();
    }
  } // end infinite loop

}
