/*
    Copyright (C) 2014 Infinite Automation Systems Inc. All rights reserved.
    @author Matthew Lohbihler
 */
package com.serotonin.m2m2.pointLinks;

import java.util.List;

import com.serotonin.db.spring.ExtendedJdbcTemplate;
import com.serotonin.m2m2.Common;
import com.serotonin.m2m2.module.DatabaseSchemaDefinition;

public class PointLinkSchemaDefinition extends DatabaseSchemaDefinition {
    
    public static final String TABLE_NAME = "pointLinks";
    
    @Override
    public void newInstallationCheck(ExtendedJdbcTemplate ejt) {
        if (!Common.databaseProxy.tableExists(ejt, "pointLinks")) {
            String path = Common.MA_HOME + getModule().getDirectoryPath() + "/web/db/createTables-"
                    + Common.databaseProxy.getType().name() + ".sql";
            Common.databaseProxy.runScriptFile(path, null);
        }
    }

    @Override
    public void addConversionTableNames(List<String> tableNames) {
        tableNames.add(TABLE_NAME);
    }

    @Override
    public String getUpgradePackage() {
        return "com.serotonin.m2m2.pointLinks.upgrade";
    }

    @Override
    public int getDatabaseSchemaVersion() {
        return 6;
    }

    @Override
    public void postRuntimeManagerTerminate(boolean uninstall) {
        if(uninstall) {
            String path = Common.MA_HOME + getModule().getDirectoryPath() + "/web/db/uninstall.sql";
            Common.databaseProxy.runScriptFile(path, null);
        }
    }
}
