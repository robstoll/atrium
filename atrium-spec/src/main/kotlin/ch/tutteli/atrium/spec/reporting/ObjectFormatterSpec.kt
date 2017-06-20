package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.isSame
import ch.tutteli.atrium.reporting.IObjectFormatter
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.ITranslator
import ch.tutteli.atrium.reporting.translating.TranslatableRawString
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.toBe
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it


open class ObjectFormatterSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (ITranslator) -> IObjectFormatter
) : Spek({

    val translatable = AssertionVerb.ASSERT
    val translatedText = "es gilt"
    val translator = mock<ITranslator> {
        on { translate(any()) } doReturn (translatedText)
    }
    val testee = testeeFactory(translator)

    describe("format `null`") {
        val i: Int? = null
        val result = testee.format(i)
        it("returns null") {
            verbs.checkImmediately(result).toBe("null")
        }
    }

    describe("format a ${RawString::class.simpleName}") {
        val text = "test message"
        val result = testee.format(RawString(text))
        it("should still be the RawString") {
            verbs.checkLazily(result) { isSame(text) }
        }
    }

    describe("format a ${TranslatableRawString::class.simpleName}") {
        val result = testee.format(TranslatableRawString(translatable))
        it("should still be the RawString") {
            verbs.checkImmediately(result).isSame(translatedText)
        }
    }
})
