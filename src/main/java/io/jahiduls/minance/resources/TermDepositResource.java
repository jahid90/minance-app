package io.jahiduls.minance.resources;

import io.jahiduls.minance.models.AmountImpl;
import io.jahiduls.minance.models.Date;
import io.jahiduls.minance.models.InterestRate;
import io.jahiduls.minance.models.InvestmentPeriod;
import io.jahiduls.minance.models.MaturityInstruction;
import io.jahiduls.minance.models.User;
import lombok.ToString;

/**
 * JSON structure
 *
 * <pre>
 * {
 *     period: {
 *         years: number,
 *         months: number,
 *         days: number,
 *     },
 *     amount: {
 *         rupee: number,
 *         paise: number,
 *         maturityInstruction: PAYOUT_PRINCIPLE_AND_INTEREST | REINVEST_PRINCIPLE_AND_INTEREST | REINVEST_PRINCIPLE_AND_PAYOUT_INTEREST,
 *     }
 * }
 * </pre>
 */
@ToString
public class TermDepositResource {
    public User user;
    public Date createdOn;
    public String depositor;
    public AmountImpl amount;
    public InvestmentPeriod period;
    public InterestRate interestRate;
    public MaturityInstruction maturity;
}
