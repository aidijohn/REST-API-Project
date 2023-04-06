package com.pezesha.myproject.controller;

import com.pezesha.myproject.entity.Account;
import com.pezesha.myproject.entity.Transfer;
import com.pezesha.myproject.entity.TransferRequest;
import com.pezesha.myproject.repository.AccountRepository;
import com.pezesha.myproject.repository.TransferRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/transfers")
public class TransferController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransferRepository transferRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<String> transferMoney(@RequestBody TransferRequest transferRequest) {

        // Checking if the transfer amount is greater than zero
        if (transferRequest.getTransferAmount() <= 0) {
            return ResponseEntity.badRequest().body("Transfer amount must be greater than zero.");
        }

        // Getting the source and receiving accounts from the repository
        Optional<Account> sourceAccountOptional = accountRepository.findById(transferRequest.getSourceAccountId());
        Optional<Account> receivingAccountOptional = accountRepository.findById(transferRequest.getReceivingAccountId());

        // Checking if the accounts exist in the database
        if (!sourceAccountOptional.isPresent() || !receivingAccountOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Get the source and receiving accounts
        Account sourceAccount = sourceAccountOptional.get();
        Account receivingAccount = receivingAccountOptional.get();

        // Check if the source account has enough funds
        if (sourceAccount.getAccountBalance() < transferRequest.getTransferAmount()) {
            return ResponseEntity.badRequest().body("Source account doesn't have enough funds.");
        }

        // Check if the transfer will result in a negative balance for the source account
        if (sourceAccount.getAccountBalance() - transferRequest.getTransferAmount() < 0) {
            return ResponseEntity.badRequest().body("Transfer will result in a negative balance for the source account.");
        }

        // Update the balances of the source and receiving accounts
        sourceAccount.setAccountBalance(sourceAccount.getAccountBalance() - transferRequest.getTransferAmount());
        receivingAccount.setAccountBalance(receivingAccount.getAccountBalance() + transferRequest.getTransferAmount());

        // Save the updated accounts to the database
        accountRepository.save(sourceAccount);
        accountRepository.save(receivingAccount);

        // Save the transfer details to the database
        Transfer transfer = new Transfer();
        transfer.setSourceAccountId(sourceAccount.getId());
        transfer.setReceivingAccountId(receivingAccount.getId());
        transfer.setTransferAmount(transferRequest.getTransferAmount());
        transferRepository.save(transfer);

        return ResponseEntity.ok("Transfer was successful!");
    }
}
