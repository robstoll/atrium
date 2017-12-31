package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.api.cc.en_UK.contains
import ch.tutteli.atrium.api.cc.en_UK.toBe
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.DescriptionAnyAssertion.IS_SAME
import ch.tutteli.atrium.assertions.DescriptionAnyAssertion.TO_BE
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it

abstract class TextFallbackAssertionFormatterSpec(
    verbs: AssertionVerbFactory,
    testeeFactory: (Map<Class<out BulletPointIdentifier>, String>, AssertionFormatterController, ObjectFormatter, Translator) -> AssertionFormatter,
    describePrefix: String = "[Atrium] "
) : AssertionFormatterSpecBase({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val testee = testeeFactory(bulletPoints, AtriumFactory.newAssertionFormatterController(), ToStringObjectFormatter, UsingDefaultTranslator())

    val unsupportedAssertion = object : Assertion {
        override fun holds() = false
    }

    describeFun(testee::canFormat.name) {
        group("check that it always returns true even for...") {
            it("... an anonymous class of ${Assertion::class.simpleName}") {
                testee.canFormat(unsupportedAssertion)
            }
            it("... an anonymous class of ${AssertionGroup::class.simpleName} with an anonymous ${AssertionGroupType::class.simpleName}") {
                testee.canFormat(object : AssertionGroup {
                    override val type = object : AssertionGroupType {}
                    override val name = Untranslatable("outer group")
                    override val subject = "subject of outer group"
                    override val assertions = listOf<Assertion>()
                }
                )
            }
        }
    }

    describeFun(testee::formatNonGroup.name) {

        context("unsupported ${Assertion::class.simpleName}") {
            it("writes whether the assertion holds including a message telling the type is unsupported") {
                testee.formatNonGroup(unsupportedAssertion, methodObject)
                verbs.checkLazily(sb) {
                    contains("false")
                    contains("Unsupported type ${unsupportedAssertion::class.java.name}")
                }
            }
        }
        context("assertion of type ${DescriptiveAssertion::class.simpleName}") {
            it("writes ${DescriptiveAssertion::description.name} and ${DescriptiveAssertion::expected.name} on the same line separated by colon and space") {
                val assertion = BasicDescriptiveAssertion(IS_SAME, "bli", false)
                testee.formatNonGroup(assertion, methodObject)
                verbs.checkImmediately(sb.toString()).toBe("$separator${IS_SAME.getDefault()}: bli")
            }
        }
    }

    describeFun(testee::formatGroup.name) {
        context("${AssertionGroup::class.simpleName} with type ${RootAssertionGroupType::class.simpleName} with multiple assertions") {
            val facade = AtriumFactory.newAssertionFormatterFacade(AtriumFactory.newAssertionFormatterController())
            facade.register({ testeeFactory(bulletPoints, it, ToStringObjectFormatter, UsingDefaultTranslator()) })

            context("only ${BasicDescriptiveAssertion::class.simpleName}") {
                it("uses the system line separator to separate the assertions") {
                    facade.format(object : AssertionGroup {
                        override val type = RootAssertionGroupType
                        override val name = Untranslatable("group")
                        override val subject = "subject of group"
                        override val assertions = listOf(
                            BasicDescriptiveAssertion(IS_SAME, "b", false),
                            BasicDescriptiveAssertion(TO_BE, "d", false)
                        )
                    }, sb, alwaysTrueAssertionFilter)

                    verbs.checkImmediately(sb).contains(
                        "group: subject of group$separator" +
                            "$bulletPoint ${IS_SAME.getDefault()}: b$separator" +
                            "$bulletPoint ${TO_BE.getDefault()}: d")
                }
            }

            context("${AssertionGroup::class.simpleName} with an unsupported ${AssertionGroupType::class.simpleName} and an unsupported ${Assertion::class.simpleName}") {

                val indentBulletPoint = " ".repeat(bulletPoint.length + 1)
                it("uses the system line separator to separate the assertions") {
                    facade.format(object : AssertionGroup {
                        override val type = RootAssertionGroupType
                        override val name = Untranslatable("outer group")
                        override val subject = "subject of outer group"
                        override val assertions = listOf(
                            object : AssertionGroup {
                                override val type = object : AssertionGroupType {}
                                override val name = Untranslatable("inner group")
                                override val subject = "subject of inner group"
                                override val assertions = listOf(
                                    BasicDescriptiveAssertion(IS_SAME, "b", false),
                                    BasicDescriptiveAssertion(TO_BE, "d", false)
                                )
                            },
                            unsupportedAssertion
                        )
                    }, sb, alwaysTrueAssertionFilter)

                    verbs.checkImmediately(sb).contains(
                        "outer group: subject of outer group$separator" +
                            "$bulletPoint inner group: subject of inner group$separator" +
                            "$indentBulletPoint$bulletPoint ${IS_SAME.getDefault()}: b$separator" +
                            "$indentBulletPoint$bulletPoint ${TO_BE.getDefault()}: d",
                        "$bulletPoint Unsupported type ${unsupportedAssertion::class.java.name}"
                    )
                }
            }
        }
    }
})
