package team.sheldon.server;

import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;

public class Logger {

  FileWriter loggerFile;

  public Logger(String filename) {
    try {
      loggerFile = new FileWriter(filename, true);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public synchronized final void log(Socket socket, String message) {

    String eNet = socket.getInetAddress().toString();
    System.out.println(eNet + ">> " + message);
    try {
      loggerFile.write(eNet + ";" + message);
      loggerFile.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
