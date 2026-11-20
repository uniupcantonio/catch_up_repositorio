package com.icecube.platform.u202416147.detection.interfaces.rest;

import com.icecube.platform.u202416147.detection.application.commandservices.NeutrinoAlertCommandService;
import com.icecube.platform.u202416147.detection.interfaces.rest.resources.CreateNeutrinoAlertResource;
import com.icecube.platform.u202416147.detection.interfaces.rest.resources.NeutrinoAlertResource;
import com.icecube.platform.u202416147.detection.interfaces.rest.transform.CreateNeutrinoAlertCommandFromResourceAssembler;
import com.icecube.platform.u202416147.detection.interfaces.rest.transform.NeutrinoAlertResourceFromDomainAssembler;
import com.icecube.platform.u202416147.shared.interfaces.rest.transform.ResponseEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/neutrino-alerts", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Neutrino Alerts", description = "Endpoints for neutrino alerts")
public class NeutrinoAlertsController {
    private final NeutrinoAlertCommandService neutrinoAlertCommandService;

    public NeutrinoAlertsController(NeutrinoAlertCommandService neutrinoAlertCommandService) {
        this.neutrinoAlertCommandService = neutrinoAlertCommandService;
    }

    @Operation(
            summary = "Create a neutrino alert",
            description = "Registers a neutrino alert with its detector, follow-up window, energy range and confidence score",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Neutrino alert creation request",
                    content = @Content(schema = @Schema(implementation = CreateNeutrinoAlertResource.class))))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Neutrino alert created",
                    content = @Content(schema = @Schema(implementation = NeutrinoAlertResource.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "409", description = "Conflict - a neutrino alert with the given alertCode already exists",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PostMapping
    public ResponseEntity<?> createNeutrinoAlert(
            @Valid @RequestBody CreateNeutrinoAlertResource resource,
            @Parameter(description = "Preferred response language (e.g. en, es)")
            @RequestHeader(value = "Accept-Language", required = false) String acceptLanguage) {
        log.debug("POST /api/v1/neutrino-alerts - alertCode={}, Accept-Language={}", resource.alertCode(), acceptLanguage);
        var command = CreateNeutrinoAlertCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = neutrinoAlertCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                NeutrinoAlertResourceFromDomainAssembler::toResourceFromDomain,
                HttpStatus.CREATED);
    }
}
