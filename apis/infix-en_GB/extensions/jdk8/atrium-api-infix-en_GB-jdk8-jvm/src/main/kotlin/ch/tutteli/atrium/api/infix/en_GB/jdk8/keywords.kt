package ch.tutteli.atrium.api.infix.en_GB.jdk8

import ch.tutteli.atrium.api.infix.en_GB.Keyword


/**
 * A helper construct to allow expressing assertions about path existence.
 * It can be used for a parameterless function so that it has one parameter and thus can be used as infix function.
 */
object exist : Keyword

/**
 * A helper construct to allow expressing assertions about a path being a regular file.
 * It can be used for a parameterless function so that it has one parameter and thus can be used as infix function.
 */
object aRegularFile : Keyword

/**
 * A helper construct to allow expressing assertions about a path being a directory.
 * It can be used for a parameterless function so that it has one parameter and thus can be used as infix function.
 */
object aDirectory : Keyword

/**
 * A helper construct to allow expressing assertions about a path being a readable.
 * It can be used for a parameterless function so that it has one parameter and thus can be used as infix function.
 */
object readable: Keyword

/**
 * A helper construct to allow expressing assertions about a path being a writable.
 * It can be used for a parameterless function so that it has one parameter and thus can be used as infix function.
 */
object writable: Keyword
