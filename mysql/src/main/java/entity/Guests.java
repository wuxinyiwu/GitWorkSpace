package entity;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Data 该注解能够自动创建出来get、set、toString、equals、hashCode等方法
 * @NoArgsConstructor 这个是无参构造器
 * @AllArgsConstructor 全参构造器
 * @Setter set方法
 * @Getter get方法
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Guests {
	private Integer id;
	private String firstname;
	private String lastname;
	private String email;
	private Timestamp reg_date;
}
