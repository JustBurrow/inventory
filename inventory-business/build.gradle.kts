dependencies {
    implementation(project(":inventory-design"))
    implementation(project(":inventory-data"))
    implementation(project(":inventory-dto"))

    api("org.springframework.boot:spring-boot-starter")
    api("org.springframework.security:spring-security-core")
    api("org.springframework:spring-tx")

    testImplementation(project(":inventory-test-business"))
}
