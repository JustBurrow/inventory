package kr.lul.inventory.design.util

class AssertionException(val target: Any, val name: String, message: String) : RuntimeException(message)
