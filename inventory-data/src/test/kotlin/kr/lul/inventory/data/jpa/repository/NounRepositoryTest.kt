package kr.lul.inventory.data.jpa.repository

import kr.lul.inventory.data.DataModuleTestConfiguration
import kr.lul.inventory.test.data.ManagerDataUtil
import kr.lul.inventory.test.data.NounDataUtil
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author justburrow
 * @since 2019-07-06
 */
@RunWith(SpringRunner::class)
@DataJpaTest
@ContextConfiguration(classes = [DataModuleTestConfiguration::class])
class NounRepositoryTest {
    private val log = LoggerFactory.getLogger(NounRepositoryTest::class.java)!!

    @Autowired
    private lateinit var nounRepository: NounRepository
    @Autowired
    private lateinit var managerDataUtil: ManagerDataUtil
    @Autowired
    private lateinit var nounDataUtil: NounDataUtil

    @Test
    fun `test findAll()`() {
        // WHEN
        val list = nounRepository.findAll()
        log.debug("WHEN - list=$list")

        // THEN
        assertThat(list)
                .isEmpty()
    }
}
