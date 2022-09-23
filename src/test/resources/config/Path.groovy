package config

class Path {
    static String template = "http://localhost:8888/api_testing/product";
    public static String get = "/read_one.php";
    public static   String update = "/update.php";
    public  static String delete = "/delete.php"
    public static   String create ="/create.php";
    public static String pathTemplate =   "src/test/resources";
    public static String pathRequest = "/requests";
    public static String pathResponse = "/responses";
    public static String pathDelete = "/delete-request.json";
    public static String pathPut = "/put-request.json";
    public static String pathPost = "/post-request.json";
    public static String pathPostResponse = "/post-response.json";
    public static String pathGetResponse = "/get-response.json";
    public static String pathPutResponse = "/put-response.json";
    public static String pathDeleteResponse = "/delete-response.json";
    public static String dataMessage = "message";
    public static  int SUCCESS_CODE = 200;


    static String endpoint(String request ) {
        return template+ request;
    }

    static String pathRequest(String http  ) {
        return pathTemplate+ pathRequest + http;
    }

    static String pathResponse(String http ) {
        return pathTemplate+ pathResponse + http;
    }
}
