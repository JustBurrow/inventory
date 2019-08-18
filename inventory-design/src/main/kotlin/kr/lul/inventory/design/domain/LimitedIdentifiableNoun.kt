package kr.lul.inventory.design.domain

import java.time.Instant

/**
 * @author justburrow
 * @since 2019-07-15
 */
interface LimitedIdentifiableNoun : LimitedNoun, IdentifiableNoun {
    interface Updater : IdentifiableNoun.Updater, LimitedNoun.Updater

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.design.domain.IdentifiableNoun
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override val type: NounType
        get() = NounType.LIMITED_IDENTIFIABLE

    override fun updater(updatedAt: Instant): Updater
}
