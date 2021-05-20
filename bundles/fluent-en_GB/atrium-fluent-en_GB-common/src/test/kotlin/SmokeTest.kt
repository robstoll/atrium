import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.AssertionVerb
import ch.tutteli.atrium.api.verbs.assert
import ch.tutteli.atrium.api.verbs.assertThat
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.createDescriptiveAssertion
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.translations.DescriptionAnyAssertion.TO_BE
import ch.tutteli.atrium.translations.DescriptionBasic.IS
import kotlin.test.Test

class SmokeTest {
    @Test
    fun toEqual_canBeUsed() {
        expect(1).toEqual(1)
    }

    @Test
    fun expectationFunctionWithoutI18nCanBeUsed() {
        expect(2).toBeEven()
        expect(1).toBeOdd()
    }

    @Test
    fun expectationFunctionWithI18nCanBeUsed() {
        expect(4).toBeMultipleOf(2)
    }


    //TODO remove with 0.18.0
    @Test
    fun assertWithinAssert() {
        @Suppress("DEPRECATION")
        expect {
            assert(1) {
                (assert(2).toEqual(1))
            }
        }.toThrow<AssertionError> {
            messageToContain(
                "${AssertionVerb.ASSERT.getDefault()}: 1",
                "${AssertionVerb.ASSERT.getDefault()}: 2",
                "${TO_BE.getDefault()}: 1"
            )
        }
    }

    //TODO remove with 0.18.0
    @Test
    fun assertThatWithinAssertThat() {
        @Suppress("DEPRECATION")
        expect {
            assertThat(1) {
                assertThat(2).toEqual(1)
            }
        }.toThrow<AssertionError> {
            messageToContain(
                "${AssertionVerb.ASSERT_THAT.getDefault()}: 1",
                "${AssertionVerb.ASSERT_THAT.getDefault()}: 2",
                "${TO_BE.getDefault()}: 1"
            )
        }
    }

    @Test
    fun expectWithinExpect() {
        expect {
            expect(1) {
                @Suppress("DEPRECATION")
                expect(2).toEqual(1)
            }
        }.toThrow<AssertionError> {
            messageToContain(
                "${AssertionVerb.EXPECT.getDefault()}: 1",
                "${AssertionVerb.EXPECT.getDefault()}: 2",
                "${TO_BE.getDefault()}: 1"
            )
        }
    }

    @Test
    fun expectAnExceptionOccurred() {
        expect {
            throw IllegalArgumentException()
        }.toThrow<IllegalArgumentException>()
    }

    @Test
    fun expectAnExceptionWithAMessageOccurred() {
        expect {
            throw IllegalArgumentException("oho... hello btw")
        }.toThrow<IllegalArgumentException> {
            messageToContain("hello")
        }
    }

    @Test
    fun expectNotToThrow() {
        expect {

        }.notToThrow()
    }
}

fun Expect<Int>.toBeEven() =
    _logic.createAndAppendAssertion("is", Text("an even number")) { it % 2 == 0 }

fun Expect<Int>.toBeOdd() =
    _logic.appendAssertion(_logic.createDescriptiveAssertion(IS, Text("an odd number")) { it % 2 == 1 })

fun Expect<Int>.toBeMultipleOf(base: Int): Expect<Int> = _logicAppend { toBeMultipleOf(base) }

private fun AssertionContainer<Int>.toBeMultipleOf(base: Int): Assertion =
    createDescriptiveAssertion(DescriptionIntAssertions.IS_MULTIPLE_OF, base) { it % base == 0 }

enum class DescriptionIntAssertions(override val value: String) : StringBasedTranslatable {
    IS_MULTIPLE_OF("is multiple of")
}
