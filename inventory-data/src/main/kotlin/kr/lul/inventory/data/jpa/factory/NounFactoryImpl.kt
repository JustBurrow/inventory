package kr.lul.inventory.data.jpa.factory

import kr.lul.inventory.data.jpa.entity.CountableNounEntity
import kr.lul.inventory.data.jpa.entity.IdentifiableNounEntity
import kr.lul.inventory.data.jpa.entity.LimitedCountableNounEntity
import kr.lul.inventory.data.jpa.entity.LimitedIdentifiableNounEntity
import kr.lul.inventory.design.domain.*
import kr.lul.inventory.design.factory.NounFactory
import org.springframework.stereotype.Component
import java.time.Instant

/**
 * @author justburrow
 * @since 2019-07-18
 */
@Component
class NounFactoryImpl : NounFactory {
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.design.factory.NounFactory
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun identifiable(
            manager: Manager, key: String, label: String, labelCode: String, description: String, createdAt: Instant
    ): IdentifiableNoun = IdentifiableNounEntity(manager, key, label, labelCode, description, createdAt)

    override fun countable(
            manager: Manager, key: String, label: String, labelCode: String, description: String, createdAt: Instant
    ): CountableNoun = CountableNounEntity(manager, key, label, labelCode, description, createdAt)

    override fun limitedIdentifiable(
            manager: Manager, key: String, label: String, labelCode: String, limit: Int, description: String,
            createdAt: Instant
    ): LimitedIdentifiableNoun =
            LimitedIdentifiableNounEntity(manager, key, label, labelCode, limit, description, createdAt)

    override fun limitedCountable(
            manager: Manager, key: String, label: String, labelCode: String, limit: Int, description: String,
            createdAt: Instant
    ): LimitedCountableNoun = LimitedCountableNounEntity(manager, key, label, labelCode, limit, description, createdAt)
}
