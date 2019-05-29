create table PRODUCT_CODE (
    `CODE` varchar(255) unique not null,
    DISCOUNT_CODE char(1) unique not null,
    `DESCRIPTION` varchar(255),
    primary key (`CODE`, DISCOUNT_CODE)
);