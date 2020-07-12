//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.robstoll.lib.creating.filesystem

import java.io.IOException
import java.nio.file.Path

/**
 * Executes the given [block] and catches [IOException]s.
 *
 * @return [Success] with [this] path and [block]’s result if [block] executes successfully.
 *  [Failure] with [this] path and the thrown [IOException] if [block] throws an [IOException]
 *  @throws Exception any exception that is thrown by [block] if it is not an [IOException]
 */
@Deprecated("use runCatchingIo from atrium-logic; will be removed with 1.0.0")
inline fun <T> Path.runCatchingIo(block: Path.() -> T): IoResult<T> = try {
    Success(this, this.block())
} catch (e: IOException) {
    Failure(this, e)
}

@Deprecated("use IoResult from atrium-logic; will be removed with 1.0.0")
sealed class IoResult<out T>(val path: Path)
@Deprecated("use Success from atrium-logic; will be removed with 1.0.0")
class Success<out T>(path: Path, val value: T) : IoResult<T>(path)
@Deprecated("use Failure from atrium-logic; will be removed with 1.0.0")
class Failure(path: Path, val exception: IOException) : IoResult<Nothing>(path)
