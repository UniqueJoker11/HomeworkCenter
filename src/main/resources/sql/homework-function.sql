CREATE FUNCTION `getRoleList`(rootId varchar(50))
     RETURNS varchar(1000)
     BEGIN
       DECLARE sTemp VARCHAR(1000);
       DECLARE sTempChd VARCHAR(1000);
   
       SET sTemp = '';
       SET sTempChd =rootId;
   
       WHILE sTempChd is not null DO
         SET sTemp = concat(sTempChd,',',sTemp);
        SELECT group_concat(role_id) INTO sTempChd FROM homework_role where FIND_IN_SET(parent_role_id,sTempChd)>0;
      END WHILE;
      RETURN sTemp;
     END;
select getChildLst("1e310db0-6331-4457-8868-ca6a6b21ae83") as roleIdList;

CREATE FUNCTION `getAuthorityList`(rootId varchar(50))
     RETURNS varchar(1000)
     BEGIN
       DECLARE sTemp VARCHAR(1000);
       DECLARE sTempChd VARCHAR(1000);
   
       SET sTemp = '';
       SET sTempChd =rootId;
   
       WHILE sTempChd is not null DO
         SET sTemp = concat(sTempChd,',',sTemp);
        SELECT group_concat(authority_id) INTO sTempChd FROM homework_authority where FIND_IN_SET(parent_authority_id,sTempChd)>0;
      END WHILE;
      RETURN sTemp;
     END;


select getAuthorityList('b1b91332-e099-4669-8836-360428c26888');

CREATE FUNCTION `getMenuList`(rootId varchar(50))
     RETURNS varchar(1000)
     BEGIN
       DECLARE sTemp VARCHAR(1000);
       DECLARE sTempChd VARCHAR(1000);
   
       SET sTemp = '';
       SET sTempChd =rootId;
   
       WHILE sTempChd is not null DO
         SET sTemp = concat(sTempChd,',',sTemp);
        SELECT group_concat(menu_id) INTO sTempChd FROM homework_menu where FIND_IN_SET(menu_parent_id,sTempChd)>0;
      END WHILE;
      RETURN sTemp;
     END;
select getMenuList("a4c78339-cf93-4642-be1b-57b5dafb2ab6");