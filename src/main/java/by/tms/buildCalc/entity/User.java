package by.tms.buildCalc.entity;

import by.tms.buildCalc.enums.UserRoles;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "USERS")
@NamedQueries({
		@NamedQuery(name = "User.findUserByEmailAndPass", query = "select u from User u where u.email = :email and u.pass = :pass"),
		@NamedQuery(name = "User.findUserByEmail", query = "select u from User u where u.email = :email"),
		@NamedQuery(name = "User.findAllUsers", query = "select u from User u")
})
//@SecondaryTables(@SecondaryTable(name = "nametable"))

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column //(name = "users_id")
	private Long id;

	@Column //(table = "nametable") // (name = "name")
//	@Pattern(regexp = "[/d]", message = "Вы ввели недопустимое имя")
	@Size(min = 2, message = "Имя должно содержать не менее 2-х букв")
	private String name;

	@Column //(name = "age")
	@Max(value = 60, message = "Возраст должен быть не более 60")
	@Min(value = 10, message = "Возраст должен быть не ненее 10")
	private Integer age;

	@Column //(name = "email", unique = true)
	@Email(message = "Введите вашу электронную почту")
	private String email;

	@Column //(name = "pass")
	@Size(min = 3, max = 8, message = "Введите пароль от 3 до 8 символов")
	private String pass; // основной пароль для авторизации пользователя


	@Transient
	@Column(name = "doublePass")
	@Size(min = 3, max = 8, message = "Введите подтверждающий пароль от 3 до 8 символов")
	private String doublePass; // пароль подьтверждения регистрации пользоваателя

	@Transient
	@Size(min = 3, max = 8, message = "Введите административный пароль от 3 до 8 символов")
	private String altPass; // пароль для регистрации административных ролей пользователя

	@Transient
	@Size(min = 3, max = 8, message = "Введите новый пароль от 3 до 8 символов")
	private String newPass; // новый пароль при смене пароля пользователя

	@Transient // не сохраняет поле в БД
//	@Pattern(regexp = "ADMIN", message = "Неподходящее значение")
	private UserRoles userRole; // временный ключ при регистрации роли пользоваателя

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Role role; // ответвление к ролям

	@OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private UnitsConnector unitsConnector; // ответвление к юнитам

//	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//	private List<Unit1> unit1list;


	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				", email='" + email + '\'' +
				", pass='" + pass + '\'' +
				'}';
	}


	public String toStringMy() {
		return "User{" +
				"id=" + id + '\'' +
				", name='" + name + '\'' +
				", age=" + age + '\'' +
				", email='" + email + '\'' +
				", pass='" + pass + '\'' +
				", doublePass='" + doublePass + '\'' +
				", altPass='" + altPass + '\'' +
				", newPass='" + newPass + '\'' +
				", userRole=" + userRole + '\'' +
				", role=" + role + '\'' +
				", unitsConnector=" + unitsConnector + '\'' +
//				", unit1=" + unit1list +
				'}';
	}
}
