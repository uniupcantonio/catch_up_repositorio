package com.icecube.platform.u202416147.detection.infrastructure.persistence.jpa.adapters;

import com.icecube.platform.u202416147.detection.domain.model.aggregates.NeutrinoAlert;
import com.icecube.platform.u202416147.detection.domain.model.valueobjects.AlertCode;
import com.icecube.platform.u202416147.detection.domain.repositories.NeutrinoAlertRepository;
import com.icecube.platform.u202416147.detection.infrastructure.persistence.jpa.assemblers.NeutrinoAlertPersistenceEntityAssembler;
import com.icecube.platform.u202416147.detection.infrastructure.persistence.jpa.repositories.NeutrinoAlertPersistenceRepository;
import com.icecube.platform.u202416147.shared.infrastructure.events.DomainEventPublisher;
import org.springframework.stereotype.Repository;

@Repository
public class NeutrinoAlertRepositoryAdapter implements NeutrinoAlertRepository {
    private final NeutrinoAlertPersistenceRepository neutrinoAlertPersistenceRepository;
    private final DomainEventPublisher domainEventPublisher;

    public NeutrinoAlertRepositoryAdapter(NeutrinoAlertPersistenceRepository neutrinoAlertPersistenceRepository,
                                          DomainEventPublisher domainEventPublisher) {
        this.neutrinoAlertPersistenceRepository = neutrinoAlertPersistenceRepository;
        this.domainEventPublisher = domainEventPublisher;
    }

    @Override
    public boolean existsByAlertCode(AlertCode alertCode) {
        return neutrinoAlertPersistenceRepository.existsByAlertCode(alertCode.code());
    }

    @Override
    public NeutrinoAlert save(NeutrinoAlert neutrinoAlert) {
        var persistenceEntity = NeutrinoAlertPersistenceEntityAssembler.toPersistenceEntityFromDomain(neutrinoAlert);
        var savedPersistenceEntity = neutrinoAlertPersistenceRepository.save(persistenceEntity);
        domainEventPublisher.publishAndClearEvents(neutrinoAlert);
        return NeutrinoAlertPersistenceEntityAssembler.toDomainFromPersistenceEntity(savedPersistenceEntity);
    }
}
