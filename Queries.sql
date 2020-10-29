# 명함 테이블 생성
CREATE TABLE `carddb`.`businesscard` (
  `name` VARCHAR(20) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `company_name` VARCHAR(45) NULL,
  `create_date` DATE NULL)
ENGINE = MEMORY
DEFAULT CHARACTER SET = utf8;
