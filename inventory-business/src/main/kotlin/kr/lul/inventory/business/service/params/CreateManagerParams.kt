package kr.lul.inventory.business.service.params

import java.time.Instant

data class CreateManagerParams(
        val email: String,
        val name: String,
        val password: String,
        val timestamp: Instant
)
