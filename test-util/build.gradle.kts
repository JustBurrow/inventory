import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    api(project(":inventory-design"))
    api(project(":inventory-data"))
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    api("org.springframework.security:spring-security-core")
    api("org.apache.commons:commons-lang3")
}

tasks.withType<Jar> {
    enabled = true
}

tasks.withType<BootJar> {
    enabled = false
}
