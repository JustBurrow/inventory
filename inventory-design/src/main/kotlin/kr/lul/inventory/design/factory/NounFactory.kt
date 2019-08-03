package kr.lul.inventory.design.factory

import kr.lul.inventory.design.domain.*
import java.time.Instant

/**
 * @author justburrow
 * @since 2019-07-08
 */
interface NounFactory {
    fun identifiable(
            manager: Manager, key: String, label: String, labelCode: String, description: String, createdAt: Instant
    ): IdentifiableNoun

    fun countable(
            manager: Manager, key: String, label: String, labelCode: String, description: String, createdAt: Instant
    ): CountableNoun

    fun limitedIdentifiable(
            manager: Manager, key: String, label: String, labelCode: String, limit: Int, description: String,
            createdAt: Instant
    ): LimitedIdentifiableNoun

    fun limitedCountable(
            manager: Manager, key: String, label: String, labelCode: String, limit: Int, description: String,
            createdAt: Instant
    ): LimitedCountableNoun
}
