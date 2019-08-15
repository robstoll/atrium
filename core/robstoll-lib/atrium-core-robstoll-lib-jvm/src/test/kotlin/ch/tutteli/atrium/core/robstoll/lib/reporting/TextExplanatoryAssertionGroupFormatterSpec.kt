package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.DefaultExplanatoryAssertionGroupType
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KClass

//TODO #116 migrate spek1 to spek2 - move to common module
class TextExplanatoryAssertionGroupFormatterSpec : Spek({

    include(AtriumsTextExplanatoryAssertionFormatterSpec)
    include(AtriumsTextWarningAssertionFormatterSpec)
    include(AtriumsSingleAssertionGroupTypeFormatterSpec)
    include(AtriumsAssertionFormatterSpec)

}) {
    object AtriumsTextExplanatoryAssertionFormatterSpec : ch.tutteli.atrium.specs.reporting.TextExplanatoryAssertionGroupFormatterSpec(
        AssertionVerbFactory,
        factory(), "[Atrium's TextExplanatory...Spec] ")

    object AtriumsTextWarningAssertionFormatterSpec : ch.tutteli.atrium.specs.reporting.TextWarningAssertionGroupFormatterSpec(
        AssertionVerbFactory,
        factory(), "[Atrium's TextWarning...Spec] ")

    object AtriumsEmptyNameAndSubjectAssertionGroupFormatterSpec : ch.tutteli.atrium.specs.reporting.EmptyNameAndSubjectAssertionGroupFormatterSpec<ExplanatoryAssertionGroupType>(
        AssertionVerbFactory,
        factoryWithoutBulletPoint(),
        ExplanatoryAssertionGroupType::class,
        DefaultExplanatoryAssertionGroupType,
        object : ExplanatoryAssertionGroupType {},
        "[Atrium's EmptyNameAndSubject...Spec] ")

    object AtriumsSingleAssertionGroupTypeFormatterSpec : ch.tutteli.atrium.specs.reporting.SingleAssertionGroupTypeFormatterSpec<ExplanatoryAssertionGroupType>(
        AssertionVerbFactory,
        factoryWithObjectFormatter(),
        ExplanatoryAssertionGroupType::class,
        object : ExplanatoryAssertionGroupType {},
        DefaultExplanatoryAssertionGroupType,
        "[Atrium's SingleAssertionGroupType...Spec] "
    )

    object AtriumsAssertionFormatterSpec : ch.tutteli.atrium.specs.reporting.AssertionFormatterSpec(
        AssertionVerbFactory,
        factoryWithObjectFormatter(), "[Atrium's AssertionFormatterSpec] "
    )

    companion object {
        private fun factory() = { bulletPoints: Map<KClass<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController ->
            TextExplanatoryAssertionGroupFormatter(
                bulletPoints,
                assertionFormatterController
            )
        }

        private fun factoryWithoutBulletPoint() = { assertionFormatterController: AssertionFormatterController ->
            factory()(mapOf(ExplanatoryAssertionGroupType::class to "*"), assertionFormatterController)
        }

        private fun factoryWithObjectFormatter() = { bulletPoints: Map<KClass<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, _: ObjectFormatter, _: Translator ->
            factory()(bulletPoints, assertionFormatterController)
        }
    }
}
