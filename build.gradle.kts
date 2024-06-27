plugins {
    id("java")
}

group = "com.mobigen"
version = "1.0-SNAPSHOT"

allprojects {
    group = "${group}.monitoring"
    version = "1.0-SNAPSHOT"
}

repositories {
    mavenCentral()
}

object Dependencies {
    object Versions {
        const val SPRING_BOOT_VER = "3.3.0"
        const val JUNIT = "5.9.3"
        const val LOMBOK_VER = "1.18.30"
        const val OKHTTP = "4.12.0"
        const val JSON = "1.1.1"

        const val ORACLE = "23.4.0.24.05"
        const val POSTGRESQL = "42.7.3"
        const val MARIA = "3.4.0"
        const val MINIO = "8.5.11"
        const val MYSQL = "8.0.28"
        const val H2BASE = "2.2.224"

        const val JWT = "0.12.6"
    }

    object Spring {
        const val BOOT = "org.springframework.boot:spring-boot-starter:${Versions.SPRING_BOOT_VER}"
        const val BOOT_STARTER = "org.springframework.boot:spring-boot-starter:${Versions.SPRING_BOOT_VER}"
        const val STARTER_WEB = "org.springframework.boot:spring-boot-starter-web:${Versions.SPRING_BOOT_VER}"
        const val JPA = "org.springframework.boot:spring-boot-starter-data-jpa:${Versions.SPRING_BOOT_VER}"

        const val TEST = "org.springframework.boot:spring-boot-starter-test:${Versions.SPRING_BOOT_VER}"
    }

    object JWP {
        const val JWT_API = "io.jsonwebtoken:jjwt:${Versions.JWT}"
        const val JWT_IMPL = "io.jsonwebtoken:jjwt-impl:${Versions.JWT}"
        const val JWT_JACKSON = "io.jsonwebtoken:jjwt-jackson:${Versions.JWT}"
    }

    object OkHttp {
        const val OkHttp = "com.squareup.okhttp3:mockwebserver:${Versions.OKHTTP}"
    }

    object Junit {
        const val BOM = "org.junit:junit-bom:${Versions.JUNIT}"
        const val JUPITER = "org.junit.jupiter:junit-jupiter:${Versions.JUNIT}"
    }

    object Lombok {
        const val LOMBOK = "org.projectlombok:lombok:${Versions.LOMBOK_VER}"
    }

    object Json {
        const val JSON = "com.googlecode.json-simple:json-simple:${Versions.JSON}"
    }

    object DB {
        const val ORACLE = "com.oracle.database.jdbc:ojdbc11:${Versions.ORACLE}"
        const val POSTGRESQL = "org.postgresql:postgresql:${Versions.POSTGRESQL}"
        const val MARIA = "org.mariadb.jdbc:mariadb-java-client:${Versions.MARIA}"
        const val MINIO = "io.minio:minio:${Versions.MINIO}"
        const val MYSQL = "mysql:mysql-connector-java:${Versions.MYSQL}"
        const val H2BASE = "com.h2database:h2:${Versions.H2BASE}"
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring
    implementation(Dependencies.Spring.BOOT)
    implementation(Dependencies.Spring.BOOT_STARTER)
    implementation(Dependencies.Spring.STARTER_WEB)

    // JPA
    implementation(Dependencies.Spring.JPA)

    // Lombok
    annotationProcessor(Dependencies.Lombok.LOMBOK)
    implementation(Dependencies.Lombok.LOMBOK)

    // OKHttp
    implementation(Dependencies.OkHttp.OkHttp)

    // JWT
    implementation(Dependencies.JWP.JWT_API)
    implementation(Dependencies.JWP.JWT_IMPL)
    implementation(Dependencies.JWP.JWT_JACKSON)

    // Json
    implementation(Dependencies.Json.JSON)

    // DB
    implementation(Dependencies.DB.ORACLE)
    implementation(Dependencies.DB.POSTGRESQL)
    implementation(Dependencies.DB.MARIA)
    implementation(Dependencies.DB.MINIO)
    implementation(Dependencies.DB.MYSQL)
    implementation(Dependencies.DB.H2BASE)

    // Test
    testImplementation(platform(Dependencies.Junit.BOM))
    testImplementation(Dependencies.Junit.JUPITER)
}

tasks.named<Test>("test") {
    useJUnitPlatform()

    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.named<Jar>("jar") {
    manifest {
        attributes["Main-Class"] = "com.mobigen.monitoring.MonitoringApplication"
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(configurations.compileClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}
