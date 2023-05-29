description = "Runs examples, includes the code and the output in README.md"

val junitPlatformVersion: String by rootProject.extra
val spekVersion: String by rootProject.extra
val niokVersion: String by rootProject.extra

kotlin {
    sourceSets {
        val main by getting {
            dependencies {
                implementation("org.junit.platform:junit-platform-console-standalone:$junitPlatformVersion")
                implementationWithExclude("org.spekframework.spek2:spek-dsl-jvm:$spekVersion")
                implementationWithExclude("org.spekframework.spek2:spek-runner-junit5:$spekVersion")
                implementationWithExclude("org.spekframework.spek2:spek-runtime-jvm:$spekVersion")
                runtimeOnly("org.jetbrains.kotlin:kotlin-reflect")

                implementation(prefixedProject("fluent"))
                implementationWithExclude("ch.tutteli.niok:niok:$niokVersion")
            }
        }
        configureEach {
            languageSettings.apply {
                languageVersion = "1.3"
                apiVersion = "1.3"
            }
        }
    }
    tasks {
        register<JavaExec>("readme") {
            group = "documentation"
            description = "Runs examples, includes the code and the output in README.md"

            classpath = project.sourceSets.main.get().runtimeClasspath
            val version = rootProject.version.toString()
            environment("README_SOURCETREE", if (version.endsWith("-SNAPSHOT")) "tree/main" else "tree/v$version")

            this.main = "org.junit.platform.console.ConsoleLauncher"
            args = listOf(
                "--scan-class-path", project.sourceSets.main.get().output.classesDirs.asPath,
                "--disable-banner",
                "--fail-if-no-tests",
                "--include-engine", "spek2-readme",
                "--details", "summary"
            )
        }
        check {
            dependsOn("readme")
        }
    }
}
