package kr.lul.inventory.design.domain

import java.time.Instant

/**
 * @author justburrow
 * @since 2019-07-15
 */
interface LimitedCountableNoun : LimitedNoun, CountableNoun {
    interface Updater : CountableNoun.Updater, LimitedNoun.Updater

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.design.domain.CountableNoun
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override val type: NounType
        get() = NounType.LIMITED_COUNTABLE

    override fun updater(updatedAt: Instant): Updater
}
