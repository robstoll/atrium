package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.reporting.reportables.InlineElement
import kotlin.reflect.KClass

/**
 * Something which is translatable, identified by [id] with a default representation given by [getDefault].
 */
// TODO 1.3.0 explain replacement once representable is introduced
@Deprecated("will be remove with 2.0.0 at the latest")
interface Translatable: InlineElement {

    /**
     * Returns the default representation of this [Translatable].
     *
     * @return The default representation of this [Translatable].
     */
    fun getDefault(): String

    /**
     * The name of this [Translatable] -- the name together with its [KClass.qualifiedName] should
     * identify a [Translatable] (see [id]).
     */
    val name: String

    /**
     * The id of this [Translatable] -- per default it is
     * "[KClass.fullName][ch.tutteli.atrium.core.polyfills.fullName] [ID_SEPARATOR] [name]" without the spaces.
     */
    val id: String get() = this::class.fullName(this) + ID_SEPARATOR + name

    companion object {
        /**
         * The separator used in [id] to separate [KClass.qualifiedName] and [name].
         */
        const val ID_SEPARATOR = "-"
    }
}
