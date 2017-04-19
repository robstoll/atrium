package ch.tutteli.atrium.reporting

data class TranslatableRawString(val translatable: ITranslatable) : IRawString {
    override fun toString(): String {
        return "${translatable.getDefault()} (TranslatableRawString)"
    }
}
