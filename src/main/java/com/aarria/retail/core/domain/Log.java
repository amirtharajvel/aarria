package com.aarria.retail.core.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "log")
@TableGenerator(name = "log_generator", initialValue = 0, allocationSize = 1)
public class Log {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "log_generator")
	private Long id;

	@OneToOne
	private User user;

	@Column
	private String activity;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	public Log() {
		super();
	}

	public Log(User user, String activity, Date date) {
		super();
		this.user = user;
		this.activity = activity;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
