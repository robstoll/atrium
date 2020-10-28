package ch.tutteli.atrium.api.infix.en_GB.creating.path

import ch.tutteli.atrium.domain.builders.utils.VarArgHelper

/**
 *  Parameter object which collects directory entries (as [String]s).
 *  Use the function `directoryEntry(String, vararg String)` to create this representation.
 *
 *  @since 0.14.0
 */
class DirectoryEntries(override val expected: String, override val otherExpected: Array<out String>) :
    VarArgHelper<String>

