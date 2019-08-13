@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.DefaultIndentAssertionGroupType
import ch.tutteli.atrium.assertions.IndentAssertionGroupType
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KClass

//TODO remove with 1.0.0 -- no need to migrate to spek2
@Deprecated("So far indentation was achieved by grouping (which is the solution to go). Will be removed with 1.0.0")
class TextIndentAssertionGroupFormatterSpec : Spek({

    include(AtriumsTextIndentAssertionGroupFormatterSpec)
    include(AtriumsEmptyNameAndSubjectAssertionGroupFormatterSpec)
    include(AtriumsSingleAssertionGroupTypeFormatterSpec)
    include(AtriumsAssertionFormatterSpec)

}) {
    object AtriumsTextIndentAssertionGroupFormatterSpec : ch.tutteli.atrium.spec.reporting.TextIndentAssertionGroupFormatterSpec(
        AssertionVerbFactory, ::TextIndentAssertionGroupFormatter, "[Atrium's IndentGroup...Spec] ")

    object AtriumsEmptyNameAndSubjectAssertionGroupFormatterSpec : ch.tutteli.atrium.spec.reporting.EmptyNameAndSubjectAssertionGroupFormatterSpec<IndentAssertionGroupType>(
        AssertionVerbFactory,
        factory(),
        IndentAssertionGroupType::class,
        DefaultIndentAssertionGroupType,
        object : IndentAssertionGroupType {},
        "[Atrium's EmptyNameAndSubject...Spec] ")

    object AtriumsSingleAssertionGroupTypeFormatterSpec : ch.tutteli.atrium.spec.reporting.SingleAssertionGroupTypeFormatterSpec<IndentAssertionGroupType>(
        AssertionVerbFactory,
        factoryWithBulletPoints(),
        IndentAssertionGroupType::class,
        DefaultIndentAssertionGroupType,
        object : IndentAssertionGroupType {},
        "[Atrium's SingleAssertionGroupType...Spec] "
    )

    object AtriumsAssertionFormatterSpec : ch.tutteli.atrium.spec.reporting.AssertionFormatterSpec(
        AssertionVerbFactory,
        factoryWithBulletPoints(), "[Atrium's AssertionFormatterSpec] ")

    companion object {
        fun factory() = { assertionFormatterController: AssertionFormatterController ->
            TextIndentAssertionGroupFormatter(
                mapOf(
                    IndentAssertionGroupType::class to "**"
                ), assertionFormatterController
            )
        }

        fun factoryWithBulletPoints() = { _: Map<KClass<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, _: ObjectFormatter, _: Translator ->
            TextIndentAssertionGroupFormatter(
                mapOf(
                    IndentAssertionGroupType::class to "**"
                ), assertionFormatterController
            )
        }
    }
}
