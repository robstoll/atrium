package ch.tutteli.atrium.reporting.prerendering.text

import ch.tutteli.atrium.core.polyfills.cast
import ch.tutteli.atrium.reporting.reportables.Reportable
import kotlin.reflect.KClass

// TODO 1.3.0 KDOC
abstract class TypedTextPreRenderer<R : Reportable>(private val kClass: KClass<R>) : TextPreRenderer {
    final override fun canTransform(reportable: Reportable): Boolean = kClass.isInstance(reportable)
    final override fun transform(reportable: Reportable, controlObject: TextPreRenderControlObject): List<OutputNode> =
        transformIt(kClass.cast(reportable), controlObject)

    protected abstract fun transformIt(reportable: R, controlObject: TextPreRenderControlObject): List<OutputNode>
}
