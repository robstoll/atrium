package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.DefaultExplanatoryAssertionGroupType
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.reporting.text.impl.TextExplanatoryAssertionGroupFormatter
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

    object AtriumsTextInformationWithIndentAssertionFormatterSpec :
        ch.tutteli.atrium.specs.reporting.TextInformationAssertionGroupFormatterSpec(
            factory(), withIndent = true, describePrefix = "[Atrium's TextInformation...Spec] "
        )

    object AtriumsTextInformationAssertionFormatterSpec :
        ch.tutteli.atrium.specs.reporting.TextInformationAssertionGroupFormatterSpec(
            factory(), withIndent = false, describePrefix =  "[Atrium's TextInformation...Spec] "
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
            { bulletPoints: Map<KClass<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, _: ObjectFormatter ->
                factory()(bulletPoints, assertionFormatterController)
            }
    }
}
