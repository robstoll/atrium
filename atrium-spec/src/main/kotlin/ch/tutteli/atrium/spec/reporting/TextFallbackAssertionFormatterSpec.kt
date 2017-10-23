package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.api.cc.en_UK.contains
import ch.tutteli.atrium.api.cc.en_UK.containsNot
import ch.tutteli.atrium.api.cc.en_UK.toBe
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.DescriptionAnyAssertion.IS_SAME
import ch.tutteli.atrium.assertions.DescriptionAnyAssertion.TO_BE
import ch.tutteli.atrium.reporting.AssertionFormatterMethodObject
import ch.tutteli.atrium.reporting.IAssertionFormatter
import ch.tutteli.atrium.reporting.IAssertionFormatterController
import ch.tutteli.atrium.reporting.IObjectFormatter
import ch.tutteli.atrium.reporting.translating.ITranslator
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.prefixedDescribe
import ch.tutteli.atrium.spec.reporting.translating.TranslationSupplierSpec
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it

abstract class TextFallbackAssertionFormatterSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (IAssertionFormatterController, IObjectFormatter, ITranslator) -> IAssertionFormatter,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit) {
        prefixedDescribe(describePrefix, description, body)
    }

    val testee = testeeFactory(AtriumFactory.newAssertionFormatterController(), ToStringObjectFormatter, UsingDefaultTranslator())

    var sb = StringBuilder()
    var methodObject = AssertionFormatterMethodObject.new(sb, alwaysTrueAssertionFilter)
    afterEachTest {
        sb = StringBuilder()
        methodObject = AssertionFormatterMethodObject.new(sb, alwaysTrueAssertionFilter)
    }

    val squarePoint = "▪"

    val unsupportedAssertion = object : IAssertion {
        override fun holds() = false
    }

    prefixedDescribe("fun ${testee::canFormat.name}") {
        it("returns always true even for an anonymous class of ${IAssertion::class.simpleName}") {
            testee.canFormat(unsupportedAssertion)
        }
    }

    prefixedDescribe("fun ${testee::format.name}") {

        context("unsupported ${IAssertion::class.simpleName}") {
            it("writes whether the assertion holds including a message telling the type is unsupported") {
                testee.format(unsupportedAssertion, methodObject)
                verbs.checkLazily(sb) {
                    contains("false")
                    contains("Unsupported type ${unsupportedAssertion::class.java.name}")
                }
            }

            it("does not add newlines (still same line") {
                testee.format(unsupportedAssertion, methodObject)
                verbs.checkLazily(sb) {
                    containsNot("\r")
                    containsNot("\n")
                }
            }
        }
        context("assertion of type ${IBasicAssertion::class.simpleName}") {
            it("writes ${IBasicAssertion::description.name} and ${IBasicAssertion::expected.name} on the same line separated by colon and space") {
                val assertion = BasicAssertion(IS_SAME, "bli", false)
                testee.format(assertion, methodObject)
                verbs.checkImmediately(sb.toString()).toBe("${IS_SAME.getDefault()}: bli")
            }
        }

        context("${IAssertionGroup::class.simpleName} with multiple assertions") {
            val facade = AtriumFactory.newAssertionFormatterFacade(AtriumFactory.newAssertionFormatterController())
            facade.register({ testeeFactory(it, ToStringObjectFormatter, UsingDefaultTranslator()) })
            val separator = System.getProperty("line.separator")!!
            it("uses the system line separator to separate the assertions") {
                facade.format(object : IAssertionGroup {
                    override val type = RootAssertionGroupType
                    override val name = TranslationSupplierSpec.TestTranslatable.DATE_KNOWN
                    override val subject = sb
                    override val assertions = listOf(
                        BasicAssertion(IS_SAME, "b", false),
                        BasicAssertion(TO_BE, "d", false)
                    )
                }, sb, alwaysTrueAssertionFilter)

                verbs.checkImmediately(sb).contains("${IS_SAME.getDefault()}: b$separator$squarePoint ${TO_BE.getDefault()}: d")
            }
        }
    }
})
