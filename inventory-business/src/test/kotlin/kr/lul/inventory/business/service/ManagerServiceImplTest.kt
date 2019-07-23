package kr.lul.inventory.business.service

import kr.lul.inventory.business.BusinessModuleTestConfiguration
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

    private lateinit var before: Instant

    @Before
    fun setUp() {
        before = Instant.now()
    }

    @Test
    fun `test create() with random params`() {
    }
}
