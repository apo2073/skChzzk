사용 예제
```scss
command chzzk:
  trigger:
    client set with id "(클라이언트 아이디)" and secret "(클라이언트 시크릿 아이디)"
    chzzk auth as cookies with "(NID_AUT)" and "(NID_SES)"
    connect player to chzzk channel ""
    send "연결됨: %player's chzzk name%" to player

on chzzk chat:
  send "%message sender% : %chat content%" to channel connector as player

on donation: 
  send "%donation sender% : %donation content% ( &e%donation amount%치즈&f )" to channel connector as player

on left:
  disconnect player's channel from chzzk
```