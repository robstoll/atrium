import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

plugins {
    kotlin("multiplatform")
    id("build-logic.kotlin-conventions")
    id("ch.tutteli.gradle.plugins.spek")
}

if (name != "atrium-specs") {
    apply(plugin = "ch.tutteli.gradle.plugins.kotlin.module.info")
}


val spekVersion: String by rootProject.extra

spek {
    if (rootProject.name != "gradle-kotlin-dsl-accessors") {
        version = spekVersion
    }
}

//TODO 1.2.0 remove once we moved away from spec to kotlin-test
if (name == "atrium-logic" || name == "atrium-verbs" || name == "atrium-verbs-internal") {
    the<ch.tutteli.gradle.plugins.junitjacoco.JunitJacocoPluginExtension>()
        .allowedTestTasksWithoutTests.set(listOf("jsNodeTest"))
}

val jupiterVersion: String by rootProject.extra

val NamedDomainObjectContainer<KotlinSourceSet>.jvmMain: NamedDomainObjectProvider<KotlinSourceSet>
    get() = named("jvmMain")
val NamedDomainObjectContainer<KotlinSourceSet>.jvmTest: NamedDomainObjectProvider<KotlinSourceSet>
    get() = named("jvmTest")

val NamedDomainObjectContainer<KotlinSourceSet>.jsMain: NamedDomainObjectProvider<KotlinSourceSet>
    get() = named("jsMain")
val NamedDomainObjectContainer<KotlinSourceSet>.jsTest: NamedDomainObjectProvider<KotlinSourceSet>
    get() = named("jsTest")

kotlin {
    jvm {
        // for module-info.java and Java sources in test
        withJava()
        configureKotlinJvm()
    }

    //TODO 1.2.0 switch from LEGACY to IR
    js(LEGACY) {
        nodejs {
            testTask {
                useMocha {
                    // timeout in milliseconds,
                    // Windows regularly has a timeout with the default which
                    // at the time of writting was 2000
                    timeout = "10000"
                }
            }
        }
    }

    sourceSets {
        configureLanguageSettings()

        commonMain {
            dependencies {
                api(kotlin("reflect"))

                // TODO 1.1.0 shouldn't be necessary to add stdlib dependency to kotlin with kotlin 1.5.x (is automatically added)
                api(kotlin("stdlib-common"))
            }
        }

        commonTest {
            dependencies {
                // TODO 1.1.0 switch to kotlin(test) with update to kotlin > 1.4, dependency to test-annotations-common should then no longer be necessary
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        jvmMain {
            dependencies {
                // TODO 1.1.0 shouldn't be necessary to add the dependency to kotlin with kotlin 1.5.x (is automatically added, but check, maybe stdlib is added automatically but not stdlib-jdk8)
                api(kotlin("stdlib-jdk8"))
            }
        }
        jvmTest {
            dependencies {
                if (rootProject.name != "gradle-kotlin-dsl-accessors") {
                    runtimeOnly("org.junit.jupiter:junit-jupiter-engine:$jupiterVersion")
                }

                // TODO  1.1.0 should no longer be necessary with kotlin 1.5, adding kotlin("test") to common should be enough
                implementation(kotlin("test-junit5"))
            }
        }
        jsMain {
            dependencies {
                // TODO 1.1.0 shouldn't be necessary to add the dependency to kotlin with kotlin 1.5.x
                api(kotlin("stdlib-js"))
            }
        }
        jsTest {
            dependencies {
                // TODO  1.1.0 should no longer be necessary with kotlin 1.5, adding kotlin("test") to common should be enough
                implementation(kotlin("test-js"))
            }
        }
    }
}

//TODO 1.1.0 the below was actually a bug in gradle and should have been fixed since 6.9.4
// check if it works now so that we don't have to define it in an afterEvaluate

// needs to be in afterEvaluate for now because the tutteli-spek-plugin overwrites it by using useJunitPlatform which
// apparently reconfigures the TestFramework (even if already useJunitPlatform was used, so it's more a setJUnitPlatformAsTestFramework)
afterEvaluate {
    kotlin {
        jvm {
            testRuns["test"].executionTask.configure {
                useJUnitPlatform {
                    // TODO 1.1.0 decide if we really want to migrate to kotest, without support for hierarchical tests
                    // there is barely any value for Atrium to switch to kotest and we might be better of with kotlin-test (especially as there are JS issues)
                    includeEngines("spek2", "junit-jupiter")//, "kotest")
                }
            }
        }
    }
}
