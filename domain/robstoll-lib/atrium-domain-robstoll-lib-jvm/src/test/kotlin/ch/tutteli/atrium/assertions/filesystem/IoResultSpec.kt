package ch.tutteli.atrium.assertions.filesystem

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.domain.robstoll.lib.creating.filesystem.Failure
import ch.tutteli.atrium.domain.robstoll.lib.creating.filesystem.IoResult
import ch.tutteli.atrium.domain.robstoll.lib.creating.filesystem.Success
import ch.tutteli.atrium.domain.robstoll.lib.creating.filesystem.runCatchingIo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.nio.file.Paths

object IoResultSpec : Spek({
    describe("runCatchingIo") {
        val testPath = Paths.get("/test")

        it("creates a Success if the block completes normally") {
            val result = testPath.runCatchingIo { "testString" }
            expect(result).isA<Success<String>> {
                feature(IoResult<*>::path).toBe(testPath)
                feature(Success<*>::value).toBe("testString")
            }
        }

        it("creates a Failure if the block thrown an IOException") {
            val testException = NoSuchFileException(testPath.toFile())
            val result = testPath.runCatchingIo { throw testException }
            expect(result).isA<Failure> {
                feature(IoResult<*>::path).toBe(testPath)
                feature(Failure::exception).isSameAs(testException)
            }
        }

        it("re-throws other exceptions") {
            expect {
                testPath.runCatchingIo { throw IllegalStateException() }
            }.toThrow<IllegalStateException>()
        }
    }
})
