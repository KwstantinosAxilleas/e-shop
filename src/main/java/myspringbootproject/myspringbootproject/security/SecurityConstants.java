package myspringbootproject.myspringbootproject.security;



public class SecurityConstants {
    public static final String SECRET_KEY = "MEgCQQDfrM65tIZkhGRqoE5mGNIP+bWsIY26idnEftR1r2r4aSFPyUNIr84WuCjl";
    public static final int TOKEN_EXPIRATION = 7200000; // 2 hours
    public static final String BEARER = "Bearer "; // Authorization : "Bearer " + Token
    public static final String AUTHORIZATION = "Authorization"; // "Authorization" : Bearer Token
    public static final String REGISTER_PATH = "/consumer/register"; // Public path that clients can use to register.
}
