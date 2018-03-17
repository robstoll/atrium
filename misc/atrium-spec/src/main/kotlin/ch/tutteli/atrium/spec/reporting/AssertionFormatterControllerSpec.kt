package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.api.cc.en_UK.toBe
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.assertions.builders.root
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.domain.builders.assertions.builders.fixHoldsGroup
import ch.tutteli.atrium.domain.builders.creating.AssertImpl
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.AssertionFormatterParameterObject
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.translations.DescriptionComparableAssertion.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it

abstract class AssertionFormatterControllerSpec(
    verbs: AssertionVerbFactory,
    testeeFactory: () -> AssertionFormatterController,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val testee = testeeFactory()
    val arrow = "  >>"
    val warning = "  !!"
    val bulletPoint = "*"
    val listBulletPoint = "=="
    val bulletPoints = mapOf<Class<out BulletPointIdentifier>, String>(
        ExplanatoryAssertionGroupType::class.java to "$arrow ",
        WarningAssertionGroupType::class.java to "$warning ",
        ListAssertionGroupType::class.java to "$listBulletPoint ",
        RootAssertionGroupType::class.java to "$bulletPoint "
    )

    val indentArrow = " ".repeat(arrow.length + 1)
    val indentBulletPoint = " ".repeat(bulletPoint.length + 1)

    testee.register(coreFactory.newTextExplanatoryAssertionGroupFormatter(bulletPoints, testee))
    testee.register(coreFactory.newTextListAssertionGroupFormatter(bulletPoints, testee, ToStringObjectFormatter, UsingDefaultTranslator()))
    testee.register(coreFactory.newTextFallbackAssertionFormatter(bulletPoints, testee, ToStringObjectFormatter, UsingDefaultTranslator()))

    val assertion = AssertImpl.builder.descriptive.create(IS_GREATER_OR_EQUALS, 1, true)
    val failingAssertion = AssertImpl.builder.descriptive.create(IS_LESS_OR_EQUALS, 2, false)

    val separator = System.getProperty("line.separator")!!

    describeFun(testee::format.name) {

        context("assertionFilter which always returns `false`") {
            var sb = StringBuilder()
            var parameterObject = AssertionFormatterParameterObject.new(sb, alwaysFalseAssertionFilter)
            afterEachTest {
                sb = StringBuilder()
                parameterObject = AssertionFormatterParameterObject.new(sb, alwaysFalseAssertionFilter)
            }

            val anonymousType = object : ExplanatoryAssertionGroupType {}

            listOf<Pair<String, (ExplanatoryAssertionGroupType, List<Assertion>) -> AssertionGroup>>(
                "${AssertionBuilder::class.simpleName}.${AssertionBuilder::explanatoryGroup.name}.${AssertImpl.builder.explanatoryGroup::withType.name}(t)" to { t, a -> AssertImpl.builder.explanatoryGroup.withType(t).create(a) },
                "${AssertionBuilder::class.simpleName}.withType(t)" to { t, a -> AssertImpl.builder.withType(t,AssertionVerb.VERB, 1).create(a) },
                "${AssertionBuilder::class.simpleName}.${AssertionBuilder::fixHoldsGroup.name}" to { t, a -> AssertImpl.builder.fixHoldsGroup.create(AssertionVerb.VERB, 1, false, t, a) }
            ).forEach { (groupName, factory) ->
                listOf(
                    Triple(
                        "$groupName with type object: ${ExplanatoryAssertionGroupType::class.simpleName}",
                        factory(anonymousType, listOf(assertion)) to factory(anonymousType, listOf(failingAssertion)),
                        arrow
                    ),
                    Triple(
                        "$groupName with type ${DefaultExplanatoryAssertionGroupType::class.simpleName}",
                        factory(DefaultExplanatoryAssertionGroupType, listOf(assertion)) to factory(DefaultExplanatoryAssertionGroupType, listOf(failingAssertion)),
                        arrow
                    ),
                    Triple(
                        "$groupName with type ${WarningAssertionGroupType::class.simpleName}",
                        factory(WarningAssertionGroupType, listOf(assertion)) to factory(WarningAssertionGroupType, listOf(failingAssertion)),
                        warning
                    )
                ).forEach { (description, factories, prefix) ->
                    val (holdingGroup, failingGroup) = factories
                    context(description) {
                        it("appends the assertions without group header, if the assertion group holds") {
                            testee.format(holdingGroup, parameterObject)
                            verbs.checkImmediately(sb.toString()).toBe(separator +
                                "$prefix ${IS_GREATER_OR_EQUALS.getDefault()}: 1")
                        }

                        it("appends the assertions without group header, if the assertion group does not hold") {
                            testee.format(failingGroup, parameterObject)
                            verbs.checkImmediately(sb.toString()).toBe(separator +
                                "$prefix ${IS_LESS_OR_EQUALS.getDefault()}: 2")
                        }
                    }
                }
            }
        }

        context("assertionFilter which returns `false` except for the RootAssertionGroup") {
            val onlyRootAssertionGroup: (Assertion) -> Boolean = { it is AssertionGroup && it.type == RootAssertionGroupType }
            var sb = StringBuilder()
            var parameterObject = AssertionFormatterParameterObject.new(sb, onlyRootAssertionGroup)
            afterEachTest {
                sb = StringBuilder()
                parameterObject = AssertionFormatterParameterObject.new(sb, onlyRootAssertionGroup)
            }

            context("first an ${ExplanatoryAssertionGroupType::class.simpleName} and then a regular assertion") {
                val rootGroup = AssertImpl.builder.root(AssertionVerb.ASSERT, 5).create(
                    AssertImpl.builder.explanatoryGroup.withDefault.create(listOf(assertion)),
                    assertion
                )

                it("appends only the explanatory assertion group") {
                    testee.format(rootGroup, parameterObject)
                    verbs.checkImmediately(sb.toString()).toBe("${AssertionVerb.ASSERT.getDefault()}: 5$separator" +
                        "$indentBulletPoint$arrow ${IS_GREATER_OR_EQUALS.getDefault()}: 1")
                }
            }

            context("first a regular assertion, then an ${ExplanatoryAssertionGroupType::class.simpleName} and finally a regular assertion again") {
                val rootGroup = AssertImpl.builder.root(AssertionVerb.ASSERT, 5).create(listOf(
                    assertion,
                    AssertImpl.builder.explanatoryGroup.withWarning.create(assertion),
                    assertion
                ))

                it("appends only the explanatory assertion group") {
                    testee.format(rootGroup, parameterObject)
                    verbs.checkImmediately(sb.toString()).toBe("${AssertionVerb.ASSERT.getDefault()}: 5$separator" +
                        "$indentBulletPoint$warning ${IS_GREATER_OR_EQUALS.getDefault()}: 1")
                }
            }

            context("an assertion group with assertions within an ${ExplanatoryAssertionGroupType::class.simpleName}") {
                val assertionGroup = AssertImpl.builder.list(AssertionVerb.EXPECT_THROWN, 2).create(listOf(assertion, failingAssertion))
                val explanatoryAssertionGroup = AssertImpl.builder.explanatoryGroup.withDefault.create(listOf(assertionGroup, assertion))
                val rootGroup = AssertImpl.builder.root(AssertionVerb.ASSERT, 5).create(explanatoryAssertionGroup)

                it("appends the explanatory assertion group including all its assertions") {
                    testee.format(rootGroup, parameterObject)
                    verbs.checkImmediately(sb.toString()).toBe("${AssertionVerb.ASSERT.getDefault()}: 5$separator" +
                        "$indentBulletPoint$arrow ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator" +
                        "$indentBulletPoint$indentArrow$listBulletPoint ${IS_GREATER_OR_EQUALS.getDefault()}: 1$separator" +
                        "$indentBulletPoint$indentArrow$listBulletPoint ${IS_LESS_OR_EQUALS.getDefault()}: 2$separator" +
                        "$indentBulletPoint$arrow ${IS_GREATER_OR_EQUALS.getDefault()}: 1")
                }

                context("within another ${ExplanatoryAssertionGroupType::class.simpleName} which is preceded and followed by a regular assertion ") {
                    val explanatoryAssertionGroup2 = AssertImpl.builder.explanatoryGroup.withWarning.create(listOf(explanatoryAssertionGroup))
                    val rootGroup2 = AssertImpl.builder.root(IS_LESS_THAN, 10).create(listOf(failingAssertion, explanatoryAssertionGroup2, assertion))

                    it("appends the explanatory assertion group including all its assertions") {
                        testee.format(rootGroup2, parameterObject)
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
