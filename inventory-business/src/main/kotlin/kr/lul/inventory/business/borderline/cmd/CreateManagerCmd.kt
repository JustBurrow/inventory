package kr.lul.inventory.business.borderline.cmd

data class CreateManagerCmd(
        val email: String,
        val name: String,
        val password: String
)
