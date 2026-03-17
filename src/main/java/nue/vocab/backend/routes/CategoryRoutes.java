package nue.vocab.backend.routes;

public class CategoryRoutes {
    private static final String BASE = "/api/categories";

    public static final String GET_ALL   = BASE + "/all";
    public static final String GET_BY_ID = BASE + "/{id}";
    public static final String CREATE    = BASE + "/add";
    public static final String DELETE    = BASE + "/remove/{id}";
}

