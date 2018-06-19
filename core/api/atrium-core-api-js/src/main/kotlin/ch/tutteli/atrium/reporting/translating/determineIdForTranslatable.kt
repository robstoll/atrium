package ch.tutteli.atrium.reporting.translating

actual fun determineIdForTranslatable(translatable: Translatable): String
    //TODO provide a way to define the package as well in JS
     = translatable::class.simpleName + Translatable.ID_SEPARATOR + translatable.name
