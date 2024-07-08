```bash
podman machine init --cpus 2 --memory 2048 --disk-size 50  -v /Users/cch/Desktop/github:/shared/projects --now selflab  
podman system connection  default selflab
```

## Target

|能力|	範例技術|
|---|---|
|📝 撰寫 RESTFul API 文件|	springdoc-openapi|
|✍️ 設計 RESTFul API 端點|	Spring Web MVC|
|✍️ AOP/Interceptor 概念 | Spring Web MVC|
|🧬 連接 Relational DB 資料庫|	Spring Data JPA, liquebase|
|🕹️ 操作 SQL 語法	|[Spring Data JPA](https://docs.spring.io/spring-data/jpa/reference/)|
|📖 查詢資料分頁	|Spring Data|
|🧰 整合測試|	Spring Framework|
|🧱 部署系統|	jib, Docker Compose|
|📃 輸出系統日誌	|Loki in Grafana Stack|
|👁️ 觀測系統狀態	|Spring Actuator, Grafana Stack, Graceful shutdown|
|♻️ 持續集成/佈署	|Gitlab Action|


## TODO

🔑 管理 RESTFul API 權限 Spring Security

⚡️ 為 API Endpoint 加 Cache	Spring Cache

💫 正確使用 Transaction	Spring Data JPA

🔒 正確使用 Lock Spring Data JPA


🔗 連接 NoSQL 資料庫	Spring Data

🗣️ 調用 RESTFul API Client 呼叫其他服務 Spring Framework

⏱️ 排程定期執行任務	Spring Framework

## API docs

- @Tag 用於提供有關 Swagger 文件中標籤的附加資訊
- @Operation 用於為單一 API 操作提供元資料
- @ApiResponses 用於至 API 操作方法中，以提供該操作的可能回應
- @Parameter 註解用於描述 API 操作的參數
- @Schema 用於提供有關 API 中物件或參數的架構的附加資訊
## Database
Postgresql 基本操作
```bash
# \l
# \c TABLE
tutorial=# \dt dev.*
SET schema 'dev';
```

## Spring Data JPA

### Page
建立物件：

```java
Pageable paging = PageRequest.of(page, size);
```

- page: zero-based page index, must NOT be negative.
- size: number of items in a page to be returned, must be greater than 0.

- `getContent()` to retrieve the List of items in the page.
- `getNumber()` for current Page.
- `getTotalElements()` for total items stored in database.
- `getTotalPages()` for number of total pages.

### Sort

建立 Sort 物件：
```java
Pageable paging = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, titleÍ));
```

### Derived Query methods

主要有兩個元素，動作 (subject), 條件 (conditions)
- Subject
    - find…By, exists…By, count…By
    - Distinct or Top/First
- Predicate
    - And/Or
    - StartingWith, EndingWith, Containing, IgnoreCase ...

範例：

```java
findByTitle()
findTop3ByTitleContainingAndPublished()
```

### Custom query

範例：

```java
@Query("SELECT t FROM Tutorial t WHERE t.createdAt >= ?1")
Page<Tutorial> findByDateGreaterThanEqual(Date date, Pageable pageable);
```

## Exception Handler



## API 

|Methods	|Urls|	Actions|
|---|---|---|
|POST	|/api/tutorials	|create new Tutorial|
|GET	|/api/tutorials	|retrieve all Tutorials|
|GET	|/api/tutorials/:id	|retrieve a Tutorial by :id|
|PUT	|/api/tutorials/:id	|update a Tutorial by :id|
|DELETE	|/api/tutorials/:id	|delete a Tutorial by :id|
|DELETE	|/api/tutorials	|delete all Tutorials|
|GET	|/api/tutorials/published|	find all published Tutorials|
|GET	|/api/tutorials?title=[keyword]|	find all Tutorials which title contains keyword|

```bash

curl -XPOST -H "Content-type: application/json" http://localhost:8080/api/tutorials -d '{"title": "test", "description": "itachi", "published": true, "level": 1}'
{"id":"5a453b00-d0f6-48cd-80d4-6ce25d213d10","title":"test","description":"itachi","published":true,"level":1}
```

```bash
curl -XGET -H "Content-type: application/json" http://localhost:8080/api/tutorials
{"tutorialResponseDto":[{"id":"117825cc-7590-4531-befc-628c425341a9","title":"test","description":"itachi","published":true,"level":0,"createdAt":null},{"id":"9cf540a3-b0d3-40c5-9140-612350a5f710","title":"test","description":"itachi","published":true,"level":2,"createdAt":null},{"id":"a9ca44ce-21b0-475e-a317-8ddf493d788e","title":"test","description":"itachi","published":true,"level":0,"createdAt":null}],"currentPage":0,"totalItems":10,"totalPages":4}
```

```bash
curl -XGET -H "Content-type: application/json" "http://localhost:8080/api/tutorials?title=madara4&page=0&size=3"
{"tutorialResponseDto":[{"id":"80916216-6924-4348-888a-caa54077f033","title":"madara4","description":"madara4","published":true,"level":4,"createdAt":null}],"currentPage":0,"totalItems":1,"totalPages":1}%        
```

[baeldung | JAVA | Jackson](https://www.baeldung.com/jackson-annotations)

## logging

## Metrics 與 health check

## Graceful shutdown

## Jib

```bash
 gradle jib \
    -Djib.to.image=registry.hub.docker.com/cch0124/myimage:latest \
    -Djib.to.auth.username=${USERNAME} \
    -Djib.to.auth.password=${PASSWORD}
```

## IoC/ AOP / Interceptor
**IOC**

IoC （Inversion of Control ）即控制反轉/反轉控制。

傳統開發方式 ：往往是在類別 A 中手動透過 new 關鍵字來 new 一個 B 的物件出來 
IoC 開發方式 ：不透過 new 關鍵字來建立對象，而是透過 IoC 容器(Spring 框架) 來幫助我們實例化物件。需要哪個對象，直接從 IoC 容器裡面去拿即可。

**AOP**


**Interceptor**
```
                              _____Interceptor______
                             |                      |
         ----request---------|----->PreHandle()-----|-------------->
        /                    |                      |               \
       /                     |                      |                \
client                       |     AfterHandle()    |                 server
       \                     |                      |                /
        \                    |                      |               /
         <----Response-------|-----PostHandle()<----|---------------
                             |                      |
                             |______________________|
                
```

## Testing

[spring | testing](https://spring.academy/guides/spring-spring-boot-testing)

### TestContainer

if podman

```zsh
export DOCKER_HOST=unix://$(podman machine inspect --format '{{.ConnectionInfo.PodmanSocket.Path}}')
export TESTCONTAINERS_DOCKER_SOCKET_OVERRIDE=/var/run/docker.sock 
export TESTCONTAINERS_RYUK_DISABLED="true"
```


[testcontainers | JAVA | podman](https://java.testcontainers.org/supported_docker_environment/)