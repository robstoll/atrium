package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.assertions.IBulletPointIdentifier
import ch.tutteli.atrium.assertions.IExplanatoryAssertionGroupType
import ch.tutteli.atrium.reporting.translating.Translator
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include

class TextExplanatoryAssertionGroupFormatterSpec : Spek({

    include(AtriumsTextExplanatoryAssertionFormatterSpec)
    include(AtriumsTextWarningAssertionFormatterSpec)
    include(AtriumsSingleAssertionGroupTypeFormatterSpec)
    include(AtriumsAssertionFormatterSpec)

}) {
    object AtriumsTextExplanatoryAssertionFormatterSpec : ch.tutteli.atrium.spec.reporting.TextExplanatoryAssertionGroupFormatterSpec(
        AssertionVerbFactory, factory(), "[Atrium's TextExplanatory...Spec] ")

    object AtriumsTextWarningAssertionFormatterSpec : ch.tutteli.atrium.spec.reporting.TextWarningAssertionGroupFormatterSpec(
        AssertionVerbFactory, factory(), "[Atrium's TextWarning...Spec] ")

    object AtriumsEmptyNameAndSubjectAssertionGroupFormatterSpec : ch.tutteli.atrium.spec.reporting.EmptyNameAndSubjectAssertionGroupFormatterSpec<IExplanatoryAssertionGroupType>(
        AssertionVerbFactory, factoryWithoutBulletPoint(),
        IExplanatoryAssertionGroupType::class.java,
        ExplanatoryAssertionGroupType,
        object : IExplanatoryAssertionGroupType {},
        "[Atrium's EmptyNameAndSubject...Spec] ")

    object AtriumsSingleAssertionGroupTypeFormatterSpec : ch.tutteli.atrium.spec.reporting.SingleAssertionGroupTypeFormatterSpec<IExplanatoryAssertionGroupType>(
        AssertionVerbFactory, factoryWithObjectFormatter(),
        IExplanatoryAssertionGroupType::class.java,
        object : IExplanatoryAssertionGroupType {},
        ExplanatoryAssertionGroupType,
        "[Atrium's SingleAssertionGroupType...Spec] "
    )

    object AtriumsAssertionFormatterSpec : ch.tutteli.atrium.spec.reporting.AssertionFormatterSpec(
        AssertionVerbFactory, factoryWithObjectFormatter(), "[Atrium's AssertionFormatterSpec] "
    )

    companion object {
        private fun factory() = { bulletPoints: Map<Class<out IBulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController ->
            TextExplanatoryAssertionGroupFormatter(bulletPoints, assertionFormatterController)
        }

        private fun factoryWithoutBulletPoint() = { assertionFormatterController: AssertionFormatterController ->
            factory()(mapOf(IExplanatoryAssertionGroupType::class.java to "*"), assertionFormatterController)
        }

        private fun factoryWithObjectFormatter() = { bulletPoints: Map<Class<out IBulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, _: ObjectFormatter, _: Translator ->
            factory()(bulletPoints, assertionFormatterController)
        }
    }
}
