package ch.tutteli.atrium.specs.reporting

import ch.tutteli.atrium.api.fluent.en_GB.contains
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.specs.describeFun
import ch.tutteli.atrium.translations.DescriptionAnyAssertion.IS_SAME
import ch.tutteli.atrium.translations.DescriptionBasic.TO_BE
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import kotlin.reflect.KClass

//TODO #116 migrate spek1 to spek2 - move to specs-common
abstract class TextFallbackAssertionFormatterSpec(
    testeeFactory: (Map<KClass<out BulletPointIdentifier>, String>, AssertionFormatterController, ObjectFormatter, Translator) -> AssertionFormatter,
    describePrefix: String = "[Atrium] "
) : AssertionFormatterSpecBase({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit) =
        describeFun(describePrefix, funName, body = body)

    val testee = testeeFactory(
        bulletPoints, coreFactory.newAssertionFormatterController(),
        ToStringObjectFormatter, UsingDefaultTranslator()
    )

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
                    contains("Unsupported type ${unsupportedAssertion::class.java.name}")
                }
            }
        }
        context("assertion of type ${DescriptiveAssertion::class.simpleName}") {
            it("writes ${DescriptiveAssertion::description.name} and ${DescriptiveAssertion::representation.name} on the same line separated by colon and space") {
                val assertion =
                    assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(IS_SAME, "bli").build()
                testee.formatNonGroup(assertion, parameterObject)
                expect(sb.toString()).toBe("$separator${IS_SAME.getDefault()}: bli")
            }
        }
        context("assertion of type ${RepresentationOnlyAssertion::class.simpleName}") {
            it("writes ${RepresentationOnlyAssertion::representation.name} without any additional colon or such") {
                val assertion = assertionBuilder.representationOnly.failing.withRepresentation("hello").build()
                testee.formatNonGroup(assertion, parameterObject)
                expect(sb.toString()).toBe("${separator}hello")
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
                                assertionBuilder.descriptive.failing
                                    .withDescriptionAndRepresentation(IS_SAME, "b")
                                    .build(),
                                assertionBuilder.descriptive.failing
                                    .withDescriptionAndRepresentation(TO_BE, "d")
                                    .build()
                            )
                        },
                        sb,
                        alwaysTrueAssertionFilter
                    )

                    expect(sb).contains(
                        "group: subject of group$separator" +
                            "$bulletPoint ${IS_SAME.getDefault()}: b$separator" +
                            "$bulletPoint ${TO_BE.getDefault()}: d"
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
                                        assertionBuilder.descriptive.failing
                                            .withDescriptionAndRepresentation(IS_SAME, "b")
                                            .build(),
                                        assertionBuilder.descriptive.failing
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
