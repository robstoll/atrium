package ch.tutteli.atrium.reporting.translating

import java.util.*
import kotlin.reflect.KClass

/**
 * Something which is translatable, identified by [id] with a default representation given by [getDefault]
 * and defined for [locale].
 */
interface ITranslatable {
    /**
     * @return The default representation of this [ITranslatable].
     */
    fun getDefault(): String

    /**
     * The name of this [ITranslatable] -- the name together with its [Class.name] should identify a [ITranslatable] (see [id]).
     */
    val name: String

    /**
     * The [Locale] for which [getDefault] is defined.
     */
    val locale: Locale

    /**
     * The id of this [ITranslatable] -- per default it is "[Class.name]-[name]"
     */
    val id: String get() = this::class.java.name + ID_SEPARATOR + name

    companion object {
        /**
         * The separator used in [id] to separate [KClass.qualifiedName] and [name].
         */
        const val ID_SEPARATOR = "-"
    }
}
