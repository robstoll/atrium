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
     * The name of this [ITranslatable] -- the name together with its [KClass.qualifiedName] should identify a [ITranslatable] (see [id]).
     */
    val name: String

    /**
     * The [Locale] for which [getDefault] is defined.
     */
    val locale: Locale

    /**
     * The id of this [ITranslatable] -- per default it is "[KClass.qualifiedName]-[name]"
     */
    val id: String get() = this::class.qualifiedName + "-" + name
}
