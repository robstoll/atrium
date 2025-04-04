plugins {
    id("build-logic.published-java-library")
}

description = "A fluent expectation function API for java with a focus on code completion"

sourceSets {
    main {
        java {
            srcDir(project.layout.projectDirectory.dir("src/main/generated/java").asFile)
        }
    }
}

dependencies {
    api(prefixedProject("logic"))

    testImplementation(prefixedProject("specs"))
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)
}


junitjacoco {
    additionalProjectSources.addAll(
        prefixedProject("translations-en_GB"),
        prefixedProject("logic"),
        prefixedProject("core"),
    )
}

project.afterEvaluate {
    tasks.withType<Test>().configureEach {
        useJUnitPlatform {
            includeEngines = setOf("junit-jupiter")
        }
    }
}
