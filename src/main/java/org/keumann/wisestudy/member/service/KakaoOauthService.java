package org.keumann.wisestudy.member.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.keumann.wisestudy.admin.member.service.MemberService;
import org.keumann.wisestudy.constant.MemberType;
import org.keumann.wisestudy.domain.Member;
import org.keumann.wisestudy.member.dto.MemberDto;
import org.keumann.wisestudy.member.model.KakaoProfile;
import org.keumann.wisestudy.member.model.OauthToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class KakaoOauthService {

    @Value("${kakao.client_id}")
    String kakaoClientId;

    @Value("${kakao.client_secret}")
    String kakaoClientSecret;

    @Value("${active.domain}")
    String activeDomain;

    private final MemberService memberService;

    public OauthToken getOauthToken(String code){

        //POST방식으로 key-value 데이터를 카카오로 요청
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        //전송할 body 데이터가 key-value 형태의 데이터라고 header를 통해 알려줌
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //전송할 body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoClientId);
        params.add("redirect_uri",activeDomain + "/auth/kakao/callback");
        params.add("code",code);
        params.add("client_secret", kakaoClientSecret);

        //body와 header 값을 가지고 있는 entity
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        ResponseEntity<String> responseEntity = rt.exchange(
                "https://kauth.kakao.com/oauth/token",  //요청 주소
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class  //응답
        );

        //JSON 변환 : Gson, Json Simple, ObjectMapper 중 사용
        ObjectMapper objectMapper = new ObjectMapper();
        OauthToken oauthToken = null;
        try {
            oauthToken = objectMapper.readValue(responseEntity.getBody(), OauthToken.class);
        } catch (JsonMappingException e){
            e.printStackTrace();
        } catch(JsonProcessingException e){
            e.printStackTrace();
        }

        return oauthToken;
    }

    public KakaoProfile getProfile(OauthToken oauthToken){
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        //전송할 body 데이터가 key-value 형태의 데이터라고 header를 통해 알려줌
        headers.add("Authorization", "Bearer " + oauthToken.getAccess_token());
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //body와 header 값을 가지고 있는 entity
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity2 = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",  //요청 주소
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class  //응답
        );

        //JSON 변환 : Gson, Json Simple, ObjectMapper 중 사용
        KakaoProfile kakaoProfile = null;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            kakaoProfile = objectMapper.readValue(responseEntity2.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }

        return kakaoProfile;
    }

    public Member createMember(KakaoProfile kakaoProfile){
        MemberDto memberDto = new MemberDto();
        memberDto.setEmail(kakaoProfile.getKakao_account().getEmail());
        memberDto.setName(kakaoProfile.getProperties().getNickname());
        memberDto.setGender(kakaoProfile.getKakao_account().getGender());
        memberDto.setUserType(MemberType.KAKAO.getMemberType());
        memberDto.setAgeRange(kakaoProfile.getKakao_account().getAge_range());
        memberDto.setProfileUrl(kakaoProfile.getProperties().getProfile_image());
        memberDto.setPassword(Integer.toString(kakaoProfile.getId()));

        return memberService.createMember(memberDto);
    }

}
