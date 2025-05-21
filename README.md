# Chzzk, but skript

---

⛏ | 1.21.1 (Default)

⛾ | JDK21+

---

__로그인__
 * 클라이언트 로그인
```scss
[chzzk] client set with id %string% and secret %string%
```
 * 인증
```scss
[chzzk] auth as (api|naver|cookies) with %string% and %string%
```
1. api
 공식 api:
  앞에서 부터 accessToken, refreshToken
2. naver
 네이버 계정:
  앞에서 부터 네이버 아이디, 비밀번호
3. cookies:
 네이버 쿠키
  앞에서 부터 NID_AUT, NID_SES 쿠키

__채널 연결/연결해제__
```scss
connect %player% to [chzzk] channel %string%
disconnect %player%'s channel
disconnect (all|every) channel
```

__채널 정보__
```scss
[the] chzzk channel name of %string%
[the] chzzk channel follower[s] of %string%
```
__플레이어 채널 정보__
```scss
[(a|the)] %player%'s [chzzk] (channelId|channel)
[(a|the)] %player%'s [chzzk] (channelName|name)
[(a|the)] %player%'s [chzzk] (follower|fol)
```

__이벤트 - 채팅__
```scss
[chzzk] (chat|message) # 이벤트

# 이벤트 액션
[the] [chzzk] (message|chat) content
[the] [chzzk] (message|chat) sender
[the] [chzzk] channel id
[the] [chzzk] channel name
[the] [chzzk] channel connector
```

__이벤트 - 후원__
```scss
[chzzk] donation # 이벤트

# 이벤트 액션
[(the|a)] [chzzk] donation (amount|money)
[(the|a)] [chzzk] donation (sender|name)
[(the|a)] [chzzk] donation (content|message)
[the] [chzzk] channel id
[the] [chzzk] channel name
[the] [chzzk] channel connector
```

__이벤트 - 구독__
```scss
[chzzk] subscription # 이벤트

# 이벤트 액션
[the] [chzzk] subscription (sender|name)
[the] [chzzk] subscription month[s]
[the] [chzzk] subscription tier
[the] [chzzk] channel id
[the] [chzzk] channel name
[the] [chzzk] channel connector
```

---

made with [Chzzk4j](https://github.com/R2turnTrue/chzzk4j) *(modified)*
