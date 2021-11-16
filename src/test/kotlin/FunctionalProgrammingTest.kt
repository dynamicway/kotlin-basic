import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class FunctionalProgrammingTest {

    @Test
    fun fold_sum() {
        val foldResult = (1..10).fold(0) { acc, i -> acc + i }
        assertThat(foldResult).isEqualTo(55)
    }

    @Test
    fun fold_factorial() {
        val foldResult = (1..5).fold(1) { acc, i -> acc * i}
        assertThat(foldResult).isEqualTo(120)
    }

    @Test
    fun fold_fibonacci() {
        fun fibonacci(n: Int) = (2 until n).fold(1 to 1) { acc, _ -> acc.second to  acc.first + acc.second}.second
        assertThat(fibonacci(5)).isEqualTo(5)
    }

    @Test
    fun reduce_sum() {
        val reduceResult = (1..10).reduce { acc, i -> acc + i }
        assertThat(reduceResult).isEqualTo(55)
    }

    @Test
    fun reduce_throwsUnsupportedOperationException_when_listIsEmpty() {
        assertThatThrownBy { listOf<Int>().reduce{ acc, i -> acc + i } }
            .isInstanceOf(UnsupportedOperationException::class.java)
            .hasMessage("Empty collection can't be reduced.")
    }
}