package kr.lul.inventory.web.manager.controller

import kr.lul.inventory.business.borderline.NounBorderline
import kr.lul.inventory.business.borderline.cmd.CreateCountableNounCmd
import kr.lul.inventory.business.borderline.cmd.CreateIdentifiableNounCmd
import kr.lul.inventory.business.borderline.cmd.CreateLimitedCountableNounCmd
import kr.lul.inventory.business.borderline.cmd.CreateLimitedIdentifiableNounCmd
import kr.lul.inventory.business.borderline.cmd.ReadNounCmd
import kr.lul.inventory.business.borderline.cmd.SearchNounCmd
import kr.lul.inventory.business.borderline.cmd.UpdateCountableNounCmd
import kr.lul.inventory.business.borderline.cmd.UpdateIdentifiableNounCmd
import kr.lul.inventory.business.borderline.cmd.UpdateLimitedCountableNounCmd
import kr.lul.inventory.business.borderline.cmd.UpdateLimitedIdentifiableNounCmd
import kr.lul.inventory.business.service.NotOwnerException
import kr.lul.inventory.design.domain.AttributeValidationException
import kr.lul.inventory.design.domain.InvalidAttributeException
import kr.lul.inventory.design.domain.LimitedNoun
import kr.lul.inventory.design.domain.LimitedNoun.Companion.ATTR_LIMIT
import kr.lul.inventory.design.domain.Noun
import kr.lul.inventory.design.domain.NounType
import kr.lul.inventory.design.domain.NounType.COUNTABLE
import kr.lul.inventory.design.domain.NounType.IDENTIFIABLE
import kr.lul.inventory.design.domain.NounType.LIMITED_COUNTABLE
import kr.lul.inventory.design.domain.NounType.LIMITED_IDENTIFIABLE
import kr.lul.inventory.dto.LimitedNounDetailDto
import kr.lul.inventory.dto.NounDetailDto
import kr.lul.inventory.web.manager.configuration.ErrorCode.NounErrorCode.CREATE_LIMIT_IS_NULL
import kr.lul.inventory.web.manager.configuration.ErrorCode.NounErrorCode.CREATE_LIMIT_NOT_ACCEPTABLE
import kr.lul.inventory.web.manager.configuration.ErrorCode.NounErrorCode.CREATE_LIMIT_NOT_POSITIVE
import kr.lul.inventory.web.manager.configuration.ErrorCode.NounErrorCode.UPDATE_LIMIT_NOT_POSITIVE
import kr.lul.inventory.web.manager.configuration.ErrorCode.NounErrorCode.UPDATE_LIMIT_NULL
import kr.lul.inventory.web.manager.configuration.ErrorCode.NounErrorCode.UPDATE_TYPE_NULL
import kr.lul.inventory.web.manager.controller.argument.CreateNounReq
import kr.lul.inventory.web.manager.controller.argument.CurrentManager
import kr.lul.inventory.web.manager.controller.argument.UpdateNounReq
import kr.lul.inventory.web.manager.exception.NotFoundException
import kr.lul.inventory.web.manager.mapping.NounMvc.C
import kr.lul.inventory.web.manager.mapping.NounMvc.M
import kr.lul.inventory.web.manager.mapping.NounMvc.V
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.web.SortDefault
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import java.util.UUID.randomUUID
import javax.validation.Valid

@Controller
internal class NounControllerImpl : NounController {
    private val log = LoggerFactory.getLogger(NounControllerImpl::class.java)!!

    @Autowired
    private lateinit var nounBorderline: NounBorderline

    private fun doCreateForm(model: Model): String {
        if (!model.containsAttribute(M.CREATE_NOUN_REQ))
            model.addAttribute(M.CREATE_NOUN_REQ, CreateNounReq(null, null, null))

        return V.CREATE_FORM
    }

    /**
     * 추가 검증.
     */
    private fun validate(req: CreateNounReq, binding: BindingResult) {
    }

