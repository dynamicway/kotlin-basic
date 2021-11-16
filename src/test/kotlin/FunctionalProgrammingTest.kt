import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.ShouldSpec
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.math.BigInteger

class FunctionalProgrammingTest {

    @Test
    fun fold_sum() {
        val foldResult = (1..10).fold(0) { acc, i -> acc + i }
        assertThat(foldResult).isEqualTo(55)
    }

    @Test
    fun fold_factorial() {
        val foldResult = (1..5).fold(1) { acc, i -> acc * i }
        assertThat(foldResult).isEqualTo(120)
    }

    @Test
    fun fold_fibonacci() {
        fun fibonacci(n: Int) = (2 until n).fold(1 to 1) { acc, _ -> acc.second to acc.first + acc.second }.second
        assertThat(fibonacci(5)).isEqualTo(5)
    }

    @Test
    fun reduce_sum() {
        val reduceResult = (1..10).reduce { acc, i -> acc + i }
        assertThat(reduceResult).isEqualTo(55)
    }

    @Test
    fun reduce_throwsUnsupportedOperationException_when_listIsEmpty() {
        assertThatThrownBy { listOf<Int>().reduce { acc, i -> acc + i } }
            .isInstanceOf(UnsupportedOperationException::class.java)
            .hasMessage("Empty collection can't be reduced.")
    }
}

class FunctionalProgrammingKoTest : ShouldSpec({
    context("recursive") {

        should("factorial throws StackOverFlowError") {
            fun givenRecursiveFactorialFunction(n: Long): BigInteger =
                when (n) {
                    0L, 1L -> BigInteger.ONE
                    else -> BigInteger.valueOf(n) * givenRecursiveFactorialFunction(n - 1)
                }

            shouldThrowExactly<StackOverflowError> { givenRecursiveFactorialFunction(100000) }
        }

        should("tailrec recursive factorial function not throws StackOverFlowError") {
            tailrec fun givenTailrecRecursiveFactorialFunction(
                n: Long,
                acc: BigInteger = BigInteger.ONE
            ): BigInteger =
                when (n) {
                    0L -> BigInteger.ONE
                    1L -> acc
                    else -> givenTailrecRecursiveFactorialFunction(n - 1, acc * BigInteger.valueOf(n))
                }

            shouldNotThrowAny { givenTailrecRecursiveFactorialFunction(75000) }
        }
    }
})