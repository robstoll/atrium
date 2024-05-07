package ch.tutteli.atrium.specs.reporting

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.specs.describeFunTemplate
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class ObjectFormatterSpec(
    testeeFactory: () -> ObjectFormatter,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val testee = testeeFactory()

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

        //TODO 1.3.0 remove suppress again, use InlineElement instead
        @Suppress("DEPRECATION")
        context("a ${ch.tutteli.atrium.reporting.translating.Translatable::class.simpleName}") {
            val result = testee.format(DummyTranslation.TRANSLATION_KEY)
            it("returns getDefault string") {
                expect(result).toEqual("dummy translation")
            }
        }
    }
})

//TODO 1.3.0 remove suppress again, use InlineElement instead
@Suppress("DEPRECATION")
enum class DummyTranslation(override val value: String) : ch.tutteli.atrium.reporting.translating.StringBasedTranslatable {
    TRANSLATION_KEY("dummy translation");
}
