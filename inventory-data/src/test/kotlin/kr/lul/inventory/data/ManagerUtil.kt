package kr.lul.inventory.data

import kr.lul.inventory.data.jpa.entity.ManagerCredentialEntity
import kr.lul.inventory.data.jpa.entity.ManagerEntity
import kr.lul.inventory.data.jpa.repository.ManagerCredentialRepository
import kr.lul.inventory.data.jpa.repository.ManagerRepository
import kr.lul.inventory.design.domain.Manager
import kr.lul.inventory.design.domain.Manager.Companion.PASSWORD_MIN_LENGTH
import kr.lul.inventory.design.util.TimeProvider
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.RandomStringUtils.random
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.Instant
import java.util.concurrent.ThreadLocalRandom.current

/**
 * @author justburrow
 * @since 2019-07-24
 */
class ManagerUtil {
    private val log = LoggerFactory.getLogger(ManagerUtil::class.java)!!
    @Autowired
    private lateinit var managerRepository: ManagerRepository

    @Autowired
    private lateinit var credentialRepository: ManagerCredentialRepository
    @Autowired
    private lateinit var timeProvider: TimeProvider
    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder
    val name: String
        get() {
            var name: String

            do {
                name = "test.name.${random(current().nextInt(1, 10))}"
            } while (!Manager.isValidName(name))

            log.trace("return : '$name'")
            return name
        }

    val unusedName: String
        get() {
            var name: String

            do {
                name = this.name
            } while (managerRepository.existsByName(name))

            log.trace("return : '$name'")
            return name
        }

    val email: String
        get() {
            var email: String

            do {
                email = "${RandomStringUtils.randomAlphanumeric(1, 20)}@test.email".toLowerCase()
            } while (!Manager.isValidEmail(email))

            log.trace("return : '$email'")
            return email
        }

    val unusedEmail: String
        get() {
            var email: String

            do {
                email = this.email
            } while (managerRepository.existsByEmail(email))

            log.trace("return : '$email'")
            return email
        }

    val password: String = random(current().nextInt(PASSWORD_MIN_LENGTH, 4 * PASSWORD_MIN_LENGTH))

    /**
     * 새로 저장할 수 있는 [ManagerEntity].
     */
    fun freshManager(createdAt: Instant = timeProvider.instant) =
            ManagerEntity(this.unusedEmail, this.unusedName, createdAt)

    fun manager(password: String): ManagerEntity {
        val manager = managerRepository.save(freshManager())
        credentialRepository.save(
                ManagerCredentialEntity(manager, manager.name, passwordEncoder.encode(password), manager.createdAt))
        credentialRepository.save(
                ManagerCredentialEntity(manager, manager.email, passwordEncoder.encode(password), manager.createdAt))

        return manager
    }
}
