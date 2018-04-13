package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.api.cc.en_UK.toBe
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.spec.reporting.translating.TranslatorIntSpec
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it

abstract class TextListBasedAssertionGroupFormatterSpec<T : AssertionGroupType>(
    verbs: AssertionVerbFactory,
    testeeFactory: (Map<Class<out BulletPointIdentifier>, String>, AssertionFormatterController, ObjectFormatter, Translator) -> AssertionFormatter,
    assertionGroupClass: Class<T>,
    assertionGroupType: T,
    anonymousAssertionGroupType: T,
    describePrefix: String = "[Atrium] "
) : AssertionFormatterSpecBase({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assertions = listOf(
        AssertImpl.builder.descriptive.create(AssertionVerb.ASSERT, 1, true),
        AssertImpl.builder.descriptive.create(AssertionVerb.EXPECT_THROWN, 2, true)
    )
    val listAssertionGroup = AssertImpl.builder
        .withType(anonymousAssertionGroupType, TranslatorIntSpec.TestTranslatable.PLACEHOLDER, 2)
        .create(assertions)

    describeFun(AssertionFormatter::canFormat.name) {
        val testee = testeeFactory(bulletPoints, coreFactory.newAssertionFormatterController(), ToStringObjectFormatter, UsingDefaultTranslator())
        it("returns true for an ${AssertionGroup::class.simpleName} with type object: ${assertionGroupClass.simpleName}") {
            val result = testee.canFormat(AssertImpl.builder
                .withType(anonymousAssertionGroupType, Untranslatable.EMPTY, 1)
                .create(listOf())
            )
            verbs.checkImmediately(result).toBe(true)
        }
    }

    describeFun(AssertionFormatter::formatGroup.name) {

        mapOf(
            "•" to "▪",
            "[]" to "{}").forEach { (listBulletPoint, bulletPoint) ->
            val listIndent = " ".repeat(listBulletPoint.length + 1)
            val indent = " ".repeat(bulletPoint.length + 1)
            context("listBulletPoint: $listBulletPoint, bulletPoint: $bulletPoint") {
                val bulletPoints = mapOf(
                    RootAssertionGroupType::class.java to "$bulletPoint ",
                    ListAssertionGroupType::class.java to "$listBulletPoint ",
                    PrefixFeatureAssertionGroupHeader::class.java to "$arrow ",
                    FeatureAssertionGroupType::class.java to "$bulletPoint "
                )
                val facade = createFacade()
                facade.register({ testeeFactory(bulletPoints, it, ToStringObjectFormatter, UsingDefaultTranslator()) })
                facade.register({ coreFactory.newTextFeatureAssertionGroupFormatter(bulletPoints, it, ToStringObjectFormatter, UsingDefaultTranslator()) })
                facade.register({ coreFactory.newTextFallbackAssertionFormatter(bulletPoints, it, ToStringObjectFormatter, UsingDefaultTranslator()) })

                context("${AssertionGroup::class.simpleName} of type object: ${assertionGroupClass.simpleName}") {
                    context("format directly the group") {
                        it("includes the group ${AssertionGroup::name.name}, its ${AssertionGroup::representation.name} as well as the ${AssertionGroup::assertions.name} which are prepended with a `$listBulletPoint` as bullet point") {
                            facade.format(listAssertionGroup, sb, alwaysTrueAssertionFilter)
                            verbs.checkImmediately(sb.toString()).toBe(separator
                                + "placeholder %s: 2$separator"
                                + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                                + "$listBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2")
                        }
                    }

                    context("in an ${AssertionGroup::class.simpleName} of type ${DefaultFeatureAssertionGroupType::class.simpleName}") {
                        val featureAssertions = listOf(listAssertionGroup,
                            AssertImpl.builder.descriptive.create(AssertionVerb.ASSERT, 20, false)
                        )
                        val featureAssertionGroup = AssertImpl.builder.feature(AssertionVerb.ASSERT, 10).create(featureAssertions)
                        it("indents the group ${AssertionGroup::name.name} as well as the ${AssertionGroup::assertions.name} accordingly - uses `$listBulletPoint` for each assertion and `$bulletPoint` for each element in the list group") {
                            facade.format(featureAssertionGroup, sb, alwaysTrueAssertionFilter)
                            verbs.checkImmediately(sb.toString()).toBe(separator
                                + "$arrow ${AssertionVerb.ASSERT.getDefault()}: 10$separator"
                                + "$indentArrow$bulletPoint placeholder %s: 2$separator"
                                + "$indentArrow$indent$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                                + "$indentArrow$indent$listBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                                + "$indentArrow$bulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20")
                        }
                        context("in another ${AssertionGroup::class.simpleName} of type ${assertionGroupType::class.simpleName}") {
                            it("indents the group ${AssertionGroup::name.name} as well as the ${AssertionGroup::assertions.name} accordingly - uses `$listBulletPoint` for each assertion and `$bulletPoint` for each element in the list group") {
                                val listAssertions = listOf(
                                    AssertImpl.builder.descriptive.create(
                                        AssertionVerb.ASSERT,
                                        5,
                                        false
                                    ), featureAssertionGroup,
                                    AssertImpl.builder.descriptive.create(AssertionVerb.ASSERT, 30, false)
                                )
                                val listAssertionGroup2 = AssertImpl.builder
                                    .withType(assertionGroupType, AssertionVerb.EXPECT_THROWN, 10)
                                    .create(listAssertions)
                                facade.format(listAssertionGroup2, sb, alwaysTrueAssertionFilter)
                                verbs.checkImmediately(sb.toString()).toBe(separator
                                    + "${AssertionVerb.EXPECT_THROWN.getDefault()}: 10$separator"
                                    + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 5$separator"
                                    + "$listBulletPoint $arrow ${AssertionVerb.ASSERT.getDefault()}: 10$separator"
                                    + "$listIndent$indentArrow$bulletPoint placeholder %s: 2$separator"
                                    + "$listIndent$indentArrow$indent$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                                    + "$listIndent$indentArrow$indent$listBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                                    + "$listIndent$indentArrow$bulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20$separator"
                                    + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 30")
                            }
                        }
                    }
                    context("in another ${AssertionGroup::class.simpleName} of type ${assertionGroupClass.simpleName}") {
                        it("indents the group ${AssertionGroup::name.name} as well as the ${AssertionGroup::assertions.name} accordingly - uses `$listBulletPoint` for each assertion and `$bulletPoint` for each element in the list group") {
                            val listAssertions = listOf(
                                AssertImpl.builder.descriptive.create(
                                    AssertionVerb.ASSERT,
                                    5,
                                    false
                                ), listAssertionGroup,
                                AssertImpl.builder.descriptive.create(AssertionVerb.ASSERT, 30, false)
                            )
                            val listAssertionGroup2 = AssertImpl.builder
                                .withType(anonymousAssertionGroupType, AssertionVerb.EXPECT_THROWN, 10)
                                .create(listAssertions)
                            facade.format(listAssertionGroup2, sb, alwaysTrueAssertionFilter)
                            verbs.checkImmediately(sb.toString()).toBe(separator
                                + "${AssertionVerb.EXPECT_THROWN.getDefault()}: 10$separator"
                                + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 5$separator"
                                + "$listBulletPoint placeholder %s: 2$separator"
                                + "$listIndent$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                                + "$listIndent$listBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                                + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 30")
                        }
                    }
                }
            }
        }
    }
})