    private fun doCreateIdentifiable(user: CurrentManager, req: CreateNounReq, binding: BindingResult,
                                     model: Model): String {
        validate(req, binding)

        val template = if (binding.hasErrors())
            doCreateForm(model)
        else {
            val cmd = CreateIdentifiableNounCmd(
                    randomUUID(),
                    user.id!!,
                    req.key!!,
                    req.label!!,
                    req.labelCode!!,
                    req.description!!)
            val noun = nounBorderline.create(cmd)
            "redirect:${C.GROUP}/${noun.id}"
        }

        return template
    }

    private fun doCreateCountable(user: CurrentManager, req: CreateNounReq, binding: BindingResult,
                                  model: Model): String {
        validate(req, binding)

        val template = if (binding.hasErrors())
            doCreateForm(model)
        else {
            val cmd = CreateCountableNounCmd(
                    randomUUID(),
                    user.id!!,
                    req.key!!,
                    req.label!!,
                    req.labelCode!!,
                    req.description!!)
            val noun = nounBorderline.create(cmd)
            "redirect:${C.GROUP}/${noun.id}"
        }

        return template
    }

    /**
     * [LimitedNoun] 타입의 추가 검증.
     */
    private fun validateLimited(req: CreateNounReq, binding: BindingResult) {
        val error = if (null == req.limit)
            FieldError(M.CREATE_NOUN_REQ, LimitedNoun.ATTR_LIMIT, req.limit, false,
                    arrayOf(CREATE_LIMIT_IS_NULL), null, "limit is null.")
        else if (0 >= req.limit!!)
            FieldError(M.CREATE_NOUN_REQ, LimitedNoun.ATTR_LIMIT, req.limit, false,
                    arrayOf(CREATE_LIMIT_NOT_POSITIVE), null, "limit is not positive.")
        else
            null

        error ?: return
        binding.addError(error)
    }

    private fun doCreateLimitedIdentifiable(
            user: CurrentManager, req: CreateNounReq, binding: BindingResult, model: Model
    ): String {
        validateLimited(req, binding)

        val template = if (binding.hasErrors())
            doCreateForm(model)
        else {
            val cmd = CreateLimitedIdentifiableNounCmd(
                    randomUUID(),
                    user.id!!,
                    req.key!!,
                    req.label!!,
                    req.labelCode!!,
                    req.limit!!,
                    req.description!!)
            val noun = nounBorderline.create(cmd)
            "redirect:${C.GROUP}/${noun.id}"
        }

        return template
    }

    private fun doCreateLimitedCountable(
            user: CurrentManager, req: CreateNounReq, binding: BindingResult, model: Model
    ): String {
        validateLimited(req, binding)

        val template = if (binding.hasErrors())
            doCreateForm(model)
        else {
            val cmd = CreateLimitedCountableNounCmd(
                    randomUUID(),
                    user.id!!,
                    req.key!!,
                    req.label!!,
                    req.labelCode!!,
                    req.limit!!,
                    req.description!!)
            val noun = nounBorderline.create(cmd)
            "redirect:${C.GROUP}/${noun.id}"
        }

        return template
    }

    private fun doUpdateForm(user: CurrentManager, id: Int, req: UpdateNounReq?, model: Model): String {
        val noun = try {
            val cmd = ReadNounCmd(randomUUID(), user.id, id)
            nounBorderline.read<NounDetailDto>(cmd)
        } catch (e: NotOwnerException) {
            log.warn("${e.message} : user=$user, id=$id")
            null
        }
        model.addAttribute(M.NOUN, noun)

        if (null != noun && !model.containsAttribute(M.UPDATE_NOUN_REQ)) {
            val r = when (noun) {
                is LimitedNounDetailDto -> UpdateNounReq(
                        noun.type, noun.label, noun.labelCode, noun.limit, noun.description)
                else -> UpdateNounReq(noun.type, noun.label, noun.labelCode, null, noun.description)
            }
            model.addAttribute(M.UPDATE_NOUN_REQ, r)
        }

        if (log.isTraceEnabled) log.trace("result : model=$model")
        return V.UPDATE_FORM
    }

    private fun validate(req: UpdateNounReq, binding: BindingResult) {
    }

