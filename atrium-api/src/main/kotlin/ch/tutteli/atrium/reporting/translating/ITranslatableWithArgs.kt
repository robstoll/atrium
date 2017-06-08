package ch.tutteli.atrium.reporting.translating

/**
 * An [ITranslatable] with arguments
 */
interface ITranslatableWithArgs : ITranslatable {
    /**
     * The [ITranslatable].
     */
    val translatable: ITranslatable
    /**
     * The arguments.
     */
    val arguments: Array<Any>

    override val locale get() = translatable.locale
    override val name get() = translatable.name
    override val id get() = translatable::class.java.name + name
    override fun getDefault() = String.format(translatable.locale, translatable.getDefault(), *arguments)
}
