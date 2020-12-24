
insert into parkinglot(name, address, area, stdBikeSlots, eBikeSlots, twinStdBikeSlots, twinEBikeSlots)
values ('Vườn hoa Hướng Dương', 'Ha Noi', 1000,  30, 30, 30, 30);
insert into parkinglot(name, address, area, stdBikeSlots, eBikeSlots, twinStdBikeSlots, twinEBikeSlots)
values ('Hồ con cá', 'Ha Noi', 1200, 40, 30, 30, 30);
insert into parkinglot(name, address, area, stdBikeSlots, eBikeSlots, twinStdBikeSlots, twinEBikeSlots)
values ('Công viên Thống Nhất', 'Ha Noi', 1200, 30, 30, 40, 30);
insert into parkinglot(name, address, area, stdBikeSlots, eBikeSlots, twinStdBikeSlots, twinEBikeSlots)
values ('Viện bảo tàng', 'Ha Noi', 1200, 30, 40, 30, 30);
insert into parkinglot(name, address, area, stdBikeSlots, eBikeSlots, twinStdBikeSlots, twinEBikeSlots)
values ('Parking Lot 5', 'Ha Noi', 1200, 30, 30, 30, 40);
-- -----------------------------------
insert into bike(parkingLotId, type, upfrontPrice, rentPrice, lastUsed, maxTimeUsed,isAvailable)
values (1, 1, 100, 20, '2018-01-01', '6:00', true);
insert into bike(parkingLotId, type, upfrontPrice, rentPrice, lastUsed, maxTimeUsed,isAvailable)
values (2, 1, 100, 20, '2018-01-01', '6:00', true);
insert into bike(parkingLotId, type, upfrontPrice, rentPrice, lastUsed, maxTimeUsed,isAvailable)
values (2, 2, 200, 25, '2018-01-01', '6:00', true);
insert into bike(parkingLotId, type, upfrontPrice, rentPrice, lastUsed, maxTimeUsed,isAvailable)
values (2, 2, 200, 25, '2018-01-01', '6:00', true);
insert into bike(parkingLotId, type, upfrontPrice, rentPrice, lastUsed, maxTimeUsed,isAvailable)
values (1, 3, 300, 30, '2018-01-01', '6:00', true);
insert into bike(parkingLotId, type, upfrontPrice, rentPrice, lastUsed, maxTimeUsed,isAvailable)
values (3, 3, 300, 30, '2018-01-01', '6:00', true);
insert into bike(parkingLotId, type, upfrontPrice, rentPrice, lastUsed, maxTimeUsed,isAvailable)
values (3, 4, 400, 35, '2018-01-01', '6:00',true);
insert into bike(parkingLotId, type, upfrontPrice, rentPrice, lastUsed, maxTimeUsed,isAvailable)
values (2, 1, 100, 20, '2018-01-01', '6:00',true);
insert into bike(parkingLotId, type, upfrontPrice, rentPrice, lastUsed, maxTimeUsed,isAvailable)
values (1, 4, 400, 35, '2018-01-01', '6:00',true);
insert into bike(parkingLotId, type, upfrontPrice, rentPrice, lastUsed, maxTimeUsed,isAvailable)
values (3, 3, 300, 30, '2018-01-01', '6:00',true);
insert into bike(parkingLotId, type, upfrontPrice, rentPrice, lastUsed, maxTimeUsed,isAvailable)
values (3, 3, 150000, 30000, '2018-01-01', '6:00',true);