    private fun doUpdateIdentifiable(
            user: CurrentManager, id: Int, req: UpdateNounReq, binding: BindingResult, model: Model): String {

        validate(req, binding)

        return if (binding.hasErrors())
            doUpdateForm(user, id, req, model)
        else {
            val cmd = UpdateIdentifiableNounCmd(randomUUID(), user.id, id, req.label!!, req.labelCode!!,
                    req.description!!)
            val noun = nounBorderline.update(cmd)
            "redirect:${C.GROUP}/${noun.id}"
        }
    }

    private fun doUpdateCountable(
            user: CurrentManager, id: Int, req: UpdateNounReq, binding: BindingResult, model: Model): String {
        validate(req, binding)

        return if (binding.hasErrors())
            doUpdateForm(user, id, req, model)
        else {
            val cmd = UpdateCountableNounCmd(randomUUID(), user.id, id, req.label!!, req.labelCode!!, req.description!!)
            val noun = nounBorderline.update(cmd)
            "redirect:${C.GROUP}/${noun.id}"
        }
    }

    private fun validateLimited(req: UpdateNounReq, binding: BindingResult) {
        if (null == req.limit)
            binding.addError(FieldError(M.UPDATE_NOUN_REQ, ATTR_LIMIT, req.limit, false,
                    arrayOf(UPDATE_LIMIT_NULL), null, "limit is null."))
        else if (0 >= req.limit)
            binding.addError(FieldError(M.UPDATE_NOUN_REQ, ATTR_LIMIT, req.limit, false,
                    arrayOf(UPDATE_LIMIT_NOT_POSITIVE), null, "limit is not positive."))
    }

    private fun doUpdateLimitedIdentifiable(
            user: CurrentManager, id: Int, req: UpdateNounReq, binding: BindingResult, model: Model): String {
        validateLimited(req, binding)

        return if (binding.hasErrors())
            doUpdateForm(user, id, req, model)
        else {
            val cmd = UpdateLimitedIdentifiableNounCmd(randomUUID(), user.id, id, req.label!!, req.labelCode!!,
                    req.limit!!,
                    req.description!!)
            val noun = nounBorderline.update(cmd)
            "redirect:${C.GROUP}/${noun.id}"
        }
    }

