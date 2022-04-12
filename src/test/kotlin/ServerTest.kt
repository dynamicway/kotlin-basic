import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@DelicateCoroutinesApi
internal class ServerTest : FreeSpec({

    "container" - {

        "test01" {
            val server = Server()

            GlobalScope.launch {
                server.open()
            }

            val client = Client()
            client.connect()

            client.connected shouldBe true
        }

        "test02" {
            val server = Server()

            GlobalScope.launch {
                server.open()
            }

            val client = Client()
            client.connect()

            client.response shouldBe "hello server"
        }

    }

})
