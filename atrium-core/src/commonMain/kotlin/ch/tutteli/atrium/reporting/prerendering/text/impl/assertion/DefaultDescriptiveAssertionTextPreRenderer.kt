package ch.tutteli.atrium.reporting.prerendering.text.impl.assertion

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.prerendering.text.TextPreRenderControlObject
import ch.tutteli.atrium.reporting.prerendering.text.OutputNode
import ch.tutteli.atrium.reporting.prerendering.text.TypedTextPreRenderer
import ch.tutteli.atrium.reporting.prerendering.text.transformGroup

@Suppress("DEPRECATION")
@Deprecated("Switch from Assertion to Proof, was introduced to ease the migration to Proof, will be removed with 2.0.0 at the latest")
class DefaultDescriptiveAssertionTextPreRenderer :
    TypedTextPreRenderer<DescriptiveAssertion>(DescriptiveAssertion::class) {
    override fun transformIt(
        reportable: DescriptiveAssertion,
        controlObject: TextPreRenderControlObject
    ): List<OutputNode> {
        // we kind of misuse transformGroup to re-use the logic for TextDesignationPreRenderer
        // but we need to set definesOwnLevel to false as a SimpleProof does not define an own level
        return listOf(
            controlObject.transformGroup(reportable, controlObject).single().copy(definesOwnLevel = false)
        )
    }
}
