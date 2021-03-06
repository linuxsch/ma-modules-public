/**
 * Copyright (C) 2018 Infinite Automation Systems, Inc. All rights reserved
 * 
 */
package com.serotonin.m2m2.scheduledEvents.upgrade;

import java.util.HashMap;
import java.util.Map;

import com.serotonin.m2m2.db.DatabaseProxy;
import com.serotonin.m2m2.db.upgrade.DBUpgrade;

/**
 *
 * @author Phillip Dunlap
 */
public class Upgrade1 extends DBUpgrade {

    @Override
    protected void upgrade() throws Exception {
        Map<String, String[]> scripts = new HashMap<>();
        scripts.put(DatabaseProxy.DatabaseType.MYSQL.name(), mysql);
        scripts.put(DatabaseProxy.DatabaseType.H2.name(), h2);
        scripts.put(DatabaseProxy.DatabaseType.MSSQL.name(), mssql);
        scripts.put(DatabaseProxy.DatabaseType.POSTGRES.name(), postgres);
        runScript(scripts);
    }

    @Override
    protected String getNewSchemaVersion() {
        return "2";
    }

    private final String[] mysql = new String[] {
            "ALTER TABLE scheduledEvents MODIFY COLUMN xid VARCHAR(100) NOT NULL;"
    };
    private final String[] h2 = new String[] {
            "ALTER TABLE scheduledEvents ALTER COLUMN xid VARCHAR(100) NOT NULL;"
    };
    private final String[] mssql = new String[] {
            "ALTER TABLE scheduledEvents ALTER COLUMN xid NVARCHAR(100) NOT NULL;"
    };
    private final String[] postgres = new String[] {
            "ALTER TABLE scheduledEvents ALTER COLUMN xid TYPE VARCHAR(100) NOT NULL;"
    };
}
