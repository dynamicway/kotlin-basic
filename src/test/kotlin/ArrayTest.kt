import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class ArrayTest : BehaviorSpec({
    Given("길이가 5개인 Int 배열") {
        When("초기화") {
            val actualArray = Array(5) { i -> (i * i)}
            Then("배열의 결과는 0 ~ 4까지의 제곱을 담고 있는 문자열 배열") {
                actualArray shouldBe arrayOf(0, 1, 4, 9, 16)
            }
        }
    }

    Given("String 배열이 주어졌을 때") {
        val givenArray = "this is array of String".split(" ")
        When("배열의 indices 를 호출") {
            val actualIndices = givenArray.indices
            Then("호출한 indices 는 배열의 크기 -1 의 range") {
                actualIndices shouldContainExactly (0 .. 4)
            }
        }
    }
})
