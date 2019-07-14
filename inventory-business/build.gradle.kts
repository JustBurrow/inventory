dependencies {
    implementation(project(":inventory-design"))
    implementation(project(":inventory-data"))
    implementation(project(":inventory-dto"))

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework:spring-tx")

    testImplementation(project(":inventory-test-business"))
}
