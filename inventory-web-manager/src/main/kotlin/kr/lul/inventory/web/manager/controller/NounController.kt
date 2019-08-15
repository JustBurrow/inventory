package kr.lul.inventory.web.manager.controller

import kr.lul.inventory.web.manager.controller.argument.CreateNounReq
import kr.lul.inventory.web.manager.controller.argument.CurrentManager
import kr.lul.inventory.web.manager.controller.argument.UpdateNounReq
import kr.lul.inventory.web.manager.mapping.NounMvc.C
import kr.lul.inventory.web.manager.mapping.NounMvc.M
import org.springframework.data.domain.Pageable
import org.springframework.data.web.SortDefault
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

/**
 * @author justburrow
 * @since 2019-07-10
 */
@RequestMapping(C.GROUP)
interface NounController {
    @GetMapping(C.API_CREATE_FORM)
    fun createForm(user: CurrentManager, model: Model): String

    @PostMapping(C.API_CREATE)
    fun create(
            user: CurrentManager,
            @ModelAttribute(M.CREATE_NOUN_REQ) @Valid req: CreateNounReq,
            binding: BindingResult,
            model: Model
    ): String

    @GetMapping(C.API_DETAIL)
    fun detail(user: CurrentManager, @PathVariable(M.NOUN_ID) id: Int, model: Model): String

    @GetMapping(C.API_LIST)
    fun list(
            user: CurrentManager,
            @SortDefault pageable: Pageable,
            model: Model
    ): String

    @GetMapping(C.API_UPDATE_FORM)
    fun updateForm(user: CurrentManager, @PathVariable(M.NOUN_ID) id: Int, model: Model): String

    @PatchMapping(C.API_UPDATE)
    fun update(
            user: CurrentManager,
            @PathVariable(M.NOUN_ID) id: Int,
            @ModelAttribute(M.UPDATE_NOUN_REQ) @Valid req: UpdateNounReq,
            binding: BindingResult,
            model: Model
    ): String
}
