package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Represents a builder for [Assertion].
 */
object AssertionBuilder {
    /**
     * Builder to create the root [AssertionGroup].
     */
    val root = BasicAssertionGroupBuilder(RootAssertionGroupType)
    /**
     * Builder to create an [AssertionGroup] with a [ListAssertionGroupType].
     */
    val list = BasicAssertionGroupBuilder(DefaultListAssertionGroupType)
    /**
     * Builder to create an [AssertionGroup] with a [FeatureAssertionGroupType].
     */
    val feature = BasicAssertionGroupBuilder(DefaultFeatureAssertionGroupType)
    /**
     * Builder to create an [AssertionGroup] with a [SummaryAssertionGroupType].
     */
    val summary = BasicAssertionGroupBuilder(DefaultSummaryAssertionGroupType)
    /**
     * Builder to create an [AssertionGroup] with a [ExplanatoryAssertionGroupType].
     */
    val explanatoryGroup = ExplanatoryAssertionGroupOption()
    /**
     * Builder to create an [AssertionGroup] with a [InvisibleAssertionGroupType].
     */
    val invisibleGroup = EmptyNameAndSubjectAssertionGroupBuilder(DefaultInvisibleAssertionGroupType)
    /**
     * Builder to create a [DescriptiveAssertion].
     */
    val descriptive = DescriptiveAssertionBuilder()

    /**
     * Builder to create an [ExplanatoryAssertion].
     */
    val explanatory = ExplanatoryAssertionBuilder()


    /**
     * Builder to create an [AssertionGroup] with a custom [AssertionGroupType].
     */
    fun withType(groupType: AssertionGroupType) = BasicAssertionGroupBuilder(groupType)

    /**
     * Builder to create an [AssertionGroup] with the given [groupType].
     */
    class BasicAssertionGroupBuilder internal constructor(private val groupType: AssertionGroupType) {
        /**
         * Creates the [AssertionGroup] using the given [name] as [AssertionGroup.name], [subject] as
         * [AssertionGroup.subject] and [assertion] as single [AssertionGroup.assertions].
         */
        fun create(name: Translatable, subject: Any, assertion: Assertion)
            = create(name, subject, listOf(assertion))

        /**
         * Creates the [AssertionGroup] using the given [name] as [AssertionGroup.name], [subject] as
         * [AssertionGroup.subject] and [assertions] as [AssertionGroup.assertions].
         */
        fun create(name: Translatable, subject: Any, assertions: List<Assertion>): AssertionGroup
            = BasicAssertionGroup(groupType, name, subject, assertions)
    }

    /**
     * Provides options to create an [AssertionGroup] with a certain [ExplanatoryAssertionGroupType].
     */
    class ExplanatoryAssertionGroupOption internal constructor() {
        /**
         * Builder to create an [AssertionGroup] with a [DefaultListAssertionGroupType].
         */
        val withDefault = ExplanatoryAssertionGroupBuilder(DefaultExplanatoryAssertionGroupType)
        /**
         * Builder to create an [AssertionGroup] with a [WarningAssertionGroupType].
         */
        val withWarning = ExplanatoryAssertionGroupBuilder(WarningAssertionGroupType)

        /**
         * Builder to create an [AssertionGroup] with a custom [ExplanatoryAssertionGroupType].
         */
        fun withType(groupType: ExplanatoryAssertionGroupType) = ExplanatoryAssertionGroupBuilder(groupType)
    }

    /**
     * Builder to create an [AssertionGroup] with the given [groupType] (an [ExplanatoryAssertionGroupType]).
     */
    class ExplanatoryAssertionGroupBuilder internal constructor(private val groupType: ExplanatoryAssertionGroupType) {

        /**
         * Creates the [AssertionGroup] using the given [translatable] -- which is used in an [TranslatableWithArgs]
         * together with the given arguments ([arg] and optionally [otherArgs]) -- to create an [ExplanatoryAssertion]
         * which is used as single [Assertion] in [AssertionGroup.assertions].
         *
         * See [ExplanatoryAssertionBuilder.create] for details.
         */
        fun createWithExplanatoryAssertion(translatable: Translatable, arg: Any, vararg otherArgs: Any)
            = create(explanatory.create(translatable, arg, *otherArgs))

