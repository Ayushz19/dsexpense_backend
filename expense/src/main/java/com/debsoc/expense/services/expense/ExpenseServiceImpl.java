package com.debsoc.expense.services.expense;


import com.debsoc.expense.dto.ExpenseDTO;
import com.debsoc.expense.entity.Expense;
import com.debsoc.expense.repository.ExpenseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements  ExpenseService {

    private  final ExpenseRepository expenseRepository;




    public Expense postExpense(ExpenseDTO expenseDTO){
        return saveOrUpdateExpense(new Expense(), expenseDTO);
    }

    private Expense saveOrUpdateExpense(Expense expense , ExpenseDTO expenseDTO){

        expense.setTitle(expenseDTO.getTitle());
        expense.setDate(expenseDTO.getDate());
        expense.setAmount(expenseDTO.getAmount());
        expense.setCategory(expenseDTO.getCategory());
        expense.setDescription(expenseDTO.getDescription());

        return expenseRepository.save(expense);
    }

public List<Expense> getAllExpenses(){
        return expenseRepository.findAll().stream()
                .sorted(Comparator.comparing(Expense::getDate).reversed())
                .collect(Collectors.toList());
}


public Expense getExpenseById(Long id){
    Optional<Expense> optionalExpense=expenseRepository.findById(id);
    if(optionalExpense.isPresent()){
        return  optionalExpense.get();
    }
    else{
        throw new EntityNotFoundException("Expense is not present with id"+ id);
    }
}

public Expense updateExpense(Long id , ExpenseDTO expenseDTO){
        Optional<Expense> optionalExpense=expenseRepository.findById(id);
        if(optionalExpense.isPresent()){
            return saveOrUpdateExpense(optionalExpense.get(), expenseDTO);
        }else{
            throw new EntityNotFoundException("expense not present" + id);
        }
}

public void deleteExpense(Long id){
        Optional<Expense> optionalExpense=expenseRepository.findById(id);
        if(optionalExpense.isPresent()){
            expenseRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("expense not present");
        }
}

public Expense updateExpenseStatus(Long id , String status){
        Expense expense=expenseRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Expense Not Found "));
        expense.setStatus(status);
        return expenseRepository.save(expense);
}


//    public Expense postExpense(ExpenseDTO expenseDTO, MultipartFile file) {
//        // Handle file storage logic here (e.g., save the file to a directory or cloud storage)
//
//        // You may want to check if the file is not null and process it accordingly
//        if (file != null && !file.isEmpty()) {
//            try {
//                // Example: Save the file locally (ensure you handle the path appropriately)
//                String filePath = "path/to/save/" + file.getOriginalFilename();
//                file.transferTo(new File(filePath));
//                // Store the file path in the ExpenseDTO or Expense entity if needed
//            } catch (IOException e) {
//                throw new RuntimeException("Failed to save file: " + e.getMessage());
//            }
//        }
//        return saveOrUpdateExpense(new Expense(), expenseDTO);
//    }


}
