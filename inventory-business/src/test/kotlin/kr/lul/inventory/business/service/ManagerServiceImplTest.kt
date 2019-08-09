package kr.lul.inventory.business.service

import kr.lul.inventory.business.BusinessModuleTestConfiguration
import kr.lul.inventory.business.service.params.CreateManagerParams
import kr.lul.inventory.data.jpa.repository.ManagerCredentialRepository
import kr.lul.inventory.design.domain.Manager.Companion.ATTR_CREATED_AT
import kr.lul.inventory.design.domain.Manager.Companion.ATTR_EMAIL
import kr.lul.inventory.design.domain.Manager.Companion.ATTR_NAME
import kr.lul.inventory.design.domain.Manager.Companion.ATTR_UPDATED_AT
import kr.lul.inventory.design.domain.ManagerCredential.Companion.validateSecretHash
import kr.lul.inventory.design.util.TimeProvider
import kr.lul.inventory.test.ManagerUtil
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

/**
 * @author justburrow
 * @since 2019-07-14
 */
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [BusinessModuleTestConfiguration::class])
@Transactional
class ManagerServiceImplTest {
    private val log = LoggerFactory.getLogger(ManagerServiceImplTest::class.java)!!

    @Autowired
    private lateinit var service: ManagerService

    @Autowired
    private lateinit var credentialRepository: ManagerCredentialRepository
    @Autowired
    private lateinit var util: ManagerUtil
    @Autowired
    private lateinit var timeProvider: TimeProvider

    private lateinit var before: Instant

    @Before
    fun setUp() {
        before = timeProvider.instant
    }

    @Test
    fun `test create() with fresh params`() {
        // GIVEN
        val params = CreateManagerParams(
                util.unusedEmail,
                util.unusedName,
                util.password,
                before)
        log.debug("GIVEN - params=$params")

        // WHEN
        val manager = service.create(params)
        log.debug("WHEN - manager=$manager")

        // THEN
        assertThat(manager)
                .extracting(ATTR_NAME, ATTR_EMAIL, ATTR_CREATED_AT, ATTR_UPDATED_AT)
                .containsSequence(params.name, params.email, before, before)
        assertThat(manager.id)
                .isPositive()

        credentialRepository.findAllByManager(manager).forEach {
            assertThat(it.id)
                    .isPositive()
            assertThat(it.publicKey)
                    .isIn(params.name, params.email)
            validateSecretHash(it.secretHash)
            assertThat(it.createdAt)
                    .isEqualTo(manager.createdAt)
        }
    }
}
