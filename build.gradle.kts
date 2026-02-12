plugins {
    kotlin("jvm") version "2.1.21"
    kotlin("plugin.serialization") version "2.1.21"
    id("maven-publish")
    application
    alias(libs.plugins.shadow)
    alias(libs.plugins.git)
}

group = "com.quantum"
version = "1.0.0-alpha"
description = "Next-generation server software for Minecraft: Bedrock Edition"

java.sourceCompatibility = JavaVersion.VERSION_21
java.targetCompatibility = JavaVersion.VERSION_21


dependencies {
	api(libs.serialization)
	api(libs.network)
	api(libs.bundles.log4j)
	api(libs.bundles.protocol)
	implementation(libs.bundles.okaeri)
}

application {
    mainClass.set("com.quantum.Quantum")
}

tasks {
    shadowJar {
        archiveBaseName.set("Quantum")
        archiveClassifier.set("")
        archiveVersion.set(project.version.toString())
    }
    compileJava {
        options.encoding = "UTF-8"
    }
    test {
        useJUnitPlatform()
    }
    javadoc {
        options.encoding = "UTF-8"
    }
}

java {
    withSourcesJar()
    withJavadocJar()
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

sourceSets {
    main {
        resources {
            srcDirs("src/main/resources")
        }
    }
}

gitProperties {
    dateFormat = "dd.MM.yyyy '@' HH:mm:ss z"
    failOnNoGitDirectory = false
    customProperty("github.repo", "PyleMC/Quantum")
}

configurations.all {
    resolutionStrategy {
        cacheDynamicVersionsFor(10, TimeUnit.MINUTES)
        cacheChangingModulesFor(10, TimeUnit.MINUTES)
        preferProjectModules()
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "quantum"
            version = project.version.toString()

            from(components["java"])
        }
    }
}