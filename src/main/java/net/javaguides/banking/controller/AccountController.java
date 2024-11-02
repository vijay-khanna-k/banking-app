package net.javaguides.banking.controller;

import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //add account REST API
    @PostMapping
    public ResponseEntity <AccountDto> addAccount(@RequestBody AccountDto accountDto)
    {
        return  new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    //Get Account Rest API
    @GetMapping("/{id}")
    public  ResponseEntity<AccountDto> getAccountById(@PathVariable Long id)
    {
        AccountDto accountDto = accountService.getAccount(id);
        return ResponseEntity.ok(accountDto);
    }

    //Deposit Rest API
    @PutMapping("/{id}/deposit")
    public  ResponseEntity<AccountDto>deposit(@PathVariable Long id ,@RequestBody Map<String,Double> request)
    {
        double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(id,amount);
        return ResponseEntity.ok(accountDto);
    }

    // withdraw Rest API
    @PutMapping("/{id}/withdraw")
    public  ResponseEntity<AccountDto> withDraw(@PathVariable Long id,@RequestBody Map<String,Double> request)
    {
        double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id,amount);
        return  ResponseEntity.ok(accountDto);
    }

    // Get All Accounts Rest API
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts()
    {
        List<AccountDto> accounts  = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }
    //Delete Account Rest API
    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteAcoount(@PathVariable Long id)
    {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted Sucessfully!!!...");
    }
}
