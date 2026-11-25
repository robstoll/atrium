package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.api.verbs.internal.testfactories.ExpectTestExecutableForTests
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.testfactories.TestFactoryBuilder
import ch.tutteli.atrium.translations.DescriptionBasic.*
import ch.tutteli.atrium.translations.DescriptionPathAssertion.*
import ch.tutteli.niok.*
import java.io.IOException
import java.nio.charset.Charset
import java.nio.file.*
import java.nio.file.attribute.*
import java.nio.file.attribute.AclEntryPermission.*
import java.nio.file.attribute.AclEntryType.ALLOW
import java.nio.file.attribute.AclEntryType.DENY
import java.nio.file.attribute.PosixFilePermission.*
import java.util.regex.Pattern.quote
import org.junit.jupiter.api.Assumptions.assumeFalse

/**
 * A test is skipped via `assumeFalse` in case [skip] is true, stating [reason] as reason why it is skipped.
 * Replacement for Spek's Skip DSL: [No] means the test is executed, [Yes] skips the test for the given reason.
 */
private class Skip(val skip: Boolean, val reason: String)

private val No = Skip(skip = false, reason = "")

private fun Yes(reason: String) = Skip(skip = true, reason = reason)

@Suppress("FunctionName")
abstract class AbstractPathExpectationsTest(
    private val toExistSpec: Fun0<Path>,
    private val notToExistSpec: Fun0<Path>,
    private val toStartWithSpec: Fun1<Path, Path>,
    private val notToStartWithSpec: Fun1<Path, Path>,
    private val toEndWithSpec: Fun1<Path, Path>,
    private val notToEndWithSpec: Fun1<Path, Path>,
    private val toBeReadableSpec: Fun0<Path>,
    private val notToBeReadableSpec: Fun0<Path>,
    private val toBeWritableSpec: Fun0<Path>,
    private val notToBeWritableSpec: Fun0<Path>,
    private val toBeExecutableSpec: Fun0<Path>,
    private val notToBeExecutableSpec: Fun0<Path>,
    private val toBeRegularFileSpec: Fun0<Path>,
    private val toBeADirectorySpec: Fun0<Path>,
    private val toBeASymbolicLinkSpec: Fun0<Path>,
    private val toBeAbsoluteSpec: Fun0<Path>,
    private val toBeRelativeSpec: Fun0<Path>,
    private val toBeAnEmptyDirectorySpec: Fun0<Path>,
    private val toHaveTheDirectoryEntrySpec: Fun1<Path, String>,
    private val toHaveTheDirectoryEntriesSpec: Fun2<Path, String, Array<out String>>,
    private val toHaveTheSameBinaryContentAsSpec: Fun1<Path, Path>,
    private val toHaveTheSameTextualContentAsSpec: Fun3<Path, Path, Charset, Charset>,
    private val toHaveTheSameTextualContentAsDefaultArgsSpec: Fun1<Path, Path>,
    private val parentFeatureSpec: Feature0<Path, Path>,
    private val parentSpec: Fun1<Path, Expect<Path>.() -> Unit>,
    private val resolveFeatureSpec: Feature1<Path, String, Path>,
    private val resolveSpec: Fun2<Path, String, Expect<Path>.() -> Unit>,
    private val fileNameFeatureSpec: Feature0<Path, String>,
    private val fileNameSpec: Fun1<Path, Expect<String>.() -> Unit>,
    private val fileNameWithoutExtensionFeatureSpec: Feature0<Path, String>,
    private val fileNameWithoutExtensionSpec: Fun1<Path, Expect<String>.() -> Unit>,
    private val extensionFeatureSpec: Feature0<Path, String>,
    private val extensionSpec: Fun1<Path, Expect<String>.() -> Unit>
) : ExpectationFunctionBaseTest() {

    private val tempFolder = SpecTempFolder()

    // Linux & Mac
    private val ifAclNotSupported =
        if (fileSystemSupportsAcls()) No
        else Yes("ACLs are not supported on this file system")

    // Windows
    private val ifPosixNotSupported =
        if (fileSystemSupportsPosixPermissions()) No
        else Yes("POSIX permissions are not supported on this file system")

    // Windows with neither symlink nor admin privileges
    private val ifSymlinksNotSupported =
        if (fileSystemSupportsCreatingSymlinks()) No
        else Yes("creating symbolic links is not supported on this file system")

    private val supportsSymlinks = fileSystemSupportsCreatingSymlinks()

    private fun TestFactoryBuilder<ExpectTestExecutableForTests>.itWithAndWithoutSymlink(
        description: String,
        skip: Skip = No,
        forceNoLink: Boolean = false,
        body: (MaybeLink) -> Unit
    ) {
        val skipWithLink = if (skip == No) ifSymlinksNotSupported else skip

        it(description) {
            assumeFalse(skip.skip, skip.reason)
            val maybeLink = NoLink()
            body(maybeLink)
            maybeLink.confirmCreateWasCalled()
        }
        if (forceNoLink.not()) {
            it("via symbolic link: $description") {
                assumeFalse(skipWithLink.skip, skipWithLink.reason)
                val maybeLink = SimpleLink { tempFolder }
                body(maybeLink)
                maybeLink.confirmCreateWasCalled()
            }
        }
    }

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        toExistSpec.forSubjectLessTest(),
        notToExistSpec.forSubjectLessTest(),
        toStartWithSpec.forSubjectLessTest(Paths.get("a")),
        notToStartWithSpec.forSubjectLessTest(Paths.get("a")),
        toEndWithSpec.forSubjectLessTest(Paths.get("a")),
        notToEndWithSpec.forSubjectLessTest(Paths.get("a")),
        toBeReadableSpec.forSubjectLessTest(),
        notToBeReadableSpec.forSubjectLessTest(),
        toBeWritableSpec.forSubjectLessTest(),
        notToBeWritableSpec.forSubjectLessTest(),
        toBeExecutableSpec.forSubjectLessTest(),
        toBeRegularFileSpec.forSubjectLessTest(),
        toBeADirectorySpec.forSubjectLessTest(),
        toBeASymbolicLinkSpec.forSubjectLessTest(),
        toBeAbsoluteSpec.forSubjectLessTest(),
        toBeRelativeSpec.forSubjectLessTest(),
        toBeAnEmptyDirectorySpec.forSubjectLessTest(),
        toHaveTheDirectoryEntrySpec.forSubjectLessTest("a"),
        toHaveTheDirectoryEntriesSpec.forSubjectLessTest("a", arrayOf("b", "c")),
        toHaveTheSameBinaryContentAsSpec.forSubjectLessTest(Paths.get("a")),
        toHaveTheSameTextualContentAsSpec.forSubjectLessTest(Paths.get("a"), Charsets.ISO_8859_1, Charsets.ISO_8859_1),
        toHaveTheSameTextualContentAsDefaultArgsSpec.forSubjectLessTest(Paths.get("a")),
        parentFeatureSpec.forSubjectLessTest().withFeatureSuffix(),
        parentSpec.forSubjectLessTest { },
        resolveFeatureSpec.forSubjectLessTest("test").withFeatureSuffix(),
        resolveSpec.forSubjectLessTest("test") { toEqual(Paths.get("a/my.txt")) },
        fileNameFeatureSpec.forSubjectLessTest().withFeatureSuffix(),
        fileNameSpec.forSubjectLessTest { },
        fileNameWithoutExtensionFeatureSpec.forSubjectLessTest().withFeatureSuffix(),
        fileNameWithoutExtensionSpec.forSubjectLessTest { },
        extensionFeatureSpec.forSubjectLessTest().withFeatureSuffix(),
        extensionSpec.forSubjectLessTest { }
    )

    private val fileNameDescr = FILE_NAME.getDefault()
    private val fileNameWithoutExtensionDescr = FILE_NAME_WITHOUT_EXTENSION.getDefault()

    private fun TestFactoryBuilder<ExpectTestExecutableForTests>.itPrintsParentAccessDeniedDetails(
        forceNoLink: Boolean = false,
        block: (Path) -> Unit
    ) {
        itWithAndWithoutSymlink(
            "POSIX: prints parent permission error details",
            skip = ifPosixNotSupported,
            forceNoLink = forceNoLink
        ) { maybeLink ->
            // this test case makes only sense on POSIX systems, where a missing execute permission on the parent means
            // that children cannot be accessed. On Windows, for example, one can still access children without any
            // permissions on the parent.
            val start = tempFolder.newDirectory("startDir")
            val doesNotExist = maybeLink.create(start.resolve("i").resolve("dont").resolve("exist"))

            start.whileWithPermissions(OWNER_READ, OWNER_WRITE, GROUP_READ) {
                expect {
                    block(doesNotExist)
                }.toThrow<AssertionError>().message {
                    toContain(
                        String.format(FAILURE_DUE_TO_PARENT.getDefault(), start),
                        FAILURE_DUE_TO_ACCESS_DENIED.getDefault(),
                        String.format(HINT_ACTUAL_POSIX_PERMISSIONS.getDefault(), "u=rw g=r o="),
                        expectedPosixOwnerAndGroupHintFor(start)
                    )
                    containsExplanationFor(maybeLink)
                }
            }
        }
    }

    private fun throwingPath(): Path {
        val baseFile = tempFolder.tmpDir.resolve("throwing")

        // using spyk on baseFile and mocking #getFileSystem does not work on Java 8 on Linux (but everywhere else).
        // because of that, we use plain old manual delegation:
        return object : Path by baseFile {
            override fun getFileSystem() = throw IOException(TEST_IO_EXCEPTION_MESSAGE)
        }
    }

    private fun TestFactoryBuilder<ExpectTestExecutableForTests>.itPrintsFileAccessExceptionDetails(block: (Path) -> Unit) {
        it("prints details if an unknown IoException is thrown") {
            expect {
                block(throwingPath())
            }.toThrow<AssertionError>().message {
                toContain(String.format(FAILURE_DUE_TO_ACCESS_EXCEPTION.getDefault(), IOException::class.simpleName))
                toContain(TEST_IO_EXCEPTION_MESSAGE)
            }
        }
    }

    private fun TestFactoryBuilder<ExpectTestExecutableForTests>.itPrintsFileAccessProblemDetails(
        forceNoLink: Boolean = false,
        checkParentHints: Boolean = true,
        block: (Path) -> Unit
    ) {
        itWithAndWithoutSymlink(
            "prints the closest existing parent if it is a directory",
            forceNoLink = forceNoLink
        ) { maybeLink ->
            val start = tempFolder.newDirectory("startDir").toRealPath()
            val doesNotExist = maybeLink.create(start.resolve("i").resolve("dont").resolve("exist"))
            val existingParentHintMessage =
                String.format(HINT_CLOSEST_EXISTING_PARENT_DIRECTORY.getDefault(), start)
            expect {
                block(doesNotExist)
            }.toThrow<AssertionError>().message {
                toContain(existingParentHintMessage)
                containsExplanationFor(maybeLink)
            }
        }

        itWithAndWithoutSymlink(
            "explains if a parent is a file",
            forceNoLink = forceNoLink
        ) { maybeLink ->
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
                toContain(parentErrorMessage, parentErrorDescription)
                containsExplanationFor(maybeLink)
            }
        }

        it("prints an explanation for link loops") {
            if (!forceNoLink && supportsSymlinks) {
                val testDir = tempFolder.newDirectory("loop").toRealPath()
                val a = testDir.resolve("a")
                val b = a.createSymbolicLink(testDir.resolve("b"))
                b.createSymbolicLink(a)

                expect {
                    block(a)
                }.toThrow<AssertionError> {
                    messageToContain(String.format(FAILURE_DUE_TO_LINK_LOOP.getDefault(), "$a -> $b -> $a"))
                }
            }
        }

        if (checkParentHints) {
            itPrintsParentAccessDeniedDetails(forceNoLink, block)
        }
        itPrintsFileAccessExceptionDetails(block)
    }

    @TestFactory
    fun toExist_notToExist() = testFactory(toExistSpec, notToExistSpec) { toExistFun, notToExistFun ->
        describe("not accessible") {
            describe("non-existent path") {
                itWithAndWithoutSymlink("${toExistSpec.name} - throws an AssertionError") { maybeLink ->
                    val notExisting = maybeLink.create(tempFolder.tmpDir.resolve("nonExistingFile"))
                    expect {
                        expect(notExisting).toExistFun()
                    }.toThrow<AssertionError>().message {
                        toContain("${TO.getDefault()}: ${EXIST.getDefault()}")
                        containsExplanationFor(maybeLink)
                    }
                }
                itWithAndWithoutSymlink("${notToExistSpec.name} - does not throw") { maybeLink ->
                    val notExisting = maybeLink.create(tempFolder.tmpDir.resolve("nonExistingFile"))
                    expect(notExisting).notToExistFun()
                }
            }

            describe("${toExistSpec.name} has failure hints") {
                itPrintsFileAccessProblemDetails { testFile ->
                    expect(testFile).toExistFun()
                }
            }

            describe("${notToExistSpec.name} has failure hints") {
                itPrintsParentAccessDeniedDetails { testFile ->
                    expect(testFile).notToExistFun()
                }

                itPrintsFileAccessExceptionDetails { testFile ->
                    expect(testFile).notToExistFun()
                }
            }
        }

        describe("existing") {
            describe("file") {
                itWithAndWithoutSymlink("${toExistSpec.name} - does not throw") { maybeLink ->
                    val file = maybeLink.create(tempFolder.newFile("test"))
                    expect(file).toExistFun()
                }
                itWithAndWithoutSymlink("${notToExistSpec.name} - throws an AssertionError") { maybeLink ->
                    val file = maybeLink.create(tempFolder.newFile("toExist-though-shouldnt"))
                    expect {
                        expect(file).notToExistFun()
                    }.toThrow<AssertionError>().message {
                        toContain(
                            "${NOT_TO.getDefault()}: ${EXIST.getDefault()}",
                            "${WAS.getDefault()}: ${A_FILE.getDefault()}"
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }
            describe("directory") {
                itWithAndWithoutSymlink("${toExistSpec.name} - does not throw") { maybeLink ->
                    val file = maybeLink.create(tempFolder.newDirectory("test"))
                    expect(file).toExistFun()
                }

                itWithAndWithoutSymlink("${notToExistSpec.name} - throws an AssertionError") { maybeLink ->
                    val folder = maybeLink.create(tempFolder.newDirectory("toExist-though-shouldnt"))
                    expect {
                        expect(folder).notToExistFun()
                    }.toThrow<AssertionError>().message {
                        toContain(
                            "${NOT_TO.getDefault()}: ${EXIST.getDefault()}",
                            "${WAS.getDefault()}: ${A_DIRECTORY.getDefault()}"
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }
        }
    }

    @TestFactory
    fun toStartWith_notToStartWith() = testFactory(toStartWithSpec, notToStartWithSpec) { toStartWithFun, notToStartWithFun ->
        val path = "/some/path/for/test"
        val expectPath = expect(Paths.get(path))
        describe("path '$path'") {
            mapOf(
                "/some/pa" to false,
                "/some/path" to true,
                "/some/path/" to true,
                "/some/path/fo" to false,
                "/some/path/for" to true,
                "/some/path/for/" to true,
                "/some/path/for/test" to true,
                "/some/other" to false,
                "/some/path/other" to false
            ).forEach { (path, toStartWithHolds) ->
                describe("compare against $path") {
                    if (toStartWithHolds) {
                        it("${toStartWithSpec.name} - does not throw") {
                            expectPath.toStartWithFun(Paths.get(path))
                        }
                        it("${notToStartWithSpec.name} - throws an AssertionError") {
                            expect {
                                expectPath.notToStartWithFun(Paths.get(path))
                            }.toThrow<AssertionError> {
                                messageToContain("${STARTS_NOT_WITH.getDefault()}:")
                            }
                        }
                    } else {
                        it("${toStartWithSpec.name} - throws an AssertionError") {
                            expect {
                                expectPath.toStartWithFun(Paths.get(path))
                            }.toThrow<AssertionError> {
                                messageToContain("${STARTS_WITH.getDefault()}:")
                            }
                        }
                        it("${notToStartWithSpec.name} - does not throw") {
                            expectPath.notToStartWithFun(Paths.get(path))
                        }
                    }
                }
            }
        }
    }

    @TestFactory
    fun toEndWith_notToEndWith() = testFactory(toEndWithSpec, notToEndWithSpec) { toEndWithFun, notToEndWithFun ->
        val path = "/some/path/for/test"
        val expectPath = expect(Paths.get(path))
        describe("path '$path'") {
            mapOf(
                "/some/path/for/test" to true,
                "some/path/for/test" to true,
                "me/path/for/test" to false,
                "/path/for/test" to false,
                "path/for/test" to true,
                "th/for/test" to false,
                "/for/test" to false,
                "for/test" to true,
                "or/test" to false,
                "/test" to false,
                "test" to true,
                "other/test" to false,
                "other/for/test" to false
            ).forEach { (path, toEndWithHolds) ->
                describe("compare against $path") {
                    if (toEndWithHolds) {
                        it("${toEndWithSpec.name} - does not throw") {
                            expectPath.toEndWithFun(Paths.get(path))
                        }
                        it("${notToEndWithSpec.name} - throws an AssertionError") {
                            expect {
                                expectPath.notToEndWithFun(Paths.get(path))
                            }.toThrow<AssertionError> {
                                messageToContain("${ENDS_NOT_WITH.getDefault()}:")
                            }
                        }
                    } else {
                        it("${toEndWithSpec.name} - throws an AssertionError") {
                            expect {
                                expectPath.toEndWithFun(Paths.get(path))
                            }.toThrow<AssertionError> {
                                messageToContain("${ENDS_WITH.getDefault()}:")
                            }
                        }
                        it("${notToEndWithSpec.name} - does not throw") {
                            expectPath.notToEndWithFun(Paths.get(path))
                        }
                    }
                }
            }
        }
    }

    @TestFactory
    fun toBeReadable() = testFactory(toBeReadableSpec) { toBeReadableFun ->
        val expectedMessage = "$toBeDescr: ${READABLE.getDefault()}"

        describe("not accessible") {
            itWithAndWithoutSymlink("throws an AssertionError for non-existent path") { maybeLink ->
                val file = maybeLink.create(tempFolder.tmpDir.resolve("nonExistent"))
                expect {
                    expect(file).toBeReadableFun()
                }.toThrow<AssertionError>().message {
                    toContain(expectedMessage, FAILURE_DUE_TO_NO_SUCH_FILE.getDefault())
                    containsExplanationFor(maybeLink)
                }
            }

            itPrintsFileAccessProblemDetails { testFile ->
                expect(testFile).toBeReadableFun()
            }
        }

        describe("readable") {
            itWithAndWithoutSymlink("does not throw for file") { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("readable"))
                expect(file).toBeReadableFun()
            }

            itWithAndWithoutSymlink("does not throw for directory") { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("readable"))
                expect(folder).toBeReadableFun()
            }
        }

        describe("POSIX: not readable") {
            val expectedPermissionHint = String.format(HINT_ACTUAL_POSIX_PERMISSIONS.getDefault(), "u=wx g=x o=")

            itWithAndWithoutSymlink(
                "throws an AssertionError for a file",
                skip = ifPosixNotSupported
            ) { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("not-readable"))
                file.whileWithPermissions(OWNER_WRITE, OWNER_EXECUTE, GROUP_EXECUTE) {
                    expect {
                        expect(file).toBeReadableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(
                            expectedMessage,
                            expectedPermissionTypeHintFor(A_FILE, being = READABLE),
                            expectedPermissionHint,
                            expectedPosixOwnerAndGroupHintFor(file)
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }

            itWithAndWithoutSymlink(
                "throws an AssertionError for a directory",
                skip = ifPosixNotSupported
            ) { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("not-readable"))
                folder.whileWithPermissions(OWNER_WRITE, OWNER_EXECUTE, GROUP_EXECUTE) {
                    expect {
                        expect(folder).toBeReadableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(
                            expectedMessage,
                            expectedPermissionTypeHintFor(A_DIRECTORY, being = READABLE),
                            expectedPermissionHint,
                            expectedPosixOwnerAndGroupHintFor(folder)
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }
        }

        describe("ACL: not readable") {
            itWithAndWithoutSymlink(
                "throws an AssertionError for a file",
                skip = ifAclNotSupported
            ) { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("not-readable"))
                file.whileWithAcl(TestAcls::ownerNoRead) {
                    expect {
                        expect(file).toBeReadableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(
                            expectedMessage,
                            expectedPermissionTypeHintFor(A_FILE, being = READABLE),
                            expectedAclOwnerHintFor(file),
                            HINT_ACTUAL_ACL_PERMISSIONS.getDefault()
                        )
                        toContainRegex(
                            file.expectedAclEntryPartFor("DENY", "READ_DATA"),
                            // we only check a few of the allowed options
                            file.expectedAclEntryPartFor("ALLOW", "WRITE_ACL"),
                            file.expectedAclEntryPartFor("ALLOW", "WRITE_DATA")
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }

            itWithAndWithoutSymlink(
                "throws an AssertionError for a directory",
                skip = ifAclNotSupported
            ) { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("not-readable"))
                folder.whileWithAcl(TestAcls::ownerNoRead) {
                    expect {
                        expect(folder).toBeReadableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(
                            expectedMessage,
                            expectedPermissionTypeHintFor(A_DIRECTORY, being = READABLE),
                            expectedAclOwnerHintFor(folder),
                            HINT_ACTUAL_ACL_PERMISSIONS.getDefault()
                        )
                        toContainRegex(
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

    @TestFactory
    fun notToBeReadable() = testFactory(notToBeReadableSpec) { notToBeReadableFun ->
        val expectedMessage = "$notToBeDescr: ${READABLE.getDefault()}"

        describe("not accessible") {
            itWithAndWithoutSymlink("throws an AssertionError for a non-existent path") { maybeLink ->
                val file = maybeLink.create(tempFolder.tmpDir.resolve("nonExistent"))
                expect {
                    expect(file).notToBeReadableFun()
                }.toThrow<AssertionError>().message {
                    toContain(
                        expectedMessage,
                        FAILURE_DUE_TO_NO_SUCH_FILE.getDefault()
                    )
                    containsExplanationFor(maybeLink)
                }
            }

            itPrintsFileAccessProblemDetails(
                // because if we cannot access parent then it is still not readable
                checkParentHints = false
            ) { testFile ->
                expect(testFile).notToBeReadableFun()
            }
        }

        describe("POSIX: readable") {
            itWithAndWithoutSymlink(
                "throws an AssertionError for a file",
                skip = ifPosixNotSupported
            ) { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("readable"))

                file.whileWithPermissions(OWNER_READ, OWNER_WRITE, OWNER_EXECUTE, OTHERS_EXECUTE) {
                    expect {
                        expect(file).notToBeReadableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(expectedMessage)
                    }
                }
            }

            itWithAndWithoutSymlink(
                "throws an AssertionError for a directory",
                skip = ifPosixNotSupported
            ) { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("readable"))

                folder.whileWithPermissions(OWNER_READ, OWNER_WRITE, OWNER_EXECUTE, OTHERS_EXECUTE) {
                    expect {
                        expect(folder).notToBeReadableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(expectedMessage)
                    }
                }
            }
        }

        describe("POSIX: not readable") {
            itWithAndWithoutSymlink(
                "does not throw for a file",
                skip = ifPosixNotSupported
            ) { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("not-readable"))
                file.whileWithPermissions(OWNER_WRITE, OWNER_EXECUTE, OTHERS_EXECUTE) {
                    expect(file).notToBeReadableFun()
                }
            }

            itWithAndWithoutSymlink(
                "does not throw for a directory",
                skip = ifPosixNotSupported
            ) { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("not-readable"))
                folder.whileWithPermissions(OWNER_WRITE, OWNER_EXECUTE, OTHERS_EXECUTE) {
                    expect(folder).notToBeReadableFun()
                }
            }
        }

        describe("ACL: not writable") {
            itWithAndWithoutSymlink(
                "does not throw for a file",
                skip = ifAclNotSupported
            ) { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("not-readable"))
                file.whileWithAcl(TestAcls::ownerNoRead) {
                    expect(file).notToBeReadableFun()
                }
            }

            itWithAndWithoutSymlink(
                "does not throw for a directory",
                skip = ifAclNotSupported
            ) { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("not-readable"))
                folder.whileWithAcl(TestAcls::ownerNoRead) {
                    expect(folder).notToBeReadableFun()
                }
            }
        }

        describe("ACL: writable") {
            itWithAndWithoutSymlink(
                "throws an AssertionError for a file",
                skip = ifAclNotSupported
            ) { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("readable"))
                file.whileWithAcl(TestAcls::all) {
                    expect {
                        expect(file).notToBeReadableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(expectedMessage)
                    }
                }
            }

            itWithAndWithoutSymlink(
                "throws an AssertionError for a directory",
                skip = ifAclNotSupported
            ) { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("readable"))
                folder.whileWithAcl(TestAcls::all) {
                    expect {
                        expect(folder).notToBeReadableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(expectedMessage)
                    }
                }
            }
        }
    }

    @TestFactory
    fun toBeWritable() = testFactory(toBeWritableSpec) { toBeWritableFun ->
        val expectedMessage = "$toBeDescr: ${WRITABLE.getDefault()}"

        describe("not accessible") {
            itWithAndWithoutSymlink("throws an AssertionError for a non-existent path") { maybeLink ->
                val file = maybeLink.create(tempFolder.tmpDir.resolve("nonExistent"))
                expect {
                    expect(file).toBeWritableFun()
                }.toThrow<AssertionError>().message {
                    toContain(
                        expectedMessage,
                        FAILURE_DUE_TO_NO_SUCH_FILE.getDefault()
                    )
                    containsExplanationFor(maybeLink)
                }
            }

            itPrintsFileAccessProblemDetails { testFile ->
                expect(testFile).toBeWritableFun()
            }
        }

        describe("writable") {
            itWithAndWithoutSymlink("does not throw for file") { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("writable"))
                expect(file).toBeWritableFun()
            }

            itWithAndWithoutSymlink("does not throw for directory") { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("writable"))
                expect(folder).toBeWritableFun()
            }
        }

        describe("POSIX: not writable") {
            val expectedPermissionHint = String.format(HINT_ACTUAL_POSIX_PERMISSIONS.getDefault(), "u=rx g= o=x")

            itWithAndWithoutSymlink(
                "throws an AssertionError for a file",
                skip = ifPosixNotSupported
            ) { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("not-writable"))
                file.whileWithPermissions(OWNER_READ, OWNER_EXECUTE, OTHERS_EXECUTE) {
                    expect {
                        expect(file).toBeWritableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(
                            expectedMessage,
                            expectedPermissionTypeHintFor(A_FILE, being = WRITABLE),
                            expectedPermissionHint,
                            expectedPosixOwnerAndGroupHintFor(file)
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }

            itWithAndWithoutSymlink(
                "throws an AssertionError for a directory",
                skip = ifPosixNotSupported
            ) { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("not-writable"))
                folder.whileWithPermissions(OWNER_READ, OWNER_EXECUTE, OTHERS_EXECUTE) {
                    expect {
                        expect(folder).toBeWritableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(
                            expectedMessage,
                            expectedPermissionTypeHintFor(A_DIRECTORY, being = WRITABLE),
                            expectedPermissionHint,
                            expectedPosixOwnerAndGroupHintFor(folder)
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }
        }

        describe("ACL: not writable") {
            itWithAndWithoutSymlink(
                "throws an AssertionError for a file",
                skip = ifAclNotSupported
            ) { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("not-writable"))
                file.whileWithAcl(TestAcls::ownerNoWrite) {
                    expect {
                        expect(file).toBeWritableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(
                            expectedMessage,
                            expectedPermissionTypeHintFor(A_FILE, being = WRITABLE),
                            expectedAclOwnerHintFor(file),
                            HINT_ACTUAL_ACL_PERMISSIONS.getDefault()
                        )
                        toContainRegex(
                            file.expectedAclEntryPartFor("ALLOW", "READ_DATA"),
                            file.expectedAclEntryPartFor("ALLOW", "EXECUTE")
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }

            itWithAndWithoutSymlink(
                "throws an AssertionError for a directory",
                skip = ifAclNotSupported
            ) { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("not-writable"))
                folder.whileWithAcl(TestAcls::ownerNoWrite) {
                    expect {
                        expect(folder).toBeWritableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(
                            expectedMessage,
                            expectedPermissionTypeHintFor(A_DIRECTORY, being = WRITABLE),
                            expectedAclOwnerHintFor(folder),
                            HINT_ACTUAL_ACL_PERMISSIONS.getDefault()
                        )
                        toContainRegex(
                            folder.expectedAclEntryPartFor("ALLOW", "READ_DATA"),
                            folder.expectedAclEntryPartFor("ALLOW", "EXECUTE")
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }
        }
    }

    @TestFactory
    fun notToBeWritable() = testFactory(notToBeWritableSpec) { notToBeWritableFun ->
        val expectedMessage = "$notToBeDescr: ${WRITABLE.getDefault()}"

        describe("not accessible") {
            itWithAndWithoutSymlink("throws an AssertionError for a non-existent path") { maybeLink ->
                val file = maybeLink.create(tempFolder.tmpDir.resolve("nonExistent"))
                expect {
                    expect(file).notToBeWritableFun()
                }.toThrow<AssertionError>().message {
                    toContain(
                        expectedMessage,
                        FAILURE_DUE_TO_NO_SUCH_FILE.getDefault()
                    )
                    containsExplanationFor(maybeLink)
                }
            }

            itPrintsFileAccessProblemDetails(
                // because if we cannot access parent then it is still not writeable
                checkParentHints = false
            ) { testFile ->
                expect(testFile).notToBeWritableFun()
            }
        }

        describe("POSIX: writable") {
            itWithAndWithoutSymlink(
                "throws an AssertionError for a file",
                skip = ifPosixNotSupported
            ) { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("writable"))

                file.whileWithPermissions(OWNER_READ, OWNER_WRITE, OWNER_EXECUTE, OTHERS_EXECUTE) {
                    expect {
                        expect(file).notToBeWritableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(expectedMessage)
                    }
                }
            }

            itWithAndWithoutSymlink(
                "throws an AssertionError for a directory",
                skip = ifPosixNotSupported
            ) { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("writable"))

                folder.whileWithPermissions(OWNER_READ, OWNER_WRITE, OWNER_EXECUTE, OTHERS_EXECUTE) {
                    expect {
                        expect(folder).notToBeWritableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(expectedMessage)
                    }
                }
            }
        }

        describe("POSIX: not writable") {
            itWithAndWithoutSymlink(
                "does not throw for a file",
                skip = ifPosixNotSupported
            ) { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("not-writable"))
                file.whileWithPermissions(OWNER_READ, OWNER_EXECUTE, OTHERS_EXECUTE) {
                    expect(file).notToBeWritableFun()
                }
            }

            itWithAndWithoutSymlink(
                "does not throw for a directory",
                skip = ifPosixNotSupported
            ) { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("not-writable"))
                folder.whileWithPermissions(OWNER_READ, OWNER_EXECUTE, OTHERS_EXECUTE) {
                    expect(folder).notToBeWritableFun()
                }
            }
        }

        describe("ACL: not writable") {
            itWithAndWithoutSymlink(
                "does not throw for a file",
                skip = ifAclNotSupported
            ) { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("not-writable"))
                file.whileWithAcl(TestAcls::ownerNoWrite) {
                    expect(file).notToBeWritableFun()
                }
            }

            itWithAndWithoutSymlink(
                "does not throw for a directory",
                skip = ifAclNotSupported
            ) { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("not-writable"))
                folder.whileWithAcl(TestAcls::ownerNoWrite) {
                    expect(folder).notToBeWritableFun()
                }
            }
        }

        describe("ACL: writable") {
            itWithAndWithoutSymlink(
                "throws an AssertionError for a file",
                skip = ifAclNotSupported
            ) { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("writable"))
                file.whileWithAcl(TestAcls::all) {
                    expect {
                        expect(file).notToBeWritableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(expectedMessage)
                    }
                }
            }

            itWithAndWithoutSymlink(
                "throws an AssertionError for a directory",
                skip = ifAclNotSupported
            ) { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("writable"))
                folder.whileWithAcl(TestAcls::all) {
                    expect {
                        expect(folder).notToBeWritableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(expectedMessage)
                    }
                }
            }
        }
    }

    @TestFactory
    fun toBeExecutable() = testFactory(toBeExecutableSpec) { toBeExecutableFun ->
        val expectedMessage = "$toBeDescr: ${EXECUTABLE.getDefault()}"

        describe("not accessible") {
            itWithAndWithoutSymlink("throws an AssertionError for non-existent path") { maybeLink ->
                val file = maybeLink.create(tempFolder.tmpDir.resolve("nonExistent"))
                expect {
                    expect(file).toBeExecutableFun()
                }.toThrow<AssertionError>().message {
                    toContain(expectedMessage, FAILURE_DUE_TO_NO_SUCH_FILE.getDefault())
                    containsExplanationFor(maybeLink)
                }
            }

            itPrintsFileAccessProblemDetails { testFile ->
                expect(testFile).toBeExecutableFun()
            }
        }

        describe("POSIX: executable") {
            itWithAndWithoutSymlink(
                "does not throw for file",
                skip = ifPosixNotSupported
            ) { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("executable"))
                file.whileWithPermissions(OWNER_READ, OWNER_WRITE, OWNER_EXECUTE) {
                    expect(file).toBeExecutableFun()
                }
            }

            itWithAndWithoutSymlink(
                "does not throw for directory",
                skip = ifPosixNotSupported
            ) { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("executable"))
                folder.whileWithPermissions(OWNER_READ, OWNER_WRITE, OWNER_EXECUTE) {
                    expect(folder).toBeExecutableFun()
                }
            }
        }

        describe("ACL: executable") {
            itWithAndWithoutSymlink(
                "does not throw for file",
                skip = ifAclNotSupported
            ) { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("executable"))
                file.whileWithAcl(TestAcls::all) {
                    expect(file).toBeExecutableFun()
                }
            }

            itWithAndWithoutSymlink(
                "does not throw for directory",
                skip = ifAclNotSupported
            ) { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("executable"))
                folder.whileWithAcl(TestAcls::all) {
                    expect(folder).toBeExecutableFun()
                }
            }
        }

        describe("POSIX: not executable") {
            val expectedPermissionHint = String.format(HINT_ACTUAL_POSIX_PERMISSIONS.getDefault(), "u=rw g=r o=")

            itWithAndWithoutSymlink(
                "throws an AssertionError for a file",
                skip = ifPosixNotSupported
            ) { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("not-executable"))
                file.whileWithPermissions(OWNER_WRITE, OWNER_READ, GROUP_READ) {
                    expect {
                        expect(file).toBeExecutableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(
                            expectedMessage,
                            expectedPermissionTypeHintFor(A_FILE, being = EXECUTABLE),
                            expectedPermissionHint,
                            expectedPosixOwnerAndGroupHintFor(file)
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }

            itWithAndWithoutSymlink(
                "throws an AssertionError for a directory",
                skip = ifPosixNotSupported
            ) { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("not-executable"))
                folder.whileWithPermissions(OWNER_WRITE, OWNER_READ, GROUP_READ) {
                    expect {
                        expect(folder).toBeExecutableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(
                            expectedMessage,
                            expectedPermissionTypeHintFor(A_DIRECTORY, being = EXECUTABLE),
                            expectedPermissionHint,
                            expectedPosixOwnerAndGroupHintFor(folder)
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }
        }

        describe("ACL: not executable") {
            itWithAndWithoutSymlink(
                "throws an AssertionError for a file",
                skip = ifAclNotSupported
            ) { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("not-executable"))
                file.whileWithAcl(TestAcls::ownerNoExecute) {
                    expect {
                        expect(file).toBeExecutableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(
                            expectedMessage,
                            expectedPermissionTypeHintFor(A_FILE, being = EXECUTABLE),
                            expectedAclOwnerHintFor(file),
                            HINT_ACTUAL_ACL_PERMISSIONS.getDefault()
                        )
                        toContainRegex(
                            file.expectedAclEntryPartFor("DENY", "EXECUTE"),
                            // we only check a few of the allowed options
                            file.expectedAclEntryPartFor("ALLOW", "WRITE_ACL"),
                            file.expectedAclEntryPartFor("ALLOW", "WRITE_DATA")
                        )
                        containsExplanationFor(maybeLink)
                    }
                }
            }

            itWithAndWithoutSymlink(
                "throws an AssertionError for a directory",
                skip = ifAclNotSupported
            ) { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("not-readable"))
                folder.whileWithAcl(TestAcls::ownerNoExecute) {
                    expect {
                        expect(folder).toBeExecutableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(
                            expectedMessage,
                            expectedPermissionTypeHintFor(A_DIRECTORY, being = EXECUTABLE),
                            expectedAclOwnerHintFor(folder),
                            HINT_ACTUAL_ACL_PERMISSIONS.getDefault()
                        )
                        toContainRegex(
                            folder.expectedAclEntryPartFor("DENY", "EXECUTE"),
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

    @TestFactory
    fun notToBeExecutable() = testFactory(notToBeExecutableSpec) { notToBeExecutableFun ->
        val expectedMessage = "$notToBeDescr: ${EXECUTABLE.getDefault()}"

        describe("not accessible") {
            itWithAndWithoutSymlink("throws an AssertionError for a non-existent path") { maybeLink ->
                val file = maybeLink.create(tempFolder.tmpDir.resolve("nonExistent"))
                expect {
                    expect(file).notToBeExecutableFun()
                }.toThrow<AssertionError>().message {
                    toContain(
                        expectedMessage,
                        FAILURE_DUE_TO_NO_SUCH_FILE.getDefault()
                    )
                    containsExplanationFor(maybeLink)
                }
            }

            itPrintsFileAccessProblemDetails(
                // because if we cannot access parent then it is still not executable
                checkParentHints = false
            ) { testFile ->
                expect(testFile).notToBeExecutableFun()
            }
        }

        describe("POSIX: executable") {
            itWithAndWithoutSymlink(
                "throws an AssertionError for a file",
                skip = ifPosixNotSupported
            ) { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("executable"))

                file.whileWithPermissions(OWNER_READ, OWNER_WRITE, OWNER_EXECUTE, OTHERS_EXECUTE) {
                    expect {
                        expect(file).notToBeExecutableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(expectedMessage)
                    }
                }
            }

            itWithAndWithoutSymlink(
                "throws an AssertionError for a directory",
                skip = ifPosixNotSupported
            ) { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("executable"))

                folder.whileWithPermissions(OWNER_READ, OWNER_WRITE, OWNER_EXECUTE, OTHERS_EXECUTE) {
                    expect {
                        expect(folder).notToBeExecutableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(expectedMessage)
                    }
                }
            }
        }

        describe("POSIX: not executable") {
            itWithAndWithoutSymlink(
                "does not throw for a file",
                skip = ifPosixNotSupported
            ) { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("not-executable"))
                file.whileWithPermissions(OWNER_READ, OTHERS_EXECUTE) {
                    expect(file).notToBeExecutableFun()
                }
            }

            itWithAndWithoutSymlink(
                "does not throw for a directory",
                skip = ifPosixNotSupported
            ) { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("not-executable"))
                folder.whileWithPermissions(OWNER_READ, OTHERS_EXECUTE) {
                    expect(folder).notToBeExecutableFun()
                }
            }
        }

        describe("ACL: not executable") {
            itWithAndWithoutSymlink(
                "does not throw for a file",
                skip = ifAclNotSupported
            ) { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("not-executable"))
                file.whileWithAcl(TestAcls::ownerNoExecute) {
                    expect(file).notToBeExecutableFun()
                }
            }

            itWithAndWithoutSymlink(
                "does not throw for a directory",
                skip = ifAclNotSupported
            ) { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("not-executable"))
                folder.whileWithAcl(TestAcls::ownerNoExecute) {
                    expect(folder).notToBeExecutableFun()
                }
            }
        }

        describe("ACL: executable") {
            itWithAndWithoutSymlink(
                "throws an AssertionError for a file",
                skip = ifAclNotSupported
            ) { maybeLink ->
                val file = maybeLink.create(tempFolder.newFile("executable"))
                file.whileWithAcl(TestAcls::all) {
                    expect {
                        expect(file).notToBeExecutableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(expectedMessage)
                    }
                }
            }

            itWithAndWithoutSymlink(
                "throws an AssertionError for a directory",
                skip = ifAclNotSupported
            ) { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("executable"))
                folder.whileWithAcl(TestAcls::all) {
                    expect {
                        expect(folder).notToBeExecutableFun()
                    }.toThrow<AssertionError>().message {
                        toContain(expectedMessage)
                    }
                }
            }
        }
    }

    @TestFactory
    fun toBeRegularFile() = testFactory(toBeRegularFileSpec) { toBeRegularFileFun ->
        val expectedMessage = "$toBeDescr: ${A_FILE.getDefault()}"

        describe("not accessible") {
            itWithAndWithoutSymlink("throws an AssertionError for a non-existent path") { maybeLink ->
                val file = maybeLink.create(tempFolder.tmpDir.resolve("nonExistent"))
                expect {
                    expect(file).toBeRegularFileFun()
                }.toThrow<AssertionError>().message {
                    toContain(
                        expectedMessage,
                        FAILURE_DUE_TO_NO_SUCH_FILE.getDefault()
                    )
                    containsExplanationFor(maybeLink)
                }
            }

            itPrintsFileAccessProblemDetails { testFile ->
                expect(testFile).toBeRegularFileFun()
            }
        }

        itWithAndWithoutSymlink("does not throw for a file") { maybeLink ->
            val file = maybeLink.create(tempFolder.newFile("test"))
            expect(file).toBeRegularFileFun()
        }

        itWithAndWithoutSymlink("throws an AssertionError for a directory") { maybeLink ->
            val folder = maybeLink.create(tempFolder.newDirectory("test"))
            expect {
                expect(folder).toBeRegularFileFun()
            }.toThrow<AssertionError>().message {
                toContain(
                    expectedMessage,
                    "${WAS.getDefault()}: ${A_DIRECTORY.getDefault()}"
                )
                containsExplanationFor(maybeLink)
            }
        }
    }

    @TestFactory
    fun toBeASymbolicLink() = testFactory(toBeASymbolicLinkSpec) { toBeASymbolicLinkFun ->
        val expectedMessage = "$toBeDescr: ${A_SYMBOLIC_LINK.getDefault()}"

        describe("not accessible") {
            it("throws an AssertionError for a non-existent path") {
                val path = tempFolder.resolve("nonExistent")
                expect {
                    expect(path).toBeASymbolicLinkFun()
                }.toThrow<AssertionError>().message {
                    toContain(
                        expectedMessage,
                        FAILURE_DUE_TO_NO_SUCH_FILE.getDefault()
                    )
                }
            }

            itPrintsFileAccessProblemDetails(forceNoLink = true) { testFile ->
                expect(testFile).toBeASymbolicLinkFun()
            }
        }

        it("throws an AssertionError for a file") {
            val file = tempFolder.newFile("test")
            expect {
                expect(file).toBeASymbolicLinkFun()
            }.toThrow<AssertionError>().message {
                toContain(
                    expectedMessage,
                    "${WAS.getDefault()}: ${A_FILE.getDefault()}"
                )
            }
        }

        it("throws an AssertionError for a directory") {
            val folder = tempFolder.newDirectory("test")
            expect {
                expect(folder).toBeASymbolicLinkFun()
            }.toThrow<AssertionError>().message {
                toContain(
                    expectedMessage,
                    "${WAS.getDefault()}: ${A_DIRECTORY.getDefault()}"
                )
            }
        }

        it("does not throw for a symbolic link") {
            if (supportsSymlinks) {
                val target = tempFolder.resolve("target")
                val link = Files.createSymbolicLink(tempFolder.resolve("link"), target)
                expect(link).toBeASymbolicLinkFun()
            }
        }
    }

    @TestFactory
    fun toBeADirectory() = testFactory(toBeADirectorySpec) { toBeADirectoryFun ->
        val expectedMessage = "$toBeDescr: ${A_DIRECTORY.getDefault()}"

        describe("not accessible") {
            itWithAndWithoutSymlink("throws an AssertionError for a non-existent path") { maybeLink ->
                val file = maybeLink.create(tempFolder.tmpDir.resolve("nonExistent"))
                expect {
                    expect(file).toBeADirectoryFun()
                }.toThrow<AssertionError>().message {
                    toContain(expectedMessage, FAILURE_DUE_TO_NO_SUCH_FILE.getDefault())
                    containsExplanationFor(maybeLink)
                }
            }

            itPrintsFileAccessProblemDetails { testFile ->
                expect(testFile).toBeADirectoryFun()
            }
        }

        itWithAndWithoutSymlink("throws an AssertionError for a file") { maybeLink ->
            val file = maybeLink.create(tempFolder.newFile("test"))
            expect {
                expect(file).toBeADirectoryFun()
            }.toThrow<AssertionError>().message {
                toContain(expectedMessage, "${WAS.getDefault()}: ${A_FILE.getDefault()}")
                containsExplanationFor(maybeLink)
            }
        }

        itWithAndWithoutSymlink("does not throw for a directory") { maybeLink ->
            val folder = maybeLink.create(tempFolder.newDirectory("test"))
            expect(folder).toBeADirectoryFun()
        }
    }

    @TestFactory
    fun toBeAbsolute() = testFactory(toBeAbsoluteSpec) { toBeAbsoluteFun ->
        it("throws an AssertionError for relative path") {
            val path = Paths.get("test/bla.txt")
            expect {
                expect(path).toBeAbsoluteFun()
            }.toThrow<AssertionError> {
                messageToContain("$toBeDescr: ${ABSOLUTE_PATH.getDefault()}")
            }
        }

        it("does not throw for absolute path") {
            val path = tempFolder.newFile("test")
            expect(path).toBeAbsoluteFun()
        }
    }

    @TestFactory
    fun toBeRelative() = testFactory(toBeRelativeSpec) { toBeRelativeFun ->
        it("throws an AssertionError for absolute path") {
            val path = tempFolder.newFile("test")
            expect {
                expect(path).toBeRelativeFun()
            }.toThrow<AssertionError> {
                messageToContain("$toBeDescr: ${RELATIVE_PATH.getDefault()}")
            }
        }

        it("does not throw for relative path") {
            val path = Paths.get("test/bla.txt")
            expect(path).toBeRelativeFun()
        }
    }

    @TestFactory
    fun toBeAnEmptyDirectory() = testFactory(toBeAnEmptyDirectorySpec) { toBeAnEmptyDirectoryFun ->
        val expectedMessage = "$toBeDescr: ${A_DIRECTORY.getDefault()}"
        val expectedEmptyMessage = "$toBeDescr: ${AN_EMPTY_DIRECTORY.getDefault()}"

        describe("not accessible") {
            itWithAndWithoutSymlink("throws an AssertionError for a non-existent path") { maybeLink ->
                val file = maybeLink.create(tempFolder.tmpDir.resolve("nonExistent"))
                expect {
                    expect(file).toBeAnEmptyDirectoryFun()
                }.toThrow<AssertionError>().message {
                    toContain(expectedMessage, FAILURE_DUE_TO_NO_SUCH_FILE.getDefault())
                    containsExplanationFor(maybeLink)
                }
            }

            itPrintsFileAccessProblemDetails { testFile ->
                expect(testFile).toBeAnEmptyDirectoryFun()
            }
        }

        itWithAndWithoutSymlink("throws an AssertionError for a file") { maybeLink ->
            val file = maybeLink.create(tempFolder.newFile("test"))
            expect {
                expect(file).toBeAnEmptyDirectoryFun()
            }.toThrow<AssertionError>().message {
                toContain(expectedMessage, "${WAS.getDefault()}: ${A_FILE.getDefault()}")
                containsExplanationFor(maybeLink)
            }
        }

        itWithAndWithoutSymlink("throws an AssertionError for a non-empty directory") { maybeLink ->
            val dir = tempFolder.newDirectory("notEmpty")
            val showMax = 10
            (0 until showMax + 1).forEach {
                dir.newFile("f$it")
            }
            val folder = maybeLink.create(dir)
            expect {
                expect(folder).toBeAnEmptyDirectoryFun()
            }.toThrow<AssertionError>().message {
                toContain(expectedEmptyMessage)
                containsExplanationFor(maybeLink)
                val sb = StringBuilder()
                // entries should be sorted but not naturally, i.e. f10 comes before f2
                val files = ((0..1) + (10..showMax) + (2 until 10)).take(showMax)
                files.forEach {
                    sb.append(".*${listBulletPoint}f$it.*$lineSeparator")
                }
                sb.append(".*${listBulletPoint}\\.\\.\\.")
                toContainRegex(sb.toString())
                notToContain("f${showMax + 1}")
            }
        }

        itWithAndWithoutSymlink("throws an AssertionError for a directory that contains an empty directory") { maybeLink ->
            val dir = tempFolder.newDirectory("notEmpty")
            dir.newDirectory("a")
            val folder = maybeLink.create(dir)
            expect {
                expect(folder).toBeAnEmptyDirectoryFun()
            }.toThrow<AssertionError>().message {
                toContain(expectedEmptyMessage)
                containsExplanationFor(maybeLink)
                toContain("a")
                notToContain("$listBulletPoint...")
            }
        }

        itWithAndWithoutSymlink("does not throw for an empty directory") { maybeLink ->
            val folder = maybeLink.create(tempFolder.newDirectory("test"))
            expect(folder).toBeAnEmptyDirectoryFun()
        }
    }

    private val hasDirectoryEntryVariations = listOf(
        DirectoryEntryVariation("directory", "directories", requiresSymlinks = false) { entry -> newDirectory(entry) },
        DirectoryEntryVariation("file", "files", requiresSymlinks = false) { entry -> newFile(entry) },
        DirectoryEntryVariation(
            "symlink with existing target",
            "symlinks with existing targets",
            requiresSymlinks = true
        ) { entry ->
            newFile("$entry-target").createSymbolicLink(resolve(entry))
        },
        DirectoryEntryVariation(
            "symlink with non-existing target",
            "symlinks with non-existing targets",
            requiresSymlinks = true
        ) { entry ->
            resolve("$entry-not-existing-target").createSymbolicLink(resolve(entry))
        }
    )

    @TestFactory
    fun toHaveTheDirectoryEntry() = testFactory(toHaveTheDirectoryEntrySpec) { hasDirectoryEntryFun ->
        hasDirectoryEntryVariations.forEach { (singleName, _, requiresSymlinks, createEntry) ->
            itWithAndWithoutSymlink(
                "does not throw if the parameter is a child $singleName",
                skip = if (requiresSymlinks) ifSymlinksNotSupported else No
            ) { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("startDir"))
                folder.createEntry("a")
                expect(folder).hasDirectoryEntryFun("a")
            }
        }

        itWithAndWithoutSymlink("throws if the parameter does not exist") { maybeLink ->
            val folder = maybeLink.create(tempFolder.newDirectory("startDir"))
            expect {
                expect(folder).hasDirectoryEntryFun("fileA")
            }.toThrow<AssertionError>().message {
                toContain("${TO.getDefault()}: ${EXIST.getDefault()}")
                toContain("fileA")
            }
        }
    }

    @TestFactory
    fun toHaveTheDirectoryEntries() = testFactory(toHaveTheDirectoryEntriesSpec) { hasDirectoryEntryFun ->
        hasDirectoryEntryVariations.forEach { (singleName, multiName, requiresSymlinks, createEntry) ->
            val skipDueToSymlinks = if (requiresSymlinks) ifSymlinksNotSupported else No

            itWithAndWithoutSymlink(
                "does not throw if the single parameter is a child $singleName",
                skip = skipDueToSymlinks
            ) { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("startDir"))
                folder.createEntry("a")
                expect(folder).hasDirectoryEntryFun("a", emptyArray())
            }

            itWithAndWithoutSymlink(
                "does not throw if two parameters are child $multiName",
                skip = skipDueToSymlinks
            ) { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("startDir"))
                folder.createEntry("a")
                folder.createEntry("b")
                expect(folder).hasDirectoryEntryFun("a", arrayOf("b"))
            }

            itWithAndWithoutSymlink(
                "does not throw if three parameters are child $multiName",
                skip = skipDueToSymlinks
            ) { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("startDir"))
                folder.createEntry("a")
                folder.createEntry("b")
                folder.createEntry("c")
                expect(folder).hasDirectoryEntryFun("a", arrayOf("b", "c"))
            }

            itWithAndWithoutSymlink(
                "it throws if the first $singleName does not exist",
                skip = skipDueToSymlinks
            ) { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("startDir"))
                folder.createEntry("file2")
                folder.createEntry("file3")
                expect {
                    expect(folder).hasDirectoryEntryFun("file1", arrayOf("file2", "file3"))
                }.toThrow<AssertionError>().message {
                    toContain("${TO.getDefault()}: ${EXIST.getDefault()}")
                    toContain("file1")
                    notToContain("file2")
                    notToContain("file3")
                }
            }

            itWithAndWithoutSymlink(
                "it throws if the second $singleName does not exist",
                skip = skipDueToSymlinks
            ) { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("startDir"))
                folder.createEntry("file1")
                folder.createEntry("file3")
                expect {
                    expect(folder).hasDirectoryEntryFun("file1", arrayOf("file2", "file3"))
                }.toThrow<AssertionError>().message {
                    toContain("${TO.getDefault()}: ${EXIST.getDefault()}")
                    toContain("file2")
                    notToContain("file1")
                    notToContain("file3")
                }
            }

            itWithAndWithoutSymlink(
                "it throws if the third $singleName does not exist",
                skip = skipDueToSymlinks
            ) { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("startDir"))
                folder.createEntry("file1")
                folder.createEntry("file2")
                expect {
                    expect(folder).hasDirectoryEntryFun("file1", arrayOf("file2", "file3"))
                }.toThrow<AssertionError>().message {
                    toContain("${TO.getDefault()}: ${EXIST.getDefault()}")
                    toContain("file3")
                    notToContain("file2")
                    notToContain("file1")
                }
            }

            itWithAndWithoutSymlink(
                "it throws if the first and third $multiName do not exist",
                skip = skipDueToSymlinks
            ) { maybeLink ->
                val folder = maybeLink.create(tempFolder.newDirectory("startDir"))
                folder.createEntry("file2")
                expect {
                    expect(folder).hasDirectoryEntryFun("file1", arrayOf("file2", "file3"))
                }.toThrow<AssertionError>().message {
                    toContain("${TO.getDefault()}: ${EXIST.getDefault()}")
                    toContain("file1")
                    toContain("file3")
                    notToContain("file2")
                }
            }
        }

        itWithAndWithoutSymlink(
            "does not throw when using all entry types",
            skip = ifSymlinksNotSupported
        ) { maybeLink ->
            val folder = maybeLink.create(tempFolder.newDirectory("startDir"))
            hasDirectoryEntryVariations.first().createEntry(folder, "file1")
            hasDirectoryEntryVariations.drop(1).forEachIndexed { index, variation ->
                variation.createEntry(folder, "file${index + 2}")
            }
            val otherEntries = (2..(hasDirectoryEntryVariations.size)).map { "file$it" }.toTypedArray()
            expect(folder).hasDirectoryEntryFun("file1", otherEntries)
        }

        itWithAndWithoutSymlink("it throws if the directory does not exist") { maybeLink ->
            val folder = maybeLink.create(tempFolder.tmpDir.resolve("nonExistent"))
            val expectedMessage = "$toBeDescr: ${A_DIRECTORY.getDefault()}"

            expect {
                expect(folder).hasDirectoryEntryFun("file1", arrayOf("file2", "file3"))
            }.toThrow<AssertionError>().message {
                toContain(expectedMessage, FAILURE_DUE_TO_NO_SUCH_FILE.getDefault())
                containsExplanationFor(maybeLink)
            }
        }
    }

    @TestFactory
    fun toHaveTheSameBinaryContentAs_toHaveTheSameTextualContentAs() = testFactory(
        toHaveTheSameBinaryContentAsSpec,
        toHaveTheSameTextualContentAsSpec,
        toHaveTheSameTextualContentAsDefaultArgsSpec
    ) {
        val toHaveTheSameBinaryContentAsFun = toHaveTheSameBinaryContentAsSpec.lambda
        val toHaveTheSameTextualContentAsFun = toHaveTheSameTextualContentAsSpec.lambda
        val toHaveTheSameTextualContentAsDefaultArgsAsFun = toHaveTheSameTextualContentAsDefaultArgsSpec.lambda

        fun errortoHaveTheSameTextualContentAs(sourceEncoding: Charset, targetEncoding: Charset) =
            TranslatableWithArgs(HAS_SAME_TEXTUAL_CONTENT, sourceEncoding, targetEncoding).getDefault()

        describe("empty content") {
            fun createFiles(maybeLink: MaybeLink): Pair<Path, Path> {
                val sourcePath = maybeLink.create(tempFolder.newFile("text1"))
                val targetPath = maybeLink.create(tempFolder.newFile("text2"))
                return sourcePath to targetPath
            }

            itWithAndWithoutSymlink("${toHaveTheSameBinaryContentAsSpec.name} - does not throw") { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect(sourcePath).toHaveTheSameBinaryContentAsFun(targetPath)
            }

            itWithAndWithoutSymlink("${toHaveTheSameTextualContentAsSpec.name} - does not throw if ISO_8859_1, ISO_8859_1 is used") { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect(sourcePath).toHaveTheSameTextualContentAsFun(
                    targetPath,
                    Charsets.ISO_8859_1,
                    Charsets.ISO_8859_1
                )
            }

            itWithAndWithoutSymlink("${toHaveTheSameTextualContentAsSpec.name} - does not throw if UTF-16, UTF-16 is used") { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect(sourcePath).toHaveTheSameTextualContentAsFun(targetPath, Charsets.UTF_16, Charsets.UTF_16)
            }

            itWithAndWithoutSymlink("${toHaveTheSameTextualContentAsSpec.name} - does not throw if UTF-8, UTF-16 is used") { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect(sourcePath).toHaveTheSameTextualContentAsFun(targetPath, Charsets.UTF_8, Charsets.UTF_16)
            }

            itWithAndWithoutSymlink("${toHaveTheSameTextualContentAsSpec.name} - does not throw if UTF-16, ISO_8859_1 is used") { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect(sourcePath).toHaveTheSameTextualContentAsFun(targetPath, Charsets.UTF_16, Charsets.ISO_8859_1)
            }

            itWithAndWithoutSymlink("${toHaveTheSameTextualContentAsDefaultArgsSpec.name} - does not throw") { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect(sourcePath).toHaveTheSameTextualContentAsDefaultArgsAsFun(targetPath)
            }
        }

        describe("has same binary content") {
            fun createFiles(maybeLink: MaybeLink): Pair<Path, Path> {
                val sourcePath = maybeLink.create(tempFolder.newFile("text3"))
                val targetPath = maybeLink.create(tempFolder.newFile("text4"))
                sourcePath.toFile().writeText("same")
                targetPath.toFile().writeText("same")
                return sourcePath to targetPath
            }

            itWithAndWithoutSymlink("${toHaveTheSameBinaryContentAsSpec.name} - does not throw") { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect(sourcePath).toHaveTheSameBinaryContentAsFun(targetPath)
            }

            itWithAndWithoutSymlink("${toHaveTheSameTextualContentAsSpec.name} - does not throw if UTF-8, UTF-8 is used") { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect(sourcePath).toHaveTheSameTextualContentAsFun(targetPath, Charsets.UTF_8, Charsets.UTF_8)
            }

            itWithAndWithoutSymlink("${toHaveTheSameTextualContentAsSpec.name} - does not throw if UTF-16, UTF-16 is used") { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect(sourcePath).toHaveTheSameTextualContentAsFun(targetPath, Charsets.UTF_16, Charsets.UTF_16)
            }

            itWithAndWithoutSymlink("${toHaveTheSameTextualContentAsSpec.name} - throws AssertionError if UTF-8, UTF-16 is used") { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect {
                    expect(sourcePath).toHaveTheSameTextualContentAsFun(targetPath, Charsets.UTF_8, Charsets.UTF_16)
                }.toThrow<AssertionError>().message {
                    toContain(errortoHaveTheSameTextualContentAs(Charsets.UTF_8, Charsets.UTF_16))
                }
            }

            itWithAndWithoutSymlink("${toHaveTheSameTextualContentAsSpec.name} - throws AssertionError if UTF-16, ISO_8859_1 is used") { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect {
                    expect(sourcePath).toHaveTheSameTextualContentAsFun(
                        targetPath,
                        Charsets.UTF_16,
                        Charsets.ISO_8859_1
                    )
                }.toThrow<AssertionError>().message {
                    toContain(errortoHaveTheSameTextualContentAs(Charsets.UTF_16, Charsets.ISO_8859_1))
                }
            }

            itWithAndWithoutSymlink("${toHaveTheSameTextualContentAsDefaultArgsSpec.name} - does not throw if UTF-8, UTF-8 is used") { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect(sourcePath).toHaveTheSameTextualContentAsDefaultArgsAsFun(targetPath)
            }
        }

        describe("has same textual content in UTF-8 and UTF-16") {
            fun createFiles(maybeLink: MaybeLink): Pair<Path, Path> {
                val sourcePath = maybeLink.create(tempFolder.newFile("text5"))
                val targetPath = maybeLink.create(tempFolder.newFile("text6"))
                sourcePath.toFile().writeText("same")
                targetPath.toFile().writeText("same", Charsets.UTF_16)
                return sourcePath to targetPath
            }

            itWithAndWithoutSymlink("${toHaveTheSameBinaryContentAsSpec.name} - throws AssertionError") { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect {
                    expect(sourcePath).toHaveTheSameBinaryContentAsFun(targetPath)
                }.toThrow<AssertionError>().message {
                    toContain(HAS_SAME_BINARY_CONTENT.getDefault())
                }
            }

            itWithAndWithoutSymlink("${toHaveTheSameTextualContentAsSpec.name} - does not throw if UTF-8, UTF-16 is used") { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect(sourcePath).toHaveTheSameTextualContentAsFun(targetPath, Charsets.UTF_8, Charsets.UTF_16)
            }

            itWithAndWithoutSymlink("${toHaveTheSameTextualContentAsSpec.name} - throws AssertionError if UTF-8, UTF-8 is used") { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect {
                    expect(sourcePath).toHaveTheSameTextualContentAsFun(targetPath, Charsets.UTF_8, Charsets.UTF_8)
                }.toThrow<AssertionError>().message {
                    toContain(errortoHaveTheSameTextualContentAs(Charsets.UTF_8, Charsets.UTF_8))
                }
            }

            itWithAndWithoutSymlink("${toHaveTheSameTextualContentAsSpec.name} - throws AssertionError if UTF-16, UTF-8 is used") { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect {
                    expect(sourcePath).toHaveTheSameTextualContentAsFun(targetPath, Charsets.UTF_16, Charsets.UTF_8)
                }.toThrow<AssertionError>().message {
                    toContain(errortoHaveTheSameTextualContentAs(Charsets.UTF_16, Charsets.UTF_8))
                }
            }

            itWithAndWithoutSymlink("${toHaveTheSameTextualContentAsDefaultArgsSpec.name} - throws AssertionError if UTF-8, UTF-8 is used") { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect {
                    expect(sourcePath).toHaveTheSameTextualContentAsDefaultArgsAsFun(targetPath)
                }.toThrow<AssertionError>().message {
                    toContain(errortoHaveTheSameTextualContentAs(Charsets.UTF_8, Charsets.UTF_8))
                }
            }
        }

        describe("has different textual content") {
            fun createFiles(maybeLink: MaybeLink): Pair<Path, Path> {
                val sourcePath = maybeLink.create(tempFolder.newFile("text5"))
                val targetPath = maybeLink.create(tempFolder.newFile("text6"))
                sourcePath.toFile().writeText("same")
                targetPath.toFile().writeText("targetPath")
                return sourcePath to targetPath
            }

            itWithAndWithoutSymlink("${toHaveTheSameBinaryContentAsSpec.name} - throws AssertionError") { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect {
                    expect(sourcePath).toHaveTheSameBinaryContentAsFun(targetPath)
                }.toThrow<AssertionError>().message {
                    toContain(HAS_SAME_BINARY_CONTENT.getDefault())
                }
            }

            itWithAndWithoutSymlink("${toHaveTheSameTextualContentAsSpec.name} - throws AssertionError if UTF-8, UTF-8 is used") { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect {
                    expect(sourcePath).toHaveTheSameTextualContentAsFun(targetPath, Charsets.UTF_8, Charsets.UTF_8)
                }.toThrow<AssertionError>().message {
                    toContain(errortoHaveTheSameTextualContentAs(Charsets.UTF_8, Charsets.UTF_8))
                }
            }

            itWithAndWithoutSymlink("${toHaveTheSameTextualContentAsSpec.name} - throws AssertionError if UTF-16, UTF-16 is used") { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect {
                    expect(sourcePath).toHaveTheSameTextualContentAsFun(targetPath, Charsets.UTF_16, Charsets.UTF_16)
                }.toThrow<AssertionError>().message {
                    toContain(errortoHaveTheSameTextualContentAs(Charsets.UTF_16, Charsets.UTF_16))
                }
            }

            itWithAndWithoutSymlink("${toHaveTheSameTextualContentAsDefaultArgsSpec.name} - throws AssertionError if UTF-8, UTF-8 is used") { maybeLink ->
                val (sourcePath, targetPath) = createFiles(maybeLink)
                expect {
                    expect(sourcePath).toHaveTheSameTextualContentAsDefaultArgsAsFun(targetPath)
                }.toThrow<AssertionError>().message {
                    toContain(errortoHaveTheSameTextualContentAs(Charsets.UTF_8, Charsets.UTF_8))
                }
            }
        }
    }

    @TestFactory
    fun parent() = testFactory(parentFeatureSpec, parentSpec) {
        val parentFunctions = unifySignatures(parentFeatureSpec, parentSpec)

        describe("folder with parent") {
            parentFunctions.forEach { (name, parentFun, _) ->
                it("$name - toBe(folder.parent) holds") {
                    val childFolder = tempFolder.newDirectory("child")
                    val parentFolder = childFolder.parent
                    expect(childFolder).parentFun { toEqual(parentFolder) }
                }
                it("$name - toBe(folder) fails") {
                    expect {
                        val childFolder = tempFolder.newDirectory("child")
                        expect(childFolder).parentFun { toEqual(childFolder) }
                    }.toThrow<AssertionError> {
                        message { toContainRegex("$toEqualDescr: .*(/|\\\\)child") }
                    }
                }
            }
        }

        describe("folder without parent") {
            parentFunctions.forEach { (name, parentFun, hasExtraHint) ->
                it("$name - toBe(folder.parent) fails" + showsSubExpectationIf(hasExtraHint)) {
                    expect {
                        val rootFolder = tempFolder.tmpDir.root
                        expect(rootFolder).parentFun { toEqual(Paths.get("non-existing")) }
                    }.toThrow<AssertionError> {
                        messageToContain(DOES_NOT_HAVE_PARENT.getDefault())
                        if (hasExtraHint) messageToContain("non-existing")
                    }
                }
            }
        }
    }

    @TestFactory
    fun resolve() = testFactory(resolveFeatureSpec, resolveSpec) {
        val resolveFunctions = unifySignatures(resolveFeatureSpec, resolveSpec)

        describe("resolve child") {
            resolveFunctions.forEach { (name, resolveFun, _) ->
                it("$name - toBe(child) holds") {
                    val resolvedFolder = tempFolder.newDirectory("child")
                    val rootFolder = resolvedFolder.parent
                    expect(rootFolder).resolveFun(resolvedFolder.fileName.toString()) { toEqual(resolvedFolder) }
                }
            }
        }

        describe("resolve non-existing") {
            resolveFunctions.forEach { (name, resolveFun, hasExtraHint) ->
                it("$name - toBe(folder) fails" + showsSubExpectationIf(hasExtraHint)) {
                    expect {
                        val resolvedFolder = tempFolder.newDirectory("child")
                        val rootFolder = resolvedFolder.parent
                        expect(rootFolder).resolveFun("non-existing") { toEqual(resolvedFolder) }
                    }.toThrow<AssertionError> {
                        messageToContain("non-existing")
                        if (hasExtraHint) messageToContain("child")
                    }
                }
            }
        }
    }

    @TestFactory
    fun fileName() = testFactoryForFeatureNonFeature(fileNameFeatureSpec, fileNameSpec) { name, fileNameFun, _ ->
        describe("path a/my.txt") {
            it("$name - toBe(my.txt) holds") {
                expect(Paths.get("a/my.txt")).fileNameFun { toEqual("my.txt") }
            }
            it("$name - toBe(my.txt) fails") {
                expect {
                    expect(Paths.get("a/my")).fileNameFun { toEqual("my.txt") }
                }.toThrow<AssertionError> {
                    messageToContain("$fileNameDescr: \"my\"")
                }
            }
        }
    }

    @TestFactory
    fun fileNameWithoutExtension() = testFactoryForFeatureNonFeature(
        fileNameWithoutExtensionFeatureSpec,
        fileNameWithoutExtensionSpec
    ) { name, fileNameWithoutExtensionFun, _ ->
        describe("File with extension") {
            it("$name - toBe(my) holds") {
                expect(Paths.get("a/my.txt")).fileNameWithoutExtensionFun { toEqual("my") }
            }
            it("$name - toBe(my.txt) fails") {
                expect {
                    expect(Paths.get("a/my.txt")).fileNameWithoutExtensionFun { toEqual("my.txt") }
                }.toThrow<AssertionError> {
                    messageToContain("$fileNameWithoutExtensionDescr: \"my\"")
                }
            }
        }

        val directory = "a/my/"
        describe("directory $directory") {
            it("$name - toBe(my) holds") {
                expect(Paths.get(directory)).fileNameWithoutExtensionFun { toEqual("my") }
            }
            it("$name - toBe(my.txt) fails") {
                expect {
                    expect(Paths.get("a/my/")).fileNameWithoutExtensionFun { toEqual("my.txt") }
                }.toThrow<AssertionError> {
                    messageToContain("$fileNameWithoutExtensionDescr: \"my\"")
                }
            }
        }

        describe("path with double extension") {
            it("$name - toBe(my.tar) holds") {
                expect(Paths.get("a/my.tar.gz")).fileNameWithoutExtensionFun { toEqual("my.tar") }
            }
            it("$name - toBe(my) fails") {
                expect {
                    expect(Paths.get("a/my.tar.gz")).fileNameWithoutExtensionFun { toEqual("my") }
                }.toThrow<AssertionError> {
                    messageToContain("$fileNameWithoutExtensionDescr: \"my.tar\"")
                }
            }
        }
    }

    @TestFactory
    fun extension() = testFactoryForFeatureNonFeature(extensionFeatureSpec, extensionSpec) { name, extensionFun, _ ->
        describe("Path without extension") {
            it("$name - returns empty expectation") {
                expect(Paths.get("/foo/no-extension-here")).extensionFun { toEqual("") }
            }
        }

        describe("Path with extension") {
            it("$name - returns the extension") {
                expect(Paths.get("/foo/something.txt")).extensionFun { toEqual("txt") }
            }
        }
    }
}

