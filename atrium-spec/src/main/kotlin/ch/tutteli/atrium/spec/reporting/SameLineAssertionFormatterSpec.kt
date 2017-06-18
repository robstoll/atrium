package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.DescriptionAnyAssertion.IS_SAME
import ch.tutteli.atrium.DescriptionAnyAssertion.TO_BE
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.contains
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

    //TODO rewrite, some tests could be unit rather than integration tests
    val testee = AtriumFactory.newAssertionFormatterFacade(AtriumFactory.newAssertionFormatterController())
    testee.register({testeeFactory(it, ToStringObjectFormatter(), UsingDefaultTranslator())})

    val alwaysTrueAssertionFilter: (IAssertion) -> Boolean = { true }

    var sb = StringBuilder()
    afterEachTest {
        sb = StringBuilder()
    }

    context("unsupported ${IAssertion::class.simpleName}") {
        it("writes whether the assertion holds including a message telling the type is unsupported") {
            val assertion: IAssertion = object : IAssertion {
                override fun holds() = false
            }
            testee.format(assertion, sb, alwaysTrueAssertionFilter)
            verbs.checkLazily(sb.toString()) {
                contains("false")
                contains("Unsupported type ${assertion::class.java.name}")
            }
        }

        it("writes ${IBasicAssertion::description.name} and ${IBasicAssertion::representation.name} on the same line separated by colon and space") {
            val assertion = BasicAssertion(IS_SAME, "bli", false)
            testee.format(assertion, sb, alwaysTrueAssertionFilter)
            verbs.checkImmediately(sb.toString()).toBe("${IS_SAME.getDefault()}: bli")
        }

        val separator = System.getProperty("line.separator")!!
        it("uses the system line separator to separate multiple assertions in an ${IAssertionGroup::class.simpleName}") {
            testee.format(object : IAssertionGroup {
                override val type = RootAssertionGroupType
                override val name = TranslationSupplierSpec.TestTranslatable.DATE_KNOWN
                override val subject = sb
                override val assertions = listOf(
                    BasicAssertion(IS_SAME, "b", false),
                    BasicAssertion(TO_BE, "d", false)
                )
            }, sb, alwaysTrueAssertionFilter)

            verbs.checkImmediately(sb.toString()).contains("${IS_SAME.getDefault()}: b$separator"
                + "${TO_BE.getDefault()}: d")
        }
    }

})

