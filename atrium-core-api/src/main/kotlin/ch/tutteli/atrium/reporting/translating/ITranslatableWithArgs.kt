package ch.tutteli.atrium.reporting.translating

import java.util.*

/**
 * Represents a [ITranslatable] with arguments.
 *
 * It uses a [translatable], which has placeholders, as basis and [java.lang.String.format]
 * to substitute the placeholders with the [arguments].
 */
interface ITranslatableWithArgs : ITranslatable {
    /**
     * A [ITranslatable] with placeholders for the arguments.
     * @see [Formatter.format]
     */
    val translatable: ITranslatable
    /**
     * The arguments.
     */
    val arguments: Array<Any>

    override val name get() = translatable.name
    override val id get() = translatable::class.java.name + name
    override fun getDefault() = String.format(translatable.getDefault(), *arguments)
}
