package com.axionlabs.accessa.controller;

import com.axionlabs.accessa.dto.ErrorResponseDto;
import com.axionlabs.accessa.dto.user.BaseResponseDto;
import com.axionlabs.accessa.dto.user.UserDto;
import com.axionlabs.accessa.dto.user.response.UserProfileResponseDto;
import com.axionlabs.accessa.dto.user.response.UserResponseDto;
import com.axionlabs.accessa.service.impl.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(
        name = "User APIs - Accessa",
        description = "Handles user profile fetch, update, and delete for the Accessa application."
)
@RequestMapping("/api/v1/users/")
public class UserController {
    private final IUserService iUserService;

    @Autowired
    public UserController(IUserService iUserService){
        this.iUserService = iUserService;
    }
    @GetMapping("me")
    @Operation(
            summary = "Get User Profile",
            description = "Fetches the currently authenticated user's profile using the JWT Bearer token.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User profile fetched successfully.",
                    content = @Content(schema = @Schema(implementation = UserProfileResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized. User is not authenticated.",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden. User does not have access to this resource.",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error.",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    public ResponseEntity<UserProfileResponseDto> getUserProfile(){
        UserDto userData = iUserService.fetchUserDetails();
        return ResponseEntity.status(
                HttpStatus.OK
        ).body(
                new UserProfileResponseDto(
                        HttpStatus.OK,
                        "User Profile fetched successfully",
                        userData

                )
        );
    }
    @DeleteMapping("me")
    @Operation(
            summary = "Delete User Profile",
            description = "Deletes the currently authenticated user's profile using the JWT Bearer token.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "User profile deleted successfully.",
                    content = @Content(schema = @Schema(implementation = BaseResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized. User is not authenticated.",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "User profile deleted unsuccessfully",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error.",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    public ResponseEntity<BaseResponseDto> deleteUserProfile(){
        boolean isDeleted = iUserService.deleteUser();
        if(isDeleted){
            return ResponseEntity.status(
                    HttpStatus.NO_CONTENT
            ).body(
                    new BaseResponseDto(
                            HttpStatus.NO_CONTENT,
                            "User profile Deleted Successfully"
                    )
            );
        }
        return ResponseEntity.status(
                HttpStatus.BAD_REQUEST
        ).body(
                new BaseResponseDto(
                        HttpStatus.NO_CONTENT,
                        "User profile Deleted Unsuccessfully"
                )
        );
    }
}
