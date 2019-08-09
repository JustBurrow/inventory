import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    compileOnly(project(":inventory-design"))
    compileOnly(project(":inventory-dto"))
}

tasks.withType<Jar> {
    enabled = true
}

tasks.withType<BootJar> {
    enabled = true
    launchScript()
}
