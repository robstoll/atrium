package ch.tutteli.atrium.spec.reporting.translating

import ch.tutteli.atrium.*
import ch.tutteli.atrium.reporting.IReporter
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

/**
 * If you use this Spec then your reporter needs to use a translator which uses the following translations
 * which should be in
 *
 * the primary local: de_CH
 * ch.tutteli.atrium.DescriptionAnyAssertion-TO_BE = ist
 *
 * the primary Locale's natural first fallback: de
 * ch.tutteli.atrium.DescriptionAnyAssertion-NOT_TO_BE = ist nicht
 *
 * the Locale.ROOT
 * ch.tutteli.atrium.DescriptionAnyAssertion-IS_SAME = ist dieselbe Instanz wie
 *
 * the fallback Locale: fr
 * ch.tutteli.atrium.spec.AssertionVerb-ASSERT = il applique que
 *
 * the Locale it:
 * ch.tutteli.atrium.DescriptionNumberAssertion-IS_LESS_THAN = è meno di
 */
abstract class TranslationSupplierSpec(
    verbs: IAssertionVerbFactory,
    reporter: IReporter
) : Spek({

    fun <T : Any> assert(subject: T)
        = AtriumFactory.newCheckImmediately(AssertionVerb.ASSERT, subject, reporter)

    describe("primary locale is 'de_CH' and fallback is 'fr'") {

        describe("translation for ${DescriptionAnyAssertion::class.simpleName}.${DescriptionAnyAssertion.TO_BE} is provided for 'de_CH'") {
            it("a failing assertion contains 'ist' instead of 'to be' in the error message") {
                verbs.checkException {
                    assert(1).toBe(2)
                }.toThrow<AssertionError>().and.message.contains("ist: 2")
            }
        }

        describe("translation for ${DescriptionAnyAssertion::class.simpleName}.${DescriptionAnyAssertion.NOT_TO_BE} is provided for 'de'") {
            val text = "ist nicht"
            it("a failing assertion contains '$text' instead of 'not to be' in the error message") {
                verbs.checkException {
                    assert(1).notToBe(1)
                }.toThrow<AssertionError>().and.message.contains("$text: 1")
            }
        }

        describe("translation for ${DescriptionAnyAssertion::class.simpleName}.${DescriptionAnyAssertion.IS_SAME} is provided for 'Locale.ROOT'") {
            val text = "ist dieselbe Instanz wie"
            it("a failing assertion contains '$text' instead of 'is same as' in the error message") {
                verbs.checkException {
                    assert(1).isSame(2)
                }.toThrow<AssertionError>().and.message.contains("$text: 2")
            }
        }

        describe("translation for ${AssertionVerb::class.simpleName}.${AssertionVerb.ASSERT} is provided for 'fr'") {
            val text = "il applique que"
            it("a failing assertion contains '$text' instead of 'assert' in the error message") {
                verbs.checkException {
                    assert(1).toBe(2)
                }.toThrow<AssertionError>().and.message.contains("$text: 1")
            }
        }

        describe("translation for ${DescriptionNumberAssertion::class.simpleName}.${DescriptionNumberAssertion.IS_LESS_THAN} is provided for 'it'") {
            it("throws an AssertionError which message contains the default of ${DescriptionNumberAssertion::class.simpleName}.${DescriptionNumberAssertion.IS_LESS_THAN}") {
                verbs.checkException {
                    assert(1).isLessThan(1)
                }.toThrow<AssertionError>().and.message.contains("${DescriptionNumberAssertion.IS_LESS_THAN.getDefault()}: 1")
            }
        }

    }
})
