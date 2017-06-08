package ch.tutteli.atrium.reporting.translating

class TranslatableWithArgs(override val translatable: ITranslatable, override val arguments: Array<Any>) : ITranslatableWithArgs {

    constructor(translatable: ITranslatable, arg1: Any) : this(translatable, arrayOf(arg1))
    constructor(translatable: ITranslatable, arg1: Any, vararg arguments: Any) : this(translatable, arrayOf(arg1, *arguments))
}
