package com.neher.ecl.share;

public class Env {

    public static final class remote{
        public static final String register_url = "http://139.162.60.218/test/public/index.php/api/register";
        public static final String login_url = "http://139.162.60.218/test/public/index.php/api/login";
        public static final String sharing_request_url = "http://139.162.60.218/test/public/index.php/api/sharing-request";
    }

    public static final class sp{
        public static final String sp_name = "com.neher.ecl.share.user.info";
        public static final String access_token = "access_token";
        public static final String user_name = "user_name";
        public static final String user_mobile = "user_mobile";
        public static final String user_gender = "user_gender";
        public static final String user_age = "user_age";
        public static final String user_password = "user_password";
    }
}