private const val TEST_IO_EXCEPTION_MESSAGE = "unknown test error"

private val symlinkCounter = java.util.concurrent.atomic.AtomicInteger()

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

    fun all(owner: UserPrincipal) = listOf(
        aclEntry(ALLOW, owner, *AclEntryPermission.values())
    )

    fun ownerNoExecute(owner: UserPrincipal) = listOf(
        aclEntry(DENY, owner, EXECUTE),
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
 * @param aclToUse A function that gets passed the [UserPrincipal] for this file's owner and returns an access control list
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
    fun confirmCreateWasCalled()
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
    final override fun confirmCreateWasCalled() {
        check(createWasCalled) { "Expected the symbolic link creator function to have been called!" }
    }
}

internal class NoLink : InternalMaybeLink("") {
    override fun callCheckedCreate(path: Path) = path
    override fun <T : CharSequence> callCheckedCheckAssertionErrorMessage(expect: Expect<T>) {}
}

internal class SimpleLink(private val tempFolderProvider: () -> SpecTempFolder) :
    InternalMaybeLink("via symbolic link") {
    private var link: Path? = null
    private var path: Path? = null
    override fun callCheckedCreate(path: Path): Path {
        this.path = path
        val tempFolder = tempFolderProvider()
        // we assume in the tests that the temporary directory does not contain symlinks, thus we resolve them here.
        val link = Files.createSymbolicLink(
            tempFolder.tmpDir.toRealPath().resolve("__linkTo_" + symlinkCounter.incrementAndGet() + "_" + path.fileName),
            path
        )
        this.link = link
        return link
    }

    override fun <T : CharSequence> callCheckedCheckAssertionErrorMessage(expect: Expect<T>) {
        expect.toContain(TranslatableWithArgs(HINT_FOLLOWED_SYMBOLIC_LINK, link!!, path!!).getDefault())
    }
}

