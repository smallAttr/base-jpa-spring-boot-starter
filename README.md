[TOC]

## 枚举转换工具使用（EnumType）

- 自定义的枚举类需要实现`BaseEnum`
```
public enum Identity implements BaseEnum {

    DSM(1, "DSM"),
    RSM(2, "RSM"),
    MARKET(3, "市场"),
    ;
    private int value;
    private String description;

    Identity(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
```
- 在实体中加上注解`@Type`
```
@Type(type = "com.apricotforest.common.convert.EnumType")
@Column(name = "_identity", columnDefinition = "varchar(20) DEFAULT NULL COMMENT '身份 1:DSM 2:RSM 3:市场'")
private Identity identity;
```

## 支持逻辑删除
**只需要在相应项目启动类加上注解`@EnableJpaRepositories(repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)`**
```
@SpringBootApplication
@EnableEurekaClient
@ComponentScan(value = "com.apricotforest.common")
@EnableJpaAuditing
@EnableJpaRepositories(repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class CompanyManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyManageApplication.class, args);
	}
}
```