package io.jahiduls.minance.controllers;

import io.jahiduls.minance.commands.CreateTermDepositCommand;
import io.jahiduls.minance.commands.UpdateTermDepositAmountCommand;
import io.jahiduls.minance.commands.UpdateTermDepositMaturityInstructionCommand;
import io.jahiduls.minance.commands.UpdateTermDepositPeriodCommand;
import io.jahiduls.minance.resources.TermDepositResource;
import java.time.LocalDate;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CommandController {

    private final CommandGateway commandGateway;

    @PostMapping("/deposits/term")
    private void addTermDeposit(@RequestBody TermDepositResource resource) {

        log.debug("Received resource: {}", resource);
        final UUID depositId = UUID.randomUUID();
        final LocalDate create = LocalDate.now();
        // The first command creates the aggregate; others execute first sometimes and fail to find the aggregate; but why?
        // shouldn't command bus handle the ordering?
        commandGateway.send(CreateTermDepositCommand.builder().id(depositId).createdOn(create).build());
        commandGateway.send(UpdateTermDepositAmountCommand.builder().id(depositId).amount(resource.amount).build());
        commandGateway.send(UpdateTermDepositPeriodCommand.builder().id(depositId).period(resource.period).build());
        commandGateway.send(UpdateTermDepositMaturityInstructionCommand.builder()
                .id(depositId).maturityInstruction(resource.maturity).build());
    }

}
