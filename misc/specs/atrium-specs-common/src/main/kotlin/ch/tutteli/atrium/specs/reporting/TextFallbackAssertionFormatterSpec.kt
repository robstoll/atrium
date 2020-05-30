package ch.tutteli.atrium.specs.reporting

import ch.tutteli.atrium.api.fluent.en_GB.contains
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.specs.lineSeperator
import ch.tutteli.atrium.specs.describeFunTemplate
import ch.tutteli.atrium.specs.toBeDescr
import ch.tutteli.atrium.translations.DescriptionAnyAssertion.IS_SAME
import ch.tutteli.atrium.translations.DescriptionAnyAssertion.TO_BE
import org.spekframework.spek2.style.specification.Suite
import kotlin.reflect.KClass

abstract class TextFallbackAssertionFormatterSpec(
    testeeFactory: (Map<KClass<out BulletPointIdentifier>, String>, AssertionFormatterController, ObjectFormatter, Translator) -> AssertionFormatter,
    describePrefix: String = "[Atrium] "
) : AssertionFormatterSpecBase({

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val testee = testeeFactory(
        bulletPoints, coreFactory.newAssertionFormatterController(),
        ToStringObjectFormatter, UsingDefaultTranslator()
    )

    val unsupportedAssertion = object : Assertion {
        override fun holds() = false
    }

    describeFun(testee::canFormat.name) {
        context("check that it always returns true even for...") {
            it("... an anonymous class of ${Assertion::class.simpleName}") {
                testee.canFormat(unsupportedAssertion)
            }
            it("... an anonymous class of ${AssertionGroup::class.simpleName} with an anonymous ${AssertionGroupType::class.simpleName}") {
                testee.canFormat(object : AssertionGroup {
                    override val type = object : AssertionGroupType {}
                    override val description = Untranslatable("outer group")
                    override val representation = "subject of outer group"
                    override val assertions = listOf<Assertion>()
                }
                )
            }
        }
    }

    describeFun(testee::formatNonGroup.name) {

        context("unsupported ${Assertion::class.simpleName}") {
            it("writes whether the assertion holds including a message telling the type is unsupported") {
                testee.formatNonGroup(unsupportedAssertion, parameterObject)
                expect(sb) {
                    contains("false")
                    contains("Unsupported type ${unsupportedAssertion::class.fullName}")
                }
            }
        }
        context("assertion of type ${DescriptiveAssertion::class.simpleName}") {
            it("writes ${DescriptiveAssertion::description.name} and ${DescriptiveAssertion::representation.name} on the same line separated by colon and space") {
                val assertion =
                    ExpectImpl.builder.descriptive.failing.withDescriptionAndRepresentation(IS_SAME, "bli").build()
                testee.formatNonGroup(assertion, parameterObject)
                expect(sb.toString()).toBe("$lineSeperator${IS_SAME.getDefault()}: bli")
            }
        }
        context("assertion of type ${RepresentationOnlyAssertion::class.simpleName}") {
            it("writes ${RepresentationOnlyAssertion::representation.name} without any additional colon or such") {
                val assertion = ExpectImpl.builder.representationOnly.failing.withRepresentation("hello").build()
                testee.formatNonGroup(assertion, parameterObject)
                expect(sb.toString()).toBe("${lineSeperator}hello")
            }
        }
    }

    describeFun(testee::formatGroup.name) {
        context("${AssertionGroup::class.simpleName} with type ${RootAssertionGroupType::class.simpleName} with multiple assertions") {
            val facade = coreFactory.newAssertionFormatterFacade(coreFactory.newAssertionFormatterController())
            facade.register {
                testeeFactory(bulletPoints, it, ToStringObjectFormatter, UsingDefaultTranslator())
            }

            context("only ${DescriptiveAssertion::class.simpleName}") {
                it("uses the system line separator to separate the assertions") {
                    facade.format(
                        object : AssertionGroup {
                            override val type = RootAssertionGroupType
                            override val description = Untranslatable("group")
                            override val representation = "subject of group"
                            override val assertions = listOf(
                                ExpectImpl.builder.descriptive.failing
                                    .withDescriptionAndRepresentation(IS_SAME, "b")
                                    .build(),
                                ExpectImpl.builder.descriptive.failing
                                    .withDescriptionAndRepresentation(TO_BE, "d")
                                    .build()
                            )
                        },
                        sb,
                        alwaysTrueAssertionFilter
                    )

                    expect(sb).contains(
                        "group: subject of group$lineSeperator" +
                            "$bulletPoint ${IS_SAME.getDefault()}: b$lineSeperator" +
                            "$bulletPoint $toBeDescr: d"
                    )
                }
            }

            context("${AssertionGroup::class.simpleName} with an unsupported ${AssertionGroupType::class.simpleName} and an unsupported ${Assertion::class.simpleName}") {

                val indentBulletPoint = " ".repeat(bulletPoint.length + 1)
                it("uses the system line separator to separate the assertions") {
                    facade.format(
                        object : AssertionGroup {
                            override val type = RootAssertionGroupType
                            override val description = Untranslatable("outer group")
                            override val representation = "subject of outer group"
                            override val assertions = listOf(
                                object : AssertionGroup {
                                    override val type = object : AssertionGroupType {}
                                    override val description = Untranslatable("inner group")
                                    override val representation = "subject of inner group"
                                    override val assertions = listOf(
                                        ExpectImpl.builder.descriptive.failing
                                            .withDescriptionAndRepresentation(IS_SAME, "b")
                                            .build(),
                                        ExpectImpl.builder.descriptive.failing
                                            .withDescriptionAndRepresentation(TO_BE, "d")
                                            .build()
                                    )
                                },
                                unsupportedAssertion
                            )
                        },
                        sb,
                        alwaysTrueAssertionFilter
                    )

                    expect(sb).contains(
                        "outer group: subject of outer group$lineSeperator" +
                            "$bulletPoint inner group: subject of inner group$lineSeperator" +
                            "$indentBulletPoint$bulletPoint ${IS_SAME.getDefault()}: b$lineSeperator" +
                            "$indentBulletPoint$bulletPoint $toBeDescr: d",
                        "$bulletPoint Unsupported type ${unsupportedAssertion::class.fullName}"
                    )
                }
            }
        }
    }
})
