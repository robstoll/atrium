package ch.tutteli.atrium.reporting.translating

/**
 * An [ITranslatable] which contains placeholders for arguments.
 *
 * @property translatable The [ITranslatable] as such which contains placeholders and
 *                        for which translations can be been defined.
 * @property arguments The arguments which should be used to substitute the placeholders of the [translatable].
 *
 * @constructor
 * @param translatable The [ITranslatable] as such which contains placeholders and
 *                     for which translations can be been defined.
 * @param arguments The arguments which should be used to substitute the placeholders of the [translatable].
 *
 */
class TranslatableWithArgs(override val translatable: ITranslatable, override val arguments: Array<Any>) : ITranslatableWithArgs {

    /**
     * Creates an [ITranslatableWithArgs] with the given [translatable] and its one and only [argument].
     *
     * @param translatable An [ITranslatable] which one placeholder for an [argument].
     * @param argument The argument which will be used to substitute the placeholder of the given [translatable].
     */
    constructor(translatable: ITranslatable, argument: Any) : this(translatable, arrayOf(argument))

    /**
     * Creates an [ITranslatableWithArgs] with the given [translatable], the first argument [arg1] and the [otherArguments].
     *
     * @param translatable An [ITranslatable] with an variable number of placeholders (at least one for [arg1]).
     * @param arg1 The argument for the first placeholder of the given [translatable].
     * @param otherArguments The remaining arguments for the remaining placeholders of the given [translatable].
     */
    constructor(translatable: ITranslatable, arg1: Any, vararg otherArguments: Any) : this(translatable, arrayOf(arg1, *otherArguments))
}
