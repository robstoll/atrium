package readme.examples

import ch.tutteli.atrium.api.fluent.en_GB.and
import ch.tutteli.atrium.api.fluent.en_GB.toBeGreaterThanOrEqualTo
import ch.tutteli.atrium.api.fluent.en_GB.toBeLessThan
import ch.tutteli.atrium.creating.Expect
import org.junit.jupiter.api.Test
import readme.examples.jupiter.ReadmeTest
import java.util.*

/**
 * See [ReadmeTest] on how these tests are written into README.md
 */
class BetweenExample : ReadmeTest {
    @Test
    fun `code-own-compose-1`() {
        fun <T : Date> Expect<T>.toBeBetween(lowerBoundInclusive: T, upperBoundExclusive: T) =
            and {
                toBeGreaterThanOrEqualTo(lowerBoundInclusive)
                toBeLessThan(upperBoundExclusive)
            }
    }
}

