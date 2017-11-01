package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.api.cc.en_UK.toBe
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.DescriptionNumberAssertion.*
import ch.tutteli.atrium.reporting.AssertionFormatterMethodObject
import ch.tutteli.atrium.reporting.IAssertionFormatterController
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.prefixedDescribe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it

abstract class AssertionFormatterControllerSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: () -> IAssertionFormatterController,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit) {
        prefixedDescribe(describePrefix, description, body)
    }

    val testee = testeeFactory()
    val arrow = "  >>"
    val bulletPoint = "*"
    val listBulletPoint = "=="
    val bulletPoints = mapOf<Class<out IBulletPointIdentifier>, String>(
        IExplanatoryAssertionGroupType::class.java to "$arrow ",
        IListAssertionGroupType::class.java to "$listBulletPoint ",
        RootAssertionGroupType::class.java to "$bulletPoint "

    )

    val indentArrow = " ".repeat(arrow.length + 1)
    val indentBulletPoint = " ".repeat(bulletPoint.length + 1)

    testee.register(AtriumFactory.newTextExplanatoryAssertionGroupFormatter(bulletPoints, testee))
    testee.register(AtriumFactory.newTextListAssertionGroupFormatter(bulletPoints, testee, ToStringObjectFormatter, UsingDefaultTranslator()))
    testee.register(AtriumFactory.newTextFallbackAssertionFormatter(bulletPoints, testee, ToStringObjectFormatter, UsingDefaultTranslator()))

    val assertion = BasicAssertion(IS_GREATER_OR_EQUALS, 1, true)
    val failingAssertion = BasicAssertion(IS_LESS_OR_EQUALS, 2, false)

    val separator = System.getProperty("line.separator")!!

    prefixedDescribe("fun ${testee::format}") {

        context("assertionFilter which always returns `false`") {
            var sb = StringBuilder()
            var methodObject = AssertionFormatterMethodObject.new(sb, alwaysFalseAssertionFilter)
            afterEachTest {
                sb = StringBuilder()
                methodObject = AssertionFormatterMethodObject.new(sb, alwaysFalseAssertionFilter)
            }

            listOf(
                Triple(
                    "${IAssertionGroup::class.simpleName} is of type ${IExplanatoryAssertionGroupType::class.simpleName}",
                    AssertionGroup(ExplanatoryAssertionGroupType, AssertionVerb.VERB, 1, listOf(assertion)),
                    AssertionGroup(ExplanatoryAssertionGroupType, AssertionVerb.VERB, 1, listOf(failingAssertion))
                ),
                Triple(
                    "${ExplanatoryAssertionGroup::class.simpleName}",
                    ExplanatoryAssertionGroup(listOf(assertion)),
                    ExplanatoryAssertionGroup(listOf(failingAssertion))
                )
            ).forEach { (description, holdingGroup, failingGroup) ->
                context(description) {
                    it("appends the assertions without group header, if the assertion group holds") {
                        testee.format(holdingGroup, methodObject)
                        verbs.checkImmediately(sb.toString()).toBe(separator +
                            "$arrow ${IS_GREATER_OR_EQUALS.getDefault()}: 1")
                    }

                    it("appends the assertions without group header, if the assertion group does not hold") {
                        testee.format(failingGroup, methodObject)
                        verbs.checkImmediately(sb.toString()).toBe(separator +
                            "$arrow ${IS_LESS_OR_EQUALS.getDefault()}: 2")
                    }
                }
            }
        }

        context("assertionFilter which returns `false` except for the RootAssertionGroup") {
            val onlyRootAssertionGroup: (IAssertion) -> Boolean = { it is IAssertionGroup && it.type is RootAssertionGroupType }
            var sb = StringBuilder()
            var methodObject = AssertionFormatterMethodObject.new(sb, onlyRootAssertionGroup)
            afterEachTest {
                sb = StringBuilder()
                methodObject = AssertionFormatterMethodObject.new(sb, onlyRootAssertionGroup)
            }

            context("first an ${IExplanatoryAssertionGroupType::class.simpleName} and then a regular assertion") {
                val rootGroup = AssertionGroup(RootAssertionGroupType, AssertionVerb.ASSERT, 5, listOf(
                    ExplanatoryAssertionGroup(listOf(assertion)),
                    assertion
                ))

                it("appends only the explanatory assertion group") {
                    testee.format(rootGroup, methodObject)
                    verbs.checkImmediately(sb.toString()).toBe("${AssertionVerb.ASSERT.getDefault()}: 5$separator" +
                        "$indentBulletPoint$arrow ${IS_GREATER_OR_EQUALS.getDefault()}: 1")
                }
            }

            context("first a regular assertion, then an ${IExplanatoryAssertionGroupType::class.simpleName} and finally a regular assertion again") {
                val rootGroup = AssertionGroup(RootAssertionGroupType, AssertionVerb.ASSERT, 5, listOf(
                    assertion,
                    ExplanatoryAssertionGroup(listOf(assertion)),
                    assertion
                ))

                it("appends only the explanatory assertion group") {
                    testee.format(rootGroup, methodObject)
                    verbs.checkImmediately(sb.toString()).toBe("${AssertionVerb.ASSERT.getDefault()}: 5$separator" +
                        "$indentBulletPoint$arrow ${IS_GREATER_OR_EQUALS.getDefault()}: 1")
                }
            }

            context("an assertion group with assertions within an ${IExplanatoryAssertionGroupType::class.simpleName}") {
                val assertionGroup = AssertionGroup(ListAssertionGroupType, AssertionVerb.EXPECT_THROWN, 2, listOf(assertion, failingAssertion))
                val explanatoryAssertionGroup = ExplanatoryAssertionGroup(listOf(assertionGroup, assertion))
                val rootGroup = AssertionGroup(RootAssertionGroupType, AssertionVerb.ASSERT, 5, listOf(explanatoryAssertionGroup))

                it("appends the explanatory assertion group including all its assertions") {
                    testee.format(rootGroup, methodObject)
                    verbs.checkImmediately(sb.toString()).toBe("${AssertionVerb.ASSERT.getDefault()}: 5$separator" +
                        "$indentBulletPoint$arrow ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator" +
                        "$indentBulletPoint$indentArrow$listBulletPoint ${IS_GREATER_OR_EQUALS.getDefault()}: 1$separator" +
                        "$indentBulletPoint$indentArrow$listBulletPoint ${IS_LESS_OR_EQUALS.getDefault()}: 2$separator" +
                        "$indentBulletPoint$arrow ${IS_GREATER_OR_EQUALS.getDefault()}: 1")
                }

                context("within another ${IExplanatoryAssertionGroupType::class.simpleName} which is preeceded and followed by a regular assertion ") {
                    val explanatoryAssertionGroup2 = ExplanatoryAssertionGroup(listOf(explanatoryAssertionGroup))
                    val rootGroup2 = AssertionGroup(RootAssertionGroupType, IS_LESS_THAN, 10, listOf(failingAssertion, explanatoryAssertionGroup2, assertion))

                    it("appends the explanatory assertion group including all its assertions") {
                        testee.format(rootGroup2, methodObject)
                        verbs.checkImmediately(sb.toString()).toBe("${IS_LESS_THAN.getDefault()}: 10$separator" +
                            "$indentBulletPoint$indentArrow$arrow ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator" +
                            "$indentBulletPoint$indentArrow$indentArrow$listBulletPoint ${IS_GREATER_OR_EQUALS.getDefault()}: 1$separator" +
                            "$indentBulletPoint$indentArrow$indentArrow$listBulletPoint ${IS_LESS_OR_EQUALS.getDefault()}: 2$separator" +
                            "$indentBulletPoint$indentArrow$arrow ${IS_GREATER_OR_EQUALS.getDefault()}: 1")
                    }
                }
            }
        }
    }
})
