package kr.lul.inventory.test.data

import kr.lul.inventory.data.dao.ManagerDao
import kr.lul.inventory.data.jpa.entity.ManagerEntity
import kr.lul.inventory.data.jpa.repository.ManagerRepository
import kr.lul.inventory.design.domain.Manager.Companion.isValidEmail
import kr.lul.inventory.design.domain.Manager.Companion.isValidName
import kr.lul.inventory.design.util.TimeProvider
import org.apache.commons.lang3.RandomStringUtils.random
import org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import java.util.concurrent.ThreadLocalRandom.current

/**
 * @author justburrow
 * @since 2019-07-23
 */
class ManagerDataUtil {
    private val log = LoggerFactory.getLogger(ManagerDataUtil::class.java)!!

    /**
     * `@DataJpaTeat`일 경우에는 `@Repository` 외의 일반 컴포넌트는 사용 불가.
     */
    @Autowired(required = false)
    private lateinit var dao: ManagerDao
    @Autowired
    private lateinit var repository: ManagerRepository
    @Autowired
    private lateinit var timeProvider: TimeProvider

    fun name(): String {
        var name: String

        do {
            name = "test.name.${random(current().nextInt(1, 10))}"
        } while (!isValidName(name))

        log.trace("return : '$name'")
        return name
    }

    fun unusedName(): String {
        var name: String

        do {
            name = name()
        } while (repository.existsByName(name))

        log.trace("return : '$name'")
        return name
    }

    fun email(): String {
        var email: String

        do {
            email = "${randomAlphanumeric(1, 20)}@test.email".toLowerCase()
        } while (!isValidEmail(email))

        log.trace("return : '$email'")
        return email
    }

    fun unusedEmail(): String {
        var email: String

        do {
            email = email()
        } while (repository.existsByEmail(email))

        log.trace("return : '$email'")
        return email
    }

    /**
     * 새로 저장할 수 있는 [ManagerEntity].
     */
    fun freshManager() = ManagerEntity(unusedEmail(), unusedName(), timeProvider.instant)
}
