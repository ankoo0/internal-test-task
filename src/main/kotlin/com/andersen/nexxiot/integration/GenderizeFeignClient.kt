package com.andersen.nexxiot.integration

import com.andersen.nexxiot.integration.response.GenderizeResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "genderize-client", url = "\${integration.genderize.url}")
interface GenderizeFeignClient {

    @GetMapping
    fun getGenderProbability(@RequestParam name:String) : GenderizeResponse
}