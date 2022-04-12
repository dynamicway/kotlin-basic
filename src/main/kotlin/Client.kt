import java.io.DataInputStream
import java.net.Socket

class Client {

    var connected = false
    var response: String? = null

    fun connect() {
        val socket = Socket("127.0.0.1", 8080)
        connected = socket.isConnected

        val inputStream = socket.getInputStream()
        val dataInputStream = DataInputStream(inputStream)

        response = dataInputStream.readUTF()
    }

}