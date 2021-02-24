package io.jahiduls.minance.model;

import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TermDepositView {
    @Id
    private UUID id;
    private LocalDate createdOn;
    private InvestmentPeriod period;
    private Amount amount;
    private MaturityInstruction maturityInstruction;
}
