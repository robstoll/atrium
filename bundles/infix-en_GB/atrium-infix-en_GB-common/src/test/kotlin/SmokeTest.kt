
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.translations.DescriptionBasic.IS
import kotlin.test.Test

class SmokeTest {
    @Test
    fun toBe_canBeUsed() {
        //TODO #233 uncomment once toBe is implemented
        //assertThat(1) toBe 1
    }

    @Test
    fun assertionFunctionWithoutI18nCanBeUsed() {
        //TODO #233 uncomment once toBe is implemented
        //assertThat(2) toBe even
    }

    @Test
    fun assertionFunctionWithI18nCanBeUsed() {
        //TODO #233 uncomment once toBe is implemented
        //assertThat(4) toBe multipleOf 2
    }


    @Test
    fun assertWithinAssert() {
        //    TODO #233 uncomment once toBe is implemented
//        expect {
//            assert(1) {
//                @Suppress("DEPRECATION")
//                assert(2) toBe 2
//            }
//        }.toThrow<AssertionError> {
//            messageContains(
//                "${AssertionVerb.ASSERT.getDefault()}: 1",
//                "${AssertionVerb.ASSERT.getDefault()}: 2",
//                "${TO_BE.getDefault()}: 1"
//            )
//        }
    }

    @Test
    fun assertThatWithinAssertThat() {
        //TODO #233 uncomment once toBe is implemented
//        expect {
//            @Suppress("DEPRECATION")
//            assertThat(1) {
//                assertThat(2).toBe(1)
//            }
//        }.toThrow<AssertionError> {
//            messageContains(
//                "${AssertionVerb.ASSERT_THAT.getDefault()}: 1",
//                "${AssertionVerb.ASSERT_THAT.getDefault()}: 2",
//                "${TO_BE.getDefault()}: 1"
//            )
//        }
    }

    @Test
    fun expectWithinExpect() {
        //TODO #233 uncomment once toBe is implemented
//        expect {
//            @Suppress("DEPRECATION")
//            expect(1) {
//                expect(2).toBe(1)
//            }
//        }.toThrow<AssertionError> {
//            messageContains(
//                "${AssertionVerb.EXPECT.getDefault()}: 1",
//                "${AssertionVerb.EXPECT.getDefault()}: 2",
//                "${TO_BE.getDefault()}: 1"
//            )
//        }
    }

    @Test
    fun assertAnExceptionOccurred() {
        //TODO #233 uncomment once toBe is implemented
//        assertThat {
//            throw IllegalArgumentException()
//        }.toThrow<IllegalArgumentException>()
    }

    @Test
    fun assertAnExceptionWithAMessageOccurred() {
        //TODO #233 uncomment once toBe is implemented
//        assertThat {
//            throw IllegalArgumentException("oho... hello btw")
//        }.toThrow<IllegalArgumentException> {
//            messageContains("hello")
//        }
    }

    @Test
    fun assertNotToThrow() {
        //TODO #233 uncomment once toBe is implemented
//        assertThat {
//
//        }.notToThrow()
    }
}


fun Expect<Int>.isEven() = createAndAddAssertion(IS, RawString.create("an even number")) { it % 2 == 0 }

fun Expect<Int>.isMultipleOf(base: Int): Expect<Int> = addAssertion(_isMultipleOf(this, base))

private fun _isMultipleOf(assertionContainer: Expect<Int>, base: Int): Assertion =
    ExpectImpl.builder.createDescriptive(assertionContainer, DescriptionIntAssertions.IS_MULTIPLE_OF, base) {
        it % base == 0
    }

enum class DescriptionIntAssertions(override val value: String) : StringBasedTranslatable {
    IS_MULTIPLE_OF("is multiple of")
}
