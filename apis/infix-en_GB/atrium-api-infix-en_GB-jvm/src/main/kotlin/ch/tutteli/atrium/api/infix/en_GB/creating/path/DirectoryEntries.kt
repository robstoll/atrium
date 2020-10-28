package ch.tutteli.atrium.api.infix.en_GB.creating.path

/**
 *  Parameter object which collects directory entries (as [String]s).
 *  Use the function `directoryEntry(String, vararg String)` to create this representation.
 *
 *  @since 0.14.0
 */
// TODO make inline once we drop support for Kotlin 1.2
class DirectoryEntries(val entries: List<String>)

