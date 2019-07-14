package kr.lul.inventory.test.data

import kr.lul.inventory.design.domain.Noun
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author justburrow
 * @since 2019-07-14
 */
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [TestDataModuleTestConfiguration::class])
class NounDataUtilTest {
    private val log = LoggerFactory.getLogger(NounDataUtilTest::class.java)!!

    @Autowired
    private lateinit var nounDataUtil: NounDataUtil

    @Before
    fun setUp() {
    }

    @Test
    fun `test key()`() {
        // WHEN
        val key = nounDataUtil.key()
        log.debug("WHEN - key={}", key)

        // THEN
        assertThat(key)
                .matches(Noun.KEY_PATTERN)
        assertThat(key.length)
                .isGreaterThanOrEqualTo(Noun.KEY_MIN_LENGTH)
                .isLessThanOrEqualTo(Noun.KEY_MAX_LENGTH)
        Noun.validateKey(key)
    }
}