internal fun <T : CharSequence> Expect<T>.containsExplanationFor(maybeLink: MaybeLink) =
    maybeLink.checkAssertionErrorMessage(this)

private fun expectedPermissionTypeHintFor(type: Translatable, being: Translatable) = String.format(
    FAILURE_DUE_TO_PERMISSION_FILE_TYPE_HINT.getDefault(),
    type.getDefault(),
    being.getDefault()
)

internal data class DirectoryEntryVariation(
    val singleName: String,
    val multipleName: String,
    val requiresSymlinks: Boolean,
    val createEntry: Path.(entry: String) -> Path
)

/**
 * Minimal replacement for the former MemoizedTempFolder spek extension: creates a unique temporary directory
 * which is deleted on JVM shutdown and creates uniquely named files/directories within it.
 */
internal class SpecTempFolder {
    val tmpDir: Path = Files.createTempDirectory("atrium-path-expectations-test")

    init {
        Runtime.getRuntime().addShutdownHook(Thread { tmpDir.toFile().deleteRecursively() })
    }

    fun newFile(name: String): Path = Files.createTempFile(tmpDir, name, ".tmp")

    fun newDirectory(name: String): Path = Files.createTempDirectory(tmpDir, name)

    fun resolve(name: String): Path = tmpDir.resolve(name)
}
