package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.*
import ch.tutteli.atrium.reporting.ReporterBuilder
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import java.util.*

class PropertiesPerLocaleTranslationProviderSpec : Spek({

    val builder = ReporterBuilder.withTranslations(PropertiesPerLocaleTranslationProvider(), Locale("de", "CH", "Sensler"))
        .withDetailedObjectFormatter()
        .withSameLineAssertionMessageFormatter()
        .buildOnlyFailureReporting()

    fun <T : Any> assert(subject: T)
        = AtriumFactory.newCheckImmediately("assert", subject, builder)

    describe("providing only the translation for ${DescriptionAnyAssertion::class.qualifiedName}") {
        context("making an assertion which fails and uses ${DescriptionAnyAssertion::class.simpleName}.${DescriptionAnyAssertion.TO_BE}") {
            it("throws an AssertionError which message contains 'isch' instead of 'to be'") {
                expect {
                    assert(1).toBe(2)
                }.toThrow<AssertionError>().and.message.contains("isch: 2")
            }
        }
        context("making an assertion which fails and uses ${DescriptionAnyAssertion::class.simpleName}.${DescriptionAnyAssertion.NOT_TO_BE}") {
            it("throws an AssertionError which message contains 'ischa nid' instead of 'to be'") {
                expect {
                    assert(1).notToBe(1)
                }.toThrow<AssertionError>().and.message.contains("ischa nid: 1")
            }
        }
        context("making an assertion which fails and uses ${DescriptionNumberAssertion::class.simpleName}.${DescriptionNumberAssertion.IS_SMALLER_THAN}") {
            it("throws an AssertionError which message contains the default of ${DescriptionNumberAssertion::class.simpleName}.${DescriptionNumberAssertion.IS_SMALLER_THAN}") {
                expect {
                    assert(1).isSmallerThan(1)
                }.toThrow<AssertionError>().and.message.contains("${DescriptionNumberAssertion.IS_SMALLER_THAN.getDefault()}: 1")
            }
        }
    }

})
