--Добавьте процедуру, которая будет удалять записи по id
CREATE OR REPLACE PROCEDURE delete_product_id(p_id integer)
LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM products
    WHERE id = p_id;
END;
$$;
--Добавьте функцию, которая будет удалять записи если количество товара равно 0

CREATE OR REPLACE FUNCTION delete_products_with_zero_count()
RETURNS integer
LANGUAGE plpgsql
AS $$
DECLARE
    deleted_count integer;
BEGIN
    DELETE FROM products
    WHERE count = 0;
    GET DIAGNOSTICS deleted_count = ROW_COUNT;
    RETURN deleted_count;
END;
$$;