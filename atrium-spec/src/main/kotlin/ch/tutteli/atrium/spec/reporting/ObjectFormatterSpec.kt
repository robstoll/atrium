package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.api.cc.en_UK.isSame
import ch.tutteli.atrium.api.cc.en_UK.toBe
import ch.tutteli.atrium.reporting.IObjectFormatter
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.ITranslator
import ch.tutteli.atrium.reporting.translating.TranslatableRawString
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.prefixedDescribe
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it

abstract class ObjectFormatterSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (ITranslator) -> IObjectFormatter,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit) {
        prefixedDescribe(describePrefix, description, body)
    }

    val translatable = AssertionVerb.ASSERT
    val translatedText = "es gilt"
    val translator = mock<ITranslator> {
        on { translate(any()) } doReturn (translatedText)
    }
    val testee = testeeFactory(translator)

    prefixedDescribe("fun ${testee::format.name}") {

        context("`null`") {
            val i: Int? = null
            val result = testee.format(i)
            it("returns null") {
                verbs.checkImmediately(result).toBe("null")
            }
        }

        context("a ${RawString::class.simpleName}") {
            val text = "test message"
            val result = testee.format(RawString(text))
            it("should still be the ${RawString::class.simpleName}") {
                verbs.checkLazily(result) { isSame(text) }
            }
        }

        context("a ${TranslatableRawString::class.simpleName}") {
            val result = testee.format(TranslatableRawString(translatable))
            it("should be 1:1 the translation (like it was wrapped in an ${RawString::class.simpleName})") {
                verbs.checkImmediately(result).isSame(translatedText)
            }
        }
    }
})
