package com.andersen.nexxiot.exception

import feign.Response
import feign.codec.ErrorDecoder
import java.lang.Exception

class FeignErrorDecoder : ErrorDecoder {

    override fun decode(p0: String?, p1: Response?): Exception {
        TODO("Make mapping from genderize response to ProblemDetail")
    }
}