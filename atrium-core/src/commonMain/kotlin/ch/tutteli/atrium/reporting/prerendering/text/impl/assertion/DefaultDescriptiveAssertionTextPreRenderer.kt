//TODO remove file with 2.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.reporting.prerendering.text.impl.assertion

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.reporting.prerendering.text.OutputNode
import ch.tutteli.atrium.reporting.prerendering.text.TextPreRenderControlObject
import ch.tutteli.atrium.reporting.prerendering.text.TypedTextPreRenderer

@Deprecated("Switch from Assertion to Proof, was introduced to ease the migration to Proof, will be removed with 2.0.0 at the latest")
class DefaultDescriptiveAssertionTextPreRenderer :
    TypedTextPreRenderer<DescriptiveAssertion>(DescriptiveAssertion::class) {
    override fun transformIt(
        reportable: DescriptiveAssertion,
        controlObject: TextPreRenderControlObject
    ): List<OutputNode> = controlObject.transformChild(
        Proof.simple(reportable.description, reportable.representation, reportable::holds),
        controlObject
    )
}
