@file:Suppress(
    "OVERRIDE_BY_INLINE",
    "NOTHING_TO_INLINE",
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */
)

package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.PathAssertions
import ch.tutteli.atrium.domain.creating.pathAssertions
import java.nio.file.Path

/**
 * Delegates inter alia to the implementation of [PathAssertions].
 * In detail, it implements [PathAssertions] by delegating to [pathAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object PathAssertionsBuilder : PathAssertions {
    override inline fun <T : Path> startsWith(expect: Expect<T>, expected: Path) =
        pathAssertions.startsWith(expect, expected)

    override inline fun <T : Path> startsNotWith(expect: Expect<T>, expected: Path) =
        pathAssertions.startsNotWith(expect, expected)

    override inline fun <T : Path> endsWith(expect: Expect<T>, expected: Path) =
        pathAssertions.endsWith(expect, expected)

    override inline fun <T : Path> endsNotWith(expect: Expect<T>, expected: Path) =
        pathAssertions.endsNotWith(expect, expected)

    override inline fun <T : Path> exists(expect: Expect<T>) =
        pathAssertions.exists(expect)

    override inline fun <T : Path> existsNot(expect: Expect<T>) =
        pathAssertions.existsNot(expect)

    override inline fun <T : Path> fileName(expect: Expect<T>) =
        pathAssertions.fileName(expect)

    override inline fun <T : Path> extension(expect: Expect<T>) =
        pathAssertions.extension(expect)

    override inline fun <T : Path> fileNameWithoutExtension(expect: Expect<T>) =
        pathAssertions.fileNameWithoutExtension(expect)

    override inline fun <T : Path> parent(expect: Expect<T>) =
        pathAssertions.parent(expect)

    override inline fun <T : Path> isReadable(expect: Expect<T>) =
        pathAssertions.isReadable(expect)

    override inline fun <T : Path> isWritable(expect: Expect<T>) =
        pathAssertions.isWritable(expect)

    override fun <T : Path> isRegularFile(expect: Expect<T>) =
        pathAssertions.isRegularFile(expect)

    override fun <T : Path> isDirectory(expect: Expect<T>) =
        pathAssertions.isDirectory(expect)
}