    private fun doUpdateLimitedCountable(
            user: CurrentManager, id: Int, req: UpdateNounReq, binding: BindingResult, model: Model): String {
        validateLimited(req, binding)

        return if (binding.hasErrors())
            doUpdateForm(user, id, req, model)
        else {
            val cmd =
                    UpdateLimitedCountableNounCmd(randomUUID(), user.id, id, req.label!!, req.labelCode!!, req.limit!!,
                            req.description!!)
            val noun = nounBorderline.update(cmd)
            "redirect:${C.GROUP}/${noun.id}"
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.web.manager.controller.NounController
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun createForm(user: CurrentManager, model: Model): String {
        if (log.isTraceEnabled) log.trace("args : user=$user, model=$model")

        val template = doCreateForm(model)

        if (log.isTraceEnabled) log.trace("result : template='$template', model=$model")
        return V.CREATE_FORM
    }

    override fun create(
            user: CurrentManager,
            @ModelAttribute(M.CREATE_NOUN_REQ) @Valid req: CreateNounReq,
            binding: BindingResult,
            model: Model
    ): String {
        if (log.isTraceEnabled) log.trace("args : req=$req, binding=$binding, model=$model")

        val template = if (binding.hasErrors())
            doCreateForm(model)
        else {
            try {
                when (req.type) {
                    NounType.IDENTIFIABLE -> doCreateIdentifiable(user, req, binding, model)
                    NounType.COUNTABLE -> doCreateCountable(user, req, binding, model)
                    NounType.LIMITED_IDENTIFIABLE -> doCreateLimitedIdentifiable(user, req, binding, model)
                    NounType.LIMITED_COUNTABLE -> doCreateLimitedCountable(user, req, binding, model)
                    else -> {
                        binding.addError(FieldError(M.CREATE_NOUN_REQ, Noun.ATTR_TYPE, req.type, false,
                                arrayOf(CREATE_LIMIT_NOT_ACCEPTABLE), null, "not acceptable type : ${req.type}"))
                        doCreateForm(model)
                    }
                }
            } catch (e: InvalidAttributeException) {
                binding.addError(FieldError(M.CREATE_NOUN_REQ, e.attrName, e.value, false,
                        arrayOf(e.baseMessage), null, e.message))
                doCreateForm(model)
            }
        }

        if (log.isTraceEnabled) log.trace("result : template='$template', model=$model")
        return template
    }

    override fun detail(
            user: CurrentManager,
            @PathVariable(M.NOUN_ID) id: Int,
            model: Model
    ): String {
        if (log.isTraceEnabled) log.trace("args : user=$user, id=$id, model=$model")

        val noun = if (0 >= id) {
            throw NotFoundException("out of range : id=$id")
        } else try {
            nounBorderline.read<NounDetailDto>(ReadNounCmd(randomUUID(), user.id, id))
        } catch (e: NotOwnerException) {
            log.warn("${e.message} : user=$user, id=$id")
            throw NotFoundException(e.message!!, e)
        }
        model.addAttribute(M.NOUN, noun)

        if (log.isTraceEnabled) log.trace("result : template='${V.DETAIL}', model=$model")
        return V.DETAIL
    }

    override fun list(
            user: CurrentManager,
            @SortDefault pageable: Pageable,
            model: Model
    ): String {
        if (log.isTraceEnabled) log.trace("args : user=$user, pageable=$pageable, model=$model")

        val cmd = SearchNounCmd(randomUUID(), user.id, pageable.pageNumber, pageable.pageSize, pageable.sort)
        val list = nounBorderline.search(cmd)
        model.addAttribute(M.LIST, list)

        val template = V.LIST
        if (log.isTraceEnabled) log.trace("result : template='$template', model=$model")
        return template
    }

    override fun updateForm(user: CurrentManager, @PathVariable(M.NOUN_ID) id: Int, model: Model): String {
        if (log.isTraceEnabled) log.trace("args : user=$user, id=$id, model=$model")

        if (0 >= id) throw NotFoundException("out of range : id=$id")

        val template = doUpdateForm(user, id, null, model)

        if (log.isTraceEnabled) log.trace("result : template='$template', model=$model")
        return template
    }

    override fun update(
            user: CurrentManager,
            @PathVariable(M.NOUN_ID) id: Int,
            @ModelAttribute(M.UPDATE_NOUN_REQ) @Valid req: UpdateNounReq,
            binding: BindingResult,
            model: Model
    ): String {
        if (log.isTraceEnabled) log.trace("args : user=$user, id=$id, req=$req, binding=$binding, model=$model")

        if (0 >= id) throw NotFoundException("out of range : id=$id")

        val template = if (binding.hasErrors())
            doUpdateForm(user, id, req, model)
        else try {
            when (req.type) {
                IDENTIFIABLE -> doUpdateIdentifiable(user, id, req, binding, model)
                COUNTABLE -> doUpdateCountable(user, id, req, binding, model)
                LIMITED_IDENTIFIABLE -> doUpdateLimitedIdentifiable(user, id, req, binding, model)
                LIMITED_COUNTABLE -> doUpdateLimitedCountable(user, id, req, binding, model)
                else -> {
                    log.warn("$M.UPDATE_NOUN_REQ.type is null.")
                    binding.addError(ObjectError(M.UPDATE_NOUN_REQ, arrayOf(UPDATE_TYPE_NULL), null, "Illegal type.."))
                    doUpdateForm(user, id, req, model)
                }
            }
        } catch (e: AttributeValidationException) {
            log.warn(e.message, e)
            binding.addError(FieldError(M.UPDATE_NOUN_REQ, e.attribute, e.value, false, null, null, e.baseMessage))
            doUpdateForm(user, id, req, model)
        } catch (e: NotOwnerException) {
            log.warn(e.message, e)
            throw NotFoundException(e.message!!, e)
        }

        if (log.isTraceEnabled) log.trace("result : template='$template', model=$model")
        return template
    }
}
