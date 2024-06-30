//TODO 1.4.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.specs.reporting

import ch.tutteli.atrium.api.fluent.en_GB.toContain
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.impl.AssertionFormatterControllerBasedFacade
import ch.tutteli.atrium.reporting.impl.DefaultAssertionFormatterController
import ch.tutteli.atrium.specs.describeFunTemplate
import ch.tutteli.atrium.specs.lineSeparator
import ch.tutteli.atrium.specs.toEqualDescr
import ch.tutteli.atrium.translations.DescriptionAnyExpectation.TO_BE_THE_INSTANCE
import ch.tutteli.atrium.translations.DescriptionAnyExpectation.TO_EQUAL
import org.spekframework.spek2.style.specification.Suite
import kotlin.reflect.KClass

abstract class TextFallbackAssertionFormatterSpec(
    testeeFactory: (Map<KClass<out BulletPointIdentifier>, String>, AssertionFormatterController, ObjectFormatter) -> AssertionFormatter,
    describePrefix: String = "[Atrium] "
) : AssertionFormatterSpecBase({

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val testee = testeeFactory(
        bulletPoints,
        DefaultAssertionFormatterController(),
        ToStringObjectFormatter
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
                    // TODO 1.3.0 replace with representable and remove suppression
                    @Suppress("DEPRECATION")
                    override val description = ch.tutteli.atrium.reporting.translating.Untranslatable("outer group")
                    override val representation = "subject of outer group"
                    override val assertions = emptyList<Assertion>()
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
                    toContain("false")
                    toContain("Unsupported type ${unsupportedAssertion::class.fullName}")
                }
            }
        }
        context("assertion of type ${DescriptiveAssertion::class.simpleName}") {
            it("writes ${DescriptiveAssertion::description.name} and ${DescriptiveAssertion::representation.name} on the same line separated by colon and space") {
                val assertion =
                    assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(TO_BE_THE_INSTANCE, "bli").build()
                testee.formatNonGroup(assertion, parameterObject)
                expect(sb.toString()).toEqual("$lineSeparator${TO_BE_THE_INSTANCE.getDefault()}: bli")
            }
        }
        context("assertion of type ${RepresentationOnlyAssertion::class.simpleName}") {
            it("writes ${RepresentationOnlyAssertion::representation.name} without any additional colon or such") {
                val assertion = assertionBuilder.representationOnly.failing.withRepresentation("hello").build()
                testee.formatNonGroup(assertion, parameterObject)
                expect(sb.toString()).toEqual("${lineSeparator}hello")
            }
        }
    }

    describeFun(testee::formatGroup.name) {
        context("${AssertionGroup::class.simpleName} with type ${RootAssertionGroupType::class.simpleName} with multiple assertions") {
            val facade = AssertionFormatterControllerBasedFacade(DefaultAssertionFormatterController())
            facade.register {
                testeeFactory(bulletPoints, it, ToStringObjectFormatter)
            }

            context("only ${DescriptiveAssertion::class.simpleName}") {
                it("uses the system line separator to separate the assertions") {
                    facade.format(
                        object : AssertionGroup {
                            override val type = RootAssertionGroupType
                            // TODO 1.3.0 replace with representable and remove suppression
                            @Suppress("DEPRECATION")
                            override val description =  ch.tutteli.atrium.reporting.translating.Untranslatable("group")
                            override val representation = "subject of group"
                            override val assertions = listOf(
                                assertionBuilder.descriptive.failing
                                    .withDescriptionAndRepresentation(TO_BE_THE_INSTANCE, "b")
                                    .build(),
                                assertionBuilder.descriptive.failing
                                    .withDescriptionAndRepresentation(TO_EQUAL, "d")
                                    .build()
                            )
                        },
                        sb,
                        alwaysTrueAssertionFilter
                    )

                    expect(sb).toContain(
                        "group: subject of group$lineSeparator" +
                                "$bulletPoint ${TO_BE_THE_INSTANCE.getDefault()}: b$lineSeparator" +
                                "$bulletPoint $toEqualDescr: d"
                    )
                }
            }

            context("${AssertionGroup::class.simpleName} with an unsupported ${AssertionGroupType::class.simpleName} and an unsupported ${Assertion::class.simpleName}") {

                val indentBulletPoint = " ".repeat(bulletPoint.length + 1)
                it("uses the system line separator to separate the assertions") {
                    facade.format(
                        object : AssertionGroup {
                            override val type = RootAssertionGroupType
                            // TODO 1.3.0 replace with representable and remove suppression
                            @Suppress("DEPRECATION")
                            override val description =  ch.tutteli.atrium.reporting.translating.Untranslatable("outer group")
                            override val representation = "subject of outer group"
                            override val assertions = listOf(
                                object : AssertionGroup {
                                    override val type = object : AssertionGroupType {}
                                    // TODO 1.3.0 replace with representable and remove suppression
                                    @Suppress("DEPRECATION")
                                    override val description =  ch.tutteli.atrium.reporting.translating.Untranslatable("inner group")
                                    override val representation = "subject of inner group"
                                    override val assertions = listOf(
                                        assertionBuilder.descriptive.failing
                                            .withDescriptionAndRepresentation(TO_BE_THE_INSTANCE, "b")
                                            .build(),
                                        assertionBuilder.descriptive.failing
                                            .withDescriptionAndRepresentation(TO_EQUAL, "d")
                                            .build()
                                    )
                                },
                                unsupportedAssertion
                            )
                        },
                        sb,
                        alwaysTrueAssertionFilter
                    )

                    expect(sb).toContain(
                        "outer group: subject of outer group$lineSeparator" +
                            "$bulletPoint inner group: subject of inner group$lineSeparator" +
                            "$indentBulletPoint$bulletPoint ${TO_BE_THE_INSTANCE.getDefault()}: b$lineSeparator" +
                            "$indentBulletPoint$bulletPoint $toEqualDescr: d",
                        "$bulletPoint Unsupported type ${unsupportedAssertion::class.fullName}"
                    )
                }
            }
        }
    }
})
