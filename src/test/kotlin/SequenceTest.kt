import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

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
})
