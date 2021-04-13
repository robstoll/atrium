package ch.tutteli.atrium.logic.impl.creating.filesystem

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.filesystem.Failure
import ch.tutteli.atrium.logic.creating.filesystem.IoResult
import ch.tutteli.atrium.logic.creating.filesystem.Success
import ch.tutteli.atrium.logic.creating.filesystem.runCatchingIo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.nio.file.Paths

object IoResultSpec : Spek({
    describe("runCatchingIo") {
        val testPath = Paths.get("/test")

        it("creates a Success if the block completes normally") {
            val result = testPath.runCatchingIo { "testString" }
            expect(result).toBeAnInstanceOf(fun Expect<Success<String>>.() {
                feature(IoResult<*>::path).toEqual(testPath)
                feature(Success<*>::value).toEqual("testString")
            })
        }

        it("creates a Failure if the block thrown an IOException") {
            val testException = NoSuchFileException(testPath.toFile())
            val result = testPath.runCatchingIo { throw testException }
            expect(result).toBeAnInstanceOf<Failure> {
                feature(IoResult<*>::path).toEqual(testPath)
                feature(Failure::exception).toBeTheInstance(testException)
            }
        }

        it("re-throws other exceptions") {
            expect {
                testPath.runCatchingIo { throw IllegalStateException() }
            }.toThrow<IllegalStateException>()
        }
    }
})
