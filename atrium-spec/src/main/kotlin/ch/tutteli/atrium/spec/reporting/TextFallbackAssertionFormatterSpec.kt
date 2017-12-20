package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.api.cc.en_UK.contains
import ch.tutteli.atrium.api.cc.en_UK.toBe
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.DescriptionAnyAssertion.IS_SAME
import ch.tutteli.atrium.assertions.DescriptionAnyAssertion.TO_BE
import ch.tutteli.atrium.reporting.IAssertionFormatter
import ch.tutteli.atrium.reporting.IAssertionFormatterController
import ch.tutteli.atrium.reporting.IObjectFormatter
import ch.tutteli.atrium.reporting.translating.ITranslator
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.prefixedDescribe
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it

abstract class TextFallbackAssertionFormatterSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (Map<Class<out IBulletPointIdentifier>, String>, IAssertionFormatterController, IObjectFormatter, ITranslator) -> IAssertionFormatter,
    describePrefix: String = "[Atrium] "
) : AssertionFormatterSpecBase({

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit) {
        prefixedDescribe(describePrefix, description, body)
    }

    val testee = testeeFactory(bulletPoints, AtriumFactory.newAssertionFormatterController(), ToStringObjectFormatter, UsingDefaultTranslator())

    val unsupportedAssertion = object : IAssertion {
        override fun holds() = false
    }

    prefixedDescribe("fun ${testee::canFormat.name}") {
        group("check that it always returns true even for...") {
            it("... an anonymous class of ${IAssertion::class.simpleName}") {
                testee.canFormat(unsupportedAssertion)
            }
            it("... an anonymous class of ${IAssertionGroup::class.simpleName} with an anonymous ${IAssertionGroupType::class.simpleName}") {
                testee.canFormat(object : IAssertionGroup {
                    override val type = object : IAssertionGroupType {}
                    override val name = Untranslatable("outer group")
                    override val subject = "subject of outer group"
                    override val assertions = listOf<IAssertion>()
                }
                )
            }
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
        }
        context("assertion of type ${IBasicAssertion::class.simpleName}") {
            it("writes ${IBasicAssertion::description.name} and ${IBasicAssertion::expected.name} on the same line separated by colon and space") {
                val assertion = BasicAssertion(IS_SAME, "bli", false)
                testee.format(assertion, methodObject)
                verbs.checkImmediately(sb.toString()).toBe("$separator${IS_SAME.getDefault()}: bli")
            }
        }

        context("${IAssertionGroup::class.simpleName} with type ${RootAssertionGroupType::class.simpleName} with multiple assertions") {
            val facade = AtriumFactory.newAssertionFormatterFacade(AtriumFactory.newAssertionFormatterController())
            facade.register({ testeeFactory(bulletPoints, it, ToStringObjectFormatter, UsingDefaultTranslator()) })

            context("only ${BasicAssertion::class.simpleName}") {
                it("uses the system line separator to separate the assertions") {
                    facade.format(object : IAssertionGroup {
                        override val type = RootAssertionGroupType
                        override val name = Untranslatable("group")
                        override val subject = "subject of group"
                        override val assertions = listOf(
                            BasicAssertion(IS_SAME, "b", false),
                            BasicAssertion(TO_BE, "d", false)
                        )
                    }, sb, alwaysTrueAssertionFilter)

                    verbs.checkImmediately(sb).contains(
                        "group: subject of group$separator" +
                            "$bulletPoint ${IS_SAME.getDefault()}: b$separator" +
                            "$bulletPoint ${TO_BE.getDefault()}: d")
                }
            }

            context("${IAssertionGroup::class.simpleName} with an unsupported ${IAssertionGroupType::class.simpleName} and an unsupported ${IAssertion::class.simpleName}") {

                val indentBulletPoint = " ".repeat(bulletPoint.length + 1)
                it("uses the system line separator to separate the assertions") {
                    facade.format(object : IAssertionGroup {
                        override val type = RootAssertionGroupType
                        override val name = Untranslatable("outer group")
                        override val subject = "subject of outer group"
                        override val assertions = listOf(
                            object : IAssertionGroup {
                                override val type = object : IAssertionGroupType {}
                                override val name = Untranslatable("inner group")
                                override val subject = "subject of inner group"
                                override val assertions = listOf(
                                    BasicAssertion(IS_SAME, "b", false),
                                    BasicAssertion(TO_BE, "d", false)
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
