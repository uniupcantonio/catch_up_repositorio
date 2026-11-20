package com.icecube.platform.u202416147.detection.application.internal.commandservices;

import com.icecube.platform.u202416147.detection.application.commandservices.NeutrinoAlertCommandService;
import com.icecube.platform.u202416147.detection.domain.model.aggregates.NeutrinoAlert;
import com.icecube.platform.u202416147.detection.domain.model.commands.CreateNeutrinoAlertCommand;
import com.icecube.platform.u202416147.detection.domain.repositories.NeutrinoAlertRepository;
import com.icecube.platform.u202416147.shared.application.result.ApplicationError;
import com.icecube.platform.u202416147.shared.application.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import static com.icecube.platform.u202416147.detection.infrastructure.persistence.jpa.entities.NeutrinoAlertPersistenceEntity.ALERT_CODE_UNIQUE_CONSTRAINT;

@Slf4j
@Service
public class NeutrinoAlertCommandServiceImpl implements NeutrinoAlertCommandService {
    private static final String NEUTRINO_ALERT_RESOURCE = "neutrino_alert";

    private final NeutrinoAlertRepository neutrinoAlertRepository;

    public NeutrinoAlertCommandServiceImpl(NeutrinoAlertRepository neutrinoAlertRepository) {
        this.neutrinoAlertRepository = neutrinoAlertRepository;
    }

    @Override
    public Result<NeutrinoAlert, ApplicationError> handle(CreateNeutrinoAlertCommand command) {
        if (neutrinoAlertRepository.existsByAlertCode(command.alertCode())) {
            log.warn("Duplicate neutrino alert detected (pre-check): alertCode={}", command.alertCode().code());
            return duplicateAlertCodeResult(command);
        }
        var neutrinoAlert = new NeutrinoAlert(command);
        try {
            var createdNeutrinoAlert = neutrinoAlertRepository.save(neutrinoAlert);
            log.info("Neutrino alert created: alertCode={}, id={}", command.alertCode().code(), createdNeutrinoAlert.getId());
            return Result.success(createdNeutrinoAlert);
        } catch (DataIntegrityViolationException exception) {
            if (isDuplicateAlertCodeViolation(exception)) {
                log.warn("Duplicate neutrino alert detected (constraint violation): alertCode={}", command.alertCode().code());
                return duplicateAlertCodeResult(command);
            }
            throw exception;
        }
    }

    private Result<NeutrinoAlert, ApplicationError> duplicateAlertCodeResult(CreateNeutrinoAlertCommand command) {
        return Result.failure(ApplicationError.conflict(NEUTRINO_ALERT_RESOURCE, command.alertCode().code()));
    }

    private boolean isDuplicateAlertCodeViolation(DataIntegrityViolationException exception) {
        Throwable violationCause = exception;
        while (violationCause != null) {
            String message = violationCause.getMessage();
            if (message != null && message.contains(ALERT_CODE_UNIQUE_CONSTRAINT)) return true;
            violationCause = violationCause.getCause();
        }
        return false;
    }
}
