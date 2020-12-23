package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.DefaultExplanatoryAssertionGroupType
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator
import org.spekframework.spek2.Spek
import kotlin.reflect.KClass

class TextExplanatoryAssertionGroupFormatterSpec : Spek({

    include(AtriumsTextExplanatoryAssertionFormatterSpec)
    include(AtriumsTextWarningAssertionFormatterSpec)
    include(AtriumsTextInformationAssertionFormatterSpec)
    include(AtriumsSingleAssertionGroupTypeFormatterSpec)
    include(AtriumsAssertionFormatterSpec)

}) {
    object AtriumsTextExplanatoryAssertionFormatterSpec :
        ch.tutteli.atrium.specs.reporting.TextExplanatoryAssertionGroupFormatterSpec(
            factory(), "[Atrium's TextExplanatory...Spec] "
        )

    object AtriumsTextWarningAssertionFormatterSpec :
        ch.tutteli.atrium.specs.reporting.TextWarningAssertionGroupFormatterSpec(
            factory(), "[Atrium's TextWarning...Spec] "
        )

    object AtriumsTextInformationAssertionFormatterSpec :
        ch.tutteli.atrium.specs.reporting.TextInformationAssertionGroupFormatterSpec(
            factory(), "[Atrium's TextInformation...Spec] "
        )

    object AtriumsEmptyNameAndSubjectAssertionGroupFormatterSpec :
        ch.tutteli.atrium.specs.reporting.EmptyNameAndSubjectAssertionGroupFormatterSpec<ExplanatoryAssertionGroupType>(
            factoryWithoutBulletPoint(),
            ExplanatoryAssertionGroupType::class,
            DefaultExplanatoryAssertionGroupType,
            object : ExplanatoryAssertionGroupType {},
            "[Atrium's EmptyNameAndSubject...Spec] "
        )

    object AtriumsSingleAssertionGroupTypeFormatterSpec :
        ch.tutteli.atrium.specs.reporting.SingleAssertionGroupTypeFormatterSpec<ExplanatoryAssertionGroupType>(
            factoryWithObjectFormatter(),
            ExplanatoryAssertionGroupType::class,
            object : ExplanatoryAssertionGroupType {},
            DefaultExplanatoryAssertionGroupType,
            "[Atrium's SingleAssertionGroupType...Spec] "
        )

    object AtriumsAssertionFormatterSpec : ch.tutteli.atrium.specs.reporting.AssertionFormatterSpec(
        factoryWithObjectFormatter(), "[Atrium's AssertionFormatterSpec] "
    )

    companion object {
        private fun factory() =
            { bulletPoints: Map<KClass<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController ->
                TextExplanatoryAssertionGroupFormatter(
                    bulletPoints,
                    assertionFormatterController
                )
            }

        private fun factoryWithoutBulletPoint() = { assertionFormatterController: AssertionFormatterController ->
            factory()(mapOf(ExplanatoryAssertionGroupType::class to "*"), assertionFormatterController)
        }

        private fun factoryWithObjectFormatter() =
            { bulletPoints: Map<KClass<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, _: ObjectFormatter, _: Translator ->
                factory()(bulletPoints, assertionFormatterController)
            }
    }
}
