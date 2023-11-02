package com.project.user.exception.handler;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import com.project.user.exception.UserBadRequestException;
import com.project.user.util.ErrorResponse;

@Provider
public class UserBadRequestExceptionHandler implements ExceptionMapper<UserBadRequestException> {

    @Override
    public Response toResponse(UserBadRequestException exception) {
        return Response.status(400).entity(new ErrorResponse(Response.Status.BAD_REQUEST, exception.getMessage())).build();
    }
}
