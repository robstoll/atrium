plugins {
    id("build-logic.kotlin-jvm")
}

description = "Runs examples, includes the code and the output in README.md"

kotlin {
    sourceSets {
        main {
            dependencies {
                implementation(libs.junit.platform.console)
                runtimeOnly(kotlin("reflect"))

                implementation(prefixedProject("fluent"))
                implementation(libs.niok)
                implementation(libs.assertJ)
            }
        }
        configureEach {
            languageSettings.apply {
                languageVersion = "1.9"
                apiVersion = "1.9"
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

            this.mainClass.set("org.junit.platform.console.ConsoleLauncher")
            args = listOf(
                "--scan-class-path", project.sourceSets.main.get().output.classesDirs.asPath,
                "--disable-banner",
                "--fail-if-no-tests",
                "--include-engine", "junit5-readme",
                "--details", "summary"
            )
        }
        check {
            dependsOn("readme")
        }
    }
}
