import java.net.ServerSocket;
import java.net.Socket;
import java.io. *;
/**
 * The main() program in this class is designed to read requests from
 * a Web browser and display the requests on standard output.  The
 * program sets up a listener on port 50505.  It can be contacted
 * by a Web browser running on the same machine using a URL of the
 * form  http://localhost:505050/path/to/resource.html  This method
 * does not return any data to the web browser.  It simply reads the
 * request, writes it to standard output, and then closes the connection.
 * The program continues to run, and the server continues to listen
 * for new connections, until the program is terminated (by clicking the
 * red "stop" square in Eclipse or by Control-C on the command line).
 */




public class ReadRequest {
	
	/**
	 * The server listens on this port.  Note that the port number must
	 * be greater than 1024 and lest than 65535.
	 */
	private final static int LISTENING_PORT = 50505;
	
	/**
	 * Main program opens a server socket and listens for connection
	 * requests.  It calls the handleConnection() method to respond
	 * to connection requests.  The program runs in an infinite loop,
	 * unless an error occurs.
	 * @param args ignored
	 */
	public static void main(String[] args) {
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(LISTENING_PORT);
		}
		catch (Exception e) {
			System.out.println("Failed to create listening socket.");
			return;
		}
		System.out.println("Listening on port " + LISTENING_PORT);
		try {
			while (true) {
				Socket connection = serverSocket.accept();
				System.out.println("\nConnection from " 
						+ connection.getRemoteSocketAddress());
				ConnectionThread thread = new ConnectionThread(connection);
				thread.start();
			}
		}
		catch (Exception e) {
			System.out.println("Server socket shut down unexpectedly!");
			System.out.println("Error: " + e);
			System.out.println("Exiting.");
		}
	}

	/**
	 * Handle commuincation with one client connection.  This method reads
	 * lines of text from the client and prints them to standard output.
	 * It continues to read until the client closes the connection or
	 * until an error occurs or until a blank line is read.  In a connection
	 * from a Web browser, the first blank line marks the end of the request.
	 * This method can run indefinitely,  waiting for the client to send a
	 * blank line.
	 * NOTE:  This method does not throw any exceptions.  Exceptions are
	 * caught and handled in the method, so that they will not shut down
	 * the server.
	 * @param connection the connected socket that will be used to
	 *    communicate with the client.
	 */
	public static void handleConnection(Socket clientSocket) {
        InputStream input;
        OutputStream output;

        try {
            // Get input and output streams from the client socket
            input = clientSocket.getInputStream();
            output = clientSocket.getOutputStream();

            // Read the request from the input stream
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String request = reader.readLine();
            // Parse the request to extract the method, path, and HTTP version

            // Check for valid request format
            if (isValidRequest(request)) {
                // Construct the full file path
            	String rootDirectory = "C:\\Users\\User\\NodeServer\\NodeApp\\public";
                String pathToFile = extractPathFromRequest(request);
                String filePath = rootDirectory + pathToFile;

                // Handle file retrieval and sending response
                File file = new File(filePath);
                if (file.exists() && file.isFile()) {
                    sendFileResponse(file, output);
                } else {
                    sendErrorResponse(404, "Not Found", output);
                }
            } else {
                // Handle invalid request
                sendErrorResponse(400, "Bad Request", output);
            }
        } catch (IOException e) {
            // Handle exceptions
            e.printStackTrace();
        } finally {
            try {
                // Close the socket and associated streams
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean isValidRequest(String request) {
        // Check if the request starts with "GET" and contains "HTTP/1.1"
        return request != null && request.startsWith("GET") && request.contains("HTTP/1.1");
    }

    private static String extractPathFromRequest(String request) {
        // Extract the path by splitting the request
        String[] tokens = request.split(" ");
        return tokens[1]; // The path is the second token
    }

    private static void sendFileResponse(File file, OutputStream output) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[10000000];
            int bytesRead;

            // Send HTTP response headers
            String responseHeaders = "HTTP/1.1 200 OK\r\nContent-Length: " + file.length() + "\r\n\r\n";
            output.write(responseHeaders.getBytes());

            // Send the file content
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }

            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendErrorResponse(int code, String status, OutputStream output) {
        try {
            String errorResponse = "HTTP/1.1 " + code + " " + status + "\r\n\r\n";
            
            switch (code) {
            case 400:
                errorResponse += "400 Bad Request: The request could not be understood by the server.";
                break;
            case 404:
                errorResponse += "404 Not Found: The requested resource was not found on the server.";
                break;
            case 500:
                errorResponse += "500 Internal Server Error: The server encountered an unexpected condition that prevented it from fulfilling the request.";
                break;
            case 501:
                errorResponse += "501 Not Implemented: The server does not support the functionality required to fulfill the request.";
                break;
            default:
                // Default error message for unhandled error codes
                errorResponse += status;
        }

        output.write(errorResponse.getBytes());
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    
    public static String getMimeType(String fileName) {
        int pos = fileName.lastIndexOf('.');
        if (pos < 0)  // no file extension in name
            return "x-application/x-unknown";
        String ext = fileName.substring(pos+1).toLowerCase();
        if (ext.equals("txt")) return "text/plain";
        else if (ext.equals("html")) return "text/html";
        else if (ext.equals("htm")) return "text/html";
        else if (ext.equals("css")) return "text/css";
        else if (ext.equals("js")) return "text/javascript";
        else if (ext.equals("java")) return "text/x-java";
        else if (ext.equals("jpeg")) return "image/jpeg";
        else if (ext.equals("jpg")) return "image/jpeg";
        else if (ext.equals("png")) return "image/png";
        else if (ext.equals("gif")) return "image/gif";
        else if (ext.equals("ico")) return "image/x-icon";
        else if (ext.equals("class")) return "application/java-vm";
        else if (ext.equals("jar")) return "application/java-archive";
        else if (ext.equals("zip")) return "application/zip";
        else if (ext.equals("xml")) return "application/xml";
        else if (ext.equals("xhtml")) return"application/xhtml+xml";
        else return "x-application/x-unknown";
           // Note:  x-application/x-unknown  is something made up;
           // it will probably make the browser offer to save the file.
     }
    
    private static class ConnectionThread extends Thread {
        Socket connection;
        ConnectionThread(Socket connection) {
           this.connection = connection;
        }
        public void run() {
           handleConnection(connection);
        }
     }
    
}
