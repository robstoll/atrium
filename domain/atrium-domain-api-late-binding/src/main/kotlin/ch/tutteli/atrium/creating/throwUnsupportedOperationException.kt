package ch.tutteli.atrium.creating

internal fun throwUnsupportedOperationException(): Nothing
    = throw UnsupportedOperationException(
    "The atrium-domain-api-late-binding should only be used as a compileOnly dependency, " +
        "meaning as a substitute for a real implementation.")
