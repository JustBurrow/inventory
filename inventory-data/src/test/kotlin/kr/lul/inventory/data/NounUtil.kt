package kr.lul.inventory.data

import kr.lul.inventory.data.jpa.entity.*
import kr.lul.inventory.data.jpa.repository.NounRepository
import kr.lul.inventory.design.domain.Noun.Companion.DESCRIPTION_MAX_LENGTH
import kr.lul.inventory.design.domain.Noun.Companion.KEY_MAX_LENGTH
import kr.lul.inventory.design.domain.Noun.Companion.LABEL_MAX_LENGTH
import kr.lul.inventory.design.domain.Noun.Companion.isValidKey
import kr.lul.inventory.design.domain.Noun.Companion.isValidLabel
import kr.lul.inventory.design.domain.Noun.Companion.isValidLabelCode
import kr.lul.inventory.design.util.TimeProvider
import org.apache.commons.lang3.RandomStringUtils.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import java.util.concurrent.ThreadLocalRandom.current

/**
 * @author justburrow
 * @since 2019-08-04
 */
class NounUtil {
    private val log = LoggerFactory.getLogger(NounUtil::class.java)!!

    @Autowired
    private lateinit var repository: NounRepository
    @Autowired
    private lateinit var managerUtil: ManagerUtil
    @Autowired
    private lateinit var timeProvider: TimeProvider

    val key: String
        get() {
            var key: String

            do {
                key = "test.${randomAlphabetic(1)}${randomAlphanumeric(0, KEY_MAX_LENGTH - 15)}"
                        .toLowerCase()
            } while (!isValidKey(key))

            return key
        }
    val label: String
        get() {
            var label: String

            do {
                label = "test noun label - ${random(current().nextInt(1, LABEL_MAX_LENGTH - 20))}"
            } while (!isValidLabel(label))

            return label
        }

    val labelCode: String
        get() {
            var labelCode: String

            do {
                labelCode = "test.${randomAlphabetic(1)}".toLowerCase()
            } while (!isValidLabelCode(labelCode))

            return labelCode
        }

    val description: String
        get() = random(current().nextInt(0, 1 + DESCRIPTION_MAX_LENGTH))

    fun freshIdentifiable(manager: ManagerEntity = managerUtil.manager()) =
            IdentifiableNounEntity(manager, key, label, labelCode, description, timeProvider.instant)

    fun freshCountable(manager: ManagerEntity = managerUtil.manager()) =
            CountableNounEntity(manager, key, label, labelCode, description, timeProvider.instant)

    fun freshLimitedIdentifiable(manager: ManagerEntity = managerUtil.manager()) =
            LimitedIdentifiableNounEntity(manager, key, label, labelCode, current().nextInt(1, Int.MAX_VALUE),
                    description, timeProvider.instant)

    fun freshLimitedCountable(manager: ManagerEntity = managerUtil.manager()) =
            LimitedCountableNounEntity(manager, key, label, labelCode, current().nextInt(1, Int.MAX_VALUE),
                    description, timeProvider.instant)


    fun identifiable(manager: ManagerEntity = managerUtil.manager()): IdentifiableNounEntity {
        while (true) {
            try {
                return repository.saveAndFlush(freshIdentifiable(manager))
            } catch (e: Exception) {
            }
        }
    }

    fun countable(manager: ManagerEntity = managerUtil.manager()): CountableNounEntity {
        while (true) {
            try {
                return repository.saveAndFlush(freshCountable(manager))
            } catch (e: Exception) {
            }
        }
    }

    fun limitedIdentifiable(manager: ManagerEntity = managerUtil.manager()): LimitedIdentifiableNounEntity {
        while (true) {
            try {
                return repository.saveAndFlush(freshLimitedIdentifiable(manager))
            } catch (e: Exception) {
            }
        }
    }

    fun limitedCountable(manager: ManagerEntity = managerUtil.manager()): LimitedCountableNounEntity {
        while (true) {
            try {
                return repository.saveAndFlush(freshLimitedCountable(manager))
            } catch (e: Exception) {
            }
        }
    }
}
