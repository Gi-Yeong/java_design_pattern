package com.design.pattern.connection;

public abstract class AbstGameConnectHelper {

    // 외부에 노출이 되면 안되지만, 하위 클래스들이 재정의 해야 하기 때문에 protected 로 되어야 한다
    protected abstract String doSecurity(String string);
    protected abstract boolean authentication(String id, String password);
    protected abstract int authorization(String userName);
    protected abstract String connection(String info);

    // 템플릿 메소드
    public String requestConnection(String encodedInfo) {
        // 보안 작업 -> 암호화 된 문자열을 디코드 한다
        String decodedInfo =  doSecurity(encodedInfo);

        // 반환 된 것을 가지고 아이디, 암호를 할당한다
        String id = "aaa";
        String password = "bbb";

        // 인증 과정
        if (!authentication(id, password)) {
            throw new Error("아이디 암호 불일치");
        }

        // 권한 과정
        String userName = "";
        int i = authorization(userName);
        switch (i) {
            case -1:
                throw  new Error("셧다운");
            case 0: // 게임 매니저
                break;
            case 1: // 유료 회원
                break;
            case 2: // 무료 회원
                break;
            case 3: // 권한 없음
                break;
            default: // 기타 사항
                break;
        }

        return connection(decodedInfo);
    }
}
