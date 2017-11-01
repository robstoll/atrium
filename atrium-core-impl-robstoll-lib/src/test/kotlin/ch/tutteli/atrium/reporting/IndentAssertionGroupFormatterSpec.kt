package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.assertions.IBulletPointIdentifier
import ch.tutteli.atrium.assertions.IIndentAssertionGroupType
import ch.tutteli.atrium.assertions.IndentAssertionGroupType
import ch.tutteli.atrium.reporting.translating.ITranslator
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include

class IndentAssertionGroupFormatterSpec : Spek({

    include(AtriumsIndentAssertionGroupFormatterSpec)
    include(AtriumsEmptyNameAndSubjectAssertionGroupFormatterSpec)
    include(AtriumsSingleAssertionGroupTypeFormatterSpec)
    include(AtriumsAssertionFormatterSpec)

}) {
    object AtriumsIndentAssertionGroupFormatterSpec : ch.tutteli.atrium.spec.reporting.IndentAssertionGroupFormatterSpec(
        AssertionVerbFactory, ::IndentAssertionGroupFormatter, "[Atrium's IndentGroup...Spec] ")

    object AtriumsEmptyNameAndSubjectAssertionGroupFormatterSpec : ch.tutteli.atrium.spec.reporting.EmptyNameAndSubjectAssertionGroupFormatterSpec<IIndentAssertionGroupType>(
        AssertionVerbFactory, factory(),
        IIndentAssertionGroupType::class.java,
        IndentAssertionGroupType,
        object : IIndentAssertionGroupType {},
        "[Atrium's EmptyNameAndSubject...Spec] ")

    object AtriumsSingleAssertionGroupTypeFormatterSpec : ch.tutteli.atrium.spec.reporting.SingleAssertionGroupTypeFormatterSpec<IIndentAssertionGroupType>(
        AssertionVerbFactory, factoryWithBulletPoints(),
        IIndentAssertionGroupType::class.java,
        IndentAssertionGroupType,
        object : IIndentAssertionGroupType {},
        "[Atrium's SingleAssertionGroupType...Spec] "
    )

    object AtriumsAssertionFormatterSpec : ch.tutteli.atrium.spec.reporting.AssertionFormatterSpec(
        AssertionVerbFactory, factoryWithBulletPoints(), "[Atrium's AssertionFormatterSpec] ")

    companion object {
        fun factory() = { assertionFormatterController: IAssertionFormatterController ->
            IndentAssertionGroupFormatter(mapOf(IIndentAssertionGroupType::class.java to "**"), assertionFormatterController)
        }

        fun factoryWithBulletPoints() = { _: Map<Class<out IBulletPointIdentifier>, String>, assertionFormatterController: IAssertionFormatterController, _: IObjectFormatter, _: ITranslator ->
            IndentAssertionGroupFormatter(mapOf(IIndentAssertionGroupType::class.java to "**"), assertionFormatterController)
        }
    }
}
