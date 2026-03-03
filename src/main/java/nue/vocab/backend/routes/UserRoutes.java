package nue.vocab.backend.routes;

public class UserRoutes {
     private static final String BASE = "/api/users";

    public static final String GET_ALL    = BASE + "/all";
    public static final String GET_BY_ID  = BASE + "/{id}";
    public static final String CREATE     = BASE + "/add";
    public static final String LOGIN     = BASE + "/login";
    public static final String DELETE     = BASE + "/remove/{id}";
}