        /**
         * Creates the [AssertionGroup] using the given [translatable] to create an [ExplanatoryAssertion] which is used
         * as single [Assertion] in [AssertionGroup.assertions].
         *
         * See [ExplanatoryAssertionBuilder.create] for details.
         */
        fun createWithExplanatoryAssertion(translatable: Translatable)
            = create(explanatory.create(translatable))

        /**
         * Creates the [AssertionGroup] using the given [assertion] as single [Assertion] in [AssertionGroup.assertions].
         */
        fun create(assertion: Assertion): AssertionGroup
            = create(listOf(assertion))

        /**
         * Creates the [AssertionGroup] using the given [assertions] as [AssertionGroup.assertions].
         */
        fun create(assertions: List<Assertion>): AssertionGroup
            = ExplanatoryAssertionGroup(groupType, assertions)
    }

    /**
     * Builder to create an [AssertionGroup] with an empty [AssertionGroup.name], an empty [AssertionGroup.subject] and
     * with the given [groupType].
     */
    class EmptyNameAndSubjectAssertionGroupBuilder internal constructor(private val groupType: AssertionGroupType) {
        /**
         * Creates the [AssertionGroup] using the given [assertion] as single [AssertionGroup.assertions].
         */
        fun create(assertion: Assertion): AssertionGroup
            = create(listOf(assertion))

        /**
         * Creates the [AssertionGroup] using the given [assertions] as [AssertionGroup.assertions].
         */
        fun create(assertions: List<Assertion>): AssertionGroup
            = EmptyNameAndSubjectAssertionGroup(groupType, assertions)
    }

    /**
     * Builder to create an [DescriptiveAssertion].
     */
    class DescriptiveAssertionBuilder internal constructor(){
        /**
         * Wraps the [test] into a lambda and delegates to the other `create` overload.
         */
        fun create(description: String, representation: Any, test: Boolean): DescriptiveAssertion
            = create(description, representation, { test })

        /**
         * Wraps the given [description] into an [Untranslatable] and delegates to the other `create` overload.
         */
        fun create(description: String, representation: Any, test: () -> Boolean): DescriptiveAssertion
            = create(Untranslatable(description), representation, test)

        /**
         * Creates an [DescriptiveAssertion] based on the given [description], [representation] and [test].
         */
        fun create(description: Translatable, representation: Any, test: Boolean): DescriptiveAssertion
            = create(description, representation, { test })

        /**
         * Creates an [DescriptiveAssertion] based on the given [description], [representation] and [test].
         */
        fun create(description: Translatable, representation: Any, test: () -> Boolean): DescriptiveAssertion
            = BasicDescriptiveAssertion(description, representation, test)
    }

    /**
     * Builder to create an [ExplanatoryAssertion].
     */
    class ExplanatoryAssertionBuilder internal constructor() {

        /**
         * Creates an [ExplanatoryAssertion] using the given [translatable] (using the given [arg] and
         * optionally [otherArgs] as arguments of the [TranslatableWithArgs]) as explanation.
         *
         * It then delegates to the overload which expects a single [Translatable].
         */
        fun create(translatable: Translatable, arg: Any, vararg otherArgs: Any)
            = create(TranslatableWithArgs(translatable, arg, *otherArgs))

        /**
         * Creates an [ExplanatoryAssertion] using the given [translatable] as explanation.
         *
         * In detail, the given [translatable] is turned into a [RawString] so that an [ObjectFormatter] translates the
         * given [translatable] and treats the result as raw string.
         */
        fun create(translatable: Translatable): ExplanatoryAssertion
            = create(RawString.create(translatable))

        /**
         * Creates an [ExplanatoryAssertion] using the given [explanation].
         *
         * In case you want to pass a [String] which should be treated as [RawString] in reporting, then please wrap it
         * into a [RawString] (`RawString.create("Your text..")`.
         */
        fun create(explanation: Any?) : ExplanatoryAssertion
            = BasicExplanatoryAssertion(explanation)
    }
}
