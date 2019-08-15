package kr.lul.inventory.design.util

import kotlin.reflect.KClass

/**
 * @author justburrow
 * @since 2019-07-07
 */
object Assertion {
    @Throws(AssertionException::class)
    fun zero(num: Int, name: String) {
        if (0 != num) throw AssertionException(num, name, "$name is not zero : $num")
    }

    @Throws(AssertionException::class)
    fun zero(num: Long, name: String) {
        if (0L != num) throw AssertionException(num, name, "$name is not zero : $num")
    }

    @Throws(AssertionException::class)
    fun positive(num: Int, name: String) {
        if (0 >= num) throw AssertionException(num, name, "$name is not positive : $num")
    }

    @Throws(AssertionException::class)
    fun positive(num: Long, name: String) {
        if (0L >= num) throw AssertionException(num, name, "$name is not positive : $num")
    }

    @Throws(AssertionException::class)
    fun notNegative(num: Int, name: String) {
        if (0 > num)
            throw AssertionException(num, name, "$name is negative : $num")
    }

    @Throws(AssertionException::class)
    fun notNegative(num: Long, name: String) {
        if (0 > num)
            throw AssertionException(num, name, "$name is negative : $num")
    }

    @Throws(AssertionException::class)
    fun `is`(target: Any, expect: KClass<out Any>, name: String) {
        if (target::class != expect) {
            throw AssertionException(target, name, "$name is not $expect : target=${target::class}, expect=$expect")
        }
    }
}
