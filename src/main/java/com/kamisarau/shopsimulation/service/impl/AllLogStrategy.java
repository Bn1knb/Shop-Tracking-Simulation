package com.kamisarau.shopsimulation.service.impl;

import com.google.common.primitives.Bytes;
import com.kamisarau.shopsimulation.service.LogServiceStrategy;

import java.util.Date;

public class AllLogStrategy implements LogServiceStrategy {

    @Override
    public byte[] getLogs(Date date) {
        return Bytes.concat(
                new MerchandiseLogServiceStrategy().getLogs(date),
                new ProductLogServiceStrategy().getLogs(date),
                new ShelfLogServiceStrategy().getLogs(date),
                new StorageLogServiceStrategy().getLogs(date),
                new RandomiserLogServiceStrategy().getLogs(date)
        );
    }
}
