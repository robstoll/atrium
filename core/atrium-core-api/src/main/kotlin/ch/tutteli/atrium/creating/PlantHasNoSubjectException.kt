package ch.tutteli.atrium.creating

/**
 * Represents the [Exception] that an [AssertionPlant.subject] was not defined but one tried to access it.
 *
 * @param message A message which should describe why the [AssertionPlant.subject] is absent.
 */
class PlantHasNoSubjectException(message: String) : RuntimeException(message)
