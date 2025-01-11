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
	
	List<Dest> selectByMemIdAndSchId(int memId, int schId);
	
	//已新建的行程表id、範本行程表id
	boolean insertByCopy(int schId, int schSampleId);
	
	int deleteById(int id);
}
