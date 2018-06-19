package ch.tutteli.atrium.reporting.translating

actual fun determineIdForTranslatable(translatable: Translatable): String
    = translatable::class.qualifiedName + Translatable.ID_SEPARATOR + translatable.name
