package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.api.cc.en_UK.isSame
import ch.tutteli.atrium.api.cc.en_UK.toBe
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.StringBasedRawString
import ch.tutteli.atrium.reporting.translating.Translator
import ch.tutteli.atrium.reporting.translating.TranslatableBasedRawString
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it

abstract class ObjectFormatterSpec(
    verbs: IAssertionVerbFactory,
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
                verbs.checkLazily(result) { isSame(text) }
            }
        }

        context("a ${TranslatableBasedRawString::class.simpleName}") {
            val result = testee.format(RawString.create(translatable))
            it("should be 1:1 the translation (like it was wrapped in an ${StringBasedRawString::class.simpleName})") {
                verbs.checkImmediately(result).isSame(translatedText)
            }
        }
    }
})
