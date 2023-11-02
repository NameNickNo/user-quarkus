package org.acme.exception.handler;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.acme.exception.UserNotFoundException;
import org.acme.util.ErrorResponse;

@Provider
public class UserNotFoundExceptionHandler implements ExceptionMapper<UserNotFoundException> {

    @Override
    public Response toResponse(UserNotFoundException exception) {
        return Response.status(404).entity(new ErrorResponse(Response.Status.NOT_FOUND, exception.getMessage())).build();
    }
}
