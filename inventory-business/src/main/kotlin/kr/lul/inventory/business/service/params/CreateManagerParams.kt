package kr.lul.inventory.business.service.params

data class CreateManagerParams(
        val email: String,
        val name: String,
        val secret: String
)
