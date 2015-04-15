/**
 * Copyright (C) 2015 Infinite Automation Software. All rights reserved.
 * @author Terry Packer
 */
package com.serotonin.m2m2.virtual.vo.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.serotonin.m2m2.DataTypes;
import com.serotonin.m2m2.virtual.vo.ChangeTypeVO;
import com.serotonin.m2m2.virtual.vo.VirtualPointLocatorVO;
import com.serotonin.m2m2.web.mvc.rest.v1.csv.CSVColumnGetter;
import com.serotonin.m2m2.web.mvc.rest.v1.csv.CSVColumnSetter;
import com.serotonin.m2m2.web.mvc.rest.v1.csv.CSVEntity;
import com.serotonin.m2m2.web.mvc.rest.v1.model.dataPoint.PointLocatorModel;

/**
 * TODO this class should be removed and we should create
 * models for all the different types of VOs in the Point Locator.
 * 
 * Then the definitions can be matched on type such as:
 * 
 * PL.VIRTUAL.AlternateBooleanChange
 * PL.VIRTUAL.AnalogAttractorChange
 * PL.VIRTUAL.BrownianChange 
 * etc.
 * 
 * This will greatly simplify the Models and be much clearer on what the point is
 * 
 * 
 * @author Terry Packer
 *
 */
@CSVEntity()
abstract public class VirtualPointLocatorModel extends PointLocatorModel<VirtualPointLocatorVO>{

	/**
	 * @param data
	 */
	public VirtualPointLocatorModel(VirtualPointLocatorVO data) {
		super(data);
	}

	public VirtualPointLocatorModel() {
		super(new VirtualPointLocatorVO());
	}

	@JsonGetter("dataType")
	@CSVColumnGetter(order=10, header="dataType")
	public String getDataType() {
	    return DataTypes.CODES.getCode(this.data.getDataTypeId());
	}

	@JsonSetter("dataType")
	@CSVColumnSetter(order=10, header="dataType")
	public void setDataType(String dataType) {
	    this.data.setDataTypeId(DataTypes.CODES.getId(dataType));
	}

	@JsonGetter("changeType")
	@CSVColumnGetter(order=11, header="changeType")
	public String getChangeType() {
	    return ChangeTypeVO.CHANGE_TYPE_CODES.getCode(this.data.getChangeTypeId());
	}

	@JsonSetter("changeType")
	@CSVColumnSetter(order=11, header="changeType")
	public void setChangeType(String changeType) {
	    this.data.setChangeTypeId(ChangeTypeVO.CHANGE_TYPE_CODES.getId(changeType));
	}

	@JsonGetter("settable")
	@CSVColumnGetter(order=12, header="settable")
	public boolean isSettable() {
	    return this.data.isSettable();
	}

	@JsonSetter("settable")
	@CSVColumnSetter(order=12, header="settable")
	public void setSettable(boolean settable) {
	    this.data.setSettable(settable);
	}


	
}
