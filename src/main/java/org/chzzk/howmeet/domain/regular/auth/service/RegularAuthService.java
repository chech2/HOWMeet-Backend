package org.chzzk.howmeet.domain.regular.auth.service;

import lombok.RequiredArgsConstructor;
import org.chzzk.howmeet.domain.common.auth.model.AuthPrincipal;
import org.chzzk.howmeet.domain.regular.auth.dto.login.request.MemberLoginRequest;
import org.chzzk.howmeet.domain.regular.auth.dto.login.response.MemberLoginResponse;
import org.chzzk.howmeet.domain.regular.member.entity.Member;
import org.chzzk.howmeet.global.util.TokenProvider;
import org.chzzk.howmeet.infra.oauth.model.OAuthProvider;
import org.chzzk.howmeet.infra.oauth.repository.InMemoryOAuthProviderRepository;
import org.chzzk.howmeet.infra.oauth.service.OAuthClient;
import org.springframework.stereotype.Service;
import reactor.core.scheduler.Schedulers;

@RequiredArgsConstructor
@Service
public class RegularAuthService {
    private final InMemoryOAuthProviderRepository inMemoryOAuthProviderRepository;
    private final OAuthClient oAuthClient;
    private final OAuthResultHandler oauthResultHandler;
    private final TokenProvider tokenProvider;

    public MemberLoginResponse login(final MemberLoginRequest memberLoginRequest) {
        final OAuthProvider oAuthProvider = inMemoryOAuthProviderRepository.findByProviderName(memberLoginRequest.providerName());
        final Member member = oAuthClient.getProfile(oAuthProvider, memberLoginRequest.code())
                .publishOn(Schedulers.boundedElastic())
                .map(oauthResultHandler::saveOrGet)
                .block();

        final AuthPrincipal authPrincipal = AuthPrincipal.from(member);
        final String accessToken = tokenProvider.createToken(authPrincipal);
        return MemberLoginResponse.of(accessToken, member);
    }
}
