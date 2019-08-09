package kr.lul.inventory.design.domain

/**
 * 속성값에 문제가 있을 때.
 *
 * @author justburrow
 * @since 2019-08-09
 *
 * @param baseMessage 기본 메시지.
 * @param attrName 문제가 있는 속성.
 * @param value 속성의 값.
 */
class InvalidAttributeException(
        val baseMessage: String,
        val attrName: String,
        val value: Any?
) : RuntimeException("$baseMessage : attr=$attrName, value=$value")
