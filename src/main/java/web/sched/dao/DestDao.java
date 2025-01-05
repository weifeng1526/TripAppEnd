package web.sched.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import web.sched.vo.Dest;

public interface DestDao {
	List<Dest> selectAllBySchId(int id);

	List<Dest> selectAllByDate(Date date);

	int insert(Dest dest);

	Dest selectLastOne();

	int update(Dest dest);
//	Dest selectById(int Id);
//
//	
//	int deleteById(int id);
}
