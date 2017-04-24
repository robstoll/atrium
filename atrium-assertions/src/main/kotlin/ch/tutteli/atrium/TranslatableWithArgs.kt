package ch.tutteli.atrium

import ch.tutteli.atrium.reporting.translating.ITranslatable

data class TranslatableWithArgs(private val translatable: ITranslatable, val arguments: List<String>) : ITranslatable {
    constructor(translatable: ITranslatable, arg1: String) : this(translatable, listOf(arg1))
    constructor(translatable: ITranslatable, arg1: String, vararg arguments: String) : this(translatable, listOf(arg1, *arguments))

    override fun getDefault() = String.format(translatable.getDefault(), *arguments.toTypedArray())
}
