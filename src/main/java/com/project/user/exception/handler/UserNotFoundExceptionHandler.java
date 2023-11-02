package com.project.user.exception.handler;

import com.project.user.exception.UserNotFoundException;
import com.project.user.util.CustomResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UserNotFoundExceptionHandler implements ExceptionMapper<UserNotFoundException> {

    @Override
    public Response toResponse(UserNotFoundException exception) {
        return Response.status(404).entity(new CustomResponse(Response.Status.NOT_FOUND, exception.getMessage())).build();
    }
}
