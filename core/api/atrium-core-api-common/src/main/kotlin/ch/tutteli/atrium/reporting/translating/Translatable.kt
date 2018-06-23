package ch.tutteli.atrium.reporting.translating

import kotlin.reflect.KClass

/**
 * Something which is translatable, identified by [id] with a default representation given by [getDefault].
 */
interface Translatable {

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
     * The id of this [Translatable] -- per default it is "[Class.name][D_SEPARATOR][name]"
     */
    val id: String get() = determineIdForTranslatable(this)

    companion object {
        /**
         * The separator used in [id] to separate [KClass.qualifiedName] and [name].
         */
        const val ID_SEPARATOR = "-"
    }
}
expect fun determineIdForTranslatable(translatable: Translatable): String
