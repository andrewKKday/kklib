DROP TABLE IF EXISTS public.library_user;
DROP TABLE IF EXISTS public.library_book;
CREATE  TABLE  public.library_user (
        user_oid SERIAL PRIMARY KEY NOT NULL,   -- SERIAL == conti int
        create_user varchar(32) NULL,   -- always need
        modify_user varchar(32) NULL,   -- always need
        create_date timestamp NULL, -- always need
        modify_date timestamp NULL, -- always need
        user_pwd varchar(32) NULL
);

CREATE  TABLE  public.library_book (
        book_oid SERIAL PRIMARY KEY NOT NULL,
        create_user varchar(32) NULL,   -- always need
        modify_user varchar(32) NULL,   -- always need
        create_date timestamp NULL, -- always need
        modify_date timestamp NULL, -- always need
        book_category varchar(32) NOT NULL,
        book_title varchar(100) NOT NULL,
        book_author varchar(32) NOT NULL,
        book_currency varchar(10) NOT NULL,
        book_price decimal(10, 2) NOT NULL,
        book_cost decimal(10, 2) NOT NULL
);

