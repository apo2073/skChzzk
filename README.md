# Chzzk, but skript

---

__usage__
```scss
command /chzzk [<text>] [<text>]:
    trigger:
        if arg 1 is "connect":
            if arg 2 is set:
                connect to chzzk channel arg-2
                set {_name} to chzzk channel name
                set {_fol} to chzzk channel followers
                send "연결됨  %{_name}%(%{_followers}% 팔로워)"
            else:
                send "§c채널 ID를 입력해주세요"
        else if arg 1 is "disconnect":
            send "&c채널 %channel name%을(를) 연결 해제 했습니다"
            disconnect from chzzk
        else:
            send "§c/chzzk connect <채널ID> 또는 /chzzk disconnect를 사용해주세요"

on chzzk chat:
    set {_content} to chzzk message content
    set {_sender} to chzzk message sender
    broadcast "§b[치지직] §f%{_sender}%: %{_content}%"
```