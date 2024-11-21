package com.erneto13.sgfa_backend.repository;

import com.erneto13.sgfa_backend.model.DriverModel;

public interface IDriverRepository {
    DriverModel findByUserId(Integer userId);
}
