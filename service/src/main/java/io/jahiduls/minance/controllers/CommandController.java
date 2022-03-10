package io.jahiduls.minance.controllers;

import io.jahiduls.minance.commands.CreateTermDepositCommand;
import io.jahiduls.minance.commands.UpdateTermDepositAmountCommand;
import io.jahiduls.minance.commands.UpdateTermDepositDepositorCommand;
import io.jahiduls.minance.commands.UpdateTermDepositInterestRateCommand;
import io.jahiduls.minance.commands.UpdateTermDepositMaturityInstructionCommand;
import io.jahiduls.minance.commands.UpdateTermDepositPeriodCommand;
import io.jahiduls.minance.resources.TermDepositResource;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

        validate(resource);

        log.debug("Received resource: {}", resource);
        final UUID depositId = UUID.randomUUID();
        // The other commands depend on completion of the first one which creates the aggregate
        // Let's wait for its completion before firing off the remaining commands, else they might not find the aggregate
        commandGateway.sendAndWait(CreateTermDepositCommand.builder().id(depositId).user(resource.user).createdOn(resource.createdOn).build());
        commandGateway.send(UpdateTermDepositDepositorCommand.builder().id(depositId).depositor(resource.depositor).build());
        commandGateway.send(UpdateTermDepositAmountCommand.builder().id(depositId).amount(resource.amount).build());
        commandGateway.send(UpdateTermDepositPeriodCommand.builder().id(depositId).period(resource.period).build());
        commandGateway.send(UpdateTermDepositInterestRateCommand.builder().id(depositId).interestRate(resource.interestRate).build());
        commandGateway.send(UpdateTermDepositMaturityInstructionCommand.builder().id(depositId).maturity(resource.maturity).build());
    }

    @ExceptionHandler
    private void handleException(RuntimeException e) {

        log.error("{}", e);
        throw e;
    }

    private void validate(TermDepositResource resource) {
        Assert.notNull(resource.user, "user.name is required");
        Assert.notNull(resource.createdOn, "createdOn is required");
        Assert.isTrue(resource.createdOn.year > 0, "createdOn.year must be non-negative");
        Assert.isTrue(isBetween(0, resource.createdOn.month, 11), "createdOn.month must be between 0 and 11");
        Assert.isTrue(isBetween(1, resource.createdOn.day, 31), "createdOn.day must be between 0 and 11");
        Assert.notNull(resource.depositor, "depositor is required");
        Assert.notNull(resource.amount, "amount is required");
        Assert.isTrue(isBetween(0, resource.amount.fixed, Integer.MAX_VALUE), "amount.fixed must be non-negative");
        Assert.isTrue(isBetween(0, resource.amount.decimal, 99), "amount.decimal must be between 0 and 99");
        Assert.notNull(resource.period, "period is required");
        Assert.isTrue(resource.period.years > 0, "period.years must be non-negative");
        Assert.isTrue(isBetween(0, resource.period.months, 11), "period.months must be between 0 and 11");
        Assert.isTrue(isBetween(1, resource.period.days, 31), "period.days must be between 0 and 11");
        Assert.notNull(resource.interestRate, "interestRate is required");
        Assert.isTrue(isBetween(0, resource.interestRate.fixed, Integer.MAX_VALUE), "interestRate.fixed must be non-negative");
        Assert.isTrue(isBetween(0, resource.interestRate.decimal, 99), "interestRate.decimal must be between 0 and 99");
        Assert.notNull(resource.maturity, "maturity is required");
    }

    private boolean isBetween(int min, int val, int max) {
        return min <= val && val <= max;
    }

}
