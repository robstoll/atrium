package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import kotlin.test.Test

class ThirdPartyExpectationSamples {

    // simulating assertJ
    private fun <T> assertThat(subject: T) = expect(subject)
    private fun <T> Expect<T>.isEqualTo(t: T) = toEqual(t)

    @Test
    fun toHoldThirdPartyExpectation() {
        expect(listOf(1)) get index(0) {
            it toHold thirdPartyExpectation("(assertJ) is equal to", 1) { subject -> // 1
                assertThat(subject).isEqualTo(1)
            }
        }

        fails {
            expect(listOf(1)) get index(0) {
                it toHold thirdPartyExpectation("(assertJ) is equal to", 1) { subject -> // 1
                    // throws and thus the expectation that the third-party expectation holds fails
                    assertThat(subject).isEqualTo(2)
                }
            }
        }
    }

}
