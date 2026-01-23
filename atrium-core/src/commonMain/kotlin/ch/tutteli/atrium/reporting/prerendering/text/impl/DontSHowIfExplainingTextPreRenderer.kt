package ch.tutteli.atrium.reporting.prerendering.text.impl

import ch.tutteli.atrium.reporting.prerendering.text.OutputNode
import ch.tutteli.atrium.reporting.prerendering.text.TextPreRenderControlObject
import ch.tutteli.atrium.reporting.prerendering.text.TypedTextPreRenderer
import ch.tutteli.atrium.reporting.reportables.Reportable
import kotlin.reflect.KClass


internal abstract class DontShowIfExplainingTextPreRenderer<T : Reportable>(
    kClass: KClass<T>
) : TypedTextPreRenderer<T>(kClass) {

    final override fun transformIt(reportable: T, controlObject: TextPreRenderControlObject): List<OutputNode> {
        // Hide anywhere under a ProofExplanation subtree.
        if (controlObject.explainsProof) return emptyList()
        return transformIfNotExplaining(reportable, controlObject)
    }

    protected abstract fun transformIfNotExplaining(
        reportable: T,
        controlObject: TextPreRenderControlObject
    ): List<OutputNode>
}
