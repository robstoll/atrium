package ch.tutteli.atrium.reporting.prerendering.text

import ch.tutteli.atrium.core.polyfills.cast
import ch.tutteli.atrium.reporting.reportables.Diagnostic
import ch.tutteli.atrium.reporting.theming.text.StyledString
import kotlin.reflect.KClass

// TODO 1.3.0 KDOC
abstract class TypedDesignatorPreRenderer<R : Diagnostic>(private val kClass: KClass<R>) :
    TextDesignationPreRenderer {
    final override fun canTransform(description: Diagnostic): Boolean = kClass.isInstance(description)
    override fun transform(
        description: Diagnostic,
        representation: Any,
        prefixDescriptionColumns: List<StyledString>,
        suffixDescriptionColumns: List<StyledString>,
        startMergeAtColumn: Int,
        children: List<OutputNode>,
        controlObject: TextPreRenderControlObject,
    ): List<OutputNode> = transformIt(
        kClass.cast(description),
        representation,
        prefixDescriptionColumns,
        suffixDescriptionColumns,
        startMergeAtColumn,
        children,
        controlObject
    )

    protected abstract fun transformIt(
        description: R,
        representation: Any,
        prefixDescriptionColumns: List<StyledString>,
        suffixDescriptionColumns: List<StyledString>,
        startMergeAtColumn: Int,
        children: List<OutputNode>,
        controlObject: TextPreRenderControlObject,
    ): List<OutputNode>
}
