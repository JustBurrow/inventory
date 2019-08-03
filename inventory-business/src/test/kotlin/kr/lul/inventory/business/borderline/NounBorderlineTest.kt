package kr.lul.inventory.business.borderline

import kr.lul.inventory.business.BusinessModuleTestConfiguration
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author justburrow
 * @since 2019-07-09
 */
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [BusinessModuleTestConfiguration::class])
class NounBorderlineTest {
    private val log = LoggerFactory.getLogger(NounBorderlineTest::class.java)!!

    @Test
    fun test_name() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
