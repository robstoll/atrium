package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.assertions.IExplanatoryAssertionGroupType
import ch.tutteli.atrium.reporting.translating.ITranslator
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include

class TextExplanatoryAssertionGroupFormatterSpec : Spek({

    include(AtriumsTextExplanatoryAssertionFormatterSpec)
    include(AtriumsSingleAssertionGroupTypeFormatterSpec)
    include(AtriumsAssertionFormatterSpec)

}) {
    object AtriumsTextExplanatoryAssertionFormatterSpec : ch.tutteli.atrium.spec.reporting.TextExplanatoryAssertionGroupFormatterSpec(
        AssertionVerbFactory, factory(), "[Atrium's TextExplanatory...Spec] ")

    object AtriumsSingleAssertionGroupTypeFormatterSpec : ch.tutteli.atrium.spec.reporting.SingleAssertionGroupTypeFormatterSpec<IExplanatoryAssertionGroupType>(
        AssertionVerbFactory, factoryWithBullet(),
        IExplanatoryAssertionGroupType::class.java,
        object : IExplanatoryAssertionGroupType {},
        ExplanatoryAssertionGroupType,
        "[Atrium's SingleAssertionGroupType...Spec] "
    )

    object AtriumsAssertionFormatterSpec : ch.tutteli.atrium.spec.reporting.AssertionFormatterSpec(
        AssertionVerbFactory, factoryWithBullet(), "[Atrium's AssertionFormatterSpec] "
    )

    companion object {
        internal fun factory() = { bulletPoint: String, assertionFormatterController: IAssertionFormatterController, objectFormatter: IObjectFormatter, translator: ITranslator ->
            TextExplanatoryAssertionGroupFormatter(bulletPoint, assertionFormatterController, TextSameLineAssertionPairFormatter(objectFormatter, translator))
        }

        internal fun factoryWithBullet() = { assertionFormatterController: IAssertionFormatterController, objectFormatter: IObjectFormatter, translator: ITranslator ->
            factory()("▪", assertionFormatterController, objectFormatter, translator)
        }
    }
}
