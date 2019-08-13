package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.api.cc.en_GB.isSameAs
import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.StringBasedRawString
import ch.tutteli.atrium.reporting.translating.TranslatableBasedRawString
import ch.tutteli.atrium.reporting.translating.Translator
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

//TODO #116 migrate spek1 to spek2 - move to specs-common
abstract class ObjectFormatterSpec(
    verbs: AssertionVerbFactory,
    testeeFactory: (Translator) -> ObjectFormatter,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val translatable = AssertionVerb.ASSERT
    val translatedText = "es gilt"
    val translator = mock<Translator> {
        on { translate(any()) } doReturn (translatedText)
    }
    val testee = testeeFactory(translator)

    describeFun(testee::format.name) {

        context("`null`") {
            val i: Int? = null
            val result = testee.format(i)
            it("returns null") {
                verbs.checkImmediately(result).toBe("null")
            }
        }

        context("a ${StringBasedRawString::class.simpleName}") {
            val text = "test message"
            val result = testee.format(RawString.create(text))
            it("should still be the ${StringBasedRawString::class.simpleName}") {
                verbs.checkLazily(result) { isSameAs(text) }
            }
        }

        context("a ${TranslatableBasedRawString::class.simpleName}") {
            val result = testee.format(RawString.create(translatable))
            it("should be 1:1 the translation (like it was wrapped in an ${StringBasedRawString::class.simpleName})") {
                verbs.checkImmediately(result).isSameAs(translatedText)
            }
        }
    }
})
