package com.kamisarau.shopsimulation.util;

import com.kamisarau.shopsimulation.service.LogServiceStrategy;
import com.kamisarau.shopsimulation.service.impl.*;

public class LogStrategyChooser {

    private LogStrategyChooser() {
        throw new IllegalStateException("Utility class has been instantiated");
    }

    public static LogServiceStrategy choose(String type) {

        switch (type) {
            case "product":
                return new ProductLogServiceStrategy();
            case "shelf":
                return new ShelfLogServiceStrategy();
            case "storage":
                return new StorageLogServiceStrategy();
            case "merchandise":
                return new MerchandiseLogServiceStrategy();
            case "randomiser":
                return new RandomiserLogServiceStrategy();
            default:
                return new AllLogStrategy();
        }
    }
}
