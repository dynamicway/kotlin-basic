import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import kotlin.math.ceil
import kotlin.math.sqrt

class SequenceTest: BehaviorSpec({
    Given("1부터 200만 까지의 sequence 가 주어졌을 때") {
        val givenSequence = (100..2_000_000).asSequence()
        When("2를 곱한 뒤 3으로 나누어 떨어지는 것들 중 첫번째 값을 반환") {
            val actualFirstValue = givenSequence
                .map { it * 2 }
                .filter { it % 3 == 0 }
                .first()
            Then("결과는 204") {
                actualFirstValue shouldBe 204
            }
        }
        When("2를 곱한 뒤 3으로 나누어 떨어지는 수를 필터링 하는데 필터링을 거친 수들은 리스트에 담는다") {
            val givenList = mutableListOf<Int>()

            givenSequence
                .map { it * 2 }
                .filter { givenList.add(it); it % 3 == 0 }
                .first()

            Then("리스트의 사이즈는 3") {
                givenList.size shouldBe 3
            }
        }
    }

    Given("n + 1부터 시작해서 1씩 증가하는 무한 Sequence 와 소수인지 판단하는 Int 의 확잠함수가 주어졌을 때") {
        fun Int.isPrime() = this == 2 || (2..ceil(sqrt(this.toDouble())).toInt())
            .none { divisor -> this % divisor == 0 }

        fun givenNextPrimeFunction(n: Int) = generateSequence(n + 1) { it + 1 }
            .first { it.isPrime() }

        When("3의 다음 소수를 찾는다") {
            val actualGivenNextPrimeBy3Result = givenNextPrimeFunction(3)
            Then("결과는 5") {
                actualGivenNextPrimeBy3Result shouldBe 5
            }
        }

        When("5의 다음 소수를 찾는다") {
            val actualGivenNextPrimeBy5Result = givenNextPrimeFunction(5)
            Then("결과는 7") {
                actualGivenNextPrimeBy5Result shouldBe 7
            }
        }

    }

})
