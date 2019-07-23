package kr.lul.inventory.data.jpa.repository

import kr.lul.inventory.data.DataModuleTestConfiguration
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
class LimitedIdentifiableNounRepositoryTest {
    private val log = LoggerFactory.getLogger(LimitedIdentifiableNounRepositoryTest::class.java)!!

    @Autowired
    private lateinit var repository: LimitedIdentifiableNounRepository

    @Test
    fun `test findAll()`() {
        // WHEN
        val list = repository.findAll()
        log.debug("WHEN - list=$list")

        // THEN
        assertThat(list)
                .isEmpty()
    }
}
