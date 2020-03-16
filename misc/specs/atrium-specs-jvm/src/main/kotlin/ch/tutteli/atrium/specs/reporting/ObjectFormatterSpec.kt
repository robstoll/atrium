package ch.tutteli.atrium.specs.reporting

import ch.tutteli.atrium.api.fluent.en_GB.isSameAs
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.StringBasedRawString
import ch.tutteli.atrium.reporting.translating.TranslatableBasedRawString
import ch.tutteli.atrium.reporting.translating.Translator
import ch.tutteli.atrium.specs.AssertionVerb
import ch.tutteli.atrium.specs.describeFunTemplate
import io.mockk.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class ObjectFormatterSpec(
    testeeFactory: (Translator) -> ObjectFormatter,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFunTemplate(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val translatable = AssertionVerb.ASSERT
    val translatedText = "es gilt"
    val translator = mockk<Translator> {
        every { translate(any()) } returns (translatedText)
    }
    val testee = testeeFactory(translator)

    describeFunTemplate(testee::format.name) {

        context("`null`") {
            val i: Int? = null
            val result = testee.format(i)
            it("returns null") {
                expect(result).toBe("null")
            }
        }

        context("a ${StringBasedRawString::class.simpleName}") {
            val text = "test message"
            val result = testee.format(RawString.create(text))
            it("should still be the ${StringBasedRawString::class.simpleName}") {
                expect(result).isSameAs(text)
            }
        }

        context("a ${TranslatableBasedRawString::class.simpleName}") {
            val result = testee.format(RawString.create(translatable))
            it("should be 1:1 the translation (like it was wrapped in an ${StringBasedRawString::class.simpleName})") {
                expect(result).isSameAs(translatedText)
            }
        }
    }
})
