-- This script was generated by the ERD tool in pgAdmin 4.
-- Please log an issue at https://github.com/pgadmin-org/pgadmin4/issues/new/choose if you find any bugs, including reproduction steps.
BEGIN;


CREATE TABLE IF NOT EXISTS pricecomp.alert
(
    id serial NOT NULL,
    product_id character varying COLLATE pg_catalog."default" NOT NULL,
    store_id integer,
    discount integer,
    price double precision,
    alert_id character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT alert_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS pricecomp.discount
(
    id serial NOT NULL,
    product_id character varying COLLATE pg_catalog."default" NOT NULL,
    store_id integer NOT NULL,
    from_date date NOT NULL,
    to_date date NOT NULL,
    percentage_discount integer NOT NULL,
    CONSTRAINT discount_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS pricecomp.product
(
    id serial NOT NULL,
    product_id character varying COLLATE pg_catalog."default" NOT NULL,
    product_name character varying COLLATE pg_catalog."default" NOT NULL,
    product_category character varying COLLATE pg_catalog."default" NOT NULL,
    brand character varying COLLATE pg_catalog."default" NOT NULL,
    package_quantity double precision NOT NULL,
    package_unit character varying COLLATE pg_catalog."default" NOT NULL,
    price double precision NOT NULL,
    currency character varying COLLATE pg_catalog."default" NOT NULL,
    store integer NOT NULL,
    price_date date NOT NULL,
    CONSTRAINT product_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS pricecomp.store
(
    id serial NOT NULL,
    store_name character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT store_pkey PRIMARY KEY (id),
    CONSTRAINT unique_name_constraint UNIQUE (store_name)
);

ALTER TABLE IF EXISTS pricecomp.alert
    ADD CONSTRAINT store_fk FOREIGN KEY (store_id)
    REFERENCES pricecomp.store (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS pricecomp.discount
    ADD CONSTRAINT store_fk FOREIGN KEY (store_id)
    REFERENCES pricecomp.store (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS pricecomp.product
    ADD CONSTRAINT store_fk FOREIGN KEY (store)
    REFERENCES pricecomp.store (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

END;