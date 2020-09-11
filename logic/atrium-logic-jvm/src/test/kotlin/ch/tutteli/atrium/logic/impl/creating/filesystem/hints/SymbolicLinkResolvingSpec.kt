package ch.tutteli.atrium.logic.impl.creating.filesystem.hints

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.ExplanatoryAssertion
import ch.tutteli.atrium.assertions.WarningAssertionGroupType
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.filesystem.hints.explainForResolvedLink
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.specs.fileSystemSupportsCreatingSymlinks
import ch.tutteli.atrium.translations.DescriptionPathAssertion.FAILURE_DUE_TO_LINK_LOOP
import ch.tutteli.atrium.translations.DescriptionPathAssertion.HINT_FOLLOWED_SYMBOLIC_LINK
import ch.tutteli.niok.createDirectory
import ch.tutteli.niok.createFile
import ch.tutteli.niok.createSymbolicLink
import ch.tutteli.spek.extensions.memoizedTempFolder
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.spekframework.spek2.Spek
import org.spekframework.spek2.dsl.Skip
import org.spekframework.spek2.lifecycle.CachingMode.TEST
import org.spekframework.spek2.style.specification.describe
import java.nio.file.Path
import java.nio.file.Paths

object SymbolicLinkResolvingSpec : Spek({
    val tempFolder by memoizedTempFolder()

    // Windows with neither symlink nor admin privilege
    val ifSymlinksNotSupported =
        if (fileSystemSupportsCreatingSymlinks()) Skip.No else Skip.Yes("creating symbolic links is not supported on this file system")

    val testAssertion = assertionBuilder.createDescriptive(Untranslatable("testAssertion"), null) { true }
    val resolvedPathConsumer by memoized(TEST) {
        mockk<(Path) -> Assertion> {
            every { this@mockk.invoke(any()) } returns testAssertion
        }
    }

    /**
     * Throughout this suite, we have to make sure that all paths we use are already completely resolved. Otherwise, we
     * might get additional, unexpected messages because the path to the temporary folder contains a symlink.
     */
    describe("explainForResolvedLink", skip = ifSymlinksNotSupported) {
        describe("resolves correctly") {
            afterEachTest {
                confirmVerified(resolvedPathConsumer)
            }

            it("resolves an existing file to itself") {
                val file = tempFolder.newFile("testFile").toRealPath()

                explainForResolvedLink(file, resolvedPathConsumer)
                verify { resolvedPathConsumer(file) }
            }

            it("resolves an existing directory to itself") {
                val folder = tempFolder.newFile("testDir").toRealPath()

                explainForResolvedLink(folder, resolvedPathConsumer)
                verify { resolvedPathConsumer(folder) }
            }

            it("resolves a non-existent path to itself") {
                val notExisting = tempFolder.tmpDir.toRealPath().resolve("notExisting")

                explainForResolvedLink(notExisting, resolvedPathConsumer)
                verify { resolvedPathConsumer(notExisting) }
            }

            it("resolves a relative path to its absolute target") {
                val relativePath = Paths.get(".")

                explainForResolvedLink(relativePath, resolvedPathConsumer)
                verify { resolvedPathConsumer(relativePath.toRealPath()) }
            }

            it("resolves a symbolic link to its target") {
                val testDir = tempFolder.newDirectory("symbolic-to-target").toRealPath()
                val target = testDir.resolve("notExisting")
                val link = target.createSymbolicLink(testDir.resolve("link"))

                explainForResolvedLink(link, resolvedPathConsumer)
                verify { resolvedPathConsumer(target) }
            }

            it("a relative symbolic link to its absolute target") {
                val testDir = tempFolder.newDirectory("relative-symbolic-to-target").toRealPath()
                val target = testDir.resolve("testFile").createFile()
                val folder = testDir.resolve("testFolder").createDirectory()
                val relativeLink =
                    Paths.get("..").resolve(target.fileName).createSymbolicLink(folder.resolve("testLink"))

                explainForResolvedLink(relativeLink, resolvedPathConsumer)
                verify { resolvedPathConsumer(target) }
            }

            it("resolves a symbolic link chain as far as possible") {
                val testDir = tempFolder.newDirectory("chain").toRealPath()
                val nowhere = testDir.resolve("dont-exist")
                val toNowhere = nowhere.createSymbolicLink(testDir.resolve("link-to-nowhere"))
                val start = tempFolder.newSymbolicLink("start", toNowhere)

                explainForResolvedLink(start, resolvedPathConsumer)
                verify { resolvedPathConsumer(nowhere) }
            }

            it("resolves multiple symbolic links to their target") {
                val testDir = tempFolder.newDirectory("multi-links").toRealPath()
                val target = testDir.resolve("notExisting")
                val grandparent = testDir.resolve("__linksgrandparent").createDirectory()
                val parent = grandparent.resolve("step").createDirectory()
                val grandparentLink = grandparent.createSymbolicLink(testDir.resolve("__linkTo_grandparent"))
                val innerLink = target.createSymbolicLink(parent.resolve("__linkTo_${target.fileName}"))
                val innerLinkInGrandparentLink = grandparentLink.resolve(parent.fileName).resolve(innerLink.fileName)
                val linkToInnerLink = tempFolder.newSymbolicLink(
                    "__transitive_linkTo_${target.fileName}", innerLinkInGrandparentLink
                )

                explainForResolvedLink(linkToInnerLink, resolvedPathConsumer)
                verify { resolvedPathConsumer(target) }
            }
        }

        describe("explains correctly") {
            it("returns the original assertion if no link is involved") {
                val file = tempFolder.newFile("testFile").toRealPath()

                val resultAssertion = explainForResolvedLink(file, resolvedPathConsumer)
                expect(resultAssertion).isSameAs(testAssertion)
            }

            it("adds an explanation for one symbolic link") {
                val testDir = tempFolder.newDirectory("link").toRealPath()
                val target = testDir.resolve("notExisting")
                val link = target.createSymbolicLink(testDir.resolve("link"))

                val resultAssertion = explainForResolvedLink(link, resolvedPathConsumer)
                expect(resultAssertion).isA<AssertionGroup>()
                    .feature { p(it::assertions) }.containsExactly(
                        { describesLink(link, target) },
                        { isSameAs(testAssertion) }
                    )
            }

            it("adds explanations for a symbolic link chain as far as possible") {
                val testDir = tempFolder.newDirectory("chain").toRealPath()
                val nowhere = testDir.resolve("dont-exist")
                val toNowhere = nowhere.createSymbolicLink(testDir.resolve("link-to-nowhere"))
                val start = toNowhere.createSymbolicLink(testDir.resolve("start"))

                val resultAssertion = explainForResolvedLink(start, resolvedPathConsumer)
                expect(resultAssertion).isA<AssertionGroup>()
                    .feature { p(it::assertions) }.containsExactly(
                        { describesLink(start, toNowhere) },
                        { describesLink(toNowhere, nowhere) },
                        { isSameAs(testAssertion) }
                    )
            }

            it("adds explanations for multiple symbolic links") {
                val testDir = tempFolder.newDirectory("multi-links").toRealPath()
                val target = testDir.resolve("notExisting")
                val grandparent = testDir.resolve("__linksgrandparent").createDirectory()
                val parent = grandparent.resolve("step").createDirectory()
                val grandparentLink = grandparent.createSymbolicLink(testDir.resolve("__linkTo_grandparent"))
                val innerLink = target.createSymbolicLink(parent.resolve("__linkTo_${target.fileName}"))
                val innerLinkInGrandparentLink =
                    grandparentLink.resolve(parent.fileName).resolve(innerLink.fileName)
                val linkToInnerLink = innerLinkInGrandparentLink.createSymbolicLink(
                    testDir.resolve("__transitive_linkTo_${target.fileName}")
                )

                val resultAssertion = explainForResolvedLink(linkToInnerLink, resolvedPathConsumer)
                expect(resultAssertion).isA<AssertionGroup>()
                    .feature { p(it::assertions) }.containsExactly(
                        { describesLink(linkToInnerLink, innerLinkInGrandparentLink) },
                        { describesLink(grandparentLink, grandparent) },
                        { describesLink(innerLink, target) },
                        { isSameAs(testAssertion) }
                    )
            }

            it("does not assume a link loop even if the same link appears multiple times") {
                val testDir = tempFolder.newDirectory("multi-non-loop").toRealPath()
                val barLink = testDir.createSymbolicLink(testDir.resolve("bar"))
                val target = testDir.resolve("target").createFile()
                val testLink = barLink.resolve(barLink.fileName).resolve(barLink.fileName).resolve(target.fileName)

                val resultAssertion = explainForResolvedLink(testLink, resolvedPathConsumer)
                expect(resultAssertion).isA<AssertionGroup>()
                    .feature { p(it::assertions) }.containsExactly(
                        { describesLink(barLink, testDir) },
                        { describesLink(barLink, testDir) },
                        { describesLink(barLink, testDir) },
                        { isSameAs(testAssertion) }
                    )
            }
        }

        it("adds an explanation for link loops") {
            val testDir = tempFolder.newDirectory("link-loop").toRealPath()
            val a = testDir.resolve("linkA")
            val b = a.createSymbolicLink(testDir.resolve("linkB"))
            b.createSymbolicLink(a)

            val resultAssertion = explainForResolvedLink(a, resolvedPathConsumer)
            expect(resultAssertion).isA<AssertionGroup>()
                .feature { p(it::assertions) }.containsExactly(
                    { describesLink(a, b) },
                    { describesLink(b, a) },
                    { describesLinkLoop(a, b, a) },
                    { isSameAs(testAssertion) }
                )
        }

        it("adds an explanation for more subtle link loops") {
            val testDir = tempFolder.newDirectory("sneaky-loop").toRealPath()
            val foo = testDir.resolve("foo").createDirectory()
            val fooLink = foo.createSymbolicLink(testDir.resolve("bar"))
            val link = fooLink.resolve("link").createSymbolicLink(foo.resolve("link"))

            val resultAssertion = explainForResolvedLink(link, resolvedPathConsumer)
            expect(resultAssertion).isA<AssertionGroup>()
                .feature { p(it::assertions) }.containsExactly(
                    { describesLink(link, fooLink.resolve("link")) },
                    { describesLink(fooLink, foo) },
                    { describesLinkLoop(link, link) },
                    { isSameAs(testAssertion) }
                )
        }

        it("keeps explanations for links that are not part of the loop") {
            val testDir = tempFolder.newDirectory("link-loop").toRealPath()
            val a = testDir.resolve("linkA")
            val c = a.createSymbolicLink(testDir.resolve("linkC"))
            val b = c.createSymbolicLink(testDir.resolve("linkB"))
            b.createSymbolicLink(a)
            val grandparent = testDir.resolve("__linksgrandparent").createDirectory()
            val parent = grandparent.resolve("step").createDirectory()
            val grandparentLink = grandparent.createSymbolicLink(testDir.resolve("__linkTo_grandparent"))
            val innerLink = a.createSymbolicLink(parent.resolve("__linkTo_${a.fileName}"))
            val innerLinkInGrandparentLink = grandparentLink.resolve(parent.fileName).resolve(innerLink.fileName)
            val linkToInnerLink =
                innerLinkInGrandparentLink.createSymbolicLink(testDir.resolve("__transitive_linkTo_${a.fileName}"))

            val resultAssertion = explainForResolvedLink(linkToInnerLink, resolvedPathConsumer)
            expect(resultAssertion).isA<AssertionGroup>()
                .feature { p(it::assertions) }.containsExactly(
                    { describesLink(linkToInnerLink, innerLinkInGrandparentLink) },
                    { describesLink(grandparentLink, grandparent) },
                    { describesLink(innerLink, a) },
                    { describesLink(a, b) },
                    { describesLink(b, c) },
                    { describesLink(c, a) },
                    { describesLinkLoop(a, b, c, a) },
                    { isSameAs(testAssertion) }
                )
        }
    }
})

fun <T : Assertion> Expect<T>.describesLink(link: Path, target: Path) {
    isA<ExplanatoryAssertion> {
        feature { p(it::explanation) }.toBe(
            TranslatableWithArgs(HINT_FOLLOWED_SYMBOLIC_LINK, link, target)
        )
    }
}

fun <T : Assertion> Expect<T>.describesLinkLoop(vararg loop: Path) {
    isA<AssertionGroup> {
        feature { p(it::assertions) }.containsExactly {
            isA<ExplanatoryAssertion> {
                val expectedExplanation = TranslatableWithArgs(FAILURE_DUE_TO_LINK_LOOP, loop.joinToString(" -> "))
                feature { p(it::explanation) }.toBe(expectedExplanation)
            }
        }
        feature { p(it::type) }.toBe(WarningAssertionGroupType)
    }
}
