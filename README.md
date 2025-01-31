# Chzzk, but skript

---

⛏ | 1.20.1 (Default)
⛾ | JDK17+

---

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
[the] [chzzk] channel player
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
[the] [chzzk] channel player
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
[the] [chzzk] channel player
```

---

made with [Chzzk4j](https://github.com/R2turnTrue/chzzk4j) *(modified)*
