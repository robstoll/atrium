import com.github.vlsi.gradle.dsl.configureEach

plugins {
    id("java")
    id("ch.tutteli.gradle.plugins.junitjacoco")
    id("com.github.vlsi.crlf")
    id("com.github.vlsi.gradle-extensions")
    id("build-logic.build-params")
}

java {
    toolchain {
        // reading JAVA_VERSION from env to enable jdk17 build in CI
        val jdkVersion = System.getenv("JAVA_VERSION")?.toIntOrNull() ?: 11
        languageVersion.set(JavaLanguageVersion.of(jdkVersion))
    }
    consistentResolution {
        useCompileClasspathVersions()
    }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
}

tasks.register<DependencyInsightReportTask>("allDependencyInsight") {
    group = HelpTasksPlugin.HELP_GROUP
    description =
        "Shows insights where the dependency is used. For instance: allDependencyInsight --configuration compile --dependency org.jsoup:jsoup"
}

tasks.configureEach<JavaCompile> {
    inputs.property("java.version", System.getProperty("java.version"))
    inputs.property("java.vm.version", System.getProperty("java.vm.version"))
    sourceCompatibility = buildParameters.defaultJdkVersion.toString()
    targetCompatibility = buildParameters.defaultJdkVersion.toString()
    options.apply {
        encoding = "UTF-8"
        compilerArgs.add("-Xlint:deprecation")
        if (buildParameters.werror) {
            compilerArgs.add("-Werror")
        }
    }
}
