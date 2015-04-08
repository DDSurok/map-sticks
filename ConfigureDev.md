# Настройки для разработки #

## Состав используемого ПО ##

  1. Apache Tomcat 7.0.30
  1. JDK 1.7.0\_09-b30
  1. PostgreSQL 9.1

## Конфигурация используемого ПО ##

**Tomcat**
| **Parameter** | **Value** |
|:--------------|:----------|
| Local port | 8080 |
| White port | 12080 |
| Address admin panel | http://10.0.2.2:8080/manager |

**PostrgeSQL**
| **Parameter** | **Value** |
|:--------------|:----------|
| Local port | 5432 |
| White port | 12432 |

### Конфигурация виртуальной машины ###

**OS**
| **Parameter** | **Value** |
|:--------------|:----------|
| IP | 10.0.2.2/24 |
| user | maps |
| password | maps |
| SSH local port | 22 |
| SSH white port | 12022 |


### Конфигурация проброса портов ###

> -> 10.10.0.35:12080 -> 10.0.2.2:8080

vlg.netvoxlan.ru:14022 -> 10.10.0.35:12022 -> 10.0.2.2:22

vlg.netvoxlab.ru:14432 -> 10.10.0.35:12432 -> 10.0.2.2:5432