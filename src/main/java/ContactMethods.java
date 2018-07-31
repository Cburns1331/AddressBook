import spark.*;
import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.get;
import static spark.Spark.*;

public class ContactMethods {
    public ContactMethods(final ImplementContact implementContact){

        get("/contacts", new Route() {
            @Override
            public Object handle(Request request, Response response) {
                return implementContact.getContacts();
            }
        });

        post("/contacts", new Route() {
            @Override
            public Object handle(Request request, Response response) throws IllegalArgumentException {
                request.queryParams("name");
                request.queryParams("address");
                request.queryParams("email");
                request.queryParams("phoneNumber");
                return implementContact.addContact("name","address","email","phoneNumber");
            }
        });


    }
}
