package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.AssertionVerbFactory
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include

object InvisibleAssertionGroupFormatterSpec : Spek({
    include(AtriumsInvisibleAssertionGroupFormatterSpecSpec)
    include(AtriumsAssertionFormatterSpec)
}) {
    object AtriumsInvisibleAssertionGroupFormatterSpecSpec : ch.tutteli.atrium.spec.reporting.InvisibleAssertionGroupFormatterSpec(
        AssertionVerbFactory, ::InvisibleAssertionGroupFormatter, "[Atrium's InvisibleGroup...Spec] ")

    object AtriumsAssertionFormatterSpec : ch.tutteli.atrium.spec.reporting.AssertionFormatterSpec(
        AssertionVerbFactory,
        { assertionFormatterController, _, _ -> InvisibleAssertionGroupFormatter(assertionFormatterController) },
        "[Atrium's AssertionFormatterSpec] ")
}
