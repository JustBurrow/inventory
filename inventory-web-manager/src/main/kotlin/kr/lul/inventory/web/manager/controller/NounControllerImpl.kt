package kr.lul.inventory.web.manager.controller

import kr.lul.inventory.business.borderline.NounBorderline
import kr.lul.inventory.web.manager.controller.argument.CreateNounReq
import kr.lul.inventory.web.manager.mapping.NounMvc.M
import kr.lul.inventory.web.manager.mapping.NounMvc.V
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.web.SortDefault
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import javax.validation.Valid

@Controller
internal class NounControllerImpl : NounController {
    private val log = LoggerFactory.getLogger(NounControllerImpl::class.java)!!

    @Autowired
    private lateinit var nounBorderline: NounBorderline

    private fun doCreateForm(model: Model): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun doCreate(req: CreateNounReq, binding: BindingResult, model: Model): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.web.manager.controller.NounController
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun list(@SortDefault pageable: Pageable, model: Model): String {
        if (log.isTraceEnabled) log.trace("args : pageable=$pageable, model=$model")

        val template = V.LIST
        if (log.isTraceEnabled) log.trace("result : template='$template', model=$model")
        return template
    }

    override fun createForm(model: Model): String {
        if (log.isTraceEnabled) log.trace("args : model=$model")

        model.addAttribute(M.CREATE_NOUN_REQ, CreateNounReq(null, null, null))

        return V.CREATE_FORM
    }

    override fun create(@ModelAttribute(M.CREATE_NOUN_REQ) @Valid req: CreateNounReq, binding: BindingResult,
                        model: Model): String {
        if (log.isTraceEnabled) log.trace("args : req=$req, binding=$binding, model=$model")

        val template = if (binding.hasErrors())
            doCreateForm(model)
        else
            doCreate(req, binding, model)

        if (log.isTraceEnabled) log.trace("result : template='$template', model=$model")
        return template
    }
}
