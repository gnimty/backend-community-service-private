package com.gnimty.communityapiserver.domain.riotaccount.controller;

import static com.gnimty.communityapiserver.global.constant.ResponseMessage.SUCCESS_UPDATE_SUMMONERS;
import static org.springframework.http.HttpStatus.OK;

import com.gnimty.communityapiserver.domain.member.controller.dto.request.SummonerUpdateRequest;
import com.gnimty.communityapiserver.domain.riotaccount.service.RiotAccountService;
import com.gnimty.communityapiserver.global.response.CommonResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("summoners")
public class RiotAccountController {

	private final RiotAccountService riotAccountService;

	@PatchMapping
	public CommonResponse<Void> updateSummoners(
		@RequestBody @Valid SummonerUpdateRequest request
	) {
		riotAccountService.updateSummoners(request.toServiceRequest());
		return CommonResponse.success(SUCCESS_UPDATE_SUMMONERS, OK);
	}
}
