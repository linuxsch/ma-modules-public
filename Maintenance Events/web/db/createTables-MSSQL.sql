--
--    Copyright (C) 2006-2011 Serotonin Software Technologies Inc. All rights reserved.
--    @author Matthew Lohbihler
--
create table maintenanceEvents (
  id int not null identity,
  xid nvarchar(50) not null,
  dataSourceId int not null,
  alias nvarchar(255),
  alarmLevel int not null,
  scheduleType int not null,
  disabled char(1) not null,
  activeYear int,
  activeMonth int,
  activeDay int,
  activeHour int,
  activeMinute int,
  activeSecond int,
  activeCron nvarchar(25),
  inactiveYear int,
  inactiveMonth int,
  inactiveDay int,
  inactiveHour int,
  inactiveMinute int,
  inactiveSecond int,
  inactiveCron nvarchar(25),
  primary key (id)
);
alter table maintenanceEvents add constraint maintenanceEventsUn1 unique (xid);
alter table maintenanceEvents add constraint maintenanceEventsFk1 foreign key (dataSourceId) references dataSources(id) on delete cascade;
