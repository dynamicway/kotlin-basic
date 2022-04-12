import java.io.DataOutputStream
import java.net.ServerSocket

private const val PORT = 8080

class Server(
    private val serverSocket: ServerSocket = ServerSocket(PORT)
) {

    fun open() {
        val socket = serverSocket.accept()
        while (!socket.isClosed) {

            val dataOutputStream = DataOutputStream(socket.getOutputStream())

            dataOutputStream.writeUTF("hello server")
            dataOutputStream.flush()
            dataOutputStream.close()
            socket.close()
        }
    }
}