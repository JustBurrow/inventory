package kr.lul.inventory.data.dao

import kr.lul.inventory.data.DataModuleTestConfiguration
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional

/**
 * @author justburrow
 * @since 2019-07-14
 */
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [DataModuleTestConfiguration::class])
@Transactional
class ManagerDaoImplTest {
    private val log = LoggerFactory.getLogger(ManagerDaoImplTest::class.java)!!

    @Autowired
    private lateinit var managerDao: ManagerDao

    @Test
    fun `test create() with random ManagerEntity`() {
    }
}
