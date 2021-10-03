package ch.tutteli.atrium.specs.reporting

import ch.tutteli.atrium.api.fluent.en_GB.toBeTheInstance
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Translator
import ch.tutteli.atrium.specs.describeFunTemplate
import io.mockk.every
import io.mockk.mockk
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class ObjectFormatterSpec(
    testeeFactory: (Translator) -> ObjectFormatter,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val translatedText = "es gilt"
    val translator = mockk<Translator> {
        every { translate(any()) } returns (translatedText)
    }
    val testee = testeeFactory(translator)

    describeFun(testee::format.name) {

        context("`null`") {
            val i: Int? = null
            val result = testee.format(i)
            it("returns null") {
                expect(result).toEqual("null")
            }
        }

        context("a ${Text::class.simpleName}") {
            val result = testee.format(Text("hello"))
            it("returns the containing string") {
                expect(result).toEqual("hello")
            }
        }

        context("a ${Translatable::class.simpleName}") {
            val result = testee.format(ch.tutteli.atrium.api.verbs.internal.AssertionVerb.EXPECT)
            it("returns the translated string") {
                expect(result).toBeTheInstance(translatedText)
            }
        }
    }
})
