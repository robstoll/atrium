package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.DescriptionAnyAssertion.IS_SAME
import ch.tutteli.atrium.DescriptionAnyAssertion.TO_BE
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.contains
import ch.tutteli.atrium.containsNot
import ch.tutteli.atrium.reporting.AssertionFormatterMethodObject
import ch.tutteli.atrium.reporting.IAssertionFormatter
import ch.tutteli.atrium.reporting.IAssertionFormatterController
import ch.tutteli.atrium.reporting.IObjectFormatter
import ch.tutteli.atrium.reporting.translating.ITranslator
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.reporting.translating.TranslationSupplierSpec
import ch.tutteli.atrium.toBe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it

open class SameLineAssertionFormatterSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (IAssertionFormatterController, IObjectFormatter, ITranslator) -> IAssertionFormatter
) : Spek({

    val testee = testeeFactory(AtriumFactory.newAssertionFormatterController(), ToStringObjectFormatter(), UsingDefaultTranslator())

    val alwaysTrueAssertionFilter: (IAssertion) -> Boolean = { true }

    var sb = StringBuilder()
    var methodObject = AssertionFormatterMethodObject(sb, 0, alwaysTrueAssertionFilter)
    afterEachTest {
        sb = StringBuilder()
        methodObject = AssertionFormatterMethodObject(sb, 0, alwaysTrueAssertionFilter)
    }

    val unsupportedAssertion = object : IAssertion {
        override fun holds() = false
    }
    context("fun ${testee::canFormat.name}") {
        it("returns always true even for an anonymous class of ${IAssertion::class.simpleName}") {
            testee.canFormat(unsupportedAssertion)
        }
    }

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
        it("writes ${IBasicAssertion::description.name} and ${IBasicAssertion::representation.name} on the same line separated by colon and space") {
            val assertion = BasicAssertion(IS_SAME, "bli", false)
            testee.format(assertion, methodObject)
            verbs.checkImmediately(sb.toString()).toBe("${IS_SAME.getDefault()}: bli")
        }
    }

    context("${IAssertionGroup::class.simpleName} with multiple assertions") {
        val facade = AtriumFactory.newAssertionFormatterFacade(AtriumFactory.newAssertionFormatterController())
        facade.register({ testeeFactory(it, ToStringObjectFormatter(), UsingDefaultTranslator()) })
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

            verbs.checkImmediately(sb).contains("${IS_SAME.getDefault()}: b$separator${TO_BE.getDefault()}: d")
        }
    }

})

