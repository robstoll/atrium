package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.style.specification.Suite

abstract class MapContainsInAnyOrderOnlyKeyValuePairsExpectationsSpec(
    keyValuePairs: MFun2<String, Int, Int>,
    keyValuePairsNullable: MFun2<String?, Int?, Int?>,
    rootBulletPoint: String,
    successfulBulletPoint: String,
    failingBulletPoint: String,
    warningBulletPoint: String,
    listBulletPoint: String,
    explanatoryBulletPoint: String,
    featureArrow: String,
    featureBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : MapLikeContainsFormatSpecBase(
    rootBulletPoint,
    successfulBulletPoint,
    failingBulletPoint,
    warningBulletPoint,
    listBulletPoint,
    explanatoryBulletPoint,
    featureArrow,
    featureBulletPoint,
    {

        include(object : SubjectLessSpec<Map<out String, Int>>(
            describePrefix,
            keyValuePairs.forSubjectLess("key" to 1, arrayOf())
        ) {})

        include(object : SubjectLessSpec<Map<out String?, Int?>>(
            "$describePrefix[nullable Key] ",
            keyValuePairsNullable.forSubjectLess(null to 1, arrayOf("a" to null))
        ) {})

        fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
            describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

        describeFun(keyValuePairs, keyValuePairsNullable) {
            val containsKeyValuePairsFunctions = uncheckedToNonNullable(keyValuePairs, keyValuePairsNullable)

            context("empty map") {
                containsKeyValuePairsFunctions.forEach { (name, containsFun) ->
                    it("$name - a to 1 throws AssertionError, reports a") {
                        expect {
                            expect(emptyMap).containsFun("a" to 1, arrayOf())
                        }.toThrow<AssertionError> {
                            message {
                                containsInAnyOrderOnlyDescr()
                                containsSize(0, 1)
                                entryNonExisting("a", "$toBeDescr: 1")
                            }
                        }
                    }

                    it("$name - a to 1, b to 3, c to 4 throws AssertionError, reports a, b and c") {
                        expect {
                            expect(emptyMap).containsFun("a" to 1, arrayOf("b" to 3, "c" to 4))
                        }.toThrow<AssertionError> {
                            message {
                                containsInAnyOrderOnlyDescr()
                                containsSize(0, 3)
                                entryNonExisting("a", "$toBeDescr: 1")
                                entryNonExisting("b", "$toBeDescr: 3")
                                entryNonExisting("c", "$toBeDescr: 4")
                            }
                        }
                    }
                }
            }

            context("map $map") {
                containsKeyValuePairsFunctions.forEach { (name, containsFun) ->
                    listOf(
                        listOf("a" to 1, "b" to 2),
                        listOf("b" to 2, "a" to 1)
                    ).forEach {
                        it("$name - ${it.joinToString()} does not throw") {
                            expect(map).containsFun(it.first(), it.drop(1).toTypedArray())
                        }
                    }

                    it("$name - a to 1 throws AssertionError, reports second a and missing b") {
                        expect {
                            expect(map).containsFun("a" to 1, arrayOf())
                        }.toThrow<AssertionError> {
                            message {
                                containsSize(2, 1)
                                containsInAnyOrderOnlyDescr()
                                entrySuccess("a", 1, "$toBeDescr: 1")
                                additionalEntries("b" to 2)
                            }
                        }
                    }

                    it("$name - a to 1, a to 1 throws AssertionError, reports second a and missing b") {
                        expect {
                            expect(map).containsFun("a" to 1, arrayOf("a" to 1))
                        }.toThrow<AssertionError> {
                            message {
                                containsInAnyOrderOnlyDescr()
                                entrySuccess("a", 1, "$toBeDescr: 1")
                                entryNonExisting("a", "$toBeDescr: 1")
                                additionalEntries("b" to 2)
                                containsNot(sizeDescr)
                            }
                        }
                    }

                    it("$name - a to 1, b to 3, c to 4 throws AssertionError, reports b and c") {
                        expect {
                            expect(map).containsFun("a" to 1, arrayOf("b" to 3, "c" to 4))
                        }.toThrow<AssertionError> {
                            message {
                                containsInAnyOrderOnlyDescr()
                                containsSize(2, 3)
                                entrySuccess("a", 1, "$toBeDescr: 1")
                                entryFailing("b", 2, "$toBeDescr: 3")
                                entryNonExisting("c", "$toBeDescr: 4")
                            }
                        }
                    }
                }

            }
        }

        describeFun(keyValuePairsNullable) {
            val containsFun = keyValuePairsNullable.lambda
            context("map: $nullableMap") {
                listOf(
                    listOf("a" to null, null to 1, "b" to 2),
                    listOf("a" to null, "b" to 2, null to 1),
                    listOf("b" to 2, null to 1, "a" to null),
                    listOf(null to 1, "a" to null, "b" to 2)
                ).forEach {
                    it("$it does not throw") {
                        expect(nullableMap).containsFun(it.first(), it.drop(1).toTypedArray())
                    }
                }
                it("a to 1, c to 3, null to null, b to 2 throws AssertionError, reports all but b") {
                    expect {
                        expect(nullableMap).containsFun("a" to 1, arrayOf("c" to 3, null to null, "b" to 2))
                    }.toThrow<AssertionError> {
                        message {
                            containsInAnyOrderOnlyDescr()
                            containsSize(3, 4)
                            entryFailing("a", null, "$toBeDescr: 1")
                            entryNonExisting("c", "$toBeDescr: 3")
                            entryFailing(null, "1", "$toBeDescr: null")
                            entrySuccess("b", "2", "$toBeDescr: 2")
                        }
                    }
                }
            }
        }
    })
