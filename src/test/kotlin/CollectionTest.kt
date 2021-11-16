import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class CollectionTest : BehaviorSpec({
    Given("길이가 5개인 Int 배열") {
        When("초기화") {
            val actualArray = Array(5) { i -> (i * i) }
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
                actualIndices shouldContainExactly (0..4)
            }
        }
    }

    Given("키 리스트가 주어졌을 때") {
        val keys = 'a'..'f'
        When("associateWith 함수를 사용하여 map 생성") {
            val actualAssociate = keys.associateWith { it.toString().repeat(5) }
            Then("키가 5개씩 들어있는 맵이 생성됨") {
                actualAssociate shouldBe mapOf(
                    'a' to "aaaaa",
                    'b' to "bbbbb",
                    'c' to "ccccc",
                    'd' to "ddddd",
                    'e' to "eeeee",
                    'f' to "fffff"
                )
            }
        }
    }

    Given("비어있는 컬렉션이 주어졌을 때") {
        val givenEmptyList = listOf<String>()
        When("joinToString 을 수행하기 전 ifEmpty 를 사용하여 리스트 초기화") {
            val joinToStringAfterIfEmpty = givenEmptyList
                .ifEmpty { listOf("init", "list") }
                .joinToString (separator = ", ")
            Then("결과는 init, list") {
                joinToStringAfterIfEmpty shouldBe "init, list"
            }
        }
        When("joinToString 을 수행한 뒤 ifEmpty 르 사용하여 리스트 초기화") {
            val joinToStringBeforeIfEmpty = givenEmptyList
                .joinToString (", ")
                .ifEmpty { "init list" }
            Then("결과는 init, list") {
                joinToStringBeforeIfEmpty shouldBe "init list"
            }
        }
    }

    Given("1 .. 10 의 범위가 주어졌을 때") {
        val givenRange = 1..10
        When("5 를 coerceIn 으로 호출") {
            val coerceInBy5 = 5.coerceIn(givenRange)
            Then("반환 값은 5") {
                coerceInBy5 shouldBe 5
            }
        }
        When("-1 을 coerceIn 으로 호출") {
            val coerceInByMinus1 = (-1).coerceIn(givenRange)
            Then("반환 값은 1") {
                coerceInByMinus1 shouldBe 1
            }
        }
        When("100 을 coerceIn 으로 호출") {
            val coerceInBy100 = 100.coerceIn(givenRange)
            Then("반환 값은 10") {
                coerceInBy100 shouldBe 10
            }
        }
    }

    Given("최솟값, 최댓값만 주어졌을 때") {
        val givenMin = 0
        val givenMax = 10
        When("range 를 사용하지 않고 coerce 함수를 실행") {
            val actualCoerceInBy100 = 100.coerceIn(givenMin, givenMax)
            Then("결과는 10") {
                actualCoerceInBy100 shouldBe 10
            }
        }
    }

    Given("0 .. 10의 범위가 주어졌을 때") {
        val givenRange = 0 .. 10
        When("chunked 함수를 통해 길이가 3인 리스트로 나눈다") {
            val actualChunkedBy3 = givenRange.chunked(3)
            Then("결과는 012, 345, 678, 910") {
                actualChunkedBy3 shouldContainExactly listOf(
                    listOf(0, 1, 2),
                    listOf(3, 4, 5),
                    listOf(6, 7, 8),
                    listOf(9, 10)
                )
            }
        }
        When("chunked 함수를 통해 길이가 3인 리스트로 나누고 나눈 부분들의 합을 구했을 때") {
            val actualSumOfChunkedBy3 = givenRange.chunked(3) { it.sum() }
            Then("결과는 3, 12, 21, 19") {
                actualSumOfChunkedBy3 shouldBe listOf(3, 12, 21, 19)
            }
        }

    }
})
