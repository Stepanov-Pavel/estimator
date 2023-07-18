INSERT INTO customer (id,deleted,deletion_date,email,name,patronymic,surname,telephone)
    VALUES (1,false,null,'e-mail@yandex.ru','Заказчик','Заказчикович','Заказчиков','+7(000)000-00-00');

INSERT INTO building_object (id,addr,deleted,deletion_date,note,type)
    VALUES (1,'Город, Улица, Дом, Подъезд, Этаж, Офис',false,null,'Заметка','Тип');

INSERT INTO organization (id,addr,deleted,deletion_date,email,name,telephone,website)
    VALUES (1,'Город, Улица, Дом, Подъезд, Этаж, Офис',false,null,'e-mail@yandex.ru','Организация','+7(000)000-00-00','www.website.ru');

INSERT INTO person (id,deleted,deletion_date,email,last_login,name,password,patronymic,surname,telephone,username)
    VALUES (1,false,null,'e-mail@yandex.ru','2023-07-15','Пользователь','Пароль','Пользакович','Пользаков','+7(000)000-00-00','PolzovatelPP');

INSERT INTO estimate (id,person_id,organization_id,building_object_id,customer_id,name,number,date,comment,total,total_with_discount,
                        is_draft,deleted,deletion_date)
    VALUES (1,1,1,1,1,'Название','0000_ПП','2023-07-15','Коментарий',100000,10000,false,false,null);

INSERT INTO nomenclature (id,deleted,deletion_date,material,measurement_unit,name,product,sizes,type)
    VALUES (1,false,null,'Материал','Единица Измерения','Название','Изделие','0000x0000x0000','MATERIAL');