package kr.lul.inventory.business.service

import kr.lul.inventory.business.BusinessModuleTestConfiguration
import kr.lul.inventory.design.domain.Manager
import kr.lul.inventory.test.business.ManagerBusinessUtl
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
    private lateinit var managerService: ManagerService
    @Autowired
    private lateinit var managerBusinessUtl: ManagerBusinessUtl

    private lateinit var before: Instant

    @Before
    fun setUp() {
        before = Instant.now()
    }

    @Test
    fun `test create() with random params`() {
        // GIVEN
        val params = managerBusinessUtl.createParams()
        log.debug("GIVEN - params={}", params)

        // WHEN
        val manager = managerService.create(params)
        log.debug("WHEN - manager={}", manager)

        // Then
        assertThat(manager)
                .extracting(Manager.ATTR_EMAIL, Manager.ATTR_NAME)
                .containsSequence(params.email, params.name)
        assertThat(manager.getId())
                .isGreaterThan(0)
        assertThat(manager.getCreatedAt())
                .isAfterOrEqualTo(before)
                .isEqualTo(manager.getUpdatedAt())
    }
}
