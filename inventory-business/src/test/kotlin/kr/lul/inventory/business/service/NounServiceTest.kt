package kr.lul.inventory.business.service

import kr.lul.inventory.business.BusinessModuleTestConfiguration
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional

/**
 * @author justburrow
 * @since 2019-07-08
 */
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [BusinessModuleTestConfiguration::class])
@Transactional
class NounServiceTest {
    private val log = LoggerFactory.getLogger(NounServiceTest::class.java)!!

    @Test
    fun `test name`() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
