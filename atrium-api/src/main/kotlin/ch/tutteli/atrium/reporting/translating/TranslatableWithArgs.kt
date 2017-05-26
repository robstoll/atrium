package ch.tutteli.atrium.reporting.translating

data class TranslatableWithArgs(override val translatable: ITranslatable, override val arguments: List<String>) : ITranslatableWithArgs {

    constructor(translatable: ITranslatable, arg1: String) : this(translatable, listOf(arg1))
    constructor(translatable: ITranslatable, arg1: String, vararg arguments: String) : this(translatable, listOf(arg1, *arguments))

}
