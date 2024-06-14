package com.example.anirudh.cache.cacheLoader;

import com.example.anirudh.accessor.dao.EmployeeDAO;
import com.example.anirudh.model.Employee;
import com.google.common.cache.CacheLoader;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeCacheLoader extends CacheLoader<Integer, Employee> {

    private final EmployeeDAO employeeDAO;

    @Override
    public Employee load(Integer key) throws Exception {
        log.info("Cache miss, Fetching employee from DB for employeeId - {}", key);
        Optional<Employee> employeeOptional = employeeDAO.findById(key);
        if (employeeOptional.isPresent())
            return employeeOptional.get();
        log.info("!!Employee not present in DB!!");
        return new Employee();
    }

    @Override
    public ListenableFuture<Employee> reload(Integer key, Employee oldValue) throws Exception {
        ListenableFutureTask<Employee> task = ListenableFutureTask.create(() ->{
                    Optional<Employee> employeeOptional = employeeDAO.findById(key);
                    if (employeeOptional.isPresent())
                        return employeeOptional.get();
                    log.info("!!Employee not present in DB!!");
                    return oldValue;
                }
                );
        return task;
    }
}
