import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FunctionalProgrammingTest {

    @Test
    fun fold_sum() {
        val foldResult = (1..10).fold(0) { acc, i -> acc + i }
        assertThat(foldResult).isEqualTo(55)
    }

}