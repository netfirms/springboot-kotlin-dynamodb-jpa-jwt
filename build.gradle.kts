import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.2.1.RELEASE"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	kotlin("jvm") version "1.3.61"
	kotlin("plugin.spring") version "1.3.61"
}

group = "com.exp"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlin:kotlin-noarg")
	implementation("com.amazonaws:aws-java-sdk-dynamodb:1.11.573")
	implementation("com.amazonaws:aws-java-sdk-core:1.11.573")
	implementation("org.springframework.boot:spring-boot-autoconfigure")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-devtools")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.data:spring-data-commons:2.2.1.RELEASE")
	implementation("org.springframework.security:spring-security-test")
	implementation("io.springfox:springfox-swagger2:2.7.0")
	implementation("io.springfox:springfox-swagger-ui:2.7.0")
	implementation("org.modelmapper:modelmapper:2.3.5")
	implementation("io.jsonwebtoken:jjwt-jackson:0.10.7")
	implementation("io.jsonwebtoken:jjwt-api:0.10.7")
	implementation("io.jsonwebtoken:jjwt-impl:0.10.7")
	implementation("com.codahale:passpol:0.6.2")
	implementation("com.googlecode.libphonenumber:libphonenumber:8.10.8")
	implementation("org.apache.commons:commons-lang3")
	implementation("com.sun.xml.bind:jaxb-core:2.3.0")
	implementation("javax.xml.bind:jaxb-api:2.3.0")
	implementation("com.sun.xml.bind:jaxb-impl:2.3.0")
	implementation("io.vavr:vavr:0.9.3")
	implementation("com.github.derjust:spring-data-dynamodb:5.1.0")
	implementation("org.projectlombok:lombok:1.18.4")

	implementation("org.apache.logging.log4j:log4j:2.11.2")
	implementation("com.google.code.gson:gson:2.8.5")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("com.fasterxml.jackson.module:jackson-module-parameter-names")

	implementation("com.h2database:h2:1.4.199")
	implementation("org.junit.jupiter:junit-jupiter-api:5.5.2")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

