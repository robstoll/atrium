package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionBasic.*
import ch.tutteli.atrium.translations.DescriptionPathAssertion.*
import ch.tutteli.niok.*
import ch.tutteli.spek.extensions.MemoizedTempFolder
import ch.tutteli.spek.extensions.memoizedTempFolder
import io.mockk.every
import io.mockk.spyk
import org.spekframework.spek2.Spek
import org.spekframework.spek2.dsl.Skip
import org.spekframework.spek2.dsl.Skip.No
import org.spekframework.spek2.dsl.Skip.Yes
import org.spekframework.spek2.dsl.TestBody
import org.spekframework.spek2.style.specification.Suite
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.attribute.*
import java.nio.file.attribute.AclEntryPermission.READ_DATA
import java.nio.file.attribute.AclEntryPermission.WRITE_DATA
import java.nio.file.attribute.AclEntryType.ALLOW
import java.nio.file.attribute.AclEntryType.DENY
import java.nio.file.attribute.PosixFilePermission.*
import java.util.regex.Pattern.quote

abstract class PathAssertionsSpec(
    exists: Fun0<Path>,
    existsNot: Fun0<Path>,
    startsWith: Fun1<Path, Path>,
    startsNotWith: Fun1<Path, Path>,
    endsWith: Fun1<Path, Path>,
    endsNotWith: Fun1<Path, Path>,
    isReadable: Fun0<Path>,
    isWritable: Fun0<Path>,
    isRegularFile: Fun0<Path>,
    isDirectory: Fun0<Path>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Path>(
        "$describePrefix[Path] ",
        exists.forSubjectLess(),
        existsNot.forSubjectLess(),
        startsWith.forSubjectLess(Paths.get("a")),
        startsNotWith.forSubjectLess(Paths.get("a")),
        endsWith.forSubjectLess(Paths.get("a")),
        endsNotWith.forSubjectLess(Paths.get("a")),
        isReadable.forSubjectLess(),
        isWritable.forSubjectLess(),
        isRegularFile.forSubjectLess(),
        isDirectory.forSubjectLess()
    ) {})

    val tempFolder by memoizedTempFolder()

    // Linux & Mac
    val ifAclNotSupported =
        if (fileSystemSupportsAcls()) No
        else Yes("ACLs are not supported on this file system")

    // Windows
    val ifPosixNotSupported =
        if (fileSystemSupportsPosixPermissions()) No
        else Yes("POSIX permissions are not supported on this file system")

    // Windows with neither symlink nor admin privileges
    val ifSymlinksNotSupported =
        if (fileSystemSupportsCreatingSymlinks()) No
        else Yes("creating symbolic links is not supported on this file system")

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    fun Suite.it(description: String, skip: Skip = No, timeout: Long = delegate.defaultTimeout) =
        SymlinkTestBuilder({ tempFolder }, skipWithLink = ifSymlinksNotSupported) { prefix, innerSkip, body ->
            val skipToUse = if (skip == No) innerSkip else skip
            it(prefix + description, skipToUse, timeout, body)
        }

    fun Suite.itPrintsParentAccessDeniedDetails(block: (Path) -> Unit) {
        // this test case makes only sense on POSIX systems, where a missing execute permission on the parent means
        // that children cannot be accessed. On Windows, for example, one can still access children whithout any
        // permissions on the parent.
        it(
            "POSIX: prints parent permission error details",
            skip = ifPosixNotSupported
        ) withAndWithoutSymlink { maybeLink ->
            val start = tempFolder.newDirectory("startDir")
            val doesNotExist = maybeLink.create(start.resolve("i").resolve("dont").resolve("exist"))

            start.whileWithPermissions(OWNER_READ, OWNER_WRITE) {
                expect {
                    block(doesNotExist)
                }.toThrow<AssertionError>().message {
                    contains(
                        String.format(FAILURE_DUE_TO_PARENT.getDefault(), start),
                        FAILURE_DUE_TO_ACCESS_DENIED.getDefault(),
                        String.format(HINT_ACTUAL_POSIX_PERMISSIONS.getDefault(), "u=rw g= o="),
                        expectedPosixOwnerAndGroupHintFor(start)
                    )
                    containsExplanationFor(maybeLink)
                }
            }
        }
    }

    fun throwingPath(): Path {
        val baseFile = tempFolder.tmpDir.resolve("throwing")

        // using spyk on baseFile and mocking #getFileSystem does not work on Java 8 on Linux (but everywhere else).
        // because of that, we use plain old manual delegation:
        return object : Path by baseFile {
            override fun getFileSystem() = spyk(baseFile.fileSystem) {
                every { provider() } returns spyk(baseFile.fileSystem.provider()) {
                    every {
                        readAttributes(any(), any<Class<BasicFileAttributes>>(), *anyVararg())
                    } throws IOException(TEST_IO_EXCEPTION_MESSAGE)
                    every { checkAccess(any(), *anyVararg()) } throws IOException(TEST_IO_EXCEPTION_MESSAGE)
                }
            }
        }
    }

    fun Suite.itPrintsFileAccessExceptionDetails(block: (Path) -> Unit) {
        it("prints details if an unknown IoException is thrown") {
            expect {
                block(throwingPath())
            }.toThrow<AssertionError>().message {
                contains(String.format(FAILURE_DUE_TO_ACCESS_EXCEPTION.getDefault(), IOException::class.simpleName))
                contains(TEST_IO_EXCEPTION_MESSAGE)
            }
        }
    }

    fun Suite.itPrintsFileAccessProblemDetails(block: (Path) -> Unit) {
        it("prints the closest existing parent if it is a directory") withAndWithoutSymlink { maybeLink ->
            val start = tempFolder.newDirectory("startDir").toRealPath()
            val doesNotExist = maybeLink.create(start.resolve("i").resolve("dont").resolve("exist"))
            val existingParentHintMessage =
                String.format(HINT_CLOSEST_EXISTING_PARENT_DIRECTORY.getDefault(), start)
            expect {
                block(doesNotExist)
            }.toThrow<AssertionError>().message {
                contains(existingParentHintMessage)
                containsExplanationFor(maybeLink)
            }
        }

        it("explains if a parent is a file") withAndWithoutSymlink { maybeLink ->
            val start = tempFolder.newFile("startFile")
            val doesNotExist = maybeLink.create(start.resolve("i").resolve("dont").resolve("exist"))
            val parentErrorMessage = String.format(FAILURE_DUE_TO_PARENT.getDefault(), start)
            val parentErrorDescription =
                String.format(
                    FAILURE_DUE_TO_WRONG_FILE_TYPE.getDefault(),
                    A_FILE.getDefault(),
                    A_DIRECTORY.getDefault()
                )
            expect {
                block(doesNotExist)
            }.toThrow<AssertionError>().message {
                contains(parentErrorMessage, parentErrorDescription)
                containsExplanationFor(maybeLink)
            }
        }

        it("prints an explanation for link loops", skip = ifSymlinksNotSupported) {
            val testDir = tempFolder.newDirectory("loop").toRealPath()
            val a = testDir.resolve("a")
            val b = a.createSymbolicLink(testDir.resolve("b"))
            b.createSymbolicLink(a)

            expect {
                block(a)
            }.toThrow<AssertionError> {
                messageContains(String.format(FAILURE_DUE_TO_LINK_LOOP.getDefault(), "$a -> $b -> $a"))
            }
        }

        itPrintsParentAccessDeniedDetails(block)
        itPrintsFileAccessExceptionDetails(block)
    }

    describeFun(exists.name) {
        val existsFun = exists.lambda
        context("not accessible") {
            it("throws an AssertionError for a non-existent path") withAndWithoutSymlink { maybeLink ->
                val notExisting = maybeLink.create(tempFolder.tmpDir.resolve("nonExistingFile"))
                expect {
                    expect(notExisting).existsFun()
                }.toThrow<AssertionError>().message {
                    contains("${TO.getDefault()}: ${EXIST.getDefault()}")
                    containsExplanationFor(maybeLink)
                }
            }

            itPrintsFileAccessProblemDetails { testFile ->
                expect(testFile).existsFun()
            }
        }

        context("existing") {
            it("does not throw for an existing file") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("test"))
                expect(file).existsFun()
            }

            it("does not throw for an existing directory") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newDirectory("test"))
                expect(file).existsFun()
            }
        }
    }

    describeFun(existsNot.name) {
        val existsNotFun = existsNot.lambda
        context("not accessible") {
            it("does not throw for a non-existent path") withAndWithoutSymlink { maybeLink ->
                val notExisting = maybeLink.create(tempFolder.tmpDir.resolve("nonExistingFile"))
                expect(notExisting).existsNotFun()
            }

            itPrintsParentAccessDeniedDetails { testFile ->
                expect(testFile).existsNotFun()
            }

            itPrintsFileAccessExceptionDetails { testFile ->
                expect(testFile).existsNotFun()
            }
        }

        context("existing") {
            it("throws an AssertionError for a file") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("exists-though-shouldnt"))
                expect {
                    expect(file).existsNotFun()
                }.toThrow<AssertionError>().message {
                    contains(
                        "${NOT_TO.getDefault()}: ${EXIST.getDefault()}",
                        "${WAS.getDefault()}: ${A_FILE.getDefault()}"
                    )
                    containsExplanationFor(maybeLink)
                }
            }

            it("throws an AssertionError for a directory") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("exists-though-shouldnt"))
                expect {
                    expect(folder).existsNotFun()
                }.toThrow<AssertionError>().message {
                    contains(
                        "${NOT_TO.getDefault()}: ${EXIST.getDefault()}",
                        "${WAS.getDefault()}: ${A_DIRECTORY.getDefault()}"
                    )
                    containsExplanationFor(maybeLink)
                }
            }
        }
    }

    describeFun(startsWith.name, startsNotWith.name, endsWith.name, endsNotWith.name) {
        val startsWithFun = startsWith.lambda
        val startsNotWithFun = startsNotWith.lambda
        val endsWithFun = endsWith.lambda
        val endsNotWithFun = endsNotWith.lambda

        val path = "/some/path/for/test"
        val expectPath = expect(Paths.get(path))
        context("path '$path'") {
            it("${startsWith.name} '/some/path/' does not throw") {
                expectPath.startsWithFun(Paths.get("/some/path/"))
            }

            it("${startsWith.name} '/other/path' throws an AssertionError") {
                expect {
                    expectPath.startsWithFun(Paths.get("/other/path"))
                }.toThrow<AssertionError> {
                    messageContains("${STARTS_WITH.getDefault()}:")
                }
            }

            it("${startsNotWith.name} '/other/path' does not throw") {
                expectPath.startsNotWithFun(Paths.get("/other/path"))
            }

            it("${startsNotWith.name} '/some/pa' does not match partials") {
                expectPath.startsNotWithFun(Paths.get("/some/pa"))
            }

            it("${startsNotWith.name} '/some/path' throws an AssertionError") {
                expect {
                    expectPath.startsNotWithFun(Paths.get("/some/path"))
                }.toThrow<AssertionError> {
                    messageContains("${STARTS_NOT_WITH.getDefault()}:")
                }
            }

            it("${endsWith.name} 'for/test' does not throw") {
                expectPath.endsWithFun(Paths.get("for/test"))
            }

            it("${endsWith.name} 'for/another' throws an AssertionError") {
                expect {
                    expectPath.endsWithFun(Paths.get("for/another"))
                }.toThrow<AssertionError> {
                    messageContains("${ENDS_WITH.getDefault()}:")
                }
            }

            it("${endsNotWith.name} 'for/test' throws an AssertionError") {
                expect {
                    expectPath.endsNotWithFun(Paths.get("for/test"))
                }.toThrow<AssertionError> {
                    messageContains("${ENDS_NOT_WITH.getDefault()}:")
                }
            }

            it("${endsNotWith.name} 'for/another' does not throw") {
                expectPath.endsNotWithFun(Paths.get("for/another"))
            }
        }
    }

    describeFun(isReadable.name) {
        val isReadableFun = isReadable.lambda
        val expectedMessage = "${TO_BE.getDefault()}: ${READABLE.getDefault()}"

        context("not accessible") {
            it("throws an AssertionError for non-existent path") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.tmpDir.resolve("nonExistent"))
                expect {
                    expect(file).isReadableFun()
                }.toThrow<AssertionError>().message {
                    contains(expectedMessage, FAILURE_DUE_TO_NO_SUCH_FILE.getDefault())
                    containsExplanationFor(maybeLink)
                }
            }

            itPrintsFileAccessProblemDetails { testFile ->
                expect(testFile).isReadableFun()
            }
        }

        context("readable") {
            it("does not throw for file") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("readable"))
                expect(file).isReadableFun()
            }

            it("does not throw for directory") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("readable"))
                expect(folder).isReadableFun()
            }
        }

        context("POSIX: not readable", skip = ifPosixNotSupported) {
            val expectedPermissionHint = String.format(HINT_ACTUAL_POSIX_PERMISSIONS.getDefault(), "u=wx g=x o=")

            it("throws an AssertionError for a file") withAndWithoutSymlink { maybeLink ->
                val expectedTypeHint = String.format(
                    FAILURE_DUE_TO_PERMISSION_FILE_TYPE_HINT.getDefault(),
                    A_FILE.getDefault(),
                    READABLE.getDefault()
                )

                val file = maybeLink.create(tempFolder.newFile("not-readable"))
                file.whileWithPermissions(OWNER_WRITE, OWNER_EXECUTE, GROUP_EXECUTE) {
                    expect {
                        expect(file).isReadableFun()
                    }.toThrow<AssertionError>().message {
                        contains(
                            expectedMessage,
                            expectedTypeHint,
                            expectedPermissionHint,
                            expectedPosixOwnerAndGroupHintFor(file)
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }

            it("throws an AssertionError for a directory") withAndWithoutSymlink { maybeLink ->
                val expectedTypeHint = String.format(
                    FAILURE_DUE_TO_PERMISSION_FILE_TYPE_HINT.getDefault(),
                    A_DIRECTORY.getDefault(),
                    READABLE.getDefault()
                )

                val folder = maybeLink.create(tempFolder.newDirectory("not-readable"))
                folder.whileWithPermissions(OWNER_WRITE, OWNER_EXECUTE, GROUP_EXECUTE) {
                    expect {
                        expect(folder).isReadableFun()
                    }.toThrow<AssertionError>().message {
                        contains(
                            expectedMessage,
                            expectedTypeHint,
                            expectedPermissionHint,
                            expectedPosixOwnerAndGroupHintFor(folder)
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }
        }

        context("ACL: not readable", skip = ifAclNotSupported) {
            it("throws an AssertionError for a file") withAndWithoutSymlink { maybeLink ->
                val expectedTypeHint = String.format(
                    FAILURE_DUE_TO_PERMISSION_FILE_TYPE_HINT.getDefault(),
                    A_FILE.getDefault(),
                    READABLE.getDefault()
                )

                val file = maybeLink.create(tempFolder.newFile("not-readable"))
                file.whileWithAcl(TestAcls::ownerNoRead) {
                    expect {
                        expect(file).isReadableFun()
                    }.toThrow<AssertionError>().message {
                        contains(
                            expectedMessage,
                            expectedTypeHint,
                            expectedAclOwnerHintFor(file),
                            HINT_ACTUAL_ACL_PERMISSIONS.getDefault()
                        )
                        containsRegex(
                            file.expectedAclEntryPartFor("DENY", "READ_DATA"),
                            // we only check a few of the allowed options
                            file.expectedAclEntryPartFor("ALLOW", "WRITE_ACL"),
                            file.expectedAclEntryPartFor("ALLOW", "WRITE_DATA")
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }

            it("throws an AssertionError for a directory") withAndWithoutSymlink { maybeLink ->
                val expectedTypeHint = String.format(
                    FAILURE_DUE_TO_PERMISSION_FILE_TYPE_HINT.getDefault(),
                    A_DIRECTORY.getDefault(),
                    READABLE.getDefault()
                )

                val folder = maybeLink.create(tempFolder.newDirectory("not-readable"))
                folder.whileWithAcl(TestAcls::ownerNoRead) {
                    expect {
                        expect(folder).isReadableFun()
                    }.toThrow<AssertionError>().message {
                        contains(
                            expectedMessage,
                            expectedTypeHint,
                            expectedAclOwnerHintFor(folder),
                            HINT_ACTUAL_ACL_PERMISSIONS.getDefault()
                        )
                        containsRegex(
                            folder.expectedAclEntryPartFor("DENY", "READ_DATA"),
                            // we only check a few of the allowed options
                            folder.expectedAclEntryPartFor("ALLOW", "WRITE_ACL"),
                            folder.expectedAclEntryPartFor("ALLOW", "WRITE_DATA")
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }
        }
    }

    describeFun(isWritable.name) {
        val isWritableFun = isWritable.lambda
        val expectedMessage = "${TO_BE.getDefault()}: ${WRITABLE.getDefault()}"

        context("not accessible") {
            it("throws an AssertionError for a non-existent path") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.tmpDir.resolve("nonExistent"))
                expect {
                    expect(file).isWritableFun()
                }.toThrow<AssertionError>().message {
                    contains(
                        expectedMessage,
                        FAILURE_DUE_TO_NO_SUCH_FILE.getDefault()
                    )
                    containsExplanationFor(maybeLink)
                }
            }

            itPrintsFileAccessProblemDetails { testFile ->
                expect(testFile).isWritableFun()
            }
        }

        context("writable") {
            it("does not throw for file") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("writable"))
                expect(file).isWritableFun()
            }

            it("does not throw for directory") withAndWithoutSymlink { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("writable"))
                expect(folder).isWritableFun()
            }
        }

        context("POSIX: not writable", skip = ifPosixNotSupported) {
            val expectedPermissionHint = String.format(HINT_ACTUAL_POSIX_PERMISSIONS.getDefault(), "u=rx g= o=x")

            it("throws an AssertionError for a file") withAndWithoutSymlink { maybeLink ->
                val expectedTypeHint = String.format(
                    FAILURE_DUE_TO_PERMISSION_FILE_TYPE_HINT.getDefault(),
                    A_FILE.getDefault(),
                    WRITABLE.getDefault()
                )

                val file = maybeLink.create(tempFolder.newFile("not-writable"))
                file.whileWithPermissions(OWNER_READ, OWNER_EXECUTE, OTHERS_EXECUTE) {
                    expect {
                        expect(file).isWritableFun()
                    }.toThrow<AssertionError>().message {
                        contains(
                            expectedMessage,
                            expectedTypeHint,
                            expectedPermissionHint,
                            expectedPosixOwnerAndGroupHintFor(file)
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }

            it("throws an AssertionError for a directory") withAndWithoutSymlink { maybeLink ->
                val expectedHint = String.format(
                    FAILURE_DUE_TO_PERMISSION_FILE_TYPE_HINT.getDefault(),
                    A_DIRECTORY.getDefault(),
                    WRITABLE.getDefault()
                )

                val folder = maybeLink.create(tempFolder.newDirectory("not-writable"))
                folder.whileWithPermissions(OWNER_READ, OWNER_EXECUTE, OTHERS_EXECUTE) {
                    expect {
                        expect(folder).isWritableFun()
                    }.toThrow<AssertionError>().message {
                        contains(
                            expectedMessage,
                            expectedHint,
                            expectedPermissionHint,
                            expectedPosixOwnerAndGroupHintFor(folder)
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }
        }

        context("ACL: not writable", skip = ifAclNotSupported) {
            it("throws an AssertionError for a file") withAndWithoutSymlink { maybeLink ->
                val expectedTypeHint = String.format(
                    FAILURE_DUE_TO_PERMISSION_FILE_TYPE_HINT.getDefault(),
                    A_FILE.getDefault(),
                    WRITABLE.getDefault()
                )

                val file = maybeLink.create(tempFolder.newFile("not-writable"))
                file.whileWithAcl(TestAcls::ownerNoWrite) {
                    expect {
                        expect(file).isWritableFun()
                    }.toThrow<AssertionError>().message {
                        contains(
                            expectedMessage,
                            expectedTypeHint,
                            expectedAclOwnerHintFor(file),
                            HINT_ACTUAL_ACL_PERMISSIONS.getDefault()
                        )
                        containsRegex(
                            file.expectedAclEntryPartFor("ALLOW", "READ_DATA"),
                            file.expectedAclEntryPartFor("ALLOW", "EXECUTE")
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }

            it("throws an AssertionError for a directory") withAndWithoutSymlink { maybeLink ->
                val expectedHint = String.format(
                    FAILURE_DUE_TO_PERMISSION_FILE_TYPE_HINT.getDefault(),
                    A_DIRECTORY.getDefault(),
                    WRITABLE.getDefault()
                )

                val folder = maybeLink.create(tempFolder.newDirectory("not-writable"))
                folder.whileWithAcl(TestAcls::ownerNoWrite) {
                    expect {
                        expect(folder).isWritableFun()
                    }.toThrow<AssertionError>().message {
                        contains(
                            expectedMessage,
                            expectedHint,
                            expectedAclOwnerHintFor(folder),
                            HINT_ACTUAL_ACL_PERMISSIONS.getDefault()
                        )
                        containsRegex(
                            folder.expectedAclEntryPartFor("ALLOW", "READ_DATA"),
                            folder.expectedAclEntryPartFor("ALLOW", "EXECUTE")
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }
        }
    }

    describeFun(isRegularFile.name) {
        val isRegularFileFun = isRegularFile.lambda
        val expectedMessage = "${TO_BE.getDefault()}: ${A_FILE.getDefault()}"

        context("not accessible") {
            it("throws an AssertionError for a non-existent path") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.tmpDir.resolve("nonExistent"))
                expect {
                    expect(file).isRegularFileFun()
                }.toThrow<AssertionError>().message {
                    contains(
                        expectedMessage,
                        FAILURE_DUE_TO_NO_SUCH_FILE.getDefault()
                    )
                    containsExplanationFor(maybeLink)
                }
            }

            itPrintsFileAccessProblemDetails { testFile ->
                expect(testFile).isRegularFileFun()
            }
        }

        it("does not throw for a file") withAndWithoutSymlink { maybeLink ->
            val file = maybeLink.create(tempFolder.newFile("test"))
            expect(file).isRegularFileFun()
        }

        it("throws an AssertionError for a directory") withAndWithoutSymlink { maybeLink ->
            val folder = maybeLink.create(tempFolder.newDirectory("test"))
            expect {
                expect(folder).isRegularFileFun()
            }.toThrow<AssertionError>().message {
                contains(
                    expectedMessage,
                    "${WAS.getDefault()}: ${A_DIRECTORY.getDefault()}"
                )
                containsExplanationFor(maybeLink)
            }
        }
    }

    describeFun(isDirectory.name) {
        val isDirectoryFun = isDirectory.lambda
        val expectedMessage = "${TO_BE.getDefault()}: ${A_DIRECTORY.getDefault()}"

        context("not accessible") {
            it("throws an AssertionError for a non-existent path") withAndWithoutSymlink { maybeLink ->
                val file = maybeLink.create(tempFolder.tmpDir.resolve("nonExistent"))
                expect {
                    expect(file).isDirectoryFun()
                }.toThrow<AssertionError>().message {
                    contains(expectedMessage, FAILURE_DUE_TO_NO_SUCH_FILE.getDefault())
                    containsExplanationFor(maybeLink)
                }
            }

            itPrintsFileAccessProblemDetails { testFile ->
                expect(testFile).isDirectoryFun()
            }
        }

        it("throws an AssertionError for a file") withAndWithoutSymlink { maybeLink ->
            val file = maybeLink.create(tempFolder.newFile("test"))
            expect {
                expect(file).isDirectoryFun()
            }.toThrow<AssertionError>().message {
                contains(expectedMessage, "${WAS.getDefault()}: ${A_FILE.getDefault()}")
                containsExplanationFor(maybeLink)
            }
        }

        it("does not throw for a directory") withAndWithoutSymlink { maybeLink ->
            val folder = maybeLink.create(tempFolder.newDirectory("test"))
            expect(folder).isDirectoryFun()
        }
    }
})

private const val TEST_IO_EXCEPTION_MESSAGE = "unknown test error"

internal object TestAcls {
    // although counter-intuitive, the entries must be in this order!
    fun ownerNoRead(owner: UserPrincipal) = listOf(
        aclEntry(DENY, owner, READ_DATA),
        aclEntry(ALLOW, owner, *AclEntryPermission.values())
    )

    fun ownerNoWrite(owner: UserPrincipal) = listOf(
        aclEntry(DENY, owner, WRITE_DATA),
        aclEntry(ALLOW, owner, *AclEntryPermission.values())
    )
}

private fun aclEntry(type: AclEntryType, principal: UserPrincipal, vararg permissions: AclEntryPermission) =
    AclEntry.newBuilder()
        .setType(type)
        .setPrincipal(principal)
        .setPermissions(*permissions)
        .build()

/**
 * Sets the provided [permissionsToUse] on `this` path, executes the [block], and restores the permissions afterwards.
 */
private inline fun Path.whileWithPermissions(vararg permissionsToUse: PosixFilePermission, block: () -> Unit) =
    whileWithPermissions(setOf(*permissionsToUse), block = block)

/**
 * Sets the provided [permissionsToUse] on `this` path, executes the [block], and restores the permissions afterwards.
 */
private inline fun Path.whileWithPermissions(
    permissionsToUse: Set<PosixFilePermission>,
    block: () -> Unit
) {
    val oldPermissions = this.posixFilePersmissions
    this.setPosixFilePermissions(permissionsToUse)
    try {
        block()
    } finally {
        this.setPosixFilePermissions(oldPermissions)
    }
}

/**
 * Sets the provided [aclToUse] on `this` path, executes the [block], and restores the permissions afterwards.
 *
 * @param aclToUse A function that gets passed the [UserPrincipal] for this file’s owner and returns an access control list
 */
private inline fun Path.whileWithAcl(aclToUse: (owner: UserPrincipal) -> List<AclEntry>, block: () -> Unit) {
    val aclView = this.getFileAttributeView<AclFileAttributeView>()
        ?: throw IllegalStateException("Failed to get ACL for $this")
    val oldAcl = aclView.acl
    aclView.acl = aclToUse(aclView.owner)
    try {
        block()
    } finally {
        aclView.acl = oldAcl
    }
}

class SymlinkTestBuilder(
    private val tempFolderProvider: () -> MemoizedTempFolder,
    private val skipWithLink: Skip,
    private val testBodyConsumer: (testPrefix: String, skip: Skip, testBody: TestBody.() -> Unit) -> Unit
) {
    /**
     * Allows to run the provided test body multiple times in order to check proper symbolic link handling:
     *  * On the first invocation of [body], the `maybeLink` parameter will be [NoLink], i.e., the test run will not use symbolic links.
     *  * On the second invocation of [body], the `maybeLink` parameter will be [SimpleLink], i.e., the test run will use a symbolic link that points to the actual subject.
     *
     *  In effect, the test body will be executed once “directly”, and once via a symbolic link to the tested path.
     *  The test body is expected to:
     *   * pass the test subject (a [Path]) through [MaybeLink.create]
     *   * call [containsExplanationFor] when checking an assertion error
     */
    internal infix fun withAndWithoutSymlink(body: TestBody.(maybeLink: MaybeLink) -> Unit) {
        callWith(NoLink(), No, body)
        callWith(SimpleLink(tempFolderProvider), skipWithLink, body)
    }

    private inline fun callWith(
        maybeLink: InternalMaybeLink,
        skip: Skip,
        crossinline body: TestBody.(MaybeLink) -> Unit
    ) {
        val prefix = maybeLink.description.run { if (length > 0) "$this: " else "" }
        testBodyConsumer(prefix, skip) {
            body(maybeLink)
            maybeLink.confirmCreateWasCalled()
        }
    }
}

internal fun expectedPosixOwnerAndGroupHintFor(path: Path): String {
    val posixAttributes = path.readAttributes<PosixFileAttributes>()
    return String.format(
        HINT_OWNER_AND_GROUP.getDefault(),
        posixAttributes.owner().name,
        posixAttributes.group().name
    )
}

internal val Path.aclOwner: UserPrincipal
    get() = this.getFileAttributeView<AclFileAttributeView>()?.owner
        ?: throw IllegalStateException("failed to read access control list of $this")

internal fun expectedAclOwnerHintFor(path: Path): String = String.format(HINT_OWNER.getDefault(), path.aclOwner.name)
internal fun Path.expectedAclEntryPartFor(type: String, permission: String) =
    "$type ${quote(this.aclOwner.name)}: .*$permission.*"

/**
 * Helper to create test cases for both with and without symbolic link handling.
 */
internal interface MaybeLink {
    fun create(path: Path): Path
    fun <T : CharSequence> checkAssertionErrorMessage(expect: Expect<T>)
}

internal abstract class InternalMaybeLink(internal val description: String) : MaybeLink {
    private var createWasCalled = false
    final override fun create(path: Path): Path {
        createWasCalled = true
        return callCheckedCreate(path)
    }

    internal abstract fun callCheckedCreate(path: Path): Path
    final override fun <T : CharSequence> checkAssertionErrorMessage(expect: Expect<T>) {
        confirmCreateWasCalled()
        callCheckedCheckAssertionErrorMessage(expect)
    }

    internal abstract fun <T : CharSequence> callCheckedCheckAssertionErrorMessage(expect: Expect<T>)
    internal fun confirmCreateWasCalled() {
        check(createWasCalled) { "Expected the symbolic link creator function to have been called!" }
    }
}

internal class NoLink : InternalMaybeLink("") {
    override fun callCheckedCreate(path: Path) = path
    override fun <T : CharSequence> callCheckedCheckAssertionErrorMessage(expect: Expect<T>) {}
}

internal class SimpleLink(private val tempFolderProvider: () -> MemoizedTempFolder) :
    InternalMaybeLink("via symbolic link") {
    private var link: Path? = null
    private var path: Path? = null
    override fun callCheckedCreate(path: Path): Path {
        this.path = path
        val tempFolder = tempFolderProvider()
        // we assume in the tests that the temporary directory does not contain symlinks, thus we resolve them here.
        val link = Files.createSymbolicLink(tempFolder.tmpDir.toRealPath().resolve("__linkTo_" + path.fileName), path)
        this.link = link
        return link
    }

    override fun <T : CharSequence> callCheckedCheckAssertionErrorMessage(expect: Expect<T>) {
        expect.contains(TranslatableWithArgs(HINT_FOLLOWED_SYMBOLIC_LINK, link!!, path!!).getDefault())
    }
}

internal fun <T : CharSequence> Expect<T>.containsExplanationFor(maybeLink: MaybeLink) =
    maybeLink.checkAssertionErrorMessage(this)
