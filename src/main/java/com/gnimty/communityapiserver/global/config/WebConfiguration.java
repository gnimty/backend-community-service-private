package com.gnimty.communityapiserver.global.config;


import com.gnimty.communityapiserver.global.interceptor.MemberAuthInterceptor;
import com.gnimty.communityapiserver.global.interceptor.TokenAuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfiguration implements WebMvcConfigurer {

	private final TokenAuthInterceptor tokenAuthInterceptor;
	private final MemberAuthInterceptor memberAuthInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
			.addInterceptor(tokenAuthInterceptor)
			.addPathPatterns("/**")
			.excludePathPatterns("/auth/**");

		registry
			.addInterceptor(memberAuthInterceptor)
			.addPathPatterns("/members/**");
	}
}
