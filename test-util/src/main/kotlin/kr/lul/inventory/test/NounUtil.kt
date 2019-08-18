package kr.lul.inventory.test

import kr.lul.inventory.data.jpa.entity.AbstractNounEntity
import kr.lul.inventory.data.jpa.entity.CountableNounEntity
import kr.lul.inventory.data.jpa.entity.IdentifiableNounEntity
import kr.lul.inventory.data.jpa.entity.LimitedCountableNounEntity
import kr.lul.inventory.data.jpa.entity.LimitedIdentifiableNounEntity
import kr.lul.inventory.data.jpa.entity.ManagerEntity
import kr.lul.inventory.data.jpa.repository.NounRepository
import kr.lul.inventory.design.domain.Noun
import kr.lul.inventory.design.domain.NounType
import kr.lul.inventory.design.domain.NounType.COUNTABLE
import kr.lul.inventory.design.domain.NounType.IDENTIFIABLE
import kr.lul.inventory.design.domain.NounType.LIMITED_COUNTABLE
import kr.lul.inventory.design.domain.NounType.LIMITED_IDENTIFIABLE
import kr.lul.inventory.design.util.TimeProvider
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.RandomStringUtils.randomAlphabetic
import org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric
import org.springframework.beans.factory.annotation.Autowired
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.ThreadLocalRandom.current

class NounUtil {
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
                key = "test.${RandomStringUtils.randomAlphabetic(1)}${RandomStringUtils.randomAlphanumeric(0,
                        Noun.KEY_MAX_LENGTH - 15)}"
                        .toLowerCase()
            } while (!Noun.isValidKey(key))

            return key
        }
    val label: String
        get() {
            var label: String

            do {
                label = "test noun label - ${RandomStringUtils.random(
                        current().nextInt(1, Noun.LABEL_MAX_LENGTH - 20))}"
            } while (!Noun.isValidLabel(label))

            return label
        }

    val labelCode: String
        get() {
            var labelCode: String

            do {
                labelCode = "test.${randomAlphabetic(1)}${randomAlphanumeric(0, 10)}".toLowerCase()
            } while (!Noun.isValidLabelCode(labelCode))

            return labelCode
        }

    val unusedLabel: String
        get() {
            var label: String
            do {
                label = this.label
            } while (repository.existsByLabel(label))
            return label
        }
    val unusedLabelCode: String
        get() {
            var labelCode: String
            do {
                labelCode = this.labelCode
            } while (repository.existsByLabelCode(labelCode))
            return labelCode
        }

    val description: String
        get() = RandomStringUtils.random(ThreadLocalRandom.current().nextInt(0, 1 + Noun.DESCRIPTION_MAX_LENGTH))

    fun freshIdentifiable(manager: ManagerEntity = managerUtil.manager()) =
            IdentifiableNounEntity(manager, key, label, labelCode, description, timeProvider.instant)

    fun freshCountable(manager: ManagerEntity = managerUtil.manager()) =
            CountableNounEntity(manager, key, label, labelCode, description, timeProvider.instant)

    fun freshLimitedIdentifiable(manager: ManagerEntity = managerUtil.manager()) =
            LimitedIdentifiableNounEntity(manager, key, label, labelCode,
                    ThreadLocalRandom.current().nextInt(1, Int.MAX_VALUE),
                    description, timeProvider.instant)

    fun freshLimitedCountable(manager: ManagerEntity = managerUtil.manager()) =
            LimitedCountableNounEntity(manager, key, label, labelCode,
                    ThreadLocalRandom.current().nextInt(1, Int.MAX_VALUE),
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

    fun random(manager: ManagerEntity = managerUtil.manager()): AbstractNounEntity =
            when (NounType.values().toList().shuffled()[0]) {
                IDENTIFIABLE -> identifiable(manager)
                COUNTABLE -> countable(manager)
                LIMITED_IDENTIFIABLE -> limitedIdentifiable(manager)
                LIMITED_COUNTABLE -> limitedCountable(manager)
            }
}
