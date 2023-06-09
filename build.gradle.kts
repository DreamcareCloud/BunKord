plugins {
    kotlin("multiplatform") version "1.8.21"
    id("com.palantir.git-version") version "3.0.0"
    application
}

application {
    mainClass.set("cloud.dreamcare.bunkord.BunKordKt")
}

val gitVersion: groovy.lang.Closure<String> by extra
version = gitVersion()

repositories {
    mavenCentral()
}

kotlin {
    explicitApi()
    jvm {
        jvmToolchain(17)
        withJava()
    }
    sourceSets {
        getByName("jvmMain") {
            dependencies {
                implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")
                implementation("dev.kord:kord-core:0.9.0")
                implementation("ch.qos.logback:logback-classic:1.4.8")
                implementation("org.reflections:reflections:0.10.2")
                implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.2")
                implementation("io.github.seisuke:kemoji-jvm:0.2.0")

                implementation("com.aallam.openai:openai-client:3.3.1")
            }
        }
    }
}

tasks.named<Jar>("jvmJar") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    archiveClassifier.set("standalone")

    manifest { attributes(mapOf("Main-Class" to application.mainClass)) }
    from(sourceSets.main.get().output)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}
