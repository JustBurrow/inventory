package kr.lul.inventory.design.domain

/**
 * @author justburrow
 * @since 2019-07-06
 */
class AttributeValidationException(val attribute: String, val value: Any, message: String) : RuntimeException(message)
