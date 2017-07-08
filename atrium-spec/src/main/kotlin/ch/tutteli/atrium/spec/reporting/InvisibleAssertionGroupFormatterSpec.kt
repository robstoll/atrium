package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.reporting.IAssertionFormatter
import ch.tutteli.atrium.reporting.IAssertionFormatterController
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.toBe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

open class InvisibleAssertionGroupFormatterSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (IAssertionFormatterController) -> IAssertionFormatter
) : Spek({

    val facade = AtriumFactory.newAssertionFormatterFacade(AtriumFactory.newAssertionFormatterController())
    facade.register(testeeFactory)
    facade.register({ AtriumFactory.newSameLineAssertionFormatter(it, ToStringObjectFormatter(), UsingDefaultTranslator()) })

    var sb = StringBuilder()
    afterEachTest {
        sb = StringBuilder()
    }

    val assertions = listOf(
        BasicAssertion(AssertionVerb.ASSERT, 1, true),
        BasicAssertion(AssertionVerb.EXPECT_THROWN, 2, true)
    )
    val invisibleAssertionGroup = InvisibleAssertionGroup(assertions)

    val separator = System.getProperty("line.separator")!!

    describe("fun ${IAssertionFormatter::formatGroup.name}") {
        context("${IAssertionGroup::class.simpleName} of type ${IInvisibleAssertionGroupType::class.simpleName}") {
            context("format directly the group") {
                it("puts the assertions one under the others without indentation") {
                    facade.format(invisibleAssertionGroup, sb, alwaysTrueAssertionFilter)
                    verbs.checkImmediately(sb.toString()).toBe("${AssertionVerb.ASSERT.getDefault()}: 1"
                        + "$separator${AssertionVerb.EXPECT_THROWN.getDefault()}: 2")
                }
            }

            context("has ${IAssertionGroup::name.name} and ${IAssertionGroup::subject.name}") {
                it("still puts the assertions one under the others without indentation and does not include ${IAssertionGroup::name.name} or ${IAssertionGroup::subject.name}") {
                    facade.format(AssertionGroup(object : IInvisibleAssertionGroupType {}, AssertionVerb.ASSERT, 2, assertions), sb, alwaysTrueAssertionFilter)
                    verbs.checkImmediately(sb.toString()).toBe("${AssertionVerb.ASSERT.getDefault()}: 1"
                        + "$separator${AssertionVerb.EXPECT_THROWN.getDefault()}: 2")
                }
            }

            context("in an ${IAssertionGroup::class.simpleName} of type ${FeatureAssertionGroupType::class.simpleName}") {
                it("puts the assertions one under the others and indents as the other assertions") {
                    val featureAssertions = listOf(invisibleAssertionGroup, BasicAssertion(AssertionVerb.ASSERT, 20, false))
                    val featureAssertionGroup = AssertionGroup(FeatureAssertionGroupType, AssertionVerb.ASSERT, 10, featureAssertions)
                    facade.format(featureAssertionGroup, sb, alwaysTrueAssertionFilter)
                    verbs.checkImmediately(sb.toString()).toBe("-> ${AssertionVerb.ASSERT.getDefault()}: 10"
                        + "$separator   ${AssertionVerb.ASSERT.getDefault()}: 1"
                        + "$separator   ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2"
                        + "$separator   ${AssertionVerb.ASSERT.getDefault()}: 20")
                }
            }


        }
    }
})
