package com.project.user.controller;

import com.project.user.dto.UserCreateDTO;
import com.project.user.dto.UserDTO;
import com.project.user.exception.UserNotFoundException;
import com.project.user.model.User;
import com.project.user.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.project.user.util.UserValidator;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.List;
import java.util.stream.Collectors;

@Path("/user")
public class UserController {

    @ConfigProperty(name = "user.error.msg.usernotfound")
    String userNotFound;

    @ConfigProperty(name = "user.msg.deleted")
    String userDeleted;

    @Inject
    UserService userService;

    @Inject
    UserValidator userValidator;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(UserCreateDTO userDTO) {
        User user = new User(userDTO.getName(), userDTO.getEmail(), userDTO.getPassword());

        userValidator.validate(user);
        userService.save(user);

        return Response.ok().entity(userDTO).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response findById(@PathParam("id") long id) {
        User user = userService.findById(id).orElseThrow(() -> new UserNotFoundException(String.format(userNotFound, id)));
        UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword());

        return Response.ok().entity(userDTO).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/update")
    public Response updateUser(UserDTO userDTO) {
        User user = new User(userDTO.getName(), userDTO.getEmail(), userDTO.getPassword());

        userValidator.validate(user);
        userService.update(userDTO.getId(), user);

        return Response.ok().entity(userDTO).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/delete/{id}")
    public Response deleteUser(@PathParam("id") long id) {
        userService.remove(id);
        return Response.ok().entity(String.format(userDeleted, id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<UserDTO> users = userService.findAll().stream()
                .map(user -> new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword()))
                .collect(Collectors.toList());
        return Response.ok().entity(users).build();
    }
}
