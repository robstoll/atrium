import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.notToThrow
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.AssertionVerb
import ch.tutteli.atrium.api.verbs.assert
import ch.tutteli.atrium.api.verbs.assertThat
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.translations.DescriptionAnyAssertion.TO_BE
import ch.tutteli.atrium.translations.DescriptionBasic.IS
import kotlin.test.Test

class SmokeTest {
    @Test
    fun toBe_canBeUsed() {
        assertThat(1).toBe(1)
    }

    @Test
    fun assertionFunctionWithoutI18nCanBeUsed() {
        assertThat(2).isEven()
    }

    @Test
    fun assertionFunctionWithI18nCanBeUsed() {
        assertThat(4).isMultipleOf(2)
    }


    @Test
    fun assertWithinAssert() {
        expect {
            assert(1) {
                @Suppress("DEPRECATION")
                assert(2).toBe(1)
            }
        }.toThrow<AssertionError> {
            messageContains(
                "${AssertionVerb.ASSERT.getDefault()}: 1",
                "${AssertionVerb.ASSERT.getDefault()}: 2",
                "${TO_BE.getDefault()}: 1"
            )
        }
    }

    @Test
    fun assertThatWithinAssertThat() {
        expect {
            @Suppress("DEPRECATION")
            assertThat(1) {
                assertThat(2).toBe(1)
            }
        }.toThrow<AssertionError> {
            messageContains(
                "${AssertionVerb.ASSERT_THAT.getDefault()}: 1",
                "${AssertionVerb.ASSERT_THAT.getDefault()}: 2",
                "${TO_BE.getDefault()}: 1"
            )
        }
    }

    @Test
    fun expectWithinExpect() {
        expect {
            @Suppress("DEPRECATION")
            expect(1) {
                expect(2).toBe(1)
            }
        }.toThrow<AssertionError> {
            messageContains(
                "${AssertionVerb.EXPECT.getDefault()}: 1",
                "${AssertionVerb.EXPECT.getDefault()}: 2",
                "${TO_BE.getDefault()}: 1"
            )
        }
    }

    @Test
    fun assertAnExceptionOccurred() {
        assertThat {
            throw IllegalArgumentException()
        }.toThrow<IllegalArgumentException>()
    }

    @Test
    fun assertAnExceptionWithAMessageOccurred() {
        assertThat {
            throw IllegalArgumentException("oho... hello btw")
        }.toThrow<IllegalArgumentException> {
            messageContains("hello")
        }
    }

    @Test
    fun assertNotToThrow() {
        assertThat {

        }.notToThrow()
    }
}


fun Expect<Int>.isEven() = createAndAddAssertion(IS, Text("an even number")) { it % 2 == 0 }

fun Expect<Int>.isMultipleOf(base: Int): Expect<Int> = addAssertion(_isMultipleOf(this, base))

private fun _isMultipleOf(expect: Expect<Int>, base: Int): Assertion =
    assertionBuilder.createDescriptive(expect, DescriptionIntAssertions.IS_MULTIPLE_OF, base) {
        it % base == 0
    }

enum class DescriptionIntAssertions(override val value: String) : StringBasedTranslatable {
    IS_MULTIPLE_OF("is multiple of")
}
